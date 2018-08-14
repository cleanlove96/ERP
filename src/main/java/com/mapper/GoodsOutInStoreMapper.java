package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.GoodsOutInStore;
import com.pojo.GoodsStore;


public interface GoodsOutInStoreMapper {
    int deleteByPrimaryKey(String goodsOutInId);

    int insert(GoodsOutInStore record);

    int insertSelective(GoodsOutInStore record);

    GoodsOutInStore selectByPrimaryKey(String goodsOutInId);

    int updateByPrimaryKeySelective(GoodsOutInStore record);

    int updateByPrimaryKey(GoodsOutInStore record);


	List<GoodsStore> getInfoTable(Map<String, Object> map);

	int getTotalNum(Map<String, Object> map);


	/**
	 * <p>
	 *  查询成品出库的的总金额，其实就是总的销售金额
	 * </p>
	 * @author zm
	 * @Date 2018年6月30日
	 * @return
	 */
	double querySaleSumTotal();

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年6月30日
	 * @return
	 */
	double queryCostTotal();

}