package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.service.MaterialOutInStoreService;
import com.service.MaterialStorageService;

/**
 * 
 *<h2>商品原料确认控制层</h2>
 *<p>负责处理关于这张表的所有功能</p>
 * 
 *@author FSK
 *
 *
 */
@Controller
@RequestMapping("/materialStorageController")
public class MaterialStorageController {

	@Resource
	private MaterialStorageService ms;
	
	@Resource
	private MaterialOutInStoreService mo;
	
	
	/**
	 * 
	 * 
	 *<p>查询出商品需要入库的信息,分页相关</p>
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
	 *<p>查询原料需要入库的信息数量,分页相关</p>
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
		return "/view/material_storage/materialStorage-list.jsp";
	} 
	
	/**
	 * 
	 * 
	 *<p>查询所有原料仓库的信息</p>
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
	 *<p>确认原料入库数据，将入库数据录入入库表中并增加总库存数据</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型 request HttpServletRequest
	 *
	 */
	@RequestMapping(value="/ack.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String ack(HttpServletRequest request) {
		return mo.ack(request);
	}
}
