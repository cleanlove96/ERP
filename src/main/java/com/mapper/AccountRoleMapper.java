package com.mapper;

import java.util.List;

import com.model.AccountRole;
import com.model.SystemAccount;

public interface AccountRoleMapper {
    int deleteByPrimaryKey(String accountRoleId);

    int insert(AccountRole record);

    int insertSelective(AccountRole record);

    AccountRole selectByPrimaryKey(String accountRoleId);

    int updateByPrimaryKeySelective(AccountRole record);

    int updateByPrimaryKey(AccountRole record);

	AccountRole selectRoleAllByAccountId(String accountId);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年6月26日
	 * @param roleId
	 * @return
	 */
	List<SystemAccount> getAllAccountByRole(String roleId);

	
}