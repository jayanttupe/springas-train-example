package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class PickingListButtonPanel extends BaseButtonToolStript {
	private PickingListListgrid pickingListListgrid;
	private PickingListPartItemsListgrid pickingListPartItemsListgrid;

	public PickingListButtonPanel(
			final PickingListListgrid pickingListListgrid,
			final PickingListPartItemsListgrid pickingListPartItemsListgrid) {
		super("stockServiceBusiness.outStockRoomBusiness.pickingList");
		this.pickingListListgrid = pickingListListgrid;
		this.pickingListPartItemsListgrid = pickingListPartItemsListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();

		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new PickingListDetailWindow("新增配料单", false, rect,
					pickingListListgrid, null, "", false, false, null,false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (pickingListListgrid.getSelection().length == 1) {
				objWindow = new PickingListDetailWindow("修改配料单", false, rect,
						pickingListListgrid, null, "", false, true, null,false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (pickingListListgrid.getSelection().length == 1) {
				objWindow = new PickingListDetailWindow("查看配料单", false, rect,
						pickingListListgrid, null, "", false, true, null,true);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		}
		else if ("delete".equalsIgnoreCase(buttonName)) {
			if (pickingListListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					pickingListListgrid.removeSelectedData();
					// 刷新配料明细的数据
					pickingListPartItemsListgrid
							.setData(new ListGridRecord[] {});
					pickingListPartItemsListgrid.fetchData(new Criteria(
							"pickingListID", pickingListListgrid
									.getSelectedRecord().getAttribute("id")
									.toString()));
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		} else if ("picking_instruct".equalsIgnoreCase(buttonName))
			if (pickingListListgrid.getSelection().length != 0) {
				Record record = new Record();
				String[] pickinglistIDs = new String[pickingListListgrid
						.getSelection().length];
				for (int i = 0; i < pickingListListgrid.getSelection().length; i++) {
					pickinglistIDs[i] = ""
							+ pickingListListgrid.getSelection()[i]
									.getAttribute("id").toString();
				}
				record.setAttribute("operatorType", "picking_instruct");
				record.setAttribute("pickingListID", pickinglistIDs);
				pickingListListgrid.updateData(record, new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say("下达成功！");
						// 刷新配料数据
						pickingListListgrid.fetchData(new Criteria("temp",
								String.valueOf(Math.random())));
						// 刷新配料明细的数据
						pickingListPartItemsListgrid
								.setData(new ListGridRecord[] {});
						pickingListPartItemsListgrid.fetchData(new Criteria(
								"pickingListID", pickingListListgrid
										.getSelectedRecord().getAttribute("id")
										.toString()));
					}
				});
				return;
			} else {
				SC.say("请选择一条记录进行下达！");
			}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}
