package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.nonconformingReport;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class NonconformingReportButtonPanel extends BaseButtonToolStript {
	private NonconformingReportListgrid nonconformingReportListgrid;
	
	public NonconformingReportButtonPanel(final NonconformingReportListgrid nonconformingReportListgrid){
		super("stockServiceBusiness.checkAndAcceptBusiness.nonconformingReport");
		this.nonconformingReportListgrid = nonconformingReportListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
	 if ("transact".equalsIgnoreCase(buttonName)) {
			if (nonconformingReportListgrid.getSelection().length == 1) {
				objWindow = new NonconformingReportDetailWindow("不合格品报告处理", rect, "showwindow/stock_modify_01.png", nonconformingReportListgrid,true);
			} else {
				SC.say("请选择一条记录进行处理！");
			}
		} 
		 
		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}













