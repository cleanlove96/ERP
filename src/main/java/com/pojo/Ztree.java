/*****************************************************
 *  HISTORY
 *  FileName:Ztree.java
 *  Package:com.pojo
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年6月26日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * 
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年6月26日
 */
public class Ztree {
	
	private String roleName;
	private String sectionName;
	private String accountName;
	@SerializedName("id")
	private String roleId;
	@SerializedName("pId")
	private String sectionId;
	private String accountId;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Ztree(String roleName, String sectionName, String accountName, String roleId, String sectionId,
			String accountId) {
		super();
		this.roleName = roleName;
		this.sectionName = sectionName;
		this.accountName = accountName;
		this.roleId = roleId;
		this.sectionId = sectionId;
		this.accountId = accountId;
	}

}
