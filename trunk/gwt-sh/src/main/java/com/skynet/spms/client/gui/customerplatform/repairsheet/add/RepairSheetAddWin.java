package com.skynet.spms.client.gui.customerplatform.repairsheet.add;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 送修申请单添加窗体
 * 
 * @author tqc
 * 
 */
public class RepairSheetAddWin extends Window {
	

	public RepairSheetAddWin() {
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
		setTitle("添加送修申请单");
		VLayout container = new VLayout();
		container.setWidth100();
		container.setHeight100();
		container.setAlign(Alignment.CENTER);
		TabSetPanel tabSet=new TabSetPanel();
		container.addMember(tabSet);
		addItem(container);
	}
	
	

}
