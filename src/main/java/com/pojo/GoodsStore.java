package com.pojo;

public class GoodsStore {
	
    private String goodsOutInId;

    private String warehouseId;

    private String commodityId;

    private Integer materialInventoryAmount;

    private Double materialInventoryValue;

    private String goodsType;

    private String batchNumber;

    private String time;
    
    private String commodityName;
    
    private String warehouseName;

	public String getGoodsOutInId() {
		return goodsOutInId;
	}

	public void setGoodsOutInId(String goodsOutInId) {
		this.goodsOutInId = goodsOutInId;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
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

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

}
