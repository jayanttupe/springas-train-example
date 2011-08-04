package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.partsInventory.timeControlPartBusiness;

import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class TimeControlPartBusinessDetailWindow extends Window {

	/**
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param listGrid
	 * @param iconUrl
	 */
	public TimeControlPartBusinessDetailWindow(String windowTitle, 
											boolean isMax,
											final Rectangle rect, 
											ListGrid listGrid, 
											String iconUrl, 
											Boolean updateFlg) {
		
		final Window objWindow = this;
		setWidth(722);
		setHeight(500);
		
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setDataSource(listGrid.getDataSource());
		mainForm.setNumCols(8);
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}
		
		final TextItem txtStockRoomNumber = new TextItem("stockRoomNumber");
		txtStockRoomNumber.setWidth(201);
		txtStockRoomNumber.setDisabled(true);
		txtStockRoomNumber.setColSpan(3);
		
		final TextItem txtPartNumber = new TextItem("partNumber");
		txtPartNumber.setWidth(201);
		txtPartNumber.setDisabled(true);
		txtPartNumber.setColSpan(3);
		
		final TextItem txtKeyCode = new TextItem("keyCode");
		txtKeyCode.setWidth(201);
		txtKeyCode.setDisabled(true);
		txtKeyCode.setColSpan(3);

		final TextItem txtPartSerialNumber = new TextItem("partSerialNumber");
		txtPartSerialNumber.setWidth(201);
		txtPartSerialNumber.setDisabled(true);
		txtPartSerialNumber.setColSpan(3);
		
		final TextItem txtQuantity = new TextItem("quantity");
		txtQuantity.setWidth(50);
		txtQuantity.setDisabled(true);
		txtQuantity.setColSpan(1);

		final SelectItem txtUnitOfMeasure = new SelectItem("unitOfMeasure");
		txtUnitOfMeasure.setWidth(50);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode", txtUnitOfMeasure);
		txtUnitOfMeasure.setDisabled(true);
		txtUnitOfMeasure.setColSpan(1);
		
		final CustomDateItem txtManufactureDate = new CustomDateItem("manufactureDate");
		txtManufactureDate.setWidth(201);
		txtManufactureDate.setDisabled(true);
		txtManufactureDate.setColSpan(3);
		
		final TextItem txtPartStatusCode = new TextItem("partStatusCode");
		txtPartStatusCode.setWidth(201);
		txtPartStatusCode.setDisabled(true);
		txtPartStatusCode.setColSpan(3);
		
		final TextItem txtCargoSpaceNumber = new TextItem("cargoSpaceNumber");
		txtCargoSpaceNumber.setWidth(201);
		txtCargoSpaceNumber.setDisabled(true);
		txtCargoSpaceNumber.setColSpan(3);
		
		final CustomDateItem txtTimeControlTaskEndDate = new CustomDateItem("timeControlTaskEndDate");
		txtTimeControlTaskEndDate.setWidth(201);
		txtTimeControlTaskEndDate.setDisabled(true);
		txtTimeControlTaskEndDate.setColSpan(3);
		
		final TextItem txtPeriodicCheckCode = new TextItem("periodicCheckCode");
		txtPeriodicCheckCode.setWidth(201);
		txtPeriodicCheckCode.setDisabled(true);
		txtPeriodicCheckCode.setColSpan(3);
		
		final TextItem txtRenewalProcedureCode = new TextItem("renewalProcedureCode");
		txtRenewalProcedureCode.setWidth(201);
		txtRenewalProcedureCode.setDisabled(true);
		txtRenewalProcedureCode.setColSpan(3);
		
		final TextItem txtTimeControlTaskCycle = new TextItem("timeControlTaskCycle");
		txtTimeControlTaskCycle.setWidth(201);
		txtTimeControlTaskCycle.setDisabled(true);
		txtTimeControlTaskCycle.setColSpan(3);
		//本次工作日期
		final CustomDateItem thisWorkDate = new CustomDateItem("thisWorkDate");
		thisWorkDate.setWidth(201);
		thisWorkDate.setHint("<font color = 'red'>*</font>");
		thisWorkDate.setColSpan(3);
		//下次到期日期
		final CustomDateItem theNextDueDate = new CustomDateItem("theNextDueDate");
		theNextDueDate.setWidth(201);
		theNextDueDate.setHint("<font color = 'red'>*</font>");
		theNextDueDate.setColSpan(3);
		//处理人
		final TextItem createBy = new TextItem("createBy");
		createBy.setWidth(201);
		createBy.setDisabled(true);
		createBy.setColSpan(3);
		//处理日期
		final CustomDateItem createDate = new CustomDateItem("createDate");
		createDate.setWidth(201);
		createDate.setDisabled(true);
		createDate.setColSpan(3);
		//完成工作说明
		final TextAreaItem finishWorkInstructions = new TextAreaItem("finishWorkInstructions");
		finishWorkInstructions.setWidth(500);
		finishWorkInstructions.setHeight(75);
		finishWorkInstructions.setHint("<font color = 'red'>*</font>");
		finishWorkInstructions.setColSpan(7);

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

		mainForm.setFields(txtStockRoomNumber
						,txtPartNumber
						,txtKeyCode
						,txtPartSerialNumber
						,txtQuantity
						,txtUnitOfMeasure
						,txtManufactureDate
						,txtPartStatusCode
						,txtCargoSpaceNumber
						,txtTimeControlTaskEndDate
						,txtPeriodicCheckCode
						,txtRenewalProcedureCode
						,txtTimeControlTaskCycle
						,thisWorkDate
						,theNextDueDate
						,createBy
						,createDate
						,finishWorkInstructions
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