package com.skynet.spms.client.gui.customerplatform.exchangeRequisitionSheet.modify;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.repairService.sheet.SheetModelLocator;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class TabSetPanel extends TabSet {
	private I18n ui = new I18n();
	
	private SheetModelLocator model = SheetModelLocator.getInstance();

	public TabSetPanel() {
		_init();
	}

	public void _init() {
		Tab baseTab = new Tab(ui.getM().mod_exchangeRequisitionSheet_tab_title());
		baseTab.setPane(new SheetModifyForm());

		setTabs(baseTab);
	}

}
