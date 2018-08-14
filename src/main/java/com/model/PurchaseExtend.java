package com.model;

import java.util.Date;

public class PurchaseExtend {
	/**
	 * 采购单编号
	 */
    private String extendId;
    /**
     * 客户id
     */
    private String customerId;
    /**
     * 经办人姓名
     */
    private String extendName;
    /**
     * 总金额
     */
    private String extendPrices;
    /**
     * 填表时间
     */
    private Date extendTime;
    /**
     * 经办人联系方式
     */
    private String extendPhone;

    public String getExtendId() {
        return extendId;
    }

    public void setExtendId(String extendId) {
        this.extendId = extendId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getExtendName() {
        return extendName;
    }

    public void setExtendName(String extendName) {
        this.extendName = extendName;
    }

    public String getExtendPrices() {
        return extendPrices;
    }

    public void setExtendPrices(String extendPrices) {
        this.extendPrices = extendPrices;
    }

    public Date getExtendTime() {
        return extendTime;
    }

    public void setExtendTime(Date extendTime) {
        this.extendTime = extendTime;
    }

    public String getExtendPhone() {
        return extendPhone;
    }

    public void setExtendPhone(String extendPhone) {
        this.extendPhone = extendPhone;
    }
}