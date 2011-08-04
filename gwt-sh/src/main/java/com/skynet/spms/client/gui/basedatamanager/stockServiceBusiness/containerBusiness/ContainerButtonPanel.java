package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.containerBusiness;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.inStockRecord.SetPrintWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class ContainerButtonPanel extends BaseButtonToolStript {
	private ContainerListgrid containerEntityListgrid;

	public ContainerButtonPanel(
			final ContainerListgrid containerEntityListgrid) {
		super("stockServiceBusiness.basicData.container");
		this.containerEntityListgrid = containerEntityListgrid;
	}
	
	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();

		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new ContainerDetailWindow("添加容器", false, rect, containerEntityListgrid, "showwindow/stock_modify_01.png", false,false);
		}else if ("delete".equalsIgnoreCase(buttonName)) {
			if (containerEntityListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					containerEntityListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		}else if("modify".equalsIgnoreCase(buttonName)){
			if (containerEntityListgrid.getSelection().length == 1) {
				objWindow = new ContainerDetailWindow("修改容器", false, rect, containerEntityListgrid, "showwindow/stock_modify_01.png", true,false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		}else if("view".equalsIgnoreCase(buttonName)){
			if (containerEntityListgrid.getSelection().length == 1) {
				objWindow = new ContainerDetailWindow("查看容器", false, rect, containerEntityListgrid, "showwindow/stock_modify_01.png", true,true);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		}else if ("PRINT_CONTAINER_CARD".equalsIgnoreCase(buttonName)){
			if (containerEntityListgrid.getSelection().length == 1) {
				objWindow = new SetPrintContainerWindow("打印标签", false , rect ,containerEntityListgrid,"showwindow/stock_modify_01.png");
			} else {
				SC.say("请选择一条记录进行设置！");
			}
			ShowWindowTools.showWondow(rect, objWindow, -1);
		}
		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}