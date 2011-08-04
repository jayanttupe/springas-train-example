package com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.biz.ConsignProtocolBusiness;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.model.ConsignProtocolModel;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.view.ConsignProtocolViewWin;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.add.ConsignRenewAddWin;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignRenew.model.ConsignRenewModel;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 寄售协议操作按钮
 * 
 * @author fl
 * 
 */
public class ConsignProtocolToolStrip extends BaseButtonToolStript {
	private static I18n ui = new I18n();
	ConsignProtocolBusiness business = new ConsignProtocolBusiness();
	ConsignProtocolModel model = ConsignProtocolModel.getInstance();
	public ConsignRenewModel modelRenew = ConsignRenewModel.getInstance();

	public ConsignProtocolToolStrip() {
		super(DSKey.S_CONSIGNPROTOCOL);
	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADD".equals(buttonName)) {
			business.addConsignProtocol(button);
		} else if ("MODIFY".equals(buttonName)) {
			ListGridRecord record = model.proMainGrid.getSelectedRecord();
			if (business.canModifiedSheet(model.proMainGrid)) {
				model.protocolId = record.getAttribute("id");
				business.updateConsignProtocol(button);
			}
		} else if ("DELETE".equals(buttonName)) {
			business.deleteSheet(model.proMainGrid);
		} else if ("PUBLISH".equals(buttonName)) {// 发布
			business.publishSheet(model.proMainGrid);
		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布
			business.cancelPublishSheet(model.proMainGrid);
		} else if ("ADDCONSIGNRENEW".equals(buttonName)) {
			ListGridRecord record = model.proMainGrid.getSelectedRecord();
			if (business.canDoNextBusiness(model.proMainGrid)) {
				modelRenew.protocolId = record.getAttribute("id");
				modelRenew.contractNumber = record
						.getAttribute("contractNumber");
				ConsignRenewAddWin win = new ConsignRenewAddWin("", true,
						null, null, "");
				modelRenew.openedWindow = win;
				ShowWindowTools.showWindow(button.getRect(), win, 1);
			}
		} else if ("REFRESH".equals(buttonName)) {
			model.proMainGrid.invalidateCache();
			model.proMainGrid.fetchData();
		} else if ("VIEW".equals(buttonName)) {
			ListGridRecord record = model.proMainGrid.getSelectedRecord();
			if (record == null) {
				SC.say(ui.getM().msgOpSeletedData());
				return;
			}
			ConsignProtocolViewWin win = new ConsignProtocolViewWin("",
					true, button.getPageRect(), null, null);
			model.openedWindow = win;
			ShowWindowTools.showWindow(button.getPageRect(), win, 1);

		}
	}
}
