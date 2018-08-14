package com.pojo;

public class AccountPage {
	/**
	 * 员工显示的总页码数
	 */
	private String total;
	/**
	 * 员工显示的总条数
	 */
	private String totalRecords;

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

}
