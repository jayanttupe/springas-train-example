
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveIn;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Administrator
 *
 */

public class StockMoveInRecordButtonPanel extends BaseButtonToolStript{

	private StockMoveInRecordListgrid stockMoveInRecordListgrid;
	
	public StockMoveInRecordButtonPanel(final StockMoveInRecordListgrid stockMoveInRecordListgrid){
		super("stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveInRecord");
		this.stockMoveInRecordListgrid = stockMoveInRecordListgrid;
	}
	
	protected void showWindow(String buttonName, ToolStripButton objButton) {
		
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		 if ("sure_move".equalsIgnoreCase(buttonName)) {
			 if (stockMoveInRecordListgrid.getSelection().length == 1) {
				ListGridRecord rdd = stockMoveInRecordListgrid.getSelectedRecord();
				if(rdd.getAttribute("state").equals("已移入")){
					SC.say("已经移入库！");
					return;
				}
				rdd.setAttribute("state", "已移入");
				stockMoveInRecordListgrid.updateData(rdd);
			 }else{
			 	SC.say("请选择一条记录！");
			}
		 }
		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}
