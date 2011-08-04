package com.skynet.spms.datasource.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceManager;
import com.skynet.spms.datasource.DataSourceRoute;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.web.form.CommonViewForm;

@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CommonFormDSAction <T extends CommonViewForm<BaseEntity>> implements CommonDataSourceAction<T> {

	private Logger log=LoggerFactory.getLogger(CommonFormDSAction.class);
	
	@Autowired
	private DataSourceRoute  mangRoute;
	
	private DataSourceManager getDsManager(Class cls){
		
		String dsName=BeanPropUtil.getDsName();
		log.info("ds name:"+dsName);
		
		return mangRoute.getDataSourceManager(dsName,cls);
		
	}
	
	/* (non-Javadoc)
	 * @see com.skynet.spms.datasource.action.CommonDataSource#insert(com.skynet.spms.web.form.CommonViewForm)
	 */
	@Override
	public void insert(T item) {
	
		
		BaseEntity entity = item.readPersisten();

		getDsManager(null).insertEntity(entity);
	}

	private Map<String,Object> generEntityValMap(Map newValues){
		Map<String,Object> valMap=new HashMap<String,Object>();
		for(Object obj:newValues.entrySet()){
			Map.Entry<String,Object> entry=(Map.Entry<String,Object>)obj;
			
			String newKey=StringUtils.substringAfter( entry.getKey(), ".");
			valMap.put(newKey,entry.getValue());
		}
		return valMap;
	}
	/* (non-Javadoc)
	 * @see com.skynet.spms.datasource.action.CommonDataSource#update(java.util.Map, java.lang.String, java.lang.Class)
	 */
	@Override
	public T update(Map newValues, String itemID, Class<T> cls) {

		Map<String,Object> paramMap=generEntityValMap(newValues);
		
		String user=GwtActionHelper.getCurrUser();
		paramMap.put("baseInfo.modifyBy", user);
		paramMap.put("baseInfo.modifyDate", new Date());

		T form = (T)BeanPropUtil.getSafeNullEntity(cls);
		
		ViewFormAnno anno=cls.getAnnotation(ViewFormAnno.class);
		System.out.println("paramMap="+paramMap.size());
		BaseEntity dbEntity = getDsManager(anno.entityCls()).updateEntity(
				paramMap, itemID);
		form.writePersisten(dbEntity);

		return cls.cast(form);
	}

	/* (non-Javadoc)
	 * @see com.skynet.spms.datasource.action.CommonDataSource#delete(java.lang.String, java.lang.Class)
	 */
	@Override
	public void delete(String itemID, Class<BaseEntity> entCls) {
		getDsManager(entCls).deleteEntity(itemID);
	}

	/* (non-Javadoc)
	 * @see com.skynet.spms.datasource.action.CommonDataSource#doQuery(java.util.Map, int, int, java.lang.Class)
	 */
	@Override
	public List<T> doQuery(Map values, int startRow, int endRow,
			Class<T> cls) {

		T query = cls.cast(BeanPropUtil.generEntityByMap(values, cls));
		ViewFormAnno anno=cls.getAnnotation(ViewFormAnno.class);

		List<BaseEntity> userList = (List<BaseEntity>) getDsManager(anno.entityCls())
				.queryByBean(query.readPersisten(),startRow,
						endRow);

		List<T> entityList = new ArrayList<T>();
		for (BaseEntity user : userList) {
			T entity = cls.cast(BeanPropUtil.getSafeNullEntity(cls));
			entity.writePersisten(user);
			entityList.add(entity);
		}
		return entityList;
	}

	/* (non-Javadoc)
	 * @see com.skynet.spms.datasource.action.CommonDataSource#doList(int, int, java.lang.Class)
	 */
	@Override
	public List doList(int startRow, int endRow, Class<T> cls) {

		ViewFormAnno anno=cls.getAnnotation(ViewFormAnno.class);

		List<BaseEntity> userList = (List<BaseEntity>) getDsManager(anno.entityCls())
				.list( startRow,endRow);

		List<CommonViewForm> entityList = new ArrayList<CommonViewForm>();
		for (BaseEntity user : userList) {
			CommonViewForm entity = (CommonViewForm) BeanPropUtil.getSafeNullEntity(cls);
			entity.writePersisten(user);
			entityList.add(entity);
		}
		return entityList;
	}
	
	
	
}
