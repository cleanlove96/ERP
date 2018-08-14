package com.mapper;

import com.model.AccountRole;

public interface SystemAccountRoleMapper {
    int deleteByPrimaryKey(String accountRoleId);

    int insert(AccountRole record);

    int insertSelective(AccountRole record);

    AccountRole selectByPrimaryKey(String accountRoleId);

    int updateByPrimaryKeySelective(AccountRole record);

    int updateByPrimaryKey(AccountRole record);
}