package com.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.SalaryScaleTableMapper;
import com.mapper.SectionRoleMapper;
import com.mapper.SysRoleMapper;
import com.mapper.SystemAccountMapper;
import com.mapper.SystemAuthMapper;
import com.mapper.SystemRoleAuthMapper;
import com.model.SalaryScaleTable;
import com.model.SectionRole;
import com.model.SysRole;
import com.model.SystemAccount;
import com.model.SystemAuth;
import com.model.SystemRoleAuth;
import com.pojo.Page;
import com.pojo.SystemRolePojo;
import com.service.SystemRoleService;

@Service
public class SystemRoleServiceImpl implements SystemRoleService {

	@Resource
	private SysRoleMapper sr;

	@Resource
	private SystemAuthMapper sm;

	@Resource
	private SystemRoleAuthMapper rm;

	@Resource
	private SectionRoleMapper srm;

	@Resource
	private SystemAccountMapper sa;
	
	@Resource
	private SalaryScaleTableMapper sstm;

	private static Gson gson = new Gson();

	@Override
	public String getInfoTable(HttpServletRequest request) {
		String n = request.getParameter("n");
		String s = request.getParameter("sreach");
		int pageRecords = Integer.parseInt(request.getParameter("pageRecords"));
		if (s == null) {
			s = "";
		}
		int pnum = 1;
		if (n != null && n.length() > 0) {
			pnum = Integer.parseInt(n);
		}
		Map<String, Object> map = new HashMap<>();
		int start = (pnum - 1) * pageRecords;
		map.put("start", start);
		map.put("size", pageRecords);
		map.put("s", "%" + s + "%");
		List<SystemRolePojo> sm = sr.getRoleTable(map);
		return gson.toJson(sm);
	}

	@Override
	public String getPage(HttpServletRequest request) {
		String s = request.getParameter("sreach");
		int pageRecords = Integer.parseInt(request.getParameter("pageRecords"));
		if (s == null) {
			s = "";
		}
		Page p = new Page();
		int tn = sr.getTotalNum("%" + s + "%");
		p.setTotalRecords(tn + "");
		int t = 0;
		if (tn % pageRecords != 0) {
			t = tn / pageRecords + 1;
		} else {
			t = tn / pageRecords;
		}
		p.setTotal(t + "");
		return gson.toJson(p);
	}

	@Override
	public String block(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		SysRole se = sr.selectByPrimaryKey(roleId);
		SystemAccount sc = sa.selectByRoleId(roleId);
		if (sc != null) {
			sc.setAccountStatus("OFF");
			sa.updateByPrimaryKeySelective(sc);
		}
		se.setRoleStatus("1");
		sr.updateByPrimaryKeySelective(se);
		return null;
	}

	@Override
	public String start(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		SysRole se = sr.selectByPrimaryKey(roleId);
		SystemAccount sc = sa.selectByRoleId(roleId);
		if (sc != null) {
			sc.setAccountStatus("ON");
			sa.updateByPrimaryKeySelective(sc);
		}
		System.err.println(se.getRoleStatus());
		se.setRoleStatus("0");
		System.err.println(se.getRoleStatus());
		sr.updateByPrimaryKeySelective(se);
		return null;
	}

	@Override
	public String insetinfo(HttpServletRequest request) {
		String sectionId = request.getParameter("sectionId");
		System.err.println("__________"+sectionId);
		String roleName = request.getParameter("roleName");
		String roleDesc = request.getParameter("roleDesc");
		if (sr.selectByRoleName(roleName) == null) {
			SysRole se = new SysRole();
			SectionRole s = new SectionRole();
			SalaryScaleTable sst = new SalaryScaleTable();
			s.setSectionRoleId(UUID.randomUUID().toString());
			se.setRoleId(UUID.randomUUID().toString());
			s.setRoleId(se.getRoleId());
			s.setSectionId(sectionId);
			se.setRoleName(roleName);
			se.setRoleStatus("0");
			se.setRoleDesc(roleDesc);
			sr.insertSelective(se);
			srm.insertSelective(s);
			//增加职务对应薪资信息
			sst.setSalaryScaleId(UUID.randomUUID().toString());
			sst.setRoleId(se.getRoleId());
			sst.setChangeTime(new Date());
			sst.setSalary((double) 3000);
			sstm.insertSelective(sst);
			return "SUCCESS";
		} else {
			return "ERROR";
		}
	}

	@Override
	public void selectById(HttpServletRequest request) {
		String roleId = request.getParameter("roleId");
		SysRole se = sr.selectByPrimaryKey(roleId);
		List<SystemAuth> sa = sm.selectAll();
		List<SystemRoleAuth> ra = rm.selectByRoleId(roleId);
		List<String> authIds = new ArrayList<String>();
		for (SystemRoleAuth sr : ra) {
			authIds.add(sr.getAuthId());
		}
		request.setAttribute("authIds", authIds);
		request.setAttribute("sa", sa);
		request.setAttribute("se", se);
	}

	@Override
	public String update(HttpServletRequest request) {
		SysRole se = new SysRole();
		se.setRoleId(request.getParameter("roleId"));
		se.setRoleName(request.getParameter("roleName"));
		se.setRoleDesc(request.getParameter("roleDesc"));
		sr.updateByPrimaryKeySelective(se);
		return "SUCCESS";
	}

	@Override
	public void aolltRoleAuth(HttpServletRequest request) {
		SystemRoleAuth sa = new SystemRoleAuth();
		String roleId = request.getParameter("roleId");
		System.out.println(roleId);
		String[] authIds = request.getParameterValues("authIds");
		List<SystemRoleAuth> ra = rm.selectByRoleId(roleId);
		for (SystemRoleAuth sr : ra) {
			rm.deleteByPrimaryKey(sr.getRoleAuthId());
		}
		if (authIds != null) {
			for (String authId : authIds) {
				sa.setRoleAuthId(UUID.randomUUID().toString());
				sa.setAuthId(authId);
				sa.setRoleId(roleId);
				rm.insertSelective(sa);
			}
		}

	}

}
