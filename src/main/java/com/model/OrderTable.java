package com.model;

public class OrderTable {
    private String orderId;

    private String orderReceipts;

    private String customerId;

    private String accountId;

    private String commodityId;

    private Integer orderAmount;

    private Double orderPrice;

    private String orderStatus;

    private Double orderDeposit;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderReceipts() {
        return orderReceipts;
    }

    public void setOrderReceipts(String orderReceipts) {
        this.orderReceipts = orderReceipts;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderDeposit() {
        return orderDeposit;
    }

    public void setOrderDeposit(Double orderDeposit) {
        this.orderDeposit = orderDeposit;
    }
}