package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.pickupOrder;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningListgrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.UserTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class PickupOrderButtonPanel extends BaseButtonToolStript {
	private PickupOrderListgrid pickupOrderListgrid;
	private PickupDeliveryVanningListgrid pickupDeliveryVanningListgrid;

	public PickupOrderButtonPanel(
			final PickupOrderListgrid pickupDeliveryOrderListgrid,
			final PickupDeliveryVanningListgrid pickupDeliveryVanningListgrid) {
		super("logisticsCustomsDeclaration.pickupDeliveryBusiness.pickupOrder");
		this.pickupOrderListgrid = pickupDeliveryOrderListgrid;
		this.pickupDeliveryVanningListgrid = pickupDeliveryVanningListgrid;
	}

	protected void showWindow(String buttonName, final ToolStripButton objButton) {
		if ("pickup_order".equalsIgnoreCase(buttonName)) {
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
							Window objWindow = new PickupOrderDetailWindow("处理提货业务",
									true,
									rect,
									pickupOrderListgrid,
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
