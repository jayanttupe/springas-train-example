package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.nonconformingReport;

import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class NonconformingReportViewWindow extends Window {

	private DetailViewer detailView;

	public NonconformingReportViewWindow(final Rectangle rect
			,NonconformingReportListgrid nonconformingReportListgrid) {
		final Window objWindow = this;
		setWidth(360);

		setTitle("查看列表");
		setShowMinimizeButton(false);
		setIsModal(true);
		setHeight(310);
		setShowModalMask(true);
		centerInPage();
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		detailView = new DetailViewer();
		detailView.setDataSource(nonconformingReportListgrid.getDataSource());
		ListGridRecord[] record = nonconformingReportListgrid.getSelection();

		detailView.setData(record);
		addItem(detailView);

	}

}
