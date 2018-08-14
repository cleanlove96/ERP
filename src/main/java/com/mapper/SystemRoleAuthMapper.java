package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.SystemRoleAuth;

public interface SystemRoleAuthMapper {
    int deleteByPrimaryKey(String roleAuthId);

    int insert(SystemRoleAuth record);

    int insertSelective(SystemRoleAuth record);

    SystemRoleAuth selectByPrimaryKey(String roleAuthId);

    int updateByPrimaryKeySelective(SystemRoleAuth record);

    int updateByPrimaryKey(SystemRoleAuth record);
    
    SystemRoleAuth selectByAccountIdAndAuthId(Map<String, String> map);

	int deleteByAuthId(String authId);
	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年6月25日
	 * @param roleId
	 * @return
	 */
	List<SystemRoleAuth> selectByRoleId(String roleId);

	/**
	 *通过roleId 删除
	 * @param roleId
	 */
	void deleteByRoleId(String roleId);

}