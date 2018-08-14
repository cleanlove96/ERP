package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.service.SectionInfoService;


/**
 * 
 *<h2>部门表控制层</h2>
 *<p>负责处理关于这张表的所有功能</p>
 * 
 *@author FSK
 *
 *
 */
@Controller
@RequestMapping("/sectionInfoController")
public class SectionInfoController {
	
	@Resource
	private SectionInfoService is;
	
	/**
	 * 
	 * 
	 *<p>查询出所有的部门信息,分页相关</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/getinfoTable.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getPeopleTable(HttpServletRequest request) {
		return is.getInfoTable(request);
	}
	
	/**
	 * 
	 * 
	 *<p>查询出部门的数量信息,分页相关</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/getPage.ajax")
	public @ResponseBody String getPage(HttpServletRequest request) {
		return is.getPage(request);
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
		return "/view/department/department-list.jsp";
	}
	
	/**
	 * 
	 * 
	 *<p>停用部门</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/block.ajax")
	public @ResponseBody String block(HttpServletRequest request) {
		return is.block(request);
	}
	
	/**
	 * 
	 * 
	 *<p>启用部门</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/start.ajax")
	public @ResponseBody String start(HttpServletRequest request) {
		return is.start(request);
	}
	
	/**
	 * 
	 * 
	 *<p>增加部门信息</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/insetinfo.ajax")
	public @ResponseBody String insetinfo(HttpServletRequest request) {
		return is.insetinfo(request);
	}
	
	
	/**
	 * 
	 * 
	 *<p>向修改部门页面传输被修改的部门信息</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/updateSection.do",produces="text/html; charset=UTF-8")
	public String updateSection(HttpServletRequest request) {
         is.selectById(request);
		return "/view/department/department-edit.jsp";
	}
	
	/**
	 * 
	 * 
	 *<p>修改部门信息</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/update.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String update(HttpServletRequest request) {
		
		return is.update(request);
	}

}
