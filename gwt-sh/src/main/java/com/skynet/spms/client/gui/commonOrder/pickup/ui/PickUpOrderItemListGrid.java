package com.skynet.spms.client.gui.commonOrder.pickup.ui;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.gui.base.i18n.CommonOrderModuleConst;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * 订单明细列表
 * 
 * @author Tony FANG
 * 
 */
public class PickUpOrderItemListGrid extends ListGrid {
	
	final CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);

	public PickUpOrderItemListGrid() {
		this.setAutoFetchData(true);
		this.setWidth100();
		this.setMargin(5);
		this.setShowFilterEditor(true);
		this.setCellHeight(22);
	}

	public void drawGrid() {
		ListGridField field2 = new ListGridField("partNumber", ui.partNumber());
		ListGridField field3 = new ListGridField("quantity", ui.quantity());
		ListGridField field4 = new ListGridField("unitPriceAmount", ui.unitPriceAmount());
		ListGridField field5 = new ListGridField("totalAmount", ui.totalAmount());
		this.setFields(field2, field3, field4, field5);
	}

}
