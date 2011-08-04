package com.skynet.spms.client.gui.customerService.salesService.salesInquirySheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class SalesInquiryItemToolStrip extends BaseButtonToolStript {

	private SalesInquiryItemListGrid salesInquiryItemListGrid;
	private SalesInquiryListGrid salesInquiryListGrid;

	public SalesInquiryItemToolStrip(
			final SalesInquiryItemListGrid salesInquiryItemListGrid,
			final SalesInquiryListGrid salesInquiryListGrid) {
		super("customerService.salesService.salesInquirySheet", new String[] {
				"ADD", "DELETE", "MODIFY", "REFRESH" });
		this.setWidth100();
		this.setHeight(40);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.setMargin(5);
		this.salesInquiryItemListGrid = salesInquiryItemListGrid;
		this.salesInquiryListGrid = salesInquiryListGrid;

	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADD".equals(buttonName)) {
			if (ValidateUtil.isRecordSelected(salesInquiryListGrid)) {
				SalesInquiryItemAddWindow addWin = new SalesInquiryItemAddWindow(
						salesInquiryListGrid, salesInquiryItemListGrid);
				ShowWindowTools.showWondow(button.getPageRect(), addWin, 500);
			}
		} else if ("MODIFY".equals(buttonName)) {
			if (ValidateUtil.isRecordSelected(salesInquiryItemListGrid)) {
				SalesInquiryItemModityWindow modifyWin = new SalesInquiryItemModityWindow(
						salesInquiryItemListGrid, salesInquiryListGrid);
				ShowWindowTools
						.showWondow(button.getPageRect(), modifyWin, 500);
			}
		} else if ("REFRESH".equals(buttonName)) {
			if (ValidateUtil.isRecordSelected(salesInquiryListGrid)) {
				String sheetId = salesInquiryListGrid.getSelectedRecord()
						.getAttributeAsString("id");
				Criteria criteria = new Criteria();
				criteria.addCriteria("sheetId", sheetId);
				criteria.addCriteria("op", String.valueOf(Math.random()));
				salesInquiryItemListGrid.filterData(criteria);
			}
		}

	}
}
