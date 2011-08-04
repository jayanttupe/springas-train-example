package com.skynet.spms.client.gui.customerService.exchangeRequisitionSheet.add;

import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class TabSetPanel extends TabSet {

	public TabSetPanel() {
		_init();
	}

	public void _init() {
		Tab baseTab = new Tab("申请单基本信息");
		baseTab.setPane(new SheetForm());
		setTabs(baseTab);
	}

}
