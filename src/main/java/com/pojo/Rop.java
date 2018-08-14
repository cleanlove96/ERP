package com.pojo;

import java.util.Date;

public class Rop {
    private String ropId;

    private String capacityId;
    
    private String capacityName;

    private String commodityId;
    
    private String commodityName;
    
    private Integer ropUnit;

    private Integer ropLoss;

    private Date ropProductionTime;

    private Date ropWarehouseEntryTime;

    private String ropIntoWarehouse;

    private String batchNumber;

    public String getRopId() {
        return ropId;
    }

    public void setRopId(String ropId) {
        this.ropId = ropId;
    }

    public String getCapacityId() {
        return capacityId;
    }

    public void setCapacityId(String capacityId) {
        this.capacityId = capacityId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
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

    public Date getRopProductionTime() {
        return ropProductionTime;
    }

    public void setRopProductionTime(Date ropProductionTime) {
        this.ropProductionTime = ropProductionTime;
    }

    public Date getRopWarehouseEntryTime() {
        return ropWarehouseEntryTime;
    }

    public void setRopWarehouseEntryTime(Date ropWarehouseEntryTime) {
        this.ropWarehouseEntryTime = ropWarehouseEntryTime;
    }

    public String getRopIntoWarehouse() {
        return ropIntoWarehouse;
    }

    public void setRopIntoWarehouse(String ropIntoWarehouse) {
        this.ropIntoWarehouse = ropIntoWarehouse;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

	public String getCapacityName() {
		return capacityName;
	}

	public void setCapacityName(String capacityName) {
		this.capacityName = capacityName;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	@Override
	public String toString() {
		return "RopTable [ropId=" + ropId + ", capacityId=" + capacityId + ", commodityId=" + commodityId + ", ropUnit="
				+ ropUnit + ", ropLoss=" + ropLoss + ", ropProductionTime=" + ropProductionTime
				+ ", ropWarehouseEntryTime=" + ropWarehouseEntryTime + ", ropIntoWarehouse=" + ropIntoWarehouse
				+ ", batchNumber=" + batchNumber + "]";
	}
    
}