package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.Purchase;

public interface PurchaseMapper {
    int deleteByPrimaryKey(String purchaseId);

    int insert(Purchase record);

    int insertSelective(Purchase record);

    Purchase selectByPrimaryKey(String purchaseId);

    int updateByPrimaryKeySelective(Purchase record);

    int updateByPrimaryKey(Purchase record);

	List<Purchase> selectPurchaseAll(String year);

	int selectCount(Map<String, Object> map);

	List<Purchase> getPurchaseTable(Map<String, Object> map);
}