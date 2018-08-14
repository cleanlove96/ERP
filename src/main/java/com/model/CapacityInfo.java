package com.model;

import java.util.Date;

public class CapacityInfo {
    private String capacityId;

    private String capacityProductionLineName;

    private String commodityName;

    private Integer capacityYield;

    private String capacityUnit;

    private Date capacityCreationTime;

    public String getCapacityId() {
        return capacityId;
    }

    public void setCapacityId(String capacityId) {
        this.capacityId = capacityId;
    }

    public String getCapacityProductionLineName() {
        return capacityProductionLineName;
    }

    public void setCapacityProductionLineName(String capacityProductionLineName) {
        this.capacityProductionLineName = capacityProductionLineName;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getCapacityYield() {
        return capacityYield;
    }

    public void setCapacityYield(Integer capacityYield) {
        this.capacityYield = capacityYield;
    }

    public String getCapacityUnit() {
        return capacityUnit;
    }

    public void setCapacityUnit(String capacityUnit) {
        this.capacityUnit = capacityUnit;
    }

    public Date getCapacityCreationTime() {
        return capacityCreationTime;
    }

    public void setCapacityCreationTime(Date capacityCreationTime) {
        this.capacityCreationTime = capacityCreationTime;
    }
}