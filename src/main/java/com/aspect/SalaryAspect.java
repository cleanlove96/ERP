package com.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.mapper.SysRoleMapper;
import com.mapper.SysSalaryLogMapper;
import com.mapper.SystemAccountMapper;
import com.model.SysSalaryLog;
import com.model.SystemAccount;
/**
 * <p>
 * 薪资调整和薪资结算的日志记录
 * </p>	
 * @Copyright (C),华清远见
 * @author 小小
 * @Date:2018年6月30日
 */
@Aspect
@Component
public class SalaryAspect {
	
	@Resource
	private SystemAccountMapper sam;
	@Resource
	private SysSalaryLogMapper sslm;
	@Resource
	private SysRoleMapper srm;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	/**
	 * <p>
	 * 薪资结算日志
	 * </p>
	 * @author 小小
	 * @Date 2018年7月4日
	 * @param jp
	 * @param result
	 */
	@AfterReturning(value="execution(* com.service.MinistryService.liquidation(..))",returning="result")
	public void outSalaryLog(JoinPoint jp,String result) {
		if(Integer.parseInt(result)>0) {
			Object[] args=jp.getArgs();
			HttpServletRequest request=(HttpServletRequest) args[0];
			String accountId = (String) request.getSession().getAttribute("ACCOUNT");
			Thread t = new Thread(()->{ 
				SystemAccount sa =sam.selectByPrimaryKey(accountId);
				/*
			String pay = request.getParameter("pay");
			可以接受请求里的数据
				 */
				SysSalaryLog ssl =  new SysSalaryLog();
				ssl.setSalaryLogDate(sdf.format(new Date()));
				ssl.setSalaryLogId(UUID.randomUUID().toString());
				ssl.setAccountLoginId(sa.getAccountName());
				ssl.setSalaryLogType(sdf.format(new Date())+"发放工资");
				sslm.insertSelective(ssl);
			});
			t.start();
		}else
			return;
	}
	
	/**
	 * <p>
	 * 基本职位的工资修改日志
	 * </p>
	 * @author 小小
	 * @Date 2018年7月4日
	 * @param jp
	 * @param res
	 */
	@AfterReturning(value="execution(* com.service.MinistryService.changeMinistry(..))",returning="res")
	public void chageSalaryLog(JoinPoint jp,String res) {
		if(res.equals("SUCCESS")) {
			Object[] args=jp.getArgs();
			HttpServletRequest request= (HttpServletRequest) args[0];
			String accountId = (String) request.getSession().getAttribute("ACCOUNT");
			String id = request.getParameter("id");
			String salary = request.getParameter("salary");
			Thread t = new Thread(()-> { 
				String  sr  = srm.selectNameBySalaryId(id);
				SystemAccount sa =sam.selectByPrimaryKey(accountId);
				SysSalaryLog ssl =  new SysSalaryLog();
				ssl.setSalaryLogDate(sdf.format(new Date()));
				ssl.setSalaryLogId(UUID.randomUUID().toString());
				ssl.setAccountLoginId(sa.getAccountName());
				ssl.setSalaryLogType(sdf.format(new Date())+sa.getAccountName()+"修改"+sr+"基本工资为"+salary);
				sslm.insertSelective(ssl);
			}) ;
			t.start();
		}
		else 
			return;
	}
	/**
	 * <p>
	 * 修改员工的特调工资日志
	 * </p>
	 * @author 小小
	 * @Date 2018年7月4日
	 * @param jp
	 * @param res
	 */
	@AfterReturning(value="execution(* com.service.MinistryService.changeIndividual(..))",returning="res")
	public void changeIndividualLog(JoinPoint jp,String res) {
		if(res.equals("SUCCESS")) {
		Object[] args = jp.getArgs();
		HttpServletRequest request =(HttpServletRequest) args[0];
		String accountId = (String) request.getSession().getAttribute("ACCOUNT");
		String id = request.getParameter("id");
		String salary = request.getParameter("salary");
		Thread t = new Thread(()-> { 
			String  sr  = srm.selectAccountNameBySalaryId(id);
			SystemAccount sa =sam.selectByPrimaryKey(accountId);
			SysSalaryLog ssl =  new SysSalaryLog();
			ssl.setSalaryLogDate(sdf.format(new Date()));
			ssl.setSalaryLogId(UUID.randomUUID().toString());
			ssl.setAccountLoginId(sa.getAccountName());
			ssl.setSalaryLogType(sdf.format(new Date())+sa.getAccountName()+"修改"+sr+"特调工资为"+salary);
			sslm.insertSelective(ssl);
		}) ;
		t.start();
		}else
			return;
	}
}
