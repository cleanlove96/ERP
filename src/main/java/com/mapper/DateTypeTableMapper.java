package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.DateTypeTable;

public interface DateTypeTableMapper {
    int deleteByPrimaryKey(String dateTypeId);

    int insert(DateTypeTable record);

    int insertSelective(DateTypeTable record);

    DateTypeTable selectByPrimaryKey(String dateTypeId);

    int updateByPrimaryKeySelective(DateTypeTable record);

    int updateByPrimaryKey(DateTypeTable record);

	int selectByType(String type);

	List<DateTypeTable> selectTableByType(Map<String, Object> map);

	DateTypeTable selectByDate(String dateTime);
}