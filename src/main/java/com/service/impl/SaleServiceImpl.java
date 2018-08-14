package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.MoneyManagementTableMapper;
import com.mapper.SaleBillsMapper;
import com.model.CustomerInfo;
import com.model.OrderTableSelectDetailResult;
import com.model.OrderTableSelectResult;
import com.model.SaleBills;
import com.model.SaleBillsDetailResult;
import com.model.SaleSelectResult;
import com.model.SystemAccount;
import com.model.SystemCommodityInformation;
import com.pojo.Page;
import com.service.GoodsOutInStoreService;
import com.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService {
	
	private static Gson gson=new Gson();
	
	private static int perPageNum = 5;
	@Resource
	private SaleBillsMapper sbm;

	@Override
	public String selectSale(HttpServletRequest request) {
		String n = request.getParameter("n");
		String search=request.getParameter("customer");
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
		List<SaleSelectResult> listSale=sbm.selectSale(map);
		Set<SaleSelectResult> setOrder= new TreeSet<>();
		setOrder.addAll(listSale);
		return gson.toJson(setOrder);
	}

	@Override
	public String selectPageCount(HttpServletRequest request) {
		String search=request.getParameter("customer");
		if(search==null) {
			search="";
		}		
		Page page = new Page();
		int totalnumber = sbm.selectPageCount("%"+search+"%");
		page.setTotalRecords(totalnumber+"");
		int pagenumber = (int)Math.ceil((double)totalnumber/(double)perPageNum);
		page.setTotal(pagenumber+"");
		return gson.toJson(page);
	}

	@Override
	public String selectCustomerName() {
		List<CustomerInfo> listCustomer=sbm.selectCustomerName();
		return gson.toJson(listCustomer);
	}

	@Override
	public String selectAccountName() {
		List<SystemAccount> listAccount=sbm.selectAccountName();
		return gson.toJson(listAccount);
	}

	@Override
	public String selectCommodityName() {
		List<SystemCommodityInformation> listCommodity =sbm.selectCommodityName();
		return gson.toJson(listCommodity);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String insertSale(HttpServletRequest request) {
		String customerId =request.getParameter("customerId");
		String accountId = request.getParameter("accountId");
		String saleIfo = request.getParameter("sale");
		ArrayList<String> saleArr = (ArrayList) gson.fromJson(saleIfo,Object.class);
		Map<String,Object> maps = new HashMap<>();
		int res = 0;
		double salePrice = 0;
		//循环得出订单总价
		for(int j=1;j<saleArr.size();j++) {
			Map<String,Object> map=(Map<String, Object>) gson.fromJson(saleArr.get(j), Map.class);			
			String string = (String) map.get("totalPrice");
			salePrice += Double.parseDouble(string);
		}
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		String stime = st.format(date);
		for(int i=1;i<saleArr.size();i++) {
			Map<String,Object> map=(Map<String, Object>) gson.fromJson(saleArr.get(i), Map.class);
			
			String commodityId = (String) map.get("commodityId");
			Double a = (Double) map.get("amount");
			int amount = a.intValue();
			String UnitPrice = (String) map.get("UnitPrice");//一件商品的单价
			String tp = (String) map.get("totalPrice");//一件商品的总价格
			double totalPrice = Double.parseDouble(tp);
			maps.put("id", UUID.randomUUID().toString());
			maps.put("receipts", str);
			maps.put("customerId", customerId);
			maps.put("saleTime", stime);
			maps.put("accountId", accountId);
			maps.put("commodityId", commodityId);
			maps.put("amount", amount);
			maps.put("UnitPrice", UnitPrice);
			maps.put("totalPrice", totalPrice);
			maps.put("salePrice", salePrice);
			res+= sbm.insertSale(maps);
		}
		Map<String,Object> mapss = new HashMap<>();
		mapss.put("moneyManId", UUID.randomUUID().toString());
		mapss.put("customerId", customerId);
		mapss.put("orderId", str);
		mapss.put("allMoney", salePrice);
		mapss.put("paidAmount", salePrice);
		mapss.put("unpaidAmount", 0);
		mapss.put("completeTime", new Date());
		sbm.insertMMTM(mapss);	
		
		Map<String,Object> mapsGoods = new HashMap<>();
		for(int i=1;i<saleArr.size();i++) {
			Map<String,Object> map=(Map<String, Object>) gson.fromJson(saleArr.get(i), Map.class);
			
			String commodityId = (String) map.get("commodityId");
			Double a = (Double) map.get("amount");
			int amount = a.intValue();
			String goodsType = "出库";
			String tp = (String) map.get("totalPrice");//一件商品的总价格
			double totalPrice = Double.parseDouble(tp);
			mapsGoods.put("goodsOutInId", UUID.randomUUID().toString());
			mapsGoods.put("warehouseId", 1);//商品仓库
			mapsGoods.put("materialInventoryAmount", amount);
			mapsGoods.put("commodityId", commodityId);
			mapsGoods.put("materialInventoryValue", totalPrice);
			mapsGoods.put("goodsType", goodsType);
			mapsGoods.put("time", new Date());
			sbm.insertGOIS(mapsGoods);
			GoodsOutInStoreServiceImpl goisi =new GoodsOutInStoreServiceImpl();
			//goisi.deleteGoodsOutInStore(mapsGoods);
		}
		
		return res>0?"YES":"NO";
	}

	@Override
	public String selectDetailsByReceipts(HttpServletRequest request) {
		String Receipts = request.getParameter("Receipts");
		List<SaleBillsDetailResult> saleBillsDetailResult=sbm.selectDetailsByReceipts(Receipts);	
		return gson.toJson(saleBillsDetailResult);
	}

}
