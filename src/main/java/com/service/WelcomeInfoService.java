package com.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * <h2>处理欢迎页面所有信息</h2>
 * <p>用于得到欢迎页面需要显示的信息的逻辑。</p>
 * @author 青阳龙野(kohgylw)
 * @version 1.0
 */
public interface WelcomeInfoService {
	
	/**
	 * 
	 * <h2>获取欢迎页面上的静态内容</h2>
	 * <p>在跳转进入欢迎页面的时候在请求对象中加入需要显示的静态信息。</p>
	 * @author 青阳龙野(kohgylw)
	 * @param request HttpServletRequest 请求对象
	 */
	void getStaticInfo(HttpServletRequest request);
	
	/**
	 * 
	 * <h2>获取当前服务器时间</h2>
	 * <p>以字符串形式返回当前服务器时间，格式为：YYYY年MM月dd日hh时mm分ss秒</p>
	 * @author 青阳龙野(kohgylw)
	 * @return String 时间字符串
	 */
	String getServerTime();
	
	/**
	 * 
	 * <h2>获取当前在线（浏览）人数</h2>
	 * <p>从ServletContext中得到人数计数并返回。</p>
	 * @author 青阳龙野(kohgylw)
	 * @param request HttpServletRequest 请求对象
	 * @return String 在线人数
	 */
	String getVisitorCount(HttpServletRequest request);

}
