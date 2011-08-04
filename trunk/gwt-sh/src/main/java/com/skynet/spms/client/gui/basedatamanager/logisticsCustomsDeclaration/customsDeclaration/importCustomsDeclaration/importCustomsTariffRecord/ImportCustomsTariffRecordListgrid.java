/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord;

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
public class ImportCustomsTariffRecordListgrid extends ListGrid {

	private Logger log = Logger.getLogger("ImportCustomsTariffRecordListgrid");

	public void drawImportCustomsTariffRecordListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 预录入编号
		ListGridField prepareEnterNumberFiled = new ListGridField("prepareEnterNumber");
		// 合同编号
		ListGridField contractNumberFiled = new ListGridField("contractNumber");
		// 指令单号
		ListGridField orderNumberFiled=new ListGridField("orderNumber");
		// 海关编号
		ListGridField customsNumberFiled=new ListGridField("customsNumber");
		// 贸易方式
		ListGridField tradeTypeFiled=new ListGridField("tradeType");
		tradeTypeFiled.setCellFormatter(new ItemRender(EnumTool.TRADEMETHODS));
		// 运输方式
		ListGridField transportationTypeFiled=new ListGridField("transportationType");
		transportationTypeFiled.setCellFormatter(new ItemRender(EnumTool.TRANSPORTATIONCODE));
		// 口岸
		ListGridField portOfClearanceFiled=new ListGridField("portOfClearance");
		// 备件中心位置
		ListGridField spareCenterPositionFiled=new ListGridField("spareCenterPosition");
		// 运单号
		ListGridField trackingNumberFiled=new ListGridField("trackingNumber");
		// 件数
		ListGridField packagesNumberFiled=new ListGridField("packagesNumber");
		// 申报日期
		ListGridField reportingDateFiled=new ListGridField("reportingDate");
		reportingDateFiled.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 申请单位编号
		ListGridField applicantFiled=new ListGridField("applicant");
		
		prepareEnterNumberFiled.setCanFilter(true);
		contractNumberFiled.setCanFilter(true);
		orderNumberFiled.setCanFilter(true);
		customsNumberFiled.setCanFilter(true);
		tradeTypeFiled.setCanFilter(true);
		transportationTypeFiled.setCanFilter(true);
		portOfClearanceFiled.setCanFilter(true);
		spareCenterPositionFiled.setCanFilter(true);
		trackingNumberFiled.setCanFilter(true);
		packagesNumberFiled.setCanFilter(true);
		reportingDateFiled.setCanFilter(true);
		applicantFiled.setCanFilter(true);
		
		setFields(prepareEnterNumberFiled
				,contractNumberFiled
				,orderNumberFiled
				,customsNumberFiled
				,tradeTypeFiled
				,transportationTypeFiled
				,portOfClearanceFiled
				,spareCenterPositionFiled
				,trackingNumberFiled
				,packagesNumberFiled
				,reportingDateFiled
				,applicantFiled
				);
		setCellHeight(22);
	}

}
