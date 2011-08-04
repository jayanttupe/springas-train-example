package com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.business.SalesQuotationBusiness;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.model.MainModelLocator;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.modity.SalesQuotationModityWindow;
import com.skynet.spms.client.gui.customerplatform.salesQuotationSheet.view.SalesQuotationViewWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class SalesQuotationToolStrip extends BaseButtonToolStript {

	private SalesQuotationBusiness bus;

	private SalesQuotationListGrid listGrid;

	public SalesQuotationToolStrip(SalesQuotationListGrid listGrid) {
		super(DSKey.C_SALESQUOTATIONSHEET);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.listGrid = listGrid;
		bus = new SalesQuotationBusiness();

	}

	protected void showWindow(String windowName) {

	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("MODIFY".equals(buttonName)) {
			if (bus.canModifiedSheet(listGrid)) {
				SalesQuotationModityWindow win = new SalesQuotationModityWindow(
						"", true, button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), win, 200);
			}
		} else if ("PUBLISH".equals(buttonName)) {
			bus.publishSheet(listGrid,"SalesInquirySheet",listGrid.getSelectedRecord().getAttribute("salesInquirySheet.id"));
		} else if ("CANCELPUBLISH".equals(buttonName)) {
			bus.cancelPublishSheet(listGrid,"SalesInquirySheet",listGrid.getSelectedRecord().getAttribute("salesInquirySheet.id"));
		}
		else if ("DELETE".equals(buttonName)) {
			bus.deleteSheet(listGrid,"SalesInquirySheet",listGrid.getSelectedRecord().getAttribute("salesInquirySheet.id"));
		} else if ("VIEW".equals(buttonName)) {
			MainModelLocator model = MainModelLocator.getInstance();
			model.primaryKey = model.listGrid.getSelectedRecord().getAttribute(
					"id");
			SalesQuotationViewWindow win = new SalesQuotationViewWindow(
					"报价单详细信息", true, null, null, null);
			ShowWindowTools.showWindow(button.getPageRect(), win, 200);
		}else if("REFRESH".equals(buttonName)){
			bus.refreshSheet(listGrid);
		}
	}
}
