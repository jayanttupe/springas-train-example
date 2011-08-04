package com.skynet.spms.client.gui.customerService.salesService.doQuotation;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class DoQuotationItemToolStrip extends BaseButtonToolStript {

	private ToolStripButton addButton;

	private DoQuotationItemListGrid siItemListGrid;

	public DoQuotationItemToolStrip(DoQuotationItemListGrid itemListGrid) {
		super(DSKey.C_DOQUOTATION);
		this.siItemListGrid = itemListGrid;
		this.setWidth100();
		this.setHeight(40);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.setMargin(5);
//		addButton = new ToolStripButton("添加");
//		addButton.setAutoFit(true);
//		addButton.setIcon("icons/add.gif");
//		addButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				
//			}
//		});
//		this.addButton(addButton);
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {

	}
}
