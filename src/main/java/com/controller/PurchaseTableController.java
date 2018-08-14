package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.PurchaseTableService;

/**
 * 
 *<h2>采购表控制层</h2>
 *<p>实现采购表和采购延伸表的所有功能</p>
 * 
 *@author lily
 *
 *
 */
@Controller
@RequestMapping("/PurchaseTableController")
public class PurchaseTableController {

	@Resource
	private PurchaseTableService ptser;
	/**
	 * 
	 * 
	 *<p>查找卖家所有客户信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/selectCustomerAll.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectCustomerAll() {
		
		return ptser.selectCustomerAll();
	}
	/**
	 * 
	 * 
	 *<p>根据客户id，查询出客户信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	
	@RequestMapping(value="/selectCustomerAllById.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectCustomerAllById(HttpServletRequest request) {
		
		return ptser.selectCustomerAllById(request);
	}
	/**
	 * 
	 * 
	 *<p>根据传入的编号，看该编号是否存在</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/selectextendIdById.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectextendIdById(HttpServletRequest request) {
		
		return ptser.selectextendIdById(request);
	}
	/**
	 * 
	 * 
	 *<p>初始化采购单登录人名字，制表时间，年份</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/selectAccountSome.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectAccountSome(HttpServletRequest request) {		
		return ptser.selectAccountSome(request);
	}
	/**
	 * 
	 * 
	 *<p>查询所有原料</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/selectMaterialAll.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectMaterialAll() {		
		return ptser.selectMaterialAll();
	}
	/**
	 * 
	 * 
	 *<p>根据前台所传入的，增加采购单，采购单扩展表，与员工中间表，钱</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/addAllTable.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String addAllTable(HttpServletRequest request) {		
		return ptser.addAllTable(request);
	}
	/**
	 * 
	 * 
	 *<p>跳转保存年份</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/doSearchCard.do")
	public String doSearchCard(HttpServletRequest request) {
		request.setAttribute("searchCard", request.getParameter("searchCard"));
		return "../view/purchase-table/purchase-table-list2.jsp";
	}
	/**
	 * 
	 * 
	 *<p>根据年份，查询所有的采购单编号</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/selectAllPurchaseId.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectAllPurchaseId(HttpServletRequest request) {		
		return ptser.selectAllPurchaseId(request);
	}
	/**
	 * 
	 * 
	 *<p>根据传入的编号id查询采购扩展表信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/selectExtendAllById.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectExtendAllById(HttpServletRequest request) {	
		return ptser.selectExtendAllById(request);
	}
	/**
	 * 
	 * 
	 *<p>根据传入的扩展表id，查询所有采购表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/selectTableAllById.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectTableAllById(HttpServletRequest request) {	
		return ptser.selectTableAllById(request);
	}
	/**
	 * 
	 * 
	 *<p>通过传入的Id，查询采购人</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/selectAccountName.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectAccountName(HttpServletRequest request) {	
		return ptser.selectAccountName(request);
	}
}
