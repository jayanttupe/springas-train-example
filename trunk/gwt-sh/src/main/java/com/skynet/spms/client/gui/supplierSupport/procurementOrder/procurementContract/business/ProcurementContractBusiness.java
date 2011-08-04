package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.business;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.add.ProcurementContractAddWin;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.model.ProcurementContractModelLocator;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.update.ProcurementContractUpdateWin;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 菜单操作方法
 * 
 * @author gqr
 * 
 */

public class ProcurementContractBusiness extends BaseBusiness {

	public ProcurementContractModelLocator model = ProcurementContractModelLocator
			.getInstance();

	public ProcurementContractBusiness() {

	}

	/**
	 * 修改采购合同
	 */
	public void updateProcurementContract(ToolStripButton button) {
		ProcurementContractUpdateWin updateWin = new ProcurementContractUpdateWin(
				"修改合同", true, null, null, "");
		model.openedWindow=updateWin;
		ShowWindowTools.showWindow(button.getPageRect(), updateWin, 1);
	}

	/**
	 * 添加采购合同
	 */
	public void addProcurementContract(ToolStripButton button) {

		ProcurementContractAddWin addWin = new ProcurementContractAddWin(
				"新建采购合同", true, null, null, "");
		model.openedWindow=addWin;
		ShowWindowTools.showWindow(button.getPageRect(), addWin, 1);
	}
}
