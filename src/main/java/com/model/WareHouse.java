package com.model;

public class WareHouse {
    private String warehouseId;

    private String warehouseName;

    private String warehouseDesc;

    private String warehouseStatus;
    
    private String createDate;
    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseDesc() {
        return warehouseDesc;
    }

    public void setWarehouseDesc(String warehouseDesc) {
        this.warehouseDesc = warehouseDesc;
    }

    public String getWarehouseStatus() {
        return warehouseStatus;
    }

    public void setWarehouseStatus(String warehouseStatus) {
        this.warehouseStatus = warehouseStatus;
    }

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}