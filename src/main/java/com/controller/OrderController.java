package com.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.OrderService;

@Controller
@RequestMapping("/orderController")
public class OrderController {

	@Resource
	private OrderService os;
	
	@RequestMapping(value="/queryOrder.do",produces="application/json; charset=utf-8")
	public @ResponseBody String queryOrder(HttpServletRequest request) {	
		return os.selectOrder(request);
	}
	
	@RequestMapping("/getPages.do")
	public @ResponseBody String getPages(HttpServletRequest request) {
		return os.selectPageCount(request);
	}
	
	@RequestMapping(value="/getDetail.do",produces="application/json; charset=utf-8")
	public @ResponseBody String getDetail(HttpServletRequest request) {
		return os.selectDetailsByOrderReceipts(request);
	}
	
	@RequestMapping("/search.do")
	public String search(HttpServletRequest request) {	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		request.setAttribute("receipts", request.getParameter("receipts"));
		return "/view/order/listOrder.jsp";
	}
	
	@RequestMapping("/addOrder.do")
	public @ResponseBody String addOrder(HttpServletRequest request) {
		return os.addOrder(request);
	}
}
