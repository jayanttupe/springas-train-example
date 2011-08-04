package com.skynet.spms.datasource;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;


public interface DataSourceManager<T extends BaseEntity> extends DataSourceBind {
	
	List<T> queryByBean(T query, int startRow, int endRow);

	void deleteEntity(String itemID);
 
	void insertEntity(T info);

	List<T> list(int startRow, int endRow);

	
	T updateEntity(Map<String,Object> valueMap,String itemID);
	
}
