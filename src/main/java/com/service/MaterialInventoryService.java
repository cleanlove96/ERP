package com.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <h2>进行原料库存表数据处理服务</h2>
 * <p>
 * 该服务层包含了所有对于原料库存表的逻辑处理。
 * </p>
 * @author FSK
 * @version 1.0
 */
public interface MaterialInventoryService {

	/**
	 * 
	 * <h2>查询数据库数据并在页面进行分页和查询处理</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 原料库存表数据
	 */
	String getInfoTable(HttpServletRequest request);

	/**
	 * 
	 * <h2>查询数据库数据并返回总页面数和总数据数</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 总页面数和总数据数
	 */
	String getPage(HttpServletRequest request);

	void selectById(HttpServletRequest request);

	String update(HttpServletRequest request);

}
