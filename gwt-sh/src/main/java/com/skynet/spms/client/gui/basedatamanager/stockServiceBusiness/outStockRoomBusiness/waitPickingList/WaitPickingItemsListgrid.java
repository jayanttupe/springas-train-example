/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.waitPickingList;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class WaitPickingItemsListgrid extends ListGrid {

	private Logger log = Logger.getLogger("WaitPickingItemsListgrid");

	public void drawWaitPickingItemsListgrid()
	{
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowRowNumbers(true);

		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		// 件描述
		ListGridField keywordField = new ListGridField("partName");
		// 制造商
		ListGridField manufacturerField = new ListGridField("manufacturer");
		// 应发数量
		ListGridField qtyField = new ListGridField("qty");
		// 单位
		ListGridField unitField = new ListGridField("unit");
		// 备件状况
		ListGridField partStatusField = new ListGridField("partStatus");
		// 备件类型
		ListGridField partTypeField = new ListGridField("partType");
        
		partNumberField.setCanFilter(true);
		keywordField.setCanFilter(true);
		manufacturerField.setCanFilter(true);
		qtyField.setCanFilter(true);
		unitField.setCanFilter(true);
		partStatusField.setCanFilter(true);
		partTypeField.setCanFilter(true);

		setFields(partNumberField
				,keywordField
				,manufacturerField
				,qtyField
				,unitField
				,partStatusField
				,partTypeField);
		setCellHeight(22);
	}

}
