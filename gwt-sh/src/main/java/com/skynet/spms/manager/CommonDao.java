package com.skynet.spms.manager;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

public interface CommonDao{

	<T extends BaseEntity>  void deleteEntity(String entityId,Class<T> cls);

	<T extends BaseEntity> T updateEntity(Map<String,Object> entity, String entityId,Class<T> cls);

	<T extends BaseEntity> void insertEntity(T entity);
	
	<T extends BaseEntity> T queryById(String entityId,Class<T> cls);

	<T extends BaseEntity>  List<T> list(int startRow, int endRow, Class<T> cls);
	 
	<T extends BaseEntity> List<T> queryByBean(T query,
			int startRow, int endRow);

	Session getSession();

	Session getClearSession();
	
	FullTextSession getFullTextSession();
	
}
