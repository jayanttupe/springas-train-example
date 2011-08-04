package com.skynet.spms.client.gui.customerService.salesService.salesContract;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.OrganizationType;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.WorkFlowBusinessType;
import com.skynet.spms.client.gui.base.WorkFlowSheetType;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.business.SaleContractBusiness;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.model.SaleContractModelLocator;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.view.SalesContractViewWin;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.partPlanNeedOrder.add.PartPlanNeedOrderAddWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementOrder.aog.AOGProcurementOrderAddWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 销售合同操作按钮
 * 
 * @author fl
 * 
 */
public class SalesContractToolStrip extends BaseButtonToolStript {

	// private I18n ui = new I18n();

	private SaleContractModelLocator model = SaleContractModelLocator
			.getInstance();
	private BaseAddressModel addressModel = BaseAddressModel.getInstance();
	private BaseListGrid listgrid;

	private SaleContractBusiness saleBus = new SaleContractBusiness();

	public SalesContractToolStrip(final BaseListGrid listgrid) {
		super(DSKey.C_SALESCONTRACT);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.listgrid = listgrid;
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		/** 查看 **/
		ListGridRecord record = listgrid.getSelectedRecord();
		if ("VIEW".equals(buttonName)) {
			if (record == null) {
				SC.say("请选择要查看的合同！");
				return;
			}
			model.contractID = record.getAttribute("id");
			addressModel.addr_sheetId = record.getAttribute("id");
			addressModel.enterpriseId = record
					.getAttribute("customerIdenty.id");
			addressModel.businessType = OrganizationType.CUSTOMER;
			SalesContractViewWin contractViewWin = new SalesContractViewWin(
					"查看", true, button.getPageRect(), null, "");
			model.openedWindow = contractViewWin;
			ShowWindowTools
					.showWindow(button.getPageRect(), contractViewWin, 1);
		} else if ("MODIFY".equals(buttonName)) {
			
			if (saleBus.canModifiedSheet(listgrid)) {
				model.contractID = record.getAttribute("id");
				addressModel.addr_sheetId = record.getAttribute("id");
				addressModel.enterpriseId = record
						.getAttribute("customerIdenty.id");
				addressModel.businessType = OrganizationType.CUSTOMER;
				// 关联客户订单明细 saleSheet.id --在销售合同明细实体中
				model.sheetID = record.getAttribute("saleSheet.id");
				saleBus.updateSaleContract(button);
			}
		} else if ("DELETE".equals(buttonName)) {
			
			// 删除
			if(record==null){
				record= new ListGridRecord();
			}
			saleBus.deleteSheet(listgrid,"SalesRequisitionSheet",record.getAttribute("saleSheet.id"));
		} else if ("PUBLISH".equals(buttonName)) {
			
			/** 发布 **/
			if(record==null){
				record= new ListGridRecord();
			}
			saleBus.publishSheet(model.saleGrid,"SalesRequisitionSheet",record.getAttribute("saleSheet.id"));
		} else if ("CANCELPUBLISH".equals(buttonName)) {
			/** 取消发布 **/
			if(record==null){
				record= new ListGridRecord();
			}
			saleBus.cancelPublishSheet(model.saleGrid,"SalesRequisitionSheet",record.getAttribute("saleSheet.id"));
		} else if ("COMMITEXAMINE".equals(buttonName)) {
			saleBus.doApproval(listgrid, WorkFlowBusinessType.SALESCONTRACT,
					WorkFlowSheetType.SALESCONTRACT, "id", "contractNumber",
					"extendedValueTotalAmount",
					"m_BussinessPublishStatusEntity.m_PublishStatus",
					"m_Priority");
		} else if ("AOGPROCUREMENT".equals(buttonName)) {
			if (ValidateUtil.isRecordSelected(listgrid)) {
				if (listgrid.getSelectedRecord().getAttribute("m_Priority")
						.equals("AOG")) {
					AOGProcurementOrderAddWindow aog = new AOGProcurementOrderAddWindow(
							"新建AOG采购", true, button.getPageRect(), null, "");
					ShowWindowTools.showWindow(button.getPageRect(), aog, 500);
				} else {
					SC.say("请选择优先级为AOG的...");
				}
			}
		} else if ("STOCKREGISTER".equals(buttonName)) {
			if (ValidateUtil.isRecordSelected(listgrid)) {
				PartPlanNeedOrderAddWindow addWindow = new PartPlanNeedOrderAddWindow(
						"缺货登记", true, button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), addWindow, 1);
			}
		}else if("REFRESH".equals(buttonName)){
			saleBus.refreshSheet(model.saleGrid);
		}
	}
}
