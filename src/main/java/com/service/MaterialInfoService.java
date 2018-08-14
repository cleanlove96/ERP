/*****************************************************
 *  HISTORY
 *  FileName:MaterialService.java
 *  Package:com.service
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年6月23日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 原料信息service层
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年6月23日
 */
public interface MaterialInfoService {
	/***
	 * 
	 * <p>
	 * 获取所有的原料信息
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年6月23日
	 * @param request
	 *            HttpServletRequest 请求对象
	 * @return 返回SysMaterial对象的集合
	 */
	String  getAllSysMaterialInfo(HttpServletRequest request);
	/****
	 * 
	 * <p>
	 * 添加商品的原料信息 
	 * </p>
	 * @author zm
	 * @Date 2018年6月24日
	 * @param request
	 * @param response
	 * @return 如返回一个整型数字 则添加成功
	 */

	String SysMaterialInfoAdd(HttpServletRequest request, HttpServletResponse response);

	/**
	 * <p>
	 * 获取页面的页码数
	 * </p>
	 * @author zm
	 * @Date 2018年6月24日
	 * @param request
	 * @return
	 */
	String getPage(HttpServletRequest request);
	/***
	 * 
	 * <p>
	 * 修改界面，根据前端传来的ID进行数据的回显。
	 * </p>
	 * @author zm
	 * @Date 2018年6月24日
	 * @param request HttpServletRequest 请求对象
	 * @return  返回值
	 */
	String  queryBySysMaterialId(HttpServletRequest request);
	/***
	 * 
	 * <p>
	 * 根据ajax传来的数据对后台进行修改
	 * </p>
	 * @author zm
	 * @Date 2018年6月24日
	 * @param request
	 * @return
	 */
	String  updateBySysMaterialId(HttpServletRequest request);
	/****
	 * 
	 * <p>
	 * 修改商品信息的状态，到底是启用还是停用
	 * </p>
	 * @author zm
	 * @Date 2018年6月24日
	 * @param request
	 * @param response
	 * @return
	 */
	String  updateBySysMaterialStatus(HttpServletRequest request,HttpServletResponse response);
	/**
	 * 搜索到 所有的供应商传给界面
	 * @param request
	 */
	void selByStatus(HttpServletRequest request);
		
	
}
