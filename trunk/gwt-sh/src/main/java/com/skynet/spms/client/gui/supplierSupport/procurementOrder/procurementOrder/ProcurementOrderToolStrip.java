package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.business.BuyBackPactBusiness;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.model.BuybackPactModelLocator;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.business.BuyBackSheetBusiness;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackSheet.model.SheetModelLocator;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.doProcurementOrder.view.ProcurementOrderViewWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.business.ProcurementContractBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.add.ProcurementInquirySheetAddWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.model.ProcurementOrderModelLocator;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.modity.ProcurementOrderModifyWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementPlan.business.ProcurementPlanBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.add.TransferOrderAddWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 主订单操作按钮
 * 
 * @author Tony FANG
 * 
 */
public class ProcurementOrderToolStrip extends BaseButtonToolStript {
	private static I18n ui = new I18n();
	private SheetModelLocator model = SheetModelLocator.getInstance();
	private ProcurementPlanBusiness bussiness = new ProcurementPlanBusiness();
	private BuybackPactModelLocator modelLocator = BuybackPactModelLocator
			.getInstance();

	private BuyBackPactBusiness pactBusiness = new BuyBackPactBusiness();

	ProcurementContractBusiness procurementContractBusiness = new ProcurementContractBusiness();

	private ProcurementOrderListGrid list;

	public ProcurementOrderToolStrip(ProcurementOrderListGrid list) {
		super(DSKey.S_PROCUREMENTORDER);
		this.list = list;
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		// MODIFY,DELETE,PUBLISH,CANCELPUBLISH,MESSAGE,LOG,PRINT,EXPORT,SENIORSEARCH
		if ("MODIFY".equals(buttonName)) {// 修改
			if (ValidateUtil.isRecordSelected(list)) {
				ProcurementOrderModifyWindow updateWin = new ProcurementOrderModifyWindow(
						ui.getB().modifyFormTitle(
								ui.getM().mod_procurementOrder_name()), true,
						null, null, "");
				ShowWindowTools
						.showWindow(button.getPageRect(), updateWin, 200);
			}
		} else if ("DELETE".equals(buttonName)) {// 删除
			bussiness.deleteSheet(list, "ProcurementPlan", list
					.getSelectedRecord().getAttribute("procurementPlanId"));
		} else if ("PUBLISH".equals(buttonName)) {// 发布
			bussiness.publishSheet(list, "ProcurementPlan", list
					.getSelectedRecord().getAttribute("procurementPlanId"));
		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布
			bussiness.cancelPublishSheet(list, "ProcurementPlan", list
					.getSelectedRecord().getAttribute("procurementPlanId"));
		} else if ("ADDCONTRACT".equals(buttonName)) {// 新建合同
			ListGridRecord record = model.backSheetGrid.getSelectedRecord();
			if (record == null) {
				//请选择要建立合同的申请单！
				SC.say(ui.getM().msgOpSelectSheetToContant());
				return;
			}
			String isTemptlate = record.getAttribute("isTemptlate");
			if (isTemptlate.equals("true")) {
				//合同已经存在
				SC.say(ui.getM().msgContantExist());
				return;
			}
			modelLocator.requisitionSheetNumber = record.getAttribute("id");
			pactBusiness.addBuyBackPact(button);
		} else if (("ADDINQUIRY").equals(buttonName)) {
			ListGridRecord record = list.getSelectedRecord();
			if (record == null) {
				//请选择一条您要操作的数据
				SC.say(ui.getM().msgOpSeletedData());
				return;
			}
			ProcurementInquirySheetAddWindow win = new ProcurementInquirySheetAddWindow(
					"", true, null, null, null);
			win.show();
		} else if (("ADDTRANSFER").equals(buttonName)) {
			ListGridRecord record = list.getSelectedRecord();
			if (record == null) {
				//请先选择申请单
				SC.say(ui.getM().msgOpSeletedData());
				return;
			}

			TransferOrderAddWindow win = new TransferOrderAddWindow("",
					true, null, null, null);
			win.show();
		} else if (buttonName.equals("VIEW")) {
			ProcurementOrderViewWindow view = new ProcurementOrderViewWindow(
					"", true, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), view, 0);
		} else if (buttonName.equals("REFRESH")) {
			bussiness.refreshSheet(list);
		}
	}
}
