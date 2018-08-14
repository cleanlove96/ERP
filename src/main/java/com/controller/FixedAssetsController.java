package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.FixedAssets;
import com.service.AssetsService;

/**
 * <p>
 * 对固定资产的相关操作，crud
 * </p>	
 * @Copyright (C),华清远见
 * @author 小小
 * @Date:2018年6月29日
 */
@Controller
@RequestMapping("/fixedAssetsController")
public class FixedAssetsController {
	
	@Resource
	private AssetsService as;
	
	/**
	 * <p>
	 * 获取固定资产信息的总条数
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPage.ajax")
	public @ResponseBody String getPage(HttpServletRequest request) {
		return as.getPage(request);
	}
	
	/**
	 * <p>
	 * 根据kkpager的点击页面查询该页面的固定资产的详细信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getFixedAssetsTable.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getFixedAssetsTable(HttpServletRequest request) {
		return as.getFixedAssetsTable(request);
	}
	
	/**
	 * <p>
	 * 根据固定资产名子模糊查询信息
	 * 跳转回当前页面
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @param request
	 * @return
	 */
	@RequestMapping("/doSreach.do")
	public String doSreach(HttpServletRequest request) {
		request.setAttribute("sreach", request.getParameter("sreach"));
		return "../view/fixed_assets/fixed_assets_info.jsp";
	}
	/**
	 * <p>
	 * 对固定资产的编号的自动初始化，
	 * 查询数据库存有的排在最后一个编号，加一初始化为下一个资产的编号
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @return
	 */
	@RequestMapping("/getFixedAssetsNum.ajax")
	public @ResponseBody String getFixedAssetsNum() {
		return as.getFixedAssetsNum();
	}
	/**
	 * <p>
	 * 添加固定资产数据
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @param fa 固定资产实体对象
	 * @return
	 */
	@RequestMapping("/addFixedAssets.ajax")
	public @ResponseBody String addFixedAssets(FixedAssets fa) {
		return as.addFixedAssets(fa);
	}
	
	/**
	 * <p>
	 * 根据id查询固定资产信息传到前台用于修改
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @param request
	 * @return
	 */
	@RequestMapping("/fixedAssetsEdit.do")
	public String fixedAssetsEdit(HttpServletRequest request) {
		FixedAssets fa = as.queryFixedAssetsById(request);
		request.setAttribute("fixedAssets", fa);
		return "../view/fixed_assets/fixed_assets_edit.jsp";
	}
	/**
	 * <p>
	 * 修改固定资产信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @param fa 固定资产实体对象
	 * @return
	 */
	@RequestMapping("/updateFixedAssets.ajax")
	public @ResponseBody String updateFixedAssets(FixedAssets fa) {
		return as.updateCustomer(fa);
	}
	/**
	 * <p>
	 * 根据id删除固定资产信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteFixedAssets.ajax")
	public @ResponseBody String deleteFixedAssets(HttpServletRequest request) {
		return as.deleteFixedAssets(request);
	}
}
