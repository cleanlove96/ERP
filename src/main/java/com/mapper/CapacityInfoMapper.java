package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.CapacityInfo;
import com.pojo.CapacityInfor;

public interface CapacityInfoMapper {
    int deleteByPrimaryKey(String capacityId);

    int insert(CapacityInfo record);

    int insertSelective(CapacityInfo record);

    CapacityInfo selectByPrimaryKey(String capacityId);

    int updateByPrimaryKeySelective(CapacityInfo record);

    int updateByPrimaryKey(CapacityInfo record);

	/**
	 * @param capacityProductionLineName
	 * @return
	 */
	int selectByNameCount(String capacityProductionLineName);

	/**
	 * @param search
	 * @return
	 */
	int getCapacityNum(String search);

	/**
	 * @param map
	 * @return
	 */
	List<CapacityInfor> selectLike(Map<String, Object> map);


	/**
	 * @param map
	 * @return
	 */
	int selectByNameOrCount(Map<String, String> map);

	/**
	 * @return
	 */
	List<CapacityInfo> selectCapacityAll();
}