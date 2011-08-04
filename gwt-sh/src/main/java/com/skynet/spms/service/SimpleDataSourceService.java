package com.skynet.spms.service;

import java.util.Map;

import com.isomorphic.datasource.DSRequest;
import com.isomorphic.datasource.DSResponse;
import com.isomorphic.rpc.RPCManager;

public interface SimpleDataSourceService {

	Object remove(DSRequest dsRequest,Map item) throws Exception;

	void update(DSRequest dsRequest, Map newValues,RPCManager manger) throws Exception;

	DSResponse add(DSRequest dsRequest, Map item) throws Exception;

	DSResponse fetch(DSRequest dsRequest) throws Exception;

}
