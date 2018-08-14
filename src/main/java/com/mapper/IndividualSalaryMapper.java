package com.mapper;

import com.model.IndividualSalary;
import com.pojo.BaseSalaryPojo;

public interface IndividualSalaryMapper {
    int deleteByPrimaryKey(String individualSalaryId);

    int insert(IndividualSalary record);

    int insertSelective(IndividualSalary record);

    IndividualSalary selectByPrimaryKey(String individualSalaryId);

    int updateByPrimaryKeySelective(IndividualSalary record);

    int updateByPrimaryKey(IndividualSalary record);

	BaseSalaryPojo selectBaseMonerAndOtherMoney(String id);
}