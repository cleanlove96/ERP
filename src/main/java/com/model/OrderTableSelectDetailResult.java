package com.model;

public class OrderTableSelectDetailResult {
	
	private String commodityName;
	private String orderAmount;
	private String commodityPrice;
	private String commodityTotalPrice;
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getCommodityPrice() {
		return commodityPrice;
	}
	public void setCommodityPrice(String commodityPrice) {
		this.commodityPrice = commodityPrice;
	}
	public String getCommodityTotalPrice() {
		return commodityTotalPrice;
	}
	public void setCommodityTotalPrice(String commodityTotalPrice) {
		this.commodityTotalPrice = commodityTotalPrice;
	}
	public OrderTableSelectDetailResult(String commodityName, String orderAmount, String commodityPrice,
			String commodityTotalPrice) {
		super();
		this.commodityName = commodityName;
		this.orderAmount = orderAmount;
		this.commodityPrice = commodityPrice;
		this.commodityTotalPrice = commodityTotalPrice;
	}
	public OrderTableSelectDetailResult() {
		super();
	}
	@Override
	public String toString() {
		return "OrderTableSelectDetailResult [commodityName=" + commodityName + ", orderAmount=" + orderAmount
				+ ", commodityPrice=" + commodityPrice + ", commodityTotalPrice=" + commodityTotalPrice + "]";
	}
	
}