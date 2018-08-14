package com.service;

import javax.servlet.http.HttpServletRequest;

import com.model.SystemCommodityInformation;

/**
 * <h2>处理商品业务的接口</h2>
 * Author JiangShan
 * Date 2018年6月25日
 */
public interface CommodityService {

	/**
	 * <h2>查询商品</h2>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 * @return String
	 */
	String selectCommodity(HttpServletRequest request);

	/**
	 * <h2>添加商品</h2>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 * @return String
	 */
	String addCommodity(HttpServletRequest request);

	/**
	 * <h2>更改商品状态</h2>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 */
	void updateStatus(HttpServletRequest request);

	/**
	 * <h2>通过商品ID查询商品信息</h2>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 * @return 商品类的对象
	 */
	SystemCommodityInformation selectCommodityById(HttpServletRequest request);

	/**
	 * <h2>修改商品信息</h2>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 * @return String
	 */
	String updateCommodityInformation(HttpServletRequest request);

	/**
	 * <h2>通过商品名称查询商品</h2>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 * @return String
	 */
	String selectCommodityByName(HttpServletRequest request);

	/**
	 * <h2>获取页数</h2>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 * @return String
	 */
	String selectPageCount(HttpServletRequest request);

}
