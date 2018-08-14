/*****************************************************
 *  HISTORY
 *  FileName:SectionRoleService.java
 *  Package:com.service
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年6月26日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 部门角色的实现层
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年6月26日
 */
public interface SectionRoleService {
	String  getAllNodeByJson();
	String  updatePidById(HttpServletRequest request);
	String  queryAllAccountinfo(HttpServletRequest request);
}
