package com.model;

import java.util.Date;

public class WorkdayRecord {
    private String workdayRecordId;

    private String accountId;

    private Date punchTime;

    private String workdayType;

    public String getWorkdayRecordId() {
        return workdayRecordId;
    }

    public void setWorkdayRecordId(String workdayRecordId) {
        this.workdayRecordId = workdayRecordId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Date getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(Date punchTime) {
        this.punchTime = punchTime;
    }

    public String getWorkdayType() {
        return workdayType;
    }

    public void setWorkdayType(String workdayType) {
        this.workdayType = workdayType;
    }
}