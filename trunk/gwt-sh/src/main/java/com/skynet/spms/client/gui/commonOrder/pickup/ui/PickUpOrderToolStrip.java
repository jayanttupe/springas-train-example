package com.skynet.spms.client.gui.commonOrder.pickup.ui;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.commonOrder.KeyManager;
import com.skynet.spms.client.gui.commonOrder.pickup.business.PicUpOrderBusiness;
import com.skynet.spms.client.gui.commonOrder.pickup.model.DataModelLocator;
import com.skynet.spms.client.gui.commonOrder.pickup.ui.modify.PickUpOrderModifyWindow;
import com.skynet.spms.client.gui.commonOrder.pickup.ui.view.PickUpOrderViewWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 主订单操作按钮
 * 
 * @author Tony FANG
 * 
 */
public class PickUpOrderToolStrip extends BaseButtonToolStript {

	PicUpOrderBusiness business = new PicUpOrderBusiness();

	private ListGrid listgrid;

	public PickUpOrderToolStrip(ListGrid listgrid, String modName) {
		super(modName);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.listgrid = listgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton button) {

		if ("MODIFY".equals(buttonName)) {// 修改
			String businessType = listgrid.getSelectedRecord().getAttribute(
					"businessType");
			String contractId = listgrid.getSelectedRecord().getAttribute(
					"contractID");
			if (ValidateUtil.isRecordSelected(listgrid)) {
				DataModelLocator.getInstance().modifyOrderGrid = listgrid;
				PickUpOrderModifyWindow modifyWin = new PickUpOrderModifyWindow(
						"", true, button.getPageRect(), listgrid, contractId,
						"", KeyManager.businessType2ContractKey(businessType));
				ShowWindowTools
						.showWindow(button.getPageRect(), modifyWin, 500);
			}
		} else if ("DELETE".equals(buttonName)) {// 删除
			String businessType = listgrid.getSelectedRecord().getAttribute(
					"businessType");
			String contractId = listgrid.getSelectedRecord().getAttribute(
					"contractID");

			String clsName = KeyManager
					.contractBusinessType2EntityName(businessType);
			if (null != clsName) {
				business.deleteSheet(listgrid, clsName, contractId);
			} else {
				business.deleteSheet(listgrid);
			}
		} else if ("PUBLISH".equals(buttonName)) {// 发布
			String businessType = listgrid.getSelectedRecord().getAttribute(
					"businessType");
			String contractId = listgrid.getSelectedRecord().getAttribute(
					"contractID");

			String clsName = KeyManager
					.contractBusinessType2EntityName(businessType);
			if (null != clsName) {
				business.publishSheet(listgrid, clsName, contractId);
			} else {
				business.publishSheet(listgrid);
			}
		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布
			String businessType = listgrid.getSelectedRecord().getAttribute(
					"businessType");
			String contractId = listgrid.getSelectedRecord().getAttribute(
					"contractID");

			String clsName = KeyManager
					.contractBusinessType2EntityName(businessType);
			if (null != clsName) {
				business.cancelPublishSheet(listgrid, clsName, contractId);
			} else {
				business.cancelPublishSheet(listgrid);
			}
		} else if ("VIEW".equals(buttonName)) {// 查看
			String businessType = listgrid.getSelectedRecord().getAttribute(
					"businessType");
			String contractId = listgrid.getSelectedRecord().getAttribute(
					"contractID");
			if (ValidateUtil.isRecordSelected(listgrid)) {
				DataModelLocator.getInstance().modifyOrderGrid = listgrid;
				PickUpOrderViewWindow viewWin = new PickUpOrderViewWindow("",
						true, button.getPageRect(), listgrid, contractId, "",
						KeyManager.businessType2ContractKey(businessType));
				viewWin.show();
			}
		} else if ("REFRESH".equals(buttonName)) {
			business.refreshSheet(listgrid);
		}
	}
}
