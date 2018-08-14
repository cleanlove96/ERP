/**
 * 
 */
package com.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.CapacityInfoMapper;
import com.mapper.SystemCommodityInformationMapper;
import com.model.CapacityInfo;
import com.model.SystemCommodityInformation;
import com.pojo.CapacityInfor;
import com.pojo.Page;
import com.service.CapacityService;

/**
 * @Author QinPeng
 *
 * @Date 2018年6月23日
 */
@Service
public class CapacityServiceImpl implements CapacityService {
	private static final int numPerPage=5;
	@Resource
	private CapacityInfoMapper cim;
	@Resource
	private SystemCommodityInformationMapper scim;
	/**
	 * 添加数据信息
	 * @param req
	 * @param capacityInfo
	 * @return SUCCESS OR ERROR OR NAME_ERROR
	 */
	@Override
	public String addCapacityInfo(HttpServletRequest req,CapacityInfo capacityInfo) {
		if(capacityInfo==null) {
			return "ERROR";
		}
		int i=cim.selectByNameCount(capacityInfo.getCapacityProductionLineName());
		if(i>0) {
			return "NAME_ERROR";
		}
		capacityInfo.setCapacityId(UUID.randomUUID().toString());
		capacityInfo.setCapacityCreationTime(new Date());
		int c=cim.insert(capacityInfo);
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
		
		if(search==null) {
			search="";
		}
		
		int num=cim.getCapacityNum(search);
		int a=num/numPerPage;
		double b=(num+0.0)/numPerPage;
		int total=a<b?a+1:a;
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
		List<CapacityInfor> list=cim.selectLike(map);
		Gson gson=new Gson();
		return gson.toJson(list);
	}
	
	/**
	 * 通过传来的id判断是否存在，删除信息
	 * @param req
	 * @return SUCCESS OR ERROR 
	 */
	@Override
	public String updateStatus(HttpServletRequest req) {
		String capacityId=req.getParameter("capacityId");
		CapacityInfo capacityInfo =cim.selectByPrimaryKey(capacityId);
		String errorStr="ERROR";
		if(capacityInfo!=null&&capacityInfo.getCapacityId()!=null){
			int i=0;
			try {
				i=cim.deleteByPrimaryKey(capacityId);
			}catch(DataIntegrityViolationException e) {
				System.err.println("此项含关联项，无法删除!");
				errorStr="DataException_ERROR";
			}
			
			if(i>0) {
				return "SUCCESS";
			}
		}
		return errorStr;
	}
	
	
	/**
	 * 根具id查询信息，传给页面用于信息修改
	 * @param req
	 * @return
	 */
	@Override
	public void selectById(HttpServletRequest req) {
		String capacityId=req.getParameter("capacityId");
		CapacityInfo capacityInfo=cim.selectByPrimaryKey(capacityId);
		req.setAttribute("capacityInfo", capacityInfo);
		List<SystemCommodityInformation>list=scim.selectAllCommodity();
		req.setAttribute("list", list);
	}

	/**
	 * 根据修改界面传来的值更新数据库
	 * @param req
	 * @return SUCCESS OR ERROR
	 */
	@Override
	public String updateById(HttpServletRequest req,CapacityInfo capacityInfo) {
		System.out.println("-------updateById------"+capacityInfo);
		if(capacityInfo==null) {
			return "ERROR";
		}
		//int c=cim.selectByNameCount(capacityInfo.getCapacityProductionLineName());
		Map<String,String> map=new HashMap<>();
		map.put("capacityProductionLineName",capacityInfo.getCapacityProductionLineName());
		map.put("capacityId", capacityInfo.getCapacityId());
		int c=cim.selectByNameOrCount(map);
		if(c>0) {
			return "NAME_ERROR";
		}
		int i=cim.updateByPrimaryKey(capacityInfo);
		if(i>0) {
			return "SUCCESS";
		}
		return "ERROR";
	}

	
}
