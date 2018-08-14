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

import com.mapper.SystemAccountMapper;
import com.mapper.WareHouseLogMapper;
import com.model.SystemAccount;
import com.model.WareHouseLog;

@Aspect
@Component
public class WareHouseLogAspect {
	@Autowired
	private WareHouseLogMapper whlm;
	@Autowired
	private SystemAccountMapper sam;
	private SimpleDateFormat sdf=new SimpleDateFormat("YYYY年MM月dd日 hh:mm:ss");
	
	@AfterReturning(value="execution(* com.service.WareHouseService.addWareHouse(..))",returning="result")
	public void doAddLog(JoinPoint jp,String result) {
		System.err.println("result:"+result);
		
		if(result.equals("SUCCESS")) {
			Object[] args=jp.getArgs();
			HttpServletRequest request=(HttpServletRequest) args[0];
			String accountId =(String) request.getSession().getAttribute("ACCOUNT");
			SystemAccount account= sam.selectByPrimaryKey(accountId);

			String username= account.getAccountLoginId();
			
			Thread t=new Thread(()->{
				Date d=new Date();
				WareHouseLog wareHouseLog=new WareHouseLog();
				wareHouseLog.setWarehouseLogId(UUID.randomUUID().toString());
				wareHouseLog.setAccountLoginId(username);
				wareHouseLog.setOpDate(sdf.format(d));
				wareHouseLog.setOpType("添加信息");
				whlm.insert(wareHouseLog);
			});
			t.start();
		}
		
	}
	
	@AfterReturning(value="execution(* com.service.WareHouseService.updateStatus(..))",returning="result")
	public void doDelLog(JoinPoint jp,String result) {
		if(result.equals("SUCCESS")) {
			Object[] args=jp.getArgs();
			HttpServletRequest request=(HttpServletRequest) args[0];
			String accountId =(String) request.getSession().getAttribute("ACCOUNT");
			SystemAccount account= sam.selectByPrimaryKey(accountId);
			String username= account.getAccountLoginId();
			Thread t=new Thread(()->{
				Date d=new Date();
				WareHouseLog wareHouseLog=new WareHouseLog();
				wareHouseLog.setWarehouseLogId(UUID.randomUUID().toString());
				wareHouseLog.setAccountLoginId(username);
				wareHouseLog.setOpDate(sdf.format(d));
				wareHouseLog.setOpType("删除数据");
				whlm.insert(wareHouseLog);
			});
			t.start();
		}
	}
	@AfterReturning(value="execution(* com.service.WareHouseService.updateById(..))",returning="result")
	public void doUpdateLog(JoinPoint jp,String result) {
		if(result.equals("SUCCESS")) {
			Object[] args=jp.getArgs();
			HttpServletRequest request=(HttpServletRequest) args[0];
			String accountId =(String) request.getSession().getAttribute("ACCOUNT");
			SystemAccount account= sam.selectByPrimaryKey(accountId);

			String username= account.getAccountLoginId();
			Thread t=new Thread(()->{
				Date d=new Date();
				WareHouseLog wareHouseLog=new WareHouseLog();
				wareHouseLog.setWarehouseLogId(UUID.randomUUID().toString());
				wareHouseLog.setAccountLoginId(username);
				wareHouseLog.setOpDate(sdf.format(d));
				wareHouseLog.setOpType("修改数据");
				whlm.insert(wareHouseLog);
			});
			t.start();
		}
	}

}
