
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit.ImportSecurityDepositDetailWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * @author Administrator
 */
public class ExportSecurityDepositButtonPanel extends BaseButtonToolStript {
	private ExportSecurityDepositListgrid exportSecurityDepositListgrid;

	public ExportSecurityDepositButtonPanel(
			final ExportSecurityDepositListgrid exportSecurityDepositListgrid) {
		super("logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportSecurityDeposit");
		this.exportSecurityDepositListgrid = exportSecurityDepositListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("edit".equalsIgnoreCase(buttonName)) {
			if (exportSecurityDepositListgrid.getSelection().length == 1) {

				objWindow = new ExportSecurityDepositDetailWindow("修改出口保证金",
																false,
																rect,
																exportSecurityDepositListgrid,
																null,
																"showwindow/logistics_modify_01.png",
																true,
																false,
																false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (exportSecurityDepositListgrid.getSelection().length == 1) {

				objWindow = new ExportSecurityDepositDetailWindow("查看出口保证金",
																false,
																rect,
																exportSecurityDepositListgrid,
																null,
																"showwindow/logistics_modify_01.png",
																true,
																false,
																true);
			} else {
				SC.say("请选择一条记录进行查看！");
			}
		}
		
		ShowWindowTools.showWondow(rect, objWindow, -1);
	}


}
