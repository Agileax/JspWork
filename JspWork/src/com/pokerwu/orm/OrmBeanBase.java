package com.pokerwu.orm;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.pokerwu.orm.annotation.Table;

/**
 * @author pokerWu
 *
 * @email pokerwu1994@gmail.com
 */
public class OrmBeanBase implements DBOperate {
	private static OrmBeanBase orm;
	private String table;
	private Map<String,Object> params = null;
	
	private Connection conn = null;
	private PreparedStatement pre = null;
	private ResultSet result = null;
	
	private OrmBeanBase(){}
	public static OrmBeanBase getInstance(){
		if(orm == null)
			orm = new OrmBeanBase();
		return orm;
	}
	private void initParams(Object obj) {
		getTable(obj);
		params = new HashMap<String,Object>();
		Method[] methods = obj.getClass().getDeclaredMethods();
		String gs = "get(\\w+)";  
        Pattern getM = Pattern.compile(gs);
        for(Method mh:methods){
        	String name = mh.getName();
        	if(Pattern.matches(gs, name)){
	        	String fild = getM.matcher(name).replaceAll("$1").toLowerCase();
	        	try {
					Object value = mh.invoke(obj);
					params.put(fild, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        }
	}
	private void getTable(Object obj){
		Class<?> clazz = obj.getClass();
		getTable(clazz);
	}
	private void getTable(Class<?> clazz){
		if(clazz.isAnnotationPresent(Table.class)){
			table = clazz.getAnnotation(Table.class).value();
			if (table=="" || table.isEmpty())
				table = clazz.getSimpleName().toLowerCase();
			return;
		}
		throw new RuntimeException("there is not annotation,check your bean");
	}
	@Override
	public void insert(Object obj) {
		initParams(obj);
		
		int len = params.keySet().size();
		Object[] values = new Object[len];
		StringBuilder builder = new StringBuilder("insert into ");
		builder.append(table)
				.append(" (");
		int i = 0;
		for(String fild:params.keySet()){
			builder.append(fild)
					.append(",");
			values[i] = params.get(fild);
			i++;
		}
		builder.deleteCharAt(builder.length()-1)
				.append(") values(");
		for(int j=0;j<len;j++){
			builder.append("?,");
		}
		builder.deleteCharAt(builder.length()-1)
				.append(")");
		
		try {
			conn = DBhelper.getConnection();
			pre = conn.prepareStatement(builder.toString());
			for(int j=0; j<values.length;j++)
				pre.setObject(j+1, values[j]);
			System.out.println(builder.toString());
			pre.execute();
			
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Class<?> clazz ,Object id) {
		getTable(clazz);
		String sql = "delete from "+table+" where id = ?";
		try {
			conn = DBhelper.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setObject(1, id);
			pre.execute();
			
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(Object obj) {
		initParams(obj);
		
		StringBuilder builder = new StringBuilder("update ").append(table).append(" set ");
		int len = params.keySet().size();
		Object[] values = new Object[len];
		
		int i=0;
		for(String fild:params.keySet()){
			if(!"id".equals(fild)){
				builder.append(fild).append("=?,");
				values[i] = params.get(fild);
				i++;
			}
		}
		values[len-1] = params.get("id");
		if (values[len-1] == null)
			throw new RuntimeException("id must valid");
		builder.deleteCharAt(builder.length() - 1).append(" where id = ?");
		try {
			conn = DBhelper.getConnection();
			pre = conn.prepareStatement(builder.toString());
			for (int j=0;j<len;j++)
				pre.setObject(j+1, values[j]);
			System.out.println(builder.toString());
			pre.execute();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public <T> T query(Class<T> clazz,String[] filds,String condition,Object[] param) {
	
		return queryForList(clazz,filds,condition,param).get(0);
	}
	@Override
	public <T> List<T> queryForList(Class<T> clazz,String[] filds,String condition,Object[] params){
		List<T> list = new ArrayList<T>();
		try {
			getTable(clazz.newInstance());
			
			StringBuilder sql = new StringBuilder("select ");
			if (filds == null || filds.length<1)
				sql.append("* ");
			else{
				for(String fild : filds ){
					sql.append(fild).append(",");
				}
				sql.deleteCharAt(sql.length() - 1);
			}
			sql.append(" from ")
				.append(table)
				.append(" ");
			if(condition !=null)
				sql.append(condition);
			
			conn = DBhelper.getConnection();
			pre = conn.prepareStatement(sql.toString());
			if(params!=null)
				for (int j=0;j<params.length;j++)
					pre.setObject(j+1, params[j]);
			
			result = pre.executeQuery();
			ResultSetMetaData meta = result.getMetaData();
			while(result.next()){
				Map<String,Object> map = new HashMap<String, Object>();
				int column = meta.getColumnCount();
				for(int i=1;i<= column;i++){
					String name = meta.getColumnName(i);
					Object obj = result.getObject(i);
					map.put(name, obj);
				}
				list.add(wrapObj(map,clazz));
			}
			close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @param map
	 * @return
	 */
	private <T> T wrapObj(Map<String, Object> map,Class<T> clazz) {
		T obj = null;
		try {
			obj = clazz.newInstance();
			Method [] methods = clazz.getDeclaredMethods();
			for(String fild:map.keySet()){
				System.out.println(fild+"==="+ map.get(fild));
				for(Method mh: methods){
					String name = "set"+fild.substring(0,1).toUpperCase() + fild.substring(1);
					if (name.equals(mh.getName())) {
						mh.invoke(obj, map.get(fild));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	private void close() {
		if(result != null){
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			result = null;
		}
		if(pre != null){
			try {
				pre.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pre = null;
		}
		if(conn !=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
	
}
