package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.documentRecords;

import com.skynet.spms.client.gui.base.BaseButtonToolStript;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

public class DocumentRecordsButtonPanel extends BaseButtonToolStript {
	private DocumentRecordsListgrid documentRecordsListgrid;

	public DocumentRecordsButtonPanel(
			final DocumentRecordsListgrid documentRecordsListgrid) {
		super("logisticsCustomsDeclaration.documentRecords");
		this.documentRecordsListgrid = documentRecordsListgrid;
	}

	protected void showWindow(String buttonName, ToolStripButton objButton) {
		Window objWindow = null;
		Rectangle rect = objButton.getPageRect();
		
		if ("edit_document_records".equalsIgnoreCase(buttonName)) {
			if (documentRecordsListgrid.getSelection().length == 1) {
				objWindow = new DocumentRecordsDetailWindow("编辑单证记录",
						false,
						rect,
						documentRecordsListgrid,
						"showwindow/logistics_modify_01.png",
						true,
						false);
			} else {
				SC.say("请选择一条记录进行编辑！");
			}
		} else if ("view".equalsIgnoreCase(buttonName)) {
			if (documentRecordsListgrid.getSelection().length == 1) {
				objWindow = new DocumentRecordsDetailWindow("查看单证记录",
						false,
						rect,
						documentRecordsListgrid,
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