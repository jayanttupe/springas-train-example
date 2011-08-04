package com.skynet.spms.datasource;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import com.isomorphic.rpc.RPCManager;
import com.isomorphic.util.DataTools;
import com.isomorphic.util.ErrorMessage;
import com.isomorphic.util.ErrorReport;
//import com.skynet.spms.datasource.EntityInfoService;
//import com.skynet.spms.datasource.GetEntityCls;
import com.skynet.spms.web.control.SimpleDataSourceAdapte;

@Component("simpleDataSourceService")
public class SimpleDataSourceServiceImpl {
	
	private static final int PAGE_SIZE = 79;

	private Logger log = LoggerFactory.getLogger(SimpleDataSourceAdapte.class);

	@Autowired
	private DataSourceRoute actionRoute;
		
	
	public DSResponse fetch(DSRequest dsRequest,RPCManager manager) throws Exception {
		
		DSResponse dsResponse = new DSResponse();
		// DataSource protocol: get filter criteria


		String dsName=dsRequest.getDataSourceName();
		BeanPropUtil.setDsName(dsName);
		
		DataSourceAction service=actionRoute.getDataSourceAction(dsName);
		
		log.debug("val:" + dsRequest.getValues());

		// DataSource protocol: get requested row range
		long startRow = dsRequest.getStartRow();
		long endRow = dsRequest.getEndRow();
		log.info("start:" + startRow + " end:" + endRow);
		List matchingItems = null;
		

		try{
			String operID=dsRequest.getOperationId();
			if(operID.equals(dsName+"_fetch")){
			
				if (dsRequest.getValues().isEmpty()) {
					matchingItems = service.getList((int) startRow, (int) endRow);
				} else {
					matchingItems = service.doQuery(dsRequest.getValues(),
							(int) startRow, (int) endRow);
				}
			}else{
				matchingItems=(List) actionRoute.doCustomOper(dsName, operID, dsRequest, dsResponse, manager); 
			}
			log.info("get record list:" + matchingItems.size());
			List<String> fieldList=dsRequest.getDataSource().getFieldNames();

			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			for(Object obj:matchingItems){
				list.add(BeanPropUtil.getDataMap(fieldList, obj));
			}
			dsResponse.setData(list);
		}catch(DataSourceException e){
			log.error("fail",e);
			e.fillErrorRecords(dsResponse);
		}catch(Exception e){
			log.error("fail",e);
			dsResponse.setStatus(-100);
			dsResponse.setProperty("error", e.getMessage());
		}

		// tell client what rows are being returned, and what's available
		dsResponse.setStartRow(startRow);
		dsResponse.setEndRow(matchingItems.size()+startRow);
		long totalNow=startRow+matchingItems.size();
		if(totalNow==endRow){
			totalNow+=PAGE_SIZE;
		}
		dsResponse.setTotalRows(totalNow);
		return dsResponse;
	}

	
	public DSResponse add(DSRequest dsRequest, Map item,RPCManager manger)
			throws Exception {
		log.info("procesing DMI add operation");

		DSResponse dsResponse = new DSResponse();
		String dsName=dsRequest.getDataSourceName();
		BeanPropUtil.setDsName(dsName);
		
		List<String> fieldList=dsRequest.getDataSource().getFieldNames();
		
		DataSourceAction service=actionRoute.getDataSourceAction(dsName);

		
		// DataSource protocol: get new values to be saved
		if (item.isEmpty()) {
			dsResponse.setData(dsRequest.getCriteria());
			return dsResponse;
		}		
		Method method=service.getClass().getMethod("update", new Class[]{Map.class,String.class});				
		Class cls=method.getReturnType();
				
		Object newItem = BeanPropUtil.getEntityByMap(fieldList, item, cls);
		
		String operID=dsRequest.getOperationId();
		
		try{
			if(operID.equals(dsName+"_add")){
				service.insert(newItem);
			}else{
				actionRoute.doCustomOper(dsName, operID, dsRequest, dsResponse, manger); 
			}
		}catch(DataSourceException e){
			log.error("fail",e);
			if(e.getCause() instanceof DataSourceException){
				DataSourceException ex=(DataSourceException)e.getCause();
				ex.fillErrorRecords(dsResponse);
//				dsResponse.setProperty("id", );
			}else{
				e.fillErrorRecords(dsResponse);
			}
		}

		// DataSource protocol: return the committed item bean to the client for
		// cache update
		dsResponse.setData(item);
		return dsResponse;
	}
	
//	private Object[] getMethodParams(Method method){
//		
//		
//		return objArray;
//		
//	}

	
	public DSResponse update(DSRequest dsRequest, Map newValues,RPCManager manger)
			throws Exception {
		log.info("procesing DMI update operation");
		
		DSResponse dsResponse = new DSResponse();
		String dsName=dsRequest.getDataSourceName();
		BeanPropUtil.setDsName(dsName);
				
		
//	    ErrorReport errorReport = dsRequest.getDataSource().validate(newValues, false);
//        if (errorReport != null) {
//           dsResponse.setStatus(DSResponse.STATUS_VALIDATION_ERROR);
//           dsResponse.setErrorReport(errorReport);
//           log.error("Errors: " + DataTools.prettyPrint(errorReport));
////           manger.send(dsRequest, dsResponse);
//           return dsResponse;
//        }
		
		List<String> fieldList=dsRequest.getDataSource().getFieldNames();

		String pkName=dsRequest.getDataSource().getPrimaryKey();
		
		String itemID = (String) dsRequest.getFieldValue(pkName);
		// DataSource protocol: get new values to be saved
		if (newValues.isEmpty()) {
			dsResponse.setData(dsRequest.getCriteria());
			return dsResponse;
		}
		

		String operID=dsRequest.getOperationId();
		try {
			Object item = null;
			if (operID.equals(dsName + "_update")) {
				DataSourceAction service = actionRoute
						.getDataSourceAction(dsName);

				item = service.update(newValues, itemID);
			} else {
				item = actionRoute.doCustomOper(dsName, operID, dsRequest,
						dsResponse, manger);
			}

			Map<String, Object> newItem = BeanPropUtil.getDataMap(fieldList,
					item);
			dsResponse.setData(newItem);

		} catch (DataSourceException e) {
			log.error("fail",e);
			e.fillErrorRecords(dsResponse);
			dsResponse.setData(newValues);
		} catch (Exception e) {
			log.error("fail",e);
			if(e.getCause() instanceof DataSourceException){
				DataSourceException ex=(DataSourceException)e.getCause();
				ex.fillErrorRecords(dsResponse);
				dsResponse.setProperty("id", itemID);
			}else{
				dsResponse.setStatus(-100);
				dsResponse.setProperty("error", e.getMessage());
			}
		}

//		 manger.send(dsRequest, dsResponse);
         return dsResponse;
	}
	
	

	
	public DSResponse remove(DSRequest dsRequest,Map item,RPCManager manger) throws Exception {
		log.info("procesing DMI remove operation");
		String dsName=dsRequest.getDataSourceName();
		BeanPropUtil.setDsName(dsName);
		
		DSResponse dsResponse = new DSResponse();
		

		String pk=dsRequest.getDataSource().getPrimaryKey();
		if (item.isEmpty()) {
			return dsResponse;
		}
		
		String itemID = (String)item.get(pk);		
		String operID=dsRequest.getOperationId();
		
		try {
			if (operID.equals(dsName + "_remove")) {
				DataSourceAction service = actionRoute
						.getDataSourceAction(dsName);
				service.delete(itemID);
			} else {
				
				actionRoute.doCustomOper(dsName, operID, dsRequest, dsResponse,
						manger);
			}
			dsResponse.setData(item);
		} catch (DataSourceException e) {
			log.error("fail",e);
			e.fillErrorRecords(dsResponse);
		} catch (Exception e) {
			log.error("fail",e);
			dsResponse.setStatus(-100);
			dsResponse.setProperty("error", e.getMessage());
		}
		
		return dsResponse;
	}
	
	public void custom(DSRequest dsRequest,RPCManager rpcManager)throws Exception{
		 DSResponse dsResponse = new DSResponse();
		 
		 String dsName=dsRequest.getDataSourceName();
		 BeanPropUtil.setDsName(dsName);
			
		 String operID=dsRequest.getOperationId();
		 
		 actionRoute.doCustomOper(dsName, operID, dsRequest, dsResponse, rpcManager);
		
		 rpcManager.send(dsRequest, dsResponse);
         return;
	}
	
	
	
}
