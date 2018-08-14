package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.CustomerInfo;

import com.model.SaleBills;
import com.model.SaleBillsDetailResult;
import com.model.SaleSelectResult;
import com.model.SystemAccount;
import com.model.SystemCommodityInformation;
import com.pojo.ChenBenLiRun;
import com.pojo.SaleBillDetail;

public interface SaleBillsMapper {
    int deleteByPrimaryKey(String saleBillId);

    int insert(SaleBills record);

    int insertSelective(SaleBills record);

    SaleBills selectByPrimaryKey(String saleBillId);

    int updateByPrimaryKeySelective(SaleBills record);

    int updateByPrimaryKey(SaleBills record);

	List<SaleSelectResult> selectSale(Map<String, Object> map);

	int selectPageCount(String string);

	List<CustomerInfo> selectCustomerName();

	List<SystemAccount> selectAccountName();

	List<SystemCommodityInformation> selectCommodityName();

	int insertSale(Map<String, Object> map);

	List<SaleBillsDetailResult> selectDetailsByReceipts(String receipts);

	void insertMMTM(Map<String, Object> mapss);

	/**
	 * <p>
	 * 获取所有单据的信息，单据的详情，数量客户销售人员单价金额。
	 * </p>
	 * @author zm
	 * @param map 
	 * @Date 2018年7月1日
	 * @return
	 */
	List<SaleBillDetail> getAllSaleBillDetail(Map<String, Object> map);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年7月1日
	 * @param string
	 * @return
	 */
	int getTotalNum(String string);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年7月1日
	 * @param map
	 * @return
	 */
	int getAllGoodsTjSum(Map<String, Object> map);

	/**
	 * <p>
	 * 获取销售商品统计页面的内容
	 * </p>
	 * @author zm
	 * @Date 2018年7月2日
	 * @param map
	 * @return
	 */
	List<SaleBillDetail> getAllGoodsTjBillsAs(Map<String, Object> map);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年7月2日
	 * @param map
	 * @return
	 */
	int getTotalNum(Map<String, Object> map);

	/**
	 * <p>
	 * 成本利润统计查询所有的数量
	 * </p>
	 * @author zm
	 * @Date 2018年7月3日
	 * @param map
	 * @return
	 */
	int getCBLRSum(Map<String, Object> map);

	/**
	 * <p>
	 * 成本利润统计查询有哪些商品，每个商品有多少数量
	 * </p>
	 * @author zm
	 * @Date 2018年7月3日
	 * @param map
	 * @return
	 */
	List<SaleBillDetail> getCBLRBTjills(Map<String, Object> map);

	/**
	 * <p>
	 * 成本利润统计计算总的条数
	 * </p>
	 * @author zm
	 * @Date 2018年7月4日
	 * @return
	 */
	int getCBLRSum();

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @param map 
	 * @Date 2018年7月4日
	 * @return
	 */
	List<ChenBenLiRun> getAllGoodsId(Map<String, Object> map);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年7月4日
	 * @param commodityId
	 * @return 
	 */
	ChenBenLiRun getAllGoodsCB(String commodityId);

	void insertGOIS(Map<String, Object> mapsGoods);
}