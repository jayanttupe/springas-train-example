package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.credentials;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoomDetailWindow;
import com.skynet.spms.client.service.PrintCodeService;
import com.skynet.spms.client.service.PrintCodeServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class CredentialsButtonPanel extends BaseButtonToolStript {
	private CredentialsListgrid credentialsListgrid;

	private PrintCodeServiceAsync printCodeService = GWT
			.create(PrintCodeService.class);

	public CredentialsButtonPanel(final CredentialsListgrid credentialsListgrid) {
		super("stockServiceBusiness.checkAndAcceptBusiness.credentials");
		this.credentialsListgrid = credentialsListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		if ("add".equalsIgnoreCase(buttonName)) {
			Rectangle rect = objButton.getPageRect();
			Window objWindow = new CredentialsDetailWindow("新增证书", false, rect,
					credentialsListgrid, "showwindow/stock_add_01.png", false,false);
			ShowWindowTools.showWondow(rect, objWindow, -1);
		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (credentialsListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					credentialsListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (credentialsListgrid.getSelection().length == 1) {
				Rectangle rect = objButton.getPageRect();
				Window objWindow = new CredentialsDetailWindow("查看证书", false,
						rect, credentialsListgrid,
						"showwindow/stock_add_01.png", true,true);
				ShowWindowTools.showWondow(rect, objWindow, -1);
			} else {
				SC.say("请选择一条记录进行查看！");
			}

		} else if ("PRINT_CREDENTIAL_LABEL".equalsIgnoreCase(buttonName)) {
			if (credentialsListgrid.getSelection().length != 0) {
				SC.confirm("是否确认打印？", new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value == false) {
							return;
						}
						String[] credentialsCodeID = new String[credentialsListgrid
								.getSelection().length];
						ListGridRecord[] selRecord = credentialsListgrid
								.getSelection();
						for (int i = 0; i < selRecord.length; i++) {
							credentialsCodeID[i] = "CER "
									+ selRecord[i]
											.getAttribute("credentialsCode");
						}

						String id = "";
						int port = 0;
						String a = "2";
						printCodeService.print(credentialsCodeID, id, port, a,
								new AsyncCallback<Void>() {
									@Override
									public void onFailure(Throwable caught) {
										// String s = caught.getMessage();
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
