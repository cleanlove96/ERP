package com.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mapper.SystemAccountMapper;
import com.mapper.SystemAuthMapper;
import com.mapper.SystemRoleAuthMapper;
import com.model.SystemAccount;
import com.model.SystemAuth;

/**
 * 
 * <h2>用于用户身份合法性判定的过滤器</h2>
 * <p>该过滤器用于判定用户是否登录及状态是否合法。拦截所有.do和.jsp请求。</p>
 * @author 青阳龙野(kohgylw)
 * @version 1.0
 */
public class AccountFilter implements Filter {

	private ApplicationContext context;//IOC容器
	private ServletResponse response;
	private HttpServletRequest req;
	private SystemAccountMapper sam;
	private SystemAuthMapper saum;
	private SystemRoleAuthMapper sram;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO 自动生成的方法存根
		// 在非IOC容器生成的对象中获取Spring的IOC容器。
		context = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
		sam=context.getBean(SystemAccountMapper.class);
		saum=context.getBean(SystemAuthMapper.class);
		sram=context.getBean(SystemRoleAuthMapper.class);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO 自动生成的方法存根
		this.response=response;
		req = (HttpServletRequest) request;
		String path=req.getServletPath();
		//请求合法性判定，对所有非登录页面的访问进行判定
		if(path.equals("/login.jsp")) {
			chain.doFilter(request, response);
		}else {
			String accountId = (String) req.getSession().getAttribute("ACCOUNT");
			if (accountId != null) {
				SystemAccount sa = sam.selectByPrimaryKey(accountId);
				if (sa != null) {
					if (sa.getAccountStatus().equals("ON")) {
						List<SystemAuth> auths=saum.selectByHref(path);
						if(auths.size()==0) {
							chain.doFilter(request, response);//放行
						}else {
							//对于权限请求的过滤
							SystemAuth sau=auths.get(0);
							Map<String, String> map=new HashMap<>();
							map.put("accountId", accountId);
							map.put("authId", sau.getAuthId());
							if(sram.selectByAccountIdAndAuthId(map)==null) {
								toLoginPage();
							}else {
								chain.doFilter(request, response);
							}
						}
					} else {
						toLoginPage();
					}
				} else {
					toLoginPage();
				}
			} else {
				toLoginPage();
			}
		}
	}

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
	}
	
	//跳转至登录页面
	private void toLoginPage() {
		HttpServletResponse res = (HttpServletResponse) response;
		try {
			res.sendRedirect(req.getContextPath() + "/login.jsp");
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
