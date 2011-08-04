package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.logisticsOutlayRecordManage;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class LogisticsOutlayRecordButtonPanel extends BaseButtonToolStript {
	private LogisticsOutlayRecordListgrid logisticsOutlayRecordListgrid;

	public LogisticsOutlayRecordButtonPanel(
			final LogisticsOutlayRecordListgrid logisticsOutlayRecordListgrid) {
		super("logisticsCustomsDeclaration.logisticsOutlayRecordManage");
		this.logisticsOutlayRecordListgrid = logisticsOutlayRecordListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();

		if ("editoutlay".equalsIgnoreCase(buttonName)) {
			if (logisticsOutlayRecordListgrid.getSelection().length == 1) {
				objWindow = new LogisticsOutlayRecordDetailWindow("编辑费用记录",
						false,
						rect,
						logisticsOutlayRecordListgrid,
						"showwindow/logistics_modify_01.png",
						true,
						false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (logisticsOutlayRecordListgrid.getSelection().length == 1) {
				objWindow = new LogisticsOutlayRecordDetailWindow("查看费用记录",
						false,
						rect,
						logisticsOutlayRecordListgrid,
						"showwindow/logistics_modify_01.png",
						true,
						true);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		}
		
		ShowWindowTools.showWondow(rect, objWindow, -1);
	}
}