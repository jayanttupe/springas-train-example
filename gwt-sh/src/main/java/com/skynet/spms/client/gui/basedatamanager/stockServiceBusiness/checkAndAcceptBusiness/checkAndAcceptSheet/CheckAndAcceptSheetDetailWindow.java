package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet;

import java.util.Date;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetItemsListgrid;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetSelectWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.UserTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.MultipleAppearance;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.IconClickEvent;
import com.smartgwt.client.widgets.form.fields.events.IconClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class CheckAndAcceptSheetDetailWindow extends Window {

	/**
	 * 
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param headGrid
	 * @param detailGrid
	 * @param iconUrl
	 * @param updateFlg
	 * @param isFromWaitCheck
	 * @param dataSource
	 * @param isView
	 */
	public CheckAndAcceptSheetDetailWindow(String windowTitle
			,boolean isMax
			,final Rectangle rect
			,final ListGrid headGrid
			,final ListGrid detailGrid
			,String iconUrl
			,final Boolean updateFlg
			,final Boolean isFromWaitCheck
			,final DataSource dataSource
			,final Boolean isView) {
		final Window objWindow = this;
		setWidth(955);
		setHeight(475);

		// 收料单检验部分
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setNumCols(6);
		mainForm.setWidth(540);
		mainForm.setHeight("90%");
		mainForm.setColWidths(90,165,75,45,60,105);

		final HiddenItem txtReceivingSheetItemsID = new HiddenItem("receivingSheetItemsID");
		// 检验单编号 
		final TextItem txtCheckAndAcceptSheetNumber = new TextItem("checkAndAcceptSheetNumber");
		txtCheckAndAcceptSheetNumber.setDisabled(true);
		txtCheckAndAcceptSheetNumber.setWidth(150);
		// 收料单ID
		final HiddenItem txtReceivingSheetID = new HiddenItem("receivingSheetID");
		// 收料单编号
		final TextItem txtReceivingSheetNumber = new TextItem("receivingSheetNumber");
		txtReceivingSheetNumber.setWidth(195);
		txtReceivingSheetNumber.setColSpan(3);
		final FormItemIcon icoReceivingSheetNumber = new FormItemIcon();
		txtReceivingSheetNumber.setIcons(icoReceivingSheetNumber);
		// 选择收料单处理
		txtReceivingSheetNumber.addIconClickHandler(new IconClickHandler() {
			@Override
			public void onIconClick(IconClickEvent  event) {
				ReceivingSheetSelectWindow objWindow =
					new ReceivingSheetSelectWindow("选择收料单", txtReceivingSheetNumber.getPageRect(), "icons/add.gif", mainForm, false);
				ShowWindowTools.showWondow(txtReceivingSheetNumber.getPageRect(), objWindow, -1);
			}
		});
		// 业务类型
		final SelectItem selBusinessType = new SelectItem("businessType");
		selBusinessType.setDisabled(true);
		selBusinessType.setWidth(150);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.csdd.b.BusinessType", selBusinessType);
		// 合同编号
		final TextItem txtContratNumber = new TextItem("contratNumber");
		txtContratNumber.setWidth(195);
		txtContratNumber.setColSpan(3);
		// 供应商
		final TextItem txtProviderName = new TextItem("providerName");
		txtProviderName.setWidth(150);
		// 装箱单号
		final TextItem txtPackingListNumber = new TextItem("packingListNumber");
		txtPackingListNumber.setWidth(195);
		txtPackingListNumber.setColSpan(3);
		// 件号
		final TextItem txtPartNumber = new TextItem("partNumber");
		txtPartNumber.setWidth(150);
		// 序号/批号
		final TextItem txtPartSerialNumber = new TextItem("partSerialNumber");
		txtPartSerialNumber.setWidth(195);
		txtPartSerialNumber.setColSpan(3);
		// 件描述
		final TextItem txtPartName = new TextItem("partName");
		txtPartName.setWidth(150);
		// 制造商
		final TextItem txtManufacturerName = new TextItem("manufacturerName");
		txtManufacturerName.setWidth(195);
		txtManufacturerName.setColSpan(3);
		// 备件分类
		final SelectItem txtPartType = new SelectItem("partType");
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.csdd.s.SparePartClassCode", txtPartType);
		txtPartType.setWidth(150);
		// 数量
		final TextItem txtQuantity = new TextItem("quantity");
		txtQuantity.setWidth(45);
		// 单位
		final SelectItem txtUnit = new SelectItem("unit");
		txtUnit.setWidth(90);
		// 备件质量外观
		final SelectItem txtPartsQualityAppearance = new SelectItem("partsQualityAppearance");
		txtPartsQualityAppearance.setValueMap("有缺陷", "完好");
		txtPartsQualityAppearance.setWidth(150);
		// 备件状况
		final SelectItem txtPartStatus = new SelectItem("partStatus");
		txtPartStatus.setValueMap("新件", "翻修件");
		txtPartStatus.setWidth(195);
		txtPartStatus.setColSpan(3);
		txtPartStatus.setHint("<font color='red'>*</font>");
		// 是否抽样
		final RadioGroupItem rdoIsSampling = new RadioGroupItem("isSampling");
		rdoIsSampling.setValueMap("是", "否");
		rdoIsSampling.setVertical(false);
		rdoIsSampling.setDefaultValue("否");
		rdoIsSampling.setWidth(150);
		// 不合格数量
		final TextItem txtFailQuantity = new TextItem("failQuantity");
		txtFailQuantity.setWidth(45);
		// 合格数量
		final TextItem txtSucceedQuantity = new TextItem("succeedQuantity");
		txtSucceedQuantity.setWidth(90);
		// 是否时控件 
		final RadioGroupItem rdoIsTimePart = new RadioGroupItem("isTimePart");
		rdoIsTimePart.setValueMap("是", "否");
		rdoIsTimePart.setVertical(false);
		rdoIsTimePart.setDefaultValue("否");
		rdoIsTimePart.setWidth(150);
		// 时控周期
		final TextItem txtStorageRacksLife = new TextItem("storageRacksLife");
		txtStorageRacksLife.setWidth(45);
		// 到限日期
		final CustomDateItem txtLimitDate = new CustomDateItem("limitDate");
		txtLimitDate.setWidth(90);
		// 是否时寿件 
		final RadioGroupItem rdoIsLifePart = new RadioGroupItem("isLifePart");
		rdoIsLifePart.setValueMap("是", "否");
		rdoIsLifePart.setVertical(false);
		rdoIsLifePart.setDefaultValue("否");
		rdoIsLifePart.setWidth(150);
		// 寿命期
		final TextItem txtLifePartCycle = new TextItem("lifePartCycle");
		txtLifePartCycle.setWidth(45);
		// 到寿日期
		final CustomDateItem txtLifeDate = new CustomDateItem("lifeDate");
		txtLifeDate.setWidth(90);
		// 抽样方案
		final RadioGroupItem rdoSamplingScheme = new RadioGroupItem("samplingScheme");
		rdoSamplingScheme.setValueMap("正常检查", "加严检查");
		rdoSamplingScheme.setDefaultValue("正常检查");
		rdoSamplingScheme.setVertical(false);
		rdoSamplingScheme.setWidth(150);
		rdoSamplingScheme.setDisabled(true);
		// 生产日期
		final CustomDateItem txtManufactureDate = new CustomDateItem("manufactureDate");
		txtManufactureDate.setWidth(195);
		txtManufactureDate.setColSpan(3);
		// 随机证件
		final SelectItem txtCredentials = new SelectItem("credentials");
		txtCredentials.setMultiple(true);
		txtCredentials.setMultipleAppearance(MultipleAppearance.PICKLIST);
		txtCredentials.setValueMap("CASA Form 917"
								,"Certificate of Conformance"
								,"EASA Form 1"
								,"FAA Form 8130‐3"
								,"JAA Form One"
								,"TCCA 24‐0078"
								,"Transfer Document");
		txtCredentials.setColSpan(5);
		txtCredentials.setWidth(435);
		txtCredentials.setHint("<font color='red'>*</font>");
		// 证书文件编号
		final TextItem txtCertificateCode = new TextItem("certificateCode");
		txtCertificateCode.setWidth(150);
		txtCertificateCode.setHint("<font color='red'>*</font>");
		// 验收结论
		final SelectItem cmbCheckResult = new SelectItem("checkResult");
		cmbCheckResult.setValueMap("验收合格", "验收不合格", "待确认");
		cmbCheckResult.setWidth(195);
		cmbCheckResult.setColSpan(3);
		cmbCheckResult.setHint("<font color='red'>*</font>");
		// 检验员
		final SelectItem selUser = new SelectItem("checkUser");
		selUser.setWidth(150);
		// 检验日期
		final CustomDateItem txtCheckDate = new CustomDateItem("checkDate");
		txtCheckDate.setColSpan(3);
		txtCheckDate.setWidth(195);
		txtCheckDate.setValue(new Date());
		
		txtContratNumber.setDisabled(true);
		txtPackingListNumber.setDisabled(true);
		txtPartNumber.setDisabled(true);
		txtPartSerialNumber.setDisabled(true);
		txtPartName.setDisabled(true);
		txtQuantity.setDisabled(true);
		txtUnit.setDisabled(true);
		txtManufacturerName.setDisabled(true);
		txtProviderName.setDisabled(true);
		txtPartType.setDisabled(true);
		txtSucceedQuantity.setDisabled(true);
		txtFailQuantity.setDisabled(true);
		txtStorageRacksLife.setDisabled(true);
		txtLimitDate.setDisabled(true);
		txtLifePartCycle.setDisabled(true);
		txtLifeDate.setDisabled(true);
		
		mainForm.setFields(txtReceivingSheetItemsID
							,txtCheckAndAcceptSheetNumber
							,txtReceivingSheetID
							,txtReceivingSheetNumber
							,selBusinessType
							,txtContratNumber
							,txtProviderName
							,txtPackingListNumber
							,txtPartNumber
							,txtPartSerialNumber
							,txtPartName
							,txtManufacturerName
							,txtPartType
							,txtQuantity
							,txtUnit
							,txtPartsQualityAppearance
							,txtPartStatus							
							,rdoIsSampling
							,txtFailQuantity
							,txtSucceedQuantity
							,rdoSamplingScheme
							,txtManufactureDate
							,rdoIsTimePart
							,txtStorageRacksLife
							,txtLimitDate
							,rdoIsLifePart
							,txtLifePartCycle
							,txtLifeDate
							,txtCredentials
							,txtCertificateCode
							,cmbCheckResult	
							,selUser
							,txtCheckDate
							);

		// 明细列表Grid
		final ListGrid waitCheckAndAcceptSheetItemsListgrid = new ReceivingSheetItemsListgrid();
		waitCheckAndAcceptSheetItemsListgrid.setHeight("98%");
		waitCheckAndAcceptSheetItemsListgrid.setWidth(200);
		waitCheckAndAcceptSheetItemsListgrid.setCanEdit(false);
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "stockServiceBusiness.checkAndAcceptBusiness.waitCheckAndAcceptSheet";
		String detailDSName = "receivingSheetItems_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
			new PostDataSourceInit() {
				public void doPostOper(DataSource dataSource,
						DataInfo dataInfo) {
					waitCheckAndAcceptSheetItemsListgrid.setDataSource(dataSource);

					// 件号
					ListGridField partNumberField = new ListGridField("partNumber");
					partNumberField.setWidth(100);
					// 序号/批号
					ListGridField partSerialNumberField = new ListGridField("partSerialNumber");
					partSerialNumberField.setWidth(60);
					waitCheckAndAcceptSheetItemsListgrid.setShowRowNumbers(true);
					waitCheckAndAcceptSheetItemsListgrid.setFields(partNumberField
							, partSerialNumberField);
					
					if (isView == true) {
						waitCheckAndAcceptSheetItemsListgrid.setCanEdit(false);
					} else {
						waitCheckAndAcceptSheetItemsListgrid.setCanEdit(true);
					}

					Criteria criteria = new Criteria();
					String receivingSheetID = "";
					// 判断是否是待收料过来
					if (isFromWaitCheck == true) {
						receivingSheetID = headGrid.getSelectedRecord().getAttribute("id");
					} else {
						receivingSheetID = headGrid.getSelectedRecord().getAttribute("receivingSheetID");
					}
					criteria.addCriteria("isCheck", "0");
					criteria.addCriteria("receivingSheetID", receivingSheetID);
					waitCheckAndAcceptSheetItemsListgrid.fetchData(criteria);
			}
		});
		
		// 单击待检验明细数据时的处理
		waitCheckAndAcceptSheetItemsListgrid.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				mainForm.clearValues();
				Record headRecord = headGrid.getSelectedRecord();
				Record detailRecord = waitCheckAndAcceptSheetItemsListgrid.getSelectedRecord();
				// 检验员
				txtCheckDate.setValue(new Date());
				// 检验日期
				selUser.setDefaultValue(UserTools.getCurrentUserName());
				// 收料单ID
				txtReceivingSheetID.setValue(headRecord.getAttribute("id"));
				// 收料单明细ID
				txtReceivingSheetItemsID.setValue(detailRecord.getAttribute("id"));
				// 收料单编号
				txtReceivingSheetNumber.setValue(headRecord.getAttribute("receivingSheetNumber"));
				// 业务类型
				selBusinessType.setValue(headRecord.getAttribute("businessType"));
				// 合同编号
				txtContratNumber.setValue(headRecord.getAttribute("contractNumber"));
				// 供应商
				txtProviderName.setValue(headRecord.getAttribute("providerName"));
				// 装箱单号
				txtPackingListNumber.setValue(detailRecord.getAttribute("packingListNumber"));
				// 件号
				txtPartNumber.setValue(detailRecord.getAttribute("partNumber"));
				// 序号/批号
				txtPartSerialNumber.setValue(detailRecord.getAttribute("partSerialNumber"));
				// 件描述
				txtPartName.setValue(detailRecord.getAttribute("partName"));
				// 数量
				txtQuantity.setValue(detailRecord.getAttribute("quantity"));
				// 单位
				if(isFromWaitCheck){
					txtUnit.setValue(detailRecord.getAttribute("partUnit"));
				} else {
					txtUnit.setValue(detailRecord.getAttribute("unit"));
				}
				// 制造商
				txtManufacturerName.setValue(headRecord.getAttribute("manufacturerName"));
				// 备件分类
				txtPartType.setValue(detailRecord.getAttribute("partType"));

				// 设置不可编辑项
				txtContratNumber.setDisabled(true);
				txtPackingListNumber.setDisabled(true);
				txtPartNumber.setDisabled(true);
				txtPartSerialNumber.setDisabled(true);
				txtPartName.setDisabled(true);
				txtQuantity.setDisabled(true);
				txtUnit.setDisabled(true);
				txtManufacturerName.setDisabled(true);
				txtProviderName.setDisabled(true);
				txtPartType.setDisabled(true);
				txtSucceedQuantity.setDisabled(true);
				txtFailQuantity.setDisabled(true);
				txtStorageRacksLife.setDisabled(true);
				txtLimitDate.setDisabled(true);
				txtLifePartCycle.setDisabled(true);
				txtLifeDate.setDisabled(true);
			}
		});

		// 获取用户数据
		String userModeName = "organization.userinfo";
		String userDSName = "user_dataSource";
		DataSourceTool userDST = new DataSourceTool();
		userDST.onCreateDataSource(userModeName, userDSName,
			new PostDataSourceInit() {
				public void doPostOper(DataSource dataSource,
						DataInfo dataInfo) {
					selUser.clearValue();
					selUser.setOptionDataSource(dataSource);
					if (updateFlg == true) {
						selUser.setDefaultValue(UserTools.getCurrentUserName());
					} else {
						final Record record = headGrid.getSelectedRecord();
						selUser.setValue(record.getAttribute("checkUser"));
					}
					
					selUser.setValueField("username");
					selUser.setDisplayField("username");
					ListGridField usernameField = new ListGridField("username");
					ListGridField realnameField = new ListGridField("realname");
					selUser.setPickListFields(usernameField, realnameField);
				}
			});
		
		// 判断是否抽样
		rdoIsSampling.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				if (event.getValue().equals("是")) {
					txtSucceedQuantity.setDisabled(false);
					txtFailQuantity.setDisabled(false);
					rdoSamplingScheme.setDisabled(false);
				} else {
					txtSucceedQuantity.clearValue();
					txtFailQuantity.clearValue();
					txtSucceedQuantity.setDisabled(true);
					txtFailQuantity.setDisabled(true);
					rdoSamplingScheme.setDisabled(true);
				}
			}
		});
		
		// 是否时控处理
		rdoIsTimePart.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				if (event.getValue().equals("是")) {
					txtStorageRacksLife.setDisabled(false);
					txtLimitDate.setDisabled(false);
				} else {
					txtStorageRacksLife.clearValue();
					txtLimitDate.clearValue();
					txtStorageRacksLife.setDisabled(true);
					txtLimitDate.setDisabled(true);
				}
			}
		});
		
		// 是否 时寿处理
		rdoIsLifePart.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				if (event.getValue().equals("是")) {
					txtLifePartCycle.setDisabled(false);
					txtLifeDate.setDisabled(false);
				} else {
					txtLifePartCycle.clearValue();
					txtLifeDate.clearValue();
					txtLifePartCycle.setDisabled(true);
					txtLifeDate.setDisabled(true);
				}
			}
		});
		
		// 判断是否是待收料过来
		if (isFromWaitCheck == false) {
			mainForm.setDataSource(headGrid.getDataSource());
			
			if (updateFlg == true) {
				final Record record = headGrid.getSelectedRecord();
				mainForm.editRecord(record);
				
				if (record.getAttribute("credentials") != null) {
					String credentials = record.getAttribute("credentials");
					String[] credentialsList = credentials.split(",");
					txtCredentials.setValues(credentialsList);
				}
			}
		} else {
			mainForm.setDataSource(dataSource);
		}
		
		// 保存处理
		final IButton saveButton = new IButton();
		saveButton.setTitle("保存");
		saveButton.setWidth(65);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (cmbCheckResult.getValue() == null) {
					SC.say("请选择一个验收结论！");
					return;
				}
				if (txtCertificateCode.getValue() == null) {
					SC.say("请填写证书文件编号！");
					return;
				}
				if (txtCredentials.getValue() == null) {
					SC.say("请选择至少一个随机证件！");
					return;
				}
				if (txtPartStatus.getValue() == null) {
					SC.say("请选择一个备件状况！");
					return;
				}
				if (txtReceivingSheetNumber.getValue() == null) {
					SC.say("请选择一条收料单编号！");
					return;
				}
				// 设置随机证件
				String[] credentialsList = txtCredentials.getValues();
				mainForm.setValue("credentialsList", credentialsList);
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						final String executeResult = response.getData()[0].getAttribute("executeResult");
						if (executeResult.equals("0")) {
							SC.say("保存成功！");
							
							// 收料单编号
							final String checkAndAcceptSheetNumber = response.getData()[0].getAttribute("checkAndAcceptSheetNumber");
							txtCheckAndAcceptSheetNumber.setValue(checkAndAcceptSheetNumber);

							// 收料单ID
							String receivingSheetID = txtReceivingSheetID.getValue().toString();
							Criteria detailCriteria = new Criteria();
							detailCriteria.addCriteria("temp", String.valueOf(Math.random()));
							detailCriteria.addCriteria("isCheck", "0");
							detailCriteria.addCriteria("receivingSheetID", "" + receivingSheetID);
							waitCheckAndAcceptSheetItemsListgrid.fetchData(detailCriteria);
							// 待收料过来的情况,刷新待检验明细数据
							if (isFromWaitCheck == true) {
								detailGrid.fetchData(detailCriteria);
							}
						} else if (executeResult.equals("1")) {
							SC.say("当前证书编号不存在！");
						} else if (executeResult.equals("2")) {
							SC.say("当前证书编号已经被使用！");
						}
					}
				});				
			}
		});

		// 返回处理
		final IButton returnButton = new IButton();
		returnButton.setTitle("返回");
		returnButton.setWidth(65);
		returnButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		
		final BtnsHLayout buttonLayout = new BtnsHLayout();
		buttonLayout.setHeight("10%");
		buttonLayout.addMember(saveButton);
		buttonLayout.addMember(returnButton);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(buttonLayout);
		
		HLayout formLayout = new HLayout();
		formLayout.setMargin(5);
		formLayout.addMember(waitCheckAndAcceptSheetItemsListgrid);
		formLayout.addMember(layout);
		
		VLayout vLayout = new VLayout();
		vLayout.setMargin(5);
		vLayout.addMember(formLayout);

		if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
			saveButton.setVisible(false);
			returnButton.setVisible(false);
		}
		
		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,vLayout);
	}
}