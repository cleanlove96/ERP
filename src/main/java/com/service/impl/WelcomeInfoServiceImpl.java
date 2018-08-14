package com.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mapper.SystemAccountMapper;
import com.model.SystemAccount;
import com.service.AccountService;
import com.service.WelcomeInfoService;

@Service
public class WelcomeInfoServiceImpl implements WelcomeInfoService {
	
	@Resource
	private AccountService as;
	@Resource
	private SystemAccountMapper sam;
	
	private SimpleDateFormat sdf=new SimpleDateFormat("YYYY年MM月dd日hh时mm分ss秒");

	@Override
	public void getStaticInfo(HttpServletRequest request) {
		// TODO 自动生成的方法存根
		String accountId=(String)request.getSession().getAttribute("ACCOUNT");
		request.setAttribute("loginId", as.getLoginIdById(accountId));
		SystemAccount sa=sam.selectByPrimaryKey(accountId);
		request.setAttribute("name", sa.getAccountName());
		Date date=(Date)request.getSession().getAttribute("loginTime");
		request.setAttribute("loginTimeFormat", sdf.format(date));
		request.setAttribute("accountAddr", request.getRemoteAddr());
		try {
			InetAddress inet = InetAddress.getLocalHost();
			request.setAttribute("serverName", inet.getHostName());
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			request.setAttribute("serverName", "获取失败");
		}
		request.setAttribute("SessionTimeOut", request.getSession().getMaxInactiveInterval());
	}

	@Override
	public String getServerTime() {
		// TODO 自动生成的方法存根
		Date d=new Date();
		return sdf.format(d);
	}

	@Override
	public String getVisitorCount(HttpServletRequest request) {
		// TODO 自动生成的方法存根
		return request.getServletContext().getAttribute("VisitorCount")+"";
	}

}
