package com.mapper;

import com.model.CurrentAssets;

public interface CurrentAssetsMapper {
    int deleteByPrimaryKey(String allAssetsId);

    int insert(CurrentAssets record);

    int insertSelective(CurrentAssets record);

    CurrentAssets selectByPrimaryKey(String allAssetsId);

    int updateByPrimaryKeySelective(CurrentAssets record);

    int updateByPrimaryKey(CurrentAssets record);
}