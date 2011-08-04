package com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.view.SalesRequisitionTrackView;
import com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.business.SalesRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerplatform.common.ModuleKey;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.add.SalesRequisitionAddWindow;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.model.SalesRequisitionModel;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.modify.SalesRequisitionModifyWindow;
import com.skynet.spms.client.gui.customerplatform.salesRequisitionSheet.view.SalesRequisitionViewWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
public class SalesRequisitionTrackToolStrip extends BaseButtonToolStript {

	private SalesRequisitionModel model = SalesRequisitionModel.getInstance();

	public SalesRequisitionTrackToolStrip() {
		super(ModuleKey.C_ORDERTRACK);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		SalesRequisitionSheetBusiness bus = new SalesRequisitionSheetBusiness();
		ListGridRecord selectLgr=model.trackListGrid.getSelectedRecord();
		String quotationId="";
		if(selectLgr!=null){
			quotationId=selectLgr.getAttribute("quotationId");
		}
		
		 if ("VIEW".equals(buttonName)) {
			// 先给主键赋值
			model.primaryKey = model.trackListGrid.getSelectedRecord().getAttribute(
					"id");
			SalesRequisitionTrackView win = new SalesRequisitionTrackView("",
					true,null, null, "");
			ShowWindowTools.showWindow(button.getPageRect(), win, 200);
		} else if("REFRESH".equals(buttonName)){
			bus.refreshSheet(model.trackListGrid);
		}

	}

}
