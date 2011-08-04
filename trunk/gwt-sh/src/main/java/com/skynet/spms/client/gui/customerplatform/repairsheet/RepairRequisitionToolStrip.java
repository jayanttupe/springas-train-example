package com.skynet.spms.client.gui.customerplatform.repairsheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.customerplatform.common.ModuleKey;
import com.skynet.spms.client.gui.customerplatform.repairsheet.add.RepairSheetAddWin;
import com.skynet.spms.client.gui.customerplatform.repairsheet.update.RepairSheetModifyWin;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * 面板工具栏
 * 
 * @author tqc
 * 
 */
public class RepairRequisitionToolStrip extends BaseButtonToolStript {

	private RepairRqSheetBusiness rqBus = new RepairRqSheetBusiness();

	private RepairRequisitionListGrid listgrid;

	public RepairRequisitionToolStrip(final RepairRequisitionListGrid listgrid) {
		super(ModuleKey.CUSTOMERREPAIRSHEET);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.listgrid = listgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADD".equals(buttonName)) {// 添加
			SC.say("add");
			RepairSheetAddWin win=new RepairSheetAddWin();
			win.show();
		} else if ("MODIFY".equals(buttonName)) {// 修改
			RepairSheetModifyWin modifyWin=new RepairSheetModifyWin();
			modifyWin.show();
		} else if ("DELETE".equals(buttonName)) {// 删除
			rqBus.deleteSheet(listgrid);
		} else if ("PUBLISH".equals(buttonName)) {// 发布
			rqBus.publishSheet(listgrid);
		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布
			rqBus.cancelPublishSheet(listgrid);
		}

	}

}
