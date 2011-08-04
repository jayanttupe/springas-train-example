/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.customsSupervision.bondedWarehouseOutStockByIsCustoms;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

	/**
	 * @author Administrator
	 *
	 */
public class BondedWarehouseOutStockByIsCustomsButtonPanel extends BaseButtonToolStript {
	
	private BondedWarehouseOutStockByIsCustomsListgrid bondedWarehouseOutStockByIsCustomsListgrid;

	public BondedWarehouseOutStockByIsCustomsButtonPanel(
			final BondedWarehouseOutStockByIsCustomsListgrid bondedWarehouseOutStockByIsCustomsListgrid) {
		super("stockServiceBusiness.bondedWarehouseBusiness.customsSupervision.bondedWarehouseOutStockByIsCustoms");
		this.bondedWarehouseOutStockByIsCustomsListgrid = bondedWarehouseOutStockByIsCustomsListgrid;
	}
	
	protected void showWindow(String buttonName, final ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}
