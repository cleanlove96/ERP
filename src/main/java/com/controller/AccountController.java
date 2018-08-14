package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.SectionInfo;
import com.model.SysRole;
import com.model.SystemAccount;
import com.service.AccountService;

/**
 * 
 * <h2>用户控制器</h2>
 * <p>负责处理所有用户请求。</p>
 * @author zm
 * @version 1.0
 */
@Controller
@RequestMapping("/accountController")
public class AccountController {
	
	@Resource
	private AccountService as;
	
	/**
	 * 
	 * <h2>处理登录请求</h2>
	 * <p>根据传入的用户名和密码进行验证，控制是否可以登录。</p>
	 * @author zm
	 * @param request HttpServletRequest 请求对象
	 * @return String 登录判定符
	 * <p>登录判定符包含以下几种：</p>
	 * <ul>
	 * <li>SUCCESS 允许登录</li>
	 * <li>ACCOUNT_NOT_FOUND 用户不存在</li>
	 * <li>PWD_ERROR 密码错误</li>
	 * <li>STATUS_ERROR 状态错误</li>
	 * </ul>
	 */
	@RequestMapping("/doLogin.ajax")
	public @ResponseBody String doLogin(HttpServletRequest request,HttpServletResponse response) {
		return as.doLogin(request,response);
	}
	
	/**
	 * 
	 * <h2>执行注销</h2>
	 * <p>使Session失效，结束会话跟踪。</p>
	 * @author zm
	 * @param request HttpServletRequest 请求对象
	 */
	@RequestMapping("/doLogout.do")
	public String doLogout(HttpServletRequest request) {
		as.doLogout(request);
		return "/login.jsp";
	}
	
	/**
	 * 
	 * <h2>用于在初始化登录页面的时候，获取记住的账户</h2>
	 * <p>以JSON或NULL返回记住的登录状态。</p>
	 * @author zm
	 * @param request HttpServletRequest 请求对象
	 */
	@RequestMapping("/getRember.ajax")
	public @ResponseBody String getRember(HttpServletRequest request) {
		return as.getRember(request);
	}
	
	/**
	 * 
	 * <h2>清除记住的账户信息的</h2>
	 * <p>向此处发起请求会删除用于存储账户信息的Cookie。</p>
	 * @author zm
	 */
	@RequestMapping("/removeRember.ajax")
	public @ResponseBody String removeRember(HttpServletRequest request,HttpServletResponse response) {
		as.RemoveRember(request, response);
		return "SUCCESS";
	}
	
	/**
	 * 
	 * 
	 *<p>得到所有的员工信息表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	
	@RequestMapping(value="/getAccountTable.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getAccountTable(HttpServletRequest request) {

		return as.getAccountTable(request);

	}
	/**
	 * 
	 * 
	 *<p>为了计算员工信息分页</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/getPage.do")
	public @ResponseBody String getPage(HttpServletRequest request) {
		return as.getPage(request);
	}
	/**
	 * 
	 * 
	 *<p>把搜索的关键字放入session中，进行页面的刷新</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/doSearchCard.do")
	public String doSearchCard(HttpServletRequest request) {
		request.setAttribute("searchCard", request.getParameter("searchCard"));
		return "../view/account/account-list.jsp";
	}
	/**
	 * 
	 * 
	 *<p>通过用户id修改其状态</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/updateAccountStatus.ajax")
	public @ResponseBody String updateAccountStatus(HttpServletRequest request) {

		return as.updateAccountStatus(request);

	}
	/**
	 * 
	 * 
	 *<p>根据ID重置密码为：000000</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/resetPwd.ajax")
	public @ResponseBody String resetPwd(HttpServletRequest request) {

		return as.resetPwdByAccountId(request);

	}
	/**
	 * 
	 * 
	 *<p>查看所有的职位列表</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	//value="/selectAllRoleWithAccount.ajax",produces="text/html; charset=UTF-8"
	@RequestMapping(value="/selectAllRoleWithAccount.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectAllRoleWithAccount() {

		return as.selectAllRoleWithAccount();

	}
	/**
	 * 
	 * 
	 *<p>增加员工数据，并分配其职位,增加员工与角色中间表信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	
	@RequestMapping(value="/accountAdd.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String accountAdd(HttpServletRequest request) {
		
		return as.accountAdd(request);
	}	
	
	/**
	 * 
	 * 
	 *<p>根据用户Id，查询出他的信息,根据用户ID，查询出他的职位，查询出所有的职位，然后传入到前端页面</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/selsectAccountByAccountId.do")
	public String selsectAccountByAccountId(HttpServletRequest request) {
		SystemAccount saccount=as.selsectAccountByAccountId(request);
		String myRoleId=as.selectRoleByAccountId(request);
		request.setAttribute("myRoleId",myRoleId);	
		request.setAttribute("account", saccount);
		
		return "../view/account/account-update.jsp";
	}
	/**
	 * 
	 * 
	 *<p>根据前端传入的数据，修改员工信息和员工的职位</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/updateAccount.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String updateAccount(HttpServletRequest request,String roleId,SystemAccount account) {
	
		return as.updateAccount(account,roleId);
	}
	/**
	 * 
	 * 
	 *<p>根据前台传入的工号查看是否有这个工号</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/selectAccountByAccountId.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String selectAccountByAccountId(HttpServletRequest request) {
	
		return as.selectAccountByAccountId(request);
	}	
	/**
	 * 
	 *<p>根据用户的id，查询出他的信息，所属部门，担任的职位</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/selsectCenterByAccountId.do")
	public String selsectCenterByAccountId(HttpServletRequest request) {
		
		SystemAccount myAccount=as.selsectAccountAllByAccountId(request);
		String accountId=myAccount.getAccountId();
		SysRole myRole=as.selectRoleAllInfoByAccountId(accountId);
		String roleId="";
		String roleName="";
		if(myRole!=null) {
			roleId=myRole.getRoleId();
			roleName=myRole.getRoleName();
		}	
		
		SectionInfo mySection=as.selectSectionByRoleId(roleId);
		String sectionName="";
		if(mySection!=null) {
			sectionName=mySection.getSectionName();
		}
		//返回界面		
		request.setAttribute("myAccount", myAccount);
		request.setAttribute("roleName",roleName);
		request.setAttribute("sectionName",sectionName);
		return "../view/account/account-center.jsp";
	}
	/**
	 * 
	 * 
	 *<p>查询出登录者的信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/selectUpdateAccount.do")
	public String selectUpdateAccount(HttpServletRequest request) {
		SystemAccount myAccount=as.selsectAccountAllByAccountId(request);
		request.setAttribute("myAccount", myAccount);
		return "../view/account/update-password.jsp";
	}
	/**
	 * 
	 * 
	 *<p>修改登录者的密码</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/updateNewPassword.ajax",produces="text/html; charset=UTF-8")
	
	public @ResponseBody String updateNewPassword(HttpServletRequest request) {
	
		return as.updateNewPassword(request);
	}	
	/**
	 * 
	 * 
	 *<p>方法实现的功能</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping("/selectCenterAccount.do")
	public String selectCenterAccount(HttpServletRequest request) {
		SystemAccount myAccount=as.selsectAccountAllByAccountId(request);
		request.setAttribute("myAccount", myAccount);
		return "../view/account/update-center.jsp";
	}
	/**
	 * 
	 * 
	 *<p>根据前台传入的数据，修改登陆者信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	@RequestMapping(value="/updateMyAccount.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String updateMyAccount(HttpServletRequest request,SystemAccount account) {
	
		return as.updateMyAccount(request,account);
	}
}
