package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.cargoSpaceManagePolicy;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class CargoSpaceManagePolicyButtonPanel extends BaseButtonToolStript {
	private CargoSpaceManagePolicyListgrid cargoSpaceManagePolicyListgrid;

	public CargoSpaceManagePolicyButtonPanel(
			final CargoSpaceManagePolicyListgrid cargoSpaceManagePolicyListgrid) {
		super("stockServiceBusiness.basicData.cargoSpaceManagePolicy");
		this.cargoSpaceManagePolicyListgrid = cargoSpaceManagePolicyListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new CargoSpaceManagePolicyDetailWindow("新增货位管理策略", false, rect, cargoSpaceManagePolicyListgrid, "showwindow/stock_add_01.png", false,false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (cargoSpaceManagePolicyListgrid.getSelection().length == 1) {
				objWindow = new CargoSpaceManagePolicyDetailWindow("修改货位管理策略", false, rect, cargoSpaceManagePolicyListgrid, "showwindow/stock_modify_01.png", true,false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (cargoSpaceManagePolicyListgrid.getSelection().length == 1) {
				objWindow = new CargoSpaceManagePolicyDetailWindow("查看货位管理策略", false, rect, cargoSpaceManagePolicyListgrid, "showwindow/stock_modify_01.png", true,true);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (cargoSpaceManagePolicyListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					cargoSpaceManagePolicyListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}