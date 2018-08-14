package com.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <h2>进行原料出入库数据处理服务</h2>
 * <p>
 * 该服务层包含了所有对于原料出入库记录表的逻辑处理。
 * </p>
 * @author FSK
 * @version 1.0
 */
public interface MaterialOutInStoreService {

	
	/**
	 * 
	 * <h2>查询数据库数据并在页面进行分页和查询处理</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 原料出入库数据
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
	 * <h2>添加原料入库数据并同时向原料库存表中增加数据</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 标识符
	 * "SCUUESS" 入库成功
	 * "ERROR"  操作有误
	 */
	String insertMaterialOutInStore(HttpServletRequest request);
	
	
	/**
	 * 
	 * <h2>添加原料出库数据并同时向原料库存表中减少数据</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 标识符
	 * "SCUUESS" 出库成功
	 * "LAZY"  库存不足
	 * "ERROR"  操作有误
	 */
	String deleteMaterialOutInStore(HttpServletRequest request);


	/**
	 * 
	 * <h2>添加原料入库数据并同时向原料库存表中增加数据</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 标识符
	 * "SCUUESS" 入库成功
	 */
	String ack(HttpServletRequest request);

	/**
	 * 
	 * 
	 *<p>确认出库数据，将出库数据录入出库表中并减少总库存数据</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型 request HttpServletRequest
	 *
	 */
	String ac(HttpServletRequest request);


	/**
	 * 
	 * 
	 *<p>传输数据至实际原料出库页面</p>
	 *@author FSK
	 *@param 传入的参数
	 *@return 返回值类型 request HttpServletRequest
	 *
	 */
	void addUI(HttpServletRequest request);

}
