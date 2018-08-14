package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.SalaryScaleTable;
import com.pojo.SalaryScalePojo;

public interface SalaryScaleTableMapper {
    int deleteByPrimaryKey(String salaryScaleId);

    int insert(SalaryScaleTable record);

    int insertSelective(SalaryScaleTable record);

    SalaryScaleTable selectByPrimaryKey(String salaryScaleId);

    int updateByPrimaryKeySelective(SalaryScaleTable record);

    int updateByPrimaryKey(SalaryScaleTable record);

	List<SalaryScalePojo> getMinistryTable(Map<String, Object> map);

	int getRoleNum();

	SalaryScalePojo selectByAccountId(String id);

}