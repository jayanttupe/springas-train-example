package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.logisticsOutlayRecordManage;

import java.util.logging.Logger;

import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.ListGridSummaryField;

public class LogisticsOutlayItemListgrid extends ListGrid {

	private Logger log = Logger.getLogger("LogisticsOutlayItemListgrid");

	public void drawLogisticsOutlayItemListgrid()
	{
		setCanRemoveRecords(true);
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(true);
		setShowRowNumbers(true);

		// 物流费用处理记录ID
		ListGridField logisticsOutlayItemIDField = new ListGridField("logisticsOutlayItemID");
		logisticsOutlayItemIDField.setHidden(true);
		// 项号
		ListGridField itemNumberField = new ListGridField("itemNumber");
		itemNumberField.setHidden(true);
		// 单据编号
		ListGridField documentNumberFiled = new ListGridField("documentNumber");
		documentNumberFiled.setHidden(true);
		// 数量
		ListGridField quantityFiled = new ListGridField("quantity");
		quantityFiled.setType(ListGridFieldType.INTEGER);
		// 单价
		ListGridField unitOfPriceFiled = new ListGridField("unitOfPrice");
		unitOfPriceFiled.setCellFormatter(new CellFormatter() {
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				if (value == null)
					return null;
				try {
					NumberFormat nf = NumberFormat.getFormat("#,##0.00");
					return nf.format(((Number) value).doubleValue());
				} catch (Exception e) {
					return value.toString();
				}
			}
		});
		// 金额
		ListGridField amountFiled = new ListGridField("amount");
		amountFiled.setCellFormatter(new CellFormatter() {  
			public String format(Object value, ListGridRecord record, int rowNum, int colNum) {  
		           if (value == null) 
		        	   	return null;  
	                try {
	                    NumberFormat nf = NumberFormat.getFormat("#,##0.00");  
	                    return nf.format(((Number) value).doubleValue());  
	                } catch (Exception e) {  
	                    return value.toString();  
	                }  
		         }  
		      });
		// 项目描述
		ListGridField itemDescriptionFiled = new ListGridField("itemDescription");
		// 币种
		ListGridField internationalCurrencyCodeFiled = new ListGridField("internationalCurrencyCode");
		// 摘要
		ListGridField additionalDeclarationField = new ListGridField("additionalDeclaration");	
		
		setFields(logisticsOutlayItemIDField
				,itemNumberField
				,documentNumberFiled
				,quantityFiled
				,unitOfPriceFiled
				,amountFiled
				,itemDescriptionFiled
				,internationalCurrencyCodeFiled
				,additionalDeclarationField
				);
		setCellHeight(22);
	}
}