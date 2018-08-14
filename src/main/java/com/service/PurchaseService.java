package com.service;

import javax.servlet.http.HttpServletRequest;

import com.model.Purchase;

/**
 * 
 *<h2>采购计划表接口层</h2>
 *<p>实现采购计划表控制层和实现层相连接</p>
 * 
 *@author lily
 *
 *
 */
public interface PurchaseService {
	/**
	 * 
	 * 
	 *<p>根据传入的年份，计算出该年份总计划表里面所需要的原料</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectAllNeed(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>根据传入的原料id，传回其单位</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectUnit(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>添加总采购计划表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String myPurchaseAdd(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>得到分页的所有信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String getPage(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>根据分页，得到分计划表信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String getPurchaseTable(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>查看采购计划表的所有数量</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectPurchaseAll(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>删除采购计划表信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String deletePurchaseById(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>用前台传入的采购总计划表id，返回信息，以便修改</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	Purchase updatePurchaseTable(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>修改采购计划表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String purchaseUpdate(HttpServletRequest request);
	

}
