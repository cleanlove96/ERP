package com.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pojo.AuthGroupPojo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration//参见官方文档：https://docs.spring.io/spring/docs/4.3.18.RELEASE/spring-framework-reference/htmlsingle/#spring-mvc-test-server
@ContextConfiguration("classpath:spring.xml")
public class ControllerTest {
	//这是测试类
	@Resource
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Test
	public void test() {
		mvc=MockMvcBuilders.webAppContextSetup(context).build();
		try {
			//模拟向某个控制器发送指定情况的请求
			ResultActions ra=mvc.perform(post("/indexController/showIndexPage.do").sessionAttr("ACCOUNT", "0"));
			MvcResult mr=ra.andDo(print()).andReturn();//得到请求结果并打印请求状态
			HttpServletRequest request=mr.getRequest();//由请求结果可以得到最终返回的请求对象和响应对象
			HttpServletResponse response=mr.getResponse();
			List<AuthGroupPojo> pojos=(List<AuthGroupPojo>) request.getAttribute("leftButtons");
			System.out.println("数组长度为："+pojos.size());
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
