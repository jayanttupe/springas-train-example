/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration;

import java.util.logging.Logger;

import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.ItemRender;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class ExportCustomsDeclarationListgrid extends ListGrid {

	private Logger log = Logger.getLogger("ExportCustomsDeclarationListgrid");

	public void drawExportCustomsDeclarationListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		//预录入编号
		ListGridField preEntryNumberField = new ListGridField("preEntryNumber","预录入编号");
		//海关编号
		ListGridField customsNumberField = new ListGridField("customsNumber","海关编号");
		//合同编号
		ListGridField contractNumberField = new ListGridField("contractNumber","合同编号");
		//备案号
		ListGridField customsRecordationNumberField = new ListGridField("customsRecordationNumber","备案号 ");
		//申报日期
		ListGridField declarationDateField = new ListGridField("declarationDate","申报日期");
		declarationDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		//主运单号
		ListGridField mainWayBillField = new ListGridField("mainWayBill","主运单号");
		//分运单号
		ListGridField houseWayBillField = new ListGridField("houseWayBill","主运单号");
		//运输方式
		ListGridField transportationCodeField = new ListGridField("transportationCode","运输方式");
		transportationCodeField.setCellFormatter(new ItemRender(EnumTool.TRANSPORTATIONCODE));
		//贸易方式
		ListGridField tradeMethodsField = new ListGridField("tradeMethods","贸易方式");
		tradeMethodsField.setCellFormatter(new ItemRender(EnumTool.TRADEMETHODS));
		//保证金/关税
		ListGridField securityOrCustomsTariffNameField = new ListGridField("securityOrCustomsTariffName","保证金/关税");
		
		preEntryNumberField.setCanFilter(true);
		customsNumberField.setCanFilter(true);
		contractNumberField.setCanFilter(true);
		customsRecordationNumberField.setCanFilter(true);
		declarationDateField.setCanFilter(true);
		mainWayBillField.setCanFilter(true);
		houseWayBillField.setCanFilter(true);
		transportationCodeField.setCanFilter(true);
		tradeMethodsField.setCanFilter(true);
		securityOrCustomsTariffNameField.setCanFilter(true);
		
		setFields(preEntryNumberField
				,customsNumberField
				,contractNumberField
				,customsRecordationNumberField
				,declarationDateField
				,mainWayBillField
				,houseWayBillField
				,transportationCodeField
				,tradeMethodsField
				,securityOrCustomsTariffNameField);
		setCellHeight(22);
	}

}

