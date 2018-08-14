package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.model.AuthGroup;
import com.model.SystemAuth;
import com.pojo.AuthGroupPojo;

/**
 * 
 * <h2>负责权限功能</h2>
 * <p>执行权限相关的逻辑处理。</p>
 * @author 青阳龙野(kohgylw)
 * @version 1.0
 */
public interface AuthService {
	
	/**
	 * 
	 * <h2>根据用户ID生成一个按钮组列表</h2>
	 * <p>从Session中得到登录用户的ID，并由此生成权限列表。</p>
	 * @author 青阳龙野(kohgylw)
	 * @param request HttpServletRequest 请求对象
	 * @return List<AuthGroupPojo> 权限组的封装列表
	 */
	List<AuthGroupPojo> getAuthGroupPojo(HttpServletRequest request);

	/**
	 * <p>
	 * 查询所有权限信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月25日
	 * @return
	 */
	String queryAll();

	/**
	 * <p>
	 * 根据权限ID查询该权限的信息，生成信息面板
	 * </p>
	 * @author 小小
	 * @Date 2018年6月25日
	 * @param request 请求对象
	 * @return
	 */
	String queryByAuthId(HttpServletRequest request);

	/**
	 * <p>
	 * 查询authGroup的所有信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月25日
	 * @return
	 */
	String queryAuthGroup();

	/**
	 * <p>
	 * 修改权限信息，表单提交的信息拿来修改对应权限信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月25日
	 * @param sa
	 * @return
	 */
	String authChange(SystemAuth sa);

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
	String authDelete(HttpServletRequest request);

	/**
	 * <p>
	 * 增加权限信息，id为uuid生成
	 * </p>
	 * @author 小小
	 * @Date 2018年6月25日
	 * @param sa 权限对象实体
	 * @return
	 */
	String authAdd(SystemAuth sa);

	/**
	 * <p>
	 * 根据权限组AuthGroupId查询该权限组的详细信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月25日
	 * @param request
	 * @return
	 */
	String queryByAuthGroupId(HttpServletRequest request);

	/**
	 * <p>
	 * 修改权限组信息，表单提交的信息拿来修改对应权限组信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月26日
	 * @param ag
	 * @return
	 */
	String authGroupChange(AuthGroup ag);

	/**
	 * <p>
	 * 增加权限组信息，id为uuid生成
	 * </p>
	 * @author 小小
	 * @Date 2018年6月26日
	 * @param ag
	 * @return
	 */
	String authGroupAdd(AuthGroup ag);

	/**
	 * <p>
	 * 删除权限信息，表单提交的权限ID拿来删除对应权限信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月26日
	 * @param request
	 * @return
	 */
	String authGroupDelete(HttpServletRequest request);

}
