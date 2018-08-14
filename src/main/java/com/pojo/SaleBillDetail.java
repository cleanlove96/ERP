/*****************************************************
 *  HISTORY
 *  FileName:SaleBillDetail.java
 *  Package:com.pojo
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年7月1日 zm创建文件
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
 * @Date:2018年7月1日
 */
public class SaleBillDetail {
	private String saleBillId;
	private String accountName;
	private String customerName;
	private String commodityName;
	private String saleTime;
	private int sum;
	private double price;
	private double total;
	public String getSaleBillId() {
		return saleBillId;
	}
	public void setSaleBillId(String saleBillId) {
		this.saleBillId = saleBillId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
