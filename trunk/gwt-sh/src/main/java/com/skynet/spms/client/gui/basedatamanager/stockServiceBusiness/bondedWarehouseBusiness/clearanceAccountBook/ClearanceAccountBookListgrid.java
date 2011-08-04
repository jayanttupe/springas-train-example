/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class ClearanceAccountBookListgrid extends ListGrid {

	private Logger log = Logger.getLogger("ClearanceAccountBookListgrid");

	public void drawClearanceAccountBookListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SINGLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 通关电子账册号 
		ListGridField clearanceAccountBookNumberFiled = new ListGridField("clearanceAccountBookNumber");
		// 描述
		ListGridField describeFiled = new ListGridField("describe");
        
		clearanceAccountBookNumberFiled.setCanFilter(true);
		describeFiled.setCanFilter(true);
		
		setFields(clearanceAccountBookNumberFiled
				,describeFiled
				);
		setCellHeight(22);
	}

}
