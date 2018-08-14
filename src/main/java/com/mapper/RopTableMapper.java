package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.RopTable;
import com.pojo.Rop;
import com.pojo.materialOutPojo;

public interface RopTableMapper {
    int deleteByPrimaryKey(String ropId);

    int insert(RopTable record);

    int insertSelective(RopTable record);

    RopTable selectByPrimaryKey(String ropId);

    int updateByPrimaryKeySelective(RopTable record);

    int updateByPrimaryKey(RopTable record);

	/**
	 * @param search
	 * @return
	 */
	int getRopTableNum(String search);

	/**
	 * @param map
	 * @return
	 */
	List<Rop> selectLike(Map<String, Object> map);

	/**
	 * @param batchNumber
	 * @return
	 */
	int selectByBatchCount(String batchNumber);

	/**
	 * 根据业务类型查询数据量
	 * @param search
	 * @return
	 */
	int getRopTableNumByBus(Map<String, Object> map);
	/**
	 * 根据业务类型查询数据
	 * @param map
	 * @return
	 */
	List<Rop> selectLikeByBus(Map<String, Object> map);

	/**
	 * 查询商品需要出库的原料信息
	 * @param map
	 * @return
	 */
	List<materialOutPojo> getInfoTable(Map<String, Object> map);

	/**
	 * 查询商品需要出库的原料信息的数量
	 * @param map
	 * @return
	 */
	List<materialOutPojo> getTotalNum(String string);
}