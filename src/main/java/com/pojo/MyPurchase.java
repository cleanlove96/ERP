package com.pojo;

/**
 * 
 *<h2>采购总计划表需要显示的东西</h2>
 *
 *@author lily
 *
 *
 */
public class MyPurchase {
	/**
	 * 原料id
	 */
	private String materialId;
	/**
	 * 原料名
	 */
	private String materialName;
	/**
	 * 原料数量
	 */
	private Integer materialNum;
	/**
	 * 原料单位
	 */
	private String materialUnit;
	/**
	 * 原料单价
	 */
	private double materialPrice;
	/**
	 * 原料总金额
	 */
	private double materialTotalPrices;
	/**
	 * 原料未计划量
	 */
	private Integer materialNotPlan;
	/**
	 * 原料未采购量
	 */
	private Integer materialNotBuy;
	/**
	 * 客户id
	 */
	private String customerId;
	/**
	 * 客户名
	 */
	private String customerName;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public Integer getMaterialNum() {
		return materialNum;
	}
	public void setMaterialNum(Integer materialNum) {
		this.materialNum = materialNum;
	}
	public String getMaterialUnit() {
		return materialUnit;
	}
	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}
	public double getMaterialPrice() {
		return materialPrice;
	}
	public void setMaterialPrice(double materialPrice) {
		this.materialPrice = materialPrice;
	}
	public double getMaterialTotalPrices() {
		return materialTotalPrices;
	}
	public void setMaterialTotalPrices(double materialTotalPrices) {
		this.materialTotalPrices = materialTotalPrices;
	}
	public Integer getMaterialNotPlan() {
		return materialNotPlan;
	}
	public void setMaterialNotPlan(Integer materialNotPlan) {
		this.materialNotPlan = materialNotPlan;
	}
	public Integer getMaterialNotBuy() {
		return materialNotBuy;
	}
	public void setMaterialNotBuy(Integer materialNotBuy) {
		this.materialNotBuy = materialNotBuy;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
}
