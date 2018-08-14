package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.FixedAssets;

public interface FixedAssetsMapper {
    int deleteByPrimaryKey(String fixedAssetsId);

    int insert(FixedAssets record);

    int insertSelective(FixedAssets record);

    FixedAssets selectByPrimaryKey(String fixedAssetsId);

    int updateByPrimaryKeySelective(FixedAssets record);

    int updateByPrimaryKey(FixedAssets record);

	int getTotalNum(Map<String, String> map);

	List<FixedAssets> getFixedAssetsTable(Map<String, Object> map);

	FixedAssets selectFixedAssets();
}