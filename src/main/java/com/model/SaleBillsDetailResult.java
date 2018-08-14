package com.model;

public class SaleBillsDetailResult {

	private String commodityName;
	private String amount;
	private String price;
	private String total;
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public SaleBillsDetailResult() {
		super();
	}
	public SaleBillsDetailResult(String commodityName, String amount, String price, String total) {
		super();
		this.commodityName = commodityName;
		this.amount = amount;
		this.price = price;
		this.total = total;
	}
	@Override
	public String toString() {
		return "SaleBillsDetailResult [commodityName=" + commodityName + ", amount=" + amount + ", price=" + price
				+ ", total=" + total + "]";
	}
	
}
