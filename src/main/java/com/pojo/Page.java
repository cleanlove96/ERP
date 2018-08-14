/*****************************************************
 *  HISTORY
 *  FileName:Page.java
 *  Package:com.kkpagerajax.pojo
 *  Project:1803Test
 *  Version:1.0
 *  Date:2018年6月16日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.pojo;

/**
 * <p>
 * 
 * </p>	
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年6月16日
 */
public class Page {
	private String total;
	private String totalRecords;
	public Page() {
		super();
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}
	public Page(String total, String totalRecords) {
		super();
		this.total = total;
		this.totalRecords = totalRecords;
	}
}
