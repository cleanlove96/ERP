/*****************************************************
 *  HISTORY
 *  FileName:MaterialAddAsLogAspect.java
 *  Package:com.aspect
 *  Project:ERPSystem
 *  Version:1.0
 *  Date:2018年7月8日 zm创建文件
 **********修改记录*************
 * Date:          Author:
 *
 *******************************************************/
package com.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.mapper.SysMaterialMapper;
import com.mapper.SystemAccountMapper;
import com.mapper.materialLogMapper;
import com.model.SystemAccount;
import com.model.SystemLoginLog;
import com.model.materialLog;

/**
 * <p>
 * 增加原料信息，完成后进行操作日志的增加记录
 * </p>	
 * @Copyright (C),华清远见
 * @author zm
 * @Date:2018年7月8日
 */
@Aspect
@Component
public class MaterialAddAsLogAspect {
	@Autowired
	private  SysMaterialMapper smm;
	@Autowired
	private  SystemAccountMapper sam;
	@Autowired
	private materialLogMapper mlm;
	
	private  SimpleDateFormat sdf=new SimpleDateFormat("YYYY年MM月dd日 hh:mm:ss");
	/***
	 * 
	 * <p>
	 * 增加原料基本信息的日志记录的添加
	 * </p>
	 * @author zm
	 * @Date 2018年7月8日
	 * @param jp 切面切切点
	 * @param result
	 */
	@AfterReturning(value="execution(* com.service.MaterialInfoService.SysMaterialInfoAdd(..))",returning="result")
	public  void addMaterial(JoinPoint jp,String result) {
		if(result.equals("succeed")) {
			Object[] args = jp.getArgs();
			HttpServletRequest request=(HttpServletRequest) args[0];
			//获取保存在session中当前操作对象的登陆id
			String accountId =(String) request.getSession().getAttribute("ACCOUNT");
			//根据ID查找到当前这个对象
			SystemAccount account= sam.selectByPrimaryKey(accountId);
			//根据对象获取到当前这个人的username;
			String username= account.getAccountLoginId();
			//开启一个线程 把log日志表的记录插入进去。
			Thread t=new Thread(()->{
				Date d=new Date();
				materialLog mtl=new materialLog();			
				mtl.setSysMaterialLogId(UUID.randomUUID().toString());
				mtl.setAccountLoginId(username);
				mtl.setMaterialLogDate(sdf.format(d));		
				mtl.setMaterialLogType("增加了原料信息");
				mlm.insert(mtl);				
				});
			t.start();
		}
	
	}
	/****
	 * 
	 * <p>
	 * 修改原料基本信息，进行日志记录的添加
	 * </p>
	 * @author zm
	 * @Date 2018年7月8日
	 * @param jp 切点，切面切切点
	 * @param result
	 */
	@AfterReturning(value="execution(* com.service.MaterialInfoService.updateBySysMaterialId(..))",returning="result")
	public void updateMaterial(JoinPoint jp,String result) {
		if(result.equals("succee")) {
			Object[] args=jp.getArgs();
			HttpServletRequest request=(HttpServletRequest) args[0];
			//获取保存在session中当前操作对象的登陆id
			String accountId =(String) request.getSession().getAttribute("ACCOUNT");
			//根据ID查找到当前这个对象
			SystemAccount account= sam.selectByPrimaryKey(accountId);
			//根据对象获取到当前这个人的username;
			String username= account.getAccountLoginId();
			Thread t=new Thread(()->{
				//将一个商品信息日志对象set进去
				Date d=new Date();
				materialLog ml=new materialLog();
				ml.setSysMaterialLogId(UUID.randomUUID().toString());
				ml.setAccountLoginId(username);
				ml.setMaterialLogDate(sdf.format(d));		
				ml.setMaterialLogType("修改了原料信息");
				mlm.insert(ml);
			});		
			//开启一个线程
			t.start();
			System.out.println("调用了增加修改信息日志吗");
		}
	}
	/****
	 * 
	 * <p>
	 * 原料信息的状态进行更够后加入日志记录
	 * </p>
	 * @author zm
	 * @Date 2018年7月8日
	 * @param jp 切面切切点
	 * @param result
	 */
	@AfterReturning(value="execution(* com.service.MaterialInfoService.updateBySysMaterialStatus(..))",returning="result")
	public void deleteMaterial(JoinPoint jp,String result) {
		if(result.equals("succee")) {
			Object[] args=jp.getArgs();
			HttpServletRequest request=(HttpServletRequest) args[0];
			//获取保存在session中当前操作对象的登陆id
			String accountId =(String) request.getSession().getAttribute("ACCOUNT");
			//根据ID查找到当前这个对象
			SystemAccount account= sam.selectByPrimaryKey(accountId);
			//根据对象获取到当前这个人的username;
			String username= account.getAccountLoginId();
			Thread t=new Thread(()->{
				//将一个商品信息日志对象set进去
				Date d=new Date();
				materialLog ml=new materialLog();
				ml.setSysMaterialLogId(UUID.randomUUID().toString());
				ml.setAccountLoginId(username);
				ml.setMaterialLogDate(sdf.format(d));		
				ml.setMaterialLogType("修改了原料信息的状态");
				mlm.insert(ml);
			});			
			//开启一个线程
			t.start();
			System.out.println("调用了增加修改状态日志吗");
		}
	}
}
