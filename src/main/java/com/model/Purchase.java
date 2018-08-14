package com.model;

import java.util.Date;

public class Purchase {
	/**
	 * 采购计划ID
	 */
    private String purchaseId;
    /**
     * 原料ID
     */
    private String materialId;
    /**
     * 数量
     */
    private Integer purchaseCount;
    /**
     * 金额
     */
    private Double purchaseTotalPrices;
    /**
     * 状态
     */
    private String purchaseStatus;
    /**
     * 单价
     */
    private Double price;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 单位
     */
    private String purchaseUnit;
    /**
     * 年份
     */
    private String purchaseTime;

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public Integer getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public Double getPurchaseTotalPrices() {
        return purchaseTotalPrices;
    }

    public void setPurchaseTotalPrices(Double purchaseTotalPrices) {
        this.purchaseTotalPrices = purchaseTotalPrices;
    }

    public String getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(String purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getPurchaseUnit() {
        return purchaseUnit;
    }

    public void setPurchaseUnit(String purchaseUnit) {
        this.purchaseUnit = purchaseUnit;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

	@Override
	public String toString() {
		return "Purchase [purchaseId=" + purchaseId + ", materialId=" + materialId + ", purchaseCount=" + purchaseCount
				+ ", purchaseTotalPrices=" + purchaseTotalPrices + ", purchaseStatus=" + purchaseStatus + ", price="
				+ price + ", endTime=" + endTime + ", startTime=" + startTime + ", purchaseUnit=" + purchaseUnit
				+ ", purchaseTime=" + purchaseTime + "]";
	}
    
}