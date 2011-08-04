package com.skynet.spms.client.gui.customerplatform.salesQuotationSheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.SalesQuotationListGrid;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.business.SalesQuotationBusiness;
import com.skynet.spms.client.gui.customerplatform.common.ModuleKey;
import com.skynet.spms.client.gui.customerplatform.salesQuotationSheet.view.SalesQuotationViewWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.skynet.spms.client.gui.customerService.salesService.salesQuotationSheet.model.MainModelLocator;
import com.skynet.spms.client.gui.customerplatform.salesQuotationSheet.add.C_SalesRequisitionSheetAddWindow;
public class SalesQuotationToolStrip extends BaseButtonToolStript {

	private SalesQuotationBusiness bus;

	private SalesQuotationListGrid listGrid;

	public SalesQuotationToolStrip(SalesQuotationListGrid listGrid) {
		super(ModuleKey.C_CONTACTDETAIL);
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
		bus= new SalesQuotationBusiness();
		if("VIEW".equals(buttonName)){
			MainModelLocator model=MainModelLocator.getInstance();
			model.primaryKey=model.listGrid.getSelectedRecord().getAttribute("id");
			SalesQuotationViewWindow win =new SalesQuotationViewWindow("",true,null,null,null);
			ShowWindowTools.showWindow(button.getPageRect(), win, 200);
		} else if ("ADDSALESREQUISITION".equals(buttonName)) {
			if(bus.canDoNextBusiness(listGrid)){
				C_SalesRequisitionSheetAddWindow win = new C_SalesRequisitionSheetAddWindow(
						"", true, null, null, null);
				ShowWindowTools.showWindow(button.getPageRect(), win, 200);
			}
			
		} else if("REFRESH".equals(buttonName)){
			bus.refreshSheet(listGrid);
		}
	}
}
