package com.mapper;

import com.model.WareHouseLog;

public interface WareHouseLogMapper {
    int deleteByPrimaryKey(String warehouseLogId);

    int insert(WareHouseLog record);

    int insertSelective(WareHouseLog record);

    WareHouseLog selectByPrimaryKey(String warehouseLogId);

    int updateByPrimaryKeySelective(WareHouseLog record);

    int updateByPrimaryKey(WareHouseLog record);
}