package com.skynet.spms.client.gui.customerService.exchangeRequisitionSheet;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.skynet.spms.client.gui.customerService.exchangeRequisitionSheet.add.ExchangeRequisitionSheetAddWin;
import com.skynet.spms.client.gui.customerService.exchangeRequisitionSheet.modify.ExchangeRequisitionSheetModifyWin;
/**
 * 面板工具栏
 * 
 * @author tqc
 * 
 */
public class ExchangeRequisitionSheetToolStrip extends BaseButtonToolStript {

	private ExchangeRequisitionSheetBusiness rqBus = new ExchangeRequisitionSheetBusiness();

	private ExchangeRequisitionSheetListGrid listgrid;

	public ExchangeRequisitionSheetToolStrip(final ExchangeRequisitionSheetListGrid listgrid) {
		super(DSKey.C_EXCHANGE_REQUISITION);
		this.setWidth100();
		this.setHeight(30);
		this.setLayoutLeftMargin(5);
		this.setLayoutRightMargin(5);
		this.listgrid = listgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton button) {
		if ("ADD".equals(buttonName)) {// 添加
			ExchangeRequisitionSheetAddWin win=new ExchangeRequisitionSheetAddWin();
			ShowWindowTools.showWindow(button.getPageRect(), win, 500);
		} else if ("MODIFY".equals(buttonName)) {// 修改
			ExchangeRequisitionSheetModifyWin modifyWin=new ExchangeRequisitionSheetModifyWin();
			ShowWindowTools.showWindow(button.getPageRect(), modifyWin, 300);
		} else if ("DELETE".equals(buttonName)) {// 删除
			rqBus.deleteSheet(listgrid);
		} else if ("PUBLISH".equals(buttonName)) {// 发布
			rqBus.publishSheet(listgrid);
		} else if ("CANCELPUBLISH".equals(buttonName)) {// 取消发布
			rqBus.cancelPublishSheet(listgrid);
		} else if ("REFRESH".equals(buttonName)) {
			rqBus.refreshSheet(listgrid);
		} 

	}

}
