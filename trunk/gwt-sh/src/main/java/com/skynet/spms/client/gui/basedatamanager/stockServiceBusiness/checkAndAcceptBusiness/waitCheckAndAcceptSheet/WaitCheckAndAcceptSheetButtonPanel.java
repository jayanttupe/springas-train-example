/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.waitCheckAndAcceptSheet;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet.CheckAndAcceptSheetDetailWindow;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetItemsListgrid;
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
public class WaitCheckAndAcceptSheetButtonPanel extends BaseButtonToolStript {
	private WaitCheckAndAcceptSheetListgrid waitCheckAndAcceptSheetListgrid;
	private ReceivingSheetItemsListgrid receivingSheetItemsListgrid;

	public WaitCheckAndAcceptSheetButtonPanel(
			final WaitCheckAndAcceptSheetListgrid waitCheckAndAcceptSheetListgrid,
			final ReceivingSheetItemsListgrid receivingSheetItemsListgrid) {
		super("stockServiceBusiness.checkAndAcceptBusiness.waitCheckAndAcceptSheet");
		this.waitCheckAndAcceptSheetListgrid = waitCheckAndAcceptSheetListgrid;
		this.receivingSheetItemsListgrid = receivingSheetItemsListgrid;
	}

	protected void showWindow(String buttonName, final ToolStripButton objButton) {		
		if ("CHECKING".equalsIgnoreCase(buttonName)) {
			if (waitCheckAndAcceptSheetListgrid.getSelection().length == 1) {
				// 构造一个收料DataSource		
				DataSourceTool headDST = new DataSourceTool();
				String modeName = "stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet";
				String dsName = "checkAndAcceptSheet_dataSource";
				headDST.onCreateDataSource(modeName, dsName,
						new PostDataSourceInit() {
							public void doPostOper(DataSource dataSource,
									DataInfo dataInfo) {
								Window objWindow = null;							
								Rectangle rect = objButton.getPageRect();
								objWindow = new CheckAndAcceptSheetDetailWindow("新建验收单", true,rect, waitCheckAndAcceptSheetListgrid, receivingSheetItemsListgrid, "showwindow/stock_add_01.png",true,true,dataSource,false);
								ShowWindowTools.showWondow(rect, objWindow, -1);
				    
		         	}
				});
			} else {
				SC.say("请选择一条收料单进行检验！");
			}
		}
	}
}