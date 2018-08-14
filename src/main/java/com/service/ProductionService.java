/**
 * 
 */
package com.service;


import javax.servlet.http.HttpServletRequest;

import com.model.RopTable;

/**
 * @Author QinPeng
 *
 * @Date 2018年6月23日
 */
public interface ProductionService {

	/**
	 * 添加数据信息
	 * @param req
	 * @param ropTable
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
	String addRopTable(HttpServletRequest req,RopTable ropTable);

	/**
	 * 获取表单总页数，总条数
	 * @param req
	 * @return string
	 */
	String getStaticPage(HttpServletRequest req);

	/**
	* 获取一页需要显示的信息，转换成json
	 * @param req
	 * @return string
	 */
	String getPageList(HttpServletRequest req);

	/**
	 * 通过传来的id判断是否存在，若在修改状态表示被删除
	 * @param req
	 * @return SUCCESS OR ERROR 
	 */
	String updateStatus(HttpServletRequest req);

	/**
	 * 根具id查询信息，传给页面用于信息修改
	 * @param req
	 * @return string
	 */
	void selectById(HttpServletRequest req);

	/**
	 * 根具id查询信息，修改信息
	 * @param req
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
	String updateById(HttpServletRequest req,RopTable ropTable);

	/**
	 * 为添加界面获取相关信息
	 * @param req
	 * @return url
	 */
	String getData(HttpServletRequest req);

	/**
	 * 向生产管理表记录生产单信息
	 * @param req
	 * @return SUCCESS OR ERROR
	 * @throws Exception 
	 */
	String inertOrderPerson(HttpServletRequest req) throws Exception;






	

}
