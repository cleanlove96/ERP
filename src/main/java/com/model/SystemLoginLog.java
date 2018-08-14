package com.model;

public class SystemLoginLog {
    private String loginLogId;

    private String accountLoginId;

    private String loginLogDate;

    private String loginLogType;

    public String getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(String loginLogId) {
        this.loginLogId = loginLogId;
    }

    public String getAccountLoginId() {
        return accountLoginId;
    }

    public void setAccountLoginId(String accountLoginId) {
        this.accountLoginId = accountLoginId;
    }

    public String getLoginLogDate() {
        return loginLogDate;
    }

    public void setLoginLogDate(String loginLogDate) {
        this.loginLogDate = loginLogDate;
    }

    public String getLoginLogType() {
        return loginLogType;
    }

    public void setLoginLogType(String loginLogType) {
        this.loginLogType = loginLogType;
    }
}