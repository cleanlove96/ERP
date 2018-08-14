package com.service;

import javax.servlet.http.HttpServletRequest;

public interface materialOutService {

	String getInfoTable(HttpServletRequest request);

	String getPage(HttpServletRequest request);

	String getwarehouse(HttpServletRequest request);

}
