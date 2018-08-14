package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.mapper.GcountMapper;
import com.mapper.GoodsOutInStoreMapper;
import com.mapper.OrderPersonMapper;
import com.model.Gcount;
import com.model.GoodsOutInStore;
import com.model.OrderPerson;
import com.pojo.GoodsStore;
import com.pojo.Page;
import com.service.GoodsOutInStoreService;

@Service
public class GoodsOutInStoreServiceImpl implements GoodsOutInStoreService {
	@Resource
	private GoodsOutInStoreMapper gm;

	@Resource
	private GcountMapper gt;

	@Resource
	private OrderPersonMapper om;
	
	private static Gson gson = new Gson();

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");

	@Override
	public String getInfoTable(HttpServletRequest request) {
		String n = request.getParameter("n");
		String s = request.getParameter("sreach");
		String s2 = request.getParameter("sreach2");
		int pageRecords = Integer.parseInt(request.getParameter("pageRecords"));
		if (s == null) {
			s = "";
		}
		;
		if (s2 == null) {
			s2 = "";
		}
		;
		int pnum = 1;
		if (n != null && n.length() > 0) {
			pnum = Integer.parseInt(n);
		}
		Map<String, Object> map = new HashMap<>();
		int start = (pnum - 1) * pageRecords;
		map.put("start", start);
		map.put("size", pageRecords);
		map.put("s", "%" + s + "%");
		map.put("s2", "%" + s2 + "%");
		List<GoodsStore> gs = gm.getInfoTable(map);
		return gson.toJson(gs);
	}

	@Override
	public String getPage(HttpServletRequest request) {
		String s = request.getParameter("sreach");
		String s2 = request.getParameter("sreach2");
		int pageRecords = Integer.parseInt(request.getParameter("pageRecords"));
		if (s == null) {
			s = "";
		}
		if (s2 == null) {
			s2 = "";
		}
		Page p = new Page();
		Map<String, Object> map = new HashMap<>();
		map.put("s", "%" + s + "%");
		map.put("s2", "%" + s2 + "%");
		int tn = gm.getTotalNum(map);
		p.setTotalRecords(tn + "");
		int t = 0;
		if (tn % pageRecords != 0) {
			t = tn / pageRecords + 1;
		} else {
			t = tn / pageRecords;
		}
		p.setTotal(t + "");
		return gson.toJson(p);
	}


	@Transactional
	public String insertGoodsOutInStore(HttpServletRequest request) {
		String warehouseId = request.getParameter("warehouseId");
		String commodityId = request.getParameter("commodityId");
		int materialInventoryAmount = Integer.parseInt(request.getParameter("materialInventoryAmount"));
		double materialInventoryValue = Double.parseDouble(request.getParameter("materialInventoryValue"));
		String goodsType = request.getParameter("goodsType");
		String batchNumber = request.getParameter("batchNumber");

		if (goodsType.equals("入库")) {
			GoodsOutInStore gs = new GoodsOutInStore();
			Map<String, Object> map = new HashMap<>();
			map.put("commodityId", commodityId);
			map.put("warehouseId", warehouseId);
			Gcount g = gt.selectByCommodityIdAndWarehouseId(map);

			//向入库表添加数据
			gs.setGoodsOutInId(UUID.randomUUID().toString());
			gs.setWarehouseId(warehouseId);
			gs.setCommodityId(commodityId);
			gs.setMaterialInventoryAmount(materialInventoryAmount);
			gs.setMaterialInventoryValue(materialInventoryValue);
			gs.setGoodsType(goodsType);
			gs.setBatchNumber(batchNumber);
			gs.setTime(sdf.format(new Date()));
			gm.insertSelective(gs);

			//向总库存表添加数据
			if (g == null) {
				Gcount gcount = new Gcount();
				gcount.setGcountId(UUID.randomUUID().toString());
				gcount.setGcountAmount(materialInventoryAmount);
				gcount.setGcountMoney(materialInventoryValue);
				gcount.setWarehouseId(warehouseId);
				gt.insertSelective(gcount);
			} else {

				Integer gcountAmount = g.getGcountAmount();
				gcountAmount += materialInventoryAmount;
				g.setGcountAmount(gcountAmount);

				Double gcountMoney = g.getGcountMoney();
				gcountMoney += materialInventoryValue;
				g.setGcountMoney(gcountMoney);
				gt.updateByPrimaryKeySelective(g);
			}
			return "SUCCESS";
		} else {
			return "ERROR";
		}
	}

	

	@Transactional
	public String deleteGoodsOutInStore(Map map1) {
		String warehouseId = map1.get("warehouseId")+"";
		String commodityId = map1.get("commodityId")+"";
		int materialInventoryAmount = (int)map1.get("materialInventoryAmount");
		double materialInventoryValue = (double)map1.get("materialInventoryValue");
		String goodsType = (String)map1.get("goodsType");
		String batchNumber =(String) map1.get("batchNumber");

		if(goodsType.equals("出库")) {		
			Map<String, Object> map = new HashMap<>();
			map.put("commodityId", commodityId);
			map.put("warehouseId", warehouseId);
			Gcount gc = gt.selectByCommodityIdAndWarehouseId(map);
		if(gc.getGcountAmount()!=0||materialInventoryAmount<gc.getGcountAmount()) {
		GoodsOutInStore gs = new GoodsOutInStore();	

		//添加出库信息
		gs.setGoodsOutInId(UUID.randomUUID().toString());
		gs.setWarehouseId(warehouseId);
		gs.setCommodityId(commodityId);
		gs.setMaterialInventoryAmount(materialInventoryAmount);
		gs.setMaterialInventoryValue(materialInventoryValue);
		gs.setGoodsType(goodsType);
		gs.setBatchNumber(batchNumber);
		gs.setTime(sdf.format(new Date()));
		gm.insertSelective(gs);

		//向总库存表减少数据	
		Integer gcountAmount = gc.getGcountAmount();
		gcountAmount -= materialInventoryAmount;
		gc.setGcountAmount(gcountAmount);

		Double gcountMoney = gc.getGcountMoney();
		gcountMoney -= materialInventoryValue;
		gc.setGcountMoney(gcountMoney);
		gt.updateByPrimaryKeySelective(gc);
			
		return "SUCCESS";
		}else {
			return "LAZY";
		}
	        }else {
		    	
			return "ERROR";
		}
	}

	@Transactional
	public String ack(HttpServletRequest request) {
		//获取ajax传输的数据
		String ropId = request.getParameter("ropId");
		String accountId = (String) request.getSession().getAttribute("ACCOUNT");
		String warehouseId = request.getParameter("warehouseId");
		String commodityId = request.getParameter("commodityId");
		String num = request.getParameter("num");
		int materialInventoryAmount = Integer.parseInt(num);
		double materialInventoryValue = Double.parseDouble(request.getParameter("money"));
		String goodsType = "入库";
		String batchNumber = request.getParameter("batchNumber");
		
		//向生产管理表记录入库操作
		OrderPerson op = new OrderPerson();
		op.setOrderPersonId(UUID.randomUUID().toString());
		op.setRopId(ropId);
		op.setAccountId(accountId);
		op.setBusiness("入库完成");
		op.setOrderPersonTime(new Date());
		om.insertSelective(op);
		
		//向商品入库表添加入库信息
		GoodsOutInStore gs = new GoodsOutInStore();
		gs.setGoodsOutInId(UUID.randomUUID().toString());
		gs.setWarehouseId(warehouseId);
		gs.setCommodityId(commodityId);
		gs.setMaterialInventoryAmount(materialInventoryAmount);
		gs.setMaterialInventoryValue(materialInventoryValue);
		gs.setGoodsType(goodsType);
		gs.setBatchNumber(batchNumber);
		gs.setTime(sdf.format(new Date()));
		gm.insertSelective(gs);
		
		//向商品总库存表添加数据
		Map<String, Object> map = new HashMap<>();
		map.put("commodityId", commodityId);
		map.put("warehouseId", warehouseId);
		Gcount g = gt.selectByCommodityIdAndWarehouseId(map);
		if (g == null) {
			Gcount gcount = new Gcount();
			gcount.setCommodityId(commodityId);
			gcount.setGcountId(UUID.randomUUID().toString());
			gcount.setGcountAmount(materialInventoryAmount);
			gcount.setGcountMoney(materialInventoryValue);
			gcount.setWarehouseId(warehouseId);
			gt.insertSelective(gcount);
		} else {
			Integer gcountAmount = g.getGcountAmount();
			gcountAmount += materialInventoryAmount;
			g.setGcountAmount(gcountAmount);
			Double gcountMoney = g.getGcountMoney();
			gcountMoney += materialInventoryValue;
			g.setGcountMoney(gcountMoney);
			gt.updateByPrimaryKeySelective(g);
		}
		return "SUCCESS";
	}

}
