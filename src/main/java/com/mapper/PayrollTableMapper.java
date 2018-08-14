package com.mapper;

import java.util.Map;

import com.model.PayrollTable;

public interface PayrollTableMapper {
    int deleteByPrimaryKey(String payrollId);

    int insert(PayrollTable record);

    int insertSelective(PayrollTable record);

    PayrollTable selectByPrimaryKey(String payrollId);

    int updateByPrimaryKeySelective(PayrollTable record);

    int updateByPrimaryKey(PayrollTable record);

	int isLiquidation(Map<String, String> map);
}