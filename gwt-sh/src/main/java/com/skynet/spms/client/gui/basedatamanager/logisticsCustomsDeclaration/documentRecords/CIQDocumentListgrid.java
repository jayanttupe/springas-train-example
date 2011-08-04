package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.documentRecords;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class CIQDocumentListgrid extends ListGrid {

	private Logger log = Logger.getLogger("CIQDocumentListgrid");

	public void drawCIQDocumentListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setCanEdit(true);

		// 单证编号
		ListGridField documentNumber = new ListGridField("documentNumber");
		// 单证名称
		ListGridField documentName = new ListGridField("documentName");
		// 登记人
		ListGridField registrant = new ListGridField("registrant");
		// 备注
		ListGridField remarkText = new ListGridField("remarkText");
		// 状态
		ListGridField status = new ListGridField("status");
		// 报关单证类型
		ListGridField clearanceDocumentsType = new ListGridField("clearanceDocumentsType");
		
		setFields(documentNumber
				,documentName
				,registrant
				,remarkText
				,status
				,clearanceDocumentsType
				);
		setCellHeight(22);
	}

}
