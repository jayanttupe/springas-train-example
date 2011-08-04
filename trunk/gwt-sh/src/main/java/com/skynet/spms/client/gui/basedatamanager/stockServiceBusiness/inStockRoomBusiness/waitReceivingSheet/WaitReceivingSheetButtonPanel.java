/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.waitReceivingSheet;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetDetailWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Administrator
 *
 */
public class WaitReceivingSheetButtonPanel extends BaseButtonToolStript {
	private WaitReceivingSheetListgrid waitReceivingSheetListgrid;
	private WaitReceivingSheetItemsListgrid waitReceivingSheetItemsListgrid;

	public WaitReceivingSheetButtonPanel(
			final WaitReceivingSheetListgrid waitReceivingSheetListgrid,
			final WaitReceivingSheetItemsListgrid waitReceivingSheetItemsListgrid) {
		super("stockServiceBusiness.inStockRoomBusiness.waitReceivingSheet");
		this.waitReceivingSheetListgrid = waitReceivingSheetListgrid;
		this.waitReceivingSheetItemsListgrid = waitReceivingSheetItemsListgrid;
	}

	protected void showWindow(String buttonName, final ToolStripButton objButton) {

		if ("rceiving".equalsIgnoreCase(buttonName)) {
			if(waitReceivingSheetListgrid.getSelection().length == 1){
				// 构造一个收料DataSource
				DataSourceTool headDST = new DataSourceTool();
				String headModeName = "stockServiceBusiness.inStockRoomBusiness.receivingSheet";
				String headDSName = "receivingSheet_dataSource";
				headDST.onCreateDataSource(headModeName, headDSName,
					new PostDataSourceInit() {
						public void doPostOper(DataSource dataSource,
								DataInfo dataInfo) {
							Window objWindow = null;
							Rectangle rect = objButton.getPageRect();
							objWindow = new ReceivingSheetDetailWindow("新增收料记录",
									rect,
									waitReceivingSheetListgrid,
									waitReceivingSheetItemsListgrid,
									true,
									true,
									dataSource,
									"showwindow/stock_add_01.png",false);
							ShowWindowTools.showWondow(rect, objWindow, -1);
						}
					}
				);
			}else {
				SC.say("请选择一条记录进行收料！");
			}
		}
	}
}
