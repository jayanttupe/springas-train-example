package com.skynet.spms.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.vo.contractManagement.Contract;

public interface ContractInfoServiceAsync {

	void getContract(String id, String key, AsyncCallback<Contract> callback);

	void getContracts(String userName, String key,
			AsyncCallback<List<Contract>> callback);
}
