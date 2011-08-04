package com.skynet.spms.client.gui.customerService.repairService.customerRepairInspectionOrder;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.repairService.customerRepairInspectionOrder.update.CustomerRepairInspectionOrderUpdateWin;
import com.skynet.spms.client.gui.customerService.repairService.customerRepairInspectionOrder.view.CustomerRepairInspectionOrderViewWindow;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 面板工具栏
 * 
 * @author tqc
 * 
 */
public class CustomerRepairInsOrderToolStrip extends BaseButtonToolStript {

	private CustomerRepairInsOrderListGrid listgrid;

	private BaseBusiness business = new BaseBusiness();

	private I18n ui = new I18n();

	public CustomerRepairInsOrderToolStrip(
			final CustomerRepairInsOrderListGrid listgrid) {
		super(DSKey.C_CUSTOMERREPAIRINSORDER);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.listgrid = listgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADDORDER".equals(buttonName)) {// 新建客户确认送修送检指令

		} else if ("MODIFY".equals(buttonName)) {// 修改
			if (!ValidateUtil.isRecordSelected(listgrid)) {
				return;
			}
			Record target = listgrid.getSelectedRecord();
			String publishStatus = target
					.getAttribute(BaseBusiness.PUBLISHSTATUSCONS);
			if (BaseBusiness.PULISHSTATUS_PUBLISHED.equals(publishStatus)) {
				SC.say(ui.getRepairModule().msg_publish_before_update());
				return;
			}
			CustomerRepairInspectionOrderUpdateWin win = new CustomerRepairInspectionOrderUpdateWin(
					listgrid);
			win.show();
		} else if ("DELETE".equals(buttonName)) {// 删除

			business.deleteSheet(listgrid, "RepairContractTemplate", listgrid
					.getSelectedRecord().getAttribute("contractTemplateID"));

		} else if ("PUBLISH".equals(buttonName)) {// 发布

			business.publishSheet(listgrid, "RepairContractTemplate", listgrid
					.getSelectedRecord().getAttribute("contractTemplateID"));

		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布

			business.cancelPublishSheet(listgrid, "RepairContractTemplate",
					listgrid.getSelectedRecord().getAttribute(
							"contractTemplateID"));

		} else if ("VIEW".equals(buttonName)) {// 查看
			if (!ValidateUtil.isRecordSelected(listgrid)) {
				return;
			}
			CustomerRepairInspectionOrderViewWindow win = new CustomerRepairInspectionOrderViewWindow(
					listgrid);
			win.show();

		}else if("REFRESH".equals(buttonName)){
			business.refreshSheet(listgrid);
		}

	}

}
