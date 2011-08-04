package com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.tabs;

import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class SuppliersParityTabSet extends TabSet {
	
	public SuppliersParityTabSet() {
		super();
		_init();
		// TODO Auto-generated constructor stub
	}

	public void _init() {

		Tab baseTab = new Tab("供应商报价");
		baseTab.setPane(new SuppliersParityBaseForm());

		//Tab addressTab = new Tab("附件");
		//addressTab.setPane(new SuppliersParityAttachmentForm());

		
		setTabs(baseTab );

	}
}
