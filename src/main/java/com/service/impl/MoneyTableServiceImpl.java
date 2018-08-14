/*****************************************************
 *  HISTORY
 *  FileName:MoneyTableServiceImpl.java
 *  Package:com.service.impl
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年7月2日 zm创建文件
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.mapper.MoneyManagementTableMapper;
import com.model.MoneyManagementTable;
import com.pojo.MoneyTableLooks;
import com.pojo.Page;
import com.pojo.SaleBillDetail;
import com.service.MoneyTableService;

/**
 * <p>
 * 往来账务管理的实现层
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年7月2日
 */
@Service
public class MoneyTableServiceImpl implements MoneyTableService {
	/***
	 * 获取页码数量
	 */
	// 时间段查询还有问题，没设置对头，待完善
	private static Gson gson = new Gson();
	private static final int numPerPage = 5;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Resource
	private MoneyManagementTableMapper mmtm;

	@Override
	public String getMoneyTablePages(HttpServletRequest request) {
		String searchCustomer = request.getParameter("searchCustomer");
		String searchTimeStart = request.getParameter("searchTimeStart");
		String searchTimeEnd = request.getParameter("searchTimeEnd");
		if (searchCustomer == null) {
			searchCustomer = "";
		}
		if (searchTimeStart == null) {
			searchTimeStart = "1900-01-01 00:00:00";
		}
		if (searchTimeEnd == null) {
			Date d = new Date();
			searchTimeEnd = sdf.format(d);
		}
		System.out.println("到底搜索了什么东西：" + searchCustomer);
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchTimeEnd", searchTimeEnd);
		map.put("searchTimeStart", searchTimeStart);
		map.put("searchCustomer", "%" + searchCustomer + "%");
		System.out.println("搜索页码的起始时间：" + searchTimeStart);
		System.out.println("搜索页码的结束时间：" + searchTimeEnd);
		int sum = mmtm.getTotalNums(map);
		System.out.println("查询的总数量" + sum);
		page.setTotal(sum+"");
		int tn = 0;
		if (sum % 5 > 0) {
			tn = (sum / 5) + 1;

		} else {
			tn = (sum / 5);
		}

		page.setTotalRecords(tn + "");
		System.out.println("总数量：" + page.getTotal());
		System.out.println("总页码：" + page.getTotalRecords());
		return gson.toJson(page);
	}

	/****
	 * 往来账务管理每页显示的数据
	 */
	@Override
	public String getMoneyTableBills(HttpServletRequest request) {
		String num = request.getParameter("n");
		String searchCustomer = request.getParameter("searchCustomer");
		String searchTimeStart = request.getParameter("searchTimeStart");
		String searchTimeEnd = request.getParameter("searchTimeEnd");
		if (searchCustomer == "") {
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
		map.put("start", start);
		map.put("size", numPerPage);
		map.put("searchCustomer", "%" + searchCustomer + "%");
		map.put("searchTimeStart", searchTimeStart);
		map.put("searchTimeEnd", searchTimeEnd);
		System.out.println("搜索的起始时间：" + searchTimeStart);
		System.out.println("搜索的结束时间：" + searchTimeEnd);
		List<MoneyManagementTable> list = mmtm.getReceivingTable(map);
		return gson.toJson(list);
	}

	/****
	 * 往来账务管理详情，获取详情共有多少条数据，有多少页码
	 */
	@Override
	public String getMoneyTableLooksPage(HttpServletRequest request) {
		String searchCustomer = request.getParameter("searchCustomer");
		System.out.println("传过来的数据是什么" + searchCustomer);
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchCustomer", searchCustomer);
		int sum = mmtm.getWlzwLook(map);
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
	 * 获取每页显示的数据
	 */
	@Override
	public String getMoneyTableLooksBill(HttpServletRequest request) {
		String num = request.getParameter("n");
		String searchCustomer = request.getParameter("searchCustomer");
		System.out.println("传过来的数据是什么" + searchCustomer);
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		int pum = 1;
		if (num != null && num.length() > 0) {
			pum = Integer.parseInt(num);
		}
		int start = (pum - 1) * numPerPage;
		map.put("start", start);
		map.put("size", numPerPage);
		map.put("searchCustomer", searchCustomer);
		List<MoneyTableLooks> list = mmtm.getLooksAllBillsAs(map);
		return gson.toJson(list);
	}

	/***
	 * 商品销售统计获取需要多少页码数
	 */
	@Override
	public String getGoodsSaleParticularsPage(HttpServletRequest request) {
		String searchGoodsName = request.getParameter("searchGoodsName");
		System.out.println("传过来的数据是什么" + searchGoodsName);
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchGoodsName", searchGoodsName);
		int sum = mmtm.getSaleGoodsNums(map);
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
	 * 获取每页的数据的记录
	 */
	@Override
	public String getGoodsSaleParticularsBills(HttpServletRequest request) {
		String num = request.getParameter("n");
		String searchGoodsName = request.getParameter("searchGoodsName");
		System.out.println("传过来的数据是什么" + searchGoodsName);
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		int pum = 1;
		if (num != null && num.length() > 0) {
			pum = Integer.parseInt(num);
		}
		int start = (pum - 1) * numPerPage;
		map.put("start", start);
		map.put("size", numPerPage);
		map.put("searchGoodsName", searchGoodsName);
		List<MoneyTableLooks> list = mmtm.getGoodsSaleParticularsTable(map);
		return gson.toJson(list);
	}

}
