package com.model;

import java.util.Date;

public class CurrentAssets {
    private String allAssetsId;

    private Double fixedAssets;

    private Double currentAssets;

    private Date assetsTime;

    private String assetsDescribe;

    public String getAllAssetsId() {
        return allAssetsId;
    }

    public void setAllAssetsId(String allAssetsId) {
        this.allAssetsId = allAssetsId;
    }

    public Double getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(Double fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public Double getCurrentAssets() {
        return currentAssets;
    }

    public void setCurrentAssets(Double currentAssets) {
        this.currentAssets = currentAssets;
    }

    public Date getAssetsTime() {
        return assetsTime;
    }

    public void setAssetsTime(Date assetsTime) {
        this.assetsTime = assetsTime;
    }

    public String getAssetsDescribe() {
        return assetsDescribe;
    }

    public void setAssetsDescribe(String assetsDescribe) {
        this.assetsDescribe = assetsDescribe;
    }
}