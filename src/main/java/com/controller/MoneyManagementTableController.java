/*****************************************************
 *  HISTORY
 *  FileName:MoneyManagementTableController.java
 *  Package:com.controller
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年7月2日 zm创建文件
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

import com.service.MoneyTableService;

/**
 * <p>
 * 往来账务管理，以及应收应付款
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年7月2日
 */
@Controller
@RequestMapping("/moneyTableController")
public class MoneyManagementTableController {
	@Resource
	private MoneyTableService mts;
	/***
	 * 
	 * <p>
	 * 往来账务管理获取页码数
	 * </p>
	 * @author zm
	 * @Date 2018年7月2日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getMoneyTablePages.ajax",produces = "text/html; charset=UTF-8")
	public @ResponseBody  String  getMoneyTables(HttpServletRequest request) {
		return mts.getMoneyTablePages(request);
		
	}
	/***
	 * 
	 * <p>
	 * 往来账务管理获取页面显示的内容
	 * </p>
	 * @author zm
	 * @Date 2018年7月2日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getMoneyTableBillsss.ajax",produces = "text/html; charset=UTF-8")
	public  @ResponseBody String  getMoneyTableBilles(HttpServletRequest request) {
		return mts.getMoneyTableBills(request);
	}
	/***
	 * 
	 * <p>
	 * 搜索的内容返回到页面
	 * </p>
	 * @author zm
	 * @Date 2018年7月2日
	 * @param request
	 * @return
	 */
	@RequestMapping("/doSearchCustomer.do")
	public  String  doSearch(HttpServletRequest request) {
		String searchCustomer=request.getParameter("searchCustomer");
		String searchTimeStart=request.getParameter("searchTimeStart");
		String searchTimeEnd=request.getParameter("searchTimeEnd");
		request.setAttribute("searchTimeStart", searchTimeStart);
		request.setAttribute("searchTimeEnd", searchTimeEnd);
		System.out.println("到底搜索的什么："+searchCustomer);
		request.setAttribute("searchCustomer", searchCustomer);
		return "../view/receivingTable/receivingTable-list.jsp";		
	}
	/***
	 * 
	 * <p>
	 * 往来账务管理表详情查询获取page页码;
	 * </p>
	 * @author zm
	 * @Date 2018年7月4日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getMoneyTableLooksPage.ajax",produces = "text/html; charset=UTF-8")
	public @ResponseBody String  getMoneyTablepages(HttpServletRequest request) {	
		return mts.getMoneyTableLooksPage(request);
		
	}
	/***
	 * 
	 * <p>
	 * 往来账务管理获取每页的条的数据
	 * </p>
	 * @author zm
	 * @Date 2018年7月4日
	 * @return
	 */
	@RequestMapping(value="/getMoneyTableLooksBilss.ajax",produces = "text/html; charset=UTF-8")
	public @ResponseBody String  getMoneyTableLooksBils(HttpServletRequest request) {
		return mts.getMoneyTableLooksBill(request);		
	}
	/****
	 * 
	 * <p>
	 * 商品销售统计查看详情，获取页码数量
	 * </p>
	 * @author zm
	 * @Date 2018年7月4日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getSpxsTjParticularsPage.ajax",produces = "text/html; charset=UTF-8")
	public @ResponseBody String  getSpxsTjParticulars(HttpServletRequest request) {
		return mts.getGoodsSaleParticularsPage(request);
		
	}
	/***
	 * 
	 * <p>
	 * 商品销售统计查看详情，获取页面的数据
	 * </p>
	 * @author zm
	 * @Date 2018年7月4日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getSpxsTjBills",produces = "text/html; charset=UTF-8")
	public @ResponseBody String  getSpxsTjBills(HttpServletRequest request) {
		return mts.getGoodsSaleParticularsBills(request);
		
	}
}
