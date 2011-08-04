/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit;

import java.util.logging.Logger;

import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 */
public class ExportSecurityDepositItemsListgrid extends ListGrid {

	private Logger log = Logger.getLogger("ExportSecurityDepositSparepartsListgrid");

	public void drawExportSecurityDepositSparepartsListgrid()
	{
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowRowNumbers(true);

		//指令ID
		ListGridField orderIDField = new ListGridField("orderID","指令ID");
		orderIDField.setHidden(true);
		//报关ID
		ListGridField customsIDField = new ListGridField("customsID","报关ID");
		customsIDField.setHidden(true);
		//件号
		ListGridField partNumberField = new ListGridField("partNumber","件号");
		partNumberField.setCanEdit(false);
		//件描述
		ListGridField partNameField = new ListGridField("partName","件描述");
		partNameField.setCanEdit(false);
		//数量
		ListGridField quantityField = new ListGridField("quantity","数量");
		//单价
		ListGridField unitPriceField = new ListGridField("unitPrice","单价");
		//总价
		ListGridField amountField = new ListGridField("amount","总价");
		//单位
		ListGridField partUnitField = new ListGridField("unitOfMeasure","单位");
		partUnitField.setCanEdit(false);
		//税号(HS商品编码) 
		ListGridField taxFileNumberField = new ListGridField("taxFileNumber","税号");
		//担保金额
		ListGridField invoiceGuarantyAmountField = new ListGridField("invoiceGuarantyAmount","担保金额");
		//税率(%)
		ListGridField invoiceRatesField = new ListGridField("invoiceRates","税率(%)");
		//完税金额
		ListGridField invoiceTaxAmountField = new ListGridField("invoiceTaxAmount","完税金额");
		//(计量)单位(发票备件)
		ListGridField invoiceUnitField = new ListGridField("invoiceUnit","(计量)单位");
		//征免性质
		ListGridField freeGoodsPropertieField = new ListGridField("freeGoodsPropertie","征免性质");
		//原产地
		ListGridField countryOfOriginField = new ListGridField("countryOfOrigin","原产地");
		
		setFields(orderIDField
				,customsIDField
				,partNumberField
				,partNameField
				,quantityField
				,unitPriceField
				,amountField
				,partUnitField
				,taxFileNumberField
				,invoiceGuarantyAmountField
				,invoiceRatesField
				,invoiceTaxAmountField
				,invoiceUnitField
				,freeGoodsPropertieField
				,countryOfOriginField
				);
		setCellHeight(22);
	}
}
