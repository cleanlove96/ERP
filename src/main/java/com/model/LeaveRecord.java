package com.model;

import java.util.Date;

public class LeaveRecord {
    private String leaveRecordId;

    private String accountId;

    private Date leaveTime;

    private Double leaveDays;

    public String getLeaveRecordId() {
        return leaveRecordId;
    }

    public void setLeaveRecordId(String leaveRecordId) {
        this.leaveRecordId = leaveRecordId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Double getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(Double leaveDays) {
        this.leaveDays = leaveDays;
    }
}