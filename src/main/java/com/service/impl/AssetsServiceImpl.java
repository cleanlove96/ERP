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
import com.mapper.CurrentAssetsMapper;
import com.mapper.FixedAssetsMapper;
import com.model.CurrentAssets;
import com.model.CustomerInfo;
import com.model.FixedAssets;
import com.pojo.Page;
import com.service.AssetsService;

@Service
public class AssetsServiceImpl implements AssetsService {
	@Resource
	private FixedAssetsMapper fas;
	@Resource
	private CurrentAssetsMapper cam;
	private static final int NUM_PER_PAGE = 5;
	private static Gson gson = new Gson();
	@Override
	public String getPage(HttpServletRequest request) {
		String str =request.getParameter("sreach");
		if(str==null) {
			 str="";
		}
		Page p = new Page();
		Map<String, String> map = new HashMap<>();
		map.put("name", "%"+str+"%");
		int num=fas.getTotalNum(map);
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
	public String getFixedAssetsTable(HttpServletRequest request) {
		String n=request.getParameter("n");
		String sreach=request.getParameter("sreach");
		if(sreach==null) {
			sreach="";
		}
		int pnum=1;
		if(n!=null&&n.length()>0) {
			pnum=Integer.parseInt(n);
		}
		Map<String, Object> map=new HashMap<>();
		int start=(pnum-1)*NUM_PER_PAGE;
		map.put("start", start);
		map.put("size", NUM_PER_PAGE);
		map.put("sreach", "%"+sreach+"%");
		List<FixedAssets> fa=fas.getFixedAssetsTable(map);
		return gson.toJson(fa);
	}
	@Override
	public String getFixedAssetsNum() {
		FixedAssets fa = fas.selectFixedAssets();
		if(fa!=null) {
			String num = fa.getFixedAssetsNum();
			String numNext = num.substring(6,num.length());
			int n = Integer.parseInt(numNext);
			String nextNum = (n+1)+"";
			String numFront = num.substring(0,num.length()-nextNum.length());
			return numFront+nextNum;
		}else
			return "53DUD-000001";

	}
	@Override
	public String addFixedAssets(FixedAssets fa) {
		fa.setFixedAssetsId(UUID.randomUUID().toString());
		fa.setFixedAssetsTime(new Date());
		int n = fas.insertSelective(fa);
		System.out.println("-------"+n);
//		总账对象 固定资产的增加记录
		CurrentAssets ca = new CurrentAssets();
		ca.setAllAssetsId(fa.getFixedAssetsId());
		ca.setFixedAssets(fa.getFixedAssetsPrice());
		ca.setAssetsTime(new Date());
		ca.setAssetsDescribe(fa.getFixedAssetsName());
		cam.insertSelective(ca);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}
	@Override
	public FixedAssets queryFixedAssetsById(HttpServletRequest request) {
		String id = request.getParameter("fixedAssetsId");
		return fas.selectByPrimaryKey(id);
	}
	@Override
	public String updateCustomer(FixedAssets fa) {
		int n = fas.updateByPrimaryKeySelective(fa);
		CurrentAssets ca = new CurrentAssets();
		ca.setAllAssetsId(fa.getFixedAssetsId());
		ca.setFixedAssets(fa.getFixedAssetsPrice());
		ca.setAssetsTime(new Date());
		cam.updateByPrimaryKeySelective(ca);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;
	}
	@Override
	public String deleteFixedAssets(HttpServletRequest request) {
		String id = request.getParameter("id");
		int n =fas.deleteByPrimaryKey(id);
		cam.deleteByPrimaryKey(id);
		String result;
		if(n>0) {
			 result = "SUCCESS";
		}else
			result = "ERROR";
		return result;	
	}
}
