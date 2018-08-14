/**
 * 
 */
package com.service;


import javax.servlet.http.HttpServletRequest;

import com.model.Formula;

/**
 * @Author QinPeng
 *
 * @Date 2018年6月23日
 */
public interface FormulaService {

	/**
	 * 添加数据信息
	 * @param req
	 * @param formula
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
//	String addFormula(HttpServletRequest req,Formula formula);
	/**
	 * @param req
	 * @return
	 */
	String addFormula(HttpServletRequest req);
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
	String updateById(HttpServletRequest req,Formula formula);

	/**
	 * 为添加界面获取相关信息
	 * @param req
	 * @return url
	 */
	String getData(HttpServletRequest req);








	

}
