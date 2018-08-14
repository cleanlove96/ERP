package com.service;

import javax.servlet.http.HttpServletRequest;

import com.model.SectionInfo;

/**
 * 
 * <h2>进行部门信息处理服务</h2>
 * <p>该服务层包含了所有对于部门信息的逻辑处理。</p>
 * @author FSk
 * @version 1.0
 */
public interface SectionInfoService {

	/**
	 * 
	 * <h2>查询数据库数据并在页面进行分页和查询处理</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 部门数据
	 */
	String getInfoTable(HttpServletRequest request);
	
	/**
	 * 
	 * <h2>查询数据库数据并返回总页面数和总数据数</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 总页面数和总数据数
	 */
	String getPage(HttpServletRequest request);

	/**
	 * 
	 * <h2>根据部门Id修改部门的状态,状态码置为 1</h2>
	 * <p>状态码包含以下几种：</p>
	 * <ul>
	 * <li>0 启用</li>
	 * <li>1 停用</li>
	 * </ul>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 标识符
	 */
	String block(HttpServletRequest request);
	
	/**
	 * 
	 * <h2>根据部门Id修改部门的状态,状态码置为 0</h2>
	 * <p>状态码包含以下几种：</p>
	 * <ul>
	 * <li>0 启用</li>
	 * <li>1 停用</li>
	 * </ul>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 标识符
	 */
	String start(HttpServletRequest request);
	
	
	/**
	 * 
	 * <h2>增加部门数据</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 标识符
	 * <p>登录判定符包含以下几种：</p>
	 * <ul>
	 * <li>SUCCESS 增加成功</li>
	 * <li>ERROR 部门添加信息重复</li>
	 * </ul>
	 */
	String insetinfo(HttpServletRequest request);
	
	
	/**
	 * 
	 * <h2>根据部门Id查询部门信息</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return String 部门信息
	 */
	SectionInfo selectById(String sectionId);

	/**
	 * 
	 * <h2>根据部门Id获得部门信息</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 */
	void selectById(HttpServletRequest request);

	/**
	 * 
	 * <h2>根据部门Id修改部门信息</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 * @return SUCCESS 修改成功
	 */
	String update(HttpServletRequest request);

	
	/**
	 * 
	 * <h2>查询所有部门信息</h2>
	 * @author FSk
	 * @param request HttpServletRequest 请求对象
	 */
	void selectAll(HttpServletRequest request);



}
