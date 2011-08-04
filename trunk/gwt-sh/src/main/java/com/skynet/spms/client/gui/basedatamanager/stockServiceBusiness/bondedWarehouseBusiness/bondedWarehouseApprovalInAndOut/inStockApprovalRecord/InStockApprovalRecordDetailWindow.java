package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.inStockApprovalRecord;

import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class InStockApprovalRecordDetailWindow extends Window {

	/**
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param listGrid
	 * @param iconUrl
	 */
	public InStockApprovalRecordDetailWindow(String windowTitle, 
											boolean isMax,
											final Rectangle rect, 
											final ListGrid listGrid, 
											String iconUrl, 
											Boolean updateFlg) {

		final Window objWindow = this;
		setWidth(400);
		setHeight(310);
		
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(200);
		mainForm.setHeight(300);
		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}

		// 检验单号
		final TextItem txtCheckAndAcceptSheetNumber = new TextItem("checkAndAcceptSheetNumber");
		// 通关电子帐册项号
		final TextItem txtAccountBookItemsNumber = new TextItem("accountBookItemsNumber");
		// 件号
		final TextItem txtPartNumber = new TextItem("partNumber");
		// 序号/批号
		final TextItem txtPartSerialNumber = new TextItem("partSerialNumber");
		// 数量
		final TextItem txtQuantity = new TextItem("quantity");
		// 入库审批号
		final TextItem txtInStockApprovalNumber = new TextItem("inStockApprovalNumber");
		// 入库日期
		final CustomDateItem txtInStockDate = new CustomDateItem("inStockDate");
		// 进口报关编号
		final TextItem txtCustomsNumber = new TextItem("customsNumber");
		// 入库单号
		final TextItem txtInStockRecordNumber = new TextItem("inStockRecordNumber");

		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainForm.saveData();
				mainForm.clearValues();
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		final IButton cancelButton = new IButton();
		cancelButton.setTitle("返回");
		cancelButton.setWidth(65);
		cancelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		mainForm.setFields(txtCheckAndAcceptSheetNumber
						,txtAccountBookItemsNumber
						,txtPartNumber
						,txtPartSerialNumber
						,txtQuantity
						,txtInStockApprovalNumber
						,txtInStockDate
						,txtCustomsNumber
						,txtInStockRecordNumber
						);

		final BtnsHLayout buttonLayout = new BtnsHLayout();
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(cancelButton);

		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(buttonLayout);

		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}