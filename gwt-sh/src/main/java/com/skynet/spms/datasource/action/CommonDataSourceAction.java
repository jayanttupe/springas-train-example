package com.skynet.spms.datasource.action;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

public interface CommonDataSourceAction<T> {

	void insert(T item);

	T update(Map newValues, String itemID, Class<T> cls);

	void delete(String itemID, Class<BaseEntity> entCls);

	List<T> doQuery(Map values, int startRow, int endRow,
			Class<T> cls);

	List<T> doList(int startRow, int endRow, Class<T> cls);

}