package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.allocationBillBusiness;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class AllocationBillBusinessButtonPanel extends BaseButtonToolStript {
	private AllocationBillBusinessListgrid allocationBillBusinessListgrid;

	public AllocationBillBusinessButtonPanel(
			final AllocationBillBusinessListgrid allocationBillBusinessListgrid) {
		super("stockServiceBusiness.partsInventory.allocationBillBusiness");
		this.allocationBillBusinessListgrid = allocationBillBusinessListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new AllocationBillBusinessDetailWindow("新建调拨单", false, rect, allocationBillBusinessListgrid, "showwindow/stock_add_01.png", false,false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (allocationBillBusinessListgrid.getSelection().length == 1) {
				objWindow = new AllocationBillBusinessDetailWindow("修改调拨单", false, rect, allocationBillBusinessListgrid, "showwindow/stock_modify_01.png", true,false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (allocationBillBusinessListgrid.getSelection().length == 1) {
				objWindow = new AllocationBillBusinessDetailWindow("查看调拨单", false, rect, allocationBillBusinessListgrid, "showwindow/stock_modify_01.png", true,true);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} 	
		else if ("delete".equalsIgnoreCase(buttonName)) {
			if (allocationBillBusinessListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					allocationBillBusinessListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}