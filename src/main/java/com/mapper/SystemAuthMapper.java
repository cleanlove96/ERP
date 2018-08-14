package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.SystemAuth;

public interface SystemAuthMapper {
    int deleteByPrimaryKey(String authId);

    int insert(SystemAuth record);

    int insertSelective(SystemAuth record);

    SystemAuth selectByPrimaryKey(String authId);

    int updateByPrimaryKeySelective(SystemAuth record);

    int updateByPrimaryKey(SystemAuth record);
    
    List<SystemAuth> selectByAccountIdAndGroupId(Map<String, String> map);
    
    List<SystemAuth> selectByHref(String href);

	List<SystemAuth> selectAll();

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年6月25日
	 * @return
	 */
	List<SystemAuth> selectAuthAll();


}