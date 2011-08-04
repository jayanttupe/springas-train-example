/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseInventory;

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
public class BondedWarehouseInventoryButtonPanel extends BaseButtonToolStript {
	
	private BondedWarehouseInventoryListgrid bondedWarehouseInStockListgrid;

	public BondedWarehouseInventoryButtonPanel(
			final BondedWarehouseInventoryListgrid bondedWarehouseInStockListgrid) {
		super("stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseInventoryStock");
		this.bondedWarehouseInStockListgrid = bondedWarehouseInStockListgrid;
	}
	
	protected void showWindow(String buttonName, final ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("view".equalsIgnoreCase(buttonName)) {
			if(bondedWarehouseInStockListgrid.getSelection().length != 0){
				
			}else{
				SC.say("请选择一条记录");
				return;
			}
		}
		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}
