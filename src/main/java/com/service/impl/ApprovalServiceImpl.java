package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import com.mapper.LeaveInfoMapper;
import com.mapper.ReimbursementMapper;
import com.mapper.SectionInfoMapper;
import com.mapper.SystemAccountMapper;
import com.model.LeaveInfo;
import com.model.Reimbursement;
import com.model.SectionInfo;
import com.model.SystemAccount;
import com.pojo.AccountLeaveInfo;
import com.pojo.AccountPage;
import com.pojo.ApprovalReimbursement;
import com.pojo.State;
import com.service.ApprovalService;

/**
 * 
 * <h2>审核流程业务实现层</h2>
 * <p>
 * 对业务逻辑接口的实现
 * </p>
 * 
 * @author QJ
 * @version 1.0
 */
@Service
public class ApprovalServiceImpl implements ApprovalService {

	private static Gson gson = new Gson();
	private static final int numAccPage = 5;
	@Resource
	private LeaveInfoMapper leaveInfoMapper;
	@Resource
	private SystemAccountMapper systemAccountMapper;
	@Resource
	private ReimbursementMapper reimbursementMapper;
	@Resource
	private SectionInfoMapper sectionInfoMapper;
	/**
	 * <h2>写假条</he>
	 * <p>
	 * </p>
	 * 
	 * @author QJ
	 * @Date 2018年6月29日
	 * @return
	 */

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

	@Override
	public String insertLeave(HttpServletRequest request) {
		LeaveInfo leaveInfo = new LeaveInfo();
		leaveInfo.setLeaveInfoId(UUID.randomUUID().toString());
		leaveInfo.setAccountId((String) request.getSession().getAttribute("ACCOUNT"));
		leaveInfo.setLeaveInfoReason(request.getParameter("leaveInfoReason"));

		leaveInfo.setLeaveInfoType(request.getParameter("leaveInfoType"));
		// 请假状态： 未审批(0)，审批中(1)，同意(2)，拒绝(3)
		leaveInfo.setLeaveInfoState("未审批");

		leaveInfo.setLeaveStartTime(request.getParameter("leaveStartTime"));
		leaveInfo.setLeaveEndTime(request.getParameter("leaveEndTime"));

		leaveInfo.setLeaveCreateTime(new Date());
		// 拒绝理由
		leaveInfo.setRefuseReason("正在审核");
		// 已读？0未读，1已读
		leaveInfo.setLeaveIsread("1");

		int i = leaveInfoMapper.insert(leaveInfo);
		// //通过登录用户ID查询对象
		// SystemAccount account = systemAccountMapper.selectByPrimaryKey((String)
		// request.getSession().getAttribute("ACCOUNT"));

		return i > 0 ? "success" : "error";
	}

	/**
	 * 
	 * <p>
	 * 请假人分页
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@Override
	public String LgetPage(HttpServletRequest request) {
		AccountPage apage = new AccountPage();
		String stateNum = request.getParameter("state");
		State sta = new State();
		sta.setAccountId((String) request.getSession().getAttribute("ACCOUNT"));
		int tn = 1;
		if (stateNum == null) {
			stateNum = "";
		} else {
			// 判断传入的状态
			if (stateNum.equals("3")) {
				// 请假人已读消息,0未读
				tn = leaveInfoMapper.queryNotReadLeaveCount(sta);
			} else if (stateNum.equals("2")) {
				tn = leaveInfoMapper.queryCompleteLeaveCount(sta);
			} else {
				// 查询审批状态
				if (stateNum.equals("0")) {
					sta.setLeaveInfoState("未审批");
				} else {
					sta.setLeaveInfoState("审批中");
				}
				tn = leaveInfoMapper.queryNotCompleteLeaveCount(sta);

			}

		}
		apage.setTotalRecords(tn + "");
		int t1 = tn % numAccPage;
		int t = tn / numAccPage;
		if (t1 > 0) {
			t = t + 1;
		}
		apage.setTotal(t + "");
		return gson.toJson(apage);
	}

	/**
	 * 
	 * <p>
	 * 请假人员查询自己的请假条
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@Override
	public String seeLeave(HttpServletRequest request) {
		String n = request.getParameter("n");
		State st = new State();
		st.setAccountId((String) request.getSession().getAttribute("ACCOUNT"));
		String stateNum = request.getParameter("state");
		System.out.println("++++++++++++++++++++++++++" + stateNum);

		if (stateNum == null) {
			stateNum = "";
		}

		int anum = 1;
		if (n != null && n.length() > 0) {
			anum = Integer.parseInt(n);
		}
		Map<String, Object> map = new HashMap<>();
		int start = (anum - 1) * numAccPage;
		map.put("start", start);
		map.put("size", numAccPage);
		map.put("accountId", (String) request.getSession().getAttribute("ACCOUNT"));
		// 判断传入的状态
		if (stateNum.equals("3")) {
			// 请假人已读消息,0未读

			System.out.println("__________________________1");
			List<LeaveInfo> leaveInfoList = leaveInfoMapper.queryNotRead(map);
			for (LeaveInfo leaveInfo : leaveInfoList) {
				leaveInfoMapper.updateIsState(leaveInfo.getLeaveInfoId());
			}
			return gson.toJson(leaveInfoList);
		} else if (stateNum.equals("2")) {
			System.out.println("__________________________2");
			List<LeaveInfo> leaveInfoList = leaveInfoMapper.queryCompleteLeave(map);
			for (LeaveInfo leaveInfo : leaveInfoList) {
				leaveInfoMapper.updateIsState(leaveInfo.getLeaveInfoId());
			}
			return gson.toJson(leaveInfoList);
		} else {
			// 查询审批状态
			if (stateNum.equals("0")) {
				st.setLeaveInfoState("未审批");
				List<LeaveInfo> leaveInfoList = leaveInfoMapper.queryNotCompleteLeave(map);
				return gson.toJson(leaveInfoList);
			} else {
				st.setLeaveInfoState("审批中");
				List<LeaveInfo> leaveInfoList = leaveInfoMapper.queryWaitCompleteLeave(map);
				return gson.toJson(leaveInfoList);
			}
		}
	}

	/**
	 * 
	 * <p>
	 * 请假审核人分页
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@Override
	public String getPageLeaveApproval(HttpServletRequest request) {
		AccountPage apage = new AccountPage();
		String stateNum = request.getParameter("state");
		State sta = new State();
		sta.setAccountId((String) request.getSession().getAttribute("ACCOUNT"));
		int tn = 1;
		if (stateNum == null) {
			stateNum = "";
		} else {
			// 判断传入的状态
			if (stateNum.equals("2")) {
				// 审核请假已读消息,0未读
				tn = leaveInfoMapper.queryAllNotReadLeaveCount(sta);
			} else if (stateNum.equals("1")) {
				tn = leaveInfoMapper.queryAllCompleteLeaveCount(sta);
			} else {
				tn = leaveInfoMapper.queryAllNotCompleteLeaveCount(sta);

			}

		}
		apage.setTotalRecords(tn + "");
		int t1 = tn % numAccPage;
		int t = tn / numAccPage;
		if (t1 > 0) {
			t = t + 1;
		}
		apage.setTotal(t + "");
		return gson.toJson(apage);
	}

	/**
	 * 
	 * <p>
	 * 请假审核人员查询请假条分页
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@Override
	public String seeAllLeave(HttpServletRequest request) {
		String n = request.getParameter("n");
		State st = new State();
		String s = (String) request.getSession().getAttribute("ACCOUNT");

		String approval = request.getParameter("state");
		System.out.println("++++++++++++++++++++++++++" + approval);

		if (approval == null) {
			approval = "";
		}

		int anum = 1;
		if (n != null && n.length() > 0) {
			anum = Integer.parseInt(n);
		}
		Map<String, Object> map = new HashMap<>();
		int start = (anum - 1) * numAccPage;
		map.put("start", start);
		map.put("size", numAccPage);
		map.put("accountId", (String) request.getSession().getAttribute("ACCOUNT"));
		// 判断传入的状态
		List<State> stateList = new ArrayList<State>();
		if (approval.equals("0")) {
			// 审核人查询待审核的假条
			System.out.println("++++++++++没有审批++++++++++");
			List<LeaveInfo> leaveInfoList = leaveInfoMapper.queryNotAllLeave(map);
			for (LeaveInfo leaveInfo : leaveInfoList) {
				State sta = new State();
				sta.setLeaveInfoId(leaveInfo.getLeaveInfoId());
				sta.setAccountId(leaveInfo.getAccountId());
				sta.setAccountName(systemAccountMapper.selectByPrimaryKey(leaveInfo.getAccountId()).getAccountName());
				sta.setLeaveStartTime(leaveInfo.getLeaveStartTime());
				sta.setLeaveEndTime(leaveInfo.getLeaveEndTime());
				sta.setLeaveCreateTime(leaveInfo.getLeaveCreateTime());
				sta.setLeaveInfoReason(leaveInfo.getLeaveInfoReason());
				sta.setLeaveInfoState(leaveInfo.getLeaveInfoState());
				sta.setLeaveInfoType(leaveInfo.getLeaveInfoType());
				sta.setLeaveIsread(leaveInfo.getLeaveIsread());
				sta.setRefuseReason(leaveInfo.getRefuseReason());
				stateList.add(sta);
				System.out.print("+++++++++打印+++++++++++++");
				System.out.println(sta.toString());
				leaveInfoMapper.updateState(leaveInfo.getLeaveInfoId());
			}
		} else if (approval.equals("1")) {
			// 审核人查询已经审核的假条
			System.out.println("++++++++++正在审批++++++++++");
			List<LeaveInfo> leaveInfoList = leaveInfoMapper.queryApprovalAllLeave(map);
			for (LeaveInfo leaveInfo : leaveInfoList) {
				State sta = new State();
				sta.setLeaveInfoId(leaveInfo.getLeaveInfoId());
				sta.setAccountId(leaveInfo.getAccountId());
				sta.setAccountName(systemAccountMapper.selectByPrimaryKey(leaveInfo.getAccountId()).getAccountName());
				sta.setLeaveStartTime(leaveInfo.getLeaveStartTime());
				sta.setLeaveEndTime(leaveInfo.getLeaveEndTime());
				sta.setLeaveCreateTime(leaveInfo.getLeaveCreateTime());
				sta.setLeaveInfoReason(leaveInfo.getLeaveInfoReason());
				sta.setLeaveInfoState(leaveInfo.getLeaveInfoState());
				sta.setLeaveInfoType(leaveInfo.getLeaveInfoType());
				sta.setLeaveIsread(leaveInfo.getLeaveIsread());
				sta.setRefuseReason(leaveInfo.getRefuseReason());
				stateList.add(sta);

			}

		} else {
			// 审核人查询未审批的假条
			System.out.println("++++++++++没有查看++++++++++");
			List<LeaveInfo> leaveInfoList = leaveInfoMapper.queryNotReadAllLeave(map);
			for (LeaveInfo leaveInfo : leaveInfoList) {
				State sta = new State();
				sta.setLeaveInfoId(leaveInfo.getLeaveInfoId());
				sta.setAccountId(leaveInfo.getAccountId());
				sta.setAccountName(systemAccountMapper.selectByPrimaryKey(leaveInfo.getAccountId()).getAccountName());
				sta.setLeaveStartTime(leaveInfo.getLeaveStartTime());
				sta.setLeaveEndTime(leaveInfo.getLeaveEndTime());
				sta.setLeaveCreateTime(leaveInfo.getLeaveCreateTime());
				sta.setLeaveInfoReason(leaveInfo.getLeaveInfoReason());
				sta.setLeaveInfoState(leaveInfo.getLeaveInfoState());
				sta.setLeaveInfoType(leaveInfo.getLeaveInfoType());
				sta.setLeaveIsread(leaveInfo.getLeaveIsread());
				sta.setRefuseReason(leaveInfo.getRefuseReason());
				stateList.add(sta);
				leaveInfoMapper.updateState(leaveInfo.getLeaveInfoId());
			}

		}
		for (State state : stateList) {
			System.out.println(
					"+++++++++++++++数组内容++++++++++++++++" + state.getAccountName() + state.getLeaveInfoReason());
		}
		System.err.println("++++++++++++数组长度++++++++++++++++++++++" + stateList.size());
		return gson.toJson(stateList);
	}

	/**
	 * 
	 * <p>
	 * 负责完成对家条的审核
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月2日
	 */

	@Override
	public String updateLeaveState(HttpServletRequest request) {
		LeaveInfo sta = new LeaveInfo();
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + request.getParameter("appro"));
		request.getParameter("leaveInfoId");
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + request.getParameter("leaveInfoId"));
		sta = leaveInfoMapper.selectByPrimaryKey(request.getParameter("leaveInfoId"));

		String str = request.getParameter("appro");
		if (str.equals("0")) {
			str = "拒绝";
		} else {
			str = "同意";
		}
		sta.setLeaveInfoState(str);
		System.out.println("^^^^^^^^^^^^^str^^^^^^^^^^^^" + sta.getLeaveInfoState());
		sta.setLeaveIsread("0");

		System.out.println("^^^^^^^^^^^^^str^^^^^^^^^^^^" + sta.getLeaveInfoId());
		int i = leaveInfoMapper.updateByPrimaryKeySelective(sta);

		return i > 0 ? "SUCCESS" : "ERROR";
	}

	/**
	 * 
	 * <p>
	 * 填写报销单
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@Override
	public String writeMyReimbursement(HttpServletRequest request) {
		Reimbursement rei = new Reimbursement();
		rei.setReimbursementId(UUID.randomUUID().toString());
		rei.setBxPersonId((String) request.getSession().getAttribute("ACCOUNT"));
		rei.setCostCreateTime(new Date());
		rei.setCostItem(request.getParameter("costItem"));
		rei.setCostTotal(request.getParameter("costTotal"));
		rei.setCostType(request.getParameter("costType"));
		rei.setReimbursementRespon(request.getParameter("reimbursementRespon"));
		// 设置审核状态
		rei.setReimbursementState("未审批");
		// 设置读取状态，0未读，1已读
		rei.setReimbursementIsread("1");
		int i = reimbursementMapper.insertSelective(rei);

		return i > 0 ? "SUCCESS" : "ERROR";

	}

	/**
	 * 
	 * <p>
	 * 报销人员查询自己的报销单
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@Override
	public String seeMyReimbursement(HttpServletRequest request) {

		String n = request.getParameter("n");
		Reimbursement rei = new Reimbursement();
		rei.setBxPersonId((String) request.getSession().getAttribute("ACCOUNT"));
		String stateNum = request.getParameter("state");
		System.out.println("#################ss###############" + stateNum);
		if (stateNum == null) {
			stateNum = "";
		}

		int anum = 1;
		if (n != null && n.length() > 0) {
			anum = Integer.parseInt(n);
		}
		Map<String, Object> map = new HashMap<>();
		int start = (anum - 1) * numAccPage;
		map.put("start", start);
		map.put("size", numAccPage);
		map.put("bxPersonId", (String) request.getSession().getAttribute("ACCOUNT"));
		if (stateNum.equals("2")) {
			// 报销人未读消息,0未读
			rei.setReimbursementIsread("0");
			System.out.println("__________________________1");
			List<Reimbursement> ReimbursementList = reimbursementMapper.queryNotRead(map);
			for (Reimbursement reimbursement : ReimbursementList) {
				System.err.println("#####################" + reimbursement.getReimbursementRespon());
				reimbursementMapper.updateIsRead(reimbursement.getReimbursementId());
			}

			return gson.toJson(ReimbursementList);
		} else if (stateNum.equals("1")) {
			// 报销人查看已审核报销单
			System.out.println("__________________________2");
			List<Reimbursement> ReimbursementList = reimbursementMapper.queryComplete(map);
			for (Reimbursement reimbursement : ReimbursementList) {
				System.err.println("#####################" + reimbursement.getReimbursementRespon());
				reimbursementMapper.updateIsRead(reimbursement.getReimbursementId());
			}
			return gson.toJson(ReimbursementList);
		} else {
			if (stateNum.equals("0")) {
				// 报销人查看未审核报销单
				List<Reimbursement> ReimbursementList = reimbursementMapper.queryNotCompleteREI(map);
				return gson.toJson(ReimbursementList);
			} else {
				// 报销人查看审核中报销单
				System.out.println("___________状态_______________3");
				List<Reimbursement> ReimbursementList = reimbursementMapper.queryNotComplete(map);
				for (Reimbursement reimbursement : ReimbursementList) {

					System.out.println("###########################" + reimbursement.getReimbursementRespon());
				}
				return gson.toJson(ReimbursementList);
			}

		}
	}

	/**
	 * 
	 * <p>
	 * 报销人分页
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@Override
	public String getPage(HttpServletRequest request) {
		AccountPage apage = new AccountPage();
		String state = request.getParameter("state");
		System.out.println("################state#############" + state);
		Reimbursement rei = new Reimbursement();
		rei.setBxPersonId((String) request.getSession().getAttribute("ACCOUNT"));
		String bxPersonI = rei.getBxPersonId();
		int tn = 1;
		if (state == null) {
			state = "";
		} else {
			if (state.equals("0")) {
				// 未审批报销单
				tn = reimbursementMapper.queryWaitApprovalCount(bxPersonI);
			} else if (state.equals("1")) {
				// 已审批报销单
				tn = reimbursementMapper.queryApprovalCount(bxPersonI);
			} else {
				if (state.equals("2")) {
					// 未读消息
					tn = reimbursementMapper.queryNotApprovalCount(bxPersonI);
				} else {
					// 审批中报销单
					tn = reimbursementMapper.queryMeiApprovalCount(bxPersonI);
				}
			}
		}
		apage.setTotalRecords(tn + "");
		int t1 = tn % numAccPage;
		int t = tn / numAccPage;
		if (t1 > 0) {
			t = t + 1;
		}
		apage.setTotal(t + "");
		return gson.toJson(apage);
	}

	/**
	 * 
	 * <p>
	 * 审核人分页
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@Override
	public String ARgetPage(HttpServletRequest request) {
		AccountPage apage = new AccountPage();
		String state = request.getParameter("state");
		System.out.println("################state#############" + state);
		Reimbursement rei = new Reimbursement();
		String bxPersonI = rei.getBxPersonId();
		int tn = 1;
		if (state == null) {
			state = "";
		} else {
			if (state.equals("0")) {
				// 待审核
				tn = reimbursementMapper.queryAllWaitApprovalCount(bxPersonI);
			} else if (state.equals("1")) {
				// 已审核
				tn = reimbursementMapper.queryAllApprovalCount(bxPersonI);
			} else {
				// 未读消息
				tn = reimbursementMapper.queryAllNotApprovalCount(bxPersonI);
			}
		}
		apage.setTotalRecords(tn + "");
		int t1 = tn % numAccPage;
		int t = tn / numAccPage;
		if (t1 > 0) {
			t = t + 1;
		}
		apage.setTotal(t + "");
		return gson.toJson(apage);
	}

	/**
	 * 
	 * <p>
	 * 审核人员查询所有报销单,但不包括自己部分
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月3日
	 */
	@Override
	public String seeAllReimbursement(HttpServletRequest request) {

		String n = request.getParameter("n");
		Reimbursement rei = new Reimbursement();
		rei.setBxPersonId((String) request.getSession().getAttribute("ACCOUNT"));
		String stateNum = request.getParameter("state");
		System.out.println("################################" + stateNum);
		if (stateNum == null) {
			stateNum = "";
		}

		int anum = 1;
		if (n != null && n.length() > 0) {
			anum = Integer.parseInt(n);
		}
		Map<String, Object> map = new HashMap<>();
		int start = (anum - 1) * numAccPage;
		map.put("start", start);
		map.put("size", numAccPage);
		map.put("bxPersonId", (String) request.getSession().getAttribute("ACCOUNT"));
		List<ApprovalReimbursement> ARList = new ArrayList<ApprovalReimbursement>();
		if (stateNum.equals("2")) {
			// 审核人(报销单)已读消息,0未读

			System.out.println("__________________________1");
			List<Reimbursement> ReimbursementList = reimbursementMapper.queryAllNotRead(map);
			ApprovalReimbursement AR = new ApprovalReimbursement();
			for (Reimbursement reimbursement : ReimbursementList) {
				AR.setAccountName(
						systemAccountMapper.selectByPrimaryKey(reimbursement.getBxPersonId()).getAccountName());
				AR.setBxPersonId(reimbursement.getBxPersonId());
				AR.setCostAuditorId(reimbursement.getCostAuditorId());
				AR.setCostCreateTime(reimbursement.getCostCreateTime());
				AR.setCostItem(reimbursement.getCostItem());
				AR.setCostTotal(reimbursement.getCostTotal());
				AR.setCostType(reimbursement.getCostType());
				AR.setReimbursementId(reimbursement.getReimbursementId());
				AR.setReimbursementIsread(reimbursement.getReimbursementIsread());
				AR.setReimbursementRefuse(reimbursement.getReimbursementRefuse());
				AR.setReimbursementRespon(reimbursement.getReimbursementRespon());
				AR.setReimbursementState(reimbursement.getReimbursementState());
				ARList.add(AR);
				reimbursementMapper.updateState(reimbursement.getReimbursementId());
			}

			return gson.toJson(ARList);
		} else if (stateNum.equals("1")) {
			System.out.println("__________________________2");
			List<Reimbursement> ReimbursementList = reimbursementMapper.queryAllComplete(map);
			ApprovalReimbursement AR = new ApprovalReimbursement();
			for (Reimbursement reimbursement : ReimbursementList) {
				AR.setAccountName(
						systemAccountMapper.selectByPrimaryKey(reimbursement.getBxPersonId()).getAccountName());
				AR.setBxPersonId(reimbursement.getBxPersonId());
				AR.setCostAuditorId(reimbursement.getCostAuditorId());
				AR.setCostCreateTime(reimbursement.getCostCreateTime());
				AR.setCostItem(reimbursement.getCostItem());
				AR.setCostTotal(reimbursement.getCostTotal());
				AR.setCostType(reimbursement.getCostType());
				AR.setReimbursementId(reimbursement.getReimbursementId());
				AR.setReimbursementIsread(reimbursement.getReimbursementIsread());
				AR.setReimbursementRefuse(reimbursement.getReimbursementRefuse());
				AR.setReimbursementRespon(reimbursement.getReimbursementRespon());
				AR.setReimbursementState(reimbursement.getReimbursementState());
				ARList.add(AR);
			}

			return gson.toJson(ARList);
		} else {
			// 查询待审批

			System.out.println("___________状态_______________3");
			List<Reimbursement> ReimbursementList = reimbursementMapper.queryAllNotComplete(map);
			ApprovalReimbursement AR = new ApprovalReimbursement();
			for (Reimbursement reimbursement : ReimbursementList) {
				AR.setAccountName(
						systemAccountMapper.selectByPrimaryKey(reimbursement.getBxPersonId()).getAccountName());
				AR.setBxPersonId(reimbursement.getBxPersonId());
				AR.setCostAuditorId(reimbursement.getCostAuditorId());
				AR.setCostCreateTime(reimbursement.getCostCreateTime());
				AR.setCostItem(reimbursement.getCostItem());
				AR.setCostTotal(reimbursement.getCostTotal());
				AR.setCostType(reimbursement.getCostType());
				AR.setReimbursementId(reimbursement.getReimbursementId());
				AR.setReimbursementIsread(reimbursement.getReimbursementIsread());
				AR.setReimbursementRefuse(reimbursement.getReimbursementRefuse());
				AR.setReimbursementRespon(reimbursement.getReimbursementRespon());
				AR.setReimbursementState(reimbursement.getReimbursementState());
				ARList.add(AR);
				reimbursementMapper.updateState(reimbursement.getReimbursementId());
			}

			return gson.toJson(ARList);
		}
	}

	/**
	 * 
	 * <p>
	 * 负责完成对报销单的审核
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月2日
	 */

	@Override
	public String approvalRei(HttpServletRequest request) {
		Reimbursement rei = new Reimbursement();
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + request.getParameter("appro"));
		rei.setReimbursementId(request.getParameter("reimbursementId"));

		String str = request.getParameter("appro");
		if (str.equals("0")) {
			str = "拒绝";
		} else {
			str = "同意";
		}

		rei.setReimbursementState(str);

		rei.setReimbursementIsread("0");
		rei.setCostAuditorId((String) request.getSession().getAttribute("ACCOUNT"));

		int i = reimbursementMapper.updateByPrimaryKeySelective(rei);

		return i > 0 ? "SUCCESS" : "ERROR";
	}

	/**
	 * 
	 * <p>
	 * 查询员工信息
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月4日
	 */

	@Override
	public String seeLeaveAccountInfo(HttpServletRequest request) {

		List<AccountLeaveInfo> accList = new ArrayList<>();
		AccountLeaveInfo acc = new AccountLeaveInfo();
		SystemAccount sys = new SystemAccount();
		String accountId = request.getParameter("accountId");
		System.err.println(accountId + "###################################");
		SectionInfo sec = new SectionInfo();
		sec = sectionInfoMapper.seeLeaveAccountSection(accountId);
		sys = systemAccountMapper.selectByPrimaryKey(accountId);
		acc.setAccountName(sys.getAccountName());
		acc.setAccountNum(sys.getAccountNum());
		acc.setSectionName(sec.getSectionName());
		acc.setAccountSex(sys.getAccountSex());
		acc.setAccountLocation(sys.getAccountLocation());
		acc.setAccountPhone(sys.getAccountPhone());
		acc.setAccountEntryDate(sys.getAccountEntryDate());
		accList.add(acc);
		System.err.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
		return gson.toJson(accList);
	}

	/**
	 * 
	 * <p>
	 * 请假人查询所有未读信息条数
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@Override
	public int seeLeaveCountNotRead(HttpServletRequest request) {
		State sta = new State();
		sta.setAccountId((String) request.getSession().getAttribute("ACCOUNT"));
		int i = leaveInfoMapper.queryNotReadLeaveCount(sta);
		return i;
	}

	/**
	 * 
	 * <p>
	 * 请假人审核人查询所有没有审批的强假条数量，不包括自己的
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@Override
	public int queryAllNotApprovalLeaveCount(HttpServletRequest request) {
		State sta = new State();
		sta.setAccountId((String) request.getSession().getAttribute("ACCOUNT"));
		int i = leaveInfoMapper.queryAllNotReadLeaveCount(sta);
		return i;
	}

	/**
	 * 
	 * <p>
	 * 报销人查询未读信息
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@Override
	public int queryReiCount(HttpServletRequest request) {
		Reimbursement rei = new Reimbursement();
		rei.setBxPersonId((String) request.getSession().getAttribute("ACCOUNT"));
		String bxPersonI = rei.getBxPersonId();
		int i = reimbursementMapper.queryNotApprovalCount(bxPersonI);
		return i;
	}

	/**
	 * 
	 * <p>
	 * 报销审核人查询未读信息条数
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@Override
	public int queryAllCountRei(HttpServletRequest request) {
		int i = reimbursementMapper.queryAllNotApprovalCount((String) request.getSession().getAttribute("ACCOUNT"));
		return i;
	}

	/**
	 * 
	 * <p>
	 * 请假人修改信息
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@Override
	public LeaveInfo selectLeaveById(HttpServletRequest request) {

		return leaveInfoMapper.selectByPrimaryKey(request.getParameter("leaveInfoId"));
	}

	/**
	 * 
	 * <p>
	 * 修改请假单
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@Override
	public String xiuGaiLeave(HttpServletRequest request) {
		LeaveInfo lea = new LeaveInfo();

		lea.setLeaveInfoReason(request.getParameter("leaveInfoReason"));
		lea.setLeaveInfoType(request.getParameter("leaveInfoType"));
		lea.setLeaveStartTime(request.getParameter("leaveStartTime"));
		lea.setLeaveEndTime(request.getParameter("leaveEndTime"));
		lea.setLeaveInfoId(request.getParameter("leaveInfoId"));
		lea.setLeaveCreateTime(new Date());
		lea.setLeaveIsread("1");
		lea.setLeaveInfoState("未审批");
		lea.setRefuseReason("正在审核");
		int i = leaveInfoMapper.updateByPrimaryKeySelective(lea);
		return i > 0 ? "success" : "n";
	}

	/**
	 * 
	 * <p>
	 * 修改报销单UI
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@Override
	public Reimbursement selectReiById(HttpServletRequest request) {

		return reimbursementMapper.selectByPrimaryKey(request.getParameter("reimbursementId"));
	}

	/**
	 * 
	 * <p>
	 * 修改报销单
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@Override
	public String updateRei(HttpServletRequest request) {
		Reimbursement rei = new Reimbursement();
		rei.setReimbursementId(request.getParameter("reimbursementId"));
		rei.setCostItem(request.getParameter("costItem"));
		rei.setReimbursementRespon(request.getParameter("reimbursementRespon"));
		rei.setCostType(request.getParameter("costType"));
		rei.setCostTotal(request.getParameter("costTotal"));
		rei.setReimbursementIsread("1");
		rei.setReimbursementRefuse("正在审核");
		rei.setCostCreateTime(new Date());
		rei.setReimbursementState("未审批");
		int i = reimbursementMapper.updateByPrimaryKeySelective(rei);

		return i > 0 ? "SUCCESS" : "n";
	}

	/**
	 * 
	 * <p>
	 * 删除假条
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@Override
	public String delLeave(HttpServletRequest request) {
		int i = leaveInfoMapper.deleteByPrimaryKey(request.getParameter("leaveInfoId"));
		return i > 0 ? "SUCCESS" : "ERROR";
	}

	/**
	 * 
	 * <p>
	 * 删除报销
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@Override
	public String delRei(HttpServletRequest request) {
		int i = reimbursementMapper.deleteByPrimaryKey(request.getParameter("reimbursementId"));
		return i > 0 ? "SUCCESS" : "ERROR";
	}

	/**
	 * 
	 * <p>
	 * 查看详细信息
	 * </p>
	 * 
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	@Override
	public String chakan(HttpServletRequest request) {
		Reimbursement rei = new Reimbursement();
		List<ApprovalReimbursement> arList = new ArrayList<ApprovalReimbursement>();
		ApprovalReimbursement ar = new ApprovalReimbursement();
		rei = reimbursementMapper.selectByPrimaryKey(request.getParameter("reimbursementId"));
		ar.setAccountName(systemAccountMapper.selectByPrimaryKey(rei.getBxPersonId()).getAccountName());
		ar.setCostItem(rei.getCostItem());
		ar.setReimbursementRespon(rei.getReimbursementRespon());
		ar.setCostType(rei.getCostType());
		ar.setCostTotal(rei.getCostTotal());
		ar.setCostCreateTime(rei.getCostCreateTime());
		ar.setReimbursementState(rei.getReimbursementState());
		ar.setAccountName1(systemAccountMapper.selectByPrimaryKey(rei.getCostAuditorId()).getAccountName());
		arList.add(ar);
		return gson.toJson(arList);
	}
}
