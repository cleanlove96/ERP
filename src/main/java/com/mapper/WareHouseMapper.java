package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.WareHouse;

public interface WareHouseMapper {
	/**
     * 按id删除信息
     * @param search
     * @return int
     */
    int deleteByPrimaryKey(String warehouseId);
    /**
     * 信息插入
     * @param search
     * @return int
     */
    int insert(WareHouse record);
    /**
     * 信息插入
     * @param search
     * @return int
     */
    int insertSelective(WareHouse record);
    /**
     * 按id搜索信息
     * @param search
     * @return WareHouse
     */
    WareHouse selectByPrimaryKey(String warehouseId);
    /**
     * 按id修改信息
     * @param search
     * @return int
     */
    int updateByPrimaryKeySelective(WareHouse record);
    /**
     * 按id修改信息
     * @param search
     * @return int
     */
    int updateByPrimaryKey(WareHouse record);
    /**
     * 模糊查询计数，满足条件：状态为1，关键字模糊
     * @param search
     * @return int
     */
	int getWareHouseNum(String search);
	 /**
     * 模糊查询，满足条件：状态为1，关键字模糊
     * @param search
     * @return List<WareHouse>
     */
	List<WareHouse> selectLike(Map<String, Object> map);
	
	/**
     * 按名字 查询，满足条件：状态为1，关键字
     * @param search
     * @return int 
     */
	int selectByNameCount(String warehouseName);
	
	/**
	 * 
	 * <p>
	 * 查询所有非停用的商品仓库信息
	 * </p>
	 * @author FSK
	 * @Date 2018年7月2日
	 * @return
	 */
	List<WareHouse> selectAll();
	
	/**
	 * @param map
	 * @return
	 */
	int selectByNameOrIDCount(Map<String, String> map);
	
	/**
	 * 
	 * <p>
	 * 查询所有非停用的原料仓库信息
	 * </p>
	 * @author FSK
	 * @Date 2018年7月2日
	 * @return
	 */
	List<WareHouse> selectByMaterial();


}