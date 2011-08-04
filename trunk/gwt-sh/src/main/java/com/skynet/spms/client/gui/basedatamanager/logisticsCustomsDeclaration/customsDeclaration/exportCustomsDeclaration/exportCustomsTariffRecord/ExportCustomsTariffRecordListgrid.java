/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord;

import java.util.logging.Logger;

import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.ItemRender;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;

/**
 * @author Administrator
 *
 */
public class ExportCustomsTariffRecordListgrid extends ListGrid {

	private Logger log = Logger.getLogger("ExportCustomsTariffRecordListgrid");

	public void drawExportCustomsTariffRecordListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);


		// 预录入编号
		ListGridField prepareEnterNumberFiled = new ListGridField("prepareEnterNumber","预录入编号");
		// 合同编号
		ListGridField contractNumberFiled = new ListGridField("contractNumber","合同编号");
		// 指令单号
		ListGridField orderNumberFiled=new ListGridField("orderNumber","指令单号");
		// 海关编号
		ListGridField customsNumberFiled=new ListGridField("customsNumber","海关编号");
		// 贸易方式
		ListGridField tradeTypeFiled=new ListGridField("tradeType","贸易方式");
		tradeTypeFiled.setCellFormatter(new ItemRender(EnumTool.TRADEMETHODS));
		// 运输方式
		ListGridField transportationTypeFiled=new ListGridField("transportationType","运输方式");
		transportationTypeFiled.setCellFormatter(new ItemRender(EnumTool.TRANSPORTATIONCODE));
		// 口岸
		ListGridField portFiled=new ListGridField("port","口岸");
		// 备件中心位置
		ListGridField spareCenterPositionFiled=new ListGridField("spareCenterPosition","备件中心位置");
		// 运单号
		ListGridField trackingNumberFiled=new ListGridField("trackingNumber","运单号");
		// 件数
		ListGridField packagesNumberFiled=new ListGridField("packagesNumber","件数");
		// 申报日期
		ListGridField reportingDateFiled=new ListGridField("reportingDate","申报日期");
		// 申请单位编号
		ListGridField applicantFiled=new ListGridField("applicant","申请单位编号");
		// 税款金额
		ListGridField taxPaymentFiled=new ListGridField("taxPayment","税款金额");
//		// 关税单号
//		ListGridField tariffOrderFiled=new ListGridField("tariffOrder");
//		// 总金额
//		ListGridField totalAmountFiled=new ListGridField("totalAmount");
//		// 填发日期
//		ListGridField paymentDateFiled=new ListGridField("paymentDate");
//		// 缴款期限
//		ListGridField payDeadlineFiled=new ListGridField("payDeadline");
//		// 汇率
//		ListGridField exchangeRateFiled=new ListGridField("exchangeRate");
		
		prepareEnterNumberFiled.setCanFilter(true);
		contractNumberFiled.setCanFilter(true);
		customsNumberFiled.setCanFilter(true);
		orderNumberFiled.setCanFilter(true);
		customsNumberFiled.setCanFilter(true);
		tradeTypeFiled.setCanFilter(true);
		transportationTypeFiled.setCanFilter(true);
		portFiled.setCanFilter(true);
		spareCenterPositionFiled.setCanFilter(true);
		trackingNumberFiled.setCanFilter(true);
		packagesNumberFiled.setCanFilter(true);
		reportingDateFiled.setCanFilter(true);
		applicantFiled.setCanFilter(true);
		taxPaymentFiled.setCanFilter(true);
		
		setFields(prepareEnterNumberFiled
				,contractNumberFiled
				,customsNumberFiled
				,orderNumberFiled
				,customsNumberFiled
				,tradeTypeFiled
				,transportationTypeFiled
				,portFiled
				,spareCenterPositionFiled
				,trackingNumberFiled
				,packagesNumberFiled
				,reportingDateFiled
				,applicantFiled
				,taxPaymentFiled
				);
		setCellHeight(22);
	}

}
