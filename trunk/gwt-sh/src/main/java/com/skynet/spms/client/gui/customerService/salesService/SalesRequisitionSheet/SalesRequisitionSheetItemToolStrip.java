package com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class SalesRequisitionSheetItemToolStrip extends BaseButtonToolStript {

	// private ToolStripButton showButton;

	private SalesRequisitionSheetItemListGrid siItemListGrid;

	public SalesRequisitionSheetItemToolStrip(
			SalesRequisitionSheetItemListGrid itemListGrid) {
		super(DSKey.C_SALESQUOTATIONSHEET_ITEM);
		this.siItemListGrid = itemListGrid;
		this.setWidth100();
		this.setHeight(40);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.setMargin(5);
	
		// showButton = new ToolStripButton("备件销售信息");
		// showButton.setAutoFit(true);
		// showButton.setIcon("icons/add.gif");
		// showButton.addClickHandler(new ClickHandler() {
		// public void onClick(ClickEvent event) {
		// showWindow("show");
		// }
		// });

		// this.addButton(showButton);
	}

	protected void showWindow(String windowName) {

	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("SHOW".equals(buttonName)) {
			
		}
	}
}
