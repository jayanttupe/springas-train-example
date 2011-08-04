package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.documentRecords;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class CIQDocumentDetailsListgrid extends ListGrid {

	private Logger log = Logger.getLogger("CIQDocumentDetailsListgrid");

	public void drawCIQDocumentDetailsListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);

		// 单证编号
		ListGridField documentNumber = new ListGridField("documentNumber");
		// 单证名称
		ListGridField documentName = new ListGridField("documentName");
		// 单证类型
		ListGridField transportDocumentsType = new ListGridField("transportDocumentsType");
		// 单证附件
		ListGridField attachment = new ListGridField("attachment");
		// 登记人
		ListGridField registrant = new ListGridField("registrant");
		// 更新时间
		ListGridField updateDate = new ListGridField("updateDate");
		updateDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 是否审核
		ListGridField isVerify = new ListGridField("isVerify");
		// 审核人
		ListGridField verifyUser = new ListGridField("verifyUser");
		// 审核时间
		ListGridField verifyDate = new ListGridField("verifyDate");
		verifyDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		
        
		setFields(documentNumber
				,documentName
				,transportDocumentsType
				,attachment
				,registrant
				,updateDate
				,isVerify
				,verifyUser
				,verifyDate
				);
		setCellHeight(22);
	}

}
