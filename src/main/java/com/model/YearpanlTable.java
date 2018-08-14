package com.model;

public class YearpanlTable {
	/**
	 * 生产计划表ID
	 */
    private String yearpanlTableId;
    /**
	 * 商品ID
	 */
    private String commodityId;
    /**
   	 * 商品年份
   	 */
    private String yearNum;
    /**
   	 * 备注
   	 */
    private String yearpanlVariety;
    /**
   	 * 计划生产数量
   	 */
    private Integer goodsSum;
    /**
   	 * 计划开始时间
   	 */
    private String startTime;
    /**
   	 * 该表制作人
   	 */
    private String accountId;
    /**
     * 结束时间
     */
    private String endTime;

    public String getYearpanlTableId() {
        return yearpanlTableId;
    }

    public void setYearpanlTableId(String yearpanlTableId) {
        this.yearpanlTableId = yearpanlTableId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getYearNum() {
        return yearNum;
    }

    public void setYearNum(String yearNum) {
        this.yearNum = yearNum;
    }

    public String getYearpanlVariety() {
        return yearpanlVariety;
    }

    public void setYearpanlVariety(String yearpanlVariety) {
        this.yearpanlVariety = yearpanlVariety;
    }

    public Integer getGoodsSum() {
        return goodsSum;
    }

    public void setGoodsSum(Integer goodsSum) {
        this.goodsSum = goodsSum;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}