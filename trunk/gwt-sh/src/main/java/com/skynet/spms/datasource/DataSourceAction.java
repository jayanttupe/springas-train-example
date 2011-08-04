package com.skynet.spms.datasource;

import java.util.List;
import java.util.Map;

public interface  DataSourceAction<T> extends DataSourceBind {
		
	void insert(T item);

	T update(Map<String,Object> newValues, String itemID);

	void delete(String itemID);

	List<T> doQuery(Map<String,Object> values, int startRow, int endRow);

	List<T> getList(int startRow, int endRow);

}
