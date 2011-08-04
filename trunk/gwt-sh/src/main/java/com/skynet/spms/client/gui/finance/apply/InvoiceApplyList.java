package com.skynet.spms.client.gui.finance.apply;

import java.util.logging.Logger;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.gui.finance.format.PayApplyCheckFormat;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class InvoiceApplyList extends ListGrid {

	
	private Logger log = Logger.getLogger("InvoiceApplyList");
	private DataInfo invoiceApplyDataInfo;
	
	public InvoiceApplyList(){
		
	}
	
	public void setInvoiceApplyDataInfo(DataInfo dataInfo) {
		// TODO Auto-generated method stub
		invoiceApplyDataInfo = dataInfo;
	}

	public void drawInvoiceApplyList() {
		// TODO Auto-generated method stub
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");

		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		
		//开票申请编号
		ListGridField applyNumberField = new ListGridField("applyNumber");
		applyNumberField.setCanEdit(false);
		applyNumberField.setCanFilter(true);
		applyNumberField.setWidth("15%");

		//审批状态
		ListGridField checkField = new ListGridField("check");
		checkField.setCellFormatter(new PayApplyCheckFormat());//渲染Field中的显示值
		checkField.setCanEdit(false);
		checkField.setCanFilter(true);
		checkField.setWidth("15%");
		
		//合同/订单编号
		ListGridField orderField = new ListGridField("orderNumber");
		orderField.setCanEdit(false);
		orderField.setCanFilter(true);
		orderField.setWidth("15%");
		
		//业务类型 m_PayActionType
		ListGridField businessField = new ListGridField("m_PayActionType");
		businessField.setCanEdit(false);
		businessField.setCanFilter(true);
		businessField.setWidth("15%");
		
		
		//发票类型
		ListGridField invoiceTypeField = new ListGridField("m_InvoiceType");
		invoiceTypeField.setCanEdit(false);
		invoiceTypeField.setCanFilter(true);
		invoiceTypeField.setWidth("15%");
		
		//收票方名称
		ListGridField recipeCashierNameField = new ListGridField("recipeCashierName");
		recipeCashierNameField.setCanEdit(false);
		recipeCashierNameField.setCanFilter(true);	
		recipeCashierNameField.setWidth("20%");
		
		//开票金额
		ListGridField payAmountField = new ListGridField("invoiceAmout");
		payAmountField.setCanEdit(false);
		payAmountField.setCanFilter(true);
		payAmountField.setWidth("10%");


		
		//申请人
		ListGridField applyUserField = new ListGridField("applyUser");
		applyUserField.setCanEdit(false);
		applyUserField.setCanFilter(true);
		applyUserField.setWidth("10%");
		
		//申请日期
		ListGridField applyDateField = new ListGridField("applyDate");
		applyDateField.setCanEdit(false);
		applyDateField.setCanFilter(true);
		applyDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		setFields(applyNumberField,checkField,orderField,businessField,invoiceTypeField,recipeCashierNameField,payAmountField,applyUserField,applyDateField);
}

	public DataInfo getInvoiceApplyDataInfo() {
		return invoiceApplyDataInfo;
	}
}
