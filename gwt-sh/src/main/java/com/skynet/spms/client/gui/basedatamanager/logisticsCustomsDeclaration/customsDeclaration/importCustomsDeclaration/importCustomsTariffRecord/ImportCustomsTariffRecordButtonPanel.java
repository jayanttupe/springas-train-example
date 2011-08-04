/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Administrator
 *
 */
public class ImportCustomsTariffRecordButtonPanel extends BaseButtonToolStript {
	private ImportCustomsTariffRecordListgrid importCustomsTariffRecordListgrid;

	public ImportCustomsTariffRecordButtonPanel(
			final ImportCustomsTariffRecordListgrid importCustomsTariffRecordListgrid) {
		super("logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.importCustomsDeclaration.importCustomsTariffRecord");
		this.importCustomsTariffRecordListgrid = importCustomsTariffRecordListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();

		if ("edit".equalsIgnoreCase(buttonName)) {
			if (importCustomsTariffRecordListgrid.getSelection().length == 1) {
				objWindow = new ImportCustomsTariffRecordDetailWindow("修改进口关税金记录"
																	, false
																	, rect
																	, importCustomsTariffRecordListgrid
																	, null
																	, "showwindow/logistics_modify_01.png"
																	, true
																	, false
																	, false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (importCustomsTariffRecordListgrid.getSelection().length == 1) {
				objWindow = new ImportCustomsTariffRecordDetailWindow("查看进口关税金记录"
																	, false
																	, rect
																	, importCustomsTariffRecordListgrid
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
