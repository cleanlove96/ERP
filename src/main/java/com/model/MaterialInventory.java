package com.model;

public class MaterialInventory {
    private String materialInventoryId;

    private String materialId;

    private String warehouseId;

    private Integer materialInventoryAmount;

    private Double materialInventoryValue;

    public String getMaterialInventoryId() {
        return materialInventoryId;
    }

    public void setMaterialInventoryId(String materialInventoryId) {
        this.materialInventoryId = materialInventoryId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
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
}