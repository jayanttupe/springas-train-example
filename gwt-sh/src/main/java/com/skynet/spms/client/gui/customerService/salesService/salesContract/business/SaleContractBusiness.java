package com.skynet.spms.client.gui.customerService.salesService.salesContract.business;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.add.SalesContractAddWindow;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.model.SaleContractModelLocator;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.update.SalesContractUpdateWin;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class SaleContractBusiness  extends BaseBusiness{

	public SaleContractModelLocator model = SaleContractModelLocator
			.getInstance();

	public SaleContractBusiness() {
	}

	/**
	 * 修改销售合同
	 */
	public void updateSaleContract(ToolStripButton button) {
		SalesContractUpdateWin updateWin = new SalesContractUpdateWin("修改合同信息", true,
				null, null, "");
		model.openedWindow = updateWin;
		ShowWindowTools.showWindow(button.getPageRect(), updateWin, 1);
	}

	/**
	 * 添加销售合同
	 */
	public void addSaleContractPact(ToolStripButton button) {
		SalesContractAddWindow addWin = new SalesContractAddWindow("新建合同", true,
				null, null, "");
		model.openedWindow = addWin;
		ShowWindowTools.showWindow(button.getPageRect(), addWin, 1);
	}
}
