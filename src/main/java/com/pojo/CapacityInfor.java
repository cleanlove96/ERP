/**
 * 
 */
package com.pojo;

import java.util.Date;

/**
 * @Author QinPeng
 *
 * @Date 2018年7月3日
 */
public class CapacityInfor {
	private String capacityId;

    private String capacityProductionLineName;

    private String commodityName;

    private String commodityType;
    
    private String odorType;
    
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

	public String getOdorType() {
		return odorType;
	}

	public void setOdorType(String odorType) {
		this.odorType = odorType;
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

	public String getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
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

	@Override
	public String toString() {
		return "CapacityInfor [capacityId=" + capacityId + ", capacityProductionLineName=" + capacityProductionLineName
				+ ", commodityName=" + commodityName + ", commodityType=" + commodityType + ", odorType=" + odorType
				+ ", capacityYield=" + capacityYield + ", capacityUnit=" + capacityUnit + ", capacityCreationTime="
				+ capacityCreationTime + "]";
	}
}
