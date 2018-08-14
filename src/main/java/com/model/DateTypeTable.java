package com.model;

import java.util.Date;

public class DateTypeTable {
    private String dateTypeId;

    private String dateTypeName;

    private String dateTypeDate;

    private Date changeTime;

    private String dateTypeType;

    @Override
	public String toString() {
		return "DateTypeTable [dateTypeId=" + dateTypeId + ", dateTypeName=" + dateTypeName + ", dateTypeDate="
				+ dateTypeDate + ", changeTime=" + changeTime + ", dateTypeType=" + dateTypeType + "]";
	}

	public String getDateTypeId() {
        return dateTypeId;
    }

    public void setDateTypeId(String dateTypeId) {
        this.dateTypeId = dateTypeId;
    }

    public String getDateTypeName() {
        return dateTypeName;
    }

    public void setDateTypeName(String dateTypeName) {
        this.dateTypeName = dateTypeName;
    }

    public String getDateTypeDate() {
        return dateTypeDate;
    }

    public void setDateTypeDate(String dateTypeDate) {
        this.dateTypeDate = dateTypeDate;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getDateTypeType() {
        return dateTypeType;
    }

    public void setDateTypeType(String dateTypeType) {
        this.dateTypeType = dateTypeType;
    }
}