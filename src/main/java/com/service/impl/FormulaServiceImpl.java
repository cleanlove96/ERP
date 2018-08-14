package com.service.impl;

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
import com.mapper.SysMaterialMapper;
import com.mapper.SystemCommodityInformationMapper;
import com.model.Formula;
import com.model.SysMaterial;
import com.model.SystemCommodityInformation;
import com.pojo.FormulaList;
import com.pojo.FormulaTable;
import com.pojo.Page;
import com.service.FormulaService;

/**
 * @Author QinPeng
 *
 * @Date 2018年6月23日
 */
@Service
public class FormulaServiceImpl implements FormulaService {
	private static final int numPerPage=5;
	@Resource
	private FormulaMapper fm;
	@Resource
	private SysMaterialMapper smm;
	@Resource
	private SystemCommodityInformationMapper ssmm;
	/**
	 * 添加数据信息
	 * @param req
	 * @param formula
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
	
	@Override
	public String addFormula(HttpServletRequest req) {
		String commodityId=req.getParameter("commodityId");
		if(commodityId==null){
			return "ERROR";
		}
		String material=req.getParameter("material");
		System.err.println("================="+material);
		Gson gson=new Gson();
		ArrayList<String> list=(ArrayList) gson.fromJson(material, Object.class);
		Formula formula=new Formula();
		formula.setCommodityId(commodityId);
		
		for(String str:list) {
			Map map=gson.fromJson(str, Map.class);
			String materialId=(String) map.get("materialId");
			int formulaCount=Integer.parseInt((String) map.get("formulaCount")) ;
			System.out.println("--------------------"+materialId);
			System.out.println("--------------------"+formulaCount);
			formula.setMaterialId(materialId);
			formula.setFormulaCount(formulaCount);
			formula.setFormulaId(UUID.randomUUID().toString());
			formula.setFormulaCreateTime(new Date());
			int c=fm.insert(formula);
			if(c==0) {
				return "ERROR";
			}
		}
		return "SUCCESS";
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
		
		int num=fm.getComIdNum(search);
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
		Map<String,Object> map=new HashMap<>();
		map.put("search", search);
		map.put("start",start);
		map.put("numPerPage",numPerPage);
		List<FormulaList> list=fm.getComId(map);
		for (FormulaList formulaList : list) {
			formulaList.setList(fm.getDataByComId(formulaList.getCommodityId()));
		}
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
		String formulaId=req.getParameter("formulaId");
		Formula formula =fm.selectByPrimaryKey(formulaId);
		if(formula!=null){
			int i=fm.deleteByPrimaryKey(formulaId);
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
		String formulaId=req.getParameter("formulaId");
		Formula formula=fm.selectByPrimaryKey(formulaId);
		req.setAttribute("formula", formula);
		List<SysMaterial>list=smm.selectSysMaterialAll();
		req.setAttribute("materialList", list);
		List<SystemCommodityInformation>commodityList=ssmm.selectAllCommodity();
		req.setAttribute("commodityList", commodityList);
		
	}

	/**
	 * 根具修改界面传来的信息更新数据库
	 * @param req
	 * @return SUCCESS OR ERROR
	 */
	@Override
	public String updateById(HttpServletRequest req,Formula formula) {
		System.out.println("-------updateById------"+formula);
		if(formula==null) {
			return "ERROR";
		}
		int i=fm.updateByPrimaryKey(formula);
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
		List<SysMaterial>list=smm.selectSysMaterialAll();
		req.setAttribute("materialList", list);
		List<SystemCommodityInformation>commodityList=ssmm.selectAllCommodity();
		req.setAttribute("commodityList", commodityList);
		return "/view/formula/formula_add.jsp";
	}
	
}
