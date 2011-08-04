/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit;

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
public class ImportSecurityDepositListgrid extends ListGrid {

	private Logger log = Logger.getLogger("ImportSecurityDepositListggrid");

	public void drawImportSecurityDepositListgrid()
	{
		setSelectionType(SelectionStyle.SINGLE);
		setCanEdit(false);
		setShowFilterEditor(true);

		// 合同编号
		ListGridField contractNumberField = new ListGridField("contractNumber","合同编号");
		// 申报日期
		ListGridField declarationDateField = new ListGridField("declarationDate","申报日期");
		declarationDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 到期时间
		ListGridField expireDatesField = new ListGridField("expireDates","到期时间");
		expireDatesField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 主运单号
		ListGridField masterWaybillNumberField = new ListGridField("masterWaybillNumber","主运单号");
		// 分运单号
		ListGridField houseWaybillNumberField = new ListGridField("houseWaybillNumber","分运单号");
		// 件数
		ListGridField numberOfPackageField = new ListGridField("numberOfPackage","件数");
		// 办保原因
		ListGridField reasonField = new ListGridField("reason","办保原因");
		// 担保金额
		ListGridField securityDepositAmountField = new ListGridField("securityDepositAmount","担保金额");
		// 保证金单编号
		ListGridField securityDepositNumberField = new ListGridField("securityDepositNumber","保证金单编号");
		// 保证起始日期
		ListGridField startDateField = new ListGridField("startDate","保证起始日期");
		startDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
		// 运输方式代码
		ListGridField transportationCodeField = new ListGridField("transportationCode","运输方式");
		transportationCodeField.setCellFormatter(new ItemRender(EnumTool.TRANSPORTATIONCODE));
		// 报关代理商
		ListGridField clearanceAgentField = new ListGridField("clearanceAgent","报关代理商");
		// 保证金类型
		ListGridField securityDepositTypeField = new ListGridField("securityDepositType","保证金类型");
		// 报关口岸
		ListGridField portOfClearanceField = new ListGridField("portOfClearance","报关口岸");
		// 备件中心位置
		ListGridField sparePartCenterPositionField = new ListGridField("sparePartCenterPosition","备件中心位置");
		// 进口日期
		ListGridField importDateField = new ListGridField("importDate","进口日期");
		importDateField.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);

		contractNumberField.setCanFilter(true);
		declarationDateField.setCanFilter(true);
		expireDatesField.setCanFilter(true);
		masterWaybillNumberField.setCanFilter(true);
		houseWaybillNumberField.setCanFilter(true);
		numberOfPackageField.setCanFilter(true);
		reasonField.setCanFilter(true);
		securityDepositAmountField.setCanFilter(true);
		securityDepositNumberField.setCanFilter(true);
		startDateField.setCanFilter(true);
		transportationCodeField.setCanFilter(true);
		clearanceAgentField.setCanFilter(true);
		securityDepositTypeField.setCanFilter(true);
		portOfClearanceField.setCanFilter(true);
		sparePartCenterPositionField.setCanFilter(true);
		importDateField.setCanFilter(true);
		
		setFields(contractNumberField
				,declarationDateField
				,expireDatesField
				,masterWaybillNumberField
				,houseWaybillNumberField
				,numberOfPackageField
				,reasonField
				,securityDepositAmountField
				,securityDepositNumberField
				,startDateField
				,transportationCodeField
				,clearanceAgentField
				,securityDepositTypeField
				,portOfClearanceField
				,sparePartCenterPositionField
				,importDateField
				);
		setCellHeight(22);
	}

}
