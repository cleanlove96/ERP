package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.PurchaseTable;

public interface PurchaseTableMapper {
    int deleteByPrimaryKey(String purchaseTableId);

    int insert(PurchaseTable record);

    int insertSelective(PurchaseTable record);

    PurchaseTable selectByPrimaryKey(String purchaseTableId);

    int updateByPrimaryKeySelective(PurchaseTable record);

    int updateByPrimaryKey(PurchaseTable record);
    /**
     * 
     * 
     *<p>根据传入的材料id和年份查找</p>
     *@author lily
     *@param 传入的参数
     *@return 返回值类型
     *
     */
	List<PurchaseTable> selectCountByIdAndYear(Map<String, Object> map);
	/**
	 * 
	 * 
	 *<p>根据传入的年份查找</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	List<PurchaseTable> selectExtendIdByYear(String searchCard);
	
	List<PurchaseTable> selectAllByExtendId(String extendId);
}