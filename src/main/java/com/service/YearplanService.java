package com.service;

import javax.servlet.http.HttpServletRequest;

import com.model.YearpanlTable;

/**
 * 
 *<h2>生产总计划表接口层</h2>
 *<p>连接控制层和实现层</p>
 * 
 *@author lily
 *
 *
 */
public interface YearplanService {
	/**
	 * 
	 * 
	 *<p>查询出所有的总计划表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String getYearplanTable(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>为了计算总计划表信息分页</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String getPage(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>查询所有商品的信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectAllCommodity();
	/**
	 * 
	 * 
	 *<p>增加年度总计划表</p>
	 *@author lily
	 * @param request 
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String yearplanAdd(HttpServletRequest request, YearpanlTable yplan);
	/**
	 * 
	 * 
	 *<p>根据前端传入的计划表ID值，删除相应的信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String deleteCommodityById(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>查询关于传入的总计划表ID所获得的信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	YearpanlTable selectByYearpanlTableId(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>修改总计划表信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String yearplanUpdate(HttpServletRequest request, YearpanlTable yearTable);

}
