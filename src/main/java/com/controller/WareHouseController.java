/**
 * 
 */
package com.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.model.WareHouse;
import com.service.WareHouseService;

/**
 * @Author QinPeng
 *
 * @Date 2018年6月23日
 */
@Controller
@RequestMapping("/wareHouseController")
public class WareHouseController {
	@Autowired
	private WareHouseService whs;
	/**
	 * 添加数据信息
	 * @param req
	 * @param wareHouse
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
	@RequestMapping("/add.do")
	public @ResponseBody String add(HttpServletRequest req,WareHouse wareHouse) {
		return whs.addWareHouse(req,wareHouse);
		
	}
	/**
	 * 获取表单总页数，总条数
	 * @param req
	 * @return string
	 */
	@RequestMapping("/page.ajax")
	public @ResponseBody String getPage(HttpServletRequest req) {
		return whs.getStaticPage(req);
	}
	/**
	 * 获取一页需要显示的信息
	 * @param req
	 * @return string
	 */
	@RequestMapping(value="/getList.do",produces="text/html;charset=utf-8")
	/*@RequestMapping(value="/getList.do")*/
	public @ResponseBody String getList(HttpServletRequest req) {
		return whs.getPageList(req);
	}
	/**
	 * 单个删除信息
	 * @param req
	 * @return SUCCESS OR ERROR
	 */
	@RequestMapping("/del.do")
	public @ResponseBody String del(HttpServletRequest req) {
		return whs.updateStatus(req);
	}
	/**
	 * 批量删除信息
	 * @param ids
	 * @param req
	 * @return SUCCESS OR ERROR
	 */
	@RequestMapping("/dels.do")
	public @ResponseBody String dels(String ids,HttpServletRequest req) {
		String result=whs.updateSelectStatus(ids);
		System.out.println("+++++++++++++++++++"+result);
		return result;
		
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
		
		return "/view/warehouse/warehouse_list.jsp";
	}
	/**
	 * 根具id查询信息，传给页面用于信息修改
	 * @param req
	 * @return 
	 */
	@RequestMapping("/updateUI.do")
	public String updateUI(HttpServletRequest req) {
		
		whs.selectById(req);
		return "/view/warehouse/warehouse_update.jsp";
	}
	/**
	 * 获取页面传来的信息，实现信息修改
	 * @param req
	 * @param wareHouse
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
	@RequestMapping("/update.do")
	public @ResponseBody String update(HttpServletRequest req,WareHouse wareHouse) {
		return whs.updateById(req,wareHouse);
	}
}
