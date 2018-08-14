package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.SectionInfoService;
import com.service.SystemRoleService;


/**
 * 
 *<h2>角色表控制层</h2>
 *<p>负责处理关于这张表的所有功能</p>
 * 
 *@author FSK
 *
 *
 */
@Controller
@RequestMapping("/roleController")
public class SystemRoleController {
	
	@Resource
	private SystemRoleService sr;
	@Resource
	private SectionInfoService sf;
	
	/**
	 * 
	 * 
	 *<p>查询出所有的角色信息,分页相关</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/getRoleTable.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getPeopleTable(HttpServletRequest request) {
		return sr.getInfoTable(request);
	}
	
	/**
	 * 
	 * 
	 *<p>查询出角色的数量信息,分页相关</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/getPage.ajax")
	public @ResponseBody String getPage(HttpServletRequest request) {
		return sr.getPage(request);
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
		return "/view/role/role-list.jsp";
	}
	
	/**
	 * 
	 * 
	 *<p>启用角色并启用修改账号</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/start.ajax")
	public @ResponseBody String start(HttpServletRequest request) {
		return sr.start(request);
	}
	
	/**
	 * 
	 * 
	 *<p>停用角色并停用修改账号</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/block.ajax")
	public @ResponseBody String deletInfo(HttpServletRequest request) {
		return sr.block(request);
	}
	
	/**
	 * 
	 * 
	 *<p>增加角色信息</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/insetinfo.ajax")
	public @ResponseBody String insetinfo(HttpServletRequest request) {
		return sr.insetinfo(request);
	}
	
	/**
	 * 
	 * 
	 *<p>向修改角色页面传输被修改的角色信息</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/updateRole.do",produces="text/html; charset=UTF-8")
	public String updateRole(HttpServletRequest request) {
         sr.selectById(request);
		return "/view/role/role-edit.jsp";
	}
	
	/**
	 * 
	 * 
	 *<p>修改角色信息</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/update.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String update(HttpServletRequest request) {
		
		return sr.update(request);
	}
	
	/**
	 * 
	 * 
	 *<p>向分配角色页面传输被分配的角色信息</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/aollt.do",produces="text/html; charset=UTF-8")
	public String aollt(HttpServletRequest request) {
         sr.selectById(request);
		return "/view/role/role-auth.jsp";
	}
	
	/**
	 * 
	 * 
	 *<p>跳转到空白页面后关闭小窗口</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/roleAuth.do",produces="text/html; charset=UTF-8")
	public String roleAuth(HttpServletRequest request) {
         sr.aolltRoleAuth(request);
         try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return "/view/role/role-null.jsp";
	}
	
	/**
	 * 
	 * 
	 *<p>向增加角色页面传输所有部门信息</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/insertRole.do",produces="text/html; charset=UTF-8")
	public String insertRole(HttpServletRequest request) {
         sf.selectAll(request); 
		return "/view/role/role-add.jsp";
	}

}
