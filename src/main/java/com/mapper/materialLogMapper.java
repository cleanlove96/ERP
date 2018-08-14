package com.mapper;

import com.model.materialLog;
import com.model.materialLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface materialLogMapper {
    int countByExample(materialLogExample example);

    int deleteByExample(materialLogExample example);

    int insert(materialLog record);

    int insertSelective(materialLog record);

    List<materialLog> selectByExample(materialLogExample example);

    int updateByExampleSelective(@Param("record") materialLog record, @Param("example") materialLogExample example);

    int updateByExample(@Param("record") materialLog record, @Param("example") materialLogExample example);
}