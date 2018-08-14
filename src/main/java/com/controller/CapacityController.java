/**
 * 
 */
package com.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mapper.SystemCommodityInformationMapper;
import com.model.CapacityInfo;
import com.model.SystemCommodityInformation;
import com.service.CapacityService;

/**
 * @Author QinPeng
 *
 * @Date 2018年6月23日
 */
@Controller
@RequestMapping("/capacityController")
public class CapacityController {
	@Autowired
	private CapacityService cs;
	@Autowired
	private SystemCommodityInformationMapper scim;
	/**
	 * 根具id查询信息，传给页面用于信息修改
	 * @param req
	 * @return 
	 */
	@RequestMapping("/addUI.do")
	public String addUI(HttpServletRequest req) {
		
		List<SystemCommodityInformation>list=scim.selectAllCommodity();
		req.setAttribute("list", list);
		return "/view/capacity/capacity_add.jsp";
	}
	/**
	 * 添加数据信息
	 * @param req
	 * @param capacity
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
	@RequestMapping("/add.do")
	public @ResponseBody String add(HttpServletRequest req,CapacityInfo capacityInfo) {
		return cs.addCapacityInfo(req,capacityInfo);
	}
	/**
	 * 获取表单总页数，总条数
	 * @param req
	 * @return string
	 */
	@RequestMapping("/page.ajax")
	public @ResponseBody String getPage(HttpServletRequest req) {
		return cs.getStaticPage(req);
	}
	/**
	 * 获取一页需要显示的信息
	 * @param req
	 * @return string
	 */
	@RequestMapping(value="/getList.do",produces="text/html;charset=utf-8")
	/*@RequestMapping(value="/getList.do")*/
	public @ResponseBody String getList(HttpServletRequest req) {
		return cs.getPageList(req);
	}
	/**
	 * 单个删除信息
	 * @param req
	 * @return SUCCESS OR ERROR
	 */
	@RequestMapping("/del.do")
	public @ResponseBody String del(HttpServletRequest req) {
		return cs.updateStatus(req);
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
		
		return "/view/capacity/capacity_list.jsp";
	}
	/**
	 * 根具id查询信息，传给页面用于信息修改
	 * @param req
	 * @return 
	 */
	@RequestMapping("/updateUI.do")
	public String updateUI(HttpServletRequest req) {
		
		cs.selectById(req);
		return "/view/capacity/capacity_update.jsp";
	}
	/**
	 * 获取页面传来的信息，实现信息修改
	 * @param req
	 * @param capacity
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
	@RequestMapping("/update.do")
	public @ResponseBody String update(HttpServletRequest req,CapacityInfo capacity) {
		return cs.updateById(req,capacity);
	}
}
