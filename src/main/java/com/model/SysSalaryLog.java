package com.model;

public class SysSalaryLog {
    private String salaryLogId;

    private String accountLoginId;

    private String salaryLogDate;

    private String salaryLogType;

    public String getSalaryLogId() {
        return salaryLogId;
    }

    public void setSalaryLogId(String salaryLogId) {
        this.salaryLogId = salaryLogId;
    }

    public String getAccountLoginId() {
        return accountLoginId;
    }

    public void setAccountLoginId(String accountLoginId) {
        this.accountLoginId = accountLoginId;
    }

    public String getSalaryLogDate() {
        return salaryLogDate;
    }

    public void setSalaryLogDate(String salaryLogDate) {
        this.salaryLogDate = salaryLogDate;
    }

    public String getSalaryLogType() {
        return salaryLogType;
    }

    public void setSalaryLogType(String salaryLogType) {
        this.salaryLogType = salaryLogType;
    }
}