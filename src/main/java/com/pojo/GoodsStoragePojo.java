package com.pojo;


public class GoodsStoragePojo {
	private String ropId;
	
	private String commodityName;
	
    private Integer ropUnit;

    private Integer ropLoss;
	
	private String batchNumber;
	
	private String business;
	
	private String commodityId;
	
	private String commodtyPrice;
	
	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public Integer getRopUnit() {
		return ropUnit;
	}

	public void setRopUnit(Integer ropUnit) {
		this.ropUnit = ropUnit;
	}

	public Integer getRopLoss() {
		return ropLoss;
	}

	public void setRopLoss(Integer ropLoss) {
		this.ropLoss = ropLoss;
	}

	public String getRopId() {
		return ropId;
	}

	public void setRopId(String ropId) {
		this.ropId = ropId;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodtyPrice() {
		return commodtyPrice;
	}

	public void setCommodtyPrice(String commodtyPrice) {
		this.commodtyPrice = commodtyPrice;
	}

}
