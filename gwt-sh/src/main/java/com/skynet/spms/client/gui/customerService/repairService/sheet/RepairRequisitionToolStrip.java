package com.skynet.spms.client.gui.customerService.repairService.sheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.repairService.sheet.add.RepairSheetAddWin;
import com.skynet.spms.client.gui.customerService.repairService.sheet.modify.RepairSheetModifyWin;
import com.skynet.spms.client.gui.customerService.repairService.sheet.view.RepairSheetViewWin;
import com.skynet.spms.client.tools.ShowWindowTools;
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
		super(DSKey.C_REPAIRREQUISITIONSHEET);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.listgrid = listgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADD".equals(buttonName)) {// 添加
			RepairSheetAddWin win=new RepairSheetAddWin();
			ShowWindowTools.showWindow(button.getPageRect(), win, 500);
		} else if ("MODIFY".equals(buttonName)) {// 修改
			if(!ValidateUtil.isRecordSelected(listgrid)){
				return ;
			}
			RepairSheetModifyWin modifyWin=new RepairSheetModifyWin();
			modifyWin.show();
		} else if ("DELETE".equals(buttonName)) {// 删除
			rqBus.deleteSheet(listgrid);
		} else if ("PUBLISH".equals(buttonName)) {// 发布
			rqBus.publishSheet(listgrid);
		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布
			rqBus.cancelPublishSheet(listgrid);
		} else if ("ADDCONTRACT".equals(buttonName)) {// 新建合同
			rqBus.addRQContract(listgrid, button);
		}else if("VIEW".equals(buttonName)){//查看
			if(!ValidateUtil.isRecordSelected(listgrid)){
				return ;
			}
			RepairSheetViewWin viewWin=new RepairSheetViewWin();
			viewWin.show();
		}else if("REFRESH".equals(buttonName)){
			rqBus.refreshSheet(listgrid);
		}

	}

}
