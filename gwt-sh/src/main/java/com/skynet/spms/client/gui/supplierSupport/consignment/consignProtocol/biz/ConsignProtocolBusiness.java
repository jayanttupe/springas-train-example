package com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.biz;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.add.ConsignProtocolAddWin;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.model.ConsignProtocolModel;
import com.skynet.spms.client.gui.supplierSupport.consignment.consignProtocol.update.ConsignProtocolUpdateWin;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class ConsignProtocolBusiness extends BaseBusiness{
	ConsignProtocolModel model = ConsignProtocolModel.getInstance();

	/**
	 * 创建协议
	 */
	public void addConsignProtocol(ToolStripButton button) {
		ConsignProtocolAddWin addWin = new ConsignProtocolAddWin("寄售协议", true,
				button.getPageRect(), null, "");
		model.openedWindow = addWin;
		ShowWindowTools.showWindow(button.getPageRect(), addWin, 1);
	}

	public void updateConsignProtocol(ToolStripButton button) {
		ConsignProtocolUpdateWin updateWin = new ConsignProtocolUpdateWin("修改",
				true, button.getPageRect(), null, "");
		model.openedWindow = updateWin;
		ShowWindowTools.showWindow(button.getPageRect(), updateWin, 1);
	}
}
