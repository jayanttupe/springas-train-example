package com.skynet.spms.client.gui.customerplatform.repairsheet.add;

import com.skynet.spms.client.gui.customerService.commonui.BaseAddressForm;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class TabSetPanel extends TabSet {

	public TabSetPanel() {
		_init();
	}

	public void _init() {
		Tab baseTab = new Tab("申请单基本信息");
		baseTab.setPane(new SheetForm());
		
		Tab partRqDataTab = new Tab("部件拆换信息");
		partRqDataTab.setPane(new PartRepairDisassembleDataAddForm());
		
		Tab addressTab = new Tab("地址信息");
		addressTab.setPane(new BaseAddressForm(BaseAddressForm.Type.modify));
		
		setTabs(baseTab,partRqDataTab,addressTab);
	}

}
