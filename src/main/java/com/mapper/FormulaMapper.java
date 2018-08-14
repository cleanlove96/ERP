package com.mapper;


import java.util.List;
import java.util.Map;


import com.model.Formula;
import com.pojo.FormulaList;
import com.pojo.FormulaTable;

public interface FormulaMapper {
    int deleteByPrimaryKey(String formulaId);

    int insert(Formula record);

    int insertSelective(Formula record);

    Formula selectByPrimaryKey(String formulaId);

    int updateByPrimaryKeySelective(Formula record);

    int updateByPrimaryKey(Formula record);

    /**
     * 
     * 
     *<p>根据商品id，查询出物料表信息（配方）</p>
     *@author lily
     *@param 传入的参数
     *@return 返回值类型
     *
     */
	List<Formula> selsectMaterialIdByCommodityId(String commodityId);
	
	
	

	/**
	 * @param search
	 * @return
	 */
	int getFormulaNum(String search);

	/**
	 * @param map
	 * @return
	 */
	List<FormulaTable> selectLike(Map<String, Object> map);

	
	/**
	 * <p>获取所有商品ID并去重</p>
	 * @param map
	 * @return
	 */
	List<FormulaList> getComId(Map<String, Object> map);
	
	/**
	 * <p>获取所有去重后的商品ID个数</p>
	 * @param map
	 * @return
	 */
	int getComIdNum(String search);
	
	/**
	 * <p>通过商品ID查询数据</p>
	 * @param map
	 * @return
	 */
	List<FormulaTable> getDataByComId(String commodityId);

}