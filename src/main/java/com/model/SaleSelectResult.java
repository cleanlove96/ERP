package com.model;

public class SaleSelectResult implements Comparable<SaleSelectResult>{

	private String customerName;
	private String commodityName;
	private String saleTime;
	private String sum;
	private String price;
	private String total;
	private String saleTotal;
	private String accountName;
	private String receiptId;
	public String getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
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
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
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
	public String getSaleTotal() {
		return saleTotal;
	}
	public void setSaleTotal(String saleTotal) {
		this.saleTotal = saleTotal;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public SaleSelectResult() {
		super();
	}
	public SaleSelectResult(String customerName, String commodityName, String saleTime, String sum, String price,
			String total, String saleTotal, String accountName, String receiptId) {
		super();
		this.customerName = customerName;
		this.commodityName = commodityName;
		this.saleTime = saleTime;
		this.sum = sum;
		this.price = price;
		this.total = total;
		this.saleTotal = saleTotal;
		this.accountName = accountName;
		this.receiptId = receiptId;
	}
	@Override
	public String toString() {
		return "SaleSelectResult [customerName=" + customerName + ", commodityName=" + commodityName + ", saleTime="
				+ saleTime + ", sum=" + sum + ", price=" + price + ", total=" + total + ", saleTotal=" + saleTotal
				+ ", accountName=" + accountName + ", receiptId=" + receiptId + "]";
	}
	@Override
	  public boolean equals(Object obj) {
	         if(obj instanceof SaleSelectResult) {
	        	 SaleSelectResult ssr = (SaleSelectResult) obj;
	               return (ssr.getReceiptId().equals(this.getReceiptId()));
	        }
	         return super.equals(obj);
	  }
	
	@Override
	public int compareTo(SaleSelectResult o) {
		if(this.getReceiptId().equals(o.getReceiptId())) {
			return 0;
		}
		return -1;
	}
	
}
