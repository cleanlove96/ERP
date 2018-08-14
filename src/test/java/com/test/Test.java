/**
 * 
 */
package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * @Author QinPeng
 *
 * @Date 2018年7月1日
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Map<String,String> map=new HashMap<>();
			map.put("ads", "sd");
			map.put("a3ds", "3sd");
			Gson g=new Gson();
			ArrayList list=new ArrayList();
			list.add(map);
			g.toJson(list);
			System.out.println(g.toJson(list));
	}

}
