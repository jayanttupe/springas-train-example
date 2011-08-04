package com.skynet.spms.client.gui.finance.apply;

import java.util.logging.Logger;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.gui.finance.format.PayApplyCheckFormat;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

public class PayApplyList extends ListGrid {

	private Logger log = Logger.getLogger("PayApplyList");
	private DataInfo payApplyDataInfo;
	
	public PayApplyList(){

		
	}
	
	public void setPayApplyDataInfo(DataInfo dataInfo){
		payApplyDataInfo = dataInfo;
	}
	
	public DataInfo getPayApplyDataInfo() {
		return payApplyDataInfo;
	}

	public void drawPayApplyList(){
		setCanRemoveRecords(true);
		setRemoveFieldTitle("删除");

		setSelectionType(SelectionStyle.SIMPLE);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		
		
		//付款申请编号
		ListGridField applyNumberField = new ListGridField("applyNumber");
		applyNumberField.setCanEdit(false);
		applyNumberField.setCanFilter(true);
		applyNumberField.setWidth("15%");
		
		//审批状态
		ListGridField checkField = new ListGridField("check");
		checkField.setCellFormatter(new PayApplyCheckFormat());//渲染Field中的显示值
		checkField.setCanEdit(false);
		checkField.setCanFilter(true);
		checkField.setWidth("10%");
		
		//业务类型 2011-6-14 yhuang
		ListGridField businessTypeField = new ListGridField("m_PayActionType");
		businessTypeField.setCanEdit(false);
		businessTypeField.setCanFilter(true);
		businessTypeField.setWidth("10%");
		
		//合同编号 2011-6-14 yhuang
		ListGridField contractNumberField = new ListGridField("orderNumber");
		contractNumberField.setCanEdit(false);
		contractNumberField.setCanFilter(true);
		contractNumberField.setWidth("10%");
		
		//收款方名称
		ListGridField getField = new ListGridField("check");
		checkField.setCanEdit(false);
		checkField.setCanFilter(true);		
		
		//收款人
		ListGridField cashierNameField = new ListGridField("cashierName");
		cashierNameField.setCanEdit(false);
		cashierNameField.setCanFilter(true);
		cashierNameField.setWidth("15%");
		
		//支付金额
		ListGridField payAmountField = new ListGridField("payAmount");
		payAmountField.setCanEdit(false);
		payAmountField.setCanFilter(true);
		payAmountField.setWidth("10%");
		
		//付款方式
		ListGridField paymentField =payApplyDataInfo.getFieldInfoByName("m_Payment").generGridField();
		paymentField.setCanEdit(false);
		paymentField.setCanFilter(true);
		paymentField.setWidth("15%");

		//申请人
		ListGridField applyUserField = new ListGridField("applyUser");
		applyUserField.setCanEdit(false);
		applyUserField.setCanFilter(true);
		applyUserField.setWidth("15%");
		
		//申请日期
		ListGridField applyDateField = new ListGridField("applyDate");
		applyDateField.setCanEdit(false);
		applyDateField.setCanFilter(true);
		applyDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		setFields(applyNumberField,checkField,businessTypeField,contractNumberField,cashierNameField,payAmountField,applyUserField,applyDateField);
		
		
	}

	


}
