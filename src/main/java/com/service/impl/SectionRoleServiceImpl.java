/*****************************************************
 *  HISTORY
 *  FileName:SectionRoleServiceImpl.java
 *  Package:com.service.impl
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年6月26日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.SectionInfoMapper;
import com.mapper.SectionRoleMapper;
import com.mapper.SysRoleMapper;
import com.mapper.SystemAccountMapper;
import com.model.SectionInfo;
import com.model.SysRole;
import com.model.SystemAccount;
import com.pojo.Ztree;
import com.service.AccountService;
import com.service.SectionRoleService;

import net.sf.json.JSONArray;

/**
 * <p>
 * 
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年6月26日
 */
@Service
public class SectionRoleServiceImpl implements SectionRoleService {
	@Resource
	private SectionRoleMapper sectionRoleMapper;
	@Resource
	private SectionInfoMapper sectionInfoMapper;
	@Resource
	private SysRoleMapper sysRoleMapper;
	@Resource
	private SystemAccountMapper systemAccountMapper;

	private static Gson g = new Gson();

	/****
	 * 获取角色ID和部门ID
	 */
	@Override
	public String getAllNodeByJson() {
		List<Ztree> list = sectionRoleMapper.getAllNodes();
		List<SectionInfo> list1 = sectionInfoMapper.selectAllSectionInfo();
		List<Map> strList = new ArrayList<>();
		/*
		 * List<String> strList = new ArrayList<>(); for (SectionInfo sectionInfo :
		 * list1) { String s1 =
		 * "{id:"+sectionInfo.getSectionId()+",pId:0,name:"+sectionInfo.getSectionName()
		 * +"}"; System.out.println("所有部门：" + s1); strList.add(s1); // 未完待续
		 * List<SysRole> list2 =
		 * sysRoleMapper.selectAllRole(sectionInfo.getSectionId()); for (SysRole sysRole
		 * : list2) { String s2 =
		 * "{id:"+sysRole.getRoleId()+",pId:"+sectionInfo.getSectionId()+",name:"+
		 * sysRole.getRoleName()+"}"; strList.add(s2); System.out.println("所有职位的name" +
		 * sysRole.getRoleName() + "所有职位的ID" + sysRole.getRoleId());
		 * List<SystemAccount>list3=systemAccountMapper.getAllAccountByRole(sysRole.
		 * getRoleId()); System.out.println(list3.toString()); for (SystemAccount
		 * systemAccount : list3) { String
		 * s3="{id:"+systemAccount.getAccountId()+",pId:"+sysRole.getRoleId()+",name:"+
		 * systemAccount.getAccountName()+"}"; strList.add(s3);
		 * System.out.println("所有员工的name" + systemAccount.getAccountName() + "所有员工的ID" +
		 * systemAccount.getAccountId()); } } }
		 */
		for (SectionInfo sectionInfo : list1) {
			Map<String, String> map1 = new HashMap<>();
			map1.put("id", sectionInfo.getSectionId());
			map1.put("pId", "0");
			map1.put("name", sectionInfo.getSectionName());
			map1.put("icon", "css/zTreeStyle/img/bm.png");
			strList.add(map1);
			// 未完待续
			List<SysRole> list2 = sysRoleMapper.selectAllRole(sectionInfo.getSectionId());
			for (SysRole sysRole : list2) {
				Map<String, String> map2 = new HashMap<>();
				map2.put("id", sysRole.getRoleId());
				map2.put("pId", sectionInfo.getSectionId());
				map2.put("name", sysRole.getRoleName());
				map2.put("icon", "css/zTreeStyle/img/zw.png");
				strList.add(map2);
				List<SystemAccount> list3 = systemAccountMapper.getAllAccountByRole(sysRole.getRoleId());
				for (SystemAccount systemAccount : list3) {
					Map<String, String> map3 = new HashMap<>();
					map3.put("id", systemAccount.getAccountId());
					map3.put("pId", sysRole.getRoleId());
					map3.put("name", systemAccount.getAccountName());
					map3.put("icon", "css/zTreeStyle/img/ry.png");
					strList.add(map3);
					System.err.println(
							"所有员工的name" + systemAccount.getAccountName() + "所有员工的ID" + systemAccount.getAccountId());
				}
			}
		}
		System.err.println(JSONArray.fromObject(strList).toString());
		return JSONArray.fromObject(strList).toString();
	}

	/****
	 * 修改zrtee的父亲ID
	 */

	@Override
	public String updatePidById(HttpServletRequest request) {
		System.out.println("父亲id" + request.getParameter("pId"));
		System.out.println("本身的id" + request.getParameter("id"));
		Map<String, String> map = new HashMap();
		map.put("id", request.getParameter("id"));
		map.put("pId", request.getParameter("pId"));
		int b = systemAccountMapper.updatePidAndId(map);
		if (b >= 0) {
			return "success";
		} else {
			return "error";
		}

	}

	/****
	 * 根据ID查找信息
	 */
	@Override
	public String queryAllAccountinfo(HttpServletRequest request) {
		String id = request.getParameter("id");			
			SystemAccount sa = systemAccountMapper.selectByPrimaryKey(id);
			if(sa==null) {
				return "error";
			}
			return g.toJson(sa).toString();
	}
}
