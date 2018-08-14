package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.SystemAccountMapper;
import com.mapper.SystemCommodityInformationMapper;
import com.mapper.YearpanlTableMapper;
import com.model.SystemAccount;
import com.model.SystemCommodityInformation;
import com.model.YearpanlTable;
import com.pojo.AccountPage;
import com.service.YearplanService;
/**
 * 
 *<h2>生产总计划表实现层</h2>
 *<p>实现接口层所有的方法</p>
 * 
 *@author lily
 *
 *
 */
@Service
public class YearplanServiceImpl implements YearplanService {
	private static final int numAccPage = 5;
	
	private Gson g=new Gson();
	
	@Resource
	private YearpanlTableMapper yptm;
	@Resource
	private SystemAccountMapper sam;
	@Resource
	private SystemCommodityInformationMapper scim;
	
	
	
	@Override
	public String getYearplanTable(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String n = request.getParameter("n");

		String searchCard = request.getParameter("searchCard");
		String mycommodity = request.getParameter("mycommodity");
		if (searchCard == null) {
			searchCard = "";
		}
		if (mycommodity == null) {
			mycommodity = "";
		}
		int anum = 1;
		if (n != null && n.length() > 0) {
			anum = Integer.parseInt(n);
		}
		Map<String, Object> map = new HashMap<>();
		int start = (anum - 1) * numAccPage;
		map.put("start", start);
		map.put("size", numAccPage);
		map.put("searchCard", "%"+searchCard+"%");
		map.put("myCommodity", "%"+mycommodity+"%");
		List<YearpanlTable> yearpanlTable = yptm.getYearplanTable(map);
		for (YearpanlTable yearpanlTable2 : yearpanlTable) {
			SystemAccount sa=sam.selectByPrimaryKey(yearpanlTable2.getAccountId());
			SystemCommodityInformation sinfomation=scim.selectByPrimaryKey(yearpanlTable2.getCommodityId());
			String accountname="";
			String commodityname="";
			if(sa!=null) {
				accountname=sa.getAccountName();
			}
			if(sinfomation!=null) {
				commodityname=sinfomation.getCommodityName();
			}
			yearpanlTable2.setAccountId(accountname);
			yearpanlTable2.setCommodityId(commodityname);
		
		}
		return g.toJson(yearpanlTable);
	}

	@Override
	public String getPage(HttpServletRequest request) {
		AccountPage apage = new AccountPage();
		String searchCard = request.getParameter("searchCard");
		String mycommodity = request.getParameter("mycommodity");
		if (mycommodity == null) {
			mycommodity = "";
		}
		
		if (searchCard == null) {
			searchCard = "";
		}
		Map<String, Object> map = new HashMap<>();
		map.put("searchCard", "%"+searchCard+"%");
		map.put("myCommodity","%"+mycommodity+"%");
		int tn = yptm.selectCount(map);
		apage.setTotalRecords(tn + "");
		int t1 = tn % numAccPage;
		int t = tn / numAccPage;
		if (t1 > 0) {
			t = t + 1;
		}
		apage.setTotal(t + "");
		return g.toJson(apage);
	}

	@Override
	public String selectAllCommodity() {
		List<SystemCommodityInformation> sinfomations=scim.selectAllCommodity();
		if(sinfomations==null) {
			return "";
		}else {
			return g.toJson(sinfomations);
		}	
	}

	@Override
	public String yearplanAdd(HttpServletRequest request,YearpanlTable yplan) {
		// TODO Auto-generated method stub
		String accountId=(String)request.getSession().getAttribute("ACCOUNT");
		yplan.setYearpanlTableId(UUID.randomUUID().toString());
		yplan.setAccountId(accountId);
		int num=yptm.insertSelective(yplan);
		if(num>0) {
			return "success";
		}else {
			return "error";
		}
		
	}

	@Override
	public String deleteCommodityById(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int num=yptm.deleteByPrimaryKey(request.getParameter("id"));
		System.err.println("....id......"+request.getParameter("id"));
		if(num>0) {
			return "success";
		}else {
			return "notsuccess";
		}
	}

	@Override
	public YearpanlTable selectByYearpanlTableId(HttpServletRequest request) {
		// TODO Auto-generated method stub	
		YearpanlTable table=yptm.selectByPrimaryKey(request.getParameter("yearpanlTableId"));
		return table;
	}

	@Override
	public String yearplanUpdate(HttpServletRequest request, YearpanlTable yearTable) {
		// TODO Auto-generated method stub
		yearTable.setAccountId((String)request.getSession().getAttribute("ACCOUNT"));
		int num=yptm.updateByPrimaryKey(yearTable);
		System.err.println(".........yearTable.........."+yearTable.getYearpanlTableId());
		if(num>0) {
			return "success";
		}else {
			return "notsuccess";
		}
	
	}

	
}
