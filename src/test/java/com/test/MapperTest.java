package com.test;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mapper.SysRoleMapper;
import com.model.SysRole;
import com.model.SystemRole;
import com.service.WelcomeInfoService;

/**
 * 
 * <h2>用于做测试的类</h2>
 * <p>该类中包含了对于Mapoer层进行测试的方法。</p>
 * @author 青阳龙野(kohgylw)
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)//将测试运行环境指定为SpringIOC环境
@ContextConfiguration("classpath:spring.xml")//设置IOC容器使用的配置文件
public class MapperTest {
	
	@Resource
	private SysRoleMapper srm;
	@Resource
	private WelcomeInfoService wis;
	
	@Test
	@Transactional//想要不影响数据库，可以使用事物管理，前提是必须在配置文件中配置了事物相关的内容
	@Rollback//测试过程在结束后会回滚一切内容
	@Repeat(10)//先来个10遍
	public void test() {
		SysRole sr=new SysRole();
		String id=UUID.randomUUID().toString();
		sr.setRoleId(id);
		sr.setRoleName("新的测试角色");
		srm.insert(sr);
		SysRole sr2=srm.selectByPrimaryKey(id);
		System.out.println(sr2.getRoleName());
	}

}
