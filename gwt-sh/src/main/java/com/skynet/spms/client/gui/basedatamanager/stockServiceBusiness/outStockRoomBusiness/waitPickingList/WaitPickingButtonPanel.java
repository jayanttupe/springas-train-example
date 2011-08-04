/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.waitPickingList;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingListDetailWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Administrator
 *
 */
public class WaitPickingButtonPanel extends BaseButtonToolStript {
	private WaitPickingListgrid waitPickingListgrid;
	private WaitPickingItemsListgrid waitPickingItemsListgrid;

	public WaitPickingButtonPanel(
			final WaitPickingListgrid waitPickingListgrid,
			final WaitPickingItemsListgrid waitPickingItemsListgrid) {
		super("stockServiceBusiness.outStockRoomBusiness.waitDeliveryOrder");
		this.waitPickingListgrid = waitPickingListgrid;
		this.waitPickingItemsListgrid = waitPickingItemsListgrid;
	}

	protected void showWindow(String buttonName, final ToolStripButton objButton) {
		
		if ("picking_list".equalsIgnoreCase(buttonName)) {
			// 构造一个收料DataSource
			DataSourceTool headDST = new DataSourceTool();
			String headModeName = "stockServiceBusiness.outStockRoomBusiness.pickingList";
			String headDSName = "pickingList_dataSource";
			headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						Window objWindow = null;
						Rectangle rect = objButton.getPageRect();
						
						objWindow = new PickingListDetailWindow("新增配料单"
								, false
								, rect
								, waitPickingListgrid
								, waitPickingItemsListgrid
								, "showwindow/stock_add_01.png"
								, true
								, false
								, dataSource
								,false);
						ShowWindowTools.showWondow(rect, objWindow, -1);
					}
				});
		}
	}
}
