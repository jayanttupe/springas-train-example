package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.stockRoomManage;

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
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

public class StockRoomSelectWindow extends Window {
	
	public StockRoomSelectWindow(
			String windowTitle,
			final Rectangle rect, 
			String iconUrl,
			final DynamicForm mainForm) {
		
		final Window objWindow = this;
		setWidth(800);
		setHeight(300);
		
		// 获取数据源
		String modeName = "stockServiceBusiness.basicData.stockRoom";
		String dsName = "stockRoom_dataSource";
		
		final ListGrid listGrid = new ListGrid();
		listGrid.setCanEdit(false);
		listGrid.setCellHeight(22);
		listGrid.setWidth(600);
		listGrid.setHeight(220);
		listGrid.setMargin(5);
		listGrid.setSelectionType(SelectionStyle.SINGLE);

		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						dataSource.fetchData();
						listGrid.setDataSource(dataSource);
						listGrid.fetchData();
						// 库房编号
						ListGridField numberFiled = new ListGridField("stockRoomNumber","库房编号");
						// 库房中文名称
						ListGridField stockRoomChineseNameFiled = new ListGridField("stockRoomChineseName","库房中文名称");
						// 库房英文名称
						ListGridField stockRoomEngilshNameFiled = new ListGridField("stockRoomEngilshName","库房英文名称");
						// 库房地址
						ListGridField addressFiled = new ListGridField("address","库房地址");
						// 电话
						ListGridField teleFiled = new ListGridField("tele","电话");
						// 邮编
						ListGridField postCodeFiled = new ListGridField("postCode","邮编");
						// 仓库地区
						ListGridField stockRoomZoneCodeFiled = new ListGridField("stockRoomZoneCode","仓库地区");
						// 库房类型
						ListGridField stockRoomTypeFiled = new ListGridField("stockRoomType","库房类型");
						// 库房楼层
						ListGridField stockRoomFloorCodeFiled = new ListGridField("stockRoomFloor","库房楼层");
						// 备件中心位置
						ListGridField partCentreLocationFiled = new ListGridField("partCentreLocation","备件中心位置");
						// 备注
						ListGridField remarkFiled = new ListGridField("remark","备注");
						
						listGrid.setFields(numberFiled
								,stockRoomChineseNameFiled
								,stockRoomEngilshNameFiled
								,addressFiled
								,teleFiled
								,postCodeFiled
								,stockRoomZoneCodeFiled
								,stockRoomTypeFiled
								,stockRoomFloorCodeFiled
								,partCentreLocationFiled
								,remarkFiled
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
				for (final FormItem formItem : formItems)
				{
					// 库房ID
					if (formItem.getName().contains("stockRoomID"))
					{
						SC.say(record.getAttribute("id"));
						formItem.setValue(record.getAttribute("id"));
					}
					// 库房编号
					if (formItem.getName().contains("stockRoomNumber"))
					{
						formItem.setValue(record.getAttribute("stockRoomNumber"));
					}
					// 库房中文名称
					if (formItem.getName().contains("stockRoomChineseName"))
					{
						formItem.setValue(record.getAttribute("stockRoomChineseName"));
					}
					// 仓库地区
					if (formItem.getName().contains("stockRoomZoneCode"))
					{
						formItem.setValue(record.getAttribute("stockRoomZoneCode"));
					}
					// 库房类型
					if (formItem.getName().contains("stockRoomType"))
					{
						formItem.setValue(record.getAttribute("stockRoomType"));
					}
					// 库房楼层
					if (formItem.getName().contains("stockRoomFloor"))
					{
						formItem.setValue(record.getAttribute("stockRoomFloor"));
					}
					// 备件中心位置
					if (formItem.getName().contains("partCentreLocation"))
					{
						formItem.setValue(record.getAttribute("partCentreLocation"));
					}
//					// 库房区域
//					if (formItem.getName().contains("stockRoomArea"))
//					{
//						// 获取仓库数据
//						String stockRoomAreaModeName = "stockServiceBusiness.basicData.stockRoom";
//						String stockRoomAreaDSName = "stockArea_dataSource";
//						DataSourceTool stockRoomAreaDS = new DataSourceTool();
//						stockRoomAreaDS.onCreateDataSource(stockRoomAreaModeName, stockRoomAreaDSName,
//								new PostDataSourceInit() {
//									public void doPostOper(DataSource dataSource,
//											DataInfo dataInfo) {
//										SelectItem selStockRoomAreaCode = (SelectItem)formItem;
//										selStockRoomAreaCode.clearValue();
//										selStockRoomAreaCode.setOptionDataSource(dataSource);
//									}
//								});
//					}
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
