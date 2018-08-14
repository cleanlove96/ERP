package com.mapper;

import java.util.List;

import com.model.SectionRole;
import com.pojo.Ztree;

public interface SectionRoleMapper {
	int deleteByPrimaryKey(String sectionRoleId);

    int insert(SectionRole record);

    int insertSelective(SectionRole record);

    SectionRole selectByPrimaryKey(String sectionRoleId);

    int updateByPrimaryKeySelective(SectionRole record);

    int updateByPrimaryKey(SectionRole record);

	/**
	 * <p>
	 * 查询所有的nodes
	 * </p>
	 * @author zm
	 * @Date 2018年6月26日
	 * @return
	 */
	List<Ztree> getAllNodes();

	/**
	 * 
	 * 
	 *<p>根据传入的角色Id，查询出部门和角色中间表的信息</p>
	 *@author lily
	 *@param 传入的参数
	 *@return 返回值类型
	 *
	 */
	SectionRole selsectSectionRoleAllByRoleId(String roleId);

	SectionRole selectBysectionId(String sectionId);


}

