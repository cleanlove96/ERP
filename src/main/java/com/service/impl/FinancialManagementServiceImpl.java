/*****************************************************
 *  HISTORY
 *  FileName:FinancialManagementServiceImpl.java
 *  Package:com.service.impl
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年6月29日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.GoodsOutInStoreMapper;
import com.mapper.SaleBillsMapper;
import com.pojo.ChenBenLiRun;
import com.pojo.CostProfitTable;
import com.pojo.Page;
import com.pojo.SaleBillDetail;
import com.service.FinancialManagementService;

/**
 * <p>
 * 财务模块的实现层,获取到销售总金额，成本金额，；利润
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年6月29日
 */
@Service
public class FinancialManagementServiceImpl implements FinancialManagementService {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static Gson gson = new Gson();
	private static final int numPerPage = 5;
	@Resource
	private GoodsOutInStoreMapper gosm;
	@Resource
	private SaleBillsMapper sbm;

	/***
	 * saleSumTotal 销售总金额 cost 成本金额 profit 利润金额
	 */
	@Override
	public CostProfitTable getCostProfitTable(HttpServletRequest request) {
		CostProfitTable cpft = new CostProfitTable();
		double saleSumTotal = gosm.querySaleSumTotal();
		cpft.setSaleSumTotal(saleSumTotal);
		System.out.println("6666666" + cpft.getSaleSumTotal());
		double cost = gosm.queryCostTotal();
		cpft.setCost(cost);
		double profit = saleSumTotal - cost;
		cpft.setProfit(profit);
		System.out.println("销售总金额：" + saleSumTotal);
		System.out.println("成本总金额：" + cost);
		System.out.println("利润总金额：" + profit);
		return cpft;
	}

	/****
	 * 查询所有的销售单
	 */
	@Override
	public String getSaleBillDetail(HttpServletRequest request) {
		String num = request.getParameter("n");
		String search = request.getParameter("search");
		System.out.println("搜索了什么" + search);
		System.out.println("页码" + num);
		if (search == null) {
			search = "";
		}
		int pum = 1;
		if (num != null && num.length() > 0) {
			pum = Integer.parseInt(num);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int start = (pum - 1) * numPerPage;
		map.put("start", start);
		map.put("size", numPerPage);
		map.put("search", "%" + search + "%");
		List<SaleBillDetail> list = sbm.getAllSaleBillDetail(map);
		System.out.println("拿到集合数据了" + list);
		return gson.toJson(list);
	}

	/***
	 * 获取总的页码数
	 */
	@Override
	public String getAllBillsPage(HttpServletRequest request) {
		String search = request.getParameter("search");

		if (search == null) {
			search = "";
		}
		Page page = new Page();
		int sum = sbm.getTotalNum("%" + search + "%");
		System.out.println("查询的总数量" + sum);
		page.setTotalRecords(sum + "");
		int tn = 0;
		if (sum % 5 > 0) {
			tn = (sum / 5) + 1;

		} else {
			tn = (sum / 5);
		}

		page.setTotal(tn + "");
		System.out.println("总页码：" + page.getTotal());
		System.out.println("总数量：" + page.getTotalRecords());
		return gson.toJson(page);
	}

	/***
	 * 商品销售统计获取所有商品的总页数
	 */
	@Override
	public String getAllGoodsTjPage(HttpServletRequest request) {
		String searchGoodsName = request.getParameter("searchGoodsName");
		System.out.println("前台传来的商品名字" + searchGoodsName);
		String searchCustomer = request.getParameter("searchCustomer");
		System.out.println("前台传来的客户名字" + searchCustomer);
		String searchTimeStart = request.getParameter("searchTimeStart");
		String searchTimeEnd = request.getParameter("searchTimeEnd");
		if (searchGoodsName == null) {
			searchGoodsName = "";
		}
		if (searchCustomer == null) {
			searchCustomer = "";
		}
		if (searchTimeStart == null) {
			searchTimeStart = "1900-01-01";
		}
		if (searchTimeEnd == null) {
			Date d = new Date();
			searchTimeEnd = sdf.format(d);
		}
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(searchGoodsName, "%" + searchGoodsName + "%");
		map.put(searchCustomer, "%" + searchCustomer + "%");
		map.put(searchTimeEnd, "searchTimeEnd");
		map.put(searchTimeStart, "searchTimeStart");
		System.out.println("前台传来的客户时间11" + searchTimeEnd);
		System.out.println("前台传来的客户时间11" + searchTimeStart);
		int sum = sbm.getAllGoodsTjSum(map);
		System.out.println("查询到的总页码数量01" + sum);
		page.setTotal(sum + "");
		int tn = 0;
		if (sum % 5 > 0) {
			tn = (sum / 5) + 1;

		} else {
			tn = (sum / 5);
		}

		page.setTotalRecords(tn + "");
		System.out.println("shiwoma");
		
		System.out.println("总数量02：" + page.getTotal());
		System.out.println("总页码03：" + page.getTotalRecords());
		return gson.toJson(page);
	}

	/****
	 * 获取销售商品统计的页面内容
	 */
	@Override
	public String getAllGoodsTjBills(HttpServletRequest request) {
		String searchGoodsName = request.getParameter("searchGoodsName");
		String searchCustomer = request.getParameter("searchCustomer");
		String searchTimeEnd = request.getParameter("searchTimeEnd");
		String searchTimeStart = request.getParameter("searchTimeStart");
		System.out.println("前台传来的客户名字" + searchCustomer);
		String num = request.getParameter("n");

		if (searchGoodsName == null) {
			searchGoodsName = "";
		}
		if (searchCustomer == null) {
			searchCustomer = "";
		}
		if (searchTimeStart == "") {
			searchTimeStart = "1900-01-01";
		}
		if (searchTimeEnd == "") {
			Date d = new Date();
			searchTimeEnd = sdf.format(d);
		}
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();

		int pum = 1;
		if (num != null && num.length() > 0) {
			pum = Integer.parseInt(num);
		}
		int start = (pum - 1) * numPerPage;

		System.out.println("前台传来的商品名字" + "%" + searchGoodsName + "%");
		System.out.println("前台传来的客户名字" + searchCustomer);
		System.out.println("结束时间" + searchTimeEnd);
		System.out.println("开始时间" + searchTimeStart);
		map.put("searchGoodsName", "%" + searchGoodsName + "%");
		map.put("searchCustomer", "%" + searchCustomer + "%");
		map.put("searchTimeEnd", searchTimeEnd);
		map.put("searchTimeStart", searchTimeStart);
		map.put("start", start);
		map.put("size", numPerPage);
		List<SaleBillDetail> list = sbm.getAllGoodsTjBillsAs(map);
		return gson.toJson(list);
	}

	/****
	 * 成本利润统计计算所有的页码
	 */
	@Override
	public String getCBLRTjPages(HttpServletRequest request) {
		/*String searchGoodsName = request.getParameter("searchGoodsName");
		System.out.println("前台传来的商品名字" + searchGoodsName);
		String searchTimeStart = request.getParameter("searchTimeStart");
		String searchTimeEnd = request.getParameter("searchTimeEnd");
		if (searchGoodsName == null) {
			searchGoodsName = "";
		}
		if (searchTimeStart == "") {
			searchTimeStart = "1900-01-01";
		}
		if (searchTimeEnd == "") {
			Date d = new Date();
			searchTimeEnd = sdf.format(d);
		}*/
		Map<String, Object> map = new HashMap<String, Object>();
		/*map.put("searchGoodsName", "%" + searchGoodsName + "%");
		map.put("searchTimeStart", searchTimeStart);
		map.put("searchTimeEnd", searchTimeEnd);*/
		int sum = sbm.getCBLRSum();
		System.out.println("查询出来的总数量：" + sum);
		Page page = new Page();
		page.setTotal(sum + "");
		int tn = 0;
		if (sum % 5 > 0) {
			tn = (sum / 5) + 1;

		} else {
			tn = (sum / 5);
		}
		page.setTotalRecords(tn + "");
		System.out.println("成本利润统计总数量：" + sum);
		System.out.println("成本利润统计总页码：" + tn);
		return gson.toJson(page);
	}

	/***
	 * 查询所有的页面的数据
	 * 先查询出所有的商品，然后再根据每个商品set进去相应的成本利润
	 */
	@Override
	public String getCBLRTjBills(HttpServletRequest request) {
		System.out.println("调用了此方法了阿");
		String num = request.getParameter("n");		
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();

		int pum = 1;
		if (num != null && num.length() > 0) {
			pum = Integer.parseInt(num);
		}
		int start = (pum - 1) * numPerPage;
		map.put("start", start);
		map.put("size", numPerPage);
		List<ChenBenLiRun> list1=sbm.getAllGoodsId(map);
		for (ChenBenLiRun chenBenLiRun : list1) {
			System.out.println("查询出来的商品ID"+chenBenLiRun.getCommodityId());
			ChenBenLiRun cblr=sbm.getAllGoodsCB(chenBenLiRun.getCommodityId());
			System.out.println("查询出来的商品ID22"+cblr.getCommodityId());
			System.out.println("查询出来的单据时间"+chenBenLiRun.getSaleTime());
			if(cblr.getCommodityId().equals(chenBenLiRun.getCommodityId())) {
				chenBenLiRun.setCapital(chenBenLiRun.getSum()*cblr.getCapital());
				chenBenLiRun.setProfit(chenBenLiRun.getTotal()-(chenBenLiRun.getSum()*cblr.getCapital()));
				System.out.println("成本是多少"+chenBenLiRun.getCapital());
				
			}
		}
		
		return  gson.toJson(list1);
	}

}
