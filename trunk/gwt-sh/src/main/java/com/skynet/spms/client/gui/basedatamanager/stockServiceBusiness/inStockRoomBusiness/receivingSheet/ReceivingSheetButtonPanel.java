package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.SetPrintCargoWindow;
import com.skynet.spms.client.service.PrintCodeService;
import com.skynet.spms.client.service.PrintCodeServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class ReceivingSheetButtonPanel extends BaseButtonToolStript {

	private ReceivingSheetListgrid receivingSheetManageListgrid;
	private PrintCodeServiceAsync printCodeService = GWT
			.create(PrintCodeService.class);

	public ReceivingSheetButtonPanel(
			final ReceivingSheetListgrid receivingSheetManageListgrid) {
		super("stockServiceBusiness.inStockRoomBusiness.receivingSheet");

		this.receivingSheetManageListgrid = receivingSheetManageListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();

		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new ReceivingSheetDetailWindow("新增收料记录", rect,
					receivingSheetManageListgrid, null, false, false, null,
					"showwindow/stock_add_01.png",false);
			ShowWindowTools.showWondow(rect, objWindow, -1);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (receivingSheetManageListgrid.getSelection().length == 1) {
				objWindow = new ReceivingSheetDetailWindow("修改收料记录", rect,
						receivingSheetManageListgrid, null, true, false, null,
						"showwindow/stock_modify_01.png",false);
				ShowWindowTools.showWondow(rect, objWindow, -1);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (receivingSheetManageListgrid.getSelection().length == 1) {
				objWindow = new ReceivingSheetDetailWindow("查看收料记录", rect,
						receivingSheetManageListgrid, null, true, false, null,
						"showwindow/stock_modify_01.png",true);
				ShowWindowTools.showWondow(rect, objWindow, -1);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (receivingSheetManageListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					receivingSheetManageListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		} else if ("PRINT_RECEIVING_SHEET".equalsIgnoreCase(buttonName)) {
			if (receivingSheetManageListgrid.getSelection().length != 0) {
				SC.confirm("是否确认打印？", new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value == false) {
							return;
						}
						String[] receivingSheetID = new String[receivingSheetManageListgrid
								.getSelection().length];
						ListGridRecord[] selRecord = receivingSheetManageListgrid
								.getSelection();
						for (int i = 0; i < selRecord.length; i++) {
							// 收料单号
							receivingSheetID[i] = "RECD "
									+ selRecord[i]
											.getAttribute("receivingSheetNumber");
						}

						String id = "";
						int port = 0;
						String a = "3";
						printCodeService.print(receivingSheetID, id, port, a,
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
	}
}
