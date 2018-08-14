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

import com.google.gson.Gson;
import com.mapper.CapacityInfoMapper;
import com.mapper.RopTableMapper;
import com.mapper.SystemCommodityInformationMapper;
import com.model.CapacityInfo;
import com.model.RopTable;
import com.model.SystemCommodityInformation;
import com.pojo.Page;
import com.pojo.Rop;
import com.service.RopTableService;

/**
 * @Author QinPeng
 *
 * @Date 2018年6月23日
 */
@Service
public class RopTableServiceImpl implements RopTableService {
	private static final int numPerPage=5;
	@Resource
	private RopTableMapper rtm;
	@Resource
	private CapacityInfoMapper cim;
	@Resource
	private SystemCommodityInformationMapper scim;
	/**
	 * 添加数据信息
	 * @param req
	 * @param ropTable
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
	@Override
	public String addRopTable(HttpServletRequest req,RopTable ropTable) {
		System.err.println("-------------------+ropTable"+ropTable);
		if(ropTable==null) {
			return "ERROR";
		}
		/*int i=rtm.selectByNameCount(ropTable.getWarehouseName());
		if(i>0) {
			return "NAME_ERROR";
		}*/
		//时间需要获取表单的
		ropTable.setRopProductionTime(new Date());
		ropTable.setRopWarehouseEntryTime(new Date());
		ropTable.setRopId(UUID.randomUUID().toString());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		int c=rtm.insert(ropTable);
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
		
		int num=rtm.getRopTableNum(search);
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
		Map<String,Object> map=new HashMap<>();
		map.put("search", search);
		map.put("start",start);
		map.put("numPerPage",numPerPage);
		List<Rop> list=rtm.selectLike(map);
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
		return "/view/rop/rop_add.jsp";
	}







	
}
