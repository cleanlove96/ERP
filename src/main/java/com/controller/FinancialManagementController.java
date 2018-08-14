/*****************************************************
 *  HISTORY
 *  FileName:FinancialManagementController.java
 *  Package:com.controller
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年6月29日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.CostProfitTable;
import com.service.FinancialManagementService;


@Controller
@RequestMapping("/financialManagementController")
public class FinancialManagementController {
	@Resource
	private FinancialManagementService fms;
	/**
	 * <p>
	 * 财务管理模块控制层
	 * 获取到销售总金额，成本，；利润并返回到jsp页面。
	 * </p>
	 * 
	 * @Copyright (C),华清远见
	 * @author zm
	 * @Date:2018年6月29日
	 */
	@RequestMapping("/getSaleSumTotal.do")
	public String getSaleSumTotal(HttpServletRequest request) {
		CostProfitTable costProfitTable	=fms.getCostProfitTable(request);
		request.setAttribute("CostProfitTable", costProfitTable);
		return "../view/costProfittable/cost-profit-list.jsp" ;		
	}
	/***
	 * 
	 * <p>
	 * 获取所有的销售单句的内容
	 * </p>
	 * @author zm
	 * @Date 2018年7月1日
	 * @param request HttpServletRequest 请求对象为request
	 * @return 返回到jsp的页面
	 */
	@RequestMapping(value="/getAllSaleBills.do",produces = "text/html; charset=UTF-8")
	public @ResponseBody String getAllSaleBillsdo(HttpServletRequest request) {	
		//System.out.println("终于进来了");
		return 	fms.getSaleBillDetail(request);
		
	}
	/****
	 * 
	 * <p>
	 * 获取总页数
	 * </p>
	 * @author zm
	 * @Date 2018年7月1日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getPages.ajax",produces = "text/html; charset=UTF-8")
	public @ResponseBody String  getAllSaleBillsPagesInfo(HttpServletRequest request) {	
		
		return fms.getAllBillsPage(request);		
	}
	/***
	 * 
	 * <p>
	 * 一直显示搜索框的内容
	 * </p>
	 * @author zm
	 * @Date 2018年7月1日
	 * @param request
	 * @return
	 */
	@RequestMapping("/doSreach.do")	
	public String doSreach(HttpServletRequest request) {		
		request.setAttribute("search", request.getParameter("search"));
		System.out.println("搜索的什么++++++"+request.getParameter("search"));
		return "../view/costProfittable/saleSumTotal.jsp";
	}
	/****
	 * 
	 * <p>
	 * 销售商品统计获取商品的页码
	 * </p>
	 * @author zm
	 * @Date 2018年7月1日
	 * @param request
	 * @return
	 */
	@RequestMapping("/getSaleTjPage.ajax")
	public @ResponseBody String getSaleTjPage(HttpServletRequest request) {
		
		return fms.getAllGoodsTjPage(request);
		
	}
	/****
	 * 
	 * <p>
	 * 获取销售商品统计页面的商品信息
	 * </p>
	 * @author zm
	 * @Date 2018年7月2日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getSaleTjBills.ajax",produces = "text/html; charset=UTF-8")
	public @ResponseBody String  getSaleGoodsTjBills(HttpServletRequest request) {
		return fms.getAllGoodsTjBills(request);		
	}
	/****
	 * 
	 * <p>
	 *商品销售统计的回显
	 * </p>
	 * @author zm
	 * @Date 2018年7月3日
	 * @param request
	 * @return
	 */
	@RequestMapping("/doGoodsTjSreach.do")
	public  String  getdoGoodsTjSreach(HttpServletRequest request) {
		request.setAttribute("searchGoodsName", request.getParameter("searchGoodsName"));
		request.setAttribute("searchCustomer", request.getParameter("searchCustomer"));
		request.setAttribute("searchTimeEnd", request.getParameter("searchTimeEnd"));
		request.setAttribute("searchTimeStart", request.getParameter("searchTimeStart"));
		return "../view/saleStatistics/saleStatistics-list.jsp";
		
	}
	/***
	 * 
	 * <p>
	 * 成本利润计算页码数
	 * </p>
	 * @author zm
	 * @Date 2018年7月4日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/docblrtjPages.ajax",produces = "text/html; charset=UTF-8")
	public @ResponseBody String docblrtjPages(HttpServletRequest request){
		return fms.getCBLRTjPages(request);
		
	}
	/***
	 * 
	 * <p>
	 * 成本利润取出记录
	 * </p>
	 * @author zm
	 * @Date 2018年7月4日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getCblrtjBills.ajax",produces = "text/html; charset=UTF-8")
	public @ResponseBody String  getCblrTJBills(HttpServletRequest request) {
		return fms.getCBLRTjBills(request);
		}
}
