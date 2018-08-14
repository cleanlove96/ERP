/*****************************************************
 *  HISTORY
 *  FileName:MaterialInfoController.java
 *  Package:com.controller
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年6月23日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.SysMaterial;
import com.service.MaterialInfoService;

/**
 * <p>
 * 原料信息的controller层
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年6月23日
 */
@Controller
@RequestMapping("/materialInfoController")
public class MaterialInfoController {
	@Resource
	private MaterialInfoService materialInfoService;

	/***
	 * 
	 * <p>
	 * 查询所有的原料信息
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年6月23日
	 * @param request
	 *            HttpServletRequest 请求对象
	 * @return
	 */
	@RequestMapping(value = "/materialInfo.ajax", produces = "text/html; charset=UTF-8")
	public @ResponseBody String getmaterialInfo(HttpServletRequest request) {
		return materialInfoService.getAllSysMaterialInfo(request);
	}

	/****
	 * 
	 * <p>
	 * 获取总的页数
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年6月24日
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPage.ajax")
	public @ResponseBody String getpage(HttpServletRequest request) {

		return materialInfoService.getPage(request);

	}

	/*	*//****
			 * 
			 * <p>
			 * 使用ajax动态查询获取页面的页数
			 * </p>
			 * 
			 * @author zm
			 * @Date 2018年6月24日
			 * @param request
			 *            请求对象
			 * @param response
			 *            响应对象
			 * @return
			 *//*
				 * @RequestMapping("/materialInfoPage.ajax") public String
				 * materialInfoTable(HttpServletRequest request, HttpServletResponse response) {
				 * return null;
				 * 
				 * }
				 */

	/***
	 * 
	 * <p>
	 * 搜索的内容一直显示在搜索框内
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年6月24日
	 * @param request
	 * @return
	 */
	@RequestMapping("/doSreach.do")
	public String doSreach(HttpServletRequest request) {
		
		request.setAttribute("search", request.getParameter("search"));
		return "/view/materialInfo/material-list.jsp";
	}
	
	/**
	 * 在跳转增加前，将供应商信息给界面
	 * @param request
	 * @param response
	 * @param sysMaterial
	 * @return
	 */
	@RequestMapping("/AddUI.do")
	public  String AddUI(HttpServletRequest request, HttpServletResponse response,
			SysMaterial sysMaterial) {
		materialInfoService.selByStatus(request);
		return "/view/materialInfo/material-add.jsp";
	}
	/***
	 * 
	 * <p>
	 * 添加原料信息
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年6月23日
	 * @param request
	 *            HttpServletRequest 请求对象
	 * @param response
	 *            HttpServletResponse 响应对象
	 * @param sysMaterial
	 *            原料类 model
	 * @return 如果成功返回int类型的值
	 */
	@RequestMapping("/materialInfoAdd.do")
	public @ResponseBody String materialInfoAdd(HttpServletRequest request, HttpServletResponse response,
			SysMaterial sysMaterial) {
		String b = materialInfoService.SysMaterialInfoAdd(request, response);
		
		return b;
	}

	/***
	 * 
	 * <p>
	 * 根据ID查询原料信息
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年6月24日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/materialInfoById.do")
	public String materialInfoById(HttpServletRequest request, HttpServletResponse response) {
		return materialInfoService.queryBySysMaterialId(request);

	}

	/****
	 * 
	 * <p>
	 * 前台传来的数据，后台根据ID进行修改
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年6月24日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateBySysMaterialId.ajax")
	public @ResponseBody String setUpdateBySysMaterialId(HttpServletRequest request, HttpServletResponse response) {
		return materialInfoService.updateBySysMaterialId(request);
	}

	/***
	 * 
	 * <p>
	 * 根据ajax传递的值，修改原料信息的状态，到底是启用还是停用
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年6月24日
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/updateBySysMaterialStatus.ajax")
	public @ResponseBody String setUpdateBySysMaterialStatus(HttpServletRequest request, HttpServletResponse response) {
		return materialInfoService.updateBySysMaterialStatus(request, response);
	}

}
