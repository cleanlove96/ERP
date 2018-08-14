package com.pojo;
/**
 * 
 *<h2>自定义类</h2>
 *<p>为了装原料id和数量</p>
 * 
 *@author lily
 *
 *
 */
public class myFormula {
 
	/**
	 * 原料id
	 */
	private String materialId;
	/**
	 * 原料数量
	 */
	private Integer materialNum;
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public Integer getMaterialNum() {
		return materialNum;
	}
	public void setMaterialNum(Integer materialNum) {
		this.materialNum = materialNum;
	}
}
