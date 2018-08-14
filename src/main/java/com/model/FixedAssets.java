package com.model;

import java.util.Date;

public class FixedAssets {
    private String fixedAssetsId;

    private String fixedAssetsName;

    private String fixedAssetsNum;

    private Double fixedAssetsPrice;

    private Date fixedAssetsTime;

    public String getFixedAssetsId() {
        return fixedAssetsId;
    }

    public void setFixedAssetsId(String fixedAssetsId) {
        this.fixedAssetsId = fixedAssetsId;
    }

    public String getFixedAssetsName() {
        return fixedAssetsName;
    }

    public void setFixedAssetsName(String fixedAssetsName) {
        this.fixedAssetsName = fixedAssetsName;
    }

    public String getFixedAssetsNum() {
        return fixedAssetsNum;
    }

    public void setFixedAssetsNum(String fixedAssetsNum) {
        this.fixedAssetsNum = fixedAssetsNum;
    }

    public Double getFixedAssetsPrice() {
        return fixedAssetsPrice;
    }

    public void setFixedAssetsPrice(Double fixedAssetsPrice) {
        this.fixedAssetsPrice = fixedAssetsPrice;
    }

    public Date getFixedAssetsTime() {
        return fixedAssetsTime;
    }

    public void setFixedAssetsTime(Date fixedAssetsTime) {
        this.fixedAssetsTime = fixedAssetsTime;
    }
}