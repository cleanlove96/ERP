package com.model;


public class OrderTableSelectResult implements Comparable<OrderTableSelectResult>{

	private String orderReceipts;
	private String customerName;
	private String accountName;
	private String orderPrice;
	private String orderStatus;
	public String getOrderReceipts() {
		return orderReceipts;
	}
	public void setOrderReceipts(String orderReceipts) {
		this.orderReceipts = orderReceipts;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public OrderTableSelectResult() {
		super();
	}
	public OrderTableSelectResult(String orderReceipts, String customerName, String accountName, String orderPrice,
			String orderStatus) {
		super();
		this.orderReceipts = orderReceipts;
		this.customerName = customerName;
		this.accountName = accountName;
		this.orderPrice = orderPrice;
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "OrderTableSelectResult [orderReceipts=" + orderReceipts + ", customerName=" + customerName
				+ ", accountName=" + accountName + ", orderPrice=" + orderPrice + ", orderStatus=" + orderStatus + "]";
	}
	
	@Override
	public int hashCode()
	{
	    return orderReceipts.hashCode();
	}
	
	@Override
	  public boolean equals(Object obj) {
	         if(obj instanceof OrderTableSelectResult) {
	        	 OrderTableSelectResult otsr = (OrderTableSelectResult) obj;
	               return (otsr.getOrderReceipts().equals(this.getOrderReceipts()));
	        }
	         return super.equals(obj);
	  }
	
	@Override
	public int compareTo(OrderTableSelectResult o) {
		if(this.getOrderReceipts().equals(o.getOrderReceipts())) {
			return 0;
		}
		return -1;
	}
}
