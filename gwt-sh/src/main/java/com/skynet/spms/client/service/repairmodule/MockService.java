package com.skynet.spms.client.service.repairmodule;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.skynet.spms.client.vo.repairmodule.MockRecord;

@RemoteServiceRelativePath("mockServiceAction.form")
public interface MockService extends RemoteService{

	MockRecord getByContractID(String contractID,String type);
}
