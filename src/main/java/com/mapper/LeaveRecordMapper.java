package com.mapper;

import com.model.LeaveRecord;

public interface LeaveRecordMapper {
    int deleteByPrimaryKey(String leaveRecordId);

    int insert(LeaveRecord record);

    int insertSelective(LeaveRecord record);

    LeaveRecord selectByPrimaryKey(String leaveRecordId);

    int updateByPrimaryKeySelective(LeaveRecord record);

    int updateByPrimaryKey(LeaveRecord record);
}