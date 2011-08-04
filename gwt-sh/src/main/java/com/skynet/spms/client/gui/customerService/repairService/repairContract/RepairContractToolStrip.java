package com.skynet.spms.client.gui.customerService.repairService.repairContract;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 面板工具栏
 * 
 * @author tqc
 * 
 */
public class RepairContractToolStrip extends BaseButtonToolStript {

	private RepairContractListGrid grid;

	private Business business = new Business();

	ContractModelLocator model = ContractModelLocator.getInstance();

	public RepairContractToolStrip(RepairContractListGrid grid) {
		super(DSKey.C_REPAIRECONTRACT);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.grid = grid;
	}

	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADD".equals(buttonName)) {// 添加
		} else if ("MODIFY".equals(buttonName)) {// 修改
			if (ValidateUtil.isRecordSelected(grid)) {
				model.repairContractListGrid = grid;
				business.updateContract(grid);
			}
		} else if ("DELETE".equals(buttonName)) {// 删除
			business.deleteSheet(grid,"RepairRequisitionSheet",grid.getSelectedRecord().getAttribute("rqId"));
		} else if ("PUBLISH".equals(buttonName)) {// 发布
			business.publishSheet(grid,"RepairRequisitionSheet",grid.getSelectedRecord().getAttribute("rqId"));
		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布
			business.cancelPublishSheet(grid,"RepairRequisitionSheet",grid.getSelectedRecord().getAttribute("rqId"));
		} else if ("VIEW".equals(buttonName)) {// 查看
			if (ValidateUtil.isRecordSelected(grid)) {
				business.viewDetail(grid.getSelectedRecord()
						.getAttributeAsString("id"));
			}
		}else if("REFRESH".equals(buttonName)){
			business.refreshSheet(grid);
		}

	}

}
