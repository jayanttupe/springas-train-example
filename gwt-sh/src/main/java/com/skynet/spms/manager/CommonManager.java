package com.skynet.spms.manager;

import java.util.List;
import java.util.Map;

public interface CommonManager<T> {
	
	void deleteEntity(String entityId,Class<T> cls);

	T updateEntity(Map<String,Object> entity, String entityId,Class<T> cls);

	void insertEntity(T entity);
	
	T queryById(String entityId,Class<T> cls);

	List<T> list(int startRow, int endRow, Class<T> cls);
	
	List<T> queryByBean(T query,int startRow, int endRow);
}
