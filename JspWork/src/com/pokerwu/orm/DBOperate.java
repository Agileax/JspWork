package com.pokerwu.orm;

import java.util.List;

/**
 * @author pokerWu
 *
 * @email pokerwu1994@gmail.com
 */
public interface DBOperate {
	void insert(Object obj);
	void delete(Object obj);
	void update(Object obj);
	<T> T query(Class<T> clazz,String[] filds,String condition,Object[] param);
	<T> List<T> queryForList(Class<T> clazz,String[] filds,String condition,Object[] param);

}
