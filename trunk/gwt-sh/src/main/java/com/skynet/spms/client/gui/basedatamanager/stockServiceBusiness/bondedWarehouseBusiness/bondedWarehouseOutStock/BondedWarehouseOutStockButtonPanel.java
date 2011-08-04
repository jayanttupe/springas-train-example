/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseOutStock;

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
public class BondedWarehouseOutStockButtonPanel extends BaseButtonToolStript {
	
	private BondedWarehouseOutStockListgrid bondedWarehouseInStockListgrid;

	public BondedWarehouseOutStockButtonPanel(
			final BondedWarehouseOutStockListgrid bondedWarehouseInStockListgrid) {
		super("stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseOutStock");
		this.bondedWarehouseInStockListgrid = bondedWarehouseInStockListgrid;
	}
	
	protected void showWindow(String buttonName, final ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("sendbonded".equalsIgnoreCase(buttonName)) {
			if(bondedWarehouseInStockListgrid.getSelection().length != 0){
				ListGridRecord record = bondedWarehouseInStockListgrid.getSelectedRecord();
				record.setAttribute("isCustoms", true);
				bondedWarehouseInStockListgrid.updateData(record,new DSCallback() {
					
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						// TODO Auto-generated method stub
						SC.say("发送成功");
						return;
					}
				});
			}else{
				SC.say("请选择一条记录");
				return;
			}
		}
		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}
