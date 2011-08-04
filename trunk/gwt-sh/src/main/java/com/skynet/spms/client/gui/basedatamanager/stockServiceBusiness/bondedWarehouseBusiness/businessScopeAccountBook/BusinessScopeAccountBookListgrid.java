package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class BusinessScopeAccountBookListgrid extends ListGrid {

	private Logger log = Logger.getLogger("BusinessScopeAccountBookListgrid");

	public void drawBusinessScopeAccountBookListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SINGLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 电子账册项号 
		ListGridField accountBookItemsNumberFiled = new ListGridField("accountBookItemsNumber");
		// 描述
		ListGridField describeFiled = new ListGridField("describe");
		
		accountBookItemsNumberFiled.setCanFilter(true);
		describeFiled.setCanFilter(true);
		
		setFields(accountBookItemsNumberFiled
				,describeFiled
				);
		setCellHeight(22);
	}
}