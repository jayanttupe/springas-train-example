package com.skynet.spms.datasource.action;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceManager;
import com.skynet.spms.datasource.DataSourceRoute;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CommonEntityDSAction <T extends BaseEntity> implements CommonDataSourceAction<T>{

	private Logger log=LoggerFactory.getLogger(CommonFormDSAction.class);
	
	@Autowired
	private DataSourceRoute  mangRoute;
	
	private DataSourceManager getDsManager(Class cls){
		
		String dsName=BeanPropUtil.getDsName();
		log.info("ds name:"+dsName);
		
		return mangRoute.getDataSourceManager(dsName,cls);
		
	}
	
	public void insert(T item) {

		getDsManager(null).insertEntity(item);
	}

	public T update(Map newValues, String itemID, Class<T> cls) {

		
		log.info("paramMap="+newValues.size());
		BaseEntity dbEntity = getDsManager(cls).updateEntity(
				newValues, itemID);

		return cls.cast(dbEntity);
	}

	public void delete(String itemID, Class<BaseEntity> entCls) {
		getDsManager(entCls).deleteEntity(itemID);
	}

	public List<T> doQuery(Map values, int startRow, int endRow,
			Class<T> cls) {

		T query = cls.cast(BeanPropUtil.generEntityByMap(values, cls));

		List<T> userList = (List<T>) getDsManager(cls)
				.queryByBean(query,startRow,
						endRow);
		
		return userList;
	}

	public List<T> doList(int startRow, int endRow, Class<T> cls) {

		List<T> userList = (List<T>) getDsManager(cls).list( startRow,endRow);
		
		return userList;
	}
	
	
	
}
