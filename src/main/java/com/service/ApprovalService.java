package com.service;

import javax.servlet.http.HttpServletRequest;

import com.model.LeaveInfo;
import com.model.Reimbursement;

/**
 * 
 * <h2>审核流程接口层</h2>
 * <p>包含了审核流程所需的全部业务逻辑处理流程</p>
 * @author QJ
 * @version 1.0
 */
public interface ApprovalService {
	
	/**
	 * 
	 * <h2>提交请假表</h2>
	 * @author QJ
	 * @version 1.0
	 * 返回值 
	 */
	String insertLeave(HttpServletRequest request);

	String seeLeave(HttpServletRequest request);

	String seeAllLeave(HttpServletRequest request);

	String updateLeaveState(HttpServletRequest request);

	String seeMyReimbursement(HttpServletRequest request);

	String writeMyReimbursement(HttpServletRequest request);

	String getPage(HttpServletRequest request);

	String ARgetPage(HttpServletRequest request);

	String seeAllReimbursement(HttpServletRequest request);

	String LgetPage(HttpServletRequest request);

	String getPageLeaveApproval(HttpServletRequest request);

	

	String seeLeaveAccountInfo(HttpServletRequest request);

	int seeLeaveCountNotRead(HttpServletRequest request);

	int queryAllNotApprovalLeaveCount(HttpServletRequest request);

	int queryReiCount(HttpServletRequest request);

	int queryAllCountRei(HttpServletRequest request);

	String approvalRei(HttpServletRequest request);

	LeaveInfo selectLeaveById(HttpServletRequest request);

	String xiuGaiLeave(HttpServletRequest request);

	Reimbursement selectReiById(HttpServletRequest request);

	String updateRei(HttpServletRequest request);

	String delLeave(HttpServletRequest request);

	String delRei(HttpServletRequest request);

	String chakan(HttpServletRequest request);

}
