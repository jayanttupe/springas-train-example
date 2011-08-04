package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.dispatchTask;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryTaskAssignListgrid;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.pickupOrder.PickupOrderDetailWindow;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.pickupOrder.PickupOrderListgrid;
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

public class PickupDispatchTaskButtonPanel extends BaseButtonToolStript {
	private PickupOrderListgrid pickupOrderListgrid;
	private PickupDeliveryTaskAssignListgrid pickupDeliveryTaskAssignListgrid;

	public PickupDispatchTaskButtonPanel(
			final PickupOrderListgrid pickupOrderListgrid,
			final PickupDeliveryTaskAssignListgrid pickupDeliveryTaskAssignListgrid) {
		super("logisticsCustomsDeclaration.dispatchLogisticsTask.pickupDispatchTask");
		this.pickupOrderListgrid = pickupOrderListgrid;
		this.pickupDeliveryTaskAssignListgrid = pickupDeliveryTaskAssignListgrid;
	}

	protected void showWindow(final String buttonName, final ToolStripButton objButton) {
		if ("finish_task".equalsIgnoreCase(buttonName)) {
			String strStatus = pickupOrderListgrid.getSelectedRecord().getAttribute("status");
			if (strStatus.equals("3")) {
				SC.say("当前任务已经关闭！");
				return;
			}
			
			Record record = new Record();
			// 关闭工作处理
			record.setAttribute("setStatus", "pickupStatus");
			record.setAttribute("orderID", pickupOrderListgrid.getSelectedRecord().getAttribute("id"));
			pickupOrderListgrid.updateData(record, new DSCallback() {
				@Override
				public void execute(DSResponse response, Object rawData, DSRequest request) {
					SC.say("当前任务关闭成功！");

					Criteria criteria = new Criteria();
					criteria.addCriteria("temp", String.valueOf(Math.random()));
					criteria.addCriteria("isPublish", "2");
					criteria.addCriteria("pickupDeliveryOrderType", "1");
					pickupOrderListgrid.fetchData(criteria);

					pickupDeliveryTaskAssignListgrid.setData(new ListGridRecord[]{});
				}
			});
		} else if ("modify".equalsIgnoreCase(buttonName) ||
				"view".equalsIgnoreCase(buttonName)) {
			if (pickupOrderListgrid.getSelection().length == 1) {
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
								objWindow = new PickupOrderDetailWindow("处理提货业务",
										true,
										rect,
										pickupOrderListgrid,
										pickupDeliveryTaskAssignListgrid,
										"showwindow/logistics_modify_01.png",
										true,
										selUser,
										true,
										false);
							} else {
								objWindow = new PickupOrderDetailWindow("查看提货业务",
										true,
										rect,
										pickupOrderListgrid,
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