package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.CustomerInfo;
import com.service.CustomerService;

/**
 * <p>
 * 客户控制层，处理客户相关信息
 * </p>	
 * @Copyright (C),华清远见
 * @author 小小
 * @Date:2018年6月23日
 */
@Controller
@RequestMapping("/customerController")
public class CustomerController {
	@Resource
	private CustomerService cs;
	
	/**
	 * <p>
	 * 获取状态为0的客户信息的总条数
	 * </p>
	 * @author 小小
	 * @Date 2018年6月23日
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPage.ajax")
	public @ResponseBody String getPage(HttpServletRequest request) {
		return cs.getPage(request);
	}
	/**
	 * <p>
	 *查询客户所有信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月23日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getCustomerTable.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getCustomerTable(HttpServletRequest request) {
		return cs.getCustomerTable(request);
	}
	/**
	 * <p>
	 * 根据客户名模糊查询信息
	 * 跳转回当前页面
	 * </p>
	 * @author 小小
	 * @Date 2018年6月23日
	 * @param request
	 * @return
	 */
	@RequestMapping("/doSreach.do")
	public String doSreach(HttpServletRequest request) {
		request.setAttribute("sreach", request.getParameter("sreach"));
		return "../view/customer_relation/customer_info.jsp";
	}
	/**
	 * <p>
	 * 根据客户id查询客户信息传递到修改页面
	 * </p>
	 * @author 小小
	 * @Date 2018年6月23日
	 * @param request 请求对象
	 * @return 
	 */
	@RequestMapping("/customerEdit.do")
	public String customerEdit(HttpServletRequest request) {
		CustomerInfo ci = cs.queryCustomerById(request);
		request.setAttribute("customer", ci);
		return "../view/customer_relation/customer_edit.jsp";
	}
	/**
	 * <p>
	 * 前端传输的数据用于修改客户信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月24日
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateCustomer.ajax")
	public @ResponseBody String updateCustomer(HttpServletRequest request, CustomerInfo data) {
		return cs.updateCustomer(data);
	}
	/**
	 * <p>
	 * 删除客户，实际是修改客户状态为1
	 * </p>
	 * @author 小小
	 * @Date 2018年6月24日
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteCustomer.ajax")
	public @ResponseBody String deleteCustomer(HttpServletRequest request) {
		return cs.deleteCustomer(request);
	}
	
	/**
	 * <p>
	 * 添加客户，返回SUCCESS添加成功
	 * </p>
	 * @author 小小
	 * @Date 2018年6月24日
	 * @param request
	 * @param data
	 * @return
	 */
	@RequestMapping("/addCustomer.ajax")
	public @ResponseBody String addCustomer(HttpServletRequest request, CustomerInfo data) {
		return cs.addCustomer(data);
	}
	/**
	 * <p>
	 * 恢复客户，实际是修改客户状态为0
	 * </p>
	 * @author 小小
	 * @Date 2018年6月24日
	 * @param request
	 * @return
	 */
	@RequestMapping("/backCustomer.ajax")
	public @ResponseBody String backCustomer(HttpServletRequest request) {
		return cs.backCustomer(request);
	}
	/**
	 * <p>
	 * 根据客户名模糊查询信息
	 * 跳转回当前页面
	 * </p>
	 * @author 小小
	 * @Date 2018年6月23日
	 * @param request
	 * @return
	 */
	@RequestMapping("/doSreach2.do")
	public String doSreach2(HttpServletRequest request) {
		request.setAttribute("sreach", request.getParameter("sreach"));
		return "../view/customer_relation/customer_rollback.jsp";
	}
}
