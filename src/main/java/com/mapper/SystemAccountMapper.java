package com.mapper;


import java.util.List;
import java.util.Map;

import com.model.SystemAccount;

public interface SystemAccountMapper {
    int deleteByPrimaryKey(String accountId);

    int insert(SystemAccount record);

    int insertSelective(SystemAccount record);

    SystemAccount selectByPrimaryKey(String accountId);

    int updateByPrimaryKeySelective(SystemAccount record);

    int updateByPrimaryKey(SystemAccount record);
    
    SystemAccount selectByLoginId(String loginId);
    
    List<SystemAccount> getAccountTable(Map<String, Object> map);

	Integer selectCount(String searchCard);

	int updateStatusByAccountId(Map<String, Object> map);

	int resetPwdByAccountId(String accountId);


	/**
	 * @author FSK
	 * @Date 2018年6月27日
	 * @param roleId
	 * @return
	 */
	SystemAccount selectByRoleId(String roleId);


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


	int updateNewPasswordByAccountId(Map<String, Object> map);
	/**
	 * 
	 * 
	 *<p>修改自己的电话号码，地址，身份证等</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 
	 */
	int updateMyAccount(Map<String, Object> map);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年6月27日
	 * @param id
	 * @param pId
	 */
	int updatePidAndId(Map<String, String> map);
//	获取上月某部门出勤员工信息
	List<SystemAccount> selectBySectionWorkTime(Map<String, String> map);

	List<SystemAccount> selectAllWorkTime(Map<String, String> map);

	


	
}