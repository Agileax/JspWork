package com.renymvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.renymvc.annotation.AjaxAccess;
import com.renymvc.annotation.Controller;

@Controller(value="/test")
public class TestController {

	public String index(HttpServletRequest request, HttpServletResponse response){
		return "test";
	}
	
	@AjaxAccess
	public Map<String, Object> data(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", "一百");
		List<String> list = new ArrayList<String>();
		list.add("任英鑫");
		list.add("任英鑫");
		list.add("任英鑫");
		map.put("names", list);
		return map;
	}
}
