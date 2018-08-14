package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Gcount;
import com.pojo.GcountPojo;

public interface GcountMapper {
    int deleteByPrimaryKey(String gcountId);

    int insert(Gcount record);

    int insertSelective(Gcount record);

    Gcount selectByPrimaryKey(String gcountId);

    int updateByPrimaryKeySelective(Gcount record);

    int updateByPrimaryKey(Gcount record);

	List<GcountPojo> getInfoTable(Map<String, Object> map);

	int getTotalNum(String string);

	GcountPojo selectById(String gcountId);

	Gcount selectByCommodityIdAndWarehouseId(Map<String, Object> map);
}