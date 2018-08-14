package com.model;

public class materialLog {
    private String sysMaterialLogId;

    private String accountLoginId;

    private String materialLogDate;

    private String materialLogType;

    public String getSysMaterialLogId() {
        return sysMaterialLogId;
    }

    public void setSysMaterialLogId(String sysMaterialLogId) {
        this.sysMaterialLogId = sysMaterialLogId;
    }

    public String getAccountLoginId() {
        return accountLoginId;
    }

    public void setAccountLoginId(String accountLoginId) {
        this.accountLoginId = accountLoginId;
    }

    public String getMaterialLogDate() {
        return materialLogDate;
    }

    public void setMaterialLogDate(String materialLogDate) {
        this.materialLogDate = materialLogDate;
    }

    public String getMaterialLogType() {
        return materialLogType;
    }

    public void setMaterialLogType(String materialLogType) {
        this.materialLogType = materialLogType;
    }
}