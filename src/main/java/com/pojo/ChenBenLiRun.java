/*****************************************************
 *  HISTORY
 *  FileName:ChenBenLiRun.java
 *  Package:com.pojo
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年7月4日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.pojo;

/**
 * <p>
 * 
 * </p>	
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年7月4日
 */
public class ChenBenLiRun {
	//往来单位名称
	private String customerName;
	// 利润
	private double profit;
	// 成本
	private double capital;
	// 所有金额
	private double total;
	// 单价
	private double price;
    //单据Id
	private String saleBillId;
	// 数量
	private int sum;
	//时间
	private String saleTime;
	//经手人
	private String accountName;
	//商品名称
	private String commodityName;
	private String commodityId;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public double getCapital() {
		return capital;
	}
	public void setCapital(double capital) {
		this.capital = capital;
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
	public String getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
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
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	
	
}
