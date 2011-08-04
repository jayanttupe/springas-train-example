package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningListgrid;
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

public class BookingJobButtonPanel extends BaseButtonToolStript {
	private BookingJobListgrid bookingJobListgrid;
	private PickupDeliveryVanningListgrid pickupDeliveryVanningListgrid;

	public BookingJobButtonPanel(
			final BookingJobListgrid bookingJobListgrid,
			final PickupDeliveryVanningListgrid pickupDeliveryVanningListgrid) {
		super("logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob");
		this.bookingJobListgrid = bookingJobListgrid;
		this.pickupDeliveryVanningListgrid = pickupDeliveryVanningListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("edit_freight_attorney".equalsIgnoreCase(buttonName)) {
			if (bookingJobListgrid.getSelection().length == 1) {
				objWindow = new BookingJobDetailWindow("编辑委托书",
						true,
						rect,
						bookingJobListgrid,
						"showwindow/logistics_modify_01.png",
						true,
						false);
				ShowWindowTools.showWondow(rect, objWindow, -1);
			} else {
				SC.say("请选择一条记录进行编辑！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (bookingJobListgrid.getSelection().length == 1) {
				objWindow = new BookingJobDetailWindow("查看委托书",
						true,
						rect,
						bookingJobListgrid,
						"showwindow/logistics_modify_01.png",
						true,
						true);
				ShowWindowTools.showWondow(rect, objWindow, -1);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		} else if ("finish_work".equalsIgnoreCase(buttonName)) {
			if (bookingJobListgrid.getSelection().length == 1) {
				Record record = new Record();
				// 关闭工作处理
				record.setAttribute("setStatus", "bookingStatus");
				record.setAttribute("orderID", bookingJobListgrid.getSelectedRecord().getAttribute("orderID"));
				bookingJobListgrid.updateData(record, new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("已经关闭当前工作！");

						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						criteria.addCriteria("workStatus", "2");
						bookingJobListgrid.fetchData(criteria);

						pickupDeliveryVanningListgrid.setData(new ListGridRecord[]{});
					}
				});
			} else {
				SC.say("请选择一条记录进行处理！");
			}
		}
	}
}