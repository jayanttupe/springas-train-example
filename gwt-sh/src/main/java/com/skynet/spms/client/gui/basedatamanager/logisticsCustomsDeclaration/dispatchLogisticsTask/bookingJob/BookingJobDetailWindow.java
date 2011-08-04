package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningListgrid;
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
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class BookingJobDetailWindow extends Window {
	
	public BookingJobDetailWindow(String windowTitle,
			boolean isMax,
			final Rectangle rect,
			final ListGrid listGrid,
			String iconUrl,
			final Boolean updateFlg,
			final Boolean isView) {
		final Window objWindow = this;
		setWidth(1095);
		setHeight(650);

		// 指令ID
		final HiddenItem txtOrderID = new HiddenItem("orderID");
		// 订舱ID
		final HiddenItem txtBookingJobID = new HiddenItem("id");
		// 订舱工作编号
		final TextItem txtBookingJobNumber = new TextItem("bookingJobNumber", "订舱工作编号");
		txtBookingJobNumber.setDisabled(true);
		txtBookingJobNumber.setWidth(140);
		// 物流任务编号
		final TextItem txtLogisticsTasksNumber = new TextItem("logisticsTasksNumber", "物流任务编号");
		txtLogisticsTasksNumber.setDisabled(true);
		txtLogisticsTasksNumber.setWidth(140);
		// 委托书编号
		final TextItem txtForwardingOrderNumber = new TextItem("forwardingOrderNumber", "委托书编号");
		txtForwardingOrderNumber.setWidth(140);
		// 目的口岸(港)
		final TextItem txtPortOfDestinat = new TextItem("portOfDestinat", "目的口岸(港)");
		txtPortOfDestinat.setWidth(140);
		// 起运口岸(港)
		final TextItem txtPortOfShipment = new TextItem("portOfShipment", "起运口岸(港)");
		// 货物箱(件)总数
		final TextItem txtBillOfLadingContainerCount = new TextItem("billOfLadingContainerCount", "货物箱(件)总数");
		txtBillOfLadingContainerCount.setWidth(140);
		txtPortOfShipment.setWidth(140);
		// 交货日期
		final CustomDateItem txtDeliveryDate = new CustomDateItem("pickupDeliveryOrder.deliveryDate", "交货日期");
		txtDeliveryDate.setWidth(140);
		// 起运日期
		final CustomDateItem txtShippedDate = new CustomDateItem("pickupDeliveryOrder.shippedDate", "起运日期");
		txtShippedDate.setWidth(140);
		// 到货日期
		final CustomDateItem txtArriveDate = new CustomDateItem("pickupDeliveryOrder.arrivalDate", "到货日期");
		txtArriveDate.setWidth(140);
		// 提货日期
		final CustomDateItem txtPickupDate = new CustomDateItem("pickupDeliveryOrder.pickupDate", "提货日期");
		txtPickupDate.setWidth(140);
		// 运输申报价值
		final TextItem txtDeclaredValueForCarriage = new TextItem("declaredValueForCarriage", "运输申报价值");
		txtDeclaredValueForCarriage.setWidth(140);
		// 运输班次(航班号/车号/船号)
		final TextItem txtNumberOfRuns = new TextItem("numberOfRuns", "运输班次(航班号/车号/船号)");
		txtNumberOfRuns.setWidth(140);
		// 运输保险价值
		final TextItem txtValueOfInsurance = new TextItem("valueOfInsurance", "运输保险价值");
		txtValueOfInsurance.setWidth(140);
		// 特别要求
		final TextItem txtSpecialRequests = new TextItem("specialRequests", "特别要求");
		txtSpecialRequests.setWidth(140);
		// 国内/国际委托书
		final RadioGroupItem radioInlandOrInternational = new RadioGroupItem();  
        radioInlandOrInternational.setName("inlandOrInternational");  
        radioInlandOrInternational.setVertical(false);  
        radioInlandOrInternational.setValueMap("国内", "国外");  
        radioInlandOrInternational.setRedrawOnChange(true);
        radioInlandOrInternational.setTitle("委托书");
        radioInlandOrInternational.setWidth(140);
        radioInlandOrInternational.setDefaultValue("国内");
		// 原产地证书
		final TextItem txtCertificateOfOrigin = new TextItem("certificateOfOrigin", "原产地证书");
		txtCertificateOfOrigin.setWidth(140);
		// 原产地
		final TextItem txtCountryOfOrigin = new TextItem("countryOfOrigin", "原产地");
		txtCountryOfOrigin.setWidth(140);
		// 海关申报价值
		final TextItem txtDeclaredValueForCustoms = new TextItem("declaredValueForCustoms", "海关申报价值");
		txtDeclaredValueForCustoms.setWidth(140);
		// 产品许可证号
		final TextItem txtProductsPermitLicense = new TextItem("productsPermitLicense", "产品许可证号");
		txtProductsPermitLicense.setWidth(140);
		// 币种
		final SelectItem txtInternationalCurrencyCode = new SelectItem("internationalCurrencyCode", "币种");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode", txtInternationalCurrencyCode);
		txtInternationalCurrencyCode.setWidth(140);
		
		radioInlandOrInternational.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				if (event.getValue().equals("国外")) {
					txtCertificateOfOrigin.setDisabled(false);
					txtCountryOfOrigin.setDisabled(false);
					txtDeclaredValueForCustoms.setDisabled(false);
					txtProductsPermitLicense.setDisabled(false);
					txtInternationalCurrencyCode.setDisabled(false);
				} else {
					txtCertificateOfOrigin.setDisabled(true);
					txtCountryOfOrigin.setDisabled(true);
					txtDeclaredValueForCustoms.setDisabled(true);
					txtProductsPermitLicense.setDisabled(true);
					txtInternationalCurrencyCode.setDisabled(true);

					txtCertificateOfOrigin.setValue("");
					txtCountryOfOrigin.setValue("");
					txtDeclaredValueForCustoms.setValue("");
					txtProductsPermitLicense.setValue("");
					txtInternationalCurrencyCode.setValue("");
				}
			}
		});

		// 我方运代商
		final SelectItem cmbCarrier = new SelectItem("pickupDeliveryOrder.carrierID", "我方运代商");
		cmbCarrier.setWidth(140);
		ListGridField carrierCodeField = new ListGridField("carrierName", "运代商代码");
		ListGridField carrierNameField = new ListGridField("enterpriseName_zh", "运代商名称");
		cmbCarrier.setPickListFields(carrierCodeField, carrierNameField);
		// 获取运代商数据
		String carrierModeName = "organization.enterprise.carrier";
		String carrierDSName = "carrier_dataSource";
		DataSourceTool stockRoomDST = new DataSourceTool();
		stockRoomDST.onCreateDataSource(carrierModeName, carrierDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						cmbCarrier.clearValue();
						cmbCarrier.setOptionDataSource(dataSource);

						cmbCarrier.setValueField("id");
						cmbCarrier.setDisplayField("enterpriseName_zh");

						if (updateFlg == true)
						{
							final Record record = listGrid.getSelectedRecord();
							cmbCarrier.setValue(record.getAttribute("pickupDeliveryOrder.carrierID"));
						}
					}
				});
		// 报关代理商
		final HiddenItem txtClearanceAgent = new HiddenItem("pickupDeliveryOrder.clearanceAgent");
		txtClearanceAgent.setWidth(140);
		// 用户指定发运方式代码
		final HiddenItem txtSpecifiedShippingMethodCode = new HiddenItem("pickupDeliveryOrder.specifiedShippingMethodCode");
		txtSpecifiedShippingMethodCode.setWidth(140);
		// 贸易方式
		final SelectItem txtTradeMethods  = new SelectItem("pickupDeliveryOrder.tradeMethods", "贸易方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.TradeMethods", txtTradeMethods);
		txtTradeMethods.setWidth(140);
		txtTradeMethods.setDisabled(true);
		// 交货方式
		final SelectItem txtDeliveryType = new SelectItem("pickupDeliveryOrder.deliveryType", "交货方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.DeliveryType", txtDeliveryType);
		txtDeliveryType.setWidth(140);
		txtDeliveryType.setDisabled(true);
		// 运输方式
		final SelectItem txtTransportationCode = new SelectItem("pickupDeliveryOrder.transportationCode", "运输方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.TransportationCode", txtTransportationCode);
		txtTransportationCode.setWidth(140);
		txtTransportationCode.setDisabled(true);
		// 运代费用支付方式
		final SelectItem txtForwarderPaymentType = new SelectItem("pickupDeliveryOrder.forwarderPaymentType", "运代费用支付方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.ForwarderPaymentType", txtForwarderPaymentType);
		txtForwarderPaymentType.setWidth(140);

		// 收货人
		final TextItem txtConsignee = new TextItem("pickupDeliveryOrder.consignee", "收货人");
		txtConsignee.setColSpan(2);
		txtConsignee.setWidth(280);
		// 收货地址及详细地址
		final TextItem txtAddressOfConsignee = new TextItem("pickupDeliveryOrder.addressOfConsignee", "收货地址及详细地址");
		txtAddressOfConsignee.setColSpan(2);
		txtAddressOfConsignee.setWidth(280);
		// 收货联系人
		final TextItem txtCompanyOfConsignee = new TextItem("pickupDeliveryOrder.companyOfConsignee", "收货联系人");
		txtCompanyOfConsignee.setColSpan(2);
		txtCompanyOfConsignee.setWidth(280);
		// 收货人联系方式
		final TextAreaItem txtTelephoneOfConsignee = new TextAreaItem("pickupDeliveryOrder.telephoneOfConsignee", "收货人联系方式");
		txtTelephoneOfConsignee.setColSpan(2);
		txtTelephoneOfConsignee.setHeight(60);
		txtTelephoneOfConsignee.setWidth(280);
		// 发货人
		final TextItem txtShipper = new TextItem("pickupDeliveryOrder.shipper", "发货人");
		txtShipper.setColSpan(2);
		txtShipper.setWidth(280);
		// 发货地址及详细地址
		final TextItem txtAddressOfShipper = new TextItem("pickupDeliveryOrder.addressOfShipper", "发货地址及详细地址");
		txtAddressOfShipper.setWidth(280);
		txtAddressOfShipper.setColSpan(2);
		// 发货联系人
		final TextItem txtCompanyOfShipper = new TextItem("pickupDeliveryOrder.companyOfShipper", "发货联系人");
		txtCompanyOfShipper.setColSpan(2);
		txtCompanyOfShipper.setWidth(280);
		// 发货人联系方式
		final TextAreaItem txtTelephonOfShipper = new TextAreaItem("pickupDeliveryOrder.telephonOfShipper", "发货人联系方式");
		txtTelephonOfShipper.setColSpan(2);
		txtTelephonOfShipper.setHeight(60);
		txtTelephonOfShipper.setWidth(280);
		// 唛头
		final TextAreaItem txtConsigneeMark = new TextAreaItem("pickupDeliveryOrder.consigneeMark", "唛头");
		txtConsigneeMark.setColSpan(1);
		txtConsigneeMark.setRowSpan(4);
		txtConsigneeMark.setWidth(140);
		txtConsigneeMark.setHeight(140);
		
		// 订舱单状态
		final SelectItem txtBookingMemoStatus = new SelectItem("bookingStatus", "订舱单状态");
		txtBookingMemoStatus.setValueMap("未分配", "处理中", "处理结束");
		txtBookingMemoStatus.setDisabled(true);
		txtBookingMemoStatus.setWidth(140);
		
		// 基本信息
		final DynamicForm headForm = new DynamicForm();
		headForm.setGroupTitle("<font color='blue'>基本信息</font>");
		headForm.setIsGroup(true);
		headForm.setDataSource(listGrid.getDataSource());
		headForm.setWidth(975);
		headForm.setMargin(5);
		headForm.setNumCols(8);
		headForm.setColWidths(100, 140, 100, 140, 100, 140, 100, 140);
		headForm.setFields(txtOrderID,
							txtBookingJobID,
							txtBookingJobNumber,
							txtLogisticsTasksNumber,
							txtClearanceAgent,
							txtSpecifiedShippingMethodCode,
							txtTradeMethods ,
							txtDeliveryType,
							txtTransportationCode,
							txtBookingMemoStatus
							);

		// 委托书信息
		final DynamicForm freightAttorneyForm = new DynamicForm();
		freightAttorneyForm.setGroupTitle("<font color='blue'>委托书详细信息</font>");
		freightAttorneyForm.setIsGroup(true);
		freightAttorneyForm.setDataSource(listGrid.getDataSource());
		freightAttorneyForm.setWidth(975);
		freightAttorneyForm.setNumCols(8);
		freightAttorneyForm.setMargin(5);
		freightAttorneyForm.setColWidths(100, 140, 100, 140, 100, 140, 100, 140);
		freightAttorneyForm.setFields(txtForwardingOrderNumber
				,txtPortOfDestinat
				,txtPortOfShipment
				,txtBillOfLadingContainerCount
				,txtDeliveryDate
				,txtShippedDate
				,txtArriveDate
				,txtPickupDate
				,txtDeclaredValueForCarriage
				,txtNumberOfRuns
				,txtValueOfInsurance
				,cmbCarrier
				,txtForwarderPaymentType
				,txtSpecialRequests
				,radioInlandOrInternational
				,txtCertificateOfOrigin
				,txtProductsPermitLicense
				,txtCountryOfOrigin
				,txtDeclaredValueForCustoms
				,txtInternationalCurrencyCode
				);
		
		// 地址信息
		final DynamicForm shipperForm = new DynamicForm();
		shipperForm.setGroupTitle("<font color='blue'>地址信息</font>");
		shipperForm.setIsGroup(true);
		shipperForm.setDataSource(listGrid.getDataSource());
		shipperForm.setWidth(967);
		shipperForm.setNumCols(8);
		shipperForm.setColWidths(100, 140, 140, 100, 140, 140, 60, 140);
		shipperForm.setFields(
				txtShipper
				,txtConsignee
				,txtConsigneeMark
				,txtAddressOfShipper
				,txtAddressOfConsignee
				,txtCompanyOfShipper
				,txtCompanyOfConsignee
				,txtTelephonOfShipper
				,txtTelephoneOfConsignee
				);
		
		
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			headForm.editRecord(record);
			freightAttorneyForm.editRecord(record);
			shipperForm.editRecord(record);

			if (radioInlandOrInternational.getValue().equals("国外")) {
				txtCertificateOfOrigin.setDisabled(false);
				txtCountryOfOrigin.setDisabled(false);
				txtDeclaredValueForCustoms.setDisabled(false);
				txtProductsPermitLicense.setDisabled(false);
				txtInternationalCurrencyCode.setDisabled(false);	
			} else {
				txtCertificateOfOrigin.setDisabled(true);
				txtCountryOfOrigin.setDisabled(true);
				txtDeclaredValueForCustoms.setDisabled(true);
				txtProductsPermitLicense.setDisabled(true);
				txtInternationalCurrencyCode.setDisabled(true);
			}
		}

		// 指令明细箱信息
		final PickupDeliveryVanningListgrid pickupDeliveryVanningListgrid = new PickupDeliveryVanningListgrid();
		pickupDeliveryVanningListgrid.setWidth(975);
		pickupDeliveryVanningListgrid.setHeight(155);
		pickupDeliveryVanningListgrid.setMargin(5);
		pickupDeliveryVanningListgrid.setAutoSaveEdits(false);
		pickupDeliveryVanningListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取指令明细箱信息
		String pickupVanningModeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.bookingJob";
		String pickupVanningDsName = "bookingJobDetails_dataSource";
		DataSourceTool pickupVanningDST = new DataSourceTool();
		pickupVanningDST.onCreateDataSource(pickupVanningModeName, pickupVanningDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickupDeliveryVanningListgrid.setDataSource(dataSource);
						
						pickupDeliveryVanningListgrid.drawPickupDeliveryVanningListgrid();
						pickupDeliveryVanningListgrid.setEditEvent(ListGridEditEvent.CLICK);
						pickupDeliveryVanningListgrid.setCanEdit(false);
						if (txtBookingJobID.getValue() != null)
						{
							pickupDeliveryVanningListgrid.fetchData(new Criteria("bookingJobID", "" + txtBookingJobID.getValue().toString()));
						}
					}
				});
		
		// 新增按钮
		final IButton btnNew = new IButton();
		btnNew.setTitle("移入");
		btnNew.setWidth(65);
		btnNew.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
			}
		});

		// 删除按钮
		final IButton btnDelete = new IButton();
		btnDelete.setTitle("移出");
		btnDelete.setWidth(65);
		btnDelete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				pickupDeliveryVanningListgrid.removeSelectedData();
			}
		});

		// 保存按钮
		final IButton btnSave = new IButton();
		btnSave.setTitle("保存");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				FormItem[] freightAttorneyFormItems = freightAttorneyForm.getFields();
				FormItem[] shipperFormItems = shipperForm.getFields();
				for (FormItem formItem : freightAttorneyFormItems)
				{
					if (formItem.getValue() != null) {
						headForm.setValue(formItem.getFieldName(), "" + formItem.getValue().toString());	
					}
				}
				for (FormItem formItem : shipperFormItems)
				{
					if (formItem.getValue() != null) {
						headForm.setValue(formItem.getFieldName(), "" + formItem.getValue().toString());	
					}
				}
				headForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("保存成功！");
					}
				});
			}
		});

		// 返回按钮
		final IButton btnReturn = new IButton();
		btnReturn.setTitle("返回");
		btnReturn.setWidth(65);
		btnReturn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		final HLayout headBtnLayout = new BtnsHLayout(); 
		headBtnLayout.addMember(btnSave);
		headBtnLayout.addMember(btnReturn);

		final BtnsHLayout bototmBtnLayout = new BtnsHLayout(); 
		bototmBtnLayout.addMember(btnNew);
		bototmBtnLayout.addMember(btnDelete);

		final HLayout shipperConsigneeLayout = new HLayout();
		shipperConsigneeLayout.setMargin(5);
		shipperConsigneeLayout.setMembersMargin(5);
		shipperConsigneeLayout.addMember(shipperForm);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(headForm);
		layout.addMember(freightAttorneyForm);
		layout.addMember(shipperConsigneeLayout);
		layout.addMember(headBtnLayout);
		layout.addMember(pickupDeliveryVanningListgrid);

		if (isView == true) {
			Utils.setReadOnlyForm(headForm);
			Utils.setReadOnlyForm(freightAttorneyForm);
			Utils.setReadOnlyForm(shipperForm);
			btnSave.setVisible(false);
			btnReturn.setVisible(false);
		}
		SetWindow.SetWindowLayout(windowTitle
				,true
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}
