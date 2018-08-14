package com.service;

import javax.servlet.http.HttpServletRequest;

public interface MaterialStorageService {

	String getInfoTable(HttpServletRequest request);

	String getPage(HttpServletRequest request);

	String getwarehouse(HttpServletRequest request);

}
