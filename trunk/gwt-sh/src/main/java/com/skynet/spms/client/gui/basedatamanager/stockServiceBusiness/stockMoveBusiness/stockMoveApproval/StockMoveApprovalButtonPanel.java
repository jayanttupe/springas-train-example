
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveApproval;

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

public class StockMoveApprovalButtonPanel extends BaseButtonToolStript{

	private StockMoveApprovalListgrid stockMoveOutRecordListgrid;
	private StockMoveApprovalItemListgrid stockMoveOutRecordItemListgrid;
	
	public StockMoveApprovalButtonPanel(final StockMoveApprovalListgrid stockMoveOutRecordListgrid,final StockMoveApprovalItemListgrid
			 stockMoveOutRecordItemListgrid){
		super("stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveApproval");
		this.stockMoveOutRecordListgrid = stockMoveOutRecordListgrid;
		this.stockMoveOutRecordItemListgrid = stockMoveOutRecordItemListgrid;
	}
	
	protected void showWindow(String buttonName, ToolStripButton objButton) {
		
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
        
		if ("pass".equalsIgnoreCase(buttonName)) {
			if (stockMoveOutRecordListgrid.getSelection().length == 1) {
				ListGridRecord rdd = stockMoveOutRecordListgrid.getSelectedRecord();
				 rdd.setAttribute("type", "approval");
				 rdd.setAttribute("state", "审批完毕");
				 stockMoveOutRecordListgrid.updateData(rdd);
			} else {
				SC.say("请选择一条记录！");
			}
		} else if ("back".equalsIgnoreCase(buttonName)) {
			if (stockMoveOutRecordListgrid.getSelection().length != 0) {
				ListGridRecord rdd = stockMoveOutRecordListgrid.getSelectedRecord();
				 rdd.setAttribute("type", "approval");
				 rdd.setAttribute("state", "");
				 stockMoveOutRecordListgrid.updateData(rdd);							
			} else {
				SC.say("请选择一条记录！");
			}		
		 } 

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}
