package com.service;

import javax.servlet.http.HttpServletRequest;

public interface SaleService {

	String selectSale(HttpServletRequest request);

	String selectPageCount(HttpServletRequest request);

	String selectCustomerName();

	String selectAccountName();

	String selectCommodityName();

	String insertSale(HttpServletRequest request);

	String selectDetailsByReceipts(HttpServletRequest request);

}
