package com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.OrganizationType;
import com.skynet.spms.client.gui.base.WorkFlowBusinessType;
import com.skynet.spms.client.gui.base.WorkFlowSheetType;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.business.BuyBackPactBusiness;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.model.BuybackPactModelLocator;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.view.BuyBackPactViewWin;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 回购合同操作按钮
 * 
 * @author fl
 * 
 */
public class BuyBackPactToolStrip extends BaseButtonToolStript {

	private BuybackPactModelLocator model = BuybackPactModelLocator
			.getInstance();

	private BaseListGrid listGrid;

	private BuyBackPactBusiness pactBus = new BuyBackPactBusiness();

	private BaseAddressModel addressModel = BaseAddressModel.getInstance();

	public BuyBackPactToolStrip(final BuyBackPactGrid listgrid) {
		super(DSKey.C_BUYBACKPACT);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.listGrid = listgrid;
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		ListGridRecord record = listGrid.getSelectedRecord();
		if ("MODIFY".equals(buttonName)) {// 修改
			if (pactBus.canModifiedSheet(listGrid)) {
				model.contractID = record.getAttribute("id");
				model.requisitionSheetNumber = record.getAttribute("sheet.id");
				addressModel.addr_sheetId = record.getAttribute("id");
				addressModel.enterpriseId = record
						.getAttribute("customerIdentificationCode.id");
				addressModel.businessType = OrganizationType.CUSTOMER;
				pactBus.updateBuyBackPact(button);
			}
		} else if ("DELETE".equals(buttonName)) {// 删除
			if(record==null){
				record= new ListGridRecord();
			}
			pactBus.deleteSheet(model.pactGrid,"BuybackRequisitionSheet",record.getAttribute("sheet.id"));
		} else if ("PUBLISH".equals(buttonName)) {// 发布
			if(record==null){
				record= new ListGridRecord();
			}
			pactBus.publishSheet(model.pactGrid,"BuybackRequisitionSheet",record.getAttribute("sheet.id"));
		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布
			if(record==null){
				record= new ListGridRecord();
			}
			pactBus.cancelPublishSheet(model.pactGrid,"BuybackRequisitionSheet",record.getAttribute("sheet.id"));
		} else if ("COMMITEXAMINE".equals(buttonName)) {
			pactBus.doApproval(listGrid, WorkFlowBusinessType.BUYBACKCONTRACT,
					WorkFlowSheetType.BUYBACKCONTRACT, "id", "contractNumber",
					"extendedValueTotalAmount",
					"m_BussinessPublishStatusEntity.m_PublishStatus", "");
		} else if ("REFRESH".equals(buttonName)) {
			pactBus.refreshSheet(listGrid);
		} else if ("VIEW".equals(buttonName)) {
			if (record != null) {
				model.contractID = record.getAttribute("id");
				model.requisitionSheetNumber = record.getAttribute("sheet.id");
				addressModel.addr_sheetId = record.getAttribute("id");
				addressModel.enterpriseId = record
						.getAttribute("customerIdentificationCode.id");
				addressModel.businessType = OrganizationType.CUSTOMER;
				BuyBackPactViewWin win = new BuyBackPactViewWin("查看回购合同", true,
						button.getPageRect(), null, null);
				model.openedWindow = win;
				ShowWindowTools.showWindow(button.getPageRect(), win, 1);
			} else {
				SC.say("请选择要查看的记录！");
			}
		}
	}
}
