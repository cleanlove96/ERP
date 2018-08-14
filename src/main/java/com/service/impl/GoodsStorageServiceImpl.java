package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.OrderPersonMapper;
import com.mapper.WareHouseMapper;
import com.model.WareHouse;
import com.pojo.GoodsStoragePojo;
import com.pojo.Page;
import com.service.GoodsStorageService;

@Service
public class GoodsStorageServiceImpl implements GoodsStorageService {

	@Resource
	private OrderPersonMapper om;
	
	@Resource
	private WareHouseMapper wm;
	
	private static Gson gson = new Gson();

	@Override
	public String getInfoTable(HttpServletRequest request) {
		String n=request.getParameter("n");
		String s=request.getParameter("sreach");
		int pageRecords = Integer.parseInt(request.getParameter("pageRecords"));
		if(s==null) {
			s="";
		}
		int pnum=1;
		if(n!=null&&n.length()>0) {
			pnum=Integer.parseInt(n);
		}
		Map<String, Object> map=new HashMap<>();
		int start=(pnum-1)*pageRecords;
		map.put("start", start);
		map.put("size", pageRecords);
		map.put("s", "%"+s+"%");
		List<GoodsStoragePojo> sm=om.getInfoTable(map);
		return gson.toJson(sm);
	}

	@Override
	public String getPage(HttpServletRequest request) {
		String s = request.getParameter("sreach");
		int pageRecords = Integer.parseInt(request.getParameter("pageRecords"));
		if(s==null){
			s="";
		}
		Page p =new Page();
		List<GoodsStoragePojo> listPojo = om.getTotalNum("%"+s+"%");
		int tn = listPojo.size();
		p.setTotalRecords(tn+"");
		int t=0;
		if( tn%pageRecords!=0) {
			t=tn/pageRecords+1;
		}else {
			t=tn/pageRecords;
		}
		p.setTotal(t+"");
		return gson.toJson(p);
	}

	@Override
	public String getwarehouse(HttpServletRequest request) {
		List<WareHouse> wh = wm.selectAll();
		return gson.toJson(wh);
	}
}
