package com.aspect;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.mapper.SystemLoginLogMapper;
import com.model.SystemLoginLog;
import com.service.AccountService;

@Aspect
@Component
public class LoginLogAspect {
	
	@Resource
	private SystemLoginLogMapper sllm;
	@Resource
	private AccountService as;
	
	private SimpleDateFormat sdf=new SimpleDateFormat("YYYY年MM月dd日 hh:mm:ss");
	
	@AfterReturning(value="execution(* com.service.AccountService.doLogin(..))",returning="result")
	public void doLoginLog(JoinPoint jp,String result) {
		if(result.equals("SUCCESS")) {
			Object[] args=jp.getArgs();
			HttpServletRequest request=(HttpServletRequest) args[0];
			String username = request.getParameter("username");
			Thread t=new Thread(()->{
				Date d=new Date();
				SystemLoginLog sll=new SystemLoginLog();
				sll.setLoginLogId(UUID.randomUUID().toString());
				sll.setAccountLoginId(username);
				sll.setLoginLogDate(sdf.format(d));
				sll.setLoginLogType("登录");
				sllm.insert(sll);
			});
			t.start();
		}
		
	}
	
	@Before("execution(* com.service.AccountService.doLogout(..))")
	public void doLogoutLog(JoinPoint jp) {
		Object[] args=jp.getArgs();
		HttpServletRequest request=(HttpServletRequest) args[0];
		String accountId=(String) request.getSession().getAttribute("ACCOUNT");
		Thread t=new Thread(()-> {
			SystemLoginLog sll=new SystemLoginLog();
			sll.setLoginLogId(UUID.randomUUID().toString());
			sll.setAccountLoginId(as.getLoginIdById(accountId));
			Date d=new Date();
			sll.setLoginLogDate(sdf.format(d));
			sll.setLoginLogType("注销");
			sllm.insert(sll);
		});
		t.start();
	}

}
