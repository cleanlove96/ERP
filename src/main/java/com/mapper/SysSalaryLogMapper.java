package com.mapper;

import com.model.SysSalaryLog;

public interface SysSalaryLogMapper {
    int deleteByPrimaryKey(String salaryLogId);

    int insert(SysSalaryLog record);

    int insertSelective(SysSalaryLog record);

    SysSalaryLog selectByPrimaryKey(String salaryLogId);

    int updateByPrimaryKeySelective(SysSalaryLog record);

    int updateByPrimaryKey(SysSalaryLog record);
}