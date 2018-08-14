package com.model;

import java.util.Date;

public class LeaveInfo {
    private String leaveInfoId;

    private String accountId;

    private String leaveInfoReason;

    private String leaveInfoType;

    private String leaveInfoState;

    private String leaveStartTime;

    private String leaveEndTime;

    private Date leaveCreateTime;

    private String refuseReason;

    private String leaveIsread;

    public String getLeaveInfoId() {
        return leaveInfoId;
    }

    public void setLeaveInfoId(String leaveInfoId) {
        this.leaveInfoId = leaveInfoId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getLeaveInfoReason() {
        return leaveInfoReason;
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