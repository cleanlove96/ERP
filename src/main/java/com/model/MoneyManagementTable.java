package com.model;

import java.util.Date;

public class MoneyManagementTable {
    private String moneyManId;

    private String customerId;

    private String orderId;

    private String purchaseTableId;

    private Double allMoney;

    private Double paidAmount;

    private Double unpaidAmount;

    private Date completeTime;

    public String getMoneyManId() {
        return moneyManId;
    }

    public void setMoneyManId(String moneyManId) {
        this.moneyManId = moneyManId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPurchaseTableId() {
        return purchaseTableId;
    }

    public void setPurchaseTableId(String purchaseTableId) {
        this.purchaseTableId = purchaseTableId;
    }

    public Double getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(Double allMoney) {
        this.allMoney = allMoney;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getUnpaidAmount() {
        return unpaidAmount;
    }

    public void setUnpaidAmount(Double unpaidAmount) {
        this.unpaidAmount = unpaidAmount;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }
}