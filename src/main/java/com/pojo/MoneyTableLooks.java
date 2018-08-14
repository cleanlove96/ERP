/*****************************************************
 *  HISTORY
 *  FileName:MoneyTableLooks.java
 *  Package:com.pojo
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年7月3日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.pojo;

/**
 * <p>
 * 往来单位账目详情表，未完待续
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年7月3日
 */
public class MoneyTableLooks {
	// 往来单位名称
	private String customerName;
	// 已付金额
	private String paidAmount;
	// 未付金额
	private String unpaidAmount;
	// 所有金额
	private double total;
	// 时间
	private double price;
	// 数量
	private String saleBillId;
	private int sum;
	private String completeTime;
	private String accountName;
	private String commodityName;
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
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSaleBillId() {
		return saleBillId;
	}
	public void setSaleBillId(String saleBillId) {
		this.saleBillId = saleBillId;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public String getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	
}
