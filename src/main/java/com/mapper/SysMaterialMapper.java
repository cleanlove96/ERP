package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.SysMaterial;

public interface SysMaterialMapper {
    int deleteByPrimaryKey(String materialId);

    int insert(SysMaterial record);

    int insertSelective(SysMaterial record);

    SysMaterial selectByPrimaryKey(String materialId);

    int updateByPrimaryKeySelective(SysMaterial record);

    int updateByPrimaryKey(SysMaterial record);
    /**
	 * <p>
	 * 查询所有的sysMaterial信息
	 * </p>
	 * @author zm
	 * @Date 2018年6月23日
	 * @return 返回一个sysMaterial的list集合
	 */
	List <SysMaterial> queryAllsysMaterial(Map<String, Object> map);

	/**
	 * <p>
	 * 查询搜索的数据总量
	 * </p>
	 * @author zm
	 * @Date 2018年6月24日
	 * @param string
	 * @return
	 */
	int getTotalNum(String string);

	/**
	 * <p>
	 * 获取原料信息总数量数量
	 * </p>
	 * @author zm
	 * @Date 2018年6月24日
	 * @param map
	 * @return
	 */
	List<SysMaterial> getMaterialTable(Map<String, Object> map);
	/**
	 * <p>
	 * 获取原料信息总数量数量
	 * </p>
	 * @author zm
	 * @Date 2018年6月24日
	 * @param map
	 * @return
	 */
	List<SysMaterial> getMaterialTables(Map<String, Object> map);
	
	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年6月24日
	 * @param sysMaterial
	 */
	int updateBySysMaterialId(SysMaterial sysMaterial);

	/**
	 * <p>
	 * 更改原料信息的状态
	 * </p>
	 * @author zm
	 * @Date 2018年6月24日
	 * @param sysMaterial
	 */
	int updateBySysMaterialStatus(SysMaterial sysMaterial);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年6月25日
	 * @param parameter
	 */
	List  selectByPrimaryName(String parameter);

	/**
	 * <p>
	 * 查询所有启用的原料
	 * </p>
	 * @return
	 */
	List<SysMaterial> selectSysMaterialAll();
}