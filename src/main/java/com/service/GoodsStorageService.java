package com.service;

import javax.servlet.http.HttpServletRequest;

public interface GoodsStorageService {

	
	/**
	 * 
	 * 
	 *<p>查询出商品需要入库的信息,分页相关</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型 request HttpServletRequest
	 *
	 */
	String getInfoTable(HttpServletRequest request);

	
	/**
	 * 
	 * 
	 *<p>查询商品需要入库的信息数量,分页相关</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型 request HttpServletRequest
	 *
	 */
	String getPage(HttpServletRequest request);

	/**
	 * 
	 * 
	 *<p>查询所有仓库的信息</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型 request HttpServletRequest
	 *
	 */
	String getwarehouse(HttpServletRequest request);

}
