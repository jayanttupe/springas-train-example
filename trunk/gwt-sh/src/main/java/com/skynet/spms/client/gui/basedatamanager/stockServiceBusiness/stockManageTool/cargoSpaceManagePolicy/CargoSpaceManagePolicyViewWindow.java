package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.cargoSpaceManagePolicy;

import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class CargoSpaceManagePolicyViewWindow extends Window {

	private DetailViewer detailView;

	public CargoSpaceManagePolicyViewWindow(final Rectangle rect
			,CargoSpaceManagePolicyListgrid cargoSpaceManagePolicyListgrid) {

		final Window objWindow = this;
		setWidth(360);
		setTitle("查看货位管理策略");
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
		detailView.setDataSource(cargoSpaceManagePolicyListgrid.getDataSource());
		ListGridRecord[] record = cargoSpaceManagePolicyListgrid.getSelection();

		detailView.setData(record);
		addItem(detailView);
	}
}