package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheetHistory;

import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class ReceivingSheetHistoryViewWindow extends Window {

	private DetailViewer detailView;

	public ReceivingSheetHistoryViewWindow(final Rectangle rect,
			ReceivingSheetHistoryListgrid receivingSheetHistoryListgrid) {

		final Window objWindow = this;
		setWidth(360);
		setTitle("历史收料单查询");
		setShowMinimizeButton(false);
		setIsModal(true);
		setHeight(310);
		setShowModalMask(true);
		centerInPage();
		addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		detailView = new DetailViewer();
		detailView.setDataSource(receivingSheetHistoryListgrid.getDataSource());
		ListGridRecord[] record = receivingSheetHistoryListgrid.getSelection();

		detailView.setData(record);
		addItem(detailView);
	}
}