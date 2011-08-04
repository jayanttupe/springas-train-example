package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

public class PickingPartDetailWindow extends Window {

	/**
	 * 
	 * @param windowTitle
	 * @param rect
	 * @param pickingInstructionDetailListGrid
	 * @param pickingDetailListGrid
	 * @param pickingListID
	 */
	public PickingPartDetailWindow(String windowTitle
			, final Rectangle rect
			, String iconUrl
			, final ListGrid pickingInstructionDetailListGrid
			, final ListGrid pickingDetailListGrid
			, final String pickingListID) {
		final Window objWindow = this;
		setWidth(800);
		setHeight(420);
		
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setNumCols(4);
		mainForm.setWidth(450);
		mainForm.setColWidths(65, 150, 65, 150);
		mainForm.setDataSource(pickingInstructionDetailListGrid.getDataSource());
		mainForm.editRecord(pickingInstructionDetailListGrid.getSelectedRecord());

		// 件号
		final TextItem txtPartNumber = new TextItem("partNumber", "件号");
		txtPartNumber.setDisabled(true);
		// 制造商
		final TextItem txtManufacturer = new TextItem("manufacturer", "制造商");
		txtManufacturer.setDisabled(true);
		// 单位
		final TextItem txtUnit = new TextItem("unit", "单位");
		txtUnit.setDisabled(true);
		// 备件状况
		final TextItem txtPartStatus = new TextItem("partStatus", "备件状况");
		txtPartStatus.setDisabled(true);
		// 备件类型
		final TextItem txtPartType = new TextItem("partType", "备件类型");
		txtPartType.setDisabled(true);
		// 当前库存数量
		final TextItem txtStockQty = new TextItem("stockQty", "当前库存数量");
		txtStockQty.setDisabled(true);
		// 应发数量
		final TextItem txtQty = new TextItem("qty", "应发数量");
		txtQty.setDisabled(true);
		// 已配发数量
		final TextItem txtSendQty = new TextItem("sendQty", "已配发数量");
		txtSendQty.setDisabled(true);
		
		// 当前库存Grid
		final ListGrid partsInventoryRecordListGrid = new ListGrid();
		partsInventoryRecordListGrid.setWidth(600);
		partsInventoryRecordListGrid.setHeight(225);
		partsInventoryRecordListGrid.setMargin(5);
		partsInventoryRecordListGrid.setAutoSaveEdits(false);
		partsInventoryRecordListGrid.setEditEvent(ListGridEditEvent.CLICK);
		
		// 获取指令备件明细数据
		String instructionDetailModeName = "stockServiceBusiness.outStockRoomBusiness.pickingList";
		String instructionDetailDsName = "pickingInventory_dataSource";
		DataSourceTool instructionDetailDST = new DataSourceTool();
		instructionDetailDST.onCreateDataSource(instructionDetailModeName, instructionDetailDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						partsInventoryRecordListGrid.setDataSource(dataSource);

						// 序号/批号
						ListGridField partSerialNumberFiled = new ListGridField("partsInventoryRecord.partSerialNumber","序号/批号");
						// 当前库存数量
						ListGridField balanceQuantityFiled = new ListGridField("partsInventoryRecord.balanceQuantity","当前库存数量");
						balanceQuantityFiled.setType(ListGridFieldType.INTEGER);
						// 单位
						ListGridField unitFiled = new ListGridField("partsInventoryRecord.unit","单位");
						// 备件状况
						ListGridField partStatusFiled = new ListGridField("partsInventoryRecord.partStatus","备件状况");
						// 库房编号
						ListGridField stockRoomNumberFiled = new ListGridField("partsInventoryRecord.stockRoomNumber","库房编号");
						// 货位编号
						ListGridField cargoSpaceNumberFiled = new ListGridField("partsInventoryRecord.cargoSpaceNumber","货位编号");
						// 入库日期
						ListGridField inStockDateFiled = new ListGridField("partsInventoryRecord.inStockDate","入库日期");
						inStockDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
						inStockDateFiled.setType(ListGridFieldType.DATE);
						// 是否冻结
						ListGridField isFreezeFiled = new ListGridField("partsInventoryRecord.isFreeze","是否冻结");
						isFreezeFiled.setType(ListGridFieldType.BOOLEAN);
						// 配料单明细ID
						ListGridField pickingPartIDFiled = new ListGridField("pickingListPartItems.id","配料单明细ID");
						pickingPartIDFiled.setHidden(true);
						// 发料数量
						ListGridField sendQtyFiled = new ListGridField("pickingListPartItems.sendQty","发料数量");
						sendQtyFiled.setType(ListGridFieldType.INTEGER);

						partSerialNumberFiled.setCanEdit(false);
						balanceQuantityFiled.setCanEdit(false);
						unitFiled.setCanEdit(false);
						partStatusFiled.setCanEdit(false);
						stockRoomNumberFiled.setCanEdit(false);
						cargoSpaceNumberFiled.setCanEdit(false);
						inStockDateFiled.setCanEdit(false);
						isFreezeFiled.setCanEdit(false);
						sendQtyFiled.setCanEdit(true);

						partsInventoryRecordListGrid.setFields(
								partSerialNumberFiled
								,balanceQuantityFiled
								,unitFiled
								,partStatusFiled
								,stockRoomNumberFiled
								,cargoSpaceNumberFiled
								,inStockDateFiled
								,isFreezeFiled
								,pickingPartIDFiled
								,sendQtyFiled
								);
						
						Criteria criteria = new Criteria();
						criteria.addCriteria("partNumber", txtPartNumber.getValueAsString());
						criteria.addCriteria("pickingListID", "" + pickingListID.toString());
						partsInventoryRecordListGrid.fetchData(criteria);
					}
				});
		
		// 保存按钮
		final IButton btnSave = new IButton();
		btnSave.setTitle("保存");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int rowCount = partsInventoryRecordListGrid.getTotalRows();
				Record record = new Record();
				// 件号
				String[] partNumbers = new String[rowCount];
				// 序号/批号
				String[] partSerialNumbers = new String[rowCount];
				// 实发数量
				String[] sendQtys = new String[rowCount];
				// 单位
				String[] units = new String[rowCount];	
				// 备件状况
				String[] partStatuss = new String[rowCount];	
				// 库房编号
				String[] stockRoomNumbers = new String[rowCount];	
				// 货位编号
				String[] cargoSpaceNumbers = new String[rowCount];	
				// 配料单ID
				String[] pickinglistIDs = new String[rowCount];	
				// 配料单明细ID
				String[] pickinglistPartItemsIDs = new String[rowCount];
				
				for (int i = 0; i < partsInventoryRecordListGrid.getTotalRows(); i++)
				{
					Record curRecord = partsInventoryRecordListGrid.getEditedRecord(i);
					// 件号
					partNumbers[i] = "" + txtPartNumber.getValue().toString();
					// 序号/批号
					partSerialNumbers[i] = curRecord.getAttribute("partsInventoryRecord.partSerialNumber");
					// 实发数量
					sendQtys[i] = curRecord.getAttribute("pickingListPartItems.sendQty");
					// 单位
					units[i] = curRecord.getAttribute("partsInventoryRecord.unit");	
					// 备件状况
					partStatuss[i] = curRecord.getAttribute("partsInventoryRecord.partStatus");	
					// 库房编号
					stockRoomNumbers[i] = curRecord.getAttribute("partsInventoryRecord.stockRoomNumber");	
					// 货位编号
					cargoSpaceNumbers[i] = curRecord.getAttribute("partsInventoryRecord.cargoSpaceNumber");	
					// 配料单ID
					pickinglistIDs[i] = "" + pickingListID.toString();
					// 配料单明细ID
					pickinglistPartItemsIDs[i] = curRecord.getAttribute("pickingListPartItems.id");
				}

				// 件号
				record.setAttribute("partNumber", partNumbers);
				// 序号/批号
				record.setAttribute("partSerialNumber", partSerialNumbers);
				// 实发数量
				record.setAttribute("sendQty", sendQtys);
				// 单位
				record.setAttribute("unit", units);
				// 备件状况
				record.setAttribute("partStatus", partStatuss);
				// 库房编号
				record.setAttribute("stockRoomNumber", stockRoomNumbers);	
				// 货位编号
				record.setAttribute("cargoSpaceNumber", cargoSpaceNumbers);	
				// 配料单ID
				record.setAttribute("pickingListID", pickinglistIDs);	
				// 配料单明细ID
				record.setAttribute("pickinglistPartItemsID", pickinglistPartItemsIDs);
				partsInventoryRecordListGrid.updateData(record, new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("保存成功！");
						
						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						criteria.addCriteria("pickingListID", "" + pickingListID);
						criteria.addCriteria("partNumber", "" + txtPartNumber.getValue().toString());
						pickingDetailListGrid.fetchData(criteria);
					}
				});
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}

		});

		// 返回按钮
		final IButton btnReturn = new IButton();
		btnReturn.setTitle("返回");
		btnReturn.setWidth(65);
		btnReturn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		mainForm.setFields(txtPartNumber
						,txtManufacturer
						,txtUnit
						,txtPartStatus
						,txtPartType
						,txtStockQty
						,txtQty
						,txtSendQty
						);

		final BtnsHLayout layoutHeadBtn = new BtnsHLayout(); 
		layoutHeadBtn.addMember(btnSave);
		layoutHeadBtn.addMember(btnReturn);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(partsInventoryRecordListGrid);
		layout.addMember(layoutHeadBtn);
		
		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}