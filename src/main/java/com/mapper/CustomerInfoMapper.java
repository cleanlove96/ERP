package com.mapper;

import java.util.List;
import java.util.Map;

import com.model.CustomerInfo;

public interface CustomerInfoMapper {
    int deleteByPrimaryKey(String customerId);

    int insert(CustomerInfo record);

    int insertSelective(CustomerInfo record);

    CustomerInfo selectByPrimaryKey(String customerId);

    int updateByPrimaryKeySelective(CustomerInfo record);

    int updateByPrimaryKey(CustomerInfo record);

	List<CustomerInfo> getCustomerTable(Map<String, Object> map);
	int deleteCustomer(String id);
	int backCustomer(String id);
	int getTotalNum(Map<String, String> map);

	/**
	 * 搜索所有的供应商
	 * @param demand
	 * @return
	 */
	List<CustomerInfo> selectByDemand(String demand);
	


}