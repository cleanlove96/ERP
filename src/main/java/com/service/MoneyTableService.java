/*****************************************************
 *  HISTORY
 *  FileName:MoneyTableService.java
 *  Package:com.service
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年7月2日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 往来账务管理的服务层
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年7月2日
 */
public interface MoneyTableService {
	/***
	 * 
	 * <p>
	 * 往来账务管理获取页数，页码
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年7月2日
	 * @return
	 */
	String getMoneyTablePages(HttpServletRequest request);

	/***
	 * 
	 * <p>
	 * 获取分页后每页显示的内容
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年7月2日
	 * @param request
	 * @return
	 */
	String getMoneyTableBills(HttpServletRequest request);

	/***
	 * 
	 * <p>
	 * 往来账务管理查询详情，先获取页面有多少条数据
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年7月4日
	 * @param request
	 * @return
	 */
	String getMoneyTableLooksPage(HttpServletRequest request);

	/****
	 * 
	 * <p>
	 * 往来账务管理查询详情，获取没页的内容
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年7月4日
	 * @param request
	 * @return
	 */
	String getMoneyTableLooksBill(HttpServletRequest request);

	/****
	 * 
	 * <p>
	 * 商品销售统计查看详情，获取查询到有多少页
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年7月4日
	 * @param request
	 * @return
	 */
	String getGoodsSaleParticularsPage(HttpServletRequest request);
	/****
	 * 
	 * <p>
	 * 获取每页所需数据的总条数及其记录
	 * </p>
	 * @author zm
	 * @Date 2018年7月4日
	 * @param request
	 * @return
	 */
      String  getGoodsSaleParticularsBills(HttpServletRequest request);
}