package com.skynet.spms.client.gui.commonOrder.delivery.business;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.i18n.CommonOrderModuleConst;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;

public class DeliveryOrderBusiness extends BaseBusiness{
	
	private static I18n ui = new I18n();
	
	CommonOrderModuleConst constui=GWT.create(CommonOrderModuleConst.class);

	/**
	 * 发布
	 * 
	 * @param grid
	 */
	@Override
	public void publishSheet(final ListGrid grid) {
		if (ValidateUtil.isRecordSelected(grid)) {
			final Record target = grid.getSelectedRecord();// 获得选中的数据
			String state = target.getAttribute("isPublish");
			if (state.equals("1")) {
				SC.say(ui.getB().msgHasPublished());
				return;
			}
			target.setAttribute("isPublish", "1");// 设置发布状态为 已状态
			grid.updateData(target, new DSCallback() {
				public void execute(DSResponse response, Object rawData,
						DSRequest request) {
					SC.say(ui.getB().msgPulishSuccess());
					grid.selectRecord(target);

				}

			});

		}
	}

	/**
	 * 取消发布
	 * 
	 * @param grid
	 */
	@Override
	public void cancelPublishSheet(final ListGrid grid) {
		if (ValidateUtil.isRecordSelected(grid)) {
			final Record target = grid.getSelectedRecord();// 获得选中的数据
			String state = target.getAttribute("isPublish");
			if (state.equals("0")) {
				SC.warn(ui.getRepairModule().msg_publish_before_update());
				return;
			}
			SC.ask(constui.msg_make_sure_publish(), new BooleanCallback() {
				@Override
				public void execute(Boolean value) {
					if (value) {
						target.setAttribute("isPublish", "0");// 设置状态
						grid.updateData(target, new DSCallback() {
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {
								SC.say(ui.getRepairModule().msgAddOpSuccess());
								grid.selectRecord(target);
							}
						});

					}
				}
			});
		}
	}

}
