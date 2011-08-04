package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.inStockRecord.SetPrintWindow;
import com.skynet.spms.client.service.PrintCodeService;
import com.skynet.spms.client.service.PrintCodeServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class CargoSpaceManagerButtonPanel extends BaseButtonToolStript {
	private CargoSpaceManagerListgrid cargoSpaceManagerListgrid;

	private PrintCodeServiceAsync printCodeService = GWT
			.create(PrintCodeService.class);

	public CargoSpaceManagerButtonPanel(
			final CargoSpaceManagerListgrid cargoSpaceManagerListgrid) {
		super("stockServiceBusiness.basicData.cargoSpace");
		this.cargoSpaceManagerListgrid = cargoSpaceManagerListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();

		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new CargoSpaceDetailWindow("生成货位", false, rect,
					cargoSpaceManagerListgrid, "showwindow/stock_add_01.png",
					false,false);
			ShowWindowTools.showWondow(rect, objWindow, -1);
		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (cargoSpaceManagerListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					cargoSpaceManagerListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (cargoSpaceManagerListgrid.getSelection().length == 1) {
				objWindow = new CargoSpaceDetailWindow("查看货位", false, rect,
						cargoSpaceManagerListgrid, "showwindow/stock_modify_01.png",
						true,true);
				ShowWindowTools.showWondow(rect, objWindow, -1);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		}
		else if ("set_logic".equalsIgnoreCase(buttonName)) {
			if (cargoSpaceManagerListgrid.getSelection().length != 0) {
				objWindow = new SetLogicStockWindow("设置逻辑库", false, rect,
						cargoSpaceManagerListgrid,
						"showwindow/stock_add_01.png");
				ShowWindowTools.showWondow(rect, objWindow, -1);
			} else {
				SC.say("请选择一条记录进行设置逻辑库！");
			}
		} else if ("split_Merge".equalsIgnoreCase(buttonName)) {
			objWindow = new SplitMergeManageWindow("货位拆分合并管理", false, rect,
					cargoSpaceManagerListgrid, "showwindow/stock_modify_01.png");
			ShowWindowTools.showWondow(rect, objWindow, -1);
		} else if ("PRINT_CARGO_LABEL".equalsIgnoreCase(buttonName)) {
			if (cargoSpaceManagerListgrid.getSelection().length != 0) {
				SC.confirm("是否确认打印？", new BooleanCallback() {

					@Override
					public void execute(Boolean value) {
						if (value == false) {
							return;
						}
						String[] cargoSpaceID = new String[cargoSpaceManagerListgrid
								.getSelection().length];
						ListGridRecord[] selRecord = cargoSpaceManagerListgrid
								.getSelection();

						for (int i = 0; i < selRecord.length; i++) {
							cargoSpaceID[i] = "STK "
									+ selRecord[i]
											.getAttribute("cargoSpaceNumber");
						}
						String id = "";
						int port = 0;
						String a = "";
						printCodeService.print(cargoSpaceID, id, port, a,
								new AsyncCallback<Void>() {
									@Override
									public void onFailure(Throwable caught) {
										SC.say("打印失败");
									}

									@Override
									public void onSuccess(Void arg0) {
										SC.say("打印成功");

									}
								});
					}
				});
			}
		}

		else if ("PRINT_CARGO_CARD".equalsIgnoreCase(buttonName)) {
			if (cargoSpaceManagerListgrid.getSelection().length == 1) {
				objWindow = new SetPrintCargoWindow("打印标签", false, rect,
						cargoSpaceManagerListgrid,
						"showwindow/stock_modify_01.png");
			} else {
				SC.say("请选择一条记录进行设置！");
			}
			ShowWindowTools.showWondow(rect, objWindow, -1);
		}
	}
}
