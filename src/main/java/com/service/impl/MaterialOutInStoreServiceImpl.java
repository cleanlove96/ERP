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
import com.mapper.MaterialInventoryMapper;
import com.mapper.MaterialOutInStoreMapper;
import com.mapper.OrderPersonMapper;
import com.mapper.RopTableMapper;
import com.mapper.WareHouseMapper;
import com.model.MaterialInventory;
import com.model.MaterialOutInStore;
import com.model.OrderPerson;
import com.model.WareHouse;
import com.pojo.MaterialStore;
import com.pojo.Page;
import com.pojo.materialOutPojo;
import com.service.MaterialOutInStoreService;

@Service
public class MaterialOutInStoreServiceImpl implements MaterialOutInStoreService {
	@Resource
	private MaterialOutInStoreMapper mm;

	@Resource
	private MaterialInventoryMapper mi;
	@Resource
	private RopTableMapper rm;
	@Resource
	private OrderPersonMapper om;
	@Resource
	private WareHouseMapper wm;
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
		List<MaterialStore> gs = mm.getInfoTable(map);
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
		int tn = mm.getTotalNum(map);
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

	@Override
	public String insertMaterialOutInStore(HttpServletRequest request) {
		String warehouseId = request.getParameter("warehouseId");
		String materialId = request.getParameter("materialId");
		int materialInventoryAmount = Integer.parseInt(request.getParameter("materialInventoryAmount"));
		double materialInventoryValue = Double.parseDouble(request.getParameter("materialInventoryValue"));
		String goodsType = request.getParameter("goodsType");

		if (goodsType.equals("入库")) {
			MaterialOutInStore ms = new MaterialOutInStore();
			Map<String, Object> map = new HashMap<>();
			map.put("materialId", materialId);
			map.put("warehouseId", warehouseId);
			MaterialInventory my = mi.selectByMaterialIdAmdWarehouseId(map);

			ms.setMaterialOutInId(UUID.randomUUID().toString());
			ms.setWarehouseId(warehouseId);
			ms.setMaterialId(materialId);
			ms.setMaterialInventoryAmount(materialInventoryAmount);
			ms.setMaterialInventoryValue(materialInventoryValue);
			ms.setGoodsType(goodsType);
			ms.setTime(sdf.format(new Date()));
			mm.insertSelective(ms);

			if (my == null) {
				MaterialInventory m = new MaterialInventory();
				m.setMaterialId(UUID.randomUUID().toString());
				m.setMaterialInventoryAmount(materialInventoryAmount);
				m.setMaterialInventoryValue(materialInventoryValue);
				m.setWarehouseId(warehouseId);
				mi.insertSelective(m);
			} else {

				Integer MaterialInventoryAmount = my.getMaterialInventoryAmount();
				MaterialInventoryAmount += materialInventoryAmount;
				my.setMaterialInventoryAmount(MaterialInventoryAmount);
				Double MaterialInventoryValue = my.getMaterialInventoryValue();
				MaterialInventoryValue += materialInventoryValue;
				my.setMaterialInventoryValue(MaterialInventoryValue);
				mi.updateByPrimaryKeySelective(my);
			}
			return "SUCCESS";
		} else {
			return "ERROR";
		}
	}

	@Override
	public String deleteMaterialOutInStore(HttpServletRequest request) {
		String warehouseId = request.getParameter("warehouseId");
		String materialId = request.getParameter("materialId");
		int materialInventoryAmount = Integer.parseInt(request.getParameter("materialInventoryAmount"));
		double materialInventoryValue = Double.parseDouble(request.getParameter("materialInventoryValue"));
		String goodsType = request.getParameter("goodsType");
		Map<String, Object> map = new HashMap<>();
		map.put("materialId", materialId);
		map.put("warehouseId", warehouseId);
		MaterialInventory my = mi.selectByMaterialIdAmdWarehouseId(map);

		if (goodsType.equals("出库")) {
			if (my.getMaterialInventoryAmount() > 0 || materialInventoryAmount < my.getMaterialInventoryAmount()) {
				MaterialOutInStore ms = new MaterialOutInStore();

				ms.setMaterialOutInId(UUID.randomUUID().toString());
				ms.setWarehouseId(warehouseId);
				ms.setMaterialId(materialId);
				ms.setMaterialInventoryAmount(materialInventoryAmount);
				ms.setMaterialInventoryValue(materialInventoryValue);
				ms.setGoodsType(goodsType);
				ms.setTime(sdf.format(new Date()));
				mm.insertSelective(ms);

				Integer MaterialInventoryAmount = my.getMaterialInventoryAmount();
				MaterialInventoryAmount -= materialInventoryAmount;
				my.setMaterialInventoryAmount(materialInventoryAmount);
				Double MaterialInventoryValue = my.getMaterialInventoryValue();
				MaterialInventoryValue -= materialInventoryValue;
				my.setMaterialInventoryValue(materialInventoryValue);
				mi.updateByPrimaryKeySelective(my);
				return "SUCCESS";
			} else {
				return "LAZY";

			}

		} else {
			return "ERROR";
		}
	}

	@Transactional
	public String ack(HttpServletRequest request) {
		// 取出ajax传输的数据
		String warehouseId = request.getParameter("warehouseId");
		String materialId = request.getParameter("materialId");
		int materialInventoryAmount = Integer.parseInt(request.getParameter("purchaseTableInt"));
		double materialInventoryValue = Double.parseDouble(request.getParameter("extendPrices"));
		String goodsType = "入库";
		String purchaseId = request.getParameter("purchaseId");
		String accountId = (String) request.getSession().getAttribute("ACCOUNT");

		// 向生产管理表记录入库操作
		OrderPerson op = new OrderPerson();
		op.setOrderPersonId(UUID.randomUUID().toString());
		op.setAccountId(accountId);
		op.setPurchaseId(purchaseId);
		op.setBusiness("入库完成");
		op.setOrderPersonTime(new Date());
		om.insertSelective(op);

		// 向原料入库表添加入库信息
		MaterialOutInStore mo = new MaterialOutInStore();
		mo.setMaterialOutInId(UUID.randomUUID().toString());
		mo.setMaterialId(materialId);
		mo.setMaterialInventoryAmount(materialInventoryAmount);
		mo.setGoodsType(goodsType);
		mo.setMaterialInventoryValue(materialInventoryValue);
		mo.setTime(sdf.format(new Date()));
		mo.setWarehouseId(warehouseId);
		mm.insert(mo);

		// 向原料总库存表中添加数据
		Map<String, Object> map = new HashMap<>();
		map.put("materialId", materialId);
		map.put("warehouseId", warehouseId);
		MaterialInventory my = mi.selectByMaterialIdAmdWarehouseId(map);
		if (my == null) {
			MaterialInventory m = new MaterialInventory();
			m.setMaterialId(materialId);
			m.setMaterialInventoryAmount(materialInventoryAmount);
			m.setMaterialInventoryId(UUID.randomUUID().toString());
			m.setMaterialInventoryValue(materialInventoryValue);
			m.setWarehouseId(warehouseId);
			mi.insertSelective(m);
		} else {
			Integer MaterialInventoryAmount = my.getMaterialInventoryAmount();
			MaterialInventoryAmount += materialInventoryAmount;
			my.setMaterialInventoryAmount(MaterialInventoryAmount);
			Double MaterialInventoryValue = my.getMaterialInventoryValue();
			MaterialInventoryValue += materialInventoryValue;
			my.setMaterialInventoryValue(MaterialInventoryValue);
			mi.updateByPrimaryKeySelective(my);
		}
		return "SUCCESS";
	}

	@Transactional
	public String ac(HttpServletRequest request) {
		String s = request.getParameter("sreach");
		String[] warehouseIds = request.getParameterValues("warehouseIds");
		List<materialOutPojo> listPojo = rm.getTotalNum("%" + s + "%");
		// //取出ajax传输的数据
		// String warehouseId = request.getParameter("warehouseId");
		// String materialId = request.getParameter("materialId");
		// int materialInventoryAmount = Integer.parseInt(request.getParameter("num"));
		// double materialInventoryValue =
		// Double.parseDouble(request.getParameter("money"));
		// String ropId =request.getParameter("ropId");
		String accountId = (String) request.getSession().getAttribute("ACCOUNT");
		String goodsType = "出库";
		String warehouseId ="";
		for (materialOutPojo materialOutPojo : listPojo) { 
	          for (int i = 0; i < warehouseIds.length; i++) {
	 			 warehouseId = warehouseIds[i];
	 			
	 		}
				String materialId = materialOutPojo.getMaterialId();
				int materialInventoryAmount = materialOutPojo.getRopUnit() * materialOutPojo.getFormulaCount();
				double materialInventoryValue = materialInventoryAmount * materialOutPojo.getPrice();
				String ropId = materialOutPojo.getRopId();
				// 向原料出库表添加入库信息
				Map<String, Object> map = new HashMap<>();
				map.put("materialId", materialId);
				map.put("warehouseId", warehouseId);
				MaterialInventory my = mi.selectByMaterialIdAmdWarehouseId(map);
				if (my.getMaterialInventoryAmount() > 0 || my.getMaterialInventoryAmount() > materialInventoryAmount) {

					// 向生产管理表记录入库操作
					if (om.selectByRopId(ropId) == null) {
						OrderPerson op = new OrderPerson();
						op.setOrderPersonId(UUID.randomUUID().toString());
						op.setAccountId(accountId);
						op.setRopId(ropId);
						op.setBusiness("出库完成");
						op.setOrderPersonTime(new Date());
						om.insertSelective(op);
					}

					MaterialOutInStore ms = new MaterialOutInStore();
					ms.setMaterialOutInId(UUID.randomUUID().toString());
					ms.setWarehouseId(warehouseId);
					ms.setMaterialId(materialId);
					ms.setMaterialInventoryAmount(materialInventoryAmount);
					ms.setMaterialInventoryValue(materialInventoryValue);
					ms.setGoodsType(goodsType);
					ms.setTime(sdf.format(new Date()));
					mm.insertSelective(ms);

					Integer MaterialInventoryAmount = my.getMaterialInventoryAmount();
					MaterialInventoryAmount -= materialInventoryAmount;
					my.setMaterialInventoryAmount(MaterialInventoryAmount);
					Double MaterialInventoryValue = my.getMaterialInventoryValue();
					MaterialInventoryValue -= materialInventoryValue;
					my.setMaterialInventoryValue(MaterialInventoryValue);
					mi.updateByPrimaryKeySelective(my);
					
					return "SUCCESS";
				}else {
					return "LAZY";
				}
					
				}
		return warehouseId;
		
	}

	@Override
	public void addUI(HttpServletRequest request) {
		List<materialOutPojo> listPojo = rm.getTotalNum("%%");
		List<WareHouse> wh = wm.selectByMaterial();
		request.setAttribute("wh", wh);
		request.setAttribute("listPojo", listPojo);
	}

}
