package com.main.jspwork.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pokerwu.orm.DBOperate;
import com.pokerwu.orm.OrmBeanBase;
import com.renymvc.annotation.AjaxAccess;
import com.renymvc.annotation.Controller;

@Controller(value="/main")
public class CoreController {
	
	private DBOperate dao;
	
	{
		dao = OrmBeanBase.getInstance();
	}
	
	public String index(HttpServletRequest request, HttpServletResponse response){
		HttpSession hs = request.getSession();
		if (hs.getAttribute("LoginUser") == null){
			return "login";
		}
		return "index";
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response){
		return "login";
	}
	
	@AjaxAccess
	public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response){
		HttpSession hs = request.getSession();
		hs.removeAttribute("LoginUser");
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}
	
	@AjaxAccess
	public Map<String, Object> checkLogin(HttpServletRequest request, HttpServletResponse response){
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession hs = request.getSession();
		hs.setAttribute("LoginUser", new Object[]{userName, password});
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "true");
		return map;
	}
	
	@AjaxAccess
	public Map<String, Object> getInfoList(HttpServletRequest request, HttpServletResponse response){
		String model = request.getParameter("model");
		@SuppressWarnings("rawtypes")
		List list = null;
		try{
			Class<?> clazz = Class.forName(model);
			list = dao.queryForList(clazz, null, null, null);
		} catch (ClassNotFoundException e){
			list = new ArrayList<Object>();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		return map;
	}
	
	@AjaxAccess
	public Map<String, Object> deleteData(HttpServletRequest request, HttpServletResponse response){
		String model = request.getParameter("model");
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Class<?> clazz = Class.forName(model);
			dao.delete(clazz, id);
			map.put("result", true);
		}catch (ClassNotFoundException e){
			map.put("result", false);
		}
		
		return map;
	}
	
	@AjaxAccess
	public Map<String, Object> createData(HttpServletRequest request, HttpServletResponse response){
		String model = request.getParameter("model");
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Class<?> clazz = Class.forName(model);
			Object obj = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			Field.setAccessible(fields, true);
			for (Field f : fields){
				f.set(obj, request.getParameter(f.getName()));
			}
			dao.insert(obj);
			map.put("result", true);
		}catch (Exception e){
			e.printStackTrace();
			map.put("result", false);
		}
		
		return map;
	}
	
	@AjaxAccess
	public Map<String, Object> updateData(HttpServletRequest request, HttpServletResponse response){
		String model = request.getParameter("model");
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Class<?> clazz = Class.forName(model);
			Object obj = clazz.newInstance();
			Field[] fields = clazz.getDeclaredFields();
			Field.setAccessible(fields, true);
			for (Field f : fields){
				f.set(obj, request.getParameter(f.getName()));
			}
			dao.update(obj);
			map.put("result", true);
		}catch (Exception e){
			map.put("result", false);
		}
		
		return map;
	}
	
	@AjaxAccess
	public Map<String, Object> getObject(HttpServletRequest request, HttpServletResponse response){
		String model = request.getParameter("model");
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			Class<?> clazz = Class.forName(model);
			Object obj = dao.query(clazz, null, "where id = \""+id+"\"", null);
			map.put("obj", obj);
		}catch (ClassNotFoundException e){
			map.put("obj", new Object());
		}
		
		return map;
	}
	
}
