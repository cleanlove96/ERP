package com.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.AuthGroupMapper;
import com.mapper.SystemAuthMapper;
import com.mapper.SystemRoleAuthMapper;
import com.model.AuthGroup;
import com.model.SystemAuth;
import com.pojo.AuthGroupPojo;
import com.service.AuthService;

import sun.net.www.content.image.gif;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Resource
	private AuthGroupMapper agm;
	@Resource
	private SystemAuthMapper sam;
	@Resource
	private SystemRoleAuthMapper sram;
	private static Gson gson = new Gson();

	@Override
	public List<AuthGroupPojo> getAuthGroupPojo(HttpServletRequest request) {
		// TODO 自动生成的方法存根
		List<AuthGroup> groups=agm.selectAll();
		String accountId=(String) request.getSession().getAttribute("ACCOUNT");
		List<AuthGroupPojo> pojos=new ArrayList<>();
		for(AuthGroup ag:groups) {
			AuthGroupPojo agp=new AuthGroupPojo();
			agp.setAuthGroup(ag);
			Map<String, String> map=new HashMap<String, String>();
			map.put("accountId", accountId);
			map.put("authGroupId", ag.getAuthGroupId());
			List<SystemAuth> auths=sam.selectByAccountIdAndGroupId(map);
			agp.setAuths(auths);
			pojos.add(agp);
		}
		return pojos;
	}

	@Override
	public String queryAll() {
		List<SystemAuth> tree = sam.selectAll();
		return gson.toJson(tree);
	}

	@Override
	public String queryByAuthId(HttpServletRequest request) {
		String id = request.getParameter("authId");
		SystemAuth sa = sam.selectByPrimaryKey(id);
		return gson.toJson(sa);
	}

	@Override
	public String queryAuthGroup() {
		List<AuthGroup> groups=agm.selectAll();
		return gson.toJson(groups);
	}

	@Override
	public String authChange(SystemAuth sa) {
		int n = sam.updateByPrimaryKeySelective(sa);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}

	@Override
	public String authDelete(HttpServletRequest request) {
		String authId = request.getParameter("id");
		int n = sam.deleteByPrimaryKey(authId);
		sram.deleteByAuthId(authId);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}

	@Override
	public String authAdd(SystemAuth sa) {
		sa.setAuthId(UUID.randomUUID().toString());
		int n = sam.insertSelective(sa);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}

	@Override
	public String queryByAuthGroupId(HttpServletRequest request) {
		String id = request.getParameter("authGroupId");
		AuthGroup ag = agm.selectByPrimaryKey(id);
		return gson.toJson(ag);
	}

	@Override
	public String authGroupChange(AuthGroup ag) {
		int n = agm.updateByPrimaryKeySelective(ag);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}

	@Override
	public String authGroupAdd(AuthGroup ag) {
		ag.setAuthGroupId(UUID.randomUUID().toString());
		int n = agm.insertSelective(ag);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}

	@Override
	public String authGroupDelete(HttpServletRequest request) {
		String authGroupId = request.getParameter("id");
		int n = agm.deleteByPrimaryKey(authGroupId);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}

}
