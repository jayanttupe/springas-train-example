package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.inStockApprovalRecord;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class InStockApprovalRecordButtonPanel extends BaseButtonToolStript {
	private InStockApprovalRecordListgrid inStockApprovalRecordListgrid;

	public InStockApprovalRecordButtonPanel(
			final InStockApprovalRecordListgrid inStockApprovalRecordListgrid) {
		super("stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.inStockApprovalRecord");
		this.inStockApprovalRecordListgrid = inStockApprovalRecordListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new InStockApprovalRecordDetailWindow("新增保税库入库记录管理", false, rect, inStockApprovalRecordListgrid, "showwindow/stock_add_01.png", false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (inStockApprovalRecordListgrid.getSelection().length == 1) {
				objWindow = new InStockApprovalRecordDetailWindow("修改保税库入库记录管理", false, rect, inStockApprovalRecordListgrid, "showwindow/stock_modify_01.png", true);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (inStockApprovalRecordListgrid.getSelection().length == 1) {
				objWindow = new InStockApprovalRecordViewWindow(rect, inStockApprovalRecordListgrid);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (inStockApprovalRecordListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					inStockApprovalRecordListgrid.removeSelectedData();
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}