package com.skynet.spms.client.gui.customerService.repairService.sheet.view;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressForm;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerService.repairService.sheet.SheetModelLocator;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class TabSetPanelView extends TabSet {
	
	private SheetModelLocator model = SheetModelLocator.getInstance();
	
	private I18n ui=new I18n();

	public TabSetPanelView() {
		_init();
	}

	public void _init() {
		Tab baseTab = new Tab(ui.getRepairModule().sheet_base_info());
		baseTab.setPane(new SheetViewForm());
		
		Tab partRqDataTab = new Tab(ui.getRepairModule().part_ass_data_info());
		partRqDataTab.setPane(new PartRepairDisassembleDataViewForm());
		
		BaseAddressModel.getInstance().addr_sheetId=model.repairRequisitionListGrid.getSelectedRecord().getAttribute("id");
		Tab addressTab = new Tab(ui.getRepairModule().address_info_title());
		addressTab.setPane(new BaseAddressForm(BaseAddressForm.Type.view));
		
		setTabs(baseTab,partRqDataTab,addressTab);
	}

}
