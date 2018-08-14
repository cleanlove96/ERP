package com.model;

public class Gcount {
    private String gcountId;

    private String commodityId;

    private String warehouseId;

    private Integer gcountAmount;

    private Double gcountMoney;

    public String getGcountId() {
        return gcountId;
    }

    public void setGcountId(String gcountId) {
        this.gcountId = gcountId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getGcountAmount() {
        return gcountAmount;
    }

    public void setGcountAmount(Integer gcountAmount) {
        this.gcountAmount = gcountAmount;
    }

    public Double getGcountMoney() {
        return gcountMoney;
    }

    public void setGcountMoney(Double gcountMoney) {
        this.gcountMoney = gcountMoney;
    }
}