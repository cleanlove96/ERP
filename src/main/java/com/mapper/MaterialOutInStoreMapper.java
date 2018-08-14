package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.MaterialOutInStore;
import com.pojo.MaterialStore;

public interface MaterialOutInStoreMapper {
    int deleteByPrimaryKey(String materialOutInId);

    int insert(MaterialOutInStore record);

    int insertSelective(MaterialOutInStore record);

    MaterialOutInStore selectByPrimaryKey(String materialOutInId);

    int updateByPrimaryKeySelective(MaterialOutInStore record);

    int updateByPrimaryKey(MaterialOutInStore record);

	List<MaterialStore> getInfoTable(Map<String, Object> map);

	int getTotalNum(Map<String, Object> map);
}