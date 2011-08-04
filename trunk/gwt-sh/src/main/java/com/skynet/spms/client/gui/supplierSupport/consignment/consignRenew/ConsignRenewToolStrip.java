package com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.OrganizationType;
import com.skynet.spms.client.gui.base.WorkFlowBusinessType;
import com.skynet.spms.client.gui.base.WorkFlowSheetType;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.biz.ConsignRenewBusiness;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.model.ConsignRenewModel;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.update.ConsignRenewUpdateWin;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.view.ConsignRenewViewWin;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 寄售补库操作按钮
 * 
 * @author fl
 * 
 */
public class ConsignRenewToolStrip extends BaseButtonToolStript {
	public ConsignRenewModel model = ConsignRenewModel.getInstance();
	public ConsignRenewBusiness business = new ConsignRenewBusiness();
	private BaseAddressModel addressModel = BaseAddressModel.getInstance();

	public ConsignRenewToolStrip() {
		super(DSKey.S_CONSIGNRENEW);
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("MODIFY".equals(buttonName)) {
			ListGridRecord record = model.consignRenewGrid.getSelectedRecord();
			if (business.canModifiedSheet(model.consignRenewGrid)) {
				model.protocolId = record.getAttribute("template.id");
				model.consignRenewId = record.getAttribute("id");
				addressModel.addr_sheetId = record.getAttribute("id");
				addressModel.enterpriseId = record.getAttribute("supplier.id");
				addressModel.businessType = OrganizationType.SUPPLIER;
				ConsignRenewUpdateWin updateWin = new ConsignRenewUpdateWin(
						"修改申请单", true, button.getPageRect(), null, null);
				model.openedWindow = updateWin;
				ShowWindowTools.showWindow(button.getRect(), updateWin, 1);
			}
		} else if ("DELETE".equals(buttonName)) {//删除template
			ListGridRecord record = model.consignRenewGrid.getSelectedRecord();
			if(record==null){
				record=new ListGridRecord();
			}
			business.deleteSheet(model.consignRenewGrid,"ConsignmentAgreementTemplate",record.getAttribute("template.id"));
		} else if ("PUBLISH".equals(buttonName)) {// 发布
			ListGridRecord record = model.consignRenewGrid.getSelectedRecord();
			if(record==null){
				record=new ListGridRecord();
			}
			business.publishSheet(model.consignRenewGrid,"ConsignmentAgreementTemplate",record.getAttribute("template.id"));
		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布
			ListGridRecord record = model.consignRenewGrid.getSelectedRecord();
			if(record==null){
				record=new ListGridRecord();
			}
			business.cancelPublishSheet(model.consignRenewGrid,"ConsignmentAgreementTemplate",record.getAttribute("template.id"));
		} else if ("COMMITEXAMINE".equals(buttonName)) {
			business.doApproval(model.consignRenewGrid,
					WorkFlowBusinessType.CONSIGNRENEWFORM,
					WorkFlowSheetType.CONSIGNRENEWFORM, "id",
					"requisitionSheetNumber", "totalAmount",
					"m_BussinessPublishStatusEntity.m_PublishStatus", "");
		} else if ("REFRESH".equals(buttonName)) {
			model.consignRenewGrid.invalidateCache();
			model.consignRenewGrid.fetchData();
		} else if ("VIEW".equals(buttonName)) {
			ListGridRecord record = model.consignRenewGrid.getSelectedRecord();
			if (record == null) {
				SC.say("请选择要查看的记录！");
				return;
			}
			ConsignRenewViewWin win = new ConsignRenewViewWin("查看回购申请单", true,
					button.getPageRect(), null, null);
			model.openedWindow = win;
			ShowWindowTools.showWindow(button.getRect(), win, 1);
		}
	}
}
