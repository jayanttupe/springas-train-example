package com.skynet.spms.client.gui.customerService.repairService.repairContract.plugin;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.service.repairmodule.MockService;
import com.skynet.spms.client.service.repairmodule.MockServiceAsync;

public abstract class MockDataOper {

	protected MockView view;

	protected MockServiceAsync service = GWT.create(MockService.class);

	protected abstract void drawView(String contractID);

}
