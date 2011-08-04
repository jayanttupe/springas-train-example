package com.skynet.spms.client.service.contractManagement.provision;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.vo.contractManagement.Provision;

public interface ContractProvisionServiceAsync {

	void addProvision(Provision p, AsyncCallback<Void> callback);

	void deleteProvision(String id, String fileName, AsyncCallback<Void> callback);

	void updateProvision(Provision p, AsyncCallback<Void> callback);

}
