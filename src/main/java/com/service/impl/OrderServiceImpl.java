package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.OrderTableMapper;
import com.model.OrderTableSelectDetailResult;
import com.model.OrderTableSelectResult;
import com.pojo.Page;
import com.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {
private static Gson gson=new Gson();
	
	private static int perPageNum = 5;
	@Resource
	private OrderTableMapper otm;
	
	@Override
	public String selectOrder(HttpServletRequest request) {
		String n = request.getParameter("n");
		String search=request.getParameter("receipts");
		if(search==null) {
			search="";
		}
		int pnum=1;
		if(n!=null&&n.length()>0) {
			pnum=Integer.parseInt(n);
		}
		Map<String, Object> map=new HashMap<>();
		int start=(pnum-1)*perPageNum;
		map.put("start", start);
		map.put("size", perPageNum);
		map.put("search", "%"+search+"%");
		List<OrderTableSelectResult> listOrder=otm.selectOrder(map);
		Set<OrderTableSelectResult> setOrder= new TreeSet<>();
		setOrder.addAll(listOrder);
		return gson.toJson(setOrder);
	}

	@Override
	public String selectPageCount(HttpServletRequest request) {
		String search=request.getParameter("receipts");
		if(search==null) {
			search="";
		}		
		Page page = new Page();
		int totalnumber = otm.selectPageCount("%"+search+"%");
		page.setTotalRecords(totalnumber+"");
		int pagenumber = (int)Math.ceil((double)totalnumber/(double)perPageNum);
		page.setTotal(pagenumber+"");
		return gson.toJson(page);
	}

	@Override
	public String selectDetailsByOrderReceipts(HttpServletRequest request) {
		String orderReceipts = request.getParameter("orderReceipts");
		List<OrderTableSelectDetailResult> listOrderTableSelectDetail=otm.selectDetailsByOrderReceipts(orderReceipts);	
		return gson.toJson(listOrderTableSelectDetail);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String addOrder(HttpServletRequest request) {
		String customerId =request.getParameter("customerId");
		String accountId = request.getParameter("accountId");
		String orderIfo = request.getParameter("order");
		ArrayList<String> orderArr = (ArrayList) gson.fromJson(orderIfo,Object.class);
		Map<String,Object> maps = new HashMap<>();
		int res = 0;
		double orderPrice = 0;
		//循环得出订单总价
		for(int j=1;j<orderArr.size();j++) {
			Map<String,Object> map=(Map<String, Object>) gson.fromJson(orderArr.get(j), Map.class);			
			String string = (String) map.get("totalPrice");
			orderPrice += Double.parseDouble(string);
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(date);
		for(int i=1;i<orderArr.size();i++) {
			Map<String,Object> map=(Map<String, Object>) gson.fromJson(orderArr.get(i), Map.class);
			
			String commodityId = (String) map.get("commodityId");
			Double a = (Double) map.get("amount");
			int amount = a.intValue();
			//String UnitPrice = (String) map.get("UnitPrice");
			String tp = (String) map.get("totalPrice");//一件商品的总价格
			double totalPrice = Double.parseDouble(tp);
			maps.put("id", UUID.randomUUID().toString());
			maps.put("receipts", str);
			maps.put("customerId", customerId);
			maps.put("accountId", accountId);
			maps.put("commodityId", commodityId);
			maps.put("amount", amount);
			maps.put("totalPrice", totalPrice);
			maps.put("orderPrice", orderPrice);
			res+= otm.addOrder(maps);
		}
		return res>0?"YES":"NO";
	}
}
