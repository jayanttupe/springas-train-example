package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.partsInventory.freezeRecord;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class FreezeRecordListgrid extends ListGrid {

	private Logger log = Logger.getLogger("FreezeRecordListgrid");

	public void drawFreezeRecordListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 库房编号
		ListGridField stockRoomNumberFiled = new ListGridField("partsInventoryRecord.stockRoomNumber");
		// 件号
		ListGridField partNumberFiled = new ListGridField("partsInventoryRecord.partNumber");
		// 序号/批号
		ListGridField partSerialNumberFiled = new ListGridField("partsInventoryRecord.partSerialNumber");
		// 关键字
		ListGridField keyWordFiled = new ListGridField("partsInventoryRecord.keyword");
		// 数量
		ListGridField quantityFiled = new ListGridField("partsInventoryRecord.balanceQuantity");
		// 单位
		ListGridField unitMeasureCodeFiled = new ListGridField("partsInventoryRecord.unit");
		// 货位编号
		ListGridField cargoSpaceNumberFiled = new ListGridField("partsInventoryRecord.cargoSpaceNumber");		
		// 备件状态
		ListGridField stateFiled = new ListGridField("partsInventoryRecord.state");
		// 入库日期
		ListGridField inStockDateFiled = new ListGridField("partsInventoryRecord.inStockDate");
		inStockDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 是否冻结
		ListGridField isFreezeFiled = new ListGridField("partsInventoryRecord.isFreeze");
		// 冻结数量
		ListGridField freezeQuantityFiled = new ListGridField("freezeQuantity");
		// 冻结人
		ListGridField freezeOperatorFiled = new ListGridField("freezeOperator");
		// 冻结日期
		ListGridField freezeDateFiled = new ListGridField("freezeDate");
		freezeDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
			
		stockRoomNumberFiled.setCanFilter(true);
		partNumberFiled.setCanFilter(true);
		partSerialNumberFiled.setCanFilter(true);
		keyWordFiled.setCanFilter(true);
		quantityFiled.setCanFilter(true);
		unitMeasureCodeFiled.setCanFilter(true);
		cargoSpaceNumberFiled.setCanFilter(true);
		stateFiled.setCanFilter(true);
		inStockDateFiled.setCanFilter(true);
		isFreezeFiled.setCanFilter(true);
		freezeQuantityFiled.setCanFilter(true);
		freezeOperatorFiled.setCanFilter(true);
		freezeDateFiled.setCanFilter(true);

		setFields(stockRoomNumberFiled
				,partNumberFiled
				,partSerialNumberFiled
				,keyWordFiled
				,quantityFiled
				,unitMeasureCodeFiled
				,cargoSpaceNumberFiled
				,stateFiled
				,inStockDateFiled
				,isFreezeFiled
				,freezeQuantityFiled
				,freezeOperatorFiled
				,freezeDateFiled
	           );
		setCellHeight(22);
	}
}