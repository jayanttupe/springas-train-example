package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.deliveryOrder;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningListgrid;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.pickupOrder.PickupOrderDetailWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class DeliveryOrderButtonPanel extends BaseButtonToolStript {
	private DeliveryOrderListgrid deliveryOrderListgrid;
	private PickupDeliveryVanningListgrid pickupDeliveryVanningListgrid;

	public DeliveryOrderButtonPanel(
			final DeliveryOrderListgrid pickupDeliveryOrderListgrid,
			final PickupDeliveryVanningListgrid pickupDeliveryVanningListgrid) {
		super("logisticsCustomsDeclaration.pickupDeliveryBusiness.deliveryOrder");
		this.deliveryOrderListgrid = pickupDeliveryOrderListgrid;
		this.pickupDeliveryVanningListgrid = pickupDeliveryVanningListgrid;
	}

	protected void showWindow(String buttonName, final ToolStripButton objButton) {
		
		if ("delivery_order".equalsIgnoreCase(buttonName)) {
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
							Window objWindow = new DeliveryOrderDetailWindow("处理发货业务",
									true,
									rect,
									deliveryOrderListgrid,
									pickupDeliveryVanningListgrid,
									"showwindow/logistics_modify_01.png",
									true,
									selUser,
									false,
									false);
							ShowWindowTools.showWondow(rect, objWindow, -1);
						}
					});
			} else {
				SC.say("请选择一条记录进行处理！");
			}
		}
	}
}
