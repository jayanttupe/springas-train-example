package com.skynet.spms.client.gui.customerplatform.repairsheet.update;

import com.skynet.spms.client.gui.customerService.commonui.BaseAddressForm;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerplatform.repairsheet.SheetModelLocator;
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
		
		Tab partRqDataTab = new Tab("部件拆换信息");
		partRqDataTab.setPane(new PartRepairDisassembleDataModifyForm());
		
		BaseAddressModel.getInstance().addr_sheetId=model.repairRequisitionListGrid.getSelectedRecord().getAttribute("id");
		Tab addressTab = new Tab("地址信息");
		addressTab.setPane(new BaseAddressForm(BaseAddressForm.Type.modify));
		
		setTabs(baseTab,partRqDataTab,addressTab);
	}

}
