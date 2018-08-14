package com.mapper;

import java.util.List;
import java.util.Map;
import com.model.SystemCommodityInformation;

public interface SystemCommodityInformationMapper {
    int deleteByPrimaryKey(String commodityId);

    int insert(SystemCommodityInformation record);

    int insertSelective(SystemCommodityInformation record);

    SystemCommodityInformation selectByPrimaryKey(String commodityId);

    int updateByPrimaryKeySelective(SystemCommodityInformation record);

    int updateByPrimaryKey(SystemCommodityInformation record);

	int selectPageCount(String string);

	List<SystemCommodityInformation> selectCommodity(Map<String, Object> map);

	int addCommodity(Map<String, Object> map2);

	void updateStatus(Map<String, Object> map);

	int updateCommodity(Map<String, Object> map2);

	List<SystemCommodityInformation> selectCommodityByName(String searchName);


	/**
	 * 
	 * 
	 *<p>直接查询所有商品信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	List<SystemCommodityInformation> selectAllCommodity();

}