package com.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 *<h2>采购单的接口层</h2>
 *<p>主要实现控制层和实现层的连接功能</p>
 * 
 *@author lily
 *
 *
 */
public interface PurchaseTableService {
	/**
	 * 
	 * 
	 *<p>查找卖家所有客户信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectCustomerAll();
	/**
	 * 
	 * 
	 *<p>根据传入的id，查询出客户信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectCustomerAllById(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>根据传入的编号，看该编号是否存在</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectextendIdById(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>初始化采购单登录人名字，制表时间，年份</p>
	 *@author lily
	 * @param request 
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectAccountSome(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>查找所有的原料</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectMaterialAll();
	/**
	 * 
	 * 
	 *<p>根据前台所传入的，增加采购单，采购单扩展表，与员工中间表，钱</p>
	 *@author lily
	 * @param request 
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String addAllTable(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>根据传入的id，查询所有的采购单编号</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectAllPurchaseId(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>方法实现的功能</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectExtendAllById(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>根据传入的扩展表id，查询所有采购表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectTableAllById(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>通过传入的Id，查询采购人</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectAccountName(HttpServletRequest request);

}
