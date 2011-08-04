package com.skynet.spms.client.service.contractManagement.provision;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.skynet.spms.client.vo.contractManagement.Provision;

@RemoteServiceRelativePath("contractProvisionAction.form")
public interface ContractProvisionService extends RemoteService {
	
	void addProvision(Provision p);
	
	void deleteProvision(String id, String fileName);

	void updateProvision(Provision p);

}
