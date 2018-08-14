package com.service;


import javax.servlet.http.HttpServletRequest;

import com.model.DateTypeTable;


public interface MinistryService {

	/**
	 * <p>
	 * 查询职位的总数
	 * </p>
	 * @author 小小
	 * @Date 2018年6月30日
	 * @param request
	 * @return
	 */
	String getPage(HttpServletRequest request);

	/**
	 * <p>
	 * 获取职务对应薪资的详细信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月30日
	 * @param request
	 * @return
	 */
	String getMinistryTable(HttpServletRequest request);

	/**
	 * <p>
	 * 根据id调整薪资
	 * </p>
	 * @author 小小
	 * @Date 2018年6月30日
	 * @param request
	 * @return
	 */
	String changeMinistry(HttpServletRequest request);
	
	/**
	 * <p>
	 * 根据员工id查询出员工薪资基础信息用于调整
	 * </p>
	 * @author 小小
	 * @Date 2018年7月1日
	 * @param request
	 * @return
	 */
	String getMinistryTableByAccountId(HttpServletRequest request);

	/**
	 * <p>
	 * 根据id调整特别薪资
	 * </p>
	 * @author 小小
	 * @Date 2018年7月1日
	 * @param request
	 * @return
	 */
	String changeIndividual(HttpServletRequest request);
	
	/**
	 * <p>
	 * 查询本月有出勤记录的员工的相应工资
	 * </p>
	 * @author 小小
	 * @param request 
	 * @Date 2018年7月1日
	 * @return
	 */
	String showSalary(HttpServletRequest request);

	/**
	 * <p>
	 * 结算全部员工工资
	 * </p>
	 * @author 小小
	 * @Date 2018年7月3日
	 * @param request
	 * @return
	 */
	String liquidation(HttpServletRequest request);

	/**
	 * <p>
	 * 判断本月是否已经结算了工资，如果结算了返回yes 结算按钮不加载，
	 * 否则 返回no ，结算按钮加载
	 * </p>
	 * @author 小小
	 * @Date 2018年7月3日
	 * @return
	 */
	String isLiquidation();

	/**
	 * <p>
	 * 员工打卡，打卡时间区间为早上八点到九点之间，成功返回打卡相应数据，失败返回ERROR;
	 * </p>
	 * @author 小小
	 * @param request 
	 * @Date 2018年7月4日
	 * @return
	 */
	String accountClock(HttpServletRequest request);

	/**
	 * <p>
	 * 判断员工今日是否已打卡
	 * </p>
	 * @author 小小
	 * @Date 2018年7月4日
	 * @param request
	 * @return
	 */
	String isClock(HttpServletRequest request);

	/**
	 * <p>
	 * 添加日期管理包括节假日及特殊的工作日
	 * </p>
	 * @author 小小
	 * @Date 2018年7月4日
	 * @param dtt
	 * @return
	 */
	String addDateType(DateTypeTable dtt);

	/**
	 * <p>
	 * 日子总数分页用
	 * </p>
	 * @author 小小
	 * @Date 2018年7月5日
	 * @param request
	 * @return
	 */
	String getDayPage(HttpServletRequest request);

	/**
	 * <p>
	 * 分页查询日期数据
	 * </p>
	 * @author 小小
	 * @Date 2018年7月5日
	 * @param request
	 * @return
	 */
	String getDayTable(HttpServletRequest request);

	/**
	 * <p>
	 * 根据id来进行日期管理的修改，将数据发送到前端
	 * </p>
	 * @author 小小
	 * @Date 2018年7月5日
	 * @param request
	 * @return
	 */
	void dayEdit(HttpServletRequest request);

	/**
	 * <p>
	 * 修改日期信息
	 * </p>
	 * @author 小小
	 * @Date 2018年7月5日
	 * @param dtt
	 * @return
	 */
	String updateDateType(DateTypeTable dtt);

	/**
	 * <p>
	 * 根据Id删除一条日期信息
	 * </p>
	 * @author 小小
	 * @Date 2018年7月5日
	 * @param request
	 * @return
	 */
	String deleteDateType(HttpServletRequest request);
}
