/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class ExportCustomsDeclarationItemsListgrid extends ListGrid {

	private Logger log = Logger.getLogger("ExportCustomsDeclarationItemListgrid");

	public void drawExportCustomsDeclarationItemsListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowRowNumbers(true);
		
		// 指令ID
		ListGridField orderIDField = new ListGridField("orderID","指令ID");
		orderIDField.setHidden(true);
		// 报关ID
		ListGridField customsIDField = new ListGridField("customsID","报关ID");
		customsIDField.setHidden(true);
		// 件号
		ListGridField partNumberField = new ListGridField("partNumber","件号");
		partNumberField.setCanEdit(false);
		// 件描述
		ListGridField partNameField = new ListGridField("partName","件描述");
		partNameField.setCanEdit(false);
		// 数量
		ListGridField quantityField = new ListGridField("quantity","数量");
		// 单价
		ListGridField unitPriceField = new ListGridField("unitPrice","单价");
		// 总价
		ListGridField amountField = new ListGridField("amount","总价");
		// 单位
		ListGridField partUnitField = new ListGridField("unitOfMeasure","单位");
		partUnitField.setCanEdit(false);
		// 征免性质
		ListGridField freeGoodsPropertieField = new ListGridField("freeGoodsPropertie","征免性质");
		// 税号(HS商品编码) 
		ListGridField taxFileNumberField = new ListGridField("taxFileNumber","税号");
		// 原产地
		ListGridField countryOfOriginField = new ListGridField("countryOfOrigin","原产地");
				
		setFields(orderIDField
				,customsIDField
				,partNumberField
				,partNameField
				,quantityField
				,unitPriceField
				,amountField
				,partUnitField
				,freeGoodsPropertieField
				,taxFileNumberField
				,countryOfOriginField
				);
		setCellHeight(22);
	}
}
