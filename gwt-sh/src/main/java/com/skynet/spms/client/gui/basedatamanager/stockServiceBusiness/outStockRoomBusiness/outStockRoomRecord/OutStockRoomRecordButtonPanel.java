/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.outStockRoomRecord;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Administrator
 *
 */
public class OutStockRoomRecordButtonPanel extends BaseButtonToolStript{
	
	private OutStockRoomRecordListgrid outStockRoomRecordListgrid;
	
	public OutStockRoomRecordButtonPanel(final OutStockRoomRecordListgrid outStockRoomRecordListgrid){
		super("stockServiceBusiness.outStockRoomBusiness.outStockRoomRecord");

		this.outStockRoomRecordListgrid = outStockRoomRecordListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("print".equalsIgnoreCase(buttonName)) {
			
		} 
		ShowWindowTools.showWondow(rect, objWindow, -1);
	}

}
