package com.model;

public class MaterialOutInStore {
    private String materialOutInId;

    private String warehouseId;

    private String materialId;

    private Integer materialInventoryAmount;

    private Double materialInventoryValue;

    private String goodsType;

    private String time;

    public String getMaterialOutInId() {
        return materialOutInId;
    }

    public void setMaterialOutInId(String materialOutInId) {
        this.materialOutInId = materialOutInId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public Integer getMaterialInventoryAmount() {
        return materialInventoryAmount;
    }

    public void setMaterialInventoryAmount(Integer materialInventoryAmount) {
        this.materialInventoryAmount = materialInventoryAmount;
    }

    public Double getMaterialInventoryValue() {
        return materialInventoryValue;
    }

    public void setMaterialInventoryValue(Double materialInventoryValue) {
        this.materialInventoryValue = materialInventoryValue;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}