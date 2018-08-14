package com.service;

import javax.servlet.http.HttpServletRequest;

public interface OrderService {

	String selectOrder(HttpServletRequest request);

	String selectPageCount(HttpServletRequest request);

	String selectDetailsByOrderReceipts(HttpServletRequest request);

	String addOrder(HttpServletRequest request);

}
