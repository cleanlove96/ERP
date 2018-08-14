package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.MoneyManagementTable;
import com.pojo.MoneyTableLooks;

public interface MoneyManagementTableMapper {
    int deleteByPrimaryKey(String moneyManId);

    int insert(MoneyManagementTable record);

    int insertSelective(MoneyManagementTable record);

    MoneyManagementTable selectByPrimaryKey(String moneyManId);

    int updateByPrimaryKeySelective(MoneyManagementTable record);

    int updateByPrimaryKey(MoneyManagementTable record);

	/**
	 * <p>
	 * 获取页面所需要页码数
	 * </p>
	 * @author zm
	 * @Date 2018年7月2日
	 * @param string
	 * @return
	 */
	int getTotalNums(String string);

	/**
	 * <p>
	 * 获取页面显示的东西
	 * </p>
	 * @author zm
	 * @Date 2018年7月2日
	 * @param map
	 * @return
	 */
	List<MoneyManagementTable> getReceivingTable(Map<String, Object> map);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年7月2日
	 * @param map
	 * @return
	 */
	int getTotalNums(Map<String, Object> map);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年7月4日
	 * @param map
	 * @return
	 */
	int getWlzwLook(Map<String, Object> map);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年7月4日
	 * @param map
	 * @return
	 */
	List<MoneyTableLooks> getLooksAllBills(Map<String, Object> map);

	/**
	 * <p>
	 * 商品销售统计需要的页码数量
	 * </p>
	 * @author zm
	 * @Date 2018年7月4日
	 * @param map
	 * @return
	 */
	int getSaleGoodsNums(Map<String, Object> map);

	/**
	 * <p>
	 * s商品销售统计查询详情页面显示的数据
	 * </p>
	 * @author zm
	 * @Date 2018年7月4日
	 * @param map
	 * @return
	 */
	List<MoneyTableLooks> getGoodsSaleParticularsTable(Map<String, Object> map);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年7月6日
	 * @param map
	 * @return
	 */
	List<MoneyTableLooks> getLooksAllBillsAs(Map<String, Object> map);
}