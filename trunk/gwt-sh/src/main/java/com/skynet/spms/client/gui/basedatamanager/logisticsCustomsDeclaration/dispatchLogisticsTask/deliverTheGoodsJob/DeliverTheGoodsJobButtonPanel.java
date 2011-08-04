package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob.BookingJobDetailWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class DeliverTheGoodsJobButtonPanel extends BaseButtonToolStript {
	private DeliverTheGoodsJobListgrid deliverTheGoodsJobListgrid;

	public DeliverTheGoodsJobButtonPanel(
			final DeliverTheGoodsJobListgrid deliverTheGoodsJobListgrid) {
		super("logisticsCustomsDeclaration.dispatchLogisticsTask.deliverTheGoodsJob");
		this.deliverTheGoodsJobListgrid = deliverTheGoodsJobListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("edittake".equalsIgnoreCase(buttonName)) {
			if (deliverTheGoodsJobListgrid.getSelection().length == 1) {
				objWindow = new DeliverTheGoodsJobDetailWindow("编辑交货计划",
						false,
						rect,
						deliverTheGoodsJobListgrid,
						"showwindow/logistics_modify_01.png",
						true,
						false);
				ShowWindowTools.showWondow(rect, objWindow, -1);
			} else {
				SC.say("请选择一条记录进行编辑！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (deliverTheGoodsJobListgrid.getSelection().length == 1) {
				objWindow = new DeliverTheGoodsJobDetailWindow("查看交货计划",
						false,
						rect,
						deliverTheGoodsJobListgrid,
						"showwindow/logistics_modify_01.png",
						true,
						true);
				ShowWindowTools.showWondow(rect, objWindow, -1);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} else if ("finish_work".equalsIgnoreCase(buttonName)) {
			if (deliverTheGoodsJobListgrid.getSelection().length == 1) {
				Record record = new Record();
				// 关闭工作处理
				record.setAttribute("setStatus", "deliverStatus");
				record.setAttribute("orderID", deliverTheGoodsJobListgrid.getSelectedRecord().getAttribute("orderID"));
				deliverTheGoodsJobListgrid.updateData(record, new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("已经关闭当前工作！");

						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						criteria.addCriteria("workStatus", "2");
						deliverTheGoodsJobListgrid.fetchData(criteria);
					}
				});
			} else {
				SC.say("请选择一条记录进行处理！");
			}
		}
	}
}