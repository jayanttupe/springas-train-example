/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Administrator
 *
 */
public class ClearanceAccountBookButtonPanel extends BaseButtonToolStript {
	private ClearanceAccountBookListgrid clearanceAccountBookListgrid;

	public ClearanceAccountBookButtonPanel(
			final ClearanceAccountBookListgrid clearanceAccountBookListgrid) {
		super("stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook");
		this.clearanceAccountBookListgrid = clearanceAccountBookListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new ClearanceAccountBookDetailsWindow("新增通关电子帐册信息", false, rect, clearanceAccountBookListgrid, "showwindow/stock_add_01.png", false,false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (clearanceAccountBookListgrid.getSelection().length == 1) {
				objWindow = new ClearanceAccountBookDetailsWindow("修改通关电子帐册信息", false, rect, clearanceAccountBookListgrid, "showwindow/stock_modify_01.png", true,false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (clearanceAccountBookListgrid.getSelection().length == 1) {
				objWindow = new ClearanceAccountBookDetailsWindow("查看通关电子帐册信息", false, rect, clearanceAccountBookListgrid, "showwindow/stock_modify_01.png", true,true);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} 
		else if ("delete".equalsIgnoreCase(buttonName)) {
			if (clearanceAccountBookListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					clearanceAccountBookListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}		
		 } else if("order".equalsIgnoreCase(buttonName)){
			
		} else if ("print".equalsIgnoreCase(buttonName)){
			SC.say("打印测试 ");
			Canvas.showPrintPreview(clearanceAccountBookListgrid);
			
		}
	
		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}
