package com.mapper;

import java.util.Map;

import com.model.WorkdayRecord;

public interface WorkdayRecordMapper {
    int deleteByPrimaryKey(String workdayRecordId);

    int insert(WorkdayRecord record);

    int insertSelective(WorkdayRecord record);

    WorkdayRecord selectByPrimaryKey(String workdayRecordId);

    int updateByPrimaryKeySelective(WorkdayRecord record);

    int updateByPrimaryKey(WorkdayRecord record);

	int selectWorkDayByAccountId(Map<String, String> map);

	int selectIsClock(Map<String, String> map);

}