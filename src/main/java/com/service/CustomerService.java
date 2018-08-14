package com.service;

import javax.servlet.http.HttpServletRequest;

import com.model.CustomerInfo;

public interface CustomerService {

	/**
	 * <p>
	 * 获取客户数据的页数及数据条数
	 * </p>
	 * @author 小小
	 * @Date 2018年6月23日
	 * @param request
	 * @return
	 */
	String getPage(HttpServletRequest request);
	/**
	 * <p>
	 * 查询所有客户信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月23日
	 * @param request
	 * @return
	 */
	String getCustomerTable(HttpServletRequest request);
	//根据id查询客户信息
	CustomerInfo queryCustomerById(HttpServletRequest request);
//	修改客户信息
	String updateCustomer(CustomerInfo data);
//	删除客户，实际是修改客户状态为1
	String deleteCustomer(HttpServletRequest request);
//	添加用户，默认状态0
	String addCustomer(CustomerInfo data);
//	恢复客户，实际是修改客户状态为0
	String backCustomer(HttpServletRequest request);
	
}
