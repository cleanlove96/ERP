package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.LeaveInfo;
import com.model.Reimbursement;
import com.service.ApprovalService;




/**
 * 
 * <h2>审批流程控制器</h2>
 * <p>负责处理事由流程审批事由请求</p>
 * @author QJ
 * @version 1.0
 * 
 */
@Controller
@RequestMapping("/approvalController")
public class ApprovalController {

	@Resource
	private ApprovalService approvalService;
	
	
	/**
	 * 
	 * <p>提交请假条</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年6月30日
	 */
	@RequestMapping("/submitLeave.ajax")
	public @ResponseBody String submitLeave(HttpServletRequest request,HttpServletResponse response) {
		return approvalService.insertLeave(request);
	}
	
	/**
	 * 
	 * <p>请假人传入状态</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@RequestMapping(value="/doSearchStateLeave.do",produces="text/html; charset=UTF-8")
	public  String doSearchStateLeave(HttpServletRequest request) {
		request.setAttribute("state", request.getParameter("state"));
		int i = approvalService.seeLeaveCountNotRead(request);
		request.setAttribute("notRead", i);
		return "/view/approval/leave_staff.jsp";
	}
	
	/**
	 * 
	 * <p>请假人分页</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@RequestMapping("/LgetPage.do")
	public @ResponseBody String LgetPage(HttpServletRequest request) {
		return approvalService.LgetPage(request);
	}
	
	
	/**
	 * 
	 * <p>请假人查询请假条状态分页</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年6月30日
	 */
	@RequestMapping(value="/seeLeave.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String seeLeave(HttpServletRequest request,HttpServletResponse response) {
		return approvalService.seeLeave(request);
	}
	
	/**
	 * 
	 * <p>请假审核人传入状态</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@RequestMapping(value="/doSearchStateLeaveApproval.do",produces="text/html; charset=UTF-8")
	public  String doSearchStateLeaveApproval(HttpServletRequest request) {
		request.setAttribute("state", request.getParameter("state"));
		int i = approvalService.queryAllNotApprovalLeaveCount(request);
		request.setAttribute("notRead", i);
		return "/view/approval/leave_approval.jsp";
	}
	/**
	 * 
	 * <p>请假审核人分页</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@RequestMapping("/getPageLeaveApproval.do")
	public @ResponseBody String getPageLeaveApproval(HttpServletRequest request) {
		return approvalService.getPageLeaveApproval(request);
	}
	/**
	 * 
	 * <p>请假审核人查询分页</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年6月30日
	 */
	@RequestMapping(value="/approvalLeave.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String approvalLeave(HttpServletRequest request,HttpServletResponse response) {
		return approvalService.seeAllLeave(request);
	}
	
	/**
	 * 
	 * <p>审核假条</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月2日
	 */
	@RequestMapping(value="/approval.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String approval(HttpServletRequest request) {
		return approvalService.updateLeaveState(request);
	}
	
	
	
	
	
	//*******************************报销单**************************************************
	/**
	 * 
	 * <p>填写报销单</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@RequestMapping(value="/writeReimbursement.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String writeReimbursement(HttpServletRequest request) {
		return approvalService.writeMyReimbursement(request);
	}
	
	/**
	 * 
	 * <p>报销人传入状态</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@RequestMapping(value="/doSearchState.do",produces="text/html; charset=UTF-8")
	public  String doSearchState(HttpServletRequest request) {
		request.setAttribute("state", request.getParameter("state"));
		int i = approvalService.queryReiCount(request);
		request.setAttribute("notRead", i);
		return "/view/approval/reimbursement.jsp";
	}
	/**
	 * 
	 * <p>报销人分页</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@RequestMapping("/getPage.do")
	public @ResponseBody String getPage(HttpServletRequest request) {
		return approvalService.getPage(request);
	}
	/**
	 * 
	 * <p>报销人查询自己的报销单</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月2日
	 */
	@RequestMapping(value="/seeReimbursement.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String seeReimbursement(HttpServletRequest request) {
		return approvalService.seeMyReimbursement(request);
	}
	
	/**
	 * 
	 * <p>审核人传入状态</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@RequestMapping(value="/doSearchStateApproval.do",produces="text/html; charset=UTF-8")
	public  String doSearchStateApproval(HttpServletRequest request) {
		request.setAttribute("state", request.getParameter("state"));
		int i = approvalService.queryAllCountRei(request);
		request.setAttribute("notRead", i);
		return "/view/approval/reimbursement_appval.jsp";
	}
	
	/**
	 * 
	 * <p>审核人查询分页</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@RequestMapping("/ARgetPage.do")
	public @ResponseBody String ARgetPage(HttpServletRequest request) {
		return approvalService.ARgetPage(request);
	}
	/**
	 * 
	 * <p>审核人查询所有的报销单</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月2日
	 */
	@RequestMapping(value="/seeAllReimbursement.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String seeAllReimbursement(HttpServletRequest request) {
		return approvalService.seeAllReimbursement(request);
	}
	
	/**
	 * 
	 * <p>传入员工id</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@RequestMapping(value="/accountId.do",produces="text/html; charset=UTF-8")
	public  String accountId(HttpServletRequest request,String bxPersonId ) {
		request.setAttribute("accountId",bxPersonId);
		return "/view/approval/accountInfo.jsp";
	}
	
	/**
	 * 
	 * <p>审核报销单</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月2日
	 */
	@RequestMapping(value="/approvalRei.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String approvalRei(HttpServletRequest request) {
		return approvalService.approvalRei(request);
	}
	
	/**
	 * 
	 * <p>修改请假单</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月2日
	 */
	@RequestMapping(value="/xiuGaiLeave.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String xiuGaiLeave(HttpServletRequest request) {
		return approvalService.xiuGaiLeave(request);
	}
	/**
	 * 
	 * <p>修改报销单</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月2日
	 */
	@RequestMapping(value="/updateRei.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String updateRei(HttpServletRequest request) {
		
		return approvalService.updateRei(request);
	}
	
	/**
	 * 
	 * <p>查询员工详细信息</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@RequestMapping(value="/seeLeaveAccountInfo.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String seeLeaveAccountInfo(HttpServletRequest request) {
		return approvalService.seeLeaveAccountInfo(request);
	}
	/**
	 * 
	 * <p>删除报销</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@RequestMapping(value="/delRei.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String delRei(HttpServletRequest request) {
		return approvalService.delRei(request);
	}
	/**
	 * 
	 * <p>删除假条</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@RequestMapping(value="/delLeave.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String delLeave(HttpServletRequest request) {
		return approvalService.delLeave(request);
	}
	
	/**
	 * 
	 * <p>修改请假单UI</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@RequestMapping(value="/updateLeave.do",produces="text/html; charset=UTF-8")
	public  String updateLeave(HttpServletRequest request ) {
		LeaveInfo lea = approvalService.selectLeaveById(request);
		request.setAttribute("LeaveInfo", lea);
		return "/view/approval/leave_update.jsp";
	}
	/**
	 * 
	 * <p>修改报销单UI</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@RequestMapping(value="/updateReiUI.do",produces="text/html; charset=UTF-8")
	public  String updateReiUI(HttpServletRequest request ) {
		Reimbursement rei = approvalService.selectReiById(request);
		request.setAttribute("reimbursement", rei);
		return "/view/approval/rei_update.jsp";
	}
	/**
	 * 
	 * <p>查询报销单详细信息</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@RequestMapping(value="/seeReiXiangXi.do",produces="text/html; charset=UTF-8")
	public  String seeReiXiangXi(HttpServletRequest request ) {
		Reimbursement rei = approvalService.selectReiById(request);
		request.setAttribute("reimbursement", rei);
		return "/view/approval/rei_info.jsp";
	}
	/**
	 * 
	 * <p>查看已审核假条详情</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@RequestMapping(value="/chakan.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String chakan(HttpServletRequest request) {
		return approvalService.chakan(request);
	}
	
}
