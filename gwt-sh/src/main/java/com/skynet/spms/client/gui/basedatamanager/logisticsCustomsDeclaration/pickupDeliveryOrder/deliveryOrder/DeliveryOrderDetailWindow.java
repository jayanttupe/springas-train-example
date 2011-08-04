package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.deliveryOrder;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.enums.LogisticsTaskJobType;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryTaskAssignListgrid;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryVanningListgrid;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.Side;
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
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class DeliveryOrderDetailWindow extends Window {

	/**
	 * 
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param headGrid
	 * @param detailGrid
	 * @param iconUrl
	 * @param updateFlg
	 * @param selUser
	 * @param isFromDispatch
	 * @param isView
	 */
	public DeliveryOrderDetailWindow(String windowTitle,
			boolean isMax,
			final Rectangle rect,
			final ListGrid headGrid,
			final ListGrid detailGrid,
			String iconUrl,
			final Boolean updateFlg,
			final SelectItem selUser,
			final Boolean isFromDispatch,
			final Boolean isView) {
		final Window objWindow = this;
		setWidth(1095);
		setHeight(500);

		// 物流信息
		final DynamicForm secondForm = new DynamicForm();
		secondForm.setDataSource(headGrid.getDataSource());
		secondForm.setWidth(870);
		secondForm.setNumCols(6);
		secondForm.setMargin(5);
		secondForm.setColWidths(110, 180, 110, 180, 110, 180);
		// 合同信息
		final DynamicForm headForm = new DynamicForm();
		headForm.setDataSource(headGrid.getDataSource());
		headForm.setWidth(955);
		headForm.setNumCols(8);
		headForm.setMargin(5);
		headForm.setColWidths(80, 140, 100, 140, 100, 140, 70, 170);
		// 地址信息
		final DynamicForm shipperForm = new DynamicForm();
		shipperForm.setDataSource(headGrid.getDataSource());
		shipperForm.setWidth(950);
		shipperForm.setNumCols(6);
		shipperForm.setColWidths(100, 280, 100, 280, 40, 150);
		
		// 指令ID
		final HiddenItem txtOrderID = new HiddenItem("id");
		// 指令编号
		final TextItem txtOrderNumber = new TextItem("orderNumber", "指令编号");
		txtOrderNumber.setWidth(140);
		// 业务类型
		final SelectItem txtBusinessType = new SelectItem("businessType", "业务类型");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.csdd.b.BusinessType", txtBusinessType);
		txtBusinessType.setWidth(140);
		// 指令下达人员
		final TextItem txtOrderedBy = new TextItem("orderedBy", "指令下达人员");
		txtOrderedBy.setWidth(140);
		// 指令描述信息
		final TextItem txtDescription = new TextItem("description", "指令描述信息");
		txtDescription.setColSpan(7);
		txtDescription.setWidth(830);
		txtDescription.setHeight(30);
		// 合同指定运代商
		final TextItem txtAppointForwarder = new TextItem("appointForwarder", "合同指定运代");
		txtAppointForwarder.setWidth(140);
		// 合同指定运代商联系方式
		final TextItem txtAppointForwarderContact = new TextItem("appointForwarderContact", "合同指定运代联系方式");
		txtAppointForwarderContact.setWidth(140);
		// 合同指定运代商联系人
		final TextItem txtAppointForwarderLinkman = new TextItem("appointForwarderLinkman", "合同指定运代联系人");
		txtAppointForwarderLinkman.setWidth(140);
		// 合同编号
		final TextItem txtContractNumber = new TextItem("contractNumber", "合同编号");
		txtContractNumber.setWidth(140);
		// 合同交货期
		final TextItem txtContractLeadTime = new TextItem("contractLeadTime", "合同交货期");
		txtContractLeadTime.setWidth(140);
		
		// 物流任务编号
		final TextItem txtLogisticsTasksNumber = new TextItem("logisticsTasksNumber", "物流任务编号");
		txtLogisticsTasksNumber.setWidth(150);
		// 是否买方指定运代
		final RadioGroupItem rdoIsAppointFreightAgent = new RadioGroupItem();
		rdoIsAppointFreightAgent.setName("isAppointFreightAgent");  
		rdoIsAppointFreightAgent.setVertical(false);  
        rdoIsAppointFreightAgent.setValueMap("是", "否");  
        rdoIsAppointFreightAgent.setRedrawOnChange(true);
        rdoIsAppointFreightAgent.setTitle("买方指定运代");
        rdoIsAppointFreightAgent.setWidth(150);
        rdoIsAppointFreightAgent.setDefaultValue("是");
		// 是否保税库发货
		final RadioGroupItem rdoIsBonded = new RadioGroupItem();
		rdoIsBonded.setName("isBonded");  
		rdoIsBonded.setVertical(false);
		rdoIsBonded.setValueMap("是", "否");  
		rdoIsBonded.setRedrawOnChange(true);
		rdoIsBonded.setTitle("是否保税库发货");
		rdoIsBonded.setWidth(150);
		rdoIsBonded.setDefaultValue("是");
		// 是否依赖客户业务合同
		final RadioGroupItem rdoIsDependCustomerContract = new RadioGroupItem();
		rdoIsDependCustomerContract.setName("isDependCustomerContract");  
		rdoIsDependCustomerContract.setVertical(false);  
		rdoIsDependCustomerContract.setValueMap("是", "否");  
		rdoIsDependCustomerContract.setRedrawOnChange(true);
		rdoIsDependCustomerContract.setTitle("依赖客户业务合同");
		rdoIsDependCustomerContract.setWidth(150);
		rdoIsDependCustomerContract.setDefaultValue("是");
		// 用户指定发运方式
		final SelectItem selSpecifiedShippingMethodCode = new SelectItem("specifiedShippingMethodCode", "用户指定发运方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.csdd.s.SpecifiedShippingMethodCode", selSpecifiedShippingMethodCode);
		selSpecifiedShippingMethodCode.setWidth(150);
		// 我方运代商
		final SelectItem selCarrier = new SelectItem("carrierID", "我方运代商");
		selCarrier.setWidth(150);
		ListGridField carrierCodeField = new ListGridField("carrierName", "运代商代码");
		ListGridField carrierNameField = new ListGridField("enterpriseName_zh", "运代商名称");
		selCarrier.setPickListFields(carrierCodeField, carrierNameField);
		selCarrier.setPickListWidth(220);
		// 获取运代商数据
		String carrierModeName = "organization.enterprise.carrier";
		String carrierDSName = "carrier_dataSource";
		DataSourceTool stockRoomDST = new DataSourceTool();
		stockRoomDST.onCreateDataSource(carrierModeName, carrierDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						selCarrier.clearValue();
						selCarrier.setOptionDataSource(dataSource);

						selCarrier.setValueField("id");
						selCarrier.setDisplayField("enterpriseName_zh");

						if (updateFlg == true)
						{
							final Record record = headGrid.getSelectedRecord();
							selCarrier.setValue(record.getAttribute("carrierID"));
						}
					}
				});
		// 报关代理商
		final SelectItem selClearanceAgent = new SelectItem("clearanceAgent", "报关代理商");
		selClearanceAgent.setWidth(150);
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

						if (updateFlg == true)
						{
							final Record record = headGrid.getSelectedRecord();
							selClearanceAgent.setValue(record.getAttribute("clearanceAgent"));
						}
					}
				});
		// 发货日期
		final CustomDateItem txtDeliveryDate = new CustomDateItem("deliveryDate", "发货日期");
		txtDeliveryDate.setWidth(150);
		// 起运日期
		final CustomDateItem txtShippedDate = new CustomDateItem("shippedDate", "起运日期");
		txtShippedDate.setWidth(150);
		// 报关类型
		final RadioGroupItem rdoCustomsDeclarationType = new RadioGroupItem();
        rdoCustomsDeclarationType.setName("customsDeclarationType");  
        rdoCustomsDeclarationType.setVertical(false);  
        rdoCustomsDeclarationType.setValueMap("不需要", "进口", "出口");  
        rdoCustomsDeclarationType.setRedrawOnChange(true);
        rdoCustomsDeclarationType.setTitle("是否报关/清关");
        rdoCustomsDeclarationType.setWidth(180);
        rdoCustomsDeclarationType.setDefaultValue("不需要");
        
		// 交货方式
		final SelectItem txtDeliveryType = new SelectItem("deliveryType", "交货方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.DeliveryType", txtDeliveryType);
		txtDeliveryType.setWidth(140);
		// 贸易方式
		final SelectItem txtTradeMethods  = new SelectItem("tradeMethods", "贸易方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.TradeMethods", txtTradeMethods);
		txtTradeMethods.setWidth(140);
		// 运输方式
		final SelectItem txtTransportationCode = new SelectItem("transportationCode", "运输方式");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.TransportationCode", txtTransportationCode);
		txtTransportationCode.setWidth(140);
		// 合同优先级
		final SelectItem txtPriority = new SelectItem("priority", "合同优先级");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.Priority", txtPriority);
		txtPriority.setWidth(140);
		// 发货人姓名
		final TextItem txtShipper = new TextItem("shipper", "发货人");
		txtShipper.setWidth(280);
		// 发货地址及详细地址
		final TextItem txtAddressOfShipper = new TextItem("addressOfShipper", "发货地址及详细地址");
		txtAddressOfShipper.setWidth(280);
		// 发货联系人
		final TextItem txtCompanyOfShipper = new TextItem("companyOfShipper", "发货联系人");
		txtCompanyOfShipper.setWidth(280);
		// 发货人联系方式
		final TextAreaItem txtTelephonOfShipper = new TextAreaItem("telephonOfShipper", "发货人联系方式");
		txtTelephonOfShipper.setWidth(280);
		txtTelephonOfShipper.setHeight(60);
		// 收货人姓名
		final TextItem txtConsignee = new TextItem("consignee", "收货人");
		txtConsignee.setWidth(280);
		// 收货地址及详细信息
		final TextItem txtAddressOfConsignee = new TextItem("addressOfConsignee", "收货地址及详细地址");
		txtAddressOfConsignee.setWidth(280);
		// 收货联系人
		final TextItem txtCompanyOfConsignee = new TextItem("companyOfConsignee", "收货联系人");
		txtCompanyOfConsignee.setWidth(280);
		// 收货人联系方式
		final TextAreaItem txtTelephoneOfConsignee = new TextAreaItem("telephoneOfConsignee", "收货人联系方式");
		txtTelephoneOfConsignee.setHeight(60);
		txtTelephoneOfConsignee.setWidth(280);
		// 收货人唛头
		final TextAreaItem txtConsigneeMark  = new TextAreaItem("consigneeMark", "唛头");
		txtConsigneeMark.setWidth(150);
		txtConsigneeMark.setHeight(140);
		txtConsigneeMark.setRowSpan(4);
		// 状态
		final HiddenItem txtStatus = new HiddenItem("status");
		txtStatus.setWidth(140);

		// 合同信息
		headForm.setFields(txtOrderID
				,txtOrderNumber
				,txtContractNumber
				,txtBusinessType
				,txtOrderedBy
				,txtAppointForwarder
				,txtAppointForwarderContact
				,txtAppointForwarderLinkman
				,txtContractLeadTime
				,txtDeliveryType
				,txtTradeMethods
				,txtTransportationCode
				,txtPriority
				,txtStatus
				,txtDescription
				);
		
		// 合同信息内容全部为不可编辑
		txtLogisticsTasksNumber.setDisabled(true);
		txtOrderID.setDisabled(true);
		txtOrderNumber.setDisabled(true);
		txtBusinessType.setDisabled(true);
		txtOrderedBy.setDisabled(true);
		txtDescription.setDisabled(true);
		txtAppointForwarder.setDisabled(true);
		txtAppointForwarderContact.setDisabled(true);
		txtAppointForwarderLinkman.setDisabled(true);
		txtContractNumber.setDisabled(true);
		txtContractLeadTime.setDisabled(true);
		txtTradeMethods.setDisabled(true);
		txtDeliveryType.setDisabled(true);
		txtTransportationCode.setDisabled(true);
		txtPriority.setDisabled(true);

		// 物流信息
		secondForm.setFields(txtLogisticsTasksNumber
							,txtDeliveryDate
							,txtShippedDate
							,selSpecifiedShippingMethodCode
							,selCarrier
							,selClearanceAgent
							,rdoIsAppointFreightAgent
							,rdoIsBonded
							,rdoIsDependCustomerContract
							,rdoCustomsDeclarationType
							);
		
		// 地址信息
		shipperForm.setFields(txtShipper
				,txtConsignee
				,txtConsigneeMark
				,txtTelephonOfShipper
				,txtTelephoneOfConsignee
				,txtCompanyOfShipper
				,txtCompanyOfConsignee
				,txtAddressOfShipper
				,txtAddressOfConsignee
				);

		final TabSet tabSet = new TabSet();  
		tabSet.setTabBarPosition(Side.TOP);
		tabSet.setHeight(190);
		tabSet.setWidth100();

		Tab tab1 = new Tab("物流信息");  
		tab1.setPane(secondForm);
		tabSet.addTab(tab1);

		Tab tab2 = new Tab("合同信息");  
		tab2.setPane(headForm);
		tabSet.addTab(tab2);
   
		Tab tab3 = new Tab("地址信息");  
		tab3.setPane(shipperForm);
		tabSet.addTab(tab3);

		// 更新的时候，设置画面上的值
		if (updateFlg == true)
		{
			final Record record = headGrid.getSelectedRecord();
			headForm.editRecord(record);
			secondForm.editRecord(record);
			shipperForm.editRecord(record);
		}

		// 指令明细箱信息
		final PickupDeliveryVanningListgrid pickupDeliveryVanningListgrid = new PickupDeliveryVanningListgrid();
		pickupDeliveryVanningListgrid.setMargin(5);
		pickupDeliveryVanningListgrid.setWidth100();
		pickupDeliveryVanningListgrid.setHeight(165);
		pickupDeliveryVanningListgrid.setAutoSaveEdits(false);
		pickupDeliveryVanningListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取指令明细箱信息
		String pickupVanningModeName = "logisticsCustomsDeclaration.pickupDeliveryBusiness.pickupOrder";
		String pickupVanningDsName = "pickupDeliveryVanning_dataSource";
		DataSourceTool pickupVanningDST = new DataSourceTool();
		pickupVanningDST.onCreateDataSource(pickupVanningModeName, pickupVanningDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickupDeliveryVanningListgrid.setDataSource(dataSource);
						
						pickupDeliveryVanningListgrid.drawPickupDeliveryVanningListgrid();
						if (isView == true) {
							pickupDeliveryVanningListgrid.setCanEdit(false);	
						} else {
							pickupDeliveryVanningListgrid.setCanEdit(true);
						}
						pickupDeliveryVanningListgrid.getField("pacakgeNumber").setCanEdit(false);
						pickupDeliveryVanningListgrid.getField("packingListNumber").setCanEdit(false);
						if (txtOrderID.getValue() != null)
						{
							pickupDeliveryVanningListgrid.fetchData(new Criteria("orderID", "" + txtOrderID.getValue().toString()));
						}
					}
				});

		// 物流任务分配
		final PickupDeliveryTaskAssignListgrid pickupTaskAssignListgrid = new PickupDeliveryTaskAssignListgrid();
		pickupTaskAssignListgrid.setMargin(5);
		pickupTaskAssignListgrid.setWidth100();
		pickupTaskAssignListgrid.setHeight(175);
		pickupTaskAssignListgrid.setAutoSaveEdits(false);
		pickupTaskAssignListgrid.setShowRecordComponents(true);
		pickupTaskAssignListgrid.setShowRecordComponentsByCell(true);
		pickupTaskAssignListgrid.setSelectionType(SelectionStyle.SINGLE);

		// 获取物流任务分配信息
		String pickupTaskAssignModeName = "logisticsCustomsDeclaration.pickupDeliveryBusiness.pickupOrder";
		String pickupTaskAssignDsName = "pickupDeliveryTaskAssign_dataSource";
		DataSourceTool pickupTaskAssignDST = new DataSourceTool();
		pickupTaskAssignDST.onCreateDataSource(pickupTaskAssignModeName, pickupTaskAssignDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickupTaskAssignListgrid.setDataSource(dataSource);
						
						pickupTaskAssignListgrid.drawPickupDeliveryTaskAssignListgrid(false);
						pickupTaskAssignListgrid.getField("worker").setEditorType(selUser);
						if (isView == true) {
							pickupTaskAssignListgrid.setCanEdit(false);	
						} else {
							pickupTaskAssignListgrid.setCanEdit(true);
						}
						if (txtOrderID.getValue() != null)
						{
							pickupTaskAssignListgrid.fetchData(new Criteria("orderID", "" + txtOrderID.getValue().toString()));
						}
					}
				});

		// 新增按钮
		final IButton btnNew = new IButton();
		btnNew.setTitle("新增行");
		btnNew.setWidth(65);
		btnNew.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				pickupDeliveryVanningListgrid.startEditingNew();
			}
		});

		// 删除按钮
		final IButton btnDelete = new IButton();
		btnDelete.setTitle("删除行");
		btnDelete.setWidth(65);
		btnDelete.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (pickupDeliveryVanningListgrid.getSelection().length == 1) {
					pickupDeliveryVanningListgrid.removeSelectedData();
				} else {
					SC.say("请选择一条记录进行删除！");
				}
			}
		});

		// 保存按钮
		final IButton btnSave = new IButton();
		btnSave.setTitle("保存");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// 地址信息
				FormItem[] shipperFormItems = shipperForm.getFields();
				for (FormItem formItem : shipperFormItems)
				{
					if (formItem.getValue() != null) {
						secondForm.setValue(formItem.getFieldName(), "" + formItem.getValue().toString());
					}
				}
				
				// 获取明细数据
				ListGridRecord[] pickupVanningRecord = new ListGridRecord[pickupDeliveryVanningListgrid.getTotalRows()];
				for (int i = 0; i < pickupDeliveryVanningListgrid.getTotalRows(); i++)
				{
					pickupVanningRecord[i] = (ListGridRecord)pickupDeliveryVanningListgrid.getEditedRecord(i);
				}
				secondForm.setValue("pickupDeliveryVanningList", pickupVanningRecord);
			
				secondForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("保存成功！");

						// 设置物流任务编号
						txtLogisticsTasksNumber.setValue(response.getData()[0].getAttribute("logisticsTasksNumber"));
						// 刷新指令明细箱数据
						Criteria pickupVanningCriteria = new Criteria();
						pickupVanningCriteria.addCriteria("temp", String.valueOf(Math.random()));
						pickupVanningCriteria.addCriteria("orderID", "" + txtOrderID.getValue().toString());
						DataSource pickupVanningDS = pickupDeliveryVanningListgrid.getDataSource();
						pickupDeliveryVanningListgrid.setDataSource(pickupVanningDS);
						pickupDeliveryVanningListgrid.drawPickupDeliveryVanningListgrid();
						pickupDeliveryVanningListgrid.setCanEdit(true);
						pickupDeliveryVanningListgrid.fetchData(pickupVanningCriteria);
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

		// 分派工作按钮
		final IButton btnDispatchWork = new IButton();
		btnDispatchWork.setTitle("分派工作");
		btnDispatchWork.setWidth(80);
		btnDispatchWork.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				// 已分配的工作项数
				int workCount = 0;
				// 设置收料单明细数据
				ListGridRecord[] taskRecords = new ListGridRecord[pickupTaskAssignListgrid.getTotalRows()];
				for (int i = 0; i < pickupTaskAssignListgrid.getTotalRows(); i++)
				{
					ListGridRecord curRecord = (ListGridRecord)pickupTaskAssignListgrid.getEditedRecord(i);
					// 任务开始时间
					String strTaskStartDate = curRecord.getAttribute("taskStartDate");
					// 任务结束时间
					String strTaskEndDate = curRecord.getAttribute("taskEndDate");
					// 负责人员
					String strWorker = curRecord.getAttribute("worker");
					if (txtOrderID.getValue() != null)
						curRecord.setAttribute("orderID", "" + txtOrderID.getValue().toString());
					if (txtOrderNumber.getValue() != null)
						curRecord.setAttribute("orderNumber", "" + txtOrderNumber.getValue().toString());
					if (txtLogisticsTasksNumber.getValue() != null)
						curRecord.setAttribute("logisticsTasksNumber", "" + txtLogisticsTasksNumber.getValue().toString());
					// 只有进出口的情况，需要做清关工作
					if (LogisticsTaskJobType.valueOf(curRecord.getAttributeAsString("workType"))
							== LogisticsTaskJobType.customsClearance) {
						if ((strTaskStartDate != null && !strTaskStartDate.equals("")) &&
								(strTaskEndDate != null && !strTaskEndDate.equals("")) &&
								(strWorker != null && !strWorker.equals(""))) {
							if (rdoCustomsDeclarationType.getValue().equals("不需要")) {
								SC.say("国内业务不需要清关！");
								return;
							} else if(rdoCustomsDeclarationType.getValue().equals("进口")) {
								curRecord.setAttribute("customsDeclarationType", "2");		
							} else if(rdoCustomsDeclarationType.getValue().equals("出口")) {
								curRecord.setAttribute("customsDeclarationType", "3");		
							}
						}
					}
					if ((strTaskStartDate != null && !strTaskStartDate.equals("")) ||
							(strTaskEndDate != null && !strTaskEndDate.equals("")) ||
							(strWorker != null && !strWorker.equals(""))) {
						if (strTaskStartDate == null || strTaskStartDate.equals("")) {
							SC.say("任务开始时间不能为空！");
							return;
						}
						if (strTaskEndDate == null || strTaskEndDate.equals("")) {
							SC.say("任务结束时间不能为空！");
							return;
						}
						if (strWorker == null || strWorker.equals("")) {
							SC.say("负责人员不能为空！");
							return;
						}
					}
					
					// 统计已分配工作的项数
					if ((strTaskStartDate != null && !strTaskStartDate.equals("")) &&
							(strTaskEndDate != null && !strTaskEndDate.equals("")) &&
							(strWorker != null && !strWorker.equals(""))) {
						workCount = workCount + 1;
					}
					
					taskRecords[i] = curRecord;
				}
				
				if (workCount == 0) {
					SC.say("请至少分配一项目工作！");
					return;
				}
				
				Record record = new Record();
				record.setAttribute("taskRecords", taskRecords);
				pickupTaskAssignListgrid.updateData(record, new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						SC.say("工作分派成功！");

						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						criteria.addCriteria("orderID", "" + txtOrderID.getValue().toString());
						DataSource dataSource = pickupTaskAssignListgrid.getDataSource();
						pickupTaskAssignListgrid.setDataSource(dataSource);
						pickupTaskAssignListgrid.drawPickupDeliveryTaskAssignListgrid(false);
						pickupTaskAssignListgrid.getField("worker").setEditorType(selUser);
						pickupTaskAssignListgrid.setCanEdit(true);
						pickupTaskAssignListgrid.fetchData(criteria);
					}
				});
			}
		});

		// 发布任务按钮
		final IButton btnPublish = new IButton();
		btnPublish.setTitle("发布任务");
		btnPublish.setWidth(80);
		btnPublish.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (txtLogisticsTasksNumber.getValue() == null || txtLogisticsTasksNumber.getValue().toString().equals("")) {
					SC.say("必须先产生一个物流任务编号才能发布！");
				} else {
					Record record = new Record();
					record.setAttribute("publish", "publish");
					record.setAttribute("orderID", txtOrderID.getValue());
					pickupTaskAssignListgrid.updateData(record, new DSCallback() {
						@Override
						public void execute(DSResponse response, Object rawData, DSRequest request) {
							SC.say("任务发布成功！");
							
							// 刷新指令数据
							Criteria criteria = new Criteria();
							criteria.addCriteria("temp", String.valueOf(Math.random()));
							criteria.addCriteria("isPublish", "1");
							criteria.addCriteria("pickupDeliveryOrderType", "2");
							headGrid.fetchData(criteria);
							// 清空明细数据
							detailGrid.setData(new ListGridRecord[]{});	
							
							ShowWindowTools.showCloseWindow(rect, objWindow, -1);
						}
					});
				}
			}
		});
		
		if (isFromDispatch == true) {
			btnPublish.setDisabled(true);
		} else {
			btnPublish.setDisabled(false);
		}

		final BtnsHLayout detailBtnLayout = new BtnsHLayout(); 
		detailBtnLayout.addMember(btnNew);
		detailBtnLayout.addMember(btnDelete);
		
		final BtnsHLayout bottomBtnLayout = new BtnsHLayout(); 
		bottomBtnLayout.addMember(btnSave);
		bottomBtnLayout.addMember(btnReturn);
		
		final BtnsHLayout taskBtnLayout = new BtnsHLayout(); 
		taskBtnLayout.addMember(btnDispatchWork);
		taskBtnLayout.addMember(btnPublish);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(tabSet);
		layout.addMember(pickupDeliveryVanningListgrid);
		layout.addMember(bottomBtnLayout);
		layout.addMember(pickupTaskAssignListgrid);
		layout.addMember(taskBtnLayout);
		
		if (isView == true) {
			Utils.setReadOnlyForm(secondForm);
			Utils.setReadOnlyForm(headForm);
			Utils.setReadOnlyForm(shipperForm);
			btnSave.setVisible(false);
			btnReturn.setVisible(false);
			taskBtnLayout.setVisible(false);
		}

		SetWindow.SetWindowLayout(windowTitle
				,true
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}