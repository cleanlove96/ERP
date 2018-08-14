package com.pojo;

import java.util.List;

public class FormulaList {
    

    private String commodityId;
    
    private String commodityName;
    
    private List<FormulaTable> list;

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public List<FormulaTable> getList() {
		return list;
	}

	public void setList(List<FormulaTable> list) {
		this.list = list;
	}
}