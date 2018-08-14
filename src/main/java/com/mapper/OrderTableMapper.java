package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.OrderTable;
import com.model.OrderTableSelectDetailResult;
import com.model.OrderTableSelectResult;

public interface OrderTableMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderTable record);

    int insertSelective(OrderTable record);

    OrderTable selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderTable record);

    int updateByPrimaryKey(OrderTable record);

	List<OrderTableSelectResult> selectOrder(Map<String, Object> map);

	int selectPageCount(String string);

	List<OrderTableSelectDetailResult> selectDetailsByOrderReceipts(String orderReceipts);

	int addOrder(Map<String, Object> maps);
}