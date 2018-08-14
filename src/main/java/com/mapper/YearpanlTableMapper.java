package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.YearpanlTable;

public interface YearpanlTableMapper {
    int deleteByPrimaryKey(String yearpanlTableId);

    int insert(YearpanlTable record);

    int insertSelective(YearpanlTable record);

    YearpanlTable selectByPrimaryKey(String yearpanlTableId);

    int updateByPrimaryKeySelective(YearpanlTable record);

    int updateByPrimaryKey(YearpanlTable record);

	List<YearpanlTable> getYearplanTable(Map<String, Object> map);

	Integer selectCount(Map<String, Object> map);
	/**
	 * 
	 * 
	 *<p>根据年份，查询出年度总计划表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	List<YearpanlTable> selectAccountIdByYear(String year);
}