package com.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <h2>进行商品库存表数据处理服务</h2>
 * <p>
 * 该服务层包含了所有对于商品库存表的逻辑处理。
 * </p>
 * @author FSK
 * @version 1.0
 */
public interface GcountService {

	/**
	 * 
	 * <h2>查询数据库数据并在页面进行分页和查询处理</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 商品库存表数据
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

	/**
	 * 
	 * 
	 *<p>将选中的商品总库存的信息传给修改页面</p>
	 *@author FSK
	 *@param 传入的参数 request HttpServletRequest
	 *
	 */
	void selectById(HttpServletRequest request);

	/**
	 * 
	 * 
	 *<p>修改商品总库存数量和金额，与存放仓库</p>
	 *@author FSK
	 *@param 传入的参数 request HttpServletRequest
	 *@return 返回值类型
	 *
	 */
	String update(HttpServletRequest request);

}
