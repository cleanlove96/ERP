package com.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.FormulaMapper;
import com.mapper.PurchaseMapper;
import com.mapper.PurchaseTableMapper;
import com.mapper.SysMaterialMapper;
import com.mapper.YearpanlTableMapper;
import com.model.Formula;
import com.model.Purchase;
import com.model.PurchaseTable;
import com.model.SysMaterial;
import com.model.SystemAccount;
import com.model.YearpanlTable;
import com.pojo.AccountPage;
import com.pojo.MyCommodity;
import com.pojo.MyPurchase;
import com.pojo.MyTimeChange;
import com.pojo.myFormula;
import com.service.PurchaseService;

/**
 * 
 * <h2>采购计划表实现层</h2>
 * <p>
 * 主要实现接口层所想要实现的方法功能
 * </p>
 * 
 * @author lily
 *
 *
 */
@Service
public class PurchaseServiceImpl extends MyTimeChange implements PurchaseService {
	private static final int numAccPage = 5;
	private Gson g = new Gson();
	// 采购计划单
	@Resource
	private PurchaseMapper pcm;
	// 年度总计划单
	@Resource
	private YearpanlTableMapper ytm;
	// 配方表单
	@Resource
	private FormulaMapper fomapper;
	// 原料表
	@Resource
	private SysMaterialMapper sysmm;
	//采购表
	@Resource
	private PurchaseTableMapper rtm;
	@Override 
	public String selectAllNeed(HttpServletRequest request) {
		// TODO Auto-generated method stub

		String year = request.getParameter("searchCard");
		// 根据年份查询出商品id
		List<YearpanlTable> yplanTablle = ytm.selectAccountIdByYear(year);
		List<MyPurchase> purchaseList = new ArrayList<MyPurchase>();

		List<MyCommodity> myCommodityList = new ArrayList<MyCommodity>();

		int myGoodsNumAll = 0;
		// 根据商品id，查询出原料和对应的数量,放入myCommodityList中
		for (YearpanlTable yearpanlTable : yplanTablle) {
			// 商品id
			String commodityId = yearpanlTable.getCommodityId();
			System.err.println(".....商品去重所有商品id....." + commodityId);
			// 计划数量
			int goodsSum = yearpanlTable.getGoodsSum();
			// 把得到的商品信息放入MyCommodity集合中
			MyCommodity myCommodityOnly = new MyCommodity();
			myCommodityOnly.setCommodityId(commodityId);
			myCommodityOnly.setGoodsSum(goodsSum);
			myCommodityList.add(myCommodityOnly);

		}
		// 去重，去掉商品重复的，把相同id的数量相加
		for (int i = 0; i < myCommodityList.size(); i++) {
			int num1 = myCommodityList.get(i).getGoodsSum();
			int sum = 0;
			for (int j = i + 1; j < myCommodityList.size(); j++) {
				if (myCommodityList.get(i).getCommodityId().equals(myCommodityList.get(j).getCommodityId())) {
					int num2 = myCommodityList.get(j).getGoodsSum();
					sum += num2;
					myCommodityList.remove(j);
					j--;
				}
			}
			myCommodityList.get(i).setGoodsSum(num1 + sum);
		}

		// 根据商品id,查出所有原料id及数量，放入自定义类中
		List<myFormula> myFormulaOnly = new ArrayList<>();
		for (MyCommodity myCommodity : myCommodityList) {
			// 对应商品的数量
			int goodsSumAll = myCommodity.getGoodsSum();

			// 根据商品id，查询出配方信息
			List<Formula> formulaTable = fomapper.selsectMaterialIdByCommodityId(myCommodity.getCommodityId());
			// 遍历获得原料的id和数量
			for (Formula formula : formulaTable) {
				myFormula myFormulaOleys = new myFormula();
				String materialId = formula.getMaterialId();
				int materialNum = formula.getFormulaCount();
				myFormulaOleys.setMaterialId(materialId);

				myFormulaOleys.setMaterialNum(materialNum * goodsSumAll);
				myFormulaOnly.add(myFormulaOleys);
			}
		}

		// 去重，把自定义原料表去重，相同的原料id对应的量加起来
		for (int i = 0; i < myFormulaOnly.size(); i++) {
			String materialId = myFormulaOnly.get(i).getMaterialId();

			int num1 = myFormulaOnly.get(i).getMaterialNum();
			int sum = 0;
			for (int j = i + 1; j < myFormulaOnly.size(); j++) {

				if (myFormulaOnly.get(i).getMaterialId().equals(myFormulaOnly.get(j).getMaterialId())) {
					int num = myFormulaOnly.get(j).getMaterialNum();
					sum += num;
					myFormulaOnly.remove(j);
					j--;
				}
			}
			sum = sum + num1;
			SysMaterial materialTable = sysmm.selectByPrimaryKey(materialId);
			MyPurchase myPurchaseOnly = new MyPurchase();
			myPurchaseOnly.setMaterialId(materialTable.getMaterialId());
			myPurchaseOnly.setMaterialName(materialTable.getMaterialName());
			myPurchaseOnly.setMaterialNum(sum);
			myPurchaseOnly.setMaterialUnit(materialTable.getMaterialUnit());
			double price = materialTable.getPrice();
			myPurchaseOnly.setMaterialPrice(price);
			myPurchaseOnly.setMaterialTotalPrices(price * sum);
			/**
			 * 这里调用方法，查询出未计划量
			 */
			int someNum=sum;
			List<Purchase> purchaseListAfter=pcm.selectPurchaseAll(year);
			for (int k = 0; k < purchaseListAfter.size(); k++) {
				int num11=purchaseListAfter.get(k).getPurchaseCount();
				int sum11=0;
				for (int q = k+1; q < purchaseListAfter.size(); q++) {				
					if(purchaseListAfter.get(k).getMaterialId().equals(purchaseListAfter.get(q).getMaterialId())) {
						int num22=purchaseListAfter.get(q).getPurchaseCount();
						sum11+=num22;
						purchaseListAfter.remove(q);
						q--;
					}
				}
				sum11=sum11+num11;
				if(purchaseListAfter.get(k).getMaterialId().equals(materialId)) {
					someNum=sum-sum11;
				}
			myPurchaseOnly.setMaterialNotPlan(someNum);
			
			}
			purchaseList.add(myPurchaseOnly);
		}

		return g.toJson(purchaseList);
	}

	@Override
	public String selectUnit(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String materialId = request.getParameter("myUnitIs");
		SysMaterial materialUnit = sysmm.selectByPrimaryKey(materialId);

		return g.toJson(materialUnit);
	}

	@Override
	public String myPurchaseAdd(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Purchase purchase = new Purchase();

		String materialId = request.getParameter("materialId");
		String purchaseCount = request.getParameter("purchaseCount");
		int count = Integer.parseInt(purchaseCount);
		String purchaseUnit = request.getParameter("purchaseUnit");
		String purchaseTime = request.getParameter("purchaseTime");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String price = request.getParameter("price");
		double money = Double.parseDouble(price);
		double moneyAll = money * count;
		SimpleDateFormat simf = new SimpleDateFormat("yyyy-MM-dd");
		purchase.setPurchaseId(UUID.randomUUID().toString());
		purchase.setMaterialId(materialId);
		purchase.setPrice(money);
		purchase.setPurchaseCount(count);
		purchase.setPurchaseTotalPrices(moneyAll);
		purchase.setPurchaseTime(purchaseTime);
		purchase.setPurchaseUnit(purchaseUnit);
		purchase.setPurchaseStatus("0");
		int num = 0;
		try {
			Date start = simf.parse(startTime);
			Date end = simf.parse(endTime);
			purchase.setEndTime(end);
			purchase.setStartTime(start);
			num = pcm.insertSelective(purchase);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (num > 0) {
			return "success";
		} else {
			return "notsuccess";
		}

	}

	@Override
	public String getPage(HttpServletRequest request) {
		// TODO Auto-generated method stub

		String searchCard = request.getParameter("searchCard");
		String myStartTime1 = request.getParameter("myStartTime1");
		String myEndTime1 = request.getParameter("myEndTime1");
		String myStartTime2 = request.getParameter("myStartTime2");
		String myEndTime2 = request.getParameter("myEndTime2");
		if(myStartTime1==null||myStartTime1.equals("")) {
			myStartTime1="1999-01-01";
		}
		if(myStartTime2==null||myStartTime2.equals("")) {
			myStartTime2="2999-12-31";
		}
		if(myEndTime1==null||myEndTime1.equals("")) {
			myEndTime1="1999-01-01";
		}
		if(myEndTime2==null||myEndTime2.equals("")) {
			myEndTime2="2999-12-31";
		}
	
		Map<String, Object> map = new HashMap<>();
		AccountPage apage = new AccountPage();		
		if (searchCard == null) {
			searchCard = "";
		}

		map.put("searchCard",searchCard);
		map.put("myStartTime1",myStartTime1);
		map.put("myEndTime1",myEndTime1);
		map.put("myStartTime2",myStartTime2);
		map.put("myEndTime2",myEndTime2);
		int tn = pcm.selectCount(map);
		apage.setTotalRecords(tn + "");
		int t1 = tn % numAccPage;
		int t = tn / numAccPage;
		if (t1 > 0) {
			t = t + 1;
		}
		apage.setTotal(t + "");
		return g.toJson(apage);

	}

	@Override
	public String getPurchaseTable(HttpServletRequest request) {
		String searchCard = request.getParameter("searchCard");
		String n = request.getParameter("n");
		String myStartTime1 = request.getParameter("myStartTime1");
		String myEndTime1 = request.getParameter("myEndTime1");
		String myStartTime2 = request.getParameter("myStartTime2");
		String myEndTime2 = request.getParameter("myEndTime2");
	
		if(myStartTime1==null||myStartTime1.equals("")) {
			
			myStartTime1="1990-01-01";
		}
		if(myStartTime2==null||myStartTime2.equals("")) {
			myStartTime2="2999-01-01";
		}
		if(myEndTime1==null||myEndTime1.equals("")) {
			myEndTime1="1990-01-01";
		}
		if(myEndTime2==null||myEndTime2.equals("")) {
			myEndTime2="2999-12-31";
		}
		Map<String, Object> map = new HashMap<>();
				
		if (searchCard == null) {
			searchCard = "";
		}
		int anum = 1;
		if (n != null && n.length() > 0) {
			anum = Integer.parseInt(n);
		}
		int start = (anum - 1) * numAccPage;
		map.put("start", start);
		map.put("size", numAccPage);
		map.put("searchCard",searchCard);
		map.put("myStartTime1",myStartTime1);
		map.put("myEndTime1",myEndTime1);
		map.put("myStartTime2",myStartTime2);
		map.put("myEndTime2",myEndTime2);
		System.err.println();
		int tn = pcm.selectCount(map);	

		List<Purchase> ptable = pcm.getPurchaseTable(map);
		for (Purchase purchase : ptable) {
			purchase.setMaterialId(sysmm.selectByPrimaryKey(purchase.getMaterialId()).getMaterialName());
			
		}
		
		List<Purchase> myPurchaseTablesss=new ArrayList<>();		
		for (int i = 0; i < ptable.size(); i++) {		
			myPurchaseTablesss.add(ptable.get(i));
			for (int j = i+1; j <ptable.size(); j++) {
				if(ptable.get(i).getMaterialId().equals(ptable.get(j).getMaterialId())) {
					myPurchaseTablesss.add(ptable.get(j));
					ptable.remove(j);
					j--;
				}
				
			}
		}
		
		return g.toJson(myPurchaseTablesss);

	}
	
	//查看所有的采购计划表的东西
	@Override
	public String selectPurchaseAll(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String searchCard=request.getParameter("searchCard");
		List<Purchase> purchaseList=pcm.selectPurchaseAll(searchCard);
		//遍历添加进去
		List<MyPurchase> myPurchaseTable=new ArrayList<>();		
		for (int i = 0; i < purchaseList.size(); i++) {
			int num1=purchaseList.get(i).getPurchaseCount();
			int sum=0;
			for (int j = i+1; j < purchaseList.size(); j++) {				
				if(purchaseList.get(i).getMaterialId().equals(purchaseList.get(j).getMaterialId())) {
					int num2=purchaseList.get(j).getPurchaseCount();
					sum+=num2;
					purchaseList.remove(j);
					j--;
				}
			}
			sum=sum+num1;
			MyPurchase myPurchaseOnly=new MyPurchase();		
			SysMaterial materialTable = sysmm.selectByPrimaryKey(purchaseList.get(i).getMaterialId());
			myPurchaseOnly.setMaterialId(materialTable.getMaterialId());
			myPurchaseOnly.setMaterialName(materialTable.getMaterialName());
			myPurchaseOnly.setMaterialNum(sum);
			myPurchaseOnly.setMaterialUnit(materialTable.getMaterialUnit());
			myPurchaseOnly.setMaterialPrice(materialTable.getPrice());
			myPurchaseOnly.setMaterialTotalPrices(materialTable.getPrice()*sum);						
			/**
			 * 未采购量方法
			 */
			int nnum=selectCountMM(materialTable.getMaterialId(),searchCard);
			
			myPurchaseOnly.setMaterialNotBuy(nnum-sum);
			//客户
			myPurchaseOnly.setCustomerId(materialTable.getCustomerId());
			myPurchaseTable.add(myPurchaseOnly);
		}  
		//显示

		
		return g.toJson(myPurchaseTable);
	}

	@Override
	public String deletePurchaseById(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String purchaseId=request.getParameter("id");
		int num=pcm.deleteByPrimaryKey(purchaseId);
		if(num>0) {
			return "success";	
		}else {
			return "notsuccess";	
		}
		
	}

	@Override
	public Purchase updatePurchaseTable(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String purchaseId=request.getParameter("purchaseId");
		 Purchase  purchase =pcm.selectByPrimaryKey(purchaseId);
		return purchase;
	}

	@Override
	public String purchaseUpdate(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Purchase purchase = new Purchase();
		String purchaseId = request.getParameter("purchaseId");
		String materialId = request.getParameter("materialId");
		String purchaseCount = request.getParameter("purchaseCount");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Purchase  purchaseTable =pcm.selectByPrimaryKey(purchaseId);
		double price=purchaseTable.getPrice();
		int count = Integer.parseInt(purchaseCount);
		double priceAll=count*price;
		
		SimpleDateFormat simf = new SimpleDateFormat("yyyy-MM-dd");
		int num=0;
		try {
			Date start = simf.parse(startTime);
			Date end = simf.parse(endTime);
			purchase.setPurchaseId(purchaseId);
			purchase.setMaterialId(materialId);
			purchase.setPurchaseCount(count);
			purchase.setPurchaseTotalPrices(priceAll);
			purchase.setEndTime(end);
			purchase.setStartTime(start);
			num = pcm.updateByPrimaryKeySelective(purchase);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (num > 0) {
			return "success";
		} else {
			return "notsuccess";
		}
		
	}
	//查找某一原料和年份的所有采购数量
	public int selectCountMM (String materialId,String searchCard ) {
		Map<String, Object> map = new HashMap<>();
		map.put("materialId",materialId);
		map.put("searchCard",searchCard);
		List<PurchaseTable> purchaseTable= rtm.selectCountByIdAndYear(map);
		int sum=0;
		for (int i = 0; i < purchaseTable.size(); i++) {
			int num1=purchaseTable.get(i).getPurchaseTableInt();
			sum+=num1;				
		}
		return sum;	
	}

	
	
}
