
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveOut;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Administrator
 *
 */

public class StockMoveOutRecordButtonPanel extends BaseButtonToolStript{

	private StockMoveOutRecordListgrid stockMoveOutRecordListgrid;
	private StockMoveOutRecordItemListgrid stockMoveOutRecordItemListgrid;
	
	public StockMoveOutRecordButtonPanel(final StockMoveOutRecordListgrid stockMoveOutRecordListgrid,final StockMoveOutRecordItemListgrid
			 stockMoveOutRecordItemListgrid){
		super("stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveOutRecord");
		this.stockMoveOutRecordListgrid = stockMoveOutRecordListgrid;
		this.stockMoveOutRecordItemListgrid = stockMoveOutRecordItemListgrid;
	}
	
	protected void showWindow(String buttonName, ToolStripButton objButton) {
		
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		 if ("modify".equalsIgnoreCase(buttonName)) {
			if (stockMoveOutRecordListgrid.getSelection().length == 1) {
				objWindow = new StockMoveOutRecordDetailWindow("修改移出库记录",false,rect, stockMoveOutRecordListgrid,"showwindow/stock_modify_01.png",true);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (stockMoveOutRecordListgrid.getSelection().length == 1) {
				//objWindow = new CredentialsViewWindow(rect, stockMoveRecordListgrid);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (stockMoveOutRecordListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					stockMoveOutRecordListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}		
		 } else if("order".equalsIgnoreCase(buttonName)){
			 if (stockMoveOutRecordListgrid.getSelection().length == 1) {
				 ListGridRecord rdd = stockMoveOutRecordListgrid.getSelectedRecord();
				 if(rdd.getAttribute("state").equals("已移入")){
						SC.say("已经移入库！");
						return;
					}
				 if(rdd.getAttribute("state").equals("移库中")){
						SC.say("已在移库中！");
						return;
					}
				 rdd.setAttribute("type", "order");
				 rdd.setAttribute("state", "移库中");
				 stockMoveOutRecordListgrid.updateData(rdd);
				} else {
					SC.say("请选择一条记录下达指令！");
				}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}
