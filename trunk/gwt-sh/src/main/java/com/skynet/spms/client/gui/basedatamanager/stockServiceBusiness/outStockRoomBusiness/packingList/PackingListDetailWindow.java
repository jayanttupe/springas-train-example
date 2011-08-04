package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.packingList;

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
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingRecord.PickingRecordSelectWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Function;
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
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.IconClickEvent;
import com.smartgwt.client.widgets.form.fields.events.IconClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;

public class PackingListDetailWindow extends Window {

	/**
	 * 
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param headGrid
	 * @param detailGrid
	 * @param iconUrl
	 * @param isFromPickingRecord
	 * @param updateFlg
	 * @param dataSource
	 * @param isView
	 */
	public PackingListDetailWindow(String windowTitle, boolean isMax,
			final Rectangle rect, final ListGrid headGrid,
			final ListGrid detailGrid, String iconUrl,
			final Boolean isFromPickingRecord, final Boolean updateFlg,
			DataSource dataSource, final Boolean isView) {
		final Window objWindow = this;
		setWidth(900);
		setHeight(620);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setNumCols(6);
		mainForm.setWidth(700);
		mainForm.setMargin(5);
		mainForm.setColWidths(100, 150, 75, 150, 75, 150);

		// 装箱单ID
		final HiddenItem txtPackingListID = new HiddenItem("id");
		// 装箱单编号
		final TextItem txtPackingListNumber = new TextItem("packingListNumber");
		txtPackingListNumber.setWidth(150);
		txtPackingListNumber.setDisabled(true);
		// 配料单号
		final TextItem txtPickingListNumber = new TextItem("pickingListNumber");
		txtPickingListNumber.setWidth(150);
		final FormItemIcon icoPickingListNumber = new FormItemIcon();
		txtPickingListNumber.setIcons(icoPickingListNumber);
		// 业务类型
		final SelectItem selBusinessType = new SelectItem("businessType");
		selBusinessType.setWidth(150);
		selBusinessType.setDisabled(true);
		EnumTool.setValueMap(
				"com.skynet.spms.persistence.entity.csdd.b.BusinessType",
				selBusinessType);
		// 配料单ID
		final HiddenItem txtPickingListID = new HiddenItem("pickingListID");
		// 指令ID
		final HiddenItem txtOrderID = new HiddenItem("orderID");
		// 指令编号
		final TextItem txtOrderNumber = new TextItem("orderNumber");
		txtOrderNumber.setWidth(150);
		txtOrderNumber.setDisabled(true);
		// 合同编号
		final TextItem txtContractNumber = new TextItem("contractNumber");
		txtContractNumber.setWidth(150);
		txtContractNumber.setDisabled(true);
		// 优先级
		final SelectItem txtPriority = new SelectItem("priority");
		txtPriority.setWidth(150);
		txtPriority.setDisabled(true);
		EnumTool.setValueMap(
				"com.skynet.spms.persistence.entity.spmsdd.Priority",
				txtPriority);
		// 发货日期
		final CustomDateItem txtDeliveryDate = new CustomDateItem(
				"deliveryDate");
		txtDeliveryDate.setWidth(150);
		txtDeliveryDate.setDisabled(true);
		// 箱数
		final TextItem txtBoxQty = new TextItem("boxQty");
		txtBoxQty.setWidth(150);
		txtBoxQty.setDisabled(true);

		// 发货人姓名
		final TextItem txtShipper = new TextItem("shipper");
		txtShipper.setWidth(150);
		txtShipper.setDisabled(true);
		// 发货单位
		final TextItem txtCompanyOfShipper = new TextItem("companyOfShipper");
		txtCompanyOfShipper.setWidth(250);
		txtCompanyOfShipper.setDisabled(true);
		// 发货地址及详细地址
		final TextItem txtAddressOfShipper = new TextItem("addressOfShipper");
		txtAddressOfShipper.setWidth(250);
		txtAddressOfShipper.setDisabled(true);
		// 发货人联系方式
		final TextAreaItem txtTelephonOfShipper = new TextAreaItem(
				"telephonOfShipper");
		txtTelephonOfShipper.setWidth(250);
		txtTelephonOfShipper.setHeight(30);
		txtTelephonOfShipper.setDisabled(true);
		// 收货人姓名
		final TextItem txtConsignee = new TextItem("consignee");
		txtConsignee.setWidth(150);
		txtConsignee.setDisabled(true);
		// 收货单位
		final TextItem txtCompanyOfConsignee = new TextItem(
				"companyOfConsignee");
		txtCompanyOfConsignee.setWidth(250);
		txtCompanyOfConsignee.setDisabled(true);
		// 收货地址及详细信息
		final TextItem txtAddressOfConsignee = new TextItem(
				"addressOfConsignee");
		txtAddressOfConsignee.setWidth(250);
		txtAddressOfConsignee.setDisabled(true);
		// 收货人联系方式
		final TextAreaItem txtTelephoneOfConsignee = new TextAreaItem(
				"telephoneOfConsignee");
		txtTelephoneOfConsignee.setWidth(250);
		txtTelephoneOfConsignee.setHeight(30);
		txtTelephoneOfConsignee.setDisabled(true);

		// 地址信息
		final DynamicForm shipperForm = new DynamicForm();
		shipperForm.setGroupTitle("<font color='blue'>收发货人信息</font>");
		shipperForm.setIsGroup(true);
		shipperForm.setNumCols(4);
		shipperForm.setWidth(700);
		shipperForm.setMargin(5);
		shipperForm.setColWidths(100, 250, 100, 250);
		// 地址信息
		shipperForm.setFields(txtShipper, txtConsignee, txtCompanyOfShipper,
				txtCompanyOfConsignee, txtAddressOfShipper,
				txtAddressOfConsignee, txtTelephonOfShipper,
				txtTelephoneOfConsignee);

		// 装箱单备件明细项
		final PackingListPartItemsListgrid packingDetailListGrid = new PackingListPartItemsListgrid();
		packingDetailListGrid.setMargin(5);
		packingDetailListGrid.setWidth(700);
		packingDetailListGrid.setHeight(140);
		packingDetailListGrid.setCanEdit(true);
		packingDetailListGrid.setEditEvent(ListGridEditEvent.CLICK);
		packingDetailListGrid.setAutoSaveEdits(false);
		packingDetailListGrid.setConfirmDiscardEdits(false);
		// 获取装箱单备件明细数据
		String packingDetailModeName = "stockServiceBusiness.outStockRoomBusiness.packingList";
		String packingDetailDsName = "packingListPartItems_dataSource";
		DataSourceTool packingDetailDST = new DataSourceTool();
		packingDetailDST.onCreateDataSource(packingDetailModeName,
				packingDetailDsName, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						packingDetailListGrid.setDataSource(dataSource);

						// 件号
						ListGridField partNumberFiled = new ListGridField(
								"partNumber");
						partNumberFiled.setWidth(105);
						// 序号/批号
						ListGridField partSerialNumberFiled = new ListGridField(
								"partSerialNumber");
						partSerialNumberFiled.setWidth(100);
						// 件描述
						ListGridField keyWordFiled = new ListGridField(
								"partName");
						keyWordFiled.setWidth(140);
						// 制造商
						ListGridField manufacturerFiled = new ListGridField(
								"manufacturer");
						manufacturerFiled.setWidth(110);
						// 实发数量
						ListGridField sendQtyFiled = new ListGridField(
								"sendQty");
						sendQtyFiled.setWidth(60);
						// 单位
						ListGridField unitFiled = new ListGridField("unit");
						unitFiled.setWidth(50);
						// 随件证件
						ListGridField certificateTypeFiled = new ListGridField(
								"certificateType");
						// 分箱号
						ListGridField boxIDFiled = new ListGridField("boxID");
						boxIDFiled.setWidth(55);

						partNumberFiled.setCanEdit(false);
						partSerialNumberFiled.setCanEdit(false);
						keyWordFiled.setCanEdit(false);
						manufacturerFiled.setCanEdit(false);
						sendQtyFiled.setCanEdit(false);
						unitFiled.setCanEdit(false);
						certificateTypeFiled.setCanEdit(false);
						boxIDFiled.setCanEdit(true);

						packingDetailListGrid.setFields(partNumberFiled,
								partSerialNumberFiled, keyWordFiled,
								manufacturerFiled, sendQtyFiled, unitFiled,
								certificateTypeFiled, boxIDFiled);

						if (isFromPickingRecord == false) {
							if (txtPackingListID.getValue() != null) {
								String packingListID = txtPackingListID
										.getValue().toString();
								Criteria criteria = new Criteria();
								criteria.addCriteria("temp",
										String.valueOf(Math.random()));
								criteria.addCriteria("packingListID", ""
										+ packingListID);
								packingDetailListGrid.fetchData(criteria);
							}
						} else {
							Criteria criteria = new Criteria();
							criteria.addCriteria("pickingListID", ""
									+ txtPickingListID.getValue().toString());
							packingDetailListGrid.fetchData(criteria);
						}
						
						if (isView == true) {
							packingDetailListGrid.setCanEdit(false);
						} else {
							packingDetailListGrid.setCanEdit(true);
						}
					}
				});

		// 箱子明细项
		final ListGrid boxDetailListGrid = new ListGrid();
		boxDetailListGrid.setWidth(700);
		boxDetailListGrid.setHeight(120);
		boxDetailListGrid.setMargin(5);
		boxDetailListGrid.setSelectionType(SelectionStyle.SINGLE);
		boxDetailListGrid.setAutoSaveEdits(false);
		boxDetailListGrid.setCanEdit(true);
		boxDetailListGrid.setEditEvent(ListGridEditEvent.CLICK);

		// 获取箱子明细数据
		String boxDetailModeName = "stockServiceBusiness.outStockRoomBusiness.packingList";
		String boxDetailDsName = "packingListBoxItems_dataSource";
		DataSourceTool boxDetailDST = new DataSourceTool();
		boxDetailDST.onCreateDataSource(boxDetailModeName, boxDetailDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						boxDetailListGrid.setDataSource(dataSource);

						// 装箱单ID
						ListGridField packingListIDFiled = new ListGridField(
								"packingListID", "装箱单ID");
						packingListIDFiled.setHidden(true);
						// 箱号
						ListGridField boxNumberFiled = new ListGridField(
								"boxNumber", "箱号");
						// 包装材质
						ListGridField PackagingCodeFiled = new ListGridField(
								"packagingCode", "包装材质");
						// 件数
						ListGridField billOfLadingContainerCountFiled = new ListGridField(
								"billOfLadingContainerCount", "件数");
						billOfLadingContainerCountFiled.setCanEdit(false);
						// RFID标签唯一编号
						ListGridField rFIDTagUUIDField = new ListGridField(
								"packingRFIDTagUUID", "RFID标签唯一编号");
						// 备注
						ListGridField memoFiled = new ListGridField("memo",
								"备注");

						boxDetailListGrid.setFields(packingListIDFiled,
								boxNumberFiled, PackagingCodeFiled,
								billOfLadingContainerCountFiled,
								rFIDTagUUIDField, memoFiled);
						
						

						if (txtPackingListID.getValue() != null) {
							String packingListID = txtPackingListID.getValue()
									.toString();
							Criteria criteria = new Criteria();
							criteria.addCriteria("temp",
									String.valueOf(Math.random()));
							criteria.addCriteria("packingListID", ""
									+ packingListID);
							boxDetailListGrid.fetchData(criteria,
									new DSCallback() {
										@Override
										public void execute(
												DSResponse response,
												Object rawData,
												DSRequest request) {
											// 加载装箱单备件明细Grid中的分箱号
											setBoxNOValue(boxDetailListGrid,
													packingDetailListGrid);
										}
									});
						} else {
							// 加载装箱单备件明细Grid中的分箱号
							setBoxNOValue(boxDetailListGrid,
									packingDetailListGrid);
						}
						if (isView == true) {
							boxDetailListGrid.setCanEdit(false);
						} else {
							boxDetailListGrid.setCanEdit(true);
						}
					}
				});

		// 选择已拣货记录处理
		txtPickingListNumber.addIconClickHandler(new IconClickHandler() {
			@Override
			public void onIconClick(IconClickEvent event) {
				PickingRecordSelectWindow objWindow = new PickingRecordSelectWindow(
						"已拣货记录处理", txtPickingListNumber.getPageRect(),
						"icons/add.gif", mainForm, packingDetailListGrid);
				ShowWindowTools.showWondow(txtPickingListNumber.getPageRect(),
						objWindow, -1);
			}
		});

		// 保存按钮(装箱单)
		final IButton btnSave = new IButton();
		btnSave.setTitle("保存");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (txtPickingListNumber.getAttribute("Value") == null
						|| txtPickingListNumber.getAttribute("Value")
								.equals("")) {
					SC.say("请选择一个配料单！");
					return;
				}

				// 设置装箱明细数据
				ListGridRecord[] packingDetailRecord = new ListGridRecord[packingDetailListGrid
						.getTotalRows()];
				for (int i = 0; i < packingDetailListGrid.getTotalRows(); i++) {
					packingDetailRecord[i] = (ListGridRecord) packingDetailListGrid
							.getEditedRecord(i);
				}
				mainForm.setValue("packingListPartItemsList",
						packingDetailRecord);

				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData,
							DSRequest request) {
						String packingListID = response.getData()[0]
								.getAttribute("id");
						txtPackingListID.setValue(packingListID);
						SC.say("保存成功！");

						Criteria criteria = new Criteria();
						criteria.addCriteria("temp",
								String.valueOf(Math.random()));
						criteria.addCriteria("packingListID", ""
								+ packingListID);
						packingDetailListGrid.fetchData(criteria);

						if (isFromPickingRecord == true) {
							headGrid.fetchData(new Criteria("temp", String
									.valueOf(Math.random())));
							detailGrid.setData(new ListGridRecord[] {});
						}
					}
				});
			}
		});

		// 返回按钮(装箱单)
		final IButton btnReturn = new IButton();
		btnReturn.setTitle("返回");
		btnReturn.setWidth(65);
		btnReturn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		// 添加箱子按钮
		final IButton btnAddBox = new IButton();
		btnAddBox.setTitle("添加箱子");
		btnAddBox.setWidth(80);
		btnAddBox.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (txtPackingListID.getValue() != null
						&& !txtPackingListID.getValue().toString().equals("")) {
					HashMap map = new HashMap();
					map.put("packingListID", ""
							+ txtPackingListID.getValue().toString());
					boxDetailListGrid.startEditingNew(map);

					// 设置总箱数
					if (txtBoxQty.getValue() != null) {
						txtBoxQty.setValue(Integer.valueOf(String
								.valueOf(txtBoxQty.getValue())) + 1);
					} else {
						txtBoxQty.setValue(1);
					}
				} else {
					SC.say("请先保存装箱单信息！");
					return;
				}
			}
		});

		// 删除箱子按钮
		final IButton btnDelBox = new IButton();
		btnDelBox.setTitle("删除箱子");
		btnDelBox.setWidth(80);
		btnDelBox.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (boxDetailListGrid.getSelection().length == 1) {
					boxDetailListGrid.removeSelectedData();

					// 设置总箱数
					if (txtBoxQty.getValue() != null) {
						txtBoxQty.setValue(Integer.valueOf(String
								.valueOf(txtBoxQty.getValue())) - 1);
					} else {
						txtBoxQty.setValue(0);
					}
				} else {
					SC.say("请选择一条记录进行删除！");
				}
			}
		});

		// 保存按钮(箱子)
		final IButton btnSaveBox = new IButton();
		btnSaveBox.setTitle("保存");
		btnSaveBox.setWidth(65);
		btnSaveBox.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (txtPackingListID.getValue() != null
						&& !txtPackingListID.getValue().toString().equals("")) {
					boxDetailListGrid.saveAllEdits(new Function() {
						@Override
						public void execute() {
							SC.say("保存成功！");

							Criteria criteria = new Criteria();
							criteria.addCriteria("temp",
									String.valueOf(Math.random()));
							criteria.addCriteria("packingListID", ""
									+ txtPackingListID.getValue().toString());
							boxDetailListGrid.fetchData(criteria,
									new DSCallback() {
										@Override
										public void execute(
												DSResponse response,
												Object rawData,
												DSRequest request) {
											// 加载装箱单备件明细Grid中的分箱号
											setBoxNOValue(boxDetailListGrid,
													packingDetailListGrid);
										}
									});
						}
					});
				} else {
					SC.say("请先保存装箱单信息！");
					return;
				}
			}
		});

		mainForm.setFields(txtPackingListID, txtPackingListNumber,
				txtPickingListNumber, selBusinessType, txtOrderID,
				txtOrderNumber, txtContractNumber, txtPriority,
				txtPickingListID, txtDeliveryDate, txtBoxQty);

		if (isFromPickingRecord == false) {
			mainForm.setDataSource(headGrid.getDataSource());
			shipperForm.setDataSource(headGrid.getDataSource());

			if (updateFlg == true) {
				final Record record = headGrid.getSelectedRecord();
				mainForm.editRecord(record);
				shipperForm.editRecord(record);
			}
		} else {
			mainForm.setDataSource(dataSource);
			shipperForm.setDataSource(dataSource);
			Record record = headGrid.getSelectedRecord();

			// 配料单ID
			txtPickingListID.setValue(record.getAttribute("id"));
			// 配料单号
			txtPickingListNumber.setValue(record
					.getAttribute("pickingListNumber"));
			// 指令ID
			txtOrderID.setValue(record.getAttribute("orderID"));
			// 指令编号
			txtOrderNumber.setValue(record.getAttribute("orderNumber"));
			// 合同编号
			txtContractNumber.setValue(record.getAttribute("contractNumber"));
			// 业务类型
			selBusinessType.setValue(record.getAttribute("businessType"));
			// 优先级
			txtPriority.setValue(record.getAttribute("priority"));
			// 交货日期
			txtDeliveryDate.setValue(record.getAttributeAsDate("deliveryDate"));
			// 发货人姓名
			txtShipper.setValue(record.getAttribute("shipper"));
			// 发货单位
			txtCompanyOfShipper.setValue(record
					.getAttribute("companyOfShipper"));
			// 发货地址及详细地址
			txtAddressOfShipper.setValue(record
					.getAttribute("addressOfShipper"));
			// 发货人联系方式
			txtTelephonOfShipper.setValue(record
					.getAttribute("telephonOfShipper"));
			// 收货人姓名
			txtConsignee.setValue(record.getAttribute("consignee"));
			// 收货单位
			txtCompanyOfConsignee.setValue(record
					.getAttribute("companyOfConsignee"));
			// 收货地址及详细信息
			txtAddressOfConsignee.setValue(record
					.getAttribute("addressOfConsignee"));
			// 收货人联系方式
			txtTelephoneOfConsignee.setValue(record
					.getAttribute("telephoneOfConsignee"));
		}

		final BtnsHLayout layoutHeadBtn = new BtnsHLayout();
		layoutHeadBtn.addMember(btnSave);
		layoutHeadBtn.addMember(btnReturn);

		final BtnsHLayout layoutBoxBtn = new BtnsHLayout();
		layoutBoxBtn.addMember(btnAddBox);
		layoutBoxBtn.addMember(btnDelBox);
		layoutBoxBtn.addMember(btnSaveBox);

		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(shipperForm);
		layout.addMember(packingDetailListGrid);
		layout.addMember(layoutHeadBtn);
		layout.addMember(boxDetailListGrid);
		layout.addMember(layoutBoxBtn);

		if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
			Utils.setReadOnlyForm(shipperForm);
			btnSave.setVisible(false);
			btnReturn.setVisible(false);
			layoutBoxBtn.setVisible(false);
		}

		SetWindow.SetWindowLayout(windowTitle, false, iconUrl, rect, objWindow,
				layout);
	}

	// 加载装箱单备件明细Grid中的分箱号
	private void setBoxNOValue(ListGrid boxDetailListGrid,
			ListGrid packingDetailListGrid) {
		Map<String, String> valueMaps = new HashMap<String, String>();
		packingDetailListGrid.getField("boxID").setValueMap(valueMaps);
		if (boxDetailListGrid.getTotalRows() > 0) {
			for (int i = 0; i < boxDetailListGrid.getTotalRows(); i++) {
				ListGridRecord curRecord = (ListGridRecord) boxDetailListGrid
						.getEditedRecord(i);
				valueMaps.put(curRecord.getAttribute("id"),
						curRecord.getAttribute("boxNumber"));
			}
		} else {
			valueMaps.put("", "");
		}
		packingDetailListGrid.getField("boxID").setValueMap(valueMaps);
	}
}