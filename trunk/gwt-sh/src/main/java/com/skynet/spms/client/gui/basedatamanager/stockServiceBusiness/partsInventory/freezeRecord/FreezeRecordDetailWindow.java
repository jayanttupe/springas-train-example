package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.partsInventory.freezeRecord;

import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class FreezeRecordDetailWindow extends Window {

	/**
	 * @param windowTitle
	 * @param isMax
	 * @param srcRect
	 * @param listGrid
	 * @param iconUrl
	 */
	public FreezeRecordDetailWindow(String windowTitle
			,boolean isMax
			,final Rectangle rect
			,final ListGrid listGrid
			,String iconUrl
			,Boolean updateFlg) {
		final Window objWindow = this;
		setWidth(760);
		setHeight(400);
		
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setDataSource(listGrid.getDataSource());
		mainForm.setWidth(560);
		mainForm.setNumCols(6);
		mainForm.setHeight("90%");
		mainForm.setColWidths(100, 60, 50, 60, 120, 170);
		mainForm.setLayoutAlign(VerticalAlignment.TOP);
		
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}

		// 库房编号
		final TextItem txtStockRoomNumber = new TextItem("partsInventoryRecord.stockRoomNumber");
		txtStockRoomNumber.setColSpan(3);
		txtStockRoomNumber.setWidth(170);
		txtStockRoomNumber.setDisabled(true);
		// 件号
		final TextItem txtPartNumber = new TextItem("partsInventoryRecord.partNumber");
		txtPartNumber.setWidth(170);
		txtPartNumber.setDisabled(true);
		// 关键字
		final TextItem txtKeyWord = new TextItem("partsInventoryRecord.keyword");
		txtKeyWord.setColSpan(3);
		txtKeyWord.setWidth(170);
		txtKeyWord.setDisabled(true);
		// 序号/批号
		final TextItem txtPartSerialNumber = new TextItem("partsInventoryRecord.partSerialNumber");
		txtPartSerialNumber.setWidth(170);
		txtPartSerialNumber.setDisabled(true);
		// 数量
		final IntegerItem txtQuantity = new IntegerItem("partsInventoryRecord.balanceQuantity");
		txtQuantity.setWidth(55);
		txtQuantity.setDisabled(true);
		// 单位
		final TextItem txtUnitMeasureCode = new TextItem("partsInventoryRecord.unit");
		txtUnitMeasureCode.setWidth(55);
		txtUnitMeasureCode.setDisabled(true);
		// 入库日期
    	final CustomDateItem txtInStockDate = new CustomDateItem("partsInventoryRecord.inStockDate");
    	txtInStockDate.setWidth(170);
    	txtInStockDate.setDisabled(true);
    	// 备件状态
    	final TextItem txtState = new TextItem("partsInventoryRecord.state");
    	txtState.setColSpan(3);
    	txtState.setWidth(170);
    	txtState.setDisabled(true);
    	// 寿命期
    	final TextItem txtRemainLife = new TextItem("partsInventoryRecord.usefulLifePeriod");
    	txtRemainLife.setWidth(170);
    	txtRemainLife.setDisabled(true);
    	// 冻结数量
		final TextItem txtFreezeQuantity = new TextItem("freezeQuantity");
		txtFreezeQuantity.setColSpan(3);
		txtFreezeQuantity.setWidth(170);
		// 冻结用途
		final TextItem txtFreezeApplication = new TextItem("freezeApplication");
		txtFreezeApplication.setWidth(170);
		// 冻结原因
		final TextAreaItem txtFreezeReasonCode = new TextAreaItem("freezeReasonCode");
		txtFreezeReasonCode.setColSpan(5);
		txtFreezeReasonCode.setWidth(460); 
		txtFreezeReasonCode.setHeight(50); 
		// 自动解除日期
		final CustomDateItem txtFreezeAutoRemoveDate = new CustomDateItem("freezeAutoRemoveDate");
		txtFreezeAutoRemoveDate.setColSpan(3);
		txtFreezeAutoRemoveDate.setWidth(170);
		// 无期限(直至人工解除)
		final CheckboxItem txtInfinitiRemove = new CheckboxItem("infinitiRemove");
		txtInfinitiRemove.setWidth(100);
		// 冻结日期
		final CustomDateItem txtFreezeDate = new CustomDateItem("freezeDate");
		txtFreezeDate.setColSpan(3);
		txtFreezeDate.setWidth(170);
		// 冻结人
		final TextItem txtFreezeOperator = new TextItem("freezeOperator");
		txtFreezeOperator.setWidth(170);
		
		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (txtFreezeQuantity.getAttribute("Value") == null
						|| txtFreezeQuantity.getAttribute("Value").equals("")) {
					SC.say("请输入一个冻结数量！");
					return;
				}
				
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						listGrid.fetchData(new Criteria("temp", String.valueOf(Math.random())));
					}
				});
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
					    ,txtKeyWord
					    ,txtPartSerialNumber
					    ,txtQuantity
					    ,txtUnitMeasureCode
					    ,txtInStockDate
					    ,txtState
					    ,txtRemainLife
					    ,txtFreezeQuantity
					    ,txtFreezeApplication
					    ,txtFreezeReasonCode
					    ,txtFreezeDate
					    ,txtFreezeOperator
					    ,txtFreezeAutoRemoveDate
					    ,txtInfinitiRemove
					    );
		
		final BtnsHLayout buttonLayout = new BtnsHLayout();
		buttonLayout.setHeight("10%");
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