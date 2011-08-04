package com.skynet.spms.manager;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

public interface CommDsManager<T extends BaseEntity> {

	List<T> queryByBean(T query, int startRow, int endRow);

	void deleteEntity(String itemID, Class<T> cls);

	T updateEntity(Map<String,Object> entity, String itemID,Class<T> cls);

	void insertEntity(T info);

	List<T> list(int startRow, int endRow, Class<T> cls);

}