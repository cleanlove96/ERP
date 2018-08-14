/*****************************************************
 *  HISTORY
 *  FileName:SectionRoleController.java
 *  Package:com.controller
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年6月26日 zm创建文件
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

import com.service.AccountService;
import com.service.SectionRoleService;

/**
 * <p>
 * 
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年6月26日
 */
@Controller
@RequestMapping("/sectionRoleController")
public class SectionRoleController {
	@Resource
	private SectionRoleService sectionRoleService;
	@Resource
	private AccountService as;

	/****
	 * 
	 * <p>
	 * 查询所有的Nodes
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年6月26日
	 * @return
	 */
	@RequestMapping(value="/initZtree.do",produces="text/html; charset=UTF-8")
	public @ResponseBody String getAllNodes() {
		System.out.println("调用了此程序吗？");
		return sectionRoleService.getAllNodeByJson();

	}
	/***
	 * 
	 * <p>
	 * 实现人员的调动。
	 * </p>
	 * @author zm
	 * @Date 2018年6月27日
	 * @param request
	 * @return
	 */
	@RequestMapping("/drop.do")
	public @ResponseBody String  updatePidByIdSet( HttpServletRequest request) {
		return sectionRoleService.updatePidById(request);
		
	}
	/****
	 * 
	 * <p>
	 * 根据ajax传来的ID，查找相关者的基本信息
	 * </p>
	 * @author zm
	 * @Date 2018年6月27日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/selectAllAccount.do",produces="text/html; charset=UTF-8")
	public  @ResponseBody String  queryAllAccount(HttpServletRequest request) {
		
		return sectionRoleService.queryAllAccountinfo(request);
		
	}
}
