package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.AuthGroup;
import com.model.SystemAuth;
import com.service.AuthService;

@Controller
@RequestMapping("/authController")
public class AuthController {
	
	@Resource
	private AuthService as;
	
	/**
	 * <p>
	 * 查询所有权限的信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月25日
	 * @return
	 */
	@RequestMapping(value="/getAuth.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getAuth() {
		return as.queryAll();
	}
	/**
	 * <p>
	 * 查询该权限的详细信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月25日
	 * @return
	 */
	@RequestMapping(value="/getAuthInfo.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getAuthInfo(HttpServletRequest request) {
		return as.queryByAuthId(request);
	}
	/**
	 * <p>
	 * 查询authGroup的所有信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月25日
	 * @return
	 */
	@RequestMapping(value="/getAuthGroup.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getAuthGroup() {
		return as.queryAuthGroup();
	}
	/**
	 * <p>
	 * 修改权限信息，表单提交的信息拿来修改对应权限信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月25日
	 * @param request
	 * @return
	 */
	@RequestMapping("/authChange.ajax")
	public @ResponseBody String authChange(SystemAuth sa) {
		return as.authChange(sa);
	}
	/**
	 * <p>
	 * 删除权限信息，表单提交的权限ID拿来删除对应权限信息，
	 * 以及system_role_auth表中的角色与权限相关的信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月25日
	 * @param request
	 * @return
	 */
	@RequestMapping("/authDelete.ajax")
	public @ResponseBody String authDelete(HttpServletRequest request) {
		return as.authDelete(request);
	}
	/**
	 * <p>
	 * 增加权限信息，id为uuid生成
	 * </p>
	 * @author 小小
	 * @Date 2018年6月25日
	 * @param sa
	 * @return
	 */
	@RequestMapping("/authAdd.ajax")
	public @ResponseBody String authAdd(SystemAuth sa) {
		return as.authAdd(sa);
	}
	/**
	 * <p>
	 * 根据id查询该权限组的详细信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月25日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getAuthGroupInfo.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getAuthGroupInfo(HttpServletRequest request) {
		return as.queryByAuthGroupId(request);
	}
	/**
	 * <p>
	 * 修改权限组信息，表单提交的信息拿来修改对应权限组信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月26日
	 * @param ag
	 * @return
	 */
	@RequestMapping("/authGroupChange.ajax")
	public @ResponseBody String authGroupChange(AuthGroup ag) {
		return as.authGroupChange(ag);
	}
	/**
	 * <p>
	 * 增加权限组信息，id为uuid生成
	 * </p>
	 * @author 小小
	 * @Date 2018年6月26日
	 * @param ag
	 * @return
	 */
	@RequestMapping("/authGroupAdd.ajax")
	public @ResponseBody String authGroupAdd(AuthGroup ag) {
		return as.authGroupAdd(ag);
	}
	
	/**
	 * <p>
	 * 删除权限组信息，表单提交的权限ID拿来删除对应权限组信息，
	 * </p>
	 * @author 小小
	 * @Date 2018年6月26日
	 * @param request
	 * @return
	 */
	@RequestMapping("/authGroupDelete.ajax")
	public @ResponseBody String authGroupDelete(HttpServletRequest request) {
		return as.authGroupDelete(request);
	}
}
