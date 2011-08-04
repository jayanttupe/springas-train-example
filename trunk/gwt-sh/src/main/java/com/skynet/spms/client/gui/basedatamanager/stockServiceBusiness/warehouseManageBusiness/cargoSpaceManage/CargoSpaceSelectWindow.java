package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

public class CargoSpaceSelectWindow extends Window {
	
	public CargoSpaceSelectWindow(
			String windowTitle,
			final Rectangle rect, 
			String iconUrl,
			final DynamicForm mainForm) {
		
		final Window objWindow = this;
		setWidth(800);
		setHeight(300);

		final ListGrid listGrid = new ListGrid();
		listGrid.setCanEdit(false);
		listGrid.setCellHeight(22);
		listGrid.setWidth(600);
		listGrid.setHeight(220);
		listGrid.setMargin(5);
		listGrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取数据源
		String modeName = "stockServiceBusiness.basicData.cargoSpace";
		String dsName = "cargoSpace_dataSource";
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						dataSource.fetchData();
						listGrid.setDataSource(dataSource);
						listGrid.fetchData();

						// 货位编号
						ListGridField cargoSpaceNumberFiled = new ListGridField("cargoSpaceNumber","货位编号");
						// 库房编号
						ListGridField stockRoomNumberFiled = new ListGridField("stockRoomNumber","库房编号");
						// 库房名称
						ListGridField stockRoomChineseNameFiled = new ListGridField("stockRoomChineseName","库房名称");
						// 仓库地区
						ListGridField stockRoomZoneCodeFiled = new ListGridField("stockRoomZoneCode","仓库地区");
						// 库房类型
						ListGridField stockRoomTypeFiled = new ListGridField("stockRoomType","库房类型");
						// 库房楼层
						ListGridField stockRoomFloorCodeFiled = new ListGridField("stockRoomFloorCode","库房楼层");
						// 备件中心位置
						ListGridField partCentreLocationFiled = new ListGridField("partCentreLocation","备件中心位置");
						// 货架排
						ListGridField storageRacksRowCodeFiled = new ListGridField("storageRacksRowCode","货架排");
						// 货架形式
						ListGridField storageRacksTypeCodeFiled = new ListGridField("storageRacksTypeCode","货架形式");
						// 货位RFID标签
						ListGridField cargoSpaceRFIDCodeFiled = new ListGridField("cargoSpaceRFIDCode","货位RFID标签");
						// 货位条形码
						ListGridField cargoSpaceBarCodeFiled = new ListGridField("cargoSpaceBarCode","货位条形码");

						listGrid.setFields(cargoSpaceNumberFiled
								,stockRoomNumberFiled
								,stockRoomChineseNameFiled
								,stockRoomZoneCodeFiled
								,stockRoomTypeFiled
								,stockRoomFloorCodeFiled
								,partCentreLocationFiled
								,storageRacksRowCodeFiled
								,storageRacksTypeCodeFiled
								,cargoSpaceRFIDCodeFiled
								,cargoSpaceBarCodeFiled
								);
					}
				});
		
		final IButton saveButton = new IButton();
		saveButton.setTitle("选择");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Record record = listGrid.getSelectedRecord();
				FormItem[] formItems = mainForm.getFields();
				for (FormItem formItem : formItems)
				{
					// 货位编号
					if (formItem.getName().contains("cargoSpaceNumber"))
					{
						formItem.setValue(record.getAttribute("cargoSpaceNumber"));
					}
				}
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		final IButton returnButton = new IButton();
		returnButton.setTitle("返回");
		returnButton.setWidth(65);
		returnButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		final BtnsHLayout buttonLayout = new BtnsHLayout(); 
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(returnButton);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(listGrid);
		layout.addMember(buttonLayout);
		layout.draw();

		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}
