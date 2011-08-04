/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsDeclaration;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Administrator
 *
 */
public class ExportCustomsDeclarationDetailWindow extends Window {

	/**
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param listGrid
	 * @param iconUrl
	 */
	public ExportCustomsDeclarationDetailWindow(String windowTitle, 
			boolean isMax,
			final Rectangle rect,
			final ListGrid listGrid,
			String iconUrl,
			final Boolean updateFlg,
			final Boolean isView) {
		final Window objWindow = this;
		setWidth(1100);
		setHeight(590);

		// 预录入编号
		final TextItem txtPreEntryNumber = new TextItem("preEntryNumber","预录入编号");
		txtPreEntryNumber.setDisabled(true);
		txtPreEntryNumber.setWidth(120);
		// 指令编号
		final TextItem txtOrderNumber = new TextItem("orderNumber","指令编号");
		txtOrderNumber.setDisabled(true);
		txtOrderNumber.setWidth(120);
		// 主运单号
		final TextItem txtMainWayBill = new TextItem("mainWayBill","主运单号");
		txtMainWayBill.setWidth(120);
		// 分运单号
		final TextItem txtHouseWayBill = new TextItem("houseWayBill","分运单号");
		txtHouseWayBill.setWidth(120);
		// 批准文号
		final TextItem txtApprovalNumber = new TextItem("approvalNumber","批准文号");
		txtApprovalNumber.setWidth(120);
		// 经营单位
		final TextItem txtBusinessUnitField	= new TextItem("businessUnit","经营单位");
		txtBusinessUnitField.setWidth(120);
		// 收货单位
		final TextItem txtConsignee = new TextItem("consignee","收货单位");
		txtConsignee.setWidth(120);
		// 集装箱号
		final TextItem txtContainerNumber = new TextItem("containerNumber","集装箱号");
		txtContainerNumber.setWidth(120);
		// 合同编号
		final TextItem txtContractNumber = new TextItem("contractNumber","合同编号");
		txtContractNumber.setWidth(120);
		// 海关编号
		final TextItem txtCustomsNumber = new TextItem("customsNumber","海关编号");
		txtCustomsNumber.setWidth(120);
		// 备案号 
		final TextItem txtCustomsRecordationNumber = new TextItem("customsRecordationNumber","备案号");
		txtCustomsRecordationNumber.setWidth(120);
		// 申报日期
		final CustomDateItem txtDeclarationDate = new CustomDateItem("declarationDate","申报日期");
		txtDeclarationDate.setWidth(120);
		// 运费
		final TextItem txtFreight = new TextItem("freight","运费");
		txtFreight.setWidth(120);
		// 毛重(KG)
		final TextItem txtGrossWeight = new TextItem("grossWeight","毛重(KG)");
		txtGrossWeight.setWidth(120);
		// 保费
		final TextItem txtInsurancePremium = new TextItem("insurancePremium","保费");
		txtInsurancePremium.setWidth(120);
		// 征税比例(%)
		final TextItem txtLevyProportion= new TextItem("levyProportion","征税比例(%)");
		txtLevyProportion.setWidth(120);
		// 许可证号
		final TextItem txtLicenseNumber = new TextItem("licenseNumber","许可证号");
		txtLicenseNumber.setWidth(120);
		// 杂费
		final TextItem txtMiscellaneousFees = new TextItem("miscellaneousFees","杂费");
		txtMiscellaneousFees.setWidth(120);
		// 净重(KG)
		final TextItem txtNetWeight = new TextItem("netWeight","净重(KG)");
		txtNetWeight.setWidth(120);
		// 件数
		final TextItem txtNumberOfPackage = new TextItem("numberOfPackage","件数");
		txtNumberOfPackage.setWidth(120);
		// 指令ID
		final HiddenItem txtOrderID = new HiddenItem("orderID");
		// 包装种类
		final TextItem txtPackageType = new TextItem("packageType","包装种类");
		txtPackageType.setWidth(120);
		// 用途
		final TextAreaItem txtPurpose = new TextAreaItem("purpose","用途");
		txtPurpose.setColSpan(3);
		txtPurpose.setHeight(40);
		txtPurpose.setWidth(340);
		// 备注
		final TextAreaItem txtRemarkText = new TextAreaItem("remarkText","备注");
		txtRemarkText.setColSpan(3);
		txtRemarkText.setHeight(40);
		txtRemarkText.setWidth(340);
		// 交货方式
		final SelectItem txtDeliveryType = new SelectItem("deliveryType", "交货方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.DeliveryType", txtDeliveryType);
		txtDeliveryType.setWidth(120);
		// 运输方式
		final SelectItem txtTransportationCode = new SelectItem("transportationCode", "运输方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.TransportationCode", txtTransportationCode);
		txtTransportationCode.setWidth(120);
		// 贸易方式
		final SelectItem txtTradeMethods  = new SelectItem("tradeMethods", "贸易方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.TradeMethods", txtTradeMethods);
		txtTradeMethods.setWidth(120);
		// 报关代理商
		final SelectItem selClearanceAgent = new SelectItem("clearanceAgent", "报关代理商");
		selClearanceAgent.setWidth(120);
		ListGridField clearanceAgentCodeField = new ListGridField("clearanceAgent", "报关代理商代码");
		ListGridField clearanceAgentNameField = new ListGridField("abbreviation", "报关代理商名称");
		selClearanceAgent.setPickListFields(clearanceAgentCodeField, clearanceAgentNameField);
		selClearanceAgent.setPickListWidth(220);
		// 获取报关代理商
		String clearanceAgencyModeName = "organization.enterprise.clearanceAgency";
		String clearanceAgencyDSName = "clearanceAgency_dataSource";
		DataSourceTool clearanceAgencyDST = new DataSourceTool();
		clearanceAgencyDST.onCreateDataSource(clearanceAgencyModeName, clearanceAgencyDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						selClearanceAgent.clearValue();
						selClearanceAgent.setOptionDataSource(dataSource);

						selClearanceAgent.setValueField("id");
						selClearanceAgent.setDisplayField("abbreviation");

						if (updateFlg == true) {
							final Record record = listGrid.getSelectedRecord();
							selClearanceAgent.setValue(record.getAttribute("clearanceAgent"));
						}
					}
				});
		// 运抵国
		final TextItem txtCountryOfArrived= new TextItem("countryOfArrived","运抵国");
		txtCountryOfArrived.setWidth(120);
		// 境内货源地
		final TextItem txtDomesticSupplyOfGoods= new TextItem("domesticSupplyOfGoods","境内货源地");
		txtDomesticSupplyOfGoods.setWidth(120);
		// 出口日期
		final CustomDateItem txtExportDate= new CustomDateItem("exportDate","出口日期");
		txtExportDate.setWidth(120);
		// 出口口岸
		final TextItem txtExportPort= new TextItem("exportPort","出口口岸");
		txtExportPort.setWidth(120);
		// 生产厂家
		final TextItem txtManufacturer= new TextItem("manufacturer","生产厂家");
		txtManufacturer.setWidth(120);
		// 发货单位
		final TextItem txtSendOutGoodsUnit= new TextItem("sendOutGoodsUnit","发货单位");
		txtSendOutGoodsUnit.setWidth(120);
		// 结汇方式
		final TextItem txtSettlementWay= new TextItem("settlementWay","结汇方式");
		txtSettlementWay.setWidth(120);
		// 卸货港
		final TextItem txtUnloadingPort= new TextItem("unloadingPort","卸货港");
		txtUnloadingPort.setWidth(120);
		// 报关ID
		final HiddenItem txtCustomsID = new HiddenItem("id");
		
		// 合同信息
		final DynamicForm headForm = new DynamicForm();
		headForm.setGroupTitle("<font color='blue'>合同信息</font>");
		headForm.setIsGroup(true);
		headForm.setDataSource(listGrid.getDataSource());
		headForm.setMargin(5);
		headForm.setWidth(880);
		headForm.setNumCols(8);
		headForm.setColWidths(100, 120, 100, 120, 100, 120, 100, 120);
		headForm.setFields(txtPreEntryNumber
				,txtOrderNumber
				,txtContractNumber
				,txtTransportationCode
				,txtTradeMethods
				,txtDeliveryType
				);
		
		txtPreEntryNumber.setDisabled(true);
		txtOrderNumber.setDisabled(true);
		txtContractNumber.setDisabled(true);
		txtTransportationCode.setDisabled(true);
		txtTradeMethods.setDisabled(true);
		txtDeliveryType.setDisabled(true);
		
		// 报关信息
		final DynamicForm secondForm = new DynamicForm();
		secondForm.setGroupTitle("<font color='blue'>报关信息</font>");
		secondForm.setIsGroup(true);
		secondForm.setMargin(5);
		secondForm.setDataSource(listGrid.getDataSource());
		secondForm.setWidth(880);
		secondForm.setNumCols(8);
		secondForm.setColWidths(100, 120, 100, 120, 100, 120, 100, 120);
		secondForm.setFields(
						txtMainWayBill
						,txtHouseWayBill
						,txtOrderID
						,txtCustomsID
						,txtApprovalNumber
						,txtLicenseNumber
						,txtCustomsRecordationNumber
						,txtConsignee
						,txtContainerNumber
						,txtCustomsNumber
						,txtBusinessUnitField
						,txtDeclarationDate
						,txtFreight
						,txtInsurancePremium
						,txtMiscellaneousFees
						,txtGrossWeight
						,txtNetWeight
						,selClearanceAgent
						,txtLevyProportion
						,txtNumberOfPackage
						,txtPackageType
						,txtCountryOfArrived
						,txtDomesticSupplyOfGoods
						,txtExportPort
						,txtExportDate
						,txtUnloadingPort
						,txtPurpose
						,txtRemarkText
						);
		
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			headForm.editRecord(record);
			secondForm.editRecord(record);
		}
		
		// 出口备件项
		final ExportCustomsDeclarationItemsListgrid exportCustomsDeclarationItemsListgrid = new ExportCustomsDeclarationItemsListgrid();
		exportCustomsDeclarationItemsListgrid.setWidth(880);
		exportCustomsDeclarationItemsListgrid.setHeight(180);
		exportCustomsDeclarationItemsListgrid.setMargin(5);
		exportCustomsDeclarationItemsListgrid.setAutoSaveEdits(false);
		exportCustomsDeclarationItemsListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取明细数据
		String modeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportCustomsDeclaration";
		String dsName = "exportCustomsDeclarationItems_dataSource";
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						exportCustomsDeclarationItemsListgrid.setDataSource(dataSource);
						
						exportCustomsDeclarationItemsListgrid.drawExportCustomsDeclarationItemsListgrid();
						if (isView == true) {
							exportCustomsDeclarationItemsListgrid.setCanEdit(false);	
						} else {
							exportCustomsDeclarationItemsListgrid.setCanEdit(true);
						}
						exportCustomsDeclarationItemsListgrid.setEditEvent(ListGridEditEvent.CLICK);
						if (txtCustomsID.getValue() != null)
						{
							exportCustomsDeclarationItemsListgrid.fetchData(new Criteria("customsID", "" + txtCustomsID.getValue().toString()));
						}
					}
				});

//		// 新增按钮
//		final IButton btnNew = new IButton();
//		btnNew.setTitle("新增行");
//		btnNew.setWidth(65);
//		btnNew.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				exportCustomsDeclarationItemsListgrid.startEditingNew();
//			}
//		});

		// 保存按钮
		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// 设置明细数据
				ListGridRecord[] detailRecord = new ListGridRecord[exportCustomsDeclarationItemsListgrid.getTotalRows()];
				for (int i = 0; i < exportCustomsDeclarationItemsListgrid.getTotalRows(); i++)
				{
					detailRecord[i] = (ListGridRecord)exportCustomsDeclarationItemsListgrid.getEditedRecord(i);
				}
				secondForm.setValue("exportCustomsDeclarationItems", detailRecord);
				
				secondForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("保存成功！");
					}
				});
			}
		});

		final IButton cancelButton = new IButton();
		cancelButton.setTitle("取消");
		cancelButton.setWidth(65);
		cancelButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		final BtnsHLayout layoutHeadBtn = new BtnsHLayout(); 
		layoutHeadBtn.addMember(saveButton);		
		layoutHeadBtn.addMember(cancelButton);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.setMembersMargin(5);
		layout.addMember(headForm);
		layout.addMember(secondForm);
		layout.addMember(exportCustomsDeclarationItemsListgrid);
		layout.addMember(layoutHeadBtn);
		
		if (isView == true) {
			Utils.setReadOnlyForm(headForm);
			Utils.setReadOnlyForm(secondForm);
			saveButton.setVisible(false);
			cancelButton.setVisible(false);
		}
		
		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}