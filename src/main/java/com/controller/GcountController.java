package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.GcountService;

/**
 * 
 *<h2>商品仓库控制层</h2>
 *<p>负责处理关于这张表的所有功能</p>
 * 
 *@author FSK
 *
 *
 */
@Controller
@RequestMapping("/gcountController")
public class GcountController {
	
	@Resource
	private GcountService gs;
	
	/**
	 * 
	 * 
	 *<p>查询出商品仓库的信息,分页相关</p>
	 *@author FSK
	 *@param 传入的参数 request HttpServletRequest
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/getinfoTable.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getPeopleTable(HttpServletRequest request) {
		return gs.getInfoTable(request);
	}
	
	/**
	 * 
	 * 
	 *<p>查询商品仓库的信息数量,分页相关</p>
	 *@author FSK
	 *@param 传入的参数 request HttpServletRequest
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/getPage.ajax")
	public @ResponseBody String getPage(HttpServletRequest request) {
		return gs.getPage(request);
	}
	
	/**
	 * 
	 * 
	 *<p>刷新页面并把查询条件回传,分页相关</p>
	 *@author FSK
	 *@param 传入的参数 request HttpServletRequest
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/sreach.do")
	public String sreach(HttpServletRequest request) {
		request.setAttribute("sreach", request.getParameter("sreach"));
		return "/view/gcount/gcount-list.jsp";
	}
	
	/**
	 * 
	 * 
	 *<p>将选中的商品总库存的信息传给修改页面</p>
	 *@author FSK
	 *@param 传入的参数 request HttpServletRequest
	 *@return 跳转页面
	 *
	 */
	@RequestMapping("/updateGcountUI.do")
	public String updateGcountUI(HttpServletRequest request) {
		gs.selectById(request);
		return "/view/gcount/gcount-edit.jsp";
	}
	
	/**
	 * 
	 * 
	 *<p>修改商品总库存数量和金额，与存放仓库</p>
	 *@author FSK
	 *@param 传入的参数 request HttpServletRequest
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/update.ajax")
	public @ResponseBody String update(HttpServletRequest request) {
		return gs.update(request);
	}

}
