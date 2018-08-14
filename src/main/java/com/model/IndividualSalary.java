package com.model;

import java.util.Date;

public class IndividualSalary {
    private String individualSalaryId;

    private String accountId;

    private Double adjustMoney;

    private Date adjustTime;

    public String getIndividualSalaryId() {
        return individualSalaryId;
    }

    public void setIndividualSalaryId(String individualSalaryId) {
        this.individualSalaryId = individualSalaryId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Double getAdjustMoney() {
        return adjustMoney;
    }

    public void setAdjustMoney(Double adjustMoney) {
        this.adjustMoney = adjustMoney;
    }

    public Date getAdjustTime() {
        return adjustTime;
    }

    public void setAdjustTime(Date adjustTime) {
        this.adjustTime = adjustTime;
    }
}