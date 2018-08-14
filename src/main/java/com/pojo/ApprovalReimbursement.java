package com.pojo;

import java.util.Date;

public class ApprovalReimbursement {

	//报销人姓名
	private String accountName;
	//报销单ID
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
	//审核人姓名
	private String accountName1;
	// 创建时间
	private Date costCreateTime;
	// 审核状态
	private String reimbursementState;
	// 拒绝原因
	private String reimbursementRefuse;
	//已/未读
	private String reimbursementIsread;
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
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
	public ApprovalReimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getAccountName1() {
		return accountName1;
	}
	public void setAccountName1(String accountName1) {
		this.accountName1 = accountName1;
	}
	@Override
	public String toString() {
		return "ApprovalReimbursement [accountName=" + accountName + ", reimbursementId=" + reimbursementId
				+ ", costItem=" + costItem + ", reimbursementRespon=" + reimbursementRespon + ", costType=" + costType
				+ ", costTotal=" + costTotal + ", bxPersonId=" + bxPersonId + ", costAuditorId=" + costAuditorId
				+ ", accountName1=" + accountName1 + ", costCreateTime=" + costCreateTime + ", reimbursementState="
				+ reimbursementState + ", reimbursementRefuse=" + reimbursementRefuse + ", reimbursementIsread="
				+ reimbursementIsread + "]";
	}
	public ApprovalReimbursement(String accountName, String reimbursementId, String costItem,
			String reimbursementRespon, String costType, String costTotal, String bxPersonId, String costAuditorId,
			String accountName1, Date costCreateTime, String reimbursementState, String reimbursementRefuse,
			String reimbursementIsread) {
		super();
		this.accountName = accountName;
		this.reimbursementId = reimbursementId;
		this.costItem = costItem;
		this.reimbursementRespon = reimbursementRespon;
		this.costType = costType;
		this.costTotal = costTotal;
		this.bxPersonId = bxPersonId;
		this.costAuditorId = costAuditorId;
		this.accountName1 = accountName1;
		this.costCreateTime = costCreateTime;
		this.reimbursementState = reimbursementState;
		this.reimbursementRefuse = reimbursementRefuse;
		this.reimbursementIsread = reimbursementIsread;
	}
	
}
