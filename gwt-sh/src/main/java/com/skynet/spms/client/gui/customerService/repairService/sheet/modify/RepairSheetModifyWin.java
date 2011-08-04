package com.skynet.spms.client.gui.customerService.repairService.sheet.modify;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 送修申请单添加窗体
 * 
 * @author tqc
 * 
 */
public class RepairSheetModifyWin extends Window {
	
	
	private I18n ui=new I18n();

	public RepairSheetModifyWin() {
		_init();
	}

	private void _init() {
		setWidth100();
		setHeight100();
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();
		setAlign(Alignment.CENTER);
		setTitle(ui.getRepairModule().modify_repair_sheet_title());
		VLayout container = new VLayout();
		container.setWidth100();
		container.setHeight100();
		container.setAlign(Alignment.CENTER);
		TabSetPanel tabSet=new TabSetPanel();
		container.addMember(tabSet);
		addItem(container);
	}
	
	

}
