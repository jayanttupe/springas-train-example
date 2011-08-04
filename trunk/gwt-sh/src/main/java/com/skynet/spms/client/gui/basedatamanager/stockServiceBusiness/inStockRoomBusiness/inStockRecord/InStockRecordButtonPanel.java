package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.inStockRecord;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.service.PrintMatrixService;
import com.skynet.spms.client.service.PrintMatrixServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class InStockRecordButtonPanel extends BaseButtonToolStript {
	
	private PrintMatrixServiceAsync printMatrixService = GWT.create(PrintMatrixService.class);
	private InStockRecordListgrid inStockRecordListgrid;

	public InStockRecordButtonPanel(
			final InStockRecordListgrid inStockRecordListgrid) {
		super("stockServiceBusiness.inStockRoomBusiness.inStockRecord");
		this.inStockRecordListgrid = inStockRecordListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();

		if ("set".equalsIgnoreCase(buttonName)) {
			if (inStockRecordListgrid.getSelection().length == 1) {
				objWindow = new SetCargoSpaceWindow("设置货位", false, rect, inStockRecordListgrid, "showwindow/stock_modify_01.png", false);
			} else {
				SC.say("请选择一条记录进行设置！");
			}
			ShowWindowTools.showWondow(rect, objWindow, -1);
		} else if ("order".equalsIgnoreCase(buttonName) || "sendcard".equalsIgnoreCase(buttonName)) {
			// 取得选择行
			ListGridRecord[] rdsGridRecords = inStockRecordListgrid.getSelection();
			String[] inStockRecordID = new String[rdsGridRecords.length];
			for (int i = 0; i < rdsGridRecords.length; i++) {
				if (rdsGridRecords[i].getAttribute("recCargoSpaceNum") == null ||
						rdsGridRecords[i].getAttributeAsString("recCargoSpaceNum").equals("")) {
					SC.say("推荐货位不能为空！");
					return;
				}
				inStockRecordID[i] = rdsGridRecords[i].getAttributeAsString("id");
			}
			if (inStockRecordListgrid.getSelection().length >= 1) {
				ListGridRecord record = new ListGridRecord();
				// 设置下达类型
				if ("order".equalsIgnoreCase(buttonName)) {
					record.setAttribute("type", "order");					
				}
				if ("sendcard".equalsIgnoreCase(buttonName)) {
					record.setAttribute("type", "sendcard");
				}
				record.setAttribute("inStockRecordID", inStockRecordID);
				inStockRecordListgrid.updateData(record, new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("下达成功！");
						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						criteria.addCriteria("shelvingStatus", "NotSend");
						inStockRecordListgrid.fetchData(criteria);
					}
				});
			} else {
				SC.say("请选择一条记录下达指令！");
			}

			return;
		}else if ("PRINT_ALL_LABEL".equalsIgnoreCase(buttonName)){
			if (inStockRecordListgrid.getSelection().length == 1) {
				objWindow = new SetPrintWindow("打印标签", false , rect ,inStockRecordListgrid,"showwindow/stock_add_01.png");
			} else {
				SC.say("请选择一条记录进行设置！");
			}
			ShowWindowTools.showWondow(rect, objWindow, -1);
		}else if("PRINT_MATRIX".equalsIgnoreCase(buttonName)){
			if (inStockRecordListgrid.getSelection().length != 0) {
				SC.confirm("是否确认打印？", new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value == false) {
							return;
						}
						
						int selRecordCount = inStockRecordListgrid.getSelection().length;
						String[] inStockRecordIDs = new String[selRecordCount];
						for (int i = 0; i < selRecordCount; i++) {
							inStockRecordIDs[i] = inStockRecordListgrid.getSelection()[i].getAttribute("id");
						}
						String id = "";
						int port = 0;
						String a = "";
					
						printMatrixService.print(inStockRecordIDs, id, port, a,
							new AsyncCallback<Void>() {
								@Override
								public void onFailure(Throwable caught) {
									SC.say("打印失败");
								}

								@Override
								public void onSuccess(Void arg0) {
									SC.say("打印成功");
								}
							}
						);
					}
				});
			}
		}
	}
}