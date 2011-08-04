/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveOrderManage;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class StockMoveOrderManageItemListgrid extends ListGrid{

	private Logger log = Logger.getLogger("StockMoveOrderManageItemListgrid");
	
	public void drawStockMoveOrderItemsManageListgrid(){
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
        setEditByCell(true);
		
        // 任务项号
        ListGridField itemNoField = new ListGridField("itemNo");
		itemNoField.setCanEdit(false);
		// 序号
		ListGridField partFiled = new ListGridField("partSerialNumber");
		partFiled.setCanEdit(false);
		// 库房编号
		ListGridField stockroomField = new ListGridField("stockRoomNumber");
		stockroomField.setCanEdit(false);
		// 任务号
		ListGridField taaskField = new ListGridField("taskNo");
		taaskField.setHidden(true);
		taaskField.setCanEdit(false);
		// 单位
		ListGridField unitField = new ListGridField("unit");
		unitField.setCanEdit(false);		
		// 当前件号
		ListGridField partNumberField = new ListGridField("partNumber");
		// 数量
		ListGridField quantityField = new ListGridField("quantity");
		quantityField.setCanEdit(false);
		
		setFields(itemNoField
				,partFiled
				,stockroomField
				,taaskField
				,unitField
				,partNumberField
				,quantityField
		);
		setCellHeight(22);
	}

}
