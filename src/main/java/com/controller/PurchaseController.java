package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.model.Purchase;
import com.service.PurchaseService;


/**
 * 
 *<h2>采购总计划表控制层</h2>
 *<p>主要操作关于采购总计划的所有功能</p>
 * 
 *@author lily
 *
 *
 */
@Controller
@RequestMapping("/purchaseController")
public class PurchaseController {

	@Resource
	private PurchaseService pser;
	
	/**
	 * 
	 * 
	 *<p>把页面写的搜索语句保存在页面上</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/doSearchCard.do")
	public String doSearchCard(HttpServletRequest request) {
		request.setAttribute("searchCard", request.getParameter("searchCard"));
		request.setAttribute("myStartTime1", request.getParameter("myStartTime1"));
		request.setAttribute("myEndTime1", request.getParameter("myEndTime1"));
		request.setAttribute("myStartTime2", request.getParameter("myStartTime2"));
		request.setAttribute("myEndTime2", request.getParameter("myEndTime2"));
		return "../view/purchase/purchase-list.jsp";
	}
	/**
	 * 
	 * 
	 *<p>根据传入的年份，计算出该年份总计划表里面所需要的原料</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	
	@RequestMapping(value="/selectAllNeed.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String doLogin(HttpServletRequest request) {
		return pser.selectAllNeed(request);
	}
	/**
	 * 
	 * 
	 *<p>根据传入的原料id，传回其单位</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/selectUnit.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectUnit(HttpServletRequest request) {
		return pser.selectUnit(request);
	}
	/**
	 * 
	 * 
	 *<p>添加总采购计划表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/myPurchaseAdd.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String myPurchaseAdd(HttpServletRequest request) {
		
		return pser.myPurchaseAdd(request);
	}
	/**
	 * 
	 * 
	 *<p>获取分计划表的页码</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/getPage.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getPage(HttpServletRequest request) {	
		return pser.getPage(request);
	}
	/**
	 * 
	 * 
	 *<p>获取分计划表的所有信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/getPurchaseTable.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getPurchaseTable(HttpServletRequest request) {	
		return pser.getPurchaseTable(request);
	}
	/**
	 * 
	 * 
	 *<p>查看采购计划表所有的信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/selectPurchaseAll.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectPurchaseAll(HttpServletRequest request) {	
		return pser.selectPurchaseAll(request);
	}
	/**
	 * 
	 * 
	 *<p>删除采购计划表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/deletePurchaseById.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String deletePurchaseById(HttpServletRequest request) {	
		return pser.deletePurchaseById(request);
	}
	/**
	 * 
	 * 
	 *<p>利用前台传入的采购总计划表id，返回信息，以便修改</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/updatePurchaseTable.do")
	public String updatePurchaseTable(HttpServletRequest request) {
		Purchase purchase=pser.updatePurchaseTable(request);
		request.setAttribute("purchaseOnly",purchase );
		return "../view/purchase/purchase-update.jsp";
	}
	/**
	 * 
	 * 
	 *<p>修改计划表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/purchaseUpdate.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String purchaseUpdate(HttpServletRequest request) {	
		return pser.purchaseUpdate(request);
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
	@RequestMapping("/doSearchCardss.do")
	public String doSearchCardss(HttpServletRequest request) {
		request.setAttribute("searchCard", request.getParameter("searchCard"));
		request.setAttribute("myStartTime1", request.getParameter("myStartTime1"));
		request.setAttribute("myEndTime1", request.getParameter("myEndTime1"));
		request.setAttribute("myStartTime2", request.getParameter("myStartTime2"));
		request.setAttribute("myEndTime2", request.getParameter("myEndTime2"));
		return "../view/purchase/purchase-list2.jsp";
	}
	
	
}
