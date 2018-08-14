package com.service;

import javax.servlet.http.HttpServletRequest;

import com.model.FixedAssets;


public interface AssetsService {

	/**
	 * <p>
	 * 获取固定资产信息的总条数
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @param request
	 * @return
	 */
	String getPage(HttpServletRequest request);

	/**
	 * <p>
	 * 根据kkpager的点击页面查询该页面的固定资产的详细信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @param request
	 * @return
	 */
	String getFixedAssetsTable(HttpServletRequest request);

	/**
	 * <p>
	 * 对固定资产的编号的自动初始化，
	 * 查询数据库存有的排在最后一个编号，增加数值
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @return
	 */
	String getFixedAssetsNum();

	/**
	 * <p>
	 * 添加固定资产数据
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @param fa
	 * @return
	 */
	String addFixedAssets(FixedAssets fa);

	/**
	 * <p>
	 * 根据id查询固定资产信息传到前台用于修改
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @param request
	 * @return
	 */
	FixedAssets queryFixedAssetsById(HttpServletRequest request);

	/**
	 * <p>
	 * 修改固定资产信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @param fa
	 * @return
	 */
	String updateCustomer(FixedAssets fa);

	/**
	 * <p>
	 * 根据id删除固定资产信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月29日
	 * @param request
	 * @return
	 */
	String deleteFixedAssets(HttpServletRequest request);

}
