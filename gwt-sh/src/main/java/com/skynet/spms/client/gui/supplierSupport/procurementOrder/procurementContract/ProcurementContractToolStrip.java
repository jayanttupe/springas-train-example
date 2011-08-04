package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.OrganizationType;
import com.skynet.spms.client.gui.base.WorkFlowBusinessType;
import com.skynet.spms.client.gui.base.WorkFlowSheetType;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.business.ProcurementContractBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.model.ProcurementContractModelLocator;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.view.ProcurementContractViewWin;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 采购合同操作按钮
 * 
 * @author fl
 * 
 */
public class ProcurementContractToolStrip extends BaseButtonToolStript {
	private ProcurementContractModelLocator model = ProcurementContractModelLocator
			.getInstance();

	private BaseListGrid listGird;

	private ProcurementContractBusiness procurementBusiness = new ProcurementContractBusiness();

	private BaseAddressModel addressModel = BaseAddressModel.getInstance();

	public ProcurementContractToolStrip(final BaseListGrid listgrid) {
		super(DSKey.S_PROCUREMENTCONTRACT);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.listGird = listgrid;
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		ListGridRecord record = listGird.getSelectedRecord();
		if(record==null){
			record= new ListGridRecord();
		}
		if ("VIEW".equals(buttonName)) {
		
			if (record == null) {
				SC.say("请选择要查看的合同！");
				return;
			}
			model.contractID = record.getAttribute("id");
			model.procurementPlanUUid = record
					.getAttribute("procurementPlanUUid");
			addressModel.addr_sheetId = record.getAttribute("id");
			addressModel.enterpriseId = record.getAttribute("seller.id");
			addressModel.businessType = OrganizationType.SUPPLIER;
			ProcurementContractViewWin contractViewWin = new ProcurementContractViewWin(
					"查看采购合同", true, button.getPageRect(), null, "");
			model.openedWindow = contractViewWin;
			ShowWindowTools
					.showWindow(button.getPageRect(), contractViewWin, 1);
		} else if ("MODIFY".equals(buttonName)) {// 修改
			if (procurementBusiness.canModifiedSheet(listGird)) {
				model.contractID = record.getAttribute("id");
				model.procurementPlanUUid = record
						.getAttribute("procurementPlanUUid");
				addressModel.addr_sheetId = record.getAttribute("id");
				addressModel.enterpriseId = record.getAttribute("seller.id");
				addressModel.businessType = OrganizationType.SUPPLIER;
				procurementBusiness.updateProcurementContract(button);
			}
		} else if ("DELETE".equals(buttonName)) {// 删除
			procurementBusiness.deleteSheet(model.pactGrid,"ProcurementOrder",record.getAttribute("procurementPlanUUid"));
		} else if ("PUBLISH".equals(buttonName)) {// 发布
			procurementBusiness.publishSheet(model.pactGrid,"ProcurementOrder",record.getAttribute("procurementPlanUUid"));
		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布
			procurementBusiness.cancelPublishSheet(model.pactGrid,"ProcurementOrder",record.getAttribute("procurementPlanUUid"));
		} else if ("COMMITEXAMINE".equals(buttonName)) {// 提交审批
			procurementBusiness.doApproval(listGird,
					WorkFlowBusinessType.PROCUREMENTCONTRACT,
					WorkFlowSheetType.PROCUREMENTCONTRACT, "id",
					"contractNumber", "extendedValueTotalAmount",
					"m_BussinessPublishStatusEntity.m_PublishStatus",
					"m_Priority");
		} else if ("REFRESH".equals(buttonName)) {
			listGird.invalidateCache();
			listGird.fetchData();
		}else if("PRINT".equals(buttonName)){
			if(listGird.getSelection().length == 1){
				PrintWindow win = new PrintWindow("打印航材采购单", false, button.getPageRect(), "");
				ShowWindowTools.showWindow(button.getPageRect(), win, 1);
			}else{
				SC.say("请选择要查看的合同！");
				return;
			}
		}else if("REFRESH".equals(buttonName)){
			procurementBusiness.refreshSheet(model.pactGrid);
		}
	}
}
