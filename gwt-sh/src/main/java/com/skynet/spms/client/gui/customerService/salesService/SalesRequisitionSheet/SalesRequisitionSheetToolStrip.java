package com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.OrganizationType;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.business.SalesRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.model.RequisitionModelLocator;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.business.SaleContractBusiness;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.model.SaleContractModelLocator;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.skynet.spms.client.gui.customerService.salesService.SalesRequisitionSheet.view.SalesRequisitionViewWindow;

public class SalesRequisitionSheetToolStrip extends BaseButtonToolStript {

	private static I18n ui = new I18n();
	
	SalesRequisitionSheetBusiness bus = new SalesRequisitionSheetBusiness();

	private RequisitionModelLocator model = RequisitionModelLocator
			.getInstance();

	private SaleContractModelLocator modelLocator = SaleContractModelLocator
			.getInstance();

	private SaleContractBusiness saleBus = new SaleContractBusiness();

	private BaseAddressModel addressModel = BaseAddressModel.getInstance();
	

	public SalesRequisitionSheetToolStrip() {
		super(DSKey.C_SALESREQUISITIONSHEET);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
	}
	
	

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {

		if ("ADDCONTRACT".equals(buttonName)) {
			// 新建合同
			ListGridRecord selectedRecord = model.listGrid.getSelectedRecord();
			if (saleBus.canDoNextBusiness(model.listGrid)) {
				String isTemptlate = selectedRecord.getAttribute("isTemptlate");
				if (isTemptlate.equals("true")) {
					//合同已经存在
					SC.say(ui.getM().mod_salesRequisitionSheet_contractExists());
					return;
				}
				modelLocator.selectedRecord = selectedRecord;
				addressModel.enterpriseId = selectedRecord
						.getAttribute("m_CustomerIdentificationCode.id");
				addressModel.businessType = OrganizationType.CUSTOMER;
				saleBus.addSaleContractPact(button);
			}

		}else if("VIEW".equals(buttonName)){
			//先给主键赋值
			model.primaryKey = model.listGrid.getSelectedRecord().getAttribute(
					"id");
			SalesRequisitionViewWindow win = new SalesRequisitionViewWindow("",
					true,null, null, "");
			ShowWindowTools.showWindow(button.getPageRect(), win, 200);
		}else if("REFRESH".equals(buttonName)){
			bus.refreshSheet(model.listGrid);
		}
	}

}
