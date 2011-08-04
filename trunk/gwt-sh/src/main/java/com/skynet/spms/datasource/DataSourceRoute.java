package com.skynet.spms.datasource;

import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import com.isomorphic.rpc.RPCManager;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.smartgwt.client.docs.DsResponse;



public interface DataSourceRoute {

	DataSourceManager getDataSourceManager(String dsName,Class<? extends BaseEntity> cls);

	DataSourceAction getDataSourceAction(String dsName,Class<?> cls);
	
	DataSourceManager getDataSourceManager(String dsName);

	DataSourceAction getDataSourceAction(String dsName);


	Object doCustomOper(String dsName, String operID, DSRequest req,
			DSResponse resp, RPCManager rpcMang) throws Exception;
	

}