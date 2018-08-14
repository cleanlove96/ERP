/*****************************************************
 *  HISTORY
 *  FileName:FinancialManagementService.java
 *  Package:com.service
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年6月29日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.service;

import javax.servlet.http.HttpServletRequest;

import com.pojo.CostProfitTable;

/**
 * <p>
 * 财务管理模块服务层
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年6月29日
 */
public interface FinancialManagementService {
	/***
	 * 
	 * <p>
	 * 获取成本利润，以及销售的总金额， 成本包括原料的成本及其员工工资
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年6月29日
	 * @param request
	 *            HttpServletRequest 请求对象
	 * @return CostProfitTable模型对象
	 */
	CostProfitTable getCostProfitTable(HttpServletRequest request);

	/****
	 * 
	 * <p>
	 * SaleBillDetail 销售单明细 pojo 查询所有销售单的明细情况 根据每张销售单据id查询所有的销售单销售状况
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年7月1日
	 * @param request
	 *            HttpServletRequest 请求对象
	 * @return 返回一个SaleBillDetail 对象
	 */
	String getSaleBillDetail(HttpServletRequest request);

	/***
	 * 
	 * <p>
	 * 获取总的页码数
	 * </p>
	 * 
	 * @author zm
	 * @Date 2018年7月1日
	 * @param request
	 * @return
	 */
	String getAllBillsPage(HttpServletRequest request);
	/***
	 * 
	 * <p>
	 * 商品销售统计获取素有商品的页码
	 * </p>
	 * @author zm
	 * @Date 2018年7月1日
	 * @param request
	 * @return
	 */
	String getAllGoodsTjPage(HttpServletRequest request);
	/***
	 * 
	 * <p>
	 * 获取所有的商品统计的显示内容
	 * </p>
	 * @author zm
	 * @Date 2018年7月2日
	 * @param request
	 * @return
	 */
	String  getAllGoodsTjBills(HttpServletRequest request);
	/***
	 * 
	 * <p>
	 * 成本利润统计，先算页码
	 * </p>
	 * @author zm
	 * @Date 2018年7月3日
	 * @param request
	 * @return
	 */
	String  getCBLRTjPages(HttpServletRequest request);
	/****
	 * 
	 * <p>
	 * 成本利润统计，查询出来一页有多少数据
	 * </p>
	 * @author zm
	 * @Date 2018年7月3日
	 * @param request
	 * @return
	 */
	String  getCBLRTjBills(HttpServletRequest request);
    
}
