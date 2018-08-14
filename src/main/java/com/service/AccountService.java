package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.SectionInfo;
import com.model.SysRole;
import com.model.SystemAccount;

/**
 * 
 * <h2>进行用户信息处理服务</h2>
 * <p>
 * 该服务层包含了所有对于用户信息的逻辑处理。
 * </p>
 * 
 * @author zm
 * @version 1.0
 */
public interface AccountService {

	/**
	 * 
	 * <h2>根据传入参数判定用户是否可以登录</h2>
	 * <p>
	 * 该服务层从数据库查出用户信息并判定，返回判定结果标识符。同时负责进行记住账户的操作。
	 * </p>
	 * 
	 * @author zm
	 * @param request
	 *            HttpServletRequest 请求对象
	 * @return String 标识符
	 *         <p>
	 *         登录判定符包含以下几种：
	 *         </p>
	 *         <ul>
	 *         <li>SUCCESS 允许登录</li>
	 *         <li>ACCOUNT_NOT_FOUND 用户不存在</li>
	 *         <li>PWD_ERROR 密码错误</li>
	 *         <li>STATUS_ERROR 状态错误</li>
	 *         </ul>
	 */
	String doLogin(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 
	 * <h2>销毁Session从而实现注销</h2>
	 * <p>
	 * 清理所有用户相关信息以结束会话跟踪。
	 * </p>
	 * 
	 * @author zm
	 * @param request
	 *            HttpServletRequest 请求对象
	 */
	void doLogout(HttpServletRequest request);

	/**
	 * 
	 * <h2>根据Cookie获取用户登录信息</h2>
	 * <p>
	 * 从Cookie中取得指定的登录信息，封装为JSON并返回，当无此信息时，返回“NULL”。
	 * </p>
	 * 
	 * @author zm
	 * @param request
	 *            HttpServletRequest 请求对象
	 * @return JSON内容或NULL
	 */
	String getRember(HttpServletRequest request);

	/**
	 * 
	 * <h2>删除记住的账户信息</h2>
	 * <p>
	 * 通过将Cooike（如果有的话）的声明设为0秒达到删除一个Cookie的目的。
	 * </p>
	 * 
	 * @author zm
	 * @param request
	 *            HttpServletRequest 请求对象
	 * @param response
	 *            HttpServletResponse 响应对象
	 */
	void RemoveRember(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 
	 * <h2>根据主键获取用户的登录名</h2>
	 * <p>
	 * 查询出对应主键的用户名。
	 * </p>
	 * 
	 * @author zm
	 * @param id
	 *            String 用户主键
	 * @return String 获得用户登录名，如果该主键没有对应的用户信息，返回null
	 */
	String getLoginIdById(String id);

	/**
	 * 
	 * 
	 * <p>
	 * 该方法是用实现查询所有员工表使用的，进行了分页查询
	 * </p>
	 * 
	 * @author lily
	 * @param request
	 * @param 传入的参数
	 * @return 返回值类型
	 *
	 */
	String getAccountTable(HttpServletRequest request);

	/**
	 * 
	 * 
	 * <p>
	 * 为了计算出员工表的分页信息
	 * </p>
	 * 
	 * @author lily
	 * @param 传入的参数
	 * @return 返回值类型
	 *
	 */
	String getPage(HttpServletRequest request);

	/**
	 * 
	 * 
	 * <p>
	 * 通过用户Id修改其状态
	 * </p>
	 * 
	 * @author lily
	 * @param 传入的参数
	 * @return String ON或者OFF
	 *
	 */
	String updateAccountStatus(HttpServletRequest request);

	/**
	 * 
	 * 
	 * <p>
	 * 根据ID重置密码为：000000
	 * </p>
	 * 
	 * @author lily
	 * @param 传入的参数
	 * @return 返回值类型
	 *
	 */
	String resetPwdByAccountId(HttpServletRequest request);

	/**
	 * 
	 * 
	 * <p>
	 * 查看所有职位（角色）
	 * </p>
	 * 
	 * @author lily
	 * @param 传入的参数
	 * @return 返回值类型
	 *
	 */
	String selectAllRoleWithAccount();

	/**
	 * 
	 * 
	 * <p>
	 * 增加员工数据，并分配其职位,增加员工与角色中间表信息
	 * </p>
	 * 
	 * @author lily
	 * @param 传入的参数
	 * @return 返回值类型
	 *
	 */
	String accountAdd(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>根据用户ID查询用户全部信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	SystemAccount selsectAccountByAccountId(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>根据用户Id查询出对应的职位信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectRoleByAccountId(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>根据前台传入的数据修改员工的信息和职位，如果开始没有分配职位，则加入一个职位，如果已经存在了，则修改中间连接表的信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String updateAccount(SystemAccount account, String roleId);
	/**
	 * 
	 * 
	 *<p>根据前台传入的工号查看是否有这个工号</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String selectAccountByAccountId(HttpServletRequest request);


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

	/**
	 * 
	 * 
	 *<p>根据角色的id，查询出该角色所属部门</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	SectionInfo selectSectionByRoleId(String roleId);
	/**
	 * 
	 * 
	 *<p>根据自己写在session里面的id，查询出自己的所有信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	SysRole selectRoleAllInfoByAccountId(String accountId);
	/**
	 * 
	 * 
	 *<p>根据登录的用户id，查询出该用户的所有信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	SystemAccount selsectAccountAllByAccountId(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>修改登录者的密码</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String updateNewPassword(HttpServletRequest request);
	/**
	 * 
	 * 
	 *<p>通过前台传入的，修改自己的信息</p>
	 *@author lily
	 * @param request 
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	String updateMyAccount(HttpServletRequest request, SystemAccount account);



}
