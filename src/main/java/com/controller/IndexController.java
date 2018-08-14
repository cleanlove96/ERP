package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.AccountService;
import com.service.AuthService;
import com.service.WelcomeInfoService;

/**
 * 
 * <h2>主页面控制器</h2>
 * <p>负责处理主页的所有请求，包括默认的欢迎页面内容。</p>
 * @author 青阳龙野(kohgylw)
 * @version 1.0
 */
@Controller
@RequestMapping("/indexController")
public class IndexController {
	
	@Resource
	private AccountService as;
	@Resource
	private WelcomeInfoService wis;
	@Resource
	private AuthService aser;
	
	/**
	 * 
	 * <h2>用于获取主页上要显示的登录名</h2>
	 * <p>从Session中得到登录用户的信息并返回登录名。如果获取失败返回“ERROR”。</p>
	 * @author 青阳龙野(kohgylw)
	 */
	@RequestMapping("/getAccountLoginId.ajax")
	public @ResponseBody String getAccountLoginId(HttpServletRequest request) {
		String loginId=as.getLoginIdById((String)request.getSession().getAttribute("ACCOUNT"));
		
		return loginId==null?"ERROR":loginId;
	}
	
	/**
	 * 
	 * <h2>进入欢迎页面</h2>
	 * <p>跳转入欢迎页面并把一些固定信息传入到页面上。</p>
	 * @author 青阳龙野(kohgylw)
	 * @param request HttpServletRequest 请求对象
	 */
	@RequestMapping("/showWelcome.do")
	public String showWelcome(HttpServletRequest request) {
		wis.getStaticInfo(request);
		return "/welcome.jsp";
	}
	
	/**
	 * 
	 * <h2>获取服务器当前时间</h2>
	 * <p>时间格式：YYYY年MM月dd日hh时mm分ss秒</p>
	 * @author 青阳龙野(kohgylw)
	 */
	@RequestMapping(value="/getServerTime.ajax",produces="text/html; charset=UTF-8")
	public @ResponseBody String getServerTime() {
		return wis.getServerTime();
	}
	
	/**
	 * 
	 * <h2>获取在线人数</h2>
	 * <p>请求当前记录的在线人数，该记录由Session数量决定。</p>
	 * @author 青阳龙野(kohgylw)
	 * @param request HttpServletRequest 请求对象
	 */
	@RequestMapping("/getVisitorCount.ajax")
	public @ResponseBody String getVisitorCount(HttpServletRequest request) {
		return wis.getVisitorCount(request);
	}
	
	/**
	 * 
	 * <h2>进入主页并显示相应的内容</h2>
	 * <p>进入主页并初始化要显示的例如权限按钮等组件。</p>
	 * @author 青阳龙野(kohgylw)
	 * @param request HttpServletRequest 请求对象
	 */
	@RequestMapping("/showIndexPage.do")
	public String showIndexPage(HttpServletRequest request) {
		request.setAttribute("leftButtons", aser.getAuthGroupPojo(request));
		return "/index.jsp";
	}

}
