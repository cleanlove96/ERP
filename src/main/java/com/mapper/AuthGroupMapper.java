package com.mapper;


import java.util.List;

import com.model.AuthGroup;

public interface AuthGroupMapper {
    int deleteByPrimaryKey(String authGroupId);

    int insert(AuthGroup record);

    int insertSelective(AuthGroup record);

    AuthGroup selectByPrimaryKey(String authGroupId);

    int updateByPrimaryKeySelective(AuthGroup record);

    int updateByPrimaryKey(AuthGroup record);
    
    List<AuthGroup> selectAll();
}