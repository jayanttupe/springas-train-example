package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.UserTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.FormItemClickHandler;
import com.smartgwt.client.widgets.form.fields.events.FormItemIconClickEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class ReceivingSheetDetailWindow extends Window {

	/**
	 * 
	 * @param windowTitle
	 * @param rect
	 * @param headListGrid
	 * @param detailListGrid
	 * @param updateFlg
	 * @param isFromWaitReceiving
	 * @param dataSource
	 * @param iconUrl
	 * @param isView
	 */
	public ReceivingSheetDetailWindow(String windowTitle
			,final Rectangle rect
			,final ListGrid headListGrid
			,final ListGrid detailListGrid
			,final Boolean updateFlg
			,final Boolean isFromWaitReceiving
			,final DataSource dataSource
			,final String iconUrl,
			final Boolean isView) {
		final Window objWindow = this;
		setWidth(850);
		setHeight(590);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setWidth(500);
		mainForm.setNumCols(5);
		mainForm.setColWidths(90,160,90,80,80);

        // 收料记录ID
		final HiddenItem txtReceivingSheetID = new HiddenItem("id");
		// 收料单编号
		final TextItem txtReceivingSheetNumber = new TextItem("receivingSheetNumber");
		txtReceivingSheetNumber.setWidth(160);
		txtReceivingSheetNumber.setColSpan(2);
		// 是否保税
		final CheckboxItem chkIsBonded = new CheckboxItem("isBonded");
		chkIsBonded.setShowTitle(false);
		chkIsBonded.setDisabled(true);
		chkIsBonded.setWidth(80);
		// 是否送修登记
		final CheckboxItem chkIsRepair = new CheckboxItem("isRepair");
		chkIsRepair.setShowTitle(false);
		chkIsRepair.setWidth(80);
		// 提货指令单ID
		final HiddenItem txtOrderID = new HiddenItem("orderID");
		// 提货指令单编号
		final TextItem txtOrderNumber = new TextItem("orderNumber");
		txtOrderNumber.setWidth(160);
		FormItemIcon orderNumberItemIcon = new FormItemIcon();
		txtOrderNumber.setIcons(orderNumberItemIcon);
		// 业务类型
		final SelectItem txtBusinessType = new SelectItem("businessType");
		txtBusinessType.setWidth(160);
		txtBusinessType.setColSpan(2);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.csdd.b.BusinessType", txtBusinessType);
		// 合同编号
		final TextItem txtContractNumber = new TextItem("contractNumber");
		txtContractNumber.setWidth(160);
		// 运单号
		final TextItem txtMainWayBill = new TextItem("mainWayBill");
		txtMainWayBill.setWidth(160);
		txtMainWayBill.setColSpan(2);
		// 物流操作人员
		final TextItem txtLogisticsCreateBy = new TextItem("logisticsCreateBy");
		txtLogisticsCreateBy.setWidth(160);
		// 物流操作日期
		final CustomDateItem txtLogisticsCreateDate = new CustomDateItem("logisticsCreateDate");
		txtLogisticsCreateDate.setWidth(160);
		txtLogisticsCreateDate.setColSpan(2);
		// 包装外观
		final SelectItem txtPackagingAppearance = new SelectItem("packagingAppearance");
		txtPackagingAppearance.setWidth(160);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.stockServiceBusiness.spmsdd.PackagingAppearance", txtPackagingAppearance);
		// 箱数
		final TextItem txtBoxQuantity = new TextItem("boxQuantity");
		txtBoxQuantity.setWidth(160);
		txtBoxQuantity.setColSpan(2);
		// 收料人
		final SelectItem selUser = new SelectItem("receivingUser");
		selUser.setWidth(160);
		// 收料日期
		final CustomDateItem txtReceivingDate = new CustomDateItem("receivingDate");
		txtReceivingDate.setWidth(160);
		txtReceivingDate.setColSpan(2);
		txtReceivingDate.setValue(new Date());
		// 备注
		final TextAreaItem txtMemo = new TextAreaItem("memo");
		txtMemo.setWidth(410);
		txtMemo.setHeight(35);
		txtMemo.setColSpan(4);

		mainForm.setFields(txtReceivingSheetID,
							txtReceivingSheetNumber,
							chkIsBonded,
							chkIsRepair,
							txtOrderID,
							txtOrderNumber,
							txtBusinessType,
							txtContractNumber,
							txtMainWayBill,
							txtLogisticsCreateBy,
							txtLogisticsCreateDate,
							txtPackagingAppearance,
							txtBoxQuantity,
							selUser,
							txtReceivingDate,
							txtMemo);

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
						if (isFromWaitReceiving == true) {
							selUser.setDefaultValue(UserTools.getCurrentUserName());	
						} else {
							final Record record = headListGrid.getSelectedRecord();
							selUser.setValue(record.getAttribute("receivingUser"));
						}
						
						selUser.setValueField("username");
						selUser.setDisplayField("username");
						ListGridField usernameField = new ListGridField("username");
						ListGridField realnameField = new ListGridField("realname");
						selUser.setPickListFields(usernameField, realnameField);
					}
				});

		// 收料单编号
		txtReceivingSheetNumber.setDisabled(true);
		// 业务类型
		txtBusinessType.setDisabled(true);
		// 合同编号
		txtContractNumber.setDisabled(true);
		// 运单号
		txtMainWayBill.setDisabled(true);
		// 物流操作人员
		txtLogisticsCreateBy.setDisabled(true);
		// 物流操作日期
		txtLogisticsCreateDate.setDisabled(true);

		if (isFromWaitReceiving == false) {
			mainForm.setDataSource(headListGrid.getDataSource());
			
			if (updateFlg == true) {
				final Record record = headListGrid.getSelectedRecord();
				mainForm.editRecord(record);
			}
		} else {
			mainForm.setDataSource(dataSource);
			Record record = headListGrid.getSelectedRecord();
			// 提货指令ID
			txtOrderID.setValue(record.getAttribute("orderID"));
			// 提货指令单编号
			txtOrderNumber.setValue(record.getAttribute("orderNumber"));
			// 业务类型
			txtBusinessType.setValue(record.getAttribute("businessType"));
			// 合同编号
			txtContractNumber.setValue(record.getAttribute("contractNumber"));
			// 运单号
			txtMainWayBill.setValue(record.getAttribute("mainWayBill"));
			// 物流操作人员
			txtLogisticsCreateBy.setValue(record.getAttribute("logisticsCreateBy"));
			// 物流操作日期
			txtLogisticsCreateDate.setValue(record.getAttributeAsDate("logisticsCreateDate"));
		}
		
		// 提货指令明细Grid
		final ReceivingSheetItemsListgrid receivingSheetItemsListgrid = new ReceivingSheetItemsListgrid();
		receivingSheetItemsListgrid.setMargin(5);
		receivingSheetItemsListgrid.setHeight(280);
		receivingSheetItemsListgrid.setWidth(650);
		receivingSheetItemsListgrid.setAutoSaveEdits(false);
		receivingSheetItemsListgrid.setEditEvent(ListGridEditEvent.CLICK);

		// 获取数据源
		String detailmodeName = "stockServiceBusiness.inStockRoomBusiness.receivingSheet";
		String detaildsName = "receivingSheetItems_dataSource";
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
			new PostDataSourceInit() {
				@Override
				public void doPostOper(DataSource dataSource,
						DataInfo dataInfo) {
					receivingSheetItemsListgrid.setDataSource(dataSource);
					receivingSheetItemsListgrid.drawReceivingSheetItemsListgrid(true);
					// 设置列的编辑属性
					receivingSheetItemsListgrid.getField("partNumber").setCanEdit(false);
					receivingSheetItemsListgrid.getField("partName").setCanEdit(false);
					receivingSheetItemsListgrid.getField("partUnit").setCanEdit(false);
					receivingSheetItemsListgrid.getField("partType").setCanEdit(false);
					receivingSheetItemsListgrid.getField("isSerial").setCanEdit(false);
					receivingSheetItemsListgrid.getField("boxNO").setCanEdit(false);

					if (isView == true) {
						receivingSheetItemsListgrid.setCanEdit(false);
					} else {
						receivingSheetItemsListgrid.setCanEdit(true);
					}
					
					if (isFromWaitReceiving == false) {
						if (txtReceivingSheetID.getValue() != null) {
							Criteria criteria = new Criteria();
							criteria.addCriteria("temp", String.valueOf(Math.random()));
							criteria.addCriteria("type", "receivingSheetDetail");
							criteria.addCriteria("receivingSheetID", "" + txtReceivingSheetID.getValue().toString());
							receivingSheetItemsListgrid.fetchData(criteria);
						}
					} else {
						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						criteria.addCriteria("type", "order");
						criteria.addCriteria("orderID", "" + txtOrderID.getValue().toString());
						receivingSheetItemsListgrid.fetchData(criteria);
					}
				}
			});
		// 调用提货指令画面处理
		orderNumberItemIcon.addFormItemClickHandler(new FormItemClickHandler() {
			@Override
			public void onFormItemClick(FormItemIconClickEvent event) {
				PickupOrderSelectWindow pickupOrderSelectWindow = new PickupOrderSelectWindow("选择提货指令", rect, "icons/add.gif", mainForm, receivingSheetItemsListgrid);
				ShowWindowTools.showWondow(rect, pickupOrderSelectWindow, -1);
			}
		});
		
		// 收料单保存处理
		IButton btnSave = new IButton();
		btnSave.setTitle("保存");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (txtOrderNumber.getValue() == null
						|| txtOrderNumber.getValue().toString().equals("")) {
					SC.say("提货指令不能为空!");
					return;
				}

				Map tempMap = new HashMap();
				// 设置收料单明细数据
				ListGridRecord[] detailRecord = new ListGridRecord[receivingSheetItemsListgrid.getTotalRows()];
				for (int i = 0; i < receivingSheetItemsListgrid.getTotalRows(); i++)
				{
					ListGridRecord curRecord = (ListGridRecord)receivingSheetItemsListgrid.getEditedRecord(i);
					detailRecord[i] = curRecord;

					// 件号
					String partNumber = curRecord.getAttribute("partNumber");
					// 序号/批号
					String partSerialNumber = curRecord.getAttribute("partSerialNumber");
					String key = partNumber + "/" + partSerialNumber;
					if (tempMap.containsKey(key)) {
						SC.say("件号:" + partNumber + "存在相同的序号/批号:" + partSerialNumber + "，请重新输入！");
						return;
					} else {
						tempMap.put(key, key);
					}
					
					if (partSerialNumber == null || partSerialNumber.equals("")) {
						SC.say("序号/批号不能为空！");
						return;
					}
				}
				mainForm.setValue("receivingSheetItemsList", detailRecord);
				
				// 保存收料单信息
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						final String executeResult = response.getData()[0].getAttribute("executeResult");
						if (executeResult.equals("0")) {
							SC.say("保存成功！");
							
							if (txtReceivingSheetID.getValue() != null) {
								DataSource dataSource = receivingSheetItemsListgrid.getDataSource();
								receivingSheetItemsListgrid.setDataSource(dataSource);
								receivingSheetItemsListgrid.drawReceivingSheetItemsListgrid(true);
								// 设置不可编辑列
								receivingSheetItemsListgrid.getField("partNumber").setCanEdit(false);
								receivingSheetItemsListgrid.getField("partName").setCanEdit(false);
								receivingSheetItemsListgrid.getField("partUnit").setCanEdit(false);
								receivingSheetItemsListgrid.getField("partType").setCanEdit(false);
								receivingSheetItemsListgrid.getField("isSerial").setCanEdit(false);
								receivingSheetItemsListgrid.getField("boxNO").setCanEdit(false);

								// 刷新区域数据
								Criteria criteria = new Criteria();
								criteria.addCriteria("temp", String.valueOf(Math.random()));
								criteria.addCriteria("type", "receivingSheetDetail");
								criteria.addCriteria("receivingSheetID", "" + txtReceivingSheetID.getValue().toString());
								receivingSheetItemsListgrid.fetchData(criteria);
								
								// 从待收料单过来的时候，需要刷新待收料的数据
								if (isFromWaitReceiving == true) {
									headListGrid.fetchData(new Criteria("temp", String.valueOf(Math.random())));
									detailListGrid.setData(new ListGridRecord[]{});
								}
							}	
						} else {
							SC.say(response.getData()[0].getAttribute("errMsg"));
						}
					}
				});
			}
		});

		// 返回处理
		IButton btnReturn = new IButton();
		btnReturn.setTitle("返回");
		btnReturn.setWidth(65);
		btnReturn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		// 新增处理
		final IButton btnNew = new IButton();
		btnNew.setTitle("新增行");
		btnNew.setWidth(65);
		btnNew.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (txtOrderID.getValue() != null )
				{
					Integer nextRowNO = receivingSheetItemsListgrid.getTotalRows() + 1;
					String nextItemNO = "";
					Map map = new HashMap();
					Integer len = String.valueOf(nextRowNO).length();
					if (len == 1) {
						nextItemNO = "00" + String.valueOf(nextRowNO);
					} else if (len == 2) {
						nextItemNO = "0" + String.valueOf(nextRowNO);
					} else if (len == 3) {
						nextItemNO = String.valueOf(nextRowNO);
					}
					map.put("itemNumber", nextItemNO);

					receivingSheetItemsListgrid.startEditingNew(map);
				} else {
					SC.say("请先选择提货指令！");
					return;
				}
			}
		});

		// 删除处理
		final IButton btnDel = new IButton();
		btnDel.setTitle("删除行");
		btnDel.setWidth(65);
		btnDel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (receivingSheetItemsListgrid.getSelectedRecord() != null)
				{
					receivingSheetItemsListgrid.removeSelectedData();
				} else {
					SC.say("请先选择收料明细数据！");
					return;
				}
			}
		});
		
		final BtnsHLayout detailHLayout = new BtnsHLayout();
		detailHLayout.addMember(btnNew);
		detailHLayout.addMember(btnDel);
		
		final BtnsHLayout bottomHLayout = new BtnsHLayout();
		bottomHLayout.addMember(btnSave);
		bottomHLayout.addMember(btnReturn);
      
		VLayout vLayout = new VLayout();
		vLayout.setMargin(5);
		vLayout.addMember(mainForm);
		vLayout.addMember(detailHLayout);
		vLayout.addMember(receivingSheetItemsListgrid);	
		vLayout.addMember(bottomHLayout);

		if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
			detailHLayout.setVisible(false);
			btnSave.setVisible(false);
			btnReturn.setVisible(false);
		}
		
		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,vLayout);
	}
}