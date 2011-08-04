package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.inStockRecord;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpaceManagerListgrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

public class SetCargoSpaceWindow extends Window {

	private CargoSpaceManagerListgrid cargoSpaceManagerListgrid;

	public SetCargoSpaceWindow(String windowTitle
			, boolean isMax
			, final Rectangle rect
			, final ListGrid listGrid
			, String iconUrl
			, Boolean updateFlg) {
		final Window objWindow = this;
		setWidth(840);
		setHeight(500);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setDataSource(listGrid.getDataSource());
		mainForm.setPadding(5);
		mainForm.setNumCols(8);
		mainForm.setColWidths(60, 100, 40, 100, 65, 80, 60, 120);
		mainForm.setWidth(630);
		final Record record = listGrid.getSelectedRecord();
		mainForm.editRecord(record);

		// 检验单号
		final TextItem checkAndAcceptSheetNumber = new TextItem("checkAndAcceptSheetNumber", "检验单号");
		checkAndAcceptSheetNumber.setDisabled(true);
		checkAndAcceptSheetNumber.setWidth(100);
		// 件号
		final TextItem partNumber = new TextItem("partNumber", "件号");
		partNumber.setDisabled(true);
		partNumber.setWidth(100);
		// 序号/批号
		final TextItem partSerialNumber = new TextItem("partSerialNumber", "序号/批号");
		partSerialNumber.setWidth(80);
		partSerialNumber.setDisabled(true);
		// 货位编号
		final TextItem txtRecCargoSpaceNum = new TextItem("recCargoSpaceNum",
				"货位编号");
		txtRecCargoSpaceNum.setWidth(120);
		txtRecCargoSpaceNum.setDisabled(true);

		mainForm.setFields(checkAndAcceptSheetNumber,partNumber, partSerialNumber,
				txtRecCargoSpaceNum);

		// 保存按钮
		final IButton btnSave = new IButton();
		btnSave.setTitle("保存");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						Criteria criteria = new Criteria();
						criteria.addCriteria("temp",
								String.valueOf(Math.random()));
						criteria.addCriteria("shelvingStatus", "NotSend");
						listGrid.fetchData(criteria);
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

		cargoSpaceManagerListgrid = new CargoSpaceManagerListgrid();
		cargoSpaceManagerListgrid.setWidth100();
		cargoSpaceManagerListgrid.setHeight(220);
		cargoSpaceManagerListgrid.setMargin(5);
		cargoSpaceManagerListgrid.setShowFilterEditor(true);

		// 获取数据源
		String modeName1 = "stockServiceBusiness.basicData.cargoSpace";
		String dsName1 = "cargoSpace_dataSource";
		DataSourceTool dataSourceTool1 = new DataSourceTool();
		dataSourceTool1.onCreateDataSource(modeName1, dsName1,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						cargoSpaceManagerListgrid.setDataSource(dataSource);
						cargoSpaceManagerListgrid.fetchData();
						cargoSpaceManagerListgrid
								.drawCargoSpaceManagerListgrid();
						cargoSpaceManagerListgrid
								.setSelectionType(SelectionStyle.SINGLE);
					}
				});

		cargoSpaceManagerListgrid.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String recCargoSpaceNum = cargoSpaceManagerListgrid
						.getSelectedRecord().getAttribute("cargoSpaceNumber");
				String stockRoomNumber = cargoSpaceManagerListgrid
						.getSelectedRecord().getAttribute("stockRoomNumber");
				String stockRoomChineseName = cargoSpaceManagerListgrid
						.getSelectedRecord().getAttribute(
								"stockRoomChineseName");
				mainForm.setValue("recCargoSpaceNum", recCargoSpaceNum);
				mainForm.setValue("stockRoomNumber", stockRoomNumber);
				mainForm.setValue("stockRoomChineseName", stockRoomChineseName);
			}
		});

		final ListGrid pListgrid = new ListGrid();
		pListgrid.setCellHeight(22);
		pListgrid.setWidth100();
		pListgrid.setHeight(170);
		pListgrid.setMargin(5);

		String modeName2 = "stockServiceBusiness.partsInventory.partsInventoryRecord";
		String dsName2 = "partsInventoryRecord_dataSource";

		DataSourceTool dataSourceTool2 = new DataSourceTool();
		dataSourceTool2.onCreateDataSource(modeName2, dsName2,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pListgrid.setDataSource(dataSource);

						pListgrid.setCanRemoveRecords(true);
						pListgrid.setSelectionType(SelectionStyle.SINGLE);
						pListgrid.setCanEdit(false);
						
						// 件号 
						ListGridField partNumberFiled = new ListGridField("partNumber");
						// 序号/批号
						ListGridField partSerialNumberFiled = new ListGridField("partSerialNumber");
						// 供货单位
						ListGridField supplyUnitFiled = new ListGridField("supplyUnit");
						// 货位编号
						ListGridField cargoSpaceNumberFiled = new ListGridField("cargoSpaceNumber");
						// 库房编号
						ListGridField stockRoomNumberFiled = new ListGridField("stockRoomNumber");
						// 区域编号
						ListGridField stockAreaNumberFiled = new ListGridField("stockAreaNumber");
						partNumberFiled.setCanFilter(true);
						partSerialNumberFiled.setCanFilter(true);
						cargoSpaceNumberFiled.setCanFilter(true);

						pListgrid.setFields(partNumberFiled
								,partSerialNumberFiled
								,supplyUnitFiled
								,cargoSpaceNumberFiled
								,stockRoomNumberFiled
								,stockAreaNumberFiled
								);
						
						Criteria criteria = new Criteria();
						criteria.addCriteria("partNumber", record.getAttribute("partNumber"));
						pListgrid.fetchData(criteria);
					}
				});

		final BtnsHLayout buttonLayout = new BtnsHLayout();
		buttonLayout.addMember(btnSave);
		buttonLayout.addMember(btnReturn);

		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(pListgrid);
		layout.addMember(cargoSpaceManagerListgrid);
		layout.addMember(buttonLayout);

		SetWindow.SetWindowLayout(windowTitle
				, false
				, iconUrl
				, rect
				, objWindow
				, layout
				);
	}
}