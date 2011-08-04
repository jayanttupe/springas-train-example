package com.skynet.spms.client.gui.customerService.exchangeRequisitionSheet.modify;

import com.skynet.spms.client.gui.customerService.repairService.sheet.SheetModelLocator;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class TabSetPanel extends TabSet {

	private SheetModelLocator model = SheetModelLocator.getInstance();

	public TabSetPanel() {
		_init();
	}

	public void _init() {
		Tab baseTab = new Tab("申请单基本信息");
		baseTab.setPane(new SheetModifyForm());

		setTabs(baseTab);
	}

}
