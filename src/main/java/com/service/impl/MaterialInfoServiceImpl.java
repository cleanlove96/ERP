/*****************************************************
 *  HISTORY
 *  FileName:MaterialInfoServiceImpl.java
 *  Package:com.service.impl
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年6月23日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.CustomerInfoMapper;
import com.mapper.SysMaterialMapper;
import com.model.CustomerInfo;
import com.model.SysMaterial;
import com.pojo.Page;
import com.service.MaterialInfoService;

/**
 * <p>
 * 原料基本信息实现层
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年6月23日
 */
@Service
public class MaterialInfoServiceImpl implements MaterialInfoService {
	private static Gson gson = new Gson();
	private static final int numPerPage = 5;
	@Resource
	private SysMaterialMapper sysMaterialMapper;
	@Resource
	private CustomerInfoMapper cim;
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/***
	 * 获取所有的原料信息，并返回一个
	 */
	@Override
	public String getAllSysMaterialInfo(HttpServletRequest request) {
		String num = request.getParameter("n");
		String search = request.getParameter("search");

		if (search == null) {
			search = "";
			
		}
		int pum = 1;
		if (num != null && num.length() > 0) {
			pum = Integer.parseInt(num);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		int start = (pum - 1) * numPerPage;
		map.put("start", start);
		map.put("size", numPerPage);
		map.put("search", "%" + search + "%");
		List<SysMaterial> list = sysMaterialMapper.getMaterialTables(map);
		return gson.toJson(list);
	}

	/***
	 * 获取页面的页码数
	 */
	@Override
	public String getPage(HttpServletRequest request) {
		String search = request.getParameter("search");

		if (search == null) {
			search = "";
		}
		
		Page page = new Page();
		int sum = sysMaterialMapper.getTotalNum("%" + search + "%");
		page.setTotalRecords(sum + "");
		int tn = 0;
		if (sum % 5 > 0) {
			tn = (sum / 5) + 1;
			
		} else {
			tn = (sum / 5);
		}
		page.setTotal(tn + "");
		return gson.toJson(page);
	}

	/***
	 * 增加原料信息
	 */
	@Override
	public String SysMaterialInfoAdd(HttpServletRequest request, HttpServletResponse response) {	
	//判断c当前name是否有重名的情况
		
		List  <SysMaterial> c= sysMaterialMapper.selectByPrimaryName(request.getParameter("materialName"));
		if (c!=null&&c.size()!=0) {
			return "repetition";
		}else {
			SysMaterial sysMaterial = new SysMaterial();
			sysMaterial.setMaterialName(request.getParameter("materialName"));		
			sysMaterial.setMaterialUpdateTime(sdf.format(new Date()));
			sysMaterial.setMaterialCreateTime(sdf.format(new Date()));
			sysMaterial.setMaterialUnit(request.getParameter("materialUnit"));
			sysMaterial.setCustomerId(request.getParameter("customerId"));
			sysMaterial.setPrice(Double.parseDouble(request.getParameter("price")));
			sysMaterial.setMaterialId(UUID.randomUUID().toString());
			sysMaterial.setMaterialStatus("0");
			int b = sysMaterialMapper.insert(sysMaterial);
			return "succeed";
		}
		
	}

	/***
	 * 编辑界面，根据前端传来的ID值，查询当前id的信息
	 */
	@Override
	public String queryBySysMaterialId(HttpServletRequest request) {
		String SysMaterialId = request.getParameter("SysMaterialId");		
		SysMaterial sysMaterial = sysMaterialMapper.selectByPrimaryKey(SysMaterialId);
		request.setAttribute("sysMaterial", sysMaterial);
		String demand="卖家";
		List<CustomerInfo> list=cim.selectByDemand(demand);
		System.err.println("______________________"+list.size());
		request.setAttribute("customerList", list);
		return "/view/materialInfo/material-edit.jsp";
	}

	/***
	 * 根据前台传来的数据对后台进行修改
	 */
	@Override
	public String updateBySysMaterialId(HttpServletRequest request) {
		SysMaterial sysMaterial = new SysMaterial();
		/*List  <SysMaterial> c= sysMaterialMapper.selectByPrimaryName(request.getParameter("materialName"));
		if (c!=null&&c.size()!=0) {
			return "repetition";
		}else {*/
			String materialName = request.getParameter("materialName");
			String materialUnit = request.getParameter("materialUnit");
			String price = request.getParameter("price");
			String materialId = request.getParameter("materialId");
			sysMaterial.setCustomerId(request.getParameter("customerId"));
			System.err.println(request.getParameter("customerId"));
			sysMaterial.setMaterialUpdateTime(sdf.format(new Date()));
			sysMaterial.setMaterialName(materialName);
			sysMaterial.setMaterialUnit(materialUnit);
			sysMaterial.setMaterialId(materialId);
			sysMaterial.setPrice(Double.parseDouble(request.getParameter("price")));
			int b = sysMaterialMapper.updateBySysMaterialId(sysMaterial);
			if (b >= 0) {
				return "succee";
			} else {
				return "error";
			}

		}
		
	

	/***
	 * 原料信息的停用还是启用
	 */
	@Override
	public String updateBySysMaterialStatus(HttpServletRequest request, HttpServletResponse response) {
		SysMaterial sysMaterial = new SysMaterial();
		String sysMaterialId = request.getParameter("materialId");
		String sysMaterialStatus = request.getParameter("Status");
		sysMaterial.setMaterialId(sysMaterialId);
		sysMaterial.setMaterialStatus(sysMaterialStatus);
		
		int b = sysMaterialMapper.updateBySysMaterialStatus(sysMaterial);
		if (b >= 0) {
			return "succee";
		} else {
			return "error";
		}

	}

	/**
	 * 搜索到 所有的供应商传给界面
	 * @param request
	 */
	@Override
	public void selByStatus(HttpServletRequest request) {
		String demand="卖家";
		List<CustomerInfo> list=cim.selectByDemand(demand);
		System.err.println("______________________"+list.size());
		request.setAttribute("customerList", list);
	}

}
