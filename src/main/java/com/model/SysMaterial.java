package com.model;

public class SysMaterial {
	/**
	 * 原料id
	 */
    private String materialId;
    /**
     * 原料名
     */
    private String materialName;
    /**
     * 原料单位
     */
    private String materialUnit;
    /**
     * 原料单价
     */
    private Double price;
    /**
     * 原料状态
     */
    private String materialStatus;
    /**
     * 创建时间
     */
    private String materialCreateTime;
    /**
     * 上次修改时间
     */
    private String materialUpdateTime;
    /**
     * 客户id
     */
    private String customerId;

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
    }

    public String getMaterialCreateTime() {
        return materialCreateTime;
    }

    public void setMaterialCreateTime(String materialCreateTime) {
        this.materialCreateTime = materialCreateTime;
    }

    public String getMaterialUpdateTime() {
        return materialUpdateTime;
    }

    public void setMaterialUpdateTime(String materialUpdateTime) {
        this.materialUpdateTime = materialUpdateTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}