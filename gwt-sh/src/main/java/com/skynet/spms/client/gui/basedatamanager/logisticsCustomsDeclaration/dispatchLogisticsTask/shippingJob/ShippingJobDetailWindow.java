package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob;

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
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ShippingJobDetailWindow extends Window {

	public ShippingJobDetailWindow(String windowTitle, 
			boolean isMax,
			final Rectangle rect,
			final ShippingJobListgrid listGrid,
			String iconUrl,
			final Boolean updateFlg,
			final Boolean isView) {
		final Window objWindow = this;
		objWindow.setWidth(1095);
		objWindow.setHeight(585);

		// 起运工作ID
		final HiddenItem txtShippingJobID = new HiddenItem("id");
		// 指令ID
		final HiddenItem txtOrderID = new HiddenItem("orderID");
		// 指令编号
		final TextItem txtOrderNumber = new TextItem("orderNumber", "指令编号");
		txtOrderNumber.setDisabled(true);
		txtOrderNumber.setWidth(140);
		// 起运工作编号
		final TextItem txtShippingJobNumber = new TextItem("shippingJobNumber", "起运工作编号");
		txtShippingJobNumber.setDisabled(true);
		txtShippingJobNumber.setWidth(140);
		// 件数
		final TextItem txtBillOfLadingContainerCount = new TextItem("billOfLadingContainerCount", "件数");
		txtBillOfLadingContainerCount.setWidth(140);
		// 总重量(千克)
		final TextItem txtBillOfLadingWeight = new TextItem("billOfLadingWeight", "总重量(千克)");
		txtBillOfLadingWeight.setWidth(140);
		// 运费
		final TextItem txtCharges = new TextItem("charges", "运费");
		txtCharges.setWidth(140);
		// 合同编号
		final TextItem txtContractNumber = new TextItem("contractNumber", "合同编号");
		txtContractNumber.setWidth(140);
		// 分运单号
		final TextItem txtHouseWayBill = new TextItem("pickupDeliveryOrder.houseWayBill", "分运单号");
		txtHouseWayBill.setWidth(140);
		// 主运单号
		final TextItem txtMainWayBill = new TextItem("pickupDeliveryOrder.mainWayBill", "主运单号");
		txtMainWayBill.setWidth(140);
		// 运输班次
		final TextItem txtNumberOfRuns = new TextItem("numberOfRuns", "运输班次");
		txtNumberOfRuns.setWidth(140);
		// 目的口岸(港)
		final TextItem txtPortOfDestinat = new TextItem("portOfDestinat", "目的口岸(港)");
		txtPortOfDestinat.setWidth(140);
		// 起运口岸(港)
		final TextItem txtPortOfShipment = new TextItem("portOfShipment", "起运口岸(港)");
		txtPortOfShipment.setWidth(140);
		// 起运日期
		final CustomDateItem txtShipmentDate = new CustomDateItem("shipmentDate", "起运日期");
		txtShipmentDate.setWidth(140);
		// 到达日期
		final CustomDateItem txtEstimatedTimeOfArrival = new CustomDateItem("estimatedTimeOfArrival", "到达日期");
		txtEstimatedTimeOfArrival.setWidth(140);
		// 中转站
		final TextItem txtTransferStation = new TextItem("transferStation", "中转站");
		txtTransferStation.setWidth(140);
		// 总体积(立方米)
		final TextItem txtVolume = new TextItem("volume", "总体积(立方米)");
		txtVolume.setWidth(140);
		// 备注
		final TextItem txtRemarkText = new TextItem("remarkText", "备注");
		txtRemarkText.setColSpan(3);
		txtRemarkText.setWidth(380);
		// 贸易方式
		final SelectItem txtTradeMethods = new SelectItem("pickupDeliveryOrder.tradeMethods", "贸易方式");
		txtTradeMethods.setWidth(140);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.TradeMethods", txtTradeMethods);
		// 运代费用支付方式
		final SelectItem txtForwarderPaymentType = new SelectItem("pickupDeliveryOrder.forwarderPaymentType", "运代费用支付方式");
		txtForwarderPaymentType.setWidth(140);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.ForwarderPaymentType", txtForwarderPaymentType);
		// 运输方式
		final SelectItem txtTransportationCode = new SelectItem("pickupDeliveryOrder.transportationCode", "运输方式");
		txtTransportationCode.setWidth(140);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.TransportationCode", txtTransportationCode);
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
		// 币种
		final SelectItem txtInternationalCurrencyCode = new SelectItem("pickupDeliveryOrder.internationalCurrencyCode", "币种");
		txtInternationalCurrencyCode.setWidth(140);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.csdd.i.InternationalCurrencyCode", txtInternationalCurrencyCode);

		// 合同信息
		final DynamicForm headForm = new DynamicForm();
		headForm.setGroupTitle("<font color='blue'>合同信息</font>");
		headForm.setIsGroup(true);
		headForm.setDataSource(listGrid.getDataSource());
		headForm.setMargin(5);
		headForm.setWidth(975);
		headForm.setNumCols(8);
		headForm.setColWidths(100, 140, 100, 140, 100, 140, 100, 140);
		headForm.setFields(txtShippingJobID
				,txtOrderID
				,txtOrderNumber
				,txtShippingJobNumber
				,txtContractNumber
				,txtTradeMethods
				,txtForwarderPaymentType
				,txtTransportationCode
				);
		
		// 合同信息不可编辑
		txtOrderNumber.setDisabled(true);
		txtShippingJobNumber.setDisabled(true);
		txtContractNumber.setDisabled(true);
		txtTradeMethods.setDisabled(true);
		txtForwarderPaymentType.setDisabled(true);
		txtTransportationCode.setDisabled(true);
		
		// 运单信息
		final DynamicForm secondForm = new DynamicForm();
		secondForm.setGroupTitle("<font color='blue'>运单信息</font>");
		secondForm.setIsGroup(true);
		secondForm.setDataSource(listGrid.getDataSource());
		secondForm.setMargin(5);
		secondForm.setWidth(975);
		secondForm.setNumCols(8);
		secondForm.setColWidths(100, 140, 100, 140, 100, 140, 100, 140);
		secondForm.setFields(txtMainWayBill
				,txtHouseWayBill
				,txtPortOfShipment
				,txtPortOfDestinat
				,txtBillOfLadingContainerCount
				,txtBillOfLadingWeight
				,txtCharges
				,txtInternationalCurrencyCode
				,txtNumberOfRuns
				,txtShipmentDate
				,txtEstimatedTimeOfArrival
				,cmbCarrier
				,txtTransferStation
				,txtVolume
				,txtRemarkText
				);
		
		// 地址信息
		final DynamicForm shipperForm = new DynamicForm();
		shipperForm.setGroupTitle("<font color='blue'>地址信息</font>");
		shipperForm.setIsGroup(true);
		shipperForm.setDataSource(listGrid.getDataSource());
		shipperForm.setWidth(966);
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
			secondForm.editRecord(record);
			shipperForm.editRecord(record);
		}

		
		// 指令明细箱信息
		final PickupDeliveryVanningListgrid pickupDeliveryVanningListgrid = new PickupDeliveryVanningListgrid();
		pickupDeliveryVanningListgrid.setWidth(975);
		pickupDeliveryVanningListgrid.setHeight(180);
		pickupDeliveryVanningListgrid.setMargin(5);
		pickupDeliveryVanningListgrid.setAutoSaveEdits(false);
		pickupDeliveryVanningListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取指令明细箱信息
		String pickupVanningModeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.shippingJob";
		String pickupVanningDsName = "shippingJobDetails_dataSource";
		DataSourceTool pickupVanningDST = new DataSourceTool();
		pickupVanningDST.onCreateDataSource(pickupVanningModeName, pickupVanningDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickupDeliveryVanningListgrid.setDataSource(dataSource);
						
						pickupDeliveryVanningListgrid.drawPickupDeliveryVanningListgrid();
						pickupDeliveryVanningListgrid.setEditEvent(ListGridEditEvent.CLICK);
						pickupDeliveryVanningListgrid.setCanEdit(false);
						if (txtShippingJobID.getValue() != null)
						{
							pickupDeliveryVanningListgrid.fetchData(new Criteria("shippingJobID", "" + txtShippingJobID.getValue()));
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
				FormItem[] shipperFormItems = shipperForm.getFields();
				for (FormItem formItem : shipperFormItems)
				{
					if (formItem.getValue() != null) {
						headForm.setValue(formItem.getFieldName(), "" + formItem.getValue().toString());	
					}
				}
				secondForm.saveData(new DSCallback() {
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

		final BtnsHLayout headBtnLayout = new BtnsHLayout(); 
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
		layout.addMember(secondForm);
		layout.addMember(shipperConsigneeLayout);
		layout.addMember(headBtnLayout);
		layout.addMember(pickupDeliveryVanningListgrid);

		if (isView == true) {
			Utils.setReadOnlyForm(headForm);
			Utils.setReadOnlyForm(secondForm);
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