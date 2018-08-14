package com.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.AccountRoleMapper;
import com.mapper.IndividualSalaryMapper;
import com.mapper.SectionInfoMapper;
import com.mapper.SectionRoleMapper;
import com.mapper.SysRoleMapper;
import com.mapper.SystemAccountMapper;
import com.model.AccountRole;
import com.model.IndividualSalary;
import com.model.SectionInfo;
import com.model.SectionRole;
import com.model.SysRole;
import com.model.SystemAccount;
import com.pojo.AccountLoginInfo;
import com.pojo.AccountPage;
import com.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	/**
	 * 分页时，每页显示的数量
	 */
	private static final int numAccPage = 5;
	@Resource
	private SystemAccountMapper sam;

	private Gson g = new Gson();

	@Resource
	private SysRoleMapper srm;

	@Resource
	private AccountRoleMapper arm;
	
	@Resource
	private SectionInfoMapper sim;
	
	@Resource
	private SectionRoleMapper secrm;
	
	@Resource
	private IndividualSalaryMapper ism;

	@Override
	public String doLogin(HttpServletRequest request, HttpServletResponse response) {
		// TODO 自动生成的方法存根
		String username = request.getParameter("username");
		SystemAccount sa = sam.selectByLoginId(username);
		if (sa != null) {
			String password = request.getParameter("password");
			System.out.println("----------"+password);
			if (password.equals(sa.getAccountLoginPwd())) {
				if (sa.getAccountStatus().equals("ON")) {
					
					//------------------------
					ServletContext sc=request.getServletContext();
					Map<String,String> map=(Map<String, String>) sc.getAttribute("loginMap");
					if(map!=null) {
						System.out.println("登录验证map:"+map);
						if(map.get(sa.getAccountId())!=null) {
							System.err.println("已经有人登录此号");
							return "REPEITION_ERROR";
						}
						System.err.println("还没有人登录");
					}else {
						map=new HashMap<>();
						map.put(sa.getAccountId(), "1");
						System.err.println("还没有人登录");
						sc.setAttribute("loginMap",map);
					}
					
					//------------------------
					request.getSession().setAttribute("ACCOUNT", sa.getAccountId());
					// 记住账户操作
					String rember = request.getParameter("rember");
					if (rember.equals("t")) {
						Cookie re = new Cookie("ERPSystem", sa.getAccountId());// 设置一个新的Cookie
						re.setMaxAge(30 * 24 * 60 * 60);// 记住30天
						response.addCookie(re);// 写入一个新的Cookie
					}
					Date d = new Date();
					request.getSession().setAttribute("loginTime", d);
					
					
					return "SUCCESS";
				} else {
					return "STATUS_ERROR";
				}
			} else {
				return "PWD_ERROR";
			}
		} else {
			return "ACCOUNT_NOT_FOUND";
		}
	}

	@Override
	public void doLogout(HttpServletRequest request) {
		// TODO 自动生成的方法存根
		request.getSession().invalidate();// 注销Session
		
	}

	@Override
	public String getRember(HttpServletRequest request) {
		// TODO 自动生成的方法存根
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("ERPSystem")) {
				SystemAccount sa = sam.selectByPrimaryKey(c.getValue());// 由Cookie中的主键得到用户信息
				AccountLoginInfo ali = new AccountLoginInfo();// 用于封装登录信息的pojo对象
				ali.setUsername(sa.getAccountLoginId());
				ali.setPassword(sa.getAccountLoginPwd());
				if (sa != null) {
					return g.toJson(ali);// 以Json格式发送回去
				}
			}
		}
		return "NULL";// 没有此信息返回NULL标识
	}

	@Override
	public void RemoveRember(HttpServletRequest request, HttpServletResponse response) {
		// TODO 自动生成的方法存根
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("ERPSystem")) {
				c.setMaxAge(0);
				response.addCookie(c);// 将Cookie生命置为0后要重新发送回去以生效
				break;
			}
		}
	}

	@Override
	public String getLoginIdById(String id) {
		// TODO 自动生成的方法存根
		SystemAccount sa = sam.selectByPrimaryKey(id);
		if (sa != null) {
			return sa.getAccountLoginId();
		} else {
			return null;
		}
	}

	@Override
	public String getAccountTable(HttpServletRequest request) {
		// TODO 自动生成的方法存根
		String n = request.getParameter("n");
		String searchCard = request.getParameter("searchCard");
		if (searchCard == null) {
			searchCard = "";
		}
		int anum = 1;
		if (n != null && n.length() > 0) {
			anum = Integer.parseInt(n);
		}
		Map<String, Object> map = new HashMap<>();
		int start = (anum - 1) * numAccPage;
		map.put("start", start);
		map.put("size", numAccPage);
		map.put("searchCard", "%" + searchCard + "%");
		List<SystemAccount> atable = sam.getAccountTable(map);
		return g.toJson(atable);
	}

	@Override
	public String getPage(HttpServletRequest request) {
		AccountPage apage = new AccountPage();
		String searchCard = request.getParameter("searchCard");
		if (searchCard == null) {
			searchCard = "";
		}
		int tn = sam.selectCount("%" + searchCard + "%");
		apage.setTotalRecords(tn + "");
		int t1 = tn % numAccPage;
		int t = tn / numAccPage;
		if (t1 > 0) {
			t = t + 1;
		}
		apage.setTotal(t + "");
		return g.toJson(apage);
	}

	@Override
	public String updateAccountStatus(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String accountId = request.getParameter("id");
		String accountStatus = request.getParameter("status");
		if (accountStatus.equals("ON")) {
			Map<String, Object> map = new HashMap<>();
			map.put("accountStatus", "OFF");
			map.put("accountId", accountId);
			int numm = sam.updateStatusByAccountId(map);
			System.out.println("修改成功不？" + numm);
			if (numm > 0) {
				return g.toJson("OFF");
			} else {
				return g.toJson("ON");
			}

		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("accountStatus", "ON");
			map.put("accountId", accountId);
			int numm = sam.updateStatusByAccountId(map);
			System.out.println("修改成功不？" + numm);
			if (numm > 0) {
				return g.toJson("ON");
			} else {
				return g.toJson("OFF");
			}
		}

	}

	@Override
	public String resetPwdByAccountId(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String accountId = request.getParameter("id");
		int num = sam.resetPwdByAccountId(accountId);
		if (num > 0) {
			return "success";
		} else {
			return "error";
		}

	}

	@Override
	public String selectAllRoleWithAccount() {
		// TODO Auto-generated method stub
		List<SysRole> roleTable = srm.selectAllRoleWithAccount();
		return g.toJson(roleTable);
	}

	@Override
	public String accountAdd(HttpServletRequest request) {
		// TODO Auto-generated method stub
		SystemAccount sa = new SystemAccount();
		AccountRole ar = new AccountRole();
		sa.setAccountId(UUID.randomUUID().toString());
		sa.setAccountLoginId(request.getParameter("accountLoginId"));
		sa.setAccountNum(request.getParameter("accountLoginId"));
		sa.setAccountLoginPwd(request.getParameter("accountLoginPwd"));
		sa.setAccountName(request.getParameter("accountName"));
		sa.setAccountSex(request.getParameter("accountSex"));
		sa.setAccountStatus(request.getParameter("accountStatus"));
		sa.setAccountPhone(request.getParameter("accountPhone"));
		sa.setAccountIdcard(request.getParameter("accountIdcard"));
		sa.setAccountEntryDate(request.getParameter("accountEntryDate"));
		sa.setAccountEduLevel(request.getParameter("accountEduLevel"));
		sa.setAccountLocation(request.getParameter("accountLocation"));
		int num1 = sam.insertSelective(sa);
//		插入员工单人的薪资调整表数据
		IndividualSalary is = new IndividualSalary();
		is.setIndividualSalaryId(UUID.randomUUID().toString());
		is.setAccountId(sa.getAccountId());
//		初始化金额为0
		is.setAdjustMoney((double)0);
		is.setAdjustTime(new Date());
		ism.insertSelective(is);
//		end 插入
		if (num1 > 0) {
			ar.setAccountRoleId(UUID.randomUUID().toString());
			ar.setAccountId(sa.getAccountId());
			ar.setRoleId(request.getParameter("roleId"));
			int num2 = arm.insertSelective(ar);
			if (num2 > 0) {
				return "success";
			} else {
				return "notsuccess";
			}
		} else {
			return "error";
		}

	}

	@Override
	public SystemAccount selsectAccountByAccountId(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String accountId = request.getParameter("accountId");
		SystemAccount saccount = sam.selectByPrimaryKey(accountId);
		return saccount;
	}

	@Override
	public String selectRoleByAccountId(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String accountId = request.getParameter("accountId");
		AccountRole arole = arm.selectRoleAllByAccountId(accountId);
		if (arole != null) {
			String roleId = arole.getRoleId();
			if (roleId != null && !roleId.equals("")) {
				return roleId;
			} else {
				return "";
			}
		} else {
			return "";
		}

	}

	@Override
	public String updateAccount(SystemAccount account, String roleId) {
		// TODO Auto-generated method stub
		int n = sam.updateByPrimaryKeySelective(account);
		AccountRole arole = arm.selectRoleAllByAccountId(account.getAccountId());
		AccountRole ar = new AccountRole();
		String accountRoleId = "";
		if (arole == null) {
			accountRoleId = UUID.randomUUID().toString();
			ar.setAccountId(account.getAccountId());
			ar.setRoleId(roleId);
			ar.setAccountRoleId(accountRoleId);
			int num2 = arm.insertSelective(ar);
			if (num2 > 0) {
				return "success";
			} else {
				return "notsuccess";
			}

		}else {
			accountRoleId=arole.getAccountRoleId();
			ar.setAccountId(account.getAccountId());
			ar.setRoleId(roleId);
			ar.setAccountRoleId(accountRoleId);
			int num1 = arm.updateByPrimaryKeySelective(ar);
			if (num1 > 0) {
				return "success";
			} else {
				return "notsuccess";
			}
		}
		
		

	}

	@Override
	public String selectAccountByAccountId(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String accountLoginId= request.getParameter("accountLoginId");
		SystemAccount saccount=sam.selectByLoginId(accountLoginId);
		if(saccount==null) {
			return "yousuccess";
		}else {
			return "younotsuccess";
		}

	}
	/**
	 * 通过传入的角色id，查找出相应部门的所有信息
	 * @author lily
	 */
	@Override
	public SectionInfo selectSectionByRoleId(String roleId) {
		// TODO Auto-generated method stub
		SectionRole mysecrole=secrm.selsectSectionRoleAllByRoleId(roleId);
		String sectionId="";
		if(mysecrole!=null) {
			sectionId=mysecrole.getSectionId();
		}
		SectionInfo ainfo=sim.selectByPrimaryKey(sectionId);
		return ainfo;
	}
	/**
	 * 根据传入的员工id，查询出对应的角色列表
	 * @author lily
	 */
	@Override
	public SysRole selectRoleAllInfoByAccountId(String accountId) {
		// TODO Auto-generated method stub
		 AccountRole arole=arm.selectRoleAllByAccountId(accountId);
		 String roleId="";
		 if(arole!=null) {
			 roleId=arole.getRoleId();
		 }
		 SysRole srole=srm.selectByPrimaryKey(roleId);
		return srole;
	}
	/**
	 * 查询出登录者的信息
	 * @author lily
	 */
	@Override
	public SystemAccount selsectAccountAllByAccountId(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String accountId=(String)request.getSession().getAttribute("ACCOUNT");
		SystemAccount sacount=sam.selectByPrimaryKey(accountId);
		return sacount;
	}


	@Override
	public List<SystemAccount> getAllAccountByRole(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public String updateNewPassword(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String accountId=(String)request.getSession().getAttribute("ACCOUNT");
		String newpass= request.getParameter("newpass");
		Map<String, Object> map = new HashMap<>();
		map.put("accountId", accountId);
		map.put("accountLoginPwd", newpass);
		int num=sam.updateNewPasswordByAccountId(map);
		if(num>0) {
			return "success";
		}else {
			return "error";
		}
		
	}

	@Override
	public String updateMyAccount(HttpServletRequest request,SystemAccount account) {
		// TODO Auto-generated method stub
		String accountId=(String)request.getSession().getAttribute("ACCOUNT");
		String accountPhone= request.getParameter("accountPhone");
		String accountIdcard= request.getParameter("accountIdcard");
		String accountLocation= request.getParameter("accountLocation");
		Map<String, Object> map = new HashMap<>();
		map.put("accountId", accountId);
		map.put("accountPhone", accountPhone);
		map.put("accountIdcard", accountIdcard);
		map.put("accountLocation", accountLocation);
		int num=sam.updateMyAccount(map);
		if(num>0) {
			return "success";
		}else {
			return "error";
		}
		
	}




}
