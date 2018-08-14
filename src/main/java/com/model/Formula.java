package com.model;

import java.util.Date;

public class Formula {
	/**
	 * 物料清单id
	 */
    private String formulaId;
    /**
     * 商品id
     */
    private String commodityId;
    /**
     * 原料id
     */
    private String materialId;
    /**
     * 数量
     */
    private Integer formulaCount;
    /**
     * 创建时间
     */
    private Date formulaCreateTime;

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public Integer getFormulaCount() {
        return formulaCount;
    }

    public void setFormulaCount(Integer formulaCount) {
        this.formulaCount = formulaCount;
    }

    public Date getFormulaCreateTime() {
        return formulaCreateTime;
    }

    public void setFormulaCreateTime(Date formulaCreateTime) {
        this.formulaCreateTime = formulaCreateTime;
    }
}