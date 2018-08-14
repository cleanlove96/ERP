package com.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class MyTimeChange {
	@InitBinder
	public void initBinder(ServletRequestDataBinder servletRequestDataBinder) {
		servletRequestDataBinder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
}
