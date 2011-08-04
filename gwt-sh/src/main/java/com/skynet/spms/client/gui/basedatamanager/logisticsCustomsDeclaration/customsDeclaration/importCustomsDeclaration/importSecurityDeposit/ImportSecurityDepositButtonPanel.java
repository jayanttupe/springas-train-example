/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit;

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
public class ImportSecurityDepositButtonPanel extends BaseButtonToolStript {
	private ImportSecurityDepositListgrid importSecurityDepositListgrid;

	public ImportSecurityDepositButtonPanel(
			final ImportSecurityDepositListgrid importSecurityDepositListgrid) {
		super("logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.importCustomsDeclaration.importSecurityDeposit");
		this.importSecurityDepositListgrid = importSecurityDepositListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("edit".equalsIgnoreCase(buttonName)) {
			if (importSecurityDepositListgrid.getSelection().length == 1) {

				objWindow = new ImportSecurityDepositDetailWindow("修改进口保证金",
																false,
																rect,
																importSecurityDepositListgrid,
																null,
																"showwindow/logistics_modify_01.png",
																true,
																false,
																false);
			} else {
				SC.say("请选择一条记录进行修改！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (importSecurityDepositListgrid.getSelection().length == 1) {

				objWindow = new ImportSecurityDepositDetailWindow("查看进口保证金",
																false,
																rect,
																importSecurityDepositListgrid,
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
