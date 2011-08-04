package com.skynet.spms.client.gui.supplierSupport.procurementOrder.pickUpOrder.tabs;

import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class PickUpTabSet extends TabSet {
	
	public PickUpTabSet() {
		super();
		_init();
		// TODO Auto-generated constructor stub
	}

	public void _init() {

		Tab baseTab = new Tab("指令基本信息");
		baseTab.setPane(new PickUpBaseForm());

		Tab addressTab = new Tab("收发地址");
		addressTab.setPane(new PickUpAddressForm());

		
		setTabs(baseTab, addressTab );

	}
}
