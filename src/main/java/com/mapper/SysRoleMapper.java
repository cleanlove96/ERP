package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.SysRole;

import com.pojo.SystemRolePojo;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

	List<SysRole> selectAllRoleWithAccount();

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年6月26日
	 * @return
	 */
	List<SysRole> selectAllRole(String id);

	String selectNameBySalaryId(String id);

	String selectAccountNameBySalaryId(String id);
	
	List<SystemRolePojo> getRoleTable(Map<String, Object> map);

	int getTotalNum(String string);

	SysRole selectByRoleName(String roleName);
}