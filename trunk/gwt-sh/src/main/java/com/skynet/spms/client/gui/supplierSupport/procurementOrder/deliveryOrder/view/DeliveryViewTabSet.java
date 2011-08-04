package com.skynet.spms.client.gui.supplierSupport.procurementOrder.deliveryOrder.view;

import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class DeliveryViewTabSet extends TabSet {
	
	public DeliveryViewTabSet() {
		super();
		_init();
		// TODO Auto-generated constructor stub
	}

	public void _init() {

		Tab baseTab = new Tab("指令基本信息");
		baseTab.setPane(new DeliveryBaseViewForm());

		Tab addressTab = new Tab("收发地址");
		addressTab.setPane(new DeliveryAddressViewForm());

		
		setTabs(baseTab, addressTab );

	}
}
