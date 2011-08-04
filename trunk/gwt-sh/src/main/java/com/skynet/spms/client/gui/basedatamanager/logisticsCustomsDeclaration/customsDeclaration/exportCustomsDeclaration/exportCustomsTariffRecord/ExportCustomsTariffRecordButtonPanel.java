/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.ImportCustomsTariffRecordDetailWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Administrator
 */
public class ExportCustomsTariffRecordButtonPanel extends BaseButtonToolStript {
	private ExportCustomsTariffRecordListgrid exportCustomsTariffRecordListgrid;

	public ExportCustomsTariffRecordButtonPanel(final ExportCustomsTariffRecordListgrid exportCustomsTariffRecordListgrid) {
		super("logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportCustomsTariffRecord");
		this.exportCustomsTariffRecordListgrid = exportCustomsTariffRecordListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("edit".equalsIgnoreCase(buttonName)) {
			if (exportCustomsTariffRecordListgrid.getSelection().length == 1) {
				objWindow = new ExportCustomsTariffRecordDetailWindow("修改出口关税金记录"
																	, false
																	, rect
																	, exportCustomsTariffRecordListgrid
																	, null
																	, "showwindow/logistics_modify_01.png"
																	, true
																	, false
																	, false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (exportCustomsTariffRecordListgrid.getSelection().length == 1) {
				objWindow = new ExportCustomsTariffRecordDetailWindow("查看出口关税金记录"
																	, false
																	, rect
																	, exportCustomsTariffRecordListgrid
																	, null
																	, "showwindow/logistics_modify_01.png"
																	, true
																	, false
																	, true);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		}

		ShowWindowTools.showWondow(rect, objWindow, -1);
	}


}
