package com.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.SaleService;

@Controller
@RequestMapping("/saleController")
public class SaleController {

	@Resource
	private SaleService ss;
	
	@RequestMapping(value="/querySale.do",produces="application/json; charset=utf-8")
	public @ResponseBody String querySale(HttpServletRequest request) {
		return ss.selectSale(request);
	}
	
	@RequestMapping("/getPages.do")
	public @ResponseBody String getPages(HttpServletRequest request) {
		return ss.selectPageCount(request);
	}
	
	@RequestMapping(value="/queryCustomerName.do",produces="application/json; charset=utf-8")
	public @ResponseBody String queryCustomerName() {
		return ss.selectCustomerName();
	}
	
	@RequestMapping(value="/queryAccountName.do",produces="application/json; charset=utf-8")
	public @ResponseBody String queryAccountName() {
		return ss.selectAccountName();
	}
	
	@RequestMapping(value="/queryCommodityName.do",produces="application/json; charset=utf-8")
	public @ResponseBody String queryCommodityName() {
		return ss.selectCommodityName();
	}
	
	@RequestMapping("/addSale.do")
	public @ResponseBody String addSale(HttpServletRequest request){
		return ss.insertSale(request);
	}
	
	@RequestMapping("/search.do")
	public String search(HttpServletRequest request) {	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		request.setAttribute("customer", request.getParameter("customer"));
		return "/view/sale/listSale.jsp";
	}
	
	@RequestMapping(value="/getDetail.do",produces="application/json; charset=utf-8")
	public @ResponseBody String getDetail(HttpServletRequest request) {
		return ss.selectDetailsByReceipts(request);
	}
}
