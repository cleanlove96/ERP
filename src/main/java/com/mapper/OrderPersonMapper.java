package com.mapper;


import java.util.List;
import java.util.Map;



import com.model.OrderPerson;
import com.pojo.GoodsStoragePojo;
import com.pojo.MaterialStoragePojo;

public interface OrderPersonMapper {
    int deleteByPrimaryKey(String orderPersonId);

    int insert(OrderPerson record);

    int insertSelective(OrderPerson record);

    OrderPerson selectByPrimaryKey(String orderPersonId);

    int updateByPrimaryKeySelective(OrderPerson record);

    int updateByPrimaryKey(OrderPerson record);


    /**
     * 
     * <p>
     * 查询已质检后的生产订单的信息
     * </p>
     * @author FSK
     * @Date 2018年7月3日
     * @param map
     * @return
     */
	List<GoodsStoragePojo> getInfoTable(Map<String, Object> map);

    /**
     * 
     * <p>
     * 查询已质检后的生产订单的信息的数量
     * </p>
     * @author FSK
     * @Date 2018年7月3日
     * @param map
     * @return
     */
	List<GoodsStoragePojo> getTotalNum(String string);


	/**
	 * 已RopId，Business为条件删除数据
	 * @param map
	 */
	void deleteByRopIdBusiness(Map<String, String> map);
	/**
	 * 
	 * 
	 *<p>根据传入的采购表id，查询中间表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	List<OrderPerson> selectNameByTableId(String purchaseTableId);

    /**
     * 
     * <p>
     * 查询需要入库的原料信息
     * </p>
     * @author FSK
     * @Date 2018年7月3日
     * @param map
     * @return
     */
	List<MaterialStoragePojo> getMaterial(Map<String, Object> map);

    /**
     * 
     * <p>
     * 查询需要入库的原料信息条数
     * </p>
     * @author FSK
     * @Date 2018年7月3日
     * @param map
     * @return
     */
	List<MaterialStoragePojo> getNum(String string);

    /**
     * 
     * <p>
     * 根据生产单Id查询
     * </p>
     * @author FSK
     * @Date 2018年7月4日
     * @return
     */
	OrderPerson selectByRopId(String ropId);

}