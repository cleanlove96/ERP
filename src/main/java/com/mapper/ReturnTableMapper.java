package com.mapper;

import com.model.ReturnTable;

public interface ReturnTableMapper {
    int deleteByPrimaryKey(String returnId);

    int insert(ReturnTable record);

    int insertSelective(ReturnTable record);

    ReturnTable selectByPrimaryKey(String returnId);

    int updateByPrimaryKeySelective(ReturnTable record);

    int updateByPrimaryKey(ReturnTable record);
}