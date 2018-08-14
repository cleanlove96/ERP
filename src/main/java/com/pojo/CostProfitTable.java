/*****************************************************
 *  HISTORY
 *  FileName:SaleSumTotal.java
 *  Package:com.pojo
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年6月29日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.pojo;

/**
 * <p>
 * 成本利润表的vo模型
 * 
 * </p>
 * 
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年6月29日
 * 
 */
public class CostProfitTable {
	private double saleSumTotal;
	 //销售金额
	private double cost;
	//成本
	private double profit;
	//利润
	public double getSaleSumTotal() {
		return saleSumTotal;
	}
	public void setSaleSumTotal(double saleSumTotal) {
		this.saleSumTotal = saleSumTotal;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	
	
}  
