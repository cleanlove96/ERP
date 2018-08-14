package com.listener;

import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
public class VisitorCountLintener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO 自动生成的方法存根
		ServletContext sc=se.getSession().getServletContext();
		Integer c=(Integer) sc.getAttribute("VisitorCount");
		c++;
		sc.setAttribute("VisitorCount", c);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO 自动生成的方法存根
		ServletContext sc=se.getSession().getServletContext();
		String accountId=(String) se.getSession().getAttribute("ACCOUNT");
		Map<String,String> loginMap=(Map<String, String>) sc.getAttribute("loginMap");
		System.err.println("----------------------退出:"+accountId);
		System.err.println("-------------------loginMap---退出:"+loginMap);
		
		if(loginMap!=null) {
			loginMap.remove(accountId);
			System.err.println("已经退出了");
			sc.setAttribute("loginMap", loginMap);
		}
		Integer c=(Integer) sc.getAttribute("VisitorCount");
		c--;
		if(c<0) {
			c=0;
		}
		sc.setAttribute("VisitorCount", c);
	}

}
