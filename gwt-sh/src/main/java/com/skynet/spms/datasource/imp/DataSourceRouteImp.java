package com.skynet.spms.datasource.imp;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import com.isomorphic.rpc.RPCManager;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.datasource.DataSourceBind;
import com.skynet.spms.datasource.DataSourceManager;
import com.skynet.spms.datasource.DataSourceRoute;
import com.skynet.spms.datasource.action.CommonDataSourceAction;
import com.skynet.spms.datasource.action.CommonEntityDSAction;
import com.skynet.spms.datasource.action.CommonFormDSAction;
import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.manager.CommDsManager;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.web.form.CommonViewForm;


@Component
public class DataSourceRouteImp implements DataSourceRoute {

	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private  CommDsManager<?> commMang;
	
	@Autowired
	private CommonFormDSAction<?> commDataSource;
	
	@Autowired
	private CommonEntityDSAction<?> commEntityDataSource;

	private Map<String, String> dsMangMap;
	
	private Map<String,String> dsActionMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.skynet.spms.datasource.imp.DataSourceRoute#getDataSourceManager(java
	 * .lang.String)
	 */
	@Override
	public DataSourceManager getDataSourceManager(String dsName,Class<? extends BaseEntity> cls) {
		if (dsMangMap.containsKey(dsName)) {
			String mangName = dsMangMap.get(dsName);
			return applicationContext
					.getBean(mangName, DataSourceManager.class);
		}else{
			return new CommMangFacade(commMang,cls);
		}
	}	
	

	@PostConstruct
	public void init() throws Exception {
		
		dsMangMap=Collections.unmodifiableMap(getBeanMap(DataSourceManager.class));
		
		dsActionMap=Collections.unmodifiableMap(getBeanMap(DataSourceAction.class));

	}

	private Map<String, String> getBeanMap(Class<? extends DataSourceBind> cls) {
		String[] nameArray=applicationContext.getBeanNamesForType(cls);
		Map<String,String> dsMap=new HashMap<String,String>();
		
		for (String name:nameArray) {
			DataSourceBind bean=applicationContext.getBean(name, cls);
			if(bean.getBindDsName()!=null){
				for(String dsName:bean.getBindDsName()){
					dsMap.put(dsName,name);
				}
			}
		}
		return dsMap;
	}

	@Override
	public DataSourceAction getDataSourceAction(String dsName,Class<?> cls) {
		if (dsActionMap.containsKey(dsName)) {
			String mangName = dsActionMap.get(dsName);
			return applicationContext
					.getBean(mangName, DataSourceAction.class);
		}else{
			if(CommonViewForm.class.isAssignableFrom(cls)){
				return new CommDsFacade(commDataSource,cls.asSubclass(CommonViewForm.class));
			}else if(BaseEntity.class.isAssignableFrom(cls)){
				return new CommDsFacade(commEntityDataSource,cls.asSubclass(BaseEntity.class));
			}else{
				throw new IllegalArgumentException("invalid entity cls:"+cls);
			}
		}
	}	


	private static class CommMangFacade implements DataSourceManager{

		private  CommDsManager mang;
		
		private Class<? extends BaseEntity> cls;
		
		public CommMangFacade(CommDsManager mang,Class<? extends BaseEntity> cls){
			this.mang=mang;
			this.cls=cls;
		}
		
		@Override
		public List queryByBean(BaseEntity query, int startRow, int endRow) {
			
			return mang.queryByBean(query, startRow, endRow);
		}

		@Override
		public void deleteEntity(String itemID) {
			mang.deleteEntity(itemID, cls);
		}


		@Override
		public void insertEntity(BaseEntity info) {
			mang.insertEntity(info);
		}

		@Override
		public List list(int startRow, int endRow) {
			return mang.list(startRow, endRow, cls);
		}

		@Override
		public String[] getBindDsName() {
			return null;
		}

		@Override
		public BaseEntity updateEntity(Map valMap, String itemID) {
			return mang.updateEntity(valMap, itemID,cls);
		}
		
	}

	

	private static class CommDsFacade implements DataSourceAction{
		private final CommonDataSourceAction commDs;
		private final Class<?> cls;
		private final Class<BaseEntity> entCls;
		
		public CommDsFacade(CommonDataSourceAction commDs,Class<?> cls){
			this.commDs=commDs;
			this.cls=cls;
			if(cls.isAnnotationPresent(ViewFormAnno.class)){
				this.entCls=(Class<BaseEntity>) cls.getAnnotation(ViewFormAnno.class).entityCls();
			}else{
				this.entCls=(Class<BaseEntity>) cls;
			}
		}

		@Override
		public void insert(Object item) {
			commDs.insert(cls.cast(item));
		}

		@Override
		public Object update(Map newValues, String itemID) {
			
			return commDs.update(newValues, itemID, cls);
		}

		@Override
		public void delete(String itemID) {
			
			commDs.delete(itemID, entCls);			
		}

		@Override
		public List doQuery(Map values, int startRow, int endRow) {
			return commDs.doQuery(values, startRow, endRow, cls);
		}

		@Override
		public List getList(int startRow, int endRow) {
			return commDs.doList(startRow,endRow,cls);
		}

		@Override
		public String[] getBindDsName() {
			return null;
		}
		
	}



	@Override
	public DataSourceManager getDataSourceManager(String dsName) {
		String mangName = dsMangMap.get(dsName);
		return applicationContext
				.getBean(mangName, DataSourceManager.class);
	}


	@Override
	public DataSourceAction getDataSourceAction(String dsName) {
		String mangName = dsActionMap.get(dsName);
		return applicationContext
				.getBean(mangName, DataSourceAction.class);		
	}

	@Override	
	public Object doCustomOper(String dsName,String operID,DSRequest req,DSResponse resp,RPCManager rpcMang) throws Exception{
		
		String pkName=req.getDataSource().getPrimaryKey();
		
		String itemID = (String) req.getFieldValue(pkName);
		
		Map valMap=req.getValues();
		
		String mangName = dsActionMap.get(dsName);
		DataSourceAction action= applicationContext
				.getBean(mangName, DataSourceAction.class);		
		Method seleMethod=null;
		for(Method method:action.getClass().getDeclaredMethods()){
			if(method.getName().equals(operID)){
				seleMethod=method;
				break;
			}
		}
		
		Class[] clsArray=seleMethod.getParameterTypes();
		
		Object[] objArray=new Object[clsArray.length];
		boolean isFirst=true;
		for(int idx=0;idx<clsArray.length;idx++){
			Class cls=clsArray[idx];
			if(Map.class.isAssignableFrom(cls)){
				objArray[idx]=valMap;
			}else if(DSRequest.class.isAssignableFrom(cls)){
				objArray[idx]=req;
			}else if(DSResponse.class.isAssignableFrom(cls)){
				objArray[idx]=resp;
			}else if(RPCManager.class.isAssignableFrom(cls)){
				objArray[idx]=rpcMang;
			}else if(String.class.isAssignableFrom(cls)){
				objArray[idx]=itemID;
			}else if(int.class.isAssignableFrom(cls)){
				if(isFirst){
					int start=(int) req.getStartRow();
					objArray[idx]=start;
					isFirst=false;
				}else{
					int end=(int)req.getEndRow();
					objArray[idx]=end;					
				}
			}
		}
		
		return seleMethod.invoke(action, objArray);		
		
	}

}
