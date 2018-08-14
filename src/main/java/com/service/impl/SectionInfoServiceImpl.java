package com.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mapper.SectionInfoMapper;
import com.mapper.SectionRoleMapper;
import com.model.SectionInfo;
import com.model.SystemRole;
import com.pojo.Page;
import com.service.SectionInfoService;






@Service
public class SectionInfoServiceImpl implements SectionInfoService {
	
	@Resource
	private SectionInfoMapper si;
	@Resource
	private SectionRoleMapper sr;
	
	private static Gson gson=new Gson();

	@Override
	public String getInfoTable(HttpServletRequest request) {
		String n=request.getParameter("n");
		String s=request.getParameter("sreach");
		int pageRecords = Integer.parseInt(request.getParameter("pageRecords"));
		System.err.println(s);
		if(s==null) {
			s="";
		}
		int pnum=1;
		if(n!=null&&n.length()>0) {
			pnum=Integer.parseInt(n);
		}
		Map<String, Object> map=new HashMap<>();
		int start=(pnum-1)*pageRecords;
		map.put("start", start);
		map.put("size", pageRecords);
		map.put("s", "%"+s+"%");
		List<SectionInfo> sm=si.getInfoTable(map);
		return gson.toJson(sm);
	}

	@Override
	public String getPage(HttpServletRequest request) {
				String s = request.getParameter("sreach");
				int pageRecords = Integer.parseInt(request.getParameter("pageRecords"));
				System.err.println(s);
				if(s==null){
					s="";
				}
				Page p =new Page();
				int tn = si.getTotalNum("%"+s+"%");
				p.setTotalRecords(tn+"");
				int t=0;
				if( tn%pageRecords!=0) {
					t=tn/pageRecords+1;
				}else {
					t=tn/pageRecords;
				}
				p.setTotal(t+"");
				return gson.toJson(p);
	}

	@Override
	public String block(HttpServletRequest request) {
        String sectionId = request.getParameter("sectionId");
        SectionInfo sf= si.selectByPrimaryKey(sectionId);
        if(sr.selectBysectionId(sectionId)!=null) {
        	return "ERROR";
        }else {
        if(sf.getSectionStatus().equals("0")){
        	sf.setSectionStatus("1");
        }
        si.updateByPrimaryKeySelective(sf);
		return "SUCCESS";
        }
	}

	@Override
	public String insetinfo(HttpServletRequest request) {
		String sectionName = request.getParameter("sectionName");
		String sectionImg  = request.getParameter("sectionImg");

		if(si.selectBySectionName(sectionName)==null) {
			SectionInfo sf = new SectionInfo();
			sf.setSectionId(UUID.randomUUID().toString());
			sf.setSectionName(sectionName);
			sf.setSectionStatus("0");
			sf.setSectionImg(sectionImg);
			si.insertSelective(sf);
			return "SUCCESS";
		}else {
			return "ERROR";
	}
		
 }

	@Override
	public SectionInfo selectById(String sectionId) {

		return si.selectByPrimaryKey(sectionId);
	}

	@Override
	public void selectById(HttpServletRequest request) {
		 String sectionId = request.getParameter("sectionId");
		 SectionInfo sf= si.selectByPrimaryKey(sectionId);
			String s = (sf.getSectionImg());
			s = s.replace("/","");
			s = s.replace(".","");
		 request.setAttribute("s", s);
		 request.setAttribute("sf", sf);
	}

	@Override
	public String update(HttpServletRequest request) {

		 SectionInfo sf = new SectionInfo();
		 sf.setSectionId(request.getParameter("sectionId"));
		 sf.setSectionName(request.getParameter("sectionName"));
		 sf.setSectionImg(request.getParameter("sectionImg"));
		 si.updateByPrimaryKeySelective(sf);
		 return "SUCCESS";

      }

	@Override
	public String start(HttpServletRequest request) {
		 String sectionId = request.getParameter("sectionId");
	        SectionInfo sf= si.selectByPrimaryKey(sectionId);
	        if(sf.getSectionStatus().equals("1")){
	        	sf.setSectionStatus("0");
	        }
	        si.updateByPrimaryKeySelective(sf);
			return null;
	}

	@Override
	public void selectAll(HttpServletRequest request) {
		List<SectionInfo>  sf = si.selectAll();
		request.setAttribute("sf", sf);
		
	}
				
}
