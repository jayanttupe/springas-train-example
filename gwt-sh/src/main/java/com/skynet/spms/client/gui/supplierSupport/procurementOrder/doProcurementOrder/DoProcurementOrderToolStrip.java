package com.skynet.spms.client.gui.supplierSupport.procurementOrder.doProcurementOrder;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.DataSource;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.doProcurementOrder.view.ProcurementOrderViewWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.business.ProcurementContractBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.model.ProcurementContractModelLocator;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementInquirySheet.add.ProcurementInquirySheetAddWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.model.ProcurementOrderModelLocator;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.add.TransferOrderAddWindow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.skynet.spms.client.tools.ShowWindowTools;

public class DoProcurementOrderToolStrip extends BaseButtonToolStript {
	private static I18n ui = new I18n();
	private ProcurementOrderModelLocator model = ProcurementOrderModelLocator
			.getInstance();

	private ProcurementContractModelLocator modelLocator = ProcurementContractModelLocator
			.getInstance();

	private ProcurementContractBusiness biz = new ProcurementContractBusiness();

	public DoProcurementOrderToolStrip() {
		super(DSKey.S_DOPROCUREMENTORDER);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
	}

	@Override
	protected void showWindow(String buttonName, final ToolStripButton button) {
		if ("VIEW".equals(buttonName)) {// 查看
			if (ValidateUtil.isRecordSelected(model.doProcurementOrderListGrid)) {
				ProcurementOrderViewWindow view = new ProcurementOrderViewWindow(
						ui.getB().viewFormTitle(
								ui.getM().mod_procurementOrder_name()), true,
						button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), view, 500);
			}
		} else if ("ADDCONTRACT".equals(buttonName)) {// 新建采购合同
			ListGridRecord selectedRecord = model.doProcurementOrderListGrid
					.getSelectedRecord();
			if (biz.canDoNextBusiness(model.procurementOrderListGrid)) {
				String isTemptlate = selectedRecord.getAttribute("isTemptlate");
				if (isTemptlate.equals("true")) {
					SC.say("合同已经存在!");
					return;
				}
				modelLocator.contractID = null;
				modelLocator.orderNumber = selectedRecord
						.getAttribute("orderNumber");
				modelLocator.procurementPlanUUid = selectedRecord
						.getAttribute("id");
				modelLocator.selectedRecord = selectedRecord;
				biz.addProcurementContract(button);
			}
		} else if (("ADDINQUIRY").equals(buttonName)) {
			ListGridRecord record = model.doProcurementOrderListGrid
					.getSelectedRecord();
			if (record == null) {
				SC.say("请先选择申请单！");
				return;
			}
			final ProcurementOrderModelLocator modelLocator = ProcurementOrderModelLocator
					.getInstance();
			if (modelLocator.procurementInquiryDS == null
					|| modelLocator.procurementInquiryItemDS == null) {
				final DataSourceTool dataSourceTool = new DataSourceTool();

				// 初始化订单数据源
				dataSourceTool.onCreateDataSource(
						DSKey.S_PROCUREMENTINQUIRYSHEET,
						DSKey.S_PROCUREMENTINQUIRYSHEET_DS,
						new PostDataSourceInit() {
							public void doPostOper(DataSource dataSource,
									DataInfo dataInfo) {
								modelLocator.procurementInquiryDS = dataSource;
								dataSourceTool
										.onCreateDataSource(
												DSKey.S_PROCUREMENTINQUIRYSHEET_ITEM,
												DSKey.S_PROCUREMENTINQUIRYSHEET_ITEM_DS,
												new PostDataSourceInit() {
													public void doPostOper(
															DataSource dataSource,
															DataInfo dataInfo) {
														modelLocator.procurementInquiryItemDS = dataSource;

														ProcurementInquirySheetAddWindow ppa = new ProcurementInquirySheetAddWindow(

														"新建采购询价单", true, button
																.getPageRect(),
																null, "");
														ShowWindowTools
																.showWindow(
																		button
																				.getPageRect(),
																		ppa,
																		200);
													}
												});
							}
						});
			} else {
				ProcurementInquirySheetAddWindow ppa = new ProcurementInquirySheetAddWindow(

				"新建采购询价单", true, button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), ppa, 200);
			}
		} else if (("ADDTRANSFER").equals(buttonName)) {
			ListGridRecord record = model.doProcurementOrderListGrid
					.getSelectedRecord();
			if (record == null) {
				SC.say("请先选择申请单！");
				return;
			}
			TransferOrderAddWindow win = new TransferOrderAddWindow("新建AOG调拨单",
					true, null, null, null);
			win.show();
		} else if (buttonName.equals("REFRESH")) {
			biz.refreshSheet(model.procurementOrderListGrid);
		}
	}
}
