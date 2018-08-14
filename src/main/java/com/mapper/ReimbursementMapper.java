package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Reimbursement;

public interface ReimbursementMapper {
    int deleteByPrimaryKey(String reimbursementId);

    int insert(Reimbursement record);

    int insertSelective(Reimbursement record);

    Reimbursement selectByPrimaryKey(String reimbursementId);

    int updateByPrimaryKeySelective(Reimbursement record);

    int updateByPrimaryKey(Reimbursement record);

	int queryWaitApprovalCount(String bxPersonI);

	int queryApprovalCount(String bxPersonI);

	int queryNotApprovalCount(String bxPersonI);

	List<Reimbursement> queryNotRead(Map<String, Object> map);

	List<Reimbursement> queryComplete(Map<String, Object> map);

	List<Reimbursement> queryNotComplete(Map<String, Object> map);

	int queryAllWaitApprovalCount(String bxPersonI);

	int queryAllApprovalCount(String bxPersonI);

	int queryAllNotApprovalCount(String bxPersonI);

	List<Reimbursement> queryAllNotRead(Map<String, Object> map);

	List<Reimbursement> queryAllComplete(Map<String, Object> map);

	List<Reimbursement> queryAllNotComplete(Map<String, Object> map);

	int queryMeiApprovalCount(String bxPersonI);

	List<Reimbursement> queryNotCompleteREI(Map<String, Object> map);

	void updateIsRead(String reimbursementId);

	void updateState(String reimbursementId);
}