package com.skynet.spms.client.gui.customerService.repairService.sheet.view;

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
public class RepairSheetViewWin extends Window {
	

	public RepairSheetViewWin() {
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
		setTitle(new I18n().getRepairModule().view_repair_sheet_title());
		VLayout container = new VLayout();
		container.setWidth100();
		container.setHeight100();
		container.setAlign(Alignment.CENTER);
		TabSetPanelView tabSet=new TabSetPanelView();
		container.addMember(tabSet);
		addItem(container);
	}
	
	

}
