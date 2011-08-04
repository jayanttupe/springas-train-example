/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseInventory;

import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.viewer.DetailViewer;

/**
 * @author Administrator
 *
 */
public class BondedWarehouseInventoryViewWindow extends Window {

	private DetailViewer detailView;

	public BondedWarehouseInventoryViewWindow(final Rectangle rect
			,BondedWarehouseInventoryListgrid bondedWarehouseInventoryListgrid) {

		final Window objWindow = this;
		setWidth(360);
		setTitle("查看保税库在库信息");
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
		detailView.setDataSource(bondedWarehouseInventoryListgrid.getDataSource());
		ListGridRecord[] record = bondedWarehouseInventoryListgrid.getSelection();

		detailView.setData(record);
		addItem(detailView);
	}

}
