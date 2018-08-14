/**
 * 
 */
package com.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.RopTable;
import com.service.ProductionService;

/**
 * @Author QinPeng
 *
 * @Date 2018年6月23日
 */
@Controller
@RequestMapping("/productionController")
public class ProductionController {
	@Autowired
	private ProductionService ps;
	/**
	 * 添加数据信息
	 * @param req
	 * @param ropTable
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
	@RequestMapping("/add.do")
	public @ResponseBody String add(HttpServletRequest req,RopTable ropTable) {
		return ps.addRopTable(req,ropTable);
		
	}
	
	/**
	 * 为添加界面获取相关信息
	 * @param req
	 * @return url
	 */
	@RequestMapping("/addUI.do")
	public  String addUI(HttpServletRequest req) {
		return ps.getData(req);
		
	}
	/**
	 * 获取表单总页数，总条数
	 * @param req
	 * @return string
	 */
	@RequestMapping("/page.ajax")
	public @ResponseBody String getPage(HttpServletRequest req) {
		return ps.getStaticPage(req);
	}
	/**
	 * 获取一页需要显示的信息
	 * @param req
	 * @return string
	 */
	@RequestMapping(value="/getList.do",produces="text/html;charset=utf-8")
	/*@RequestMapping(value="/getList.do")*/
	public @ResponseBody String getList(HttpServletRequest req) {
		return ps.getPageList(req);
	}
	/**
	 * 单个删除信息
	 * @param req
	 * @return SUCCESS OR ERROR
	 */
	@RequestMapping("/del.do")
	public @ResponseBody String del(HttpServletRequest req) {
		return ps.updateStatus(req);
	}
	
	/**
	 * 向生产管理表记录生产单信息
	 * @param req
	 * @return SUCCESS OR ERROR
	 */
	@RequestMapping("/notarize.do")
	public @ResponseBody String notarize(HttpServletRequest req) {
		String str="Material_ERROR";
		try {
			str=ps.inertOrderPerson(req);
		} catch (Exception e) {
			e.printStackTrace();
			str="Material_ERROR";
		}
		return str;
	}
	
	/**
	 * 为了刷新页面，添加req.setAttribute("keyword", keyword);
	 * @param req
	 * @return
	 */
	@RequestMapping("/sreach.do")
	public String sreach(HttpServletRequest req) {
		String keyword=req.getParameter("keyword");
		if(keyword!=null) {
			req.setAttribute("keyword", keyword);
		}
		
		return "/view/production/production_list.jsp";
	}
	/**
	 * 根具id查询信息，传给页面用于信息修改
	 * @param req
	 * @return 
	 */
	@RequestMapping("/updateUI.do")
	public String updateUI(HttpServletRequest req) {
		
		ps.selectById(req);
		return "/view/production/production_update.jsp";
	}
	/**
	 * 获取页面传来的信息，实现信息修改
	 * @param req
	 * @param ropTable
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
	@RequestMapping("/update.do")
	public @ResponseBody String update(HttpServletRequest req,RopTable ropTable) {
		return ps.updateById(req,ropTable);
	}
}
