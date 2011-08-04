package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.stockTemperatureAndHumidity;

import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class StockTemperatureAndHumidityRecordViewWindow extends Window{

	private DetailViewer detailView;
	
	public StockTemperatureAndHumidityRecordViewWindow(final Rectangle rect
			,StockTemperatureAndHumidityRecordListGrid stockTemperatureAndHumidityRecordListGrid) {
		setWidth(360);
		setTitle("查看库房温湿度信息");
		setShowMinimizeButton(false);
		setIsModal(true);
		setHeight(310);
		setShowModalMask(true);
		centerInPage();
		addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClientEvent event) {
				destroy();
			}
		});

		detailView = new DetailViewer();
		detailView.setDataSource(stockTemperatureAndHumidityRecordListGrid.getDataSource());
		ListGridRecord[] record = stockTemperatureAndHumidityRecordListGrid.getSelection();
		ListGridRecord[] showList = new ListGridRecord[record.length];

		detailView.setData(record);
		addItem(detailView);
	}
}
