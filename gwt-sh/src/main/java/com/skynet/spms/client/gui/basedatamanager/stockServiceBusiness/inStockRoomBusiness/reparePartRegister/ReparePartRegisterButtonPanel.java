package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.reparePartRegister;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class ReparePartRegisterButtonPanel extends BaseButtonToolStript {
	private ReparePartRegisterListgrid reparePartRegisterBusinessListgrid;

	public ReparePartRegisterButtonPanel(
			final ReparePartRegisterListgrid reparePartRegisterListgrid) {
		super("stockServiceBusiness.inStockRoomBusiness.reparePartRegister");
		this.reparePartRegisterBusinessListgrid = reparePartRegisterListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new ReparePartRegisterDetailWindow("新增送修单登记", false, rect, reparePartRegisterBusinessListgrid, "showwindow/stock_add_01.png", false,false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (reparePartRegisterBusinessListgrid.getSelection().length == 1) {
				objWindow = new ReparePartRegisterDetailWindow("修改送修单登记", false, rect, reparePartRegisterBusinessListgrid, "showwindow/stock_modify_01.png", true,false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		}else if ("view".equalsIgnoreCase(buttonName)) {
			if (reparePartRegisterBusinessListgrid.getSelection().length == 1) {
				objWindow = new ReparePartRegisterDetailWindow("查看送修单登记", false, rect, reparePartRegisterBusinessListgrid, "showwindow/stock_modify_01.png", true,true);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		}
		else if ("delete".equalsIgnoreCase(buttonName)) {
			if (reparePartRegisterBusinessListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					reparePartRegisterBusinessListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}