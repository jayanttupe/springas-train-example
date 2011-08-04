package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.partsInventory.freezeRecord;

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

public class FreezeRecordButtonPanel extends BaseButtonToolStript {
	private FreezeRecordListgrid freezeRecordListgrid;

	public FreezeRecordButtonPanel(
		final FreezeRecordListgrid freezeRecordListgrid) {
		super("stockServiceBusiness.partsInventory.freezeRecord");
		this.freezeRecordListgrid = freezeRecordListgrid;
		}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("freeze".equalsIgnoreCase(buttonName)) {
			if (freezeRecordListgrid.getSelection().length == 1) {
				objWindow = new FreezeRecordDetailWindow("编辑备件冻结信息", false, rect, freezeRecordListgrid, "showwindow/stock_modify_01.png", true);
			} else {
				SC.say("请选择一条记录进行冻结！");
			}
		} else if ("defrost".equalsIgnoreCase(buttonName)) {
			if (freezeRecordListgrid.getSelection().length == 1) {
				if (freezeRecordListgrid.getSelectedRecord().getAttribute("partsInventoryRecord.isFreeze").equals("0")) {
					SC.say("当前库存记录没有被冻结过！");
					return;
				}
				
				ListGridRecord record = new ListGridRecord();
				// 设置操作类型为下达指令
				record.setAttribute("type", "release");
				record.setAttribute("freezeRecordID", freezeRecordListgrid.getSelectedRecord().getAttribute("id"));
				record.setAttribute("partsInventoryID", freezeRecordListgrid.getSelectedRecord().getAttribute("partsInventoryID"));
				freezeRecordListgrid.updateData(record, new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("解除成功！");
						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						freezeRecordListgrid.fetchData(criteria);
					}
				});
			} else {
				SC.say("请选择一条记录进行解除！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}