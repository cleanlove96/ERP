package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.MaterialOutInStoreService;


/**
 * 
 *<h2>原料出入库控制层</h2>
 *<p>负责处理关于这张表的所有功能</p>
 * 
 *@author FSK
 *
 *
 */
@Controller
@RequestMapping("/materialController")
public class MaterialOutInStoreController {
	@Resource
	private MaterialOutInStoreService ms;
	
	/**
	 * 
	 * 
	 *<p>查询出所有原料的出入库信息,分页相关</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/getinfoTable.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getPeopleTable(HttpServletRequest request) {
		return ms.getInfoTable(request);
	}
	
	/**
	 * 
	 * 
	 *<p>查询原料出入库的数量信息,分页相关</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
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
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/sreach.do")
	public String sreach(HttpServletRequest request) {
		request.setAttribute("sreach", request.getParameter("sreach"));
		request.setAttribute("sreach2", request.getParameter("sreach2"));
		return "/view/materialStore/materialStore-list.jsp";
	}

}
