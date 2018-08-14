package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.SectionInfo;

public interface SectionInfoMapper {
    int deleteByPrimaryKey(String sectionId);

    int insert(SectionInfo record);

    int insertSelective(SectionInfo record);

    SectionInfo selectByPrimaryKey(String sectionId);

    int updateByPrimaryKeySelective(SectionInfo record);

    int updateByPrimaryKey(SectionInfo record);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年6月25日
	 * @param map
	 * @return
	 */
	List<SectionInfo> getInfoTable(Map<String, Object> map);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年6月25日
	 * @param string
	 * @return
	 */
	int getTotalNum(String string);

	/**
	 * <p>
	 * 
	 * </p>
	 * @author zm
	 * @Date 2018年6月25日
	 * @param sectionName
	 * @return
	 */
	Object selectBySectionName(String sectionName);


	/**
	 * <p>
	 * 查询所有的部门信息
	 * </p>
	 * @author zm
	 * @Date 2018年6月26日
	 * @return
	 */
	List<SectionInfo> selectAllSectionInfo();


	List<SectionInfo> selectAll();
	/**
	 * 
	 * <p>查询请假人部门名</p>
	 * @author QJ
	 * @version 1.0
	 * @return
	 * @da2018年7月5日
	 */
	SectionInfo seeLeaveAccountSection(String accountId);

}