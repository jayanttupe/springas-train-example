package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.stockTemperatureAndHumidity;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class StockTemperatureAndHumidityRecordAddWindow extends Window {

	public StockTemperatureAndHumidityRecordAddWindow(String windowTitle, 
												boolean isMax,
												final Rectangle rect, 
												final StockTemperatureAndHumidityRecordListGrid listGrid, 
												String iconUrl, 
												Boolean updateFlg) {
		
		final Window objWindow = this;
		setWidth(600);
		setHeight(300);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setPadding(5);
		mainForm.setWidth(400);
		mainForm.setHeight(240);
		mainForm.setLayoutAlign(Alignment.LEFT);
		mainForm.setLayoutAlign(VerticalAlignment.TOP);

		// 获取数据源
		String modeName = "stockServiceBusiness.stockManageTool.stockTemperatureAndHumidityRecord";
		String dsName = "stockTemperatureAndHumidityRecord_dataSource";
		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						mainForm.setDataSource(dataSource);
						
						TextItem txtMeasurePoinTlocation = new TextItem("measurePoinTlocation", "测量点位");
						// 记录结束日期
						DateItem txtRecordEndDate = new DateItem("recordEndDate");
						txtRecordEndDate.setUseTextField(true);
						TextItem txtRecorderNumber = new TextItem("recorderNumber", "记录仪编号");
						// 记录开始日期
						DateItem txtRecordStartDate = new DateItem("recordStartDate");
						txtRecordStartDate.setUseTextField(true);
						TextItem txtStockTemperatureAndHumidityRecordNumber = new TextItem("stockTemperatureAndHumidityRecordNumber", "仓库温湿度记录编号");
						TextItem txtStockRoomNumber = new TextItem("stockRoomNumber", "库房编号");
						TextItem txtRemark = new TextItem("remark", "备注");
						txtRemark.setWidth(280);
						TextItem txtItemNumber = new TextItem("itemNumber", "附件项号");
																    					    
						mainForm.setFields(txtMeasurePoinTlocation
								,txtRecordEndDate
								,txtRecorderNumber
								,txtRecordStartDate
								,txtStockTemperatureAndHumidityRecordNumber
								,txtStockRoomNumber
								,txtRemark
								,txtItemNumber
								);
						  }
					});
		
		IButton backButton = new IButton();
		backButton.setTitle("返回");
		backButton.setWidth(65);
		backButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});
		
		IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainForm.saveData(new DSCallback() {
				@Override
				public void execute(DSResponse response, Object rawData, DSRequest request) {
					listGrid.fetchData();
					ShowWindowTools.showCloseWindow(rect, objWindow, -1);
					}
				});
			}
		});
		
		final BtnsHLayout hLayout = new BtnsHLayout();
		hLayout.addMember(saveButton);
		hLayout.addMember(backButton);
		hLayout.setHeight(10);

		VLayout vLayout = new VLayout();
		vLayout.setMargin(5);
		vLayout.addMember(mainForm);
		vLayout.addMember(hLayout);
		
		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,vLayout);	
	}
}
