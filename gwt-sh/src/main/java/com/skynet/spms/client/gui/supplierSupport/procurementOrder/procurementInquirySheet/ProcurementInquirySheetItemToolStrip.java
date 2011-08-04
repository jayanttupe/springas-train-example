package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.add.ProcurementInquirySheetAddWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.modity.ProcurementInquirySheetModityWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 订单明细操作按钮
 * @author Tony FANG
 *
 */
public class ProcurementInquirySheetItemToolStrip extends BaseButtonToolStript {


	private ProcurementInquirySheetItemListGrid itemListGrid;

	public ProcurementInquirySheetItemToolStrip(
			ProcurementInquirySheetItemListGrid itemListGrid) {
		this.itemListGrid = itemListGrid;
		this.setWidth100();
		this.setHeight(40);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.setMargin(5);


	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADD".equals(buttonName)) {
			ProcurementInquirySheetAddWindow ppo = new ProcurementInquirySheetAddWindow(
					"", true, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), ppo, 200);
		} else if ("MODIFY".equals(buttonName)) {
			ProcurementInquirySheetModityWindow ppo = new ProcurementInquirySheetModityWindow(
					"", true, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), ppo, 200);
		} else if ("DELETE".equals(buttonName)) {

		} else {

		}		
	}
}
