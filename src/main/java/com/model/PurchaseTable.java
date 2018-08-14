package com.model;

public class PurchaseTable {
	/**
	 * 采购表id
	 */
    private String purchaseTableId;
    /**
     * 采购单编号
     */
    private String extendId;
    /**
     * 原料id
     */
    private String materialId;
    /**
     * 采购数量
     */
    private Integer purchaseTableInt;
    /**
     * 采购总金额
     */
    private Double purchaseTableMoney;
    /**
     * 采购时间
     */
    private String purchaseTableTime;
    /**
     * 采购年份
     */
    private String purchase;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 单价
     */
    private Double purchaseTablePrice;

    public String getPurchaseTableId() {
        return purchaseTableId;
    }

    public void setPurchaseTableId(String purchaseTableId) {
        this.purchaseTableId = purchaseTableId;
    }

    public String getExtendId() {
        return extendId;
    }

    public void setExtendId(String extendId) {
        this.extendId = extendId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public Integer getPurchaseTableInt() {
        return purchaseTableInt;
    }

    public void setPurchaseTableInt(Integer purchaseTableInt) {
        this.purchaseTableInt = purchaseTableInt;
    }

    public Double getPurchaseTableMoney() {
        return purchaseTableMoney;
    }

    public void setPurchaseTableMoney(Double purchaseTableMoney) {
        this.purchaseTableMoney = purchaseTableMoney;
    }

    public String getPurchaseTableTime() {
        return purchaseTableTime;
    }

    public void setPurchaseTableTime(String purchaseTableTime) {
        this.purchaseTableTime = purchaseTableTime;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Double getPurchaseTablePrice() {
        return purchaseTablePrice;
    }

    public void setPurchaseTablePrice(Double purchaseTablePrice) {
        this.purchaseTablePrice = purchaseTablePrice;
    }
}