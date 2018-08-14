/**
 * 
 */
package com.service;

import javax.servlet.http.HttpServletRequest;

import com.model.CapacityInfo;

/**
 * <h2>处理生产线业务的接口</h2>
 * @Author QinPeng
 * Date 2018年6月25日
 */
public interface CapacityService {

	/**
	 * @param req
	 * @param capacityInfo
	 * @return
	 */
	String addCapacityInfo(HttpServletRequest req, CapacityInfo capacityInfo);

	/**
	 * @param req
	 * @return
	 */
	String getStaticPage(HttpServletRequest req);

	/**
	 * @param req
	 * @return
	 */
	String getPageList(HttpServletRequest req);

	/**
	 * @param req
	 */
	void selectById(HttpServletRequest req);

	/**
	 * @param req
	 * @param capacity
	 * @return
	 */
	String updateById(HttpServletRequest req, CapacityInfo capacity);

	/**
	 * @param req
	 * @return
	 */
	String updateStatus(HttpServletRequest req);

}
