package com.mapper;

import com.model.SystemLoginLog;

public interface SystemLoginLogMapper {
    int deleteByPrimaryKey(String loginLogId);

    int insert(SystemLoginLog record);

    int insertSelective(SystemLoginLog record);

    SystemLoginLog selectByPrimaryKey(String loginLogId);

    int updateByPrimaryKeySelective(SystemLoginLog record);

    int updateByPrimaryKey(SystemLoginLog record);
}