package com.model;

public class WareHouseLog {
    private String warehouseLogId;

    private String accountLoginId;

    private String opDate;

    private String opType;

    public String getWarehouseLogId() {
        return warehouseLogId;
    }

    public void setWarehouseLogId(String warehouseLogId) {
        this.warehouseLogId = warehouseLogId;
    }

    public String getAccountLoginId() {
        return accountLoginId;
    }

    public void setAccountLoginId(String accountLoginId) {
        this.accountLoginId = accountLoginId;
    }

    public String getOpDate() {
        return opDate;
    }

    public void setOpDate(String opDate) {
        this.opDate = opDate;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }
}