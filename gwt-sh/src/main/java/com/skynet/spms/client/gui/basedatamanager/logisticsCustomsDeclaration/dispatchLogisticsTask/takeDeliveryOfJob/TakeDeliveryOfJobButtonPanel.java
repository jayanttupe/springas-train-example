package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob;

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

public class TakeDeliveryOfJobButtonPanel extends BaseButtonToolStript {
	private TakeDeliveryOfJobListgrid takeDeliveryOfJobListgrid;

	public TakeDeliveryOfJobButtonPanel(
			final TakeDeliveryOfJobListgrid takeDeliveryOfJobListgrid) {
		super("logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob");
		this.takeDeliveryOfJobListgrid = takeDeliveryOfJobListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("editdelivery".equalsIgnoreCase(buttonName)) {
			if (takeDeliveryOfJobListgrid.getSelection().length == 1) {
				objWindow = new TakeDeliveryOfJobDetailWindow("编辑取货计划",
						false,
						rect,
						takeDeliveryOfJobListgrid,
						"showwindow/logistics_modify_01.png",
						true,
						false);
				ShowWindowTools.showWondow(rect, objWindow, -1);
			} else {
				SC.say("请选择一条记录进行编辑！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (takeDeliveryOfJobListgrid.getSelection().length == 1) {
				objWindow = new TakeDeliveryOfJobDetailWindow("查看取货计划",
						false,
						rect,
						takeDeliveryOfJobListgrid,
						"showwindow/logistics_modify_01.png",
						true,
						true);
				ShowWindowTools.showWondow(rect, objWindow, -1);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} else if ("finish_work".equalsIgnoreCase(buttonName)) {
			Record record = new Record();
			// 关闭工作处理
			record.setAttribute("setStatus", "takeDeliveryStatus");
			record.setAttribute("orderID", takeDeliveryOfJobListgrid.getSelectedRecord().getAttribute("orderID"));
			takeDeliveryOfJobListgrid.updateData(record, new DSCallback() {
				@Override
				public void execute(DSResponse response, Object rawData, DSRequest request) {
					SC.say("已经关闭当前工作！");

					Criteria criteria = new Criteria();
					criteria.addCriteria("temp", String.valueOf(Math.random()));
					criteria.addCriteria("workStatus", "2");
					takeDeliveryOfJobListgrid.fetchData(criteria);
				}
			});
		}
	}
}