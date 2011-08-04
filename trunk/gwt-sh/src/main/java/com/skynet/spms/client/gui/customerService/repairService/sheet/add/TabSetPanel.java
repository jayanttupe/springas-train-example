package com.skynet.spms.client.gui.customerService.repairService.sheet.add;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressForm;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class TabSetPanel extends TabSet {
	
	private I18n ui=new I18n();

	public TabSetPanel() {
		_init();
	}

	public void _init() {
		Tab baseTab = new Tab(ui.getRepairModule().sheet_base_info());
		baseTab.setPane(new SheetForm());
		
		Tab partRqDataTab = new Tab(ui.getRepairModule().part_ass_data_info());
		partRqDataTab.setPane(new PartRepairDisassembleDataAddForm());
		
		Tab addressTab = new Tab(ui.getRepairModule().address_info_title());
		addressTab.setPane(new BaseAddressForm(BaseAddressForm.Type.modify));
		
		setTabs(baseTab,partRqDataTab,addressTab);
	}

}
