package com.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <h2>进行商品出入库数据处理服务</h2>
 * <p>
 * 该服务层包含了所有对于商品出入库记录表的逻辑处理。
 * </p>
 * @author FSK
 * @version 1.0
 */
public interface GoodsOutInStoreService {

	
	/**
	 * 
	 * <h2>查询数据库数据并在页面进行分页和查询处理</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 商品出入库数据
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
	 * <h2>添加商品入库数据并同时向商品库存表中增加数据</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 标识符
	 *<p>
	 *判定符包含以下几种：
	 *</p>
	 *<ul>
	 *<li> "SCUUESS" 入库成功</li>
	 *<li>  "ERROR"  操作有误</li>
	 *</ul>
	 */
	String insertGoodsOutInStore(HttpServletRequest request);
	
	/**
	 * 
	 * <h2>添加商品出库数据并同时向商品库存表中减少数据</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 标识符
	 *<p>
	 *判定符包含以下几种：
	 *</p>
	 *<ul>
	 *<li>"SCUUESS" 出库成功</li>
	 *<li> "LAZY"  库存不足</li>
	 *<li> "ERROR"  操作有误</li>
	 *</ul>

	 */
	String deleteGoodsOutInStore(Map map);


	/**
	 * 
	 * 
	 *<p>确认入库数据，将入库数据录入入库表中并增加总库存数据</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型 request HttpServletRequest
	 *
	 */
	String ack(HttpServletRequest request);

}
