package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.service.PrintCargoService;
import com.skynet.spms.client.service.PrintCargoServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class SetPrintCargoWindow extends Window {

	private PrintCargoServiceAsync printCargoService = GWT.create(PrintCargoService.class);

	/**
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param listGrid
	 * @param iconUrl
	 */
	public SetPrintCargoWindow(String windowTitle, boolean isMax,
			final Rectangle rect, final ListGrid listGrid, String iconUrl) {
		final Window objWindow = this;
		objWindow.setWidth(600);
		objWindow.setHeight(300);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(360);
		mainForm.setColWidths(80, 70, 70, 70, 70);
		mainForm.setHeight("90%");
		mainForm.setDataSource(listGrid.getDataSource());

		final RadioGroupItem printDevice = new RadioGroupItem();
		printDevice.setName("newPrintDevice");
		printDevice.setVertical(false);
		printDevice.setValueMap("打印机", "发卡器");
		printDevice.setRedrawOnChange(true);
		printDevice.setTitle("打印设备");
		printDevice.setWidth(200);
		printDevice.setDefaultValue("打印机");
		printDevice.setColSpan(4);

		final RadioGroupItem labelCapacity = new RadioGroupItem();
		labelCapacity.setName("newLabelCapacity");
		labelCapacity.setVertical(false);
		labelCapacity.setValueMap("512Bit", "2048Bit");
		labelCapacity.setRedrawOnChange(true);
		labelCapacity.setTitle("标签容量");
		labelCapacity.setWidth(200);
		labelCapacity.setDefaultValue("512Bit");
		labelCapacity.setColSpan(4);

		mainForm.setFields(printDevice, labelCapacity);

		// 打印按钮
		final IButton btnSave = new IButton();
		btnSave.setTitle("发卡");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				int selRecordCount = listGrid.getSelection().length;
				String[] cargoSpaceIDs = new String[selRecordCount];
				for (int i = 0; i < selRecordCount; i++) {
					cargoSpaceIDs[i] = listGrid.getSelection()[i].getAttribute("id");
				}

				String id = "";
				String a = "";
				int port = 0;
				if (printDevice.getValueAsString() == "打印机") {
					// 标识为远程打印机
					port = 1;
				} else if (printDevice.getValueAsString() == "发卡器") {
					// 标识为发卡器
					port = 2;
				}

				printCargoService.print(cargoSpaceIDs, id, port, a,
					new AsyncCallback<String>() {
						@Override
						public void onFailure(Throwable caught) {
							SC.say("通信连接出错,请重试！");
						}

						@Override
						public void onSuccess(String arg0) {
							String printInfo = arg0.substring(0,1);
							if(printInfo.equalsIgnoreCase("1")){
								SC.say("发卡成功！");	}
							if(printInfo.equalsIgnoreCase("0")){
								SC.say("发卡出错，请重试！");	}
							
						}
					});
			}
		});

		// 取消按钮
		final IButton btnCancel = new IButton();
		btnCancel.setTitle("取消");
		btnCancel.setWidth(65);
		btnCancel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		final BtnsHLayout buttonLayout = new BtnsHLayout();
		buttonLayout.setHeight("10%");
		buttonLayout.addMember(btnSave);
		buttonLayout.addMember(btnCancel);

		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(buttonLayout);

		SetWindow.SetWindowLayout(windowTitle, false, iconUrl, rect, objWindow,
				layout);
	}
}
