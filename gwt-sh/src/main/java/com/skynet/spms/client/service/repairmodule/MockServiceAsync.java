package com.skynet.spms.client.service.repairmodule;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.vo.repairmodule.MockRecord;

public interface MockServiceAsync {

	void getByContractID(String contractID,String type,AsyncCallback<MockRecord> callback);

}
