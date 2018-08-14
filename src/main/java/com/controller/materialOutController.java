package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.MaterialOutInStoreService;
import com.service.materialOutService;

/**
 * 
 *<h2>商品原料出库确认控制层</h2>
 *<p>负责处理关于这张表的所有功能</p>
 * 
 *@author FSK
 *
 *
 */
@Controller
@RequestMapping("/outController")
public class materialOutController {

	@Resource
	private materialOutService ms;
	@Resource
	private MaterialOutInStoreService mo;
	
	/**
	 * 
	 * 
	 *<p>查询出商品原料需要出库的信息,分页相关</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型 request HttpServletRequest
	 *
	 */
	@RequestMapping(value="/getinfoTable.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getPeopleTable(HttpServletRequest request) {
		return ms.getInfoTable(request);
	}
	
	/**
	 * 
	 * 
	 *<p>查询商品原料需要出库的信息数量,分页相关</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型 request HttpServletRequest
	 *
	 */
	@RequestMapping("/getPage.ajax")
	public @ResponseBody String getPage(HttpServletRequest request) {
		return ms.getPage(request);
	}
	
	/**
	 * 
	 * 
	 *<p>刷新页面并把查询条件回传,分页相关</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型 request HttpServletRequest
	 *
	 */
	@RequestMapping("/sreach.do")
	public String sreach(HttpServletRequest request) {
		request.setAttribute("sreach", request.getParameter("sreach"));
		return "/view/materialOut/materialOut-list.jsp";
	}
	
	/**
	 * 
	 * 
	 *<p>查询所有商品仓库的信息</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型 request HttpServletRequest
	 *
	 */
	@RequestMapping(value="/getwarehouse.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getwarehouse(HttpServletRequest request) {
		return ms.getwarehouse(request);
	}
	
	/**
	 * 
	 * 
	 *<p>确认出库数据，将出库数据录入出库表中并减少总库存数据</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型 request HttpServletRequest
	 *
	 */
	@RequestMapping(value="/ack.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String ack(HttpServletRequest request) {
		return mo.ac(request);
	}
	
	
	/**
	 * 
	 * 
	 *<p>传输数据至实际原料出库页面</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型 request HttpServletRequest
	 *
	 */
	@RequestMapping(value="/addUI.do",produces="text/html; charset=UTF-8")
	public String addUI(HttpServletRequest request) {
		mo.addUI(request);
		return "/view/materialOut/materialOut-eait.jsp";
	}
}
