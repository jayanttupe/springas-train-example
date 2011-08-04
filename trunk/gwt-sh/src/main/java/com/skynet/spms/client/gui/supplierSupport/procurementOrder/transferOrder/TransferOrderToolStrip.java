package com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.view.TransferOrderViewWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.add.TransferOrderAddWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.business.TransferOrderBusiness;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.transferOrder.modity.TransferOrderModityWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
/**
 * 主订单操作按钮
 * 
 * @author Tony FANG
 * 
 */
public class TransferOrderToolStrip extends BaseButtonToolStript {


	private TransferOrderListGrid listGrid;

	private TransferOrderBusiness business = new TransferOrderBusiness();

	public TransferOrderToolStrip(TransferOrderListGrid listGrid) {
		super(DSKey.S_PROCUREMENTTRANSFERORDER); 

		this.listGrid = listGrid;
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
	}

	protected void showWindow(String buttonName) {

	}

	@Override
	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("MODIFY".equals(buttonName)) {
			
			if (business.canModifiedSheet(listGrid)) {
				TransferOrderModityWindow ppa = new TransferOrderModityWindow(
						"", true, button.getPageRect(), null, "");
				ShowWindowTools.showWindow(button.getPageRect(), ppa, 200);
			}
			
		}

		else if ("DELETE".equals(buttonName)) {
			business.deleteSheet(listGrid);
		} else if ("PUBLISH".equals(buttonName)) {
			business.publishSheet(listGrid);
		} else if ("CANCELPUBLISH".equals(buttonName)) {
			business.cancelPublishSheet(listGrid);
		} else if("VIEW".equals(buttonName)){
			TransferOrderViewWindow ppa = new TransferOrderViewWindow(
					"", true, button.getPageRect(), null, "");
			ShowWindowTools.showWindow(button.getPageRect(), ppa, 200);
		}else if("REFRESH".equals(buttonName)){
			business.refreshSheet(listGrid);
		}
	}
}
