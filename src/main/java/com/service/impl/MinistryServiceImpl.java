package com.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mapper.CurrentAssetsMapper;
import com.mapper.DateTypeTableMapper;
import com.mapper.IndividualSalaryMapper;
import com.mapper.PayrollTableMapper;
import com.mapper.SalaryScaleTableMapper;
import com.mapper.SysRoleMapper;
import com.mapper.SystemAccountMapper;
import com.mapper.WorkdayRecordMapper;
import com.model.CurrentAssets;
import com.model.DateTypeTable;
import com.model.IndividualSalary;
import com.model.PayrollTable;
import com.model.SalaryScaleTable;
import com.model.SystemAccount;
import com.model.WorkdayRecord;
import com.pojo.BaseSalaryPojo;
import com.pojo.Page;
import com.pojo.SalaryScalePojo;
import com.service.MinistryService;

@Service
@RunWith(SpringJUnit4ClassRunner.class)//将测试运行环境指定为SpringIOC环境
@ContextConfiguration("classpath:spring.xml")//设置IOC容器使用的配置文件
public class MinistryServiceImpl implements MinistryService {
	@Resource
	private SysRoleMapper srm;
	@Resource
	private SalaryScaleTableMapper sstm;
	@Resource
	private IndividualSalaryMapper ism;
	@Resource
	private SystemAccountMapper sam;
	@Resource
	private WorkdayRecordMapper	wrm;
	@Resource
	private PayrollTableMapper	ptm;
	@Resource
	private CurrentAssetsMapper cam;
	@Resource
	private DateTypeTableMapper dttm;
	private static Gson gson = new Gson();
	@Override
	public String getPage(HttpServletRequest request) {
		int pageRecords = Integer.parseInt(request.getParameter("pageRecords"));
		Page p = new Page();
		int tn = sstm.getRoleNum();
		p.setTotalRecords(tn + "");
		int t = 0;
		if (tn % pageRecords != 0) {
			t = tn / pageRecords + 1;
		} else {
			t = tn / pageRecords;
		}
		p.setTotal(t + "");
		return gson.toJson(p);
	}

	@Override
	public String getMinistryTable(HttpServletRequest request) {
		String n = request.getParameter("n");
		int pageRecords = Integer.parseInt(request.getParameter("pageRecords"));
		int pnum = 1;
		if (n != null && n.length() > 0) {
			pnum = Integer.parseInt(n);
		}
		Map<String, Object> map = new HashMap<>();
		int start = (pnum - 1) * pageRecords;
		map.put("start", start);
		map.put("size", pageRecords);
		List<SalaryScalePojo> sst = sstm.getMinistryTable(map);
		return gson.toJson(sst);
	}

	@Override
	public String changeMinistry(HttpServletRequest request) {
		String id = request.getParameter("id");
		String salary = request.getParameter("salary");
		Date time = new Date();
		SalaryScaleTable sst = new SalaryScaleTable();
		sst.setChangeTime(time);
		sst.setSalaryScaleId(id);
		sst.setSalary(Double.parseDouble(salary));
		int n = sstm.updateByPrimaryKeySelective(sst);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}

	@Override
	public String getMinistryTableByAccountId(HttpServletRequest request) {
		String id = request.getParameter("id");
		SalaryScalePojo ssp = sstm.selectByAccountId(id);
		if(ssp==null) {
			return "ERROR";
		}
		return gson.toJson(ssp);
	}

	@Override
	public String changeIndividual(HttpServletRequest request) {
		String id = request.getParameter("id");
		String salary = request.getParameter("salary");
		Date time = new Date();
		IndividualSalary  is = new IndividualSalary();
		is.setAdjustTime(time);
		is.setAdjustMoney(Double.parseDouble(salary));
		is.setIndividualSalaryId(id);
		int n = ism.updateByPrimaryKeySelective(is);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}
	@Override
	public String showSalary(HttpServletRequest request) {
		List<PayrollTable> pt = new ArrayList<PayrollTable>();
		Map<String, String> map = new HashMap<>();
//		获取上月时间区间
		Calendar calendar  =Calendar.getInstance();
		int year =calendar.get(Calendar.YEAR); 
		int month = calendar.get(Calendar.MONTH);
		if(month==0)
			month = 12;
		String start = year+"-"+month+"-01";//2018-6-01
		String end = year+"-"+month+"-31";//2018-6-31
//		工资计算起止时间
		map.put("start", start);
		map.put("end", end);
		
//		部门id
		String sectionId = request.getParameter("id");
//		是否是计算全公司工资的判断
		List<SystemAccount> sa= new ArrayList<>();
		if(sectionId==""||sectionId.equals("53°D酒业有限公司")) {
			sa = sam.selectAllWorkTime(map);
			if(sa.size()==0) {
				return "ERROR";
			}
		}else {
//		出勤状态码为0
			map.put("stuts", "0");
//		部门id
			map.put("sectionId",sectionId );
			sa = sam.selectBySectionWorkTime(map);
			if(sa.size()==0) {
				return "ERROR";
			}
		}
		for (SystemAccount sal : sa) {
			PayrollTable ppt = new PayrollTable();
//			员工id
			String id = sal.getAccountId();
			ppt.setAccountId(id);
//			员工
			ppt.setAccountName(sal.getAccountName());
//			员工编号
			ppt.setAccountNum(sal.getAccountNum());
//			工资条id
			ppt.setPayrollId(UUID.randomUUID().toString());
//			基本工资和特调工资
			BaseSalaryPojo bsp = ism.selectBaseMonerAndOtherMoney(id);
			if(bsp==null) {
				return "ERROR";
			}
//			特调工资
			double adjustm=0.00;
			if(bsp.getAdjustMoney()!=null) {
				adjustm = bsp.getAdjustMoney();
			}
			
//			基本工资
			double salary = bsp.getSalary();
//			实际基本工资
			Double atual = salary+adjustm;
			ppt.setSalary(atual);
//			正常上班天数
			map.put("accountId", id);
			map.put("stuts", "0");
			int n = wrm.selectWorkDayByAccountId(map);
			double num = atual/21.75*n;
			double money =new BigDecimal(num).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
//			休息日加班天数
			map.put("stuts", "1");
			int m = wrm.selectWorkDayByAccountId(map);
//			休息日加班费
			double num1 = salary/21.75*m*2.0;
//			节假日加班天数
			map.put("stuts", "2");
			int j = wrm.selectWorkDayByAccountId(map);
//			节假日加班费
			double j1 = salary/21.75*j*3.0;
			double overpay =new BigDecimal(num1+j1).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setOvertimePay(overpay);
			SystemAccount ac = sam.selectByPrimaryKey(id);
			double sbgr = 0.0;
			double sbgs = 0.0;
			if(ac.getAccountStatus().equals("ON")) {
//			成都社保缴纳基数为下下限3067上限15333
//			公积金基数为下限1500上限20972
//			下限五险一金
			if(salary<=1500) {
//			养老保险个人
			double yls =new BigDecimal(3067*0.08).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setYanglaoSelf(yls);
//			养老保险公司
			double yl =new BigDecimal(3067*0.19).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setYanglaoCompany(yl);
//			医疗保险个人
			double ys =new BigDecimal(3067*0.02).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setYiliaoSelf(ys);
//			医疗保险公司
			double y =new BigDecimal(3067*0.065).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setYiliaoCompany(y);
//			失业保险个人
			double ss =new BigDecimal(3067*0.004).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setShiyeSelf(ss);
//			失业保险公司
			double sc =new BigDecimal(3067*0.006).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setShiyeCompany(sc);
//			工伤保险个人
			ppt.setGongshangSelf(0.00);
//			工伤保险公司
			double g =new BigDecimal(3067*0.0022).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setGongshangCompany(g);
//			生育保险个人
			ppt.setShengyuSelf(0.00);
//			生育保险公司
			double s =new BigDecimal(3067*0.006).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setShengyuCompany(s);			
//			公积金个人
			double h =new BigDecimal(1500*0.06).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setHouseFundSelf(h);
//			公积金公司
			ppt.setHouseFundCompany(h);
			 sbgr = yls+ys+ss+h;
			 sbgs = yl+y+sc+g+s+h;
			}
			if(salary>1500&&salary<=15333) {
//				养老保险个人
				double yls =new BigDecimal(salary*0.08).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setYanglaoSelf(yls);
//				养老保险公司
				double yl =new BigDecimal(salary*0.19).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setYanglaoCompany(yl);
//				医疗保险个人
				double ys =new BigDecimal(salary*0.02).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setYiliaoSelf(ys);
//				医疗保险公司
				double y =new BigDecimal(salary*0.065).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setYiliaoCompany(y);
//				失业保险个人
				double ss =new BigDecimal(salary*0.004).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setShiyeSelf(ss);
//				失业保险公司
				double sc =new BigDecimal(salary*0.006).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setShiyeCompany(sc);
//				工伤保险个人
				ppt.setGongshangSelf(0.00);
//				工伤保险公司
				double g =new BigDecimal(salary*0.0022).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setGongshangCompany(g);
//				生育保险个人
				ppt.setShengyuSelf(0.00);
//				生育保险公司
				double s =new BigDecimal(salary*0.006).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setShengyuCompany(s);			
//				公积金个人
				double h =new BigDecimal(salary*0.06).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setHouseFundSelf(h);
//				公积金公司
				ppt.setHouseFundCompany(h);
				 sbgr = yls+ys+ss+h;
				 sbgs = yl+y+sc+g+s+h;
			}
			if(salary>15333&&salary<=20972){
//				养老保险个人
				double yls =new BigDecimal(15333*0.08).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setYanglaoSelf(yls);
//				养老保险公司
				double yl =new BigDecimal(15333*0.19).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setYanglaoCompany(yl);
//				医疗保险个人
				double ys =new BigDecimal(15333*0.02).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setYiliaoSelf(ys);
//				医疗保险公司
				double y =new BigDecimal(15333*0.065).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setYiliaoCompany(y);
//				失业保险个人
				double ss =new BigDecimal(15333*0.004).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setShiyeSelf(ss);
//				失业保险公司
				double sc =new BigDecimal(15333*0.006).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setShiyeCompany(sc);
//				工伤保险个人
				ppt.setGongshangSelf(0.00);
//				工伤保险公司
				double g =new BigDecimal(15333*0.0022).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setGongshangCompany(g);
//				生育保险个人
				ppt.setShengyuSelf(0.00);
//				生育保险公司
				double s =new BigDecimal(15333*0.006).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setShengyuCompany(s);			
//				公积金个人
				double h =new BigDecimal(salary*0.06).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setHouseFundSelf(h);
//				公积金公司
				ppt.setHouseFundCompany(h);
				 sbgr = yls+ys+ss+h;
				 sbgs = yl+y+sc+g+s+h;
			}
			if(salary>20972) {
//				养老保险个人
				double yls =new BigDecimal(15333*0.08).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setYanglaoSelf(yls);
//				养老保险公司
				double yl =new BigDecimal(15333*0.19).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setYanglaoCompany(yl);
//				医疗保险个人
				double ys =new BigDecimal(15333*0.02).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setYiliaoSelf(ys);
//				医疗保险公司
				double y =new BigDecimal(15333*0.065).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setYiliaoCompany(y);
//				失业保险个人
				double ss =new BigDecimal(15333*0.004).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setShiyeSelf(ss);
//				失业保险公司
				double sc =new BigDecimal(15333*0.006).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setShiyeCompany(sc);
//				工伤保险个人
				ppt.setGongshangSelf(0.00);
//				工伤保险公司
				double g =new BigDecimal(15333*0.0022).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setGongshangCompany(g);
//				生育保险个人
				ppt.setShengyuSelf(0.00);
//				生育保险公司
				double s =new BigDecimal(15333*0.006).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setShengyuCompany(s);			
//				公积金个人
				double h =new BigDecimal(20972*0.06).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				ppt.setHouseFundSelf(h);
//				公积金公司
				ppt.setHouseFundCompany(h);
				 sbgr = yls+ys+ss+h;
				 sbgs = yl+y+sc+g+s+h;
			}
		}else {
			ppt.setYanglaoSelf(0.0);
			ppt.setYanglaoCompany(0.0);
			ppt.setYiliaoSelf(0.0);
			ppt.setYiliaoCompany(0.0);
			ppt.setShiyeSelf(0.0);
			ppt.setShiyeCompany(0.0);
			ppt.setGongshangSelf(0.00);
			ppt.setGongshangCompany(0.0);
			ppt.setShengyuSelf(0.00);
			ppt.setShengyuCompany(0.0);
			ppt.setHouseFundSelf(0.0);
			ppt.setHouseFundCompany(0.0);
		}
//			税前工资
			double atualmoney=money+overpay-sbgr;
			double pay=0.00;
//			个税免征额为3500元
			if(atualmoney<=3500) {
				ppt.setOverBase(0.0);
				ppt.setPayMoney(0.0);
			}else {
//			应税工资
			double over1  = atualmoney-3500;
			double over =new BigDecimal(over1).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setOverBase(over);
//			个人所得税 =应纳税所得额*税率-速算扣除数
			double deduct=0.0;
			if(over>80000) {
				deduct = (over-80000)*0.45-13505;
				over=80000;
			}
			if(55000<over && over<=80000) {
				deduct += 25000*0.35-5505;
				over=55000;
			}
			if(35000<over && over<=55000) {
				deduct += 20000*0.30-2755;
				over=35000;
			}
			if(9000<over && over<=35000) {
				deduct += 26000*0.25-1005;
				over=9000;
			}
			if(4500<over && over<=9000) {
				deduct += 4500*0.20-555;
				over=4500;
			}
			if(1500<over && over<=4500) {
				deduct += 3000*0.10-105;
				over=1500;
			}
			if(over<=1500) {
				deduct += 1500*0.10-105;
			}
//			个人所得税
			 pay =new BigDecimal(deduct).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setPayMoney(pay);
		}
//			多余字段		
//			税率
			ppt.setUseTaxRate(0.0);
//			速算扣除数存放(公司发的工资+缴纳的五险一金总数，方便计算财务)
			double qd =new BigDecimal(sbgs+money+overpay).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setQuickDeduct(qd);
//			end  多余字段
			
//			个人五险一金扣款
			ppt.setVacateDeduct(sbgr);
			
//			实发工资 
			double finallypay =new BigDecimal(atualmoney-pay).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			ppt.setFinallyPay(finallypay);
			pt.add(ppt);
		}
		System.out.println(pt.toString());
		return  gson.toJson(pt);
	}

	@Override
	public String liquidation(HttpServletRequest request) {
		Calendar calendar  =Calendar.getInstance();
		int year =calendar.get(Calendar.YEAR); 
		int month = calendar.get(Calendar.MONTH)+1;
		String pay = request.getParameter("pay");
		if(pay==""||pay==null) {
			return "0";
		}
		
		int sum = 0;
		double sumgs=0.0;
		CurrentAssets ca = new CurrentAssets();
		List<PayrollTable> pt = new ArrayList<>();
		pt = gson.fromJson(pay, new TypeToken<List<PayrollTable>>(){}.getType());
		for (PayrollTable payrollTable : pt) {
//			已发工资状态改为1.0（多余税率字段替为状态用）
			payrollTable.setUseTaxRate(1.0);
//			结算时间
			payrollTable.setCloseAccountTime(new Date());
			int n = ptm.insertSelective(payrollTable);
			sum+=n;
//			本次发工资公司总支出
			sumgs += payrollTable.getQuickDeduct();
		}
//		为总账添加发放工资数据，流动资金减少
		ca.setAllAssetsId(UUID.randomUUID().toString());
		double money =new BigDecimal(sumgs).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		ca.setCurrentAssets(-money);
		ca.setAssetsTime(new Date());
		ca.setAssetsDescribe(year+"年"+month+"月工资发放");
		cam.insertSelective(ca);
		return sum+"";
	}

	@Override
	public String isLiquidation() {
//		获取本月时间区间
		Calendar calendar  =Calendar.getInstance();
		int year =calendar.get(Calendar.YEAR); 
		int month = calendar.get(Calendar.MONTH)+1;
		String start = year+"-"+month+"-01";//2018-7-01
		String end = year+"-"+month+"-31";//2018-7-31
		Map<String, String> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		map.put("state","1");
		int n = ptm.isLiquidation(map);
		return n>0?"yes":"no";
	}

	@Override
	public String accountClock(HttpServletRequest request) {
		String str= this.isClock(request);
		if(str.equals("1")) {
			return "ERROR";
		}else {
		String id = (String) request.getSession().getAttribute("ACCOUNT");
		WorkdayRecord wr = new WorkdayRecord();
		wr.setWorkdayRecordId(UUID.randomUUID().toString());
		wr.setAccountId(id);
//		获取当前时间，判断是否是正确时间打卡，以及打卡类型是否为加班
		Calendar calendar  =Calendar.getInstance();
		int time = calendar.get(Calendar.HOUR_OF_DAY);
		int year =calendar.get(Calendar.YEAR); 
		int month = calendar.get(Calendar.MONTH)+1;
		int dayM = calendar.get(Calendar.DAY_OF_MONTH);
		String dayNow;
		String monthNow;
		if(dayM<10) {
			dayNow = "0"+dayM;
		}else {
			dayNow = ""+dayM;
		}
		if(month<10) {
			monthNow = "0"+month;
		}else {
			monthNow = ""+month;
		}
		String dateTime = year+"-"+monthNow+"-"+dayNow;//2018-07-05
		//24小时制 8-9 点之间值为8
		if(time==8) {
			wr.setPunchTime(new Date());
		}else {
			return "WARING";
		}
		int day = calendar.get(Calendar.DAY_OF_WEEK);
        /*星期日:Calendar.SUNDAY=1
         *星期一:Calendar.MONDAY=2
         *星期二:Calendar.TUESDAY=3
         *星期三:Calendar.WEDNESDAY=4
         *星期四:Calendar.THURSDAY=5
         *星期五:Calendar.FRIDAY=6
         *星期六:Calendar.SATURDAY=7 */
		DateTypeTable dtt = dttm.selectByDate(dateTime);
		switch (day) {
		case 1:
//			休息日
			if(dtt==null) {
				wr.setWorkdayType("1");
			}
//			节假日
			else if(dtt.getDateTypeType().equals("休")) {
				wr.setWorkdayType("2");
			}
//			工作日
			else {
				wr.setWorkdayType("0");
			}
			break;
		case 7:
//			休息日
			if(dtt==null) {
				wr.setWorkdayType("1");
			}
//			节假日
			else if(dtt.getDateTypeType().equals("休")) {
				wr.setWorkdayType("2");
			}
//			工作日
			else {
				wr.setWorkdayType("0");
			}
			break;
		default:
//			工作日
			if(dtt==null) {
				wr.setWorkdayType("0");
			}
//			节假日
			else if(dtt.getDateTypeType().equals("休")) {
				wr.setWorkdayType("2");
			}
//			休息日
			else {
				wr.setWorkdayType("1");
			}
			break;
		}
		int n = wrm.insertSelective(wr);
		int m=0;
		if(n>0) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			String date2 = sdf.format(date);
			Map<String, String> map = new HashMap<>();
			map.put("id", id);
			map.put("time", "%"+date2+"%");
			m = wrm.selectIsClock(map);
		}
		return m>0?m+"":"ERROR";
		}
	}
	
	@Test
	public void test() {
		Calendar calendar  =Calendar.getInstance();
		int am = calendar.get(Calendar.HOUR_OF_DAY);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		int dayM = calendar.get(Calendar.DAY_OF_MONTH);
		int year =calendar.get(Calendar.YEAR); 
		int month = calendar.get(Calendar.MONTH)+1;
		String dayNow;
		if(dayM<10) {
			dayNow = "0"+dayM;
		}else {
			dayNow = ""+dayM;
		}
			
		String dateTime = year+"-0"+month+"-"+dayNow;//2018-07-05
		System.out.println("------"+dateTime);
        /*星期日:Calendar.SUNDAY=1
         *星期一:Calendar.MONDAY=2
         *星期二:Calendar.TUESDAY=3
         *星期三:Calendar.WEDNESDAY=4
         *星期四:Calendar.THURSDAY=5
         *星期五:Calendar.FRIDAY=6
         *星期六:Calendar.SATURDAY=7 */
		switch (day) {
		case 1:
			System.out.println("周日");
			break;
		case 7:
			System.out.println("周六");
			break;
		default:
			System.out.println("正常工作日");
			break;
		}
		
	}

	@Override
	public String isClock(HttpServletRequest request) {
		String id = (String) request.getSession().getAttribute("ACCOUNT");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(date);
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("time", "%"+time+"%");
		int n = wrm.selectIsClock(map);
		return n+"";
	}

	@Override
	public String addDateType(DateTypeTable dtt) {
		dtt.setChangeTime(new Date());
		dtt.setDateTypeId(UUID.randomUUID().toString());
		int n = dttm.insertSelective(dtt);
		return n>0?"SUCCESS":"ERROR";
	}

	@Override
	public String getDayPage(HttpServletRequest request) {
		String type = request.getParameter("status");
		int num = dttm.selectByType("%"+type+"%");
		Page p = new Page();
		int n = 0;
		if(num%5!=0) {
			n = num/5+1;
		}else
			n = num/5;
		p.setTotal(n+"");
		p.setTotalRecords(num+"");
		return gson.toJson(p);
	}

	@Override
	public String getDayTable(HttpServletRequest request) {
		String type = request.getParameter("status");
		String n = request.getParameter("n");
		int pnum = 1;
		if (n != null && n.length() > 0) {
			pnum = Integer.parseInt(n);
		}
		Map<String, Object> map = new HashMap<>();
		int start = (pnum - 1) * 5;
		map.put("start", start);
		map.put("size", 5);
		map.put("type", "%"+type+"%");
		List<DateTypeTable>  dtt= dttm.selectTableByType(map);
		return gson.toJson(dtt);
	}

	@Override
	public void dayEdit(HttpServletRequest request) {
		String id = request.getParameter("dateTypeId");
		DateTypeTable dtt = dttm.selectByPrimaryKey(id);
		request.setAttribute("day", dtt);
	}

	@Override
	public String updateDateType(DateTypeTable dtt) {
		dtt.setChangeTime(new Date());
		int n = dttm.updateByPrimaryKeySelective(dtt);
		return n>0?"SUCCESS":"ERROR";
	}

	@Override
	public String deleteDateType(HttpServletRequest request) {
		String id= request.getParameter("id");
		int n = dttm.deleteByPrimaryKey(id);
		return n>0?"SUCCESS":"ERROR";
	}

}
