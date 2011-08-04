package com.skynet.spms.client.gui.supplierSupport.procurementOrder.deliveryOrder.modity;

import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class DeliveryModityTabSet extends TabSet {
	
	public DeliveryModityTabSet() {
		super();
		_init();
		// TODO Auto-generated constructor stub
	}

	public void _init() {

		Tab baseTab = new Tab("指令基本信息");
		baseTab.setPane(new DeliveryBaseModityForm());

		Tab addressTab = new Tab("收发地址");
		addressTab.setPane(new DeliveryAddressModityForm());

		
		setTabs(baseTab, addressTab );

	}
}
