package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.CustomerInfoMapper;
import com.mapper.MoneyManagementTableMapper;
import com.mapper.OrderPersonMapper;
import com.mapper.PurchaseExtendMapper;
import com.mapper.PurchaseTableMapper;
import com.mapper.SysMaterialMapper;
import com.mapper.SystemAccountMapper;
import com.model.CustomerInfo;
import com.model.MoneyManagementTable;
import com.model.OrderPerson;
import com.model.PurchaseExtend;
import com.model.PurchaseTable;
import com.model.SysMaterial;
import com.model.SystemAccount;
import com.pojo.AccountPurchaseSome;
import com.service.PurchaseTableService;

/**
 * 
 *<h2>采购单实现层</h2>
 *<p>主要实现接口层传过来的相关方法</p>
 * 
 *@author lily
 *
 *
 */
@Service
public class PurchaseTableServiceImpl implements PurchaseTableService {

	private static final int numAccPage = 5;
	private Gson g = new Gson();
	@Resource
	private PurchaseTableMapper pctm;
	@Resource
	private PurchaseExtendMapper pcem;
	//客户表层
	@Resource
	private CustomerInfoMapper cimapper;
	//员工层
	@Resource
	private SystemAccountMapper sam;
	//原料层
	@Resource
	private SysMaterialMapper smm;
	//人员业务中间表
	@Resource
	private OrderPersonMapper opm;
	//往来账目表
	@Resource
	private MoneyManagementTableMapper mmtm;

	
	@Override
	public String selectCustomerAll() {
		// TODO Auto-generated method stub
		String demand="卖家";
		List<CustomerInfo> customerTable=cimapper.selectByDemand(demand);		
		return g.toJson(customerTable);
	}

	@Override
	public String selectCustomerAllById(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String customerId = request.getParameter("myCustomerId");
		CustomerInfo info=cimapper.selectByPrimaryKey(customerId);
		return g.toJson(info);
	}

	@Override
	public String selectextendIdById(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String extendId = request.getParameter("extendId");
		 PurchaseExtend  extendTable=pcem.selectByPrimaryKey(extendId);
		 if(extendTable!=null) {
			 return "success";
		 }else {
			 return "notsuccess";
		 }
		
	}

	@Override
	public String selectAccountSome(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String accountId=(String)request.getSession().getAttribute("ACCOUNT");
		 SystemAccount accountTable=sam.selectByPrimaryKey(accountId);
		 AccountPurchaseSome accountSome=new AccountPurchaseSome();
		 Date newTimes=new Date();
		 SimpleDateFormat adf=new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat adff=new SimpleDateFormat("yyyy");
		 String newTime=adf.format(newTimes);
		 String year=adff.format(newTimes);
		 accountSome.setAccounName(accountTable.getAccountName());
		 accountSome.setNewTime(newTime);
		 accountSome.setYear(year);
		return g.toJson(accountSome);
	}

	@Override
	public String selectMaterialAll() {
		// TODO Auto-generated method stub
		List<SysMaterial> materialTable=smm.selectSysMaterialAll();
		return g.toJson(materialTable);
	}

	@Override
	public String addAllTable(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//获取页面数据，并转为Gson格式
		//员工id
		String accountId=(String)request.getSession().getAttribute("ACCOUNT");
		//编号
		String extendId = request.getParameter("extendId");
		//客户id
		String customerId = request.getParameter("customerId");
		//经办人
		String extendName = request.getParameter("extendName");
		//联系方式
		String extendPhone = request.getParameter("extendPhone");
		//年份
		String purchase = request.getParameter("purchase");
		//总金额
		String extendPrices = request.getParameter("extendPrices");
		//list数组
		String material = request.getParameter("material");
		Gson gson = new Gson();
		ArrayList<String> materialList = (ArrayList) gson.fromJson(material,Object.class);		
		Map<String,Object> maps = new HashMap<>();
		int num=0;
		int num1=0;
		for (int i = 0; i < materialList.size(); i++) {
			Map<String,Object> map=(Map<String, Object>) gson.fromJson(materialList.get(i), Map.class);			
			String purchaseTableId=UUID.randomUUID().toString();
			//原料id
			String materialId=(String) map.get("materialId");
			System.err.println(".....materialId....."+materialId);
			//原料数量
			double purchaseTableInt=(Double) map.get("purchaseTableInt");
			
			//原料单价
			String purchaseTablePrice=(String) map.get("purchaseTablePrice");
			//原料总价
			String purchaseTableMoney=(String) map.get("purchaseTableMoney");
			//原料备注
			String remarks=(String) map.get("remarks");
			//原料购买时间
			String purchaseTableTime=(String) map.get("purchaseTableTime");			
			PurchaseTable purch=new PurchaseTable();
			purch.setPurchaseTableId(purchaseTableId);
			purch.setExtendId(extendId);
			purch.setMaterialId(materialId);
			int count=(int)purchaseTableInt;
			double price=Double.parseDouble(purchaseTablePrice);
			double money=Double.parseDouble(purchaseTableMoney);
			purch.setPurchaseTableInt(count);
			purch.setPurchaseTablePrice(price);
			purch.setPurchaseTableMoney(money);
			purch.setRemarks(remarks);
			purch.setPurchaseTableTime(purchaseTableTime);
			purch.setPurchase(purchase);
			num=pctm.insertSelective(purch);
			OrderPerson person=new OrderPerson();
			person.setOrderPersonId(UUID.randomUUID().toString());
			//增加人员中间表信息
			person.setPurchaseId(purchaseTableId);
			person.setAccountId(accountId);
			person.setBusiness("采购完成");
			person.setOrderPersonTime(new Date());
			num1=opm.insert(person);
		}
		int num3=0;
		if(num>0 && num1>0) {
			PurchaseExtend purchaseExtend =new PurchaseExtend();
			purchaseExtend.setExtendId(extendId);
			purchaseExtend.setCustomerId(customerId);
			purchaseExtend.setExtendName(extendName);
			purchaseExtend.setExtendPhone(extendPhone);
			purchaseExtend.setExtendPrices(extendPrices);
			purchaseExtend.setExtendTime(new Date());
			num3=pcem.insert(purchaseExtend);			
		}
		int num4=0;
		if(num3>0) {
			MoneyManagementTable mmtable=new MoneyManagementTable();
			mmtable.setMoneyManId(UUID.randomUUID().toString());
			mmtable.setOrderId(extendId);
			mmtable.setCustomerId(customerId);
			double paidamout=Double.parseDouble(extendPrices);
			mmtable.setPaidAmount(paidamout);
			mmtable.setAllMoney(paidamout);
			mmtable.setCompleteTime(new Date());
			num4=mmtm.insertSelective(mmtable);
		}
		if(num4>0) {
			return "success";
		}else {
			return "notsuccess";
		}
				
	}

	@Override
	public String selectAllPurchaseId(HttpServletRequest request) {
		
		// TODO Auto-generated method stub
		String searchCard = request.getParameter("searchCard");
		List<PurchaseTable> purchaseTable=pctm.selectExtendIdByYear(searchCard);
		
		for (int i = 0; i < purchaseTable.size(); i++) {
			
			for (int j = i+1; j < purchaseTable.size(); j++) {
				if(purchaseTable.get(i).getExtendId().equals(purchaseTable.get(j).getExtendId())) {
					purchaseTable.remove(j);
					j--;
				}				
			}			
		}
		for (int i = 0; i < purchaseTable.size(); i++) {
			System.err.println("   purchaseTable.get(i).getExtendId()     "+purchaseTable.get(i).getExtendId());
		}
		return g.toJson(purchaseTable);
	}

	@Override
	public String selectExtendAllById(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String extendId = request.getParameter("myextendId");
		 PurchaseExtend  extendTable=pcem.selectByPrimaryKey(extendId);		
		 CustomerInfo customerInfo=cimapper.selectByPrimaryKey( extendTable.getCustomerId());		
		 extendTable.setCustomerId( customerInfo.getCustomerName());
		return g.toJson(extendTable);
	}

	@Override
	public String selectTableAllById(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String extendId = request.getParameter("myextendId");
		
		List<PurchaseTable> purchaseTable=pctm.selectAllByExtendId(extendId);
		for (PurchaseTable purchaseTable2 : purchaseTable) {		
			String mId=purchaseTable2.getMaterialId();
			if(mId==null) {
				mId="";
			}
			SysMaterial SysMaterial =smm.selectByPrimaryKey(mId);
			String name="";
			if(SysMaterial!=null) {
				 name=smm.selectByPrimaryKey(mId).getMaterialName();
			}
			purchaseTable2.setMaterialId(name);
		}
		return g.toJson(purchaseTable);
	}

	@Override
	public String selectAccountName(HttpServletRequest request) {
		// TODO Auto-generated method stub、
		String extendId = request.getParameter("id");
		 List<PurchaseTable> table=pctm.selectAllByExtendId(extendId);
		 String nameme="null";
		 if(table!=null) {
			 String purchaseTableId=table.get(0).getPurchaseTableId();
			
			 List<OrderPerson> oder11=opm.selectNameByTableId(purchaseTableId);
			 OrderPerson oder= oder11.get(0);
			 String name=oder.getAccountId();
			
			 SystemAccount namehh=sam.selectByPrimaryKey(name);
			 nameme= namehh.getAccountName(); 
		 }		 
		return nameme;
	}	
}
