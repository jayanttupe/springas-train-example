package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.dispatchTask;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryTaskAssignListgrid;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.deliveryOrder.DeliveryOrderDetailWindow;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.deliveryOrder.DeliveryOrderListgrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class DeliveryDispatchTaskButtonPanel extends BaseButtonToolStript {
	private DeliveryOrderListgrid deliveryOrderListgrid;
	private PickupDeliveryTaskAssignListgrid pickupDeliveryTaskAssignListgrid;

	public DeliveryDispatchTaskButtonPanel(
			final DeliveryOrderListgrid deliveryOrderListgrid,
			final PickupDeliveryTaskAssignListgrid pickupDeliveryTaskAssignListgrid) {
		super("logisticsCustomsDeclaration.dispatchLogisticsTask.deliveryDispatchTask");
		this.deliveryOrderListgrid = deliveryOrderListgrid;
		this.pickupDeliveryTaskAssignListgrid = pickupDeliveryTaskAssignListgrid;
	}

	protected void showWindow(final String buttonName, final ToolStripButton objButton) {
		if ("finish_task".equalsIgnoreCase(buttonName)) {
			String strStatus = deliveryOrderListgrid.getSelectedRecord().getAttribute("status");
			if (strStatus.equals("3")) {
				SC.say("当前任务已经关闭！");
				return;
			}
			
			Record record = new Record();
			// 关闭工作处理
			record.setAttribute("setStatus", "deliveryStatus");
			record.setAttribute("orderID", deliveryOrderListgrid.getSelectedRecord().getAttribute("id"));
			deliveryOrderListgrid.updateData(record, new DSCallback() {
				@Override
				public void execute(DSResponse response, Object rawData, DSRequest request) {
					SC.say("当前任务关闭成功！");

					Criteria criteria = new Criteria();
					criteria.addCriteria("temp", String.valueOf(Math.random()));
					criteria.addCriteria("isPublish", "2");
					criteria.addCriteria("pickupDeliveryOrderType", "2");
					deliveryOrderListgrid.fetchData(criteria);

					pickupDeliveryTaskAssignListgrid.setData(new ListGridRecord[]{});
				}
			});
		} else if ("modify".equalsIgnoreCase(buttonName) ||
				"view".equalsIgnoreCase(buttonName)) {
				if (deliveryOrderListgrid.getSelection().length == 1) {
					// 用户选择列表
					final SelectItem selUser = new SelectItem("worker");
					selUser.setShowTitle(false);
					selUser.setAllowEmptyValue(true);
					// 获取用户数据
					String userModeName = "organization.userinfo";
					String userDSName = "user_dataSource";
					DataSourceTool userDST = new DataSourceTool();
					userDST.onCreateDataSource(userModeName, userDSName,
						new PostDataSourceInit() {
							public void doPostOper(DataSource dataSource,
									DataInfo dataInfo) {
								selUser.setOptionDataSource(dataSource);

								selUser.setValueField("username");
								selUser.setDisplayField("username");
								ListGridField usernameField = new ListGridField("username");
								ListGridField realnameField = new ListGridField("realname");
								selUser.setPickListFields(usernameField, realnameField);

								final Rectangle rect = objButton.getPageRect();
								Window objWindow = null;
								if ("modify".equalsIgnoreCase(buttonName)) {
									objWindow = new DeliveryOrderDetailWindow("处理发货业务",
											true,
											rect,
											deliveryOrderListgrid,
											pickupDeliveryTaskAssignListgrid,
											"showwindow/logistics_modify_01.png",
											true,
											selUser,
											true,
											false);
								} else {
									objWindow = new DeliveryOrderDetailWindow("查看发货业务",
											true,
											rect,
											deliveryOrderListgrid,
											pickupDeliveryTaskAssignListgrid,
											"showwindow/logistics_modify_01.png",
											true,
											selUser,
											true,
											true);
								}
								ShowWindowTools.showWondow(rect, objWindow, -1);
							}
						});
				} else {
					SC.say("请选择一条记录进行修改！");
				}
			}
	}
}