package com.skynet.spms.client.gui.customerService.repairService.sheet;

import com.skynet.spms.client.gui.base.BaseBusiness;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.repairService.repairContract.add.RepairContractTemplateAddWin;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 处理送修申请业务
 * 
 * @author tqc
 * 
 */
public class RepairRqSheetBusiness extends BaseBusiness {

	private I18n ui = new I18n();

	public MainModelLocator model = MainModelLocator.getInstance();

	public RepairRqSheetBusiness() {

	}

	/**
	 * 刷新送修申请表格
	 */
	public void refeshRQGrird() {
		Criteria c = new Criteria();
		c.setAttribute("_key", "reload");
		c.setAttribute("_r", String.valueOf(Math.random()));
		model.repairRequisitionListGrid.fetchData(c);
	}

	/**
	 * 新建客户送修合同
	 * 
	 * @param grid
	 */
	public void addRQContract(final ListGrid grid, final ToolStripButton button) {
		if (ValidateUtil.isRecordSelected(grid)) {// 判断是否发布了
			final String state = grid.getSelectedRecord().getAttribute(
					"m_BussinessPublishStatusEntity.m_PublishStatus");
			if ("published".equals(state)) {
				model.repairRequisitionListGrid = SheetModelLocator
						.getInstance().repairRequisitionListGrid;
				RepairContractTemplateAddWin addWin = new RepairContractTemplateAddWin();
				model.repairContractTemplateAddWin = addWin;
				addWin.show();
			} else {
				SC.warn(ui.getM().mod_msg_please_publish_first());
			}

		}
	}

}
