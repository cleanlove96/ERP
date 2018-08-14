package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.MaterialInventoryMapper;
import com.mapper.SysMaterialMapper;
import com.mapper.WareHouseMapper;
import com.model.MaterialInventory;
import com.model.SysMaterial;
import com.model.WareHouse;
import com.pojo.MaterialInventoryPojo;
import com.pojo.Page;
import com.service.MaterialInventoryService;

@Service
public class MaterialInventoryServiceImpl implements MaterialInventoryService {

	@Resource
	private MaterialInventoryMapper mm;

	@Resource
	private SysMaterialMapper sm;
	
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
		List<MaterialInventoryPojo> sm=mm.getInfoTable(map);
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
		int tn = mm.getTotalNum("%"+s+"%");
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
		String materialInventoryId = request.getParameter("materialInventoryId");
		MaterialInventoryPojo mp = mm.selectById(materialInventoryId);
		SysMaterial sl = sm.selectByPrimaryKey(mp.getMaterialId());
		List<WareHouse> listw = wm.selectByMaterial();
		request.setAttribute("mp", mp);
		request.setAttribute("listw",listw);
		request.setAttribute("price",sl.getPrice());
		
	}

	@Override
	public String update(HttpServletRequest request) {
		MaterialInventory mi = new MaterialInventory();
		mi.setMaterialId(request.getParameter("materialId"));
		mi.setMaterialInventoryId(request.getParameter("materialInventoryId"));
		mi.setMaterialInventoryAmount(Integer.parseInt(request.getParameter("materialInventoryAmount")));
		mi.setMaterialInventoryValue(Double.parseDouble(request.getParameter("materialInventoryValue")));
		mi.setWarehouseId(request.getParameter("warehouseId"));
		mm.updateByPrimaryKeySelective(mi);
		return "SUCCESS";
	}
}
