package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.MaterialInventory;
import com.pojo.MaterialInventoryPojo;

public interface MaterialInventoryMapper {
    int deleteByPrimaryKey(String materialInventoryId);

    int insert(MaterialInventory record);

    int insertSelective(MaterialInventory record);

    MaterialInventory selectByPrimaryKey(String materialInventoryId);

    int updateByPrimaryKeySelective(MaterialInventory record);

    int updateByPrimaryKey(MaterialInventory record);

	List<MaterialInventoryPojo> getInfoTable(Map<String, Object> map);

	int getTotalNum(String string);

	MaterialInventoryPojo selectById(String materialInventoryId);

	MaterialInventory selectByMaterialIdAmdWarehouseId(Map<String, Object> map);
}