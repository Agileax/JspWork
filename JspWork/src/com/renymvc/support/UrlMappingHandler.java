package com.renymvc.support;

import java.util.HashMap;
import java.util.Map;

public enum UrlMappingHandler {
	
	INSTANCE;
	
	private Map<String, Object> urlMapping;
	
	{
		urlMapping = new HashMap<String, Object>();
	}
	
	public void add(String uri, Object action){
		urlMapping.put(uri, action);
	}

	public Object getAction(String uri){
		if (urlMapping != null && urlMapping.containsKey(uri)){
			return urlMapping.get(uri);
		}
		return null;
	}
	
	@Override
	public String toString() {
		return urlMapping.toString();
	}
	
}
