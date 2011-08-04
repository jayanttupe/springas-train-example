package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class CheckAndAcceptSheetButtonPanel extends BaseButtonToolStript {

	private CheckAndAcceptSheetListgrid checkAndAcceptSheetListgrid;
	
	public CheckAndAcceptSheetButtonPanel(final CheckAndAcceptSheetListgrid checkAndAcceptSheetListgrid){
		super("stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet");
		this.checkAndAcceptSheetListgrid = checkAndAcceptSheetListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new CheckAndAcceptSheetDetailWindow("新建领料验收单",false,rect,checkAndAcceptSheetListgrid, null, "showwindow/stock_add_01.png", false,false,null,false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (checkAndAcceptSheetListgrid.getSelection().length == 1) {
				objWindow = new CheckAndAcceptSheetDetailWindow("修改领料验收单", false ,rect, checkAndAcceptSheetListgrid, null, "showwindow/stock_modify_01.png", true,false,null,false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		}else if ("view".equalsIgnoreCase(buttonName)) {
			if (checkAndAcceptSheetListgrid.getSelection().length == 1) {
				objWindow = new CheckAndAcceptSheetDetailWindow("查看领料验收单", false ,rect, checkAndAcceptSheetListgrid, null, "showwindow/stock_modify_01.png", true,false,null,true);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		}else if ("delete".equalsIgnoreCase(buttonName)) {
			if (checkAndAcceptSheetListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					checkAndAcceptSheetListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}