/*****************************************************
 *  HISTORY
 *  FileName:MoneyTable.java
 *  Package:com.pojo
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年7月2日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.pojo;

/**
 * <p>
 * 
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年7月2日
 */
public class MoneyTable {
	private String customerName;
	private String paidAmount;
	private String unpaidAmount;
	private String allMoney;
	private String completeTime;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getUnpaidAmount() {
		return unpaidAmount;
	}
	public void setUnpaidAmount(String unpaidAmount) {
		this.unpaidAmount = unpaidAmount;
	}
	public String getAllMoney() {
		return allMoney;
	}
	public void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}
	public String getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}
}
