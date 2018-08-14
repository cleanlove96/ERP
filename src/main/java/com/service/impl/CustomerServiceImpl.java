package com.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.CustomerInfoMapper;
import com.model.CustomerInfo;
import com.pojo.Page;
import com.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService {

	@Resource
	private CustomerInfoMapper cim;
	private static final int NUM_PER_PAGE = 5;
	private static Gson gson = new Gson();
	@Override
	public String getPage(HttpServletRequest request) {
		String str =request.getParameter("sreach");
		String status =request.getParameter("status");
		System.out.println(status);
		if(str==null) {
			 str="";
		}
		Page p = new Page();
		Map<String, String> map = new HashMap<>();
		map.put("name", "%"+str+"%");
		map.put("status", status);
		int num=cim.getTotalNum(map);
		int t;
		if(num%NUM_PER_PAGE!=0) {
			 t = num/NUM_PER_PAGE+1;
		}else {
			  t = num/NUM_PER_PAGE;
		}
		p.setTotalRecords(num+"");
		p.setTotal(t+"");
		return gson.toJson(p);
	}

	@Override
	public String getCustomerTable(HttpServletRequest request) {
		String n=request.getParameter("n");
		String sreach=request.getParameter("sreach");
		String status =request.getParameter("status");
		System.out.println(sreach);
		if(sreach==null) {
			sreach="";
		}
		int pnum=1;
		if(n!=null&&n.length()>0) {
			pnum=Integer.parseInt(n);
		}
		Map<String, Object> map=new HashMap<>();
		int start=(pnum-1)*NUM_PER_PAGE;
		map.put("status", status);
		map.put("start", start);
		map.put("size", NUM_PER_PAGE);
		map.put("sreach", "%"+sreach+"%");
		List<CustomerInfo> ci=cim.getCustomerTable(map);
		return gson.toJson(ci);
	}

	@Override
	public CustomerInfo queryCustomerById(HttpServletRequest request) {
		String customerId = request.getParameter("customerId");
		return  cim.selectByPrimaryKey(customerId);
	}

	@Override
	public String updateCustomer(CustomerInfo data) {
		int n = cim.updateByPrimaryKeySelective(data);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}

	@Override
	public String deleteCustomer(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println(id);
		int n = cim.deleteCustomer(id);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}

	@Override
	public String addCustomer(CustomerInfo data) {
		data.setCustomerId(UUID.randomUUID().toString());
		data.setCreateTime(new Date());
		data.setCustomerStatus("0");
		int n = cim.insertSelective(data);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}

	@Override
	public String backCustomer(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println(id);
		int n = cim.backCustomer(id);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}


	

}
