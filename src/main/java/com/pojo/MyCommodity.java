package com.pojo;

/**
 * 
 *<h2>自定义类</h2>
 *<p>装生产总计划时用到的商品和数量</p>
 * 
 *@author lily
 *
 *
 */
public class MyCommodity {
	/**
	 * 商品哦id
	 */
	private String commodityId;
	/**
	 * 商品计划量
	 */
	private Integer goodsSum;
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public Integer getGoodsSum() {
		return goodsSum;
	}
	public void setGoodsSum(Integer goodsSum) {
		this.goodsSum = goodsSum;
	}
	
	
}
