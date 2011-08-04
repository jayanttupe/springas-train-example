package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.picking;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingListListgrid;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList.PickingListPartItemsListgrid;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class PickingButtonPanel extends BaseButtonToolStript {
	private PickingListListgrid pickingListListgrid;
	private PickingListPartItemsListgrid pickingListPartItemsListgrid;

	public PickingButtonPanel(
			final PickingListListgrid pickingListListgrid
			,final PickingListPartItemsListgrid pickingListPartItemsListgrid) {
		super("stockServiceBusiness.outStockRoomBusiness.picking");
		this.pickingListListgrid = pickingListListgrid;
		this.pickingListPartItemsListgrid = pickingListPartItemsListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		if ("picking".equalsIgnoreCase(buttonName))
		{
			if (pickingListListgrid.getSelection().length != 0) {
				Record record = new Record();
				String[] pickinglistIDs = new String[pickingListListgrid.getSelection().length];
				for (int i = 0; i < pickingListListgrid.getSelection().length; i++)
				{
					pickinglistIDs[i] = "" + pickingListListgrid.getSelection()[i].getAttribute("id").toString();
				}
				record.setAttribute("operatorType", "picking");
				record.setAttribute("pickingListID", pickinglistIDs);
				pickingListListgrid.updateData(record, new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("拣货成功！");
						// 刷新配料数据
						pickingListListgrid.fetchData(new Criteria("temp", String.valueOf(Math.random())));
						// 刷新配料明细的数据
						pickingListPartItemsListgrid.setData(new ListGridRecord[]{});
						pickingListPartItemsListgrid.fetchData(new Criteria("pickingListID", pickingListListgrid.getSelectedRecord().getAttribute("id").toString()));
					}
				});
				return;
			} else {
				SC.say("请选择一条记录进行拣货！");
			}
		}
	}
}
