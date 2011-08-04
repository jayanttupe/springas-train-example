
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockCheckBusiness.stockCheck;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Administrator
 *
 */

public class StockCheckButtonPanel extends BaseButtonToolStript{

	private StockCheckListgrid stockCheckListgrid;
	
	public StockCheckButtonPanel(final StockCheckListgrid stockCheckListgrid,
			final StockCheckItemListgrid stockCheckItemListgrid){
		super("stockServiceBusiness.partsInventory.stockCheckBusiness.stockCheckManager");
		this.stockCheckListgrid = stockCheckListgrid;
	}
	
	protected void showWindow(String buttonName, ToolStripButton objButton) {
		
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new StockCheckDetailWindow("新增盘点记录", false, rect, stockCheckListgrid, "showwindow/stock_add_01.png", false, false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (stockCheckListgrid.getSelection().length == 1) {
				if(stockCheckListgrid.getSelectedRecord().getAttribute("state").equals("盘点中")){
					SC.say("盘点中不能修改");
					return;
					}
				objWindow = new StockCheckDetailWindow("修改盘点记录", false, rect, stockCheckListgrid, "showwindow/stock_modify_01.png", true, false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (stockCheckListgrid.getSelection().length == 1) {
				if(stockCheckListgrid.getSelectedRecord().getAttribute("state").equals("盘点中")){
					SC.say("盘点中不能修改");
					return;
					}
				objWindow = new StockCheckDetailWindow("修改盘点记录", false, rect, stockCheckListgrid, "showwindow/stock_modify_01.png", true, true);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (stockCheckListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					stockCheckListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}		
		 } else if("order_check".equalsIgnoreCase(buttonName)){
			 if (stockCheckListgrid.getSelection().length == 1) {
					ListGridRecord record = new ListGridRecord();
					record.setAttribute("type", "orderCheck");
					record.setAttribute("stockCheckID", stockCheckListgrid.getSelectedRecord().getAttribute("id"));
					stockCheckListgrid.updateData(record, new DSCallback() {
						@Override
						public void execute(DSResponse response, Object rawData, DSRequest request) {
							SC.say("下达成功！");
							Criteria criteria = new Criteria();
							criteria.addCriteria("temp", String.valueOf(Math.random()));
							stockCheckListgrid.fetchData(criteria);
						}
					});
			} else {
				SC.say("请选择一条记录下达指令！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}
