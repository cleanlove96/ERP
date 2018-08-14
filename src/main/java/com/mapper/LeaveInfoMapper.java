package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.LeaveInfo;
import com.pojo.State;

public interface LeaveInfoMapper {
    int deleteByPrimaryKey(String leaveInfoId);

    int insert(LeaveInfo record);

    int insertSelective(LeaveInfo record);

    LeaveInfo selectByPrimaryKey(String leaveInfoId);

    int updateByPrimaryKeySelective(LeaveInfo record);

    int updateByPrimaryKey(LeaveInfo record);

	List<LeaveInfo> queryCompleteLeave(Map<String, Object> map);

	List<LeaveInfo> queryNotRead(Map<String, Object> map);

	List<LeaveInfo> queryNotCompleteLeave(Map<String, Object> map);

	List<LeaveInfo> queryNotAllLeave(Map<String, Object> map);

	List<LeaveInfo> queryApprovalAllLeave(Map<String, Object> map);

	List<LeaveInfo> queryNotReadAllLeave(Map<String, Object> map);

	int queryNotReadLeaveCount(State sta);

	int queryCompleteLeaveCount(State sta);

	int queryNotCompleteLeaveCount(State sta);

	List<LeaveInfo> queryWaitCompleteLeave(Map<String, Object> map);

	int queryAllNotReadLeaveCount(State sta);

	int queryAllCompleteLeaveCount(State sta);

	int queryAllNotCompleteLeaveCount(State sta);

	void updateIsState(String leaveInfoId);

	void updateState(String leaveInfoId);

	

	
	
}