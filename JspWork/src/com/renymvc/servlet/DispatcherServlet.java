package com.renymvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.renymvc.annotation.AjaxAccess;
import com.renymvc.support.UrlMappingHandler;

public class DispatcherServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UrlMappingHandler urlMappingHandler;
	private String viewPrefix;
	private String viewSuffix;
	
	private static final Logger log = Logger.getGlobal();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		urlMappingHandler = UrlMappingHandler.INSTANCE;
		viewPrefix = config.getInitParameter("viewprefix");
		viewSuffix = config.getInitParameter("viewsuffix");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doDisPatcher(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doDisPatcher(req, resp);
	}
	
	public void doDisPatcher(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		String controllerName = getControllerName(req);
		String methodName = getMethodName(req);
		Object action = null;
		if (controllerName == null 
				|| (action = urlMappingHandler.getAction(controllerName)) == null
				|| methodName == null) {
			pageError(req, resp);
			return;
		}
		try {
			applyControllerHandle(action, methodName, req, resp);
		} catch (Exception e) {
			log.warning(e.getMessage());
			pageError(req, resp);
		}
		
	}
	
	private String getControllerName(HttpServletRequest req){
		String context = getServletContext().getContextPath();
		String name = req.getRequestURI().substring(context.length());
		if ("".equals(name)){
			name = "/";
		}
		return name;
	}
	
	private String getMethodName(HttpServletRequest req){
		
		String name = req.getParameter("method");
		if (name == null || "".equals(name)){
			name = "index";
		}
		return name;
	}
	
	private void pageError(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.sendError(404, req.getRequestURL().toString());
	}
	
	@SuppressWarnings("unchecked")
	private void applyControllerHandle(Object action, String methodName, 
			HttpServletRequest req, HttpServletResponse resp) 
			throws IllegalAccessException,  ParseException{
		Method method = null;
		try {
			method = action.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			IllegalAccessException exception = new IllegalAccessException();
			exception.initCause(e.getCause());
			throw exception;
		}
		try {
			if (checkAjaxAccess(method)){
				Object value = method.invoke(action, req, resp);
				if (!(value instanceof Map)){
					throw new IllegalArgumentException("The return value of method which is for ajax must be Map<String, Object>");
				}
				processHandleResult((Map<String, Object>)value, resp); 
			} else {
				Object value = method.invoke(action, req, resp);
				if (!(value instanceof String)){
					throw new IllegalArgumentException("The return value of method which is not for ajax must be String");
				}
				processHandleResult((String) value, req, resp);
			}
		} catch (InvocationTargetException e) {
			IllegalAccessException exception = new IllegalAccessException();
			exception.initCause(e.getCause());
			throw exception;
		}
		
		
	}
	private boolean checkAjaxAccess(Method method){
		AjaxAccess ajax = method.getAnnotation(AjaxAccess.class);
		
		return ajax != null;
	}
	
	private void processHandleResult(Map<String, Object> map, HttpServletResponse resp) throws ParseException{
		resp.setContentType("text/json");
		resp.setCharacterEncoding("UTF-8");
		String json = JSONObject.fromObject(map).toString();
		PrintWriter out = null;
		try {
			try {
				out = resp.getWriter();
				out.write(json);
			} finally {
				out.close();
			}
		} catch (Exception e) {
			ParseException exception = new ParseException(e.getMessage(), 0);
			exception.initCause(e.getCause());
			throw exception;
		}
		
	}
	private void processHandleResult(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ParseException {
		
		String fullViewName = getViewPath(viewName);
		try {
			req.getRequestDispatcher(fullViewName).forward(req, resp);
		} catch (Exception e) {
			ParseException exception = new ParseException(e.getMessage(), 0);
			exception.initCause(e.getCause());
			throw exception;
		}
	}
	private String getViewPath(String viewName){
		
		return (viewPrefix == null ? "" : viewPrefix) 
					+ viewName 
					+ (viewSuffix == null ? "" : viewSuffix);
	}
}
