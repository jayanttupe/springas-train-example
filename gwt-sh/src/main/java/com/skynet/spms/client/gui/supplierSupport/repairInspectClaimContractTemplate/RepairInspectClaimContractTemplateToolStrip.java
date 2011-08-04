package com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.model.ModelLocator;
import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.view.RepairInsClaimContractTemplateViewWindow;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 面板工具栏
 * 
 * @author tqc
 * 
 */
public class RepairInspectClaimContractTemplateToolStrip extends
		BaseButtonToolStript {

	private final RepairInspectClaimContractTemplateListGrid listGrid;

	public ModelLocator model = ModelLocator.getInstance();

	BusinessMang mang = new BusinessMang();

	public RepairInspectClaimContractTemplateToolStrip(
			final RepairInspectClaimContractTemplateListGrid listgrid) {
		super(DSKey.R_EPAIRINSPECTCLAIMCONTRACTTEMPLATE);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.listGrid = listgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADD".equals(buttonName)) {// 添加
		} else if ("MODIFY".equals(buttonName)) {// 修改
			mang.modifyContract(listGrid);
		} else if ("DELETE".equals(buttonName)) {// 删除
			mang.deleteSheet(listGrid,"CustomerRepairInspectionOrder",listGrid.getSelectedRecord().getAttribute("orderId"));
		} else if ("PUBLISH".equals(buttonName)) {// 发布
			mang.publishSheet(listGrid,"CustomerRepairInspectionOrder",listGrid.getSelectedRecord().getAttribute("orderId"));
		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布
			mang.cancelPublishSheet(listGrid,"CustomerRepairInspectionOrder",listGrid.getSelectedRecord().getAttribute("orderId"));
		} else if ("EXPORT".equals(buttonName)) { // 导出

		} else if ("SENIORSEARCH".equals(buttonName)) { // 高级查询

		}else if("VIEW".equals(buttonName)){//查看
			model.viewDetailContractID=listGrid.getSelectedRecord().getAttribute("id");
			RepairInsClaimContractTemplateViewWindow win=new RepairInsClaimContractTemplateViewWindow();
			win.show();
		}else if("REFRESH".equals(buttonName)){
			mang.refreshSheet(listGrid);
		}

	}

}
