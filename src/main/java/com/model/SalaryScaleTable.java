package com.model;

import java.util.Date;

public class SalaryScaleTable {
    private String salaryScaleId;

    private String roleId;

    private Double salary;

    private Date changeTime;

    public String getSalaryScaleId() {
        return salaryScaleId;
    }

    public void setSalaryScaleId(String salaryScaleId) {
        this.salaryScaleId = salaryScaleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }
}