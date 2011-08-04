package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.packingList;

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

public class PackingListButtonPanel extends BaseButtonToolStript {
	private PackingListListgrid packingListListgrid;
	private PackingListPartItemsListgrid packingListPartItemsListgrid;

	public PackingListButtonPanel(
			final PackingListListgrid packingListListgrid,
			final PackingListPartItemsListgrid packingListPartItemsListgrid) {
		super("stockServiceBusiness.outStockRoomBusiness.packingList");
		this.packingListListgrid = packingListListgrid;
		this.packingListPartItemsListgrid = packingListPartItemsListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();

		if ("add".equalsIgnoreCase(buttonName)) {
			objWindow = new PackingListDetailWindow("新增装箱管理", false, rect,
					packingListListgrid, null, "showwindow/stock_add_01.png",
					false, false, null,false);
		} else if ("modify".equalsIgnoreCase(buttonName)) {
			if (packingListListgrid.getSelection().length == 1) {
				objWindow = new PackingListDetailWindow("修改装箱管理", false, rect,
						packingListListgrid, null,
						"showwindow/stock_modify_01.png", false, true, null,false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (packingListListgrid.getSelection().length == 1) {
				objWindow = new PackingListDetailWindow("查看装箱管理", false, rect,
						packingListListgrid, null,
						"showwindow/stock_modify_01.png", false, true, null,true);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} else if ("delete".equalsIgnoreCase(buttonName)) {
			if (packingListListgrid.getSelection().length != 0) {
				boolean isDel = com.google.gwt.user.client.Window
						.confirm("是否要删除选中的记录！");
				if (isDel) {
					packingListListgrid.removeSelectedData();
					// 刷新配料明细的数据
					packingListPartItemsListgrid
							.setData(new ListGridRecord[] {});
					packingListPartItemsListgrid.fetchData(new Criteria(
							"packingListID", packingListListgrid
									.getSelectedRecord().getAttribute("id")
									.toString()));
					return;
				}
			} else {
				SC.say("请选择一条记录进行删除！");
			}
		} else if ("packing_release".equalsIgnoreCase(buttonName)) {
			if (packingListListgrid.getSelection().length != 0) {
				Record record = new Record();
				String[] packinglistIDs = new String[packingListListgrid
						.getSelection().length];
				for (int i = 0; i < packingListListgrid.getSelection().length; i++) {
					packinglistIDs[i] = ""
							+ packingListListgrid.getSelection()[i]
									.getAttribute("id").toString();
				}
				record.setAttribute("operatorType", "packing_release");
				record.setAttribute("packingListID", packinglistIDs);
				packingListListgrid.updateData(record, new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						SC.say("合格放行成功！");

						Criteria criteria = new Criteria();
						criteria.addCriteria("temp",
								String.valueOf(Math.random()));
						criteria.addCriteria("type", "notRelease");
						// 刷新装箱单数据
						packingListListgrid.fetchData(criteria);

						// 刷新装箱单明细的数据
						packingListPartItemsListgrid
								.setData(new ListGridRecord[] {});
					}
				});
				return;
			} else {
				SC.say("请选择一条记录进行合格放行！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}