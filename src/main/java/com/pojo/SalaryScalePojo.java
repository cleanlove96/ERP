package com.pojo;

import java.util.Date;

public class SalaryScalePojo {
	private String salaryScaleId;
	
	private String individualSalaryId;

	private String roleId;
	
	private String sectionName;
	
	private String accountName;
	
	private String accountId;
	
	private String accountNum;
	
	private String roleName;

	private Double salary;
	
	private Double adjustMoney;
	
	private Date changeTime;
	
	private Date adjustTime;

	public String getIndividualSalaryId() {
		return individualSalaryId;
	}

	public void setIndividualSalaryId(String individualSalaryId) {
		this.individualSalaryId = individualSalaryId;
	}

	public Date getAdjustTime() {
		return adjustTime;
	}

	public void setAdjustTime(Date adjustTime) {
		this.adjustTime = adjustTime;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public Double getAdjustMoney() {
		return adjustMoney;
	}

	public void setAdjustMoney(Double adjustMoney) {
		this.adjustMoney = adjustMoney;
	}

	public String getSalaryScaleId() {
		return salaryScaleId;
	}

	public void setSalaryScaleId(String salaryScaleId) {
		this.salaryScaleId = salaryScaleId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

}