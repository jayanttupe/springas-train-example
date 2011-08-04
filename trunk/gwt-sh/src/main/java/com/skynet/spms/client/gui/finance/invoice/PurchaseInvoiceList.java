package com.skynet.spms.client.gui.finance.invoice;

import java.util.logging.Logger;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.gui.finance.format.PayApplyCheckFormat;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PurchaseInvoiceList extends ListGrid {

	private Logger log = Logger.getLogger("PurchaseInvoiceList");
	private DataInfo purchaseInvoiceDataInfo;
	
	public PurchaseInvoiceList(){
		
	}
	
	public void drawPerchaseInvoiceList() {
		// TODO Auto-generated method stub
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");
		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		
		//发票编号
		ListGridField invoiceNumberField = new ListGridField("invoiceNumber");
		invoiceNumberField.setCanEdit(false);
		invoiceNumberField.setCanFilter(true);
		invoiceNumberField.setWidth("15%");
		
		//审核状态
		ListGridField checkField = new ListGridField("isChecked");
		checkField.setCellFormatter(new PayApplyCheckFormat());//渲染Field中的显示值
		checkField.setCanEdit(false);
		checkField.setCanFilter(true);
		checkField.setWidth("10%");
		
		//发票业务类型
		ListGridField businessTypeField = new ListGridField("m_InvoiceBusinessType");
		checkField.setCanEdit(false);
		checkField.setCanFilter(true);		
		
		//发票类型
		ListGridField invoiceTypeField =new ListGridField("m_InvoiceType");
		invoiceTypeField.setCanEdit(false);
		invoiceTypeField.setCanFilter(true);
		invoiceTypeField.setWidth("15%");
		
		//发票税额
		ListGridField taxField = new ListGridField("tax");
		taxField.setCanEdit(false);
		taxField.setCanFilter(true);
		taxField.setWidth("10%");
		
		//总金额
		ListGridField totalAmountField =new ListGridField("totalAmount");
		totalAmountField.setCanEdit(false);
		totalAmountField.setCanFilter(true);
		totalAmountField.setWidth("15%");

		
		//开票人
		ListGridField invoiceUserField = new ListGridField("invoiceUser");
		invoiceUserField.setCanEdit(false);
		invoiceUserField.setCanFilter(true);
		invoiceUserField.setWidth("15%");
		
		//开票日期
		ListGridField invoiceDateField = new ListGridField("invoiceDate");
		invoiceDateField.setCanEdit(false);
		invoiceDateField.setCanFilter(true);
		invoiceDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		setFields(invoiceNumberField,checkField,businessTypeField,invoiceTypeField,taxField,totalAmountField,invoiceUserField,invoiceDateField);
	}

	public void setPurchaseInvoiceDataInfo(DataInfo dataInfo) {
		// TODO Auto-generated method stub
		purchaseInvoiceDataInfo = dataInfo;
	}

	public DataInfo getPurchaseInvoiceDataInfo() {
		return purchaseInvoiceDataInfo;
	}
	
	

	
}
