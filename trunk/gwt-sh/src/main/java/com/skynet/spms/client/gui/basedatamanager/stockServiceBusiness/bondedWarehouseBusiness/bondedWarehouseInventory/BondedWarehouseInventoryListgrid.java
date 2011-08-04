/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseInventory;

import java.util.logging.Logger;

import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class BondedWarehouseInventoryListgrid extends ListGrid {

	private Logger log = Logger.getLogger("BondedWarehouseInventoryListgrid");

	public void drawCredentialsRecordListgrid()
	{
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 库房编码
		ListGridField credentialsCodeFiled = new ListGridField("stockRoomNumber");
		// 件号
		ListGridField stackPositionCodeFiled = new ListGridField("partNumber");
		// 关键字
		ListGridField credentialsFiled = new ListGridField("keyword");
		// 商品hs编码 
		ListGridField contratNumberFiled = new ListGridField("hsCode");
		// 数量
		ListGridField partNumberFiled = new ListGridField("quantity");
		// 计量单位
		ListGridField partSerialNumberFiled = new ListGridField("unitOfMeasure");
		// 仓位号
		ListGridField quantityFiled = new ListGridField("position");
		// 进口报关单号
		ListGridField unitFiled = new ListGridField("customsDeclarationNumber");
		// 进仓日期
		ListGridField providerNameFiled = new ListGridField("inStockDate");
		providerNameFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
        
		credentialsCodeFiled.setCanFilter(true);
		stackPositionCodeFiled.setCanFilter(true);
		credentialsFiled.setCanFilter(true);
		contratNumberFiled.setCanFilter(true);
		partNumberFiled.setCanFilter(true);
		partSerialNumberFiled.setCanFilter(true);
		quantityFiled.setCanFilter(true);
		unitFiled.setCanFilter(true);
		providerNameFiled.setCanFilter(true);
		
		setFields(credentialsCodeFiled
				,stackPositionCodeFiled
				,credentialsFiled
				,contratNumberFiled
				,partNumberFiled
				,partSerialNumberFiled
				,quantityFiled
				,unitFiled
				,providerNameFiled
				);
		setCellHeight(22);
	}

}
