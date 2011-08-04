
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveApply;

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

public class StockMoveApplyButtonPanel extends BaseButtonToolStript{

	private StockMoveApplyListgrid stockMoveOutRecordListgrid;
	private StockMoveApplyItemListgrid stockMoveOutRecordItemListgrid;
	
	public StockMoveApplyButtonPanel(final StockMoveApplyListgrid stockMoveOutRecordListgrid,final StockMoveApplyItemListgrid
			 stockMoveOutRecordItemListgrid){
		super("stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveApply");
		this.stockMoveOutRecordListgrid = stockMoveOutRecordListgrid;
		this.stockMoveOutRecordItemListgrid = stockMoveOutRecordItemListgrid;
	}
	
	protected void showWindow(String buttonName, ToolStripButton objButton) {
		
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();		
		
		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new StockMoveApplyDetailWindow("新增申请移库记录",false,rect, stockMoveOutRecordListgrid,"showwindow/stock_add_01.png",false,false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (stockMoveOutRecordListgrid.getSelection().length == 1) {
				objWindow = new StockMoveApplyDetailWindow("修改申请移库记录",false,rect, stockMoveOutRecordListgrid,"showwindow/stock_modify_01.png",true,false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (stockMoveOutRecordListgrid.getSelection().length == 1) {
				objWindow = new StockMoveApplyDetailWindow("查看申请移库记录",false,rect, stockMoveOutRecordListgrid,"showwindow/stock_modify_01.png",true,true);
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
		 } else if("apply".equalsIgnoreCase(buttonName)){
			 if (stockMoveOutRecordListgrid.getSelection().length == 1) {
				 ListGridRecord rdd = stockMoveOutRecordListgrid.getSelectedRecord();
				 rdd.setAttribute("type", "apply");
				 rdd.setAttribute("state", "审批中");
				 stockMoveOutRecordListgrid.updateData(rdd);
				} else {
					SC.say("请选择一条记录送审！");
				}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}
