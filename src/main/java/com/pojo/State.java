package com.pojo;

import java.util.Date;

public class State {
	/**
	 * 用户ID
	 */
	private String accountId;
	/**
	 * 员工姓名
	 */
    private String accountName;
	/**
	 * 请假表id
	 */
	private String leaveInfoId;
	/**
	 * 请假原因
	 */
	private String leaveInfoReason;
	/**
	 * 请假类型
	 */
	private String leaveInfoType;
	/**
	 * 审核作态
	 */
	private String leaveInfoState;
	/**
	 * 请假起始时间
	 */
	private String leaveStartTime;
	/**
	 * 请假结束时间
	 */
	private String leaveEndTime;
	/**
	 * 创建时间
	 */
	private Date leaveCreateTime;
	/**
	 * 拒绝原因
	 */
	private String refuseReason;
	/**
	 * 用户读取状态（是否已读）
	 */
	private String leaveIsread;
	
	public State() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return "State [accountId=" + accountId + ", accountName=" + accountName + ", leaveInfoId=" + leaveInfoId
				+ ", leaveInfoReason=" + leaveInfoReason + ", leaveInfoType=" + leaveInfoType + ", leaveInfoState="
				+ leaveInfoState + ", leaveStartTime=" + leaveStartTime + ", leaveEndTime=" + leaveEndTime
				+ ", leaveCreateTime=" + leaveCreateTime + ", refuseReason=" + refuseReason + ", leaveIsread="
				+ leaveIsread + "]";
	}

	public State(String accountId, String accountName, String leaveInfoId, String leaveInfoReason, String leaveInfoType,
			String leaveInfoState, String leaveStartTime, String leaveEndTime, Date leaveCreateTime,
			String refuseReason, String leaveIsread) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.leaveInfoId = leaveInfoId;
		this.leaveInfoReason = leaveInfoReason;
		this.leaveInfoType = leaveInfoType;
		this.leaveInfoState = leaveInfoState;
		this.leaveStartTime = leaveStartTime;
		this.leaveEndTime = leaveEndTime;
		this.leaveCreateTime = leaveCreateTime;
		this.refuseReason = refuseReason;
		this.leaveIsread = leaveIsread;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getLeaveInfoId() {
		return leaveInfoId;
	}

	public void setLeaveInfoId(String leaveInfoId) {
		this.leaveInfoId = leaveInfoId;
	}

	public String getLeaveInfoReason() {
		return leaveInfoReason;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setLeaveInfoReason(String leaveInfoReason) {
		this.leaveInfoReason = leaveInfoReason;
	}

	public String getLeaveInfoType() {
		return leaveInfoType;
	}

	public void setLeaveInfoType(String leaveInfoType) {
		this.leaveInfoType = leaveInfoType;
	}

	public String getLeaveInfoState() {
		return leaveInfoState;
	}

	public void setLeaveInfoState(String leaveInfoState) {
		this.leaveInfoState = leaveInfoState;
	}

	public String getLeaveStartTime() {
		return leaveStartTime;
	}

	public void setLeaveStartTime(String leaveStartTime) {
		this.leaveStartTime = leaveStartTime;
	}

	public String getLeaveEndTime() {
		return leaveEndTime;
	}

	public void setLeaveEndTime(String leaveEndTime) {
		this.leaveEndTime = leaveEndTime;
	}

	public Date getLeaveCreateTime() {
		return leaveCreateTime;
	}

	public void setLeaveCreateTime(Date leaveCreateTime) {
		this.leaveCreateTime = leaveCreateTime;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getLeaveIsread() {
		return leaveIsread;
	}

	public void setLeaveIsread(String leaveIsread) {
		this.leaveIsread = leaveIsread;
	}


}
