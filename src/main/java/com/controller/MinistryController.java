package com.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.DateTypeTable;
import com.service.MinistryService;
import com.service.SectionInfoService;

/**
 * <p>
 * 人事部控制层，管理薪资调整，考勤，工资管理,员工打卡模块,日期管理;
 * </p>	
 * @Copyright (C),华清远见
 * @author 小小
 * @Date:2018年6月30日
 */
@Controller
@RequestMapping("/ministryController")
public class MinistryController {
	@Resource
	private MinistryService ms;
	@Resource
	private SectionInfoService sf;
	
	/**
	 * <p>
	 * 查询职位的总数
	 * </p>
	 * @author 小小
	 * @Date 2018年6月30日
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPage.ajax")
	public @ResponseBody String getPage(HttpServletRequest request) {
		return ms.getPage(request);
	}
	/**
	 * <p>
	 * 获取职务对应薪资的详细信息
	 * </p>
	 * @author 小小
	 * @Date 2018年6月30日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getMinistryTable.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String getMinistryTable(HttpServletRequest request) {
		return ms.getMinistryTable(request);
	}
	/**
	 * <p>
	 * 根据id调整薪资
	 * </p>
	 * @author 小小
	 * @Date 2018年6月30日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/changeMinistry.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String changeMinistry(HttpServletRequest request) {
		return ms.changeMinistry(request);
	}
	/**
	 * <p>
	 * 根据员工id查询出员工薪资基础信息用于调整
	 * </p>
	 * @author 小小
	 * @Date 2018年7月1日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getMinistryTableByAccountId.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getMinistryTableByAccountId(HttpServletRequest request) {
		return ms.getMinistryTableByAccountId(request);
	}
	/**
	 * <p>
	 * 根据id调整特别薪资
	 * </p>
	 * @author 小小
	 * @Date 2018年7月1日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/changeIndividual.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String changeIndividual(HttpServletRequest request) {
		return ms.changeIndividual(request);
	}
	/**
	 * <p>
	 * 显示所有部门到工资计算页面
	 * </p>
	 * @author 小小
	 * @Date 2018年7月1日
	 * @param request
	 * @return
	 */
	@RequestMapping("/showSection.do")
	public String showSection(HttpServletRequest request) {
         sf.selectAll(request);
         return "/view/ministry_personnel/settlement_salary.jsp";
	}
	
	/**
	 * <p>
	 * 查询本月有出勤记录的员工的相应工资
	 * </p>
	 * @author 小小
	 * @Date 2018年7月2日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/showSalary.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String showSalary(HttpServletRequest request) {
		return ms.showSalary(request);
	}
	/**
	 * <p>
	 * 自动计算上月全公司员工工资并显示在页面上
	 * </p>
	 * @author 小小
	 * @Date 2018年7月3日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/showAllSalary.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String showAllSalary(HttpServletRequest request) {
		return ms.showSalary(request);
	}
	/**
	 * <p>
	 * 结算全部员工工资
	 * </p>
	 * @author 小小
	 * @Date 2018年7月3日
	 * @param pt
	 * @return
	 */
	@RequestMapping(value="/liquidation.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String liquidation(HttpServletRequest request) {
		return ms.liquidation(request);
	}
	/**
	 * <p>
	 * 判断本月是否已经结算了工资，如果结算了返回yes 结算按钮不加载，
	 * 否则 返回no ，结算按钮加载
	 * </p>
	 * @author 小小
	 * @Date 2018年7月3日
	 * @return
	 */
	@RequestMapping(value="/isLiquidation.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String isLiquidation() {
		return ms.isLiquidation();
	}
	/**
	 * <p>
	 * 员工打卡，打卡时间区间为早上八点到九点之间，成功返回打卡相应数据，失败返回ERROR;
	 * 节假日打卡状态为2 3倍工资  休息日状态为1  双倍工资  工作日状态为0 正常工资
	 * </p>
	 * @author 小小
	 * @Date 2018年7月4日
	 * @return
	 */
	@RequestMapping(value="/accountClock.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String accountClock(HttpServletRequest request) {
		return ms.accountClock(request);
	}
	/**
	 * <p>
	 * 判断员工今日是否已打卡
	 * </p>
	 * @author 小小
	 * @Date 2018年7月4日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/isClock.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String isClock(HttpServletRequest request) {
		return ms.isClock(request);
	}
	/**
	 * <p>
	 * 添加日期管理包括节假日及特殊的工作日
	 * </p>
	 * @author 小小
	 * @Date 2018年7月4日
	 * @param dtt
	 * @return
	 */
	@RequestMapping(value="/addDateType.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String addDateType(DateTypeTable dtt) {
		return ms.addDateType(dtt);
	}
	/**
	 * <p>
	 * 修改日期信息
	 * </p>
	 * @author 小小
	 * @Date 2018年7月5日
	 * @param dtt
	 * @return
	 */
	@RequestMapping(value="/updateDateType.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String updateDateType(DateTypeTable dtt) {
		return ms.updateDateType(dtt);
	}
	/**
	 * <p>
	 * 获取日子总条数用于分页
	 * </p>
	 * @author 小小
	 * @Date 2018年7月5日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getDayPage.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String getDayPage(HttpServletRequest request) {
		return ms.getDayPage(request);
	}
	/**
	 * <p>
	 * 分页查询日期数据
	 * </p>
	 * @author 小小
	 * @Date 2018年7月5日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getDayTable.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String getDayTable(HttpServletRequest request) {
		return ms.getDayTable(request);
	}
	/**
	 * <p>
	 * 根据Id删除一条日期信息
	 * </p>
	 * @author 小小
	 * @Date 2018年7月5日
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteDateType.ajax",produces="text/html;charset=UTF-8")
	public @ResponseBody String deleteDateType(HttpServletRequest request) {
		return ms.deleteDateType(request);
	}
	/**
	 * <p>
	 * 根据id来进行日期管理的修改，将数据发送到前端
	 * </p>
	 * @author 小小
	 * @Date 2018年7月5日
	 * @param request
	 * @return
	 */
	@RequestMapping("/dayEdit.do")
	public String dayEdit(HttpServletRequest request) {
		ms.dayEdit(request);
		return "/view/ministry_personnel/date_edit.jsp";
	}
}
