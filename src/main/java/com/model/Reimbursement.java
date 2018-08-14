package com.model;

import java.util.Date;

public class Reimbursement {
	private String reimbursementId;
	// 费用项目
	private String costItem;
	// 费用说明
	private String reimbursementRespon;
	// 费用类别
	private String costType;
	// 费用金额
	private String costTotal;
	// 报销人
	private String bxPersonId;
	// 审核人
	private String costAuditorId;
	// 创建时间
	private Date costCreateTime;
	// 审核状态
	private String reimbursementState;
	// 拒绝原因
	private String reimbursementRefuse;
	//已/未读
	private String reimbursementIsread;

	public String getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(String reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public String getCostItem() {
		return costItem;
	}

	public void setCostItem(String costItem) {
		this.costItem = costItem;
	}

	public String getReimbursementRespon() {
		return reimbursementRespon;
	}

	public void setReimbursementRespon(String reimbursementRespon) {
		this.reimbursementRespon = reimbursementRespon;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getCostTotal() {
		return costTotal;
	}

	public void setCostTotal(String costTotal) {
		this.costTotal = costTotal;
	}

	public String getBxPersonId() {
		return bxPersonId;
	}

	public void setBxPersonId(String bxPersonId) {
		this.bxPersonId = bxPersonId;
	}

	public String getCostAuditorId() {
		return costAuditorId;
	}

	public void setCostAuditorId(String costAuditorId) {
		this.costAuditorId = costAuditorId;
	}

	public Date getCostCreateTime() {
		return costCreateTime;
	}

	public void setCostCreateTime(Date costCreateTime) {
		this.costCreateTime = costCreateTime;
	}

	public String getReimbursementState() {
		return reimbursementState;
	}

	public void setReimbursementState(String reimbursementState) {
		this.reimbursementState = reimbursementState;
	}

	public String getReimbursementRefuse() {
		return reimbursementRefuse;
	}

	public void setReimbursementRefuse(String reimbursementRefuse) {
		this.reimbursementRefuse = reimbursementRefuse;
	}

	public String getReimbursementIsread() {
		return reimbursementIsread;
	}

	public void setReimbursementIsread(String reimbursementIsread) {
		this.reimbursementIsread = reimbursementIsread;
	}
}