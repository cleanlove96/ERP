package com.model;

import java.util.Date;

public class OrderPerson {
    private String orderPersonId;

    private String orderId;

    private String accountId;

    private String purchaseId;

    private String ropId;

    private String business;

    private Date orderPersonTime;

    public String getOrderPersonId() {
        return orderPersonId;
    }

    public void setOrderPersonId(String orderPersonId) {
        this.orderPersonId = orderPersonId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getRopId() {
        return ropId;
    }

    public void setRopId(String ropId) {
        this.ropId = ropId;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public Date getOrderPersonTime() {
        return orderPersonTime;
    }

    public void setOrderPersonTime(Date orderPersonTime) {
        this.orderPersonTime = orderPersonTime;
    }
}