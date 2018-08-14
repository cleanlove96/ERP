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
import com.mapper.CapacityInfoMapper;
import com.mapper.MaterialInventoryMapper;
import com.mapper.MaterialOutInStoreMapper;
import com.mapper.OrderPersonMapper;
import com.mapper.RopTableMapper;
import com.mapper.SystemCommodityInformationMapper;
import com.mapper.WareHouseMapper;
import com.model.CapacityInfo;
import com.model.MaterialInventory;
import com.model.MaterialOutInStore;
import com.model.OrderPerson;
import com.model.RopTable;
import com.model.SystemCommodityInformation;
import com.model.WareHouse;
import com.pojo.Page;
import com.pojo.Rop;
import com.pojo.materialOutPojo;
import com.service.ProductionService;

/**
 * @Author QinPeng
 *
 * @Date 2018年6月23日
 */
@Service
public class ProductionServiceImpl implements ProductionService {
	private static final int numPerPage=5;
	@Resource
	private RopTableMapper rtm;
	@Resource
	private CapacityInfoMapper cim;
	@Resource
	private SystemCommodityInformationMapper scim;
	@Resource
	private OrderPersonMapper opm;
	@Resource
	private RopTableMapper rm;
	@Resource
	private MaterialOutInStoreMapper mm;
	@Resource
	private MaterialInventoryMapper mi;
	@Resource
	private WareHouseMapper wm;

	/**
	 * 添加数据信息
	 * @param req
	 * @param ropTable
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
	@Override
	@Transactional
	public String addRopTable(HttpServletRequest req,RopTable ropTable) {
		String str="ERROR";
		if(ropTable==null) {
			return str;
		}
		Gson gson=new Gson();
		System.err.println("-------------------+ropTable"+ropTable);
		System.out.println("--------------++++++"+ropTable.getRopUnit());
		int i=rtm.selectByBatchCount(ropTable.getBatchNumber());
		if(i>0) {
			return "NAME_ERROR";
		}
		
		
		
		//时间需要获取表单的
		String dateStr=req.getParameter("dateStr");
		Date ropProductionTime=gson.fromJson(dateStr, Date.class);
		
		ropTable.setRopProductionTime(ropProductionTime);
		
		//设置默认值
		ropTable.setRopWarehouseEntryTime(new Date());
		ropTable.setRopLoss(-1);
		ropTable.setRopIntoWarehouse("-1");
		ropTable.setRopId(UUID.randomUUID().toString());
		int c=rtm.insert(ropTable);
		
		if(c>0) {
			
			return "SUCCESS";
		}
		return str;
	}

	/**
	 * 获取表单总页数，总条数
	 * @param req
	 * @return string
	 */
	@Override
	public String getStaticPage(HttpServletRequest req) {
		String search=req.getParameter("keyword");
		
		System.out.println("=================search="+search);
		if(search==null) {
			search="";
		}
		Map<String,Object> map=new HashMap<>();
		map.put("search", search);
		map.put("appear_count",0);
		int num=rtm.getRopTableNumByBus(map);
		//int num=rtm.getRopTableNum(search);
		int a=num/numPerPage;
		double b=(num+0.0)/numPerPage;
		int total=a<b?a+1:a;
		Page page=new Page();
		page.setTotal(String.valueOf(total));
		page.setTotalRecords(String.valueOf(num));
		Gson gson=new Gson();
		req.setAttribute("num",num);
		return gson.toJson(page);
	}

	/**
	* 获取一页需要显示的信息，转换成json
	 * @param req
	 * @return string
	 */
	@Override
	public String getPageList(HttpServletRequest req) {
		String search=req.getParameter("keyword");
		String pno=req.getParameter("pno");
		if(search==null) {
			search="";
		}
		int num=1;
		if(pno!=null&&pno.length()>0) {
			num=Integer.parseInt(pno);
			if(num==0) {
				num=1;
			}
		}
		int start=(num-1)*numPerPage;
		/*Map<String,Object> map=new HashMap<>();
		map.put("search", search);
		map.put("start",start);
		map.put("numPerPage",numPerPage);
		List<Rop> list=rtm.selectLike(map);*/
		
		Map<String,Object> map=new HashMap<>();
		map.put("search", search);
		map.put("appear_count",0);
		map.put("start",start);
		map.put("numPerPage",numPerPage);
		List<Rop> list=rtm.selectLikeByBus(map);
		Gson gson=new Gson();
		
		return gson.toJson(list);
	}
	
	/**
	 * 通过传来的id判断是否存在，删除
	 * @param req
	 * @return SUCCESS OR ERROR 
	 */
	@Override
	public String updateStatus(HttpServletRequest req) {
		String ropId=req.getParameter("ropId");
		RopTable ropTable =rtm.selectByPrimaryKey(ropId);
		if(ropTable!=null){
			int i=rtm.deleteByPrimaryKey(ropId);
			if(i>0) {
				return "SUCCESS";
			}
		}
		return "ERROR";
	}
	
	
	/**
	 * 根具id查询信息，传给修改页面用于信息修改
	 * @param req
	 * @return
	 */
	@Override
	public void selectById(HttpServletRequest req) {
		String ropId=req.getParameter("ropId");
		RopTable ropTable=rtm.selectByPrimaryKey(ropId);
		req.setAttribute("ropTable", ropTable);
		List<CapacityInfo>list=cim.selectCapacityAll();
		req.setAttribute("capacityList", list);
		List<SystemCommodityInformation>commodityList=scim.selectAllCommodity();
		req.setAttribute("commodityList", commodityList);
		
	}

	/**
	 * 根具修改界面传来的信息更新数据库
	 * @param req
	 * @return SUCCESS OR ERROR
	 */
	@Override
	public String updateById(HttpServletRequest req,RopTable ropTable) {
		System.out.println("-------updateById------"+ropTable);
		if(ropTable==null) {
			return "ERROR";
		}
		RopTable ropTable1 =rtm.selectByPrimaryKey(ropTable.getRopId());
		if(!ropTable1.getBatchNumber().equals(ropTable.getBatchNumber())){
			int x=rtm.selectByBatchCount(ropTable.getBatchNumber());
			if(x>0) {
				return "NAME_ERROR";
			}
		}
		String dateStr=req.getParameter("dateStr");
		Gson gson=new Gson();
		Date ropProductionTime=gson.fromJson(dateStr, Date.class);
		ropTable.setRopProductionTime(ropProductionTime);
		ropTable.setRopWarehouseEntryTime(new Date());
		ropTable.setRopLoss(-1);
		ropTable.setRopIntoWarehouse("-1");
		int i=rtm.updateByPrimaryKey(ropTable);
		if(i>0) {
			return "SUCCESS";
		}
		return "ERROR";
	}

	/**
	 * 为添加界面获取相关信息
	 * @param req
	 * @return url
	 */
	@Override
	public String getData(HttpServletRequest req) {
		List<CapacityInfo>list=cim.selectCapacityAll();
		req.setAttribute("capacityList", list);
		List<SystemCommodityInformation>commodityList=scim.selectAllCommodity();
		req.setAttribute("commodityList", commodityList);
		return "/view/production/production_add.jsp";
	}

	/**
	 * 向生产管理表记录生产单信息
	 * @param req
	 * @return SUCCESS OR ERROR
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public String inertOrderPerson(HttpServletRequest req) throws Exception {
		String ropId1=req.getParameter("ropId");
		
		//此处添加原料出库
		List<materialOutPojo> listPojo = rm.getTotalNum("%%");
		String accountId = (String) req.getSession().getAttribute("ACCOUNT");
		String goodsType = "出库";
		List<WareHouse> wh = wm.selectByMaterial();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
		for (materialOutPojo materialOutPojo : listPojo) { 
			String warehouseId = "";
		            for (WareHouse wareHouse : wh) {
		            	 warehouseId =wareHouse.getWarehouseId();
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
					my.setMaterialInventoryAmount(MaterialInventoryAmount);
					Double MaterialInventoryValue = my.getMaterialInventoryValue();
					MaterialInventoryValue -= materialInventoryValue;
					my.setMaterialInventoryValue(MaterialInventoryValue);
					mi.updateByPrimaryKeySelective(my);
						} else {
							throw new Exception();

						}

		}
		
		// 向生产管理表记录入库操作
		OrderPerson op = new OrderPerson();
		op.setOrderPersonId(UUID.randomUUID().toString());
		op.setAccountId(accountId);
		op.setRopId(ropId1);
		op.setBusiness("出库完成");
		op.setOrderPersonTime(new Date());
		opm.insertSelective(op);
		
		//需要在生产管理相关人员表添加信息
		OrderPerson orderPerson=new OrderPerson();
		orderPerson.setOrderPersonId(UUID.randomUUID().toString());
		orderPerson.setRopId(ropId1);
		orderPerson.setAccountId((String) req.getSession().getAttribute("ACCOUNT"));
		orderPerson.setBusiness("生产完成");
		orderPerson.setOrderPersonTime(new Date());
		int i=opm.insert(orderPerson);
		if(i>0) {
			return "SUCCESS";
		}else {
			return "ERROR";
		}
	}







	
}
