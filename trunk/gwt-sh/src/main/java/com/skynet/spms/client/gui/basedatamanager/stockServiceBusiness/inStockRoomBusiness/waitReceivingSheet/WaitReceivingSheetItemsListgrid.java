/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.waitReceivingSheet;

import java.util.logging.Logger;

import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class WaitReceivingSheetItemsListgrid extends ListGrid {

	private Logger log = Logger.getLogger("WaitReceivingSheetItemsListgrid");

	public void drawWaitReceivingSheetItemsListgrid()
	{
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowRowNumbers(true);

		// 件号
		ListGridField partNumberField = new ListGridField("partNumber");
		// 件描述
		ListGridField partNameField = new ListGridField("partName");
		// 数量
		ListGridField quantityField = new ListGridField("quantity");
		// 单位
		ListGridField partUnitField = new ListGridField("partUnit");
		// 备件类型
		ListGridField partTypeField = new ListGridField("partType");
		// 是否序号控制
		ListGridField isSerialField = new ListGridField("isSerial");
		isSerialField.setType(ListGridFieldType.BOOLEAN);
		// 分箱号
		ListGridField boxNOField = new ListGridField("boxNO");
        
		partNumberField.setCanFilter(true);
		partNameField.setCanFilter(true);
		quantityField.setCanFilter(true);
		partUnitField.setCanFilter(true);
		partTypeField.setCanFilter(true);
		isSerialField.setCanFilter(true);
		boxNOField.setCanFilter(true);

		setFields(partNumberField
				,partNameField
				,quantityField
				,partUnitField
				,partTypeField
				,isSerialField
				,boxNOField
				);
		setCellHeight(22);
	}

}
