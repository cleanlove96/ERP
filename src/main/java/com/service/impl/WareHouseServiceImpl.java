/**
 * 
 */
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
import com.mapper.WareHouseMapper;
import com.model.WareHouse;
import com.pojo.Page;
import com.service.WareHouseService;

/**
 * @Author QinPeng
 *
 * @Date 2018年6月23日
 */
@Service
public class WareHouseServiceImpl implements WareHouseService {
	private static final int numPerPage=5;
	@Resource
	private WareHouseMapper whm;

	/**
	 * 添加数据信息
	 * @param req
	 * @param wareHouse
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
	@Override
	public String addWareHouse(HttpServletRequest req,WareHouse wareHouse) {
		if(wareHouse==null) {
			return "ERROR";
		}
		int i=whm.selectByNameCount(wareHouse.getWarehouseName());
		if(i>0) {
			return "NAME_ERROR";
		}
		wareHouse.setWarehouseId(UUID.randomUUID().toString());
		wareHouse.setWarehouseStatus("1");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		wareHouse.setCreateDate(sdf.format(new Date()));
		int c=whm.insert(wareHouse);
		if(c>0) {
			return "SUCCESS";
		}
		return "ERROR";
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
		
		int num=whm.getWareHouseNum(search);
		int a=num/numPerPage;
		double b=(num+0.0)/numPerPage;
		int total=a<b?a+1:a;
		System.err.println("total:"+total+","+a+":"+b);
		Page page=new Page();
		page.setTotal(String.valueOf(total));
		page.setTotalRecords(String.valueOf(num));
		System.out.println("Total:"+page.getTotal()+" TotalRecords:"+page.getTotalRecords());
		Gson gson=new Gson();
		req.setAttribute("num",num);
		System.out.println(gson.toJson(page));
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
		Map<String,Object> map=new HashMap();
		map.put("search", search);
		map.put("start",start);
		map.put("numPerPage",numPerPage);
		List<WareHouse> list=whm.selectLike(map);
		Gson gson=new Gson();
		
		return gson.toJson(list);
	}
	
	/**
	 * 通过传来的id判断是否存在，若在修改状态表示被删除
	 * @param req
	 * @return SUCCESS OR ERROR 
	 */
	@Override
	public String updateStatus(HttpServletRequest req) {
		String warehouseId=req.getParameter("warehouseId");
		WareHouse wareHouse =whm.selectByPrimaryKey(warehouseId);
		if(wareHouse!=null&&wareHouse.getWarehouseId()!=null){
			
			wareHouse.setWarehouseStatus("0");
			int i=whm.updateByPrimaryKey(wareHouse);
			if(i>0) {
				return "SUCCESS";
			}
		}
		return "ERROR";
	}
	
	/**
	 * 根具多个id查询信息，传给页面用于信息修改
	 * @param req
	 * @return SUCCESS OR ERROR 
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String updateSelectStatus(String ids) {
		
		System.err.println("ids:"+ids);
		Gson gson=new Gson();
		List<String> idss=(List<String>) gson.fromJson(ids,Object.class);
		if(ids!=null){
			System.err.println("_____________________"+idss.size());
			for(String id:idss) {
				System.err.println(id);
				WareHouse wareHouse =whm.selectByPrimaryKey(id);
				System.err.println(wareHouse);
				if(wareHouse!=null&&wareHouse.getWarehouseId()!=null){
					wareHouse.setWarehouseStatus("0");
					int i=whm.updateByPrimaryKey(wareHouse);
					
					if(i<=0) {
						throw new RuntimeException("批量删除数据异常！");
					}
				}
			}
			return "SUCCESS";
		}
		return "ERROR";
	}
	
	/**
	 * 根具id查询信息，传给页面用于信息修改
	 * @param req
	 * @return
	 */
	@Override
	public void selectById(HttpServletRequest req) {
		String warehouseId=req.getParameter("warehouseId");
		WareHouse warehouse=whm.selectByPrimaryKey(warehouseId);
		req.setAttribute("warehouse", warehouse);
	}

	/**
	 * 根具id查询信息，传给页面用于信息修改
	 * @param req
	 * @return SUCCESS OR ERROR
	 */
	@Override
	public String updateById(HttpServletRequest req,WareHouse wareHouse) {
		System.out.println("-------updateById------"+wareHouse);
		if(wareHouse==null) {
			return "ERROR";
		}
		Map<String,String> map=new HashMap<>();
		map.put("warehouseName", wareHouse.getWarehouseName());
		map.put("warehouseId", wareHouse.getWarehouseId());
		int c=whm.selectByNameOrIDCount(map);
		if(c>0) {
			return "NAME_ERROR";
		}
		int i=whm.updateByPrimaryKey(wareHouse);
		if(i>0) {
			return "SUCCESS";
		}
		return "ERROR";
	}







	
}
