package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingRecord;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.packingList.PackingListDetailWindow;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingListDetailWindow;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingListListgrid;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingListPartItemsListgrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class PickingRecordButtonPanel extends BaseButtonToolStript {
	private PickingListListgrid pickingListListgrid;
	private PickingListPartItemsListgrid pickingListPartItemsListgrid;

	public PickingRecordButtonPanel(
			final PickingListListgrid pickingListListgrid
			,final PickingListPartItemsListgrid pickingListPartItemsListgrid) {
		super("stockServiceBusiness.outStockRoomBusiness.pickingRecord");
		this.pickingListListgrid = pickingListListgrid;
		this.pickingListPartItemsListgrid = pickingListPartItemsListgrid;
	}

	protected void showWindow(String buttonName, final ToolStripButton objButton) {
		if ("add_packinglist".equalsIgnoreCase(buttonName))
		{
			if (pickingListListgrid.getSelection().length != 0) 
			{
				// 构造一个DataSource
				DataSourceTool headDST = new DataSourceTool();
				String headModeName = "stockServiceBusiness.outStockRoomBusiness.packingList";
				String headDSName = "packingList_dataSource";
				headDST.onCreateDataSource(headModeName, headDSName,
					new PostDataSourceInit() {
						public void doPostOper(DataSource dataSource,
								DataInfo dataInfo) {
							Window objWindow = null;
							Rectangle rect = objButton.getPageRect();
							
							objWindow = new PackingListDetailWindow("新增装箱管理"
									, false
									, rect
									, pickingListListgrid
									, pickingListPartItemsListgrid
									, "showwindow/stock_add_01.png"
									, true
									, false
									, dataSource
									,false);
							ShowWindowTools.showWondow(rect, objWindow, -1);
						}
					});
			} else {
				SC.say("请选择一条记录进行装箱！");
			}
		}
	}
}
