package com.pojo;

import java.util.Date;

public class FormulaTable {
    private String formulaId;

    private String commodityId;
    
    private String commodityName;
    
    private String materialId;
    
    private String materialName;

	private Integer formulaCount;

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
    public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
}