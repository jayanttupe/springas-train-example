/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.documentRecords;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class TransprotationDocumentListgrid extends ListGrid {

	private Logger log = Logger.getLogger("TransprotationDocumentListgrid");

	public void drawTransprotationDocumentListgrid()
	{
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(true);
		
		// 单证编号
		ListGridField documentNumberFiled = new ListGridField("documentNumber");
		// 单证名称
		ListGridField documentNameFiled = new ListGridField("documentName");
		// 登记人
		ListGridField registrantFiled = new ListGridField("registrant");
		// 备注
		ListGridField remarkTextFiled = new ListGridField("remarkText");
		// 状态
		ListGridField statusFiled = new ListGridField("status");
		// 运输单证类型
		ListGridField transportDocumentsTypeFiled = new ListGridField("transportDocumentsType");

		setFields(documentNumberFiled
				,documentNameFiled
				,registrantFiled
				,remarkTextFiled
				,statusFiled
				,transportDocumentsTypeFiled
				);
		setCellHeight(22);
	}

}
