package com.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.YearpanlTable;
import com.service.YearplanService;

	/**
	 * 
	 *<h2>年度生产总计划表控制层</h2>
	 *<p>负责处理关于这张表的所有功能</p>
	 * 
	 *@author lily
	 *
	 *
	 */
@Controller
@RequestMapping("/yearplanController")
public class YearplanController {
		
		@Resource
		private YearplanService yps;
		
	
	/**
	 * 
	 * 
	 *<p>查询出所有的总计划表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/getYearplanTable.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getYearplanTable(HttpServletRequest request) {

		return yps.getYearplanTable(request);

	}
	/**
	 * 
	 * 
	 *<p>为了计算总计划表信息分页</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/getPage.ajax")
	public @ResponseBody String getPage(HttpServletRequest request) {
		return yps.getPage(request);
	}
	/**
	 * 
	 * 
	 *<p>把搜索的关键字放入session中，进行页面的刷新</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/doSearchCard.do")
	public String doSearchCard(HttpServletRequest request) {
		request.setAttribute("searchCard", request.getParameter("searchCard"));
		request.setAttribute("youcommodity", request.getParameter("mycommodity"));
		System.err.println("........"+123);
		return "../view/yearplan/yearplan-list.jsp";
	}
	/**
	 * 
	 * 
	 *<p>查询所有商品的信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	
	@RequestMapping(value="/selectAllCommodity.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectAllCommodity() {
		return yps.selectAllCommodity();
	}
	/**
	 * 
	 * 
	 *<p>做模糊查询时，跳转页面所用</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/doSearchCards.do")
	public String doSearchCards(HttpServletRequest request) {
		request.setAttribute("searchCard", request.getParameter("searchCard"));
		request.setAttribute("youcommodity", request.getParameter("mycommodity"));
		System.err.println("........"+123);
		return "../view/yearplan/yearplan-list2.jsp";
	}
	/**
	 * 
	 * 
	 *<p>增加年度总计划表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/yearplanAdd.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String yearplanAdd(HttpServletRequest request,YearpanlTable yplan) {
		
		return yps.yearplanAdd(request,yplan);
	}
	/**
	 * 
	 * 
	 *<p>根据前端传入的计划表ID值，删除相应的信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/deleteCommodityById.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String deleteCommodityById(HttpServletRequest request) {
		
		return yps.deleteCommodityById(request);
	}
	/**
	 * 
	 * 
	 *<p>查看根据传入的总计划表ID来查询出信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/selectByYearpanlTableId.do")
	public String selectByYearpanlTableId(HttpServletRequest request) {
		YearpanlTable table=yps.selectByYearpanlTableId(request);
		request.setAttribute("yearTable",table);
		return "../view/yearplan/yearplan-update.jsp";
	}
	/**
	 * 
	 * 
	 *<p>修改总计划表信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/yearplanUpdate.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String yearplanUpdate(HttpServletRequest request,YearpanlTable yearTable) {
		
		return yps.yearplanUpdate(request,yearTable);
	}
}
