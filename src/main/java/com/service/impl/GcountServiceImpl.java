package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.GcountMapper;
import com.mapper.SystemCommodityInformationMapper;
import com.mapper.WareHouseMapper;
import com.model.Gcount;
import com.model.SystemCommodityInformation;
import com.model.WareHouse;
import com.pojo.GcountPojo;
import com.pojo.Page;
import com.service.GcountService;

@Service
public class GcountServiceImpl implements GcountService {
	
	@Resource
	private GcountMapper gm;
	
	@Resource
	private WareHouseMapper wm;
	
	@Resource
	private SystemCommodityInformationMapper sc;
	
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
		List<GcountPojo> sm=gm.getInfoTable(map);
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
		int tn = gm.getTotalNum("%"+s+"%");
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
	public void selectById(HttpServletRequest request) {
		String gcountId = request.getParameter("gcountId");
		GcountPojo gp = gm.selectById(gcountId);
		SystemCommodityInformation c = sc.selectByPrimaryKey(gp.getCommodityId());
		List<WareHouse> listw = wm.selectAll();
		request.setAttribute("gp", gp);
		request.setAttribute("listw",listw);
		request.setAttribute("Price",c.getCommodtyPrice());
	}

	@Override
	public String update(HttpServletRequest request) {
		Gcount gcount = new Gcount();
		gcount.setGcountId(request.getParameter("gcountId"));
		gcount.setCommodityId(request.getParameter("commodityId"));
		gcount.setGcountAmount(Integer.parseInt(request.getParameter("gcountAmount")));
		gcount.setGcountMoney(Double.parseDouble(request.getParameter("gcountMoney")));
		gcount.setWarehouseId(request.getParameter("warehouseId"));
	    return "SUCCESS";
		
	}

}
