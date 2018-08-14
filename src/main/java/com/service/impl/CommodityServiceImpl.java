package com.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.SystemCommodityInformationMapper;
import com.model.SystemCommodityInformation;
import com.pojo.Page;
import com.service.CommodityService;

@Service
public class CommodityServiceImpl implements CommodityService {

	private static Gson gson=new Gson();
	
	private static int perPageNum = 5;
	@Resource
	private SystemCommodityInformationMapper scim;
	
	@Override
	public String selectCommodity(HttpServletRequest request) {
		String n = request.getParameter("n");
		String search=request.getParameter("commodity");
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
		List<SystemCommodityInformation> listCommodity=scim.selectCommodity(map);		
		return gson.toJson(listCommodity);
	}

	@Override
	public String selectPageCount(HttpServletRequest request) {
		String search=request.getParameter("commodity");
		if(search==null) {
			search="";
		}		
		Page page = new Page();
		int totalnumber = scim.selectPageCount("%"+search+"%");
		page.setTotalRecords(totalnumber+"");
		int pagenumber = (int)Math.ceil((double)totalnumber/(double)perPageNum);
		page.setTotal(pagenumber+"");
		return gson.toJson(page);
	}

	@Override
	public String addCommodity(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        Map<String, Object> map=new HashMap<>();
        map.put("commodityId", UUID.randomUUID().toString());
        map.put("commodityName", request.getParameter("commodityName"));
        map.put("commoditySpecification", request.getParameter("commoditySpecification"));            
        map.put("commodityPlace", request.getParameter("commodityPlace"));            
        map.put("commodityType", request.getParameter("commodityType"));            
        map.put("commodityBrand", request.getParameter("commodityBrand"));            
        map.put("commodityDegrees", request.getParameter("commodityDegrees"));            
        map.put("commodityRecipe", request.getParameter("commodityRecipe"));            
        map.put("commodityExpiration", request.getParameter("commodityExpiration"));            
        map.put("commodityOdor", request.getParameter("commodityOdor")); 
        if(request.getParameter("commodityPrice")==null) {
        	double price=0.0;
        	map.put("commodityPrice", price);
        }else {
        	map.put("commodityPrice", request.getParameter("commodityPrice")); 
        }                 
        map.put("commodityStatus", request.getParameter("commodityStatus"));            

        int res=scim.addCommodity(map);
        System.out.println("++++++++++++++++++++++++"+res);
		return res>0?"YES":"NO";
	}

	@Override
	public void updateStatus(HttpServletRequest request) {
		Map<String, Object> map=new HashMap<>();
		String commodityId=request.getParameter("commodityId");
		String commodtyStatus=request.getParameter("commodtyStatus");
		System.out.println("要修改的商品ID:"+commodityId);
		System.out.println("要修改的商品status:"+commodtyStatus);
		map.put("commodityId", commodityId);
		if(commodtyStatus.equals("停用")) {
			map.put("status", "启用");
		}else if(commodtyStatus.equals("启用")){
			map.put("status", "停用");
		}		
		scim.updateStatus(map);
	}

	@Override
	public SystemCommodityInformation selectCommodityById(HttpServletRequest request) {
		String commodityId = request.getParameter("commodityId");
		SystemCommodityInformation sci=scim.selectByPrimaryKey(commodityId);
		return sci;
	}

	@Override
	public String updateCommodityInformation(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        Map<String, Object> map=new HashMap<>();
        map.put("commodityId", request.getParameter("commodityId"));
        map.put("commodityName", request.getParameter("commodityName"));
        map.put("commoditySpecification", request.getParameter("commoditySpecification"));            
        map.put("commodityPlace", request.getParameter("commodityPlace"));            
        map.put("commodityType", request.getParameter("commodityType"));            
        map.put("commodityBrand", request.getParameter("commodityBrand"));            
        map.put("commodityDegrees", request.getParameter("commodityDegrees"));            
        map.put("commodityRecipe", request.getParameter("commodityRecipe"));            
        map.put("commodityExpiration", request.getParameter("commodityExpiration"));            
        map.put("commodityOdor", request.getParameter("commodityOdor"));            
        map.put("commodityPrice", request.getParameter("commodityPrice"));
        int res = scim.updateCommodity(map);
        
		return res>0?"YES":"NO";
	}

	@Override
	public String selectCommodityByName(HttpServletRequest request) {
		String searchName = request.getParameter("search");
		List<SystemCommodityInformation> listSearch= scim.selectCommodityByName(searchName);
		return gson.toJson(listSearch);
	}

}
