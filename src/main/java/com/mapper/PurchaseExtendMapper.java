package com.mapper;

import com.model.PurchaseExtend;

public interface PurchaseExtendMapper {
    int deleteByPrimaryKey(String extendId);

    int insert(PurchaseExtend record);

    int insertSelective(PurchaseExtend record);

    PurchaseExtend selectByPrimaryKey(String extendId);

    int updateByPrimaryKeySelective(PurchaseExtend record);

    int updateByPrimaryKey(PurchaseExtend record);
}