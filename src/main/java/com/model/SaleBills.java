package com.model;

public class SaleBills {
    private String saleBillId;

    private String customerId;

    private String saleTime;

    private String accountId;

    private String commodityId;

    private Integer sum;

    private Double price;

    private Double total;

    private Double saleTotal;
    
    private String receiptId;

    public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public String getSaleBillId() {
        return saleBillId;
    }

    public void setSaleBillId(String saleBillId) {
        this.saleBillId = saleBillId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(String saleTime) {
        this.saleTime = saleTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(Double saleTotal) {
        this.saleTotal = saleTotal;
    }
}