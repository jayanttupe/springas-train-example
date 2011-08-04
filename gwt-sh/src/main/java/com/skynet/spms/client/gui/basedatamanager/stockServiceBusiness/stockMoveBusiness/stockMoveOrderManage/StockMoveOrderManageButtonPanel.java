/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveOrderManage;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Administrator
 *
 */
public class StockMoveOrderManageButtonPanel extends BaseButtonToolStript{

	private StockMoveOrderManageListgrid stockMoveOrderManageListgrid;
	
	public StockMoveOrderManageButtonPanel(final StockMoveOrderManageListgrid stockMoveOrderManageListgrid){
		super("stockServiceBusiness.stockMoveBusiness.stockMoveOrderManage");
		this.stockMoveOrderManageListgrid = stockMoveOrderManageListgrid;
	}
	
	protected void showWindow(String buttonName, ToolStripButton objButton) {
		
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		 if ("view".equalsIgnoreCase(buttonName)) {
			if (stockMoveOrderManageListgrid.getSelection().length == 1) {
				objWindow = new StockMoveOrderManageViewWindow(rect, stockMoveOrderManageListgrid);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}

}
