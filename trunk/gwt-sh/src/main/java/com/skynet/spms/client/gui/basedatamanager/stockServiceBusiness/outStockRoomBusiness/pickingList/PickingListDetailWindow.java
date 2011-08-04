package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.pickingList;

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
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItemIcon;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.IconClickEvent;
import com.smartgwt.client.widgets.form.fields.events.IconClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class PickingListDetailWindow extends Window {

	/**
	 * 
	 * @param windowTitle
	 * @param isMax
	 * @param rect
	 * @param headGrid
	 * @param detailGrid
	 * @param iconUrl
	 * @param isFromWaitPicking
	 * @param updateFlg
	 * @param dataSource
	 * @param isView
	 */
	@SuppressWarnings("deprecation")
	public PickingListDetailWindow(String windowTitle
			, boolean isMax
			, final Rectangle rect
			, final ListGrid headGrid
			, final ListGrid detailGrid
			, String iconUrl
			, final Boolean isFromWaitPicking
			, final Boolean updateFlg
			, final DataSource dataSource
			,final Boolean isView) {
		final Window objWindow = this;
		setWidth(910);
		setHeight(635);

		final DynamicForm mainForm = new DynamicForm();
		mainForm.setNumCols(6);
		mainForm.setWidth(700);
		mainForm.setColWidths(100, 150, 75, 150, 75, 150);

		// 配料单ID
		final HiddenItem txtPickinglistID = new HiddenItem("id");
		// 配料单号
		final TextItem txtPickingListNumber = new TextItem("pickingListNumber");
		txtPickingListNumber.setWidth(150);
		txtPickingListNumber.setDisabled(true);
		// 业务类型
		final SelectItem selBusinessType = new SelectItem("businessType");
		selBusinessType.setWidth(150);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.csdd.b.BusinessType", selBusinessType);
		selBusinessType.setDisabled(true);
		// 指令ID
		final HiddenItem txtOrderID = new HiddenItem("orderID");
		// 指令单号
		final TextItem txtOrderNumber = new TextItem("orderNumber");
		txtOrderNumber.setWidth(150);
		final FormItemIcon icoInstructionNumbers = new FormItemIcon();
		txtOrderNumber.setIcons(icoInstructionNumbers);
		// 合同编号
		final TextItem txtContractNumber = new TextItem("contractNumber");
		txtContractNumber.setWidth(150);
		txtContractNumber.setDisabled(true);
		// 优先级
		final SelectItem txtPriority = new SelectItem("priority");
		txtPriority.setWidth(150);
		EnumTool.setValueMap("com.skynet.spms.persistence.entity.spmsdd.Priority", txtPriority);
		txtPriority.setDisabled(true);
		// 发货日期
		final CustomDateItem txtDeliveryDate = new CustomDateItem("deliveryDate");
		txtDeliveryDate.setWidth(150);
		txtDeliveryDate.setDisabled(true);
		// 备注
		final TextAreaItem txtMemo = new TextAreaItem("memo");
		txtMemo.setColSpan(3);
		txtMemo.setLength(100);
		txtMemo.setWidth(375);
		txtMemo.setHeight(30);
		// 是否保税
		final RadioGroupItem rdoIsBonded = new RadioGroupItem();  
		rdoIsBonded.setDisabled(true);
		rdoIsBonded.setName("isBonded");  
		rdoIsBonded.setVertical(false);
		rdoIsBonded.setTitle("是否保税");
		rdoIsBonded.setValueMap("是", "否");
        rdoIsBonded.setRedrawOnChange(true);
        rdoIsBonded.setWidth(100);

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
		final TextAreaItem txtTelephonOfShipper = new TextAreaItem("telephonOfShipper");
		txtTelephonOfShipper.setWidth(250);
		txtTelephonOfShipper.setHeight(30);
		txtTelephonOfShipper.setDisabled(true);
		// 收货人姓名
		final TextItem txtConsignee = new TextItem("consignee");
		txtConsignee.setWidth(150);
		txtConsignee.setDisabled(true);
		// 收货单位
		final TextItem txtCompanyOfConsignee = new TextItem("companyOfConsignee");
		txtCompanyOfConsignee.setWidth(250);
		txtCompanyOfConsignee.setDisabled(true);
		// 收货地址及详细信息
		final TextItem txtAddressOfConsignee = new TextItem("addressOfConsignee");
		txtAddressOfConsignee.setWidth(250);
		txtAddressOfConsignee.setDisabled(true);
		// 收货人联系方式
		final TextAreaItem txtTelephoneOfConsignee = new TextAreaItem("telephoneOfConsignee");
		txtTelephoneOfConsignee.setWidth(250);
		txtTelephoneOfConsignee.setHeight(30);
		txtTelephoneOfConsignee.setDisabled(true);
		
		// 地址信息
		final DynamicForm shipperForm = new DynamicForm();
		shipperForm.setGroupTitle("<font color='blue'>收发货人信息</font>");
		shipperForm.setIsGroup(true);
		shipperForm.setNumCols(4);
		shipperForm.setWidth(710);
		shipperForm.setMargin(5);
		shipperForm.setColWidths(100, 250, 100, 250);
		// 地址信息
		shipperForm.setFields(
				txtShipper
				,txtConsignee
				,txtCompanyOfShipper
				,txtCompanyOfConsignee
				,txtAddressOfShipper
				,txtAddressOfConsignee
				,txtTelephonOfShipper
				,txtTelephoneOfConsignee
				);
		
		// 配料备件明细项
		final ListGrid pickingDetailListGrid = new ListGrid();
		// 指令备件明细项
		final DeliveryOrderItemsListGrid deliveryOrderItemsListGrid = new DeliveryOrderItemsListGrid(){
			@Override
			protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {
				String fieldName = this.getFieldName(colNum);
				final ListGrid listGrid = (ListGrid)this;
				if (fieldName.equals("picking")) {
					IButton button = new IButton();
					button.setWidth(this.getField(colNum).getWidth());
					button.setTitle("配料");
					button.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent event) {
							listGrid.selectSingleRecord(record);
							// 指令备件明细ID
							String pickingListID = "";
							if (txtPickinglistID.getValue() != null )
							{
								// 指令备件明细ID
								pickingListID = txtPickinglistID.getValue().toString();
								
								pickingDetailListGrid.setData(new ListGridRecord[]{});
								Criteria criteria = new Criteria();
								criteria.addCriteria("temp", String.valueOf(Math.random()));
								criteria.addCriteria("pickingListID", "" + pickingListID);
								criteria.addCriteria("partNumber", "" + listGrid.getSelectedRecord().getAttribute("partNumber").toString());
								pickingDetailListGrid.fetchData(criteria);
							} else {
								SC.say("请先保存配料信息！");
								return;
							}
							PickingPartDetailWindow objWindow =
								new PickingPartDetailWindow("配料", rect, "showwindow/stock_modify_01.png", listGrid, pickingDetailListGrid, pickingListID);
							ShowWindowTools.showWondow(txtOrderNumber.getPageRect(), objWindow, -1);
						}
					});
					return button;
				} else {
					return null;
				}
			}
		};
		deliveryOrderItemsListGrid.setWidth(710);
		deliveryOrderItemsListGrid.setHeight(165);
		deliveryOrderItemsListGrid.setMargin(5);
		deliveryOrderItemsListGrid.setShowRecordComponents(true);
		deliveryOrderItemsListGrid.setShowRecordComponentsByCell(true);
		deliveryOrderItemsListGrid.setSelectionType(SelectionStyle.SINGLE);
		
		// 获取指令备件明细数据
		String orderDetailModeName = "stockServiceBusiness.outStockRoomBusiness.waitDeliveryOrder";
		String orderDetailDsName = "deliveryOrderItems_dataSource";
		DataSourceTool orderDetailDST = new DataSourceTool();
		orderDetailDST.onCreateDataSource(orderDetailModeName, orderDetailDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						deliveryOrderItemsListGrid.setDataSource(dataSource);
						deliveryOrderItemsListGrid.drawDeliveryOrderItemsListGrid();

						if (isView == true) {
							deliveryOrderItemsListGrid.setCanEdit(false);
						} else {
							deliveryOrderItemsListGrid.setCanEdit(true);
						}
						if (txtOrderID.getValue() != null)
						{
							String orderID = txtOrderID.getValue().toString();
							Criteria criteria = new Criteria();
							criteria.addCriteria("temp", String.valueOf(Math.random()));
							criteria.addCriteria("orderID", "" + orderID);
							deliveryOrderItemsListGrid.fetchData(criteria);
						}
					}
				});
		
		// 选择指令时的处理
		txtOrderNumber.addIconClickHandler(new IconClickHandler() {
			@Override
			public void onIconClick(IconClickEvent  event) {
				DeliveryOrderSelectWindow objWindow =
					new DeliveryOrderSelectWindow("选择发货指令", "showwindow/stock_modify_01.png", txtOrderNumber.getPageRect(), mainForm, deliveryOrderItemsListGrid);
				ShowWindowTools.showWondow(txtOrderNumber.getPageRect(), objWindow, -1);
			}
		});
		
		// 配料备件明细项
		pickingDetailListGrid.setMargin(5);
		pickingDetailListGrid.setWidth(710);
		pickingDetailListGrid.setHeight(165);
		// 获取配料备件明细数据
		String pickingDetailModeName = "stockServiceBusiness.outStockRoomBusiness.pickingList";
		String pickingDetailDsName = "pickingListPartItems_dataSource";
		DataSourceTool pickingDetailDST = new DataSourceTool();
		pickingDetailDST.onCreateDataSource(pickingDetailModeName, pickingDetailDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickingDetailListGrid.setDataSource(dataSource);

						// 件号
						ListGridField partNumberFiled = new ListGridField("partNumber","件号");
						// 件描述
						ListGridField partNameField=new ListGridField("partName","件描述");
						// 序号/批号
						ListGridField partSerialNumberFiled = new ListGridField("partSerialNumber","序号/批号");
						// 制造商
						ListGridField manufacturerFiled = new ListGridField("manufacturer","制造商");
						// 实发数量
						ListGridField sendQtyFiled = new ListGridField("sendQty","实发数量");
						sendQtyFiled.setType(ListGridFieldType.INTEGER);
						// 单位
						ListGridField unitFiled = new ListGridField("unit","单位");
						// 备件状况
						ListGridField partStatusFiled = new ListGridField("partStatus","备件状况");
						// 库房编号
						ListGridField stockRoomNumberFiled = new ListGridField("stockRoomNumber","库房编号");
						// 货位编号
						ListGridField cargoSpaceNumberFiled = new ListGridField("cargoSpaceNumber","货位编号");
						// 剩余寿命
						ListGridField lifeFiled = new ListGridField("life","剩余寿命");

						partNumberFiled.setCanEdit(false);
						partSerialNumberFiled.setCanEdit(false);
						partNameField.setCanFilter(false);
						manufacturerFiled.setCanEdit(false);
						sendQtyFiled.setCanEdit(false);
						unitFiled.setCanEdit(false);
						partStatusFiled.setCanEdit(false);
						partStatusFiled.setCanEdit(false);
						stockRoomNumberFiled.setCanEdit(false);
						cargoSpaceNumberFiled.setCanEdit(false);
						lifeFiled.setCanEdit(false);
						
						pickingDetailListGrid.setFields(
								partNumberFiled
								,partSerialNumberFiled
								,partNameField
								,manufacturerFiled
								,sendQtyFiled
								,unitFiled
								,partStatusFiled
								,stockRoomNumberFiled
								,cargoSpaceNumberFiled
								,lifeFiled
								);
						if (isView == true) {
							pickingDetailListGrid.setCanEdit(false);
						} else {
							pickingDetailListGrid.setCanEdit(true);
						}
						
					}
				});
		
		// 根据选择的指令备件项，取得相应的配料明细
		deliveryOrderItemsListGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				pickingDetailListGrid.setData(new ListGridRecord[]{});
				if (txtPickinglistID.getValue() != null)
				{
					// 指令备件明细ID
					String pickingListID = txtPickinglistID.getValue().toString();
					Criteria criteria = new Criteria();
					criteria.addCriteria("temp", String.valueOf(Math.random()));
					criteria.addCriteria("pickingListID", "" + pickingListID);
					criteria.addCriteria("partNumber", "" + deliveryOrderItemsListGrid.getSelectedRecord().getAttribute("partNumber").toString());
					pickingDetailListGrid.fetchData(criteria);
				}
			}
		});

		// 保存按钮
		final IButton btnSave = new IButton();
		btnSave.setTitle("保存");
		btnSave.setWidth(65);
		btnSave.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (txtOrderNumber.getAttribute("Value") == null
						|| txtOrderNumber.getAttribute("Value").equals("")) {
					SC.say("请选择一个指令单号！");
					return;
				}
				
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						String pickingListID = response.getData()[0].getAttribute("id");
						txtPickinglistID.setValue(pickingListID);
						SC.say("保存成功！");
						
						if (isFromWaitPicking == true) {
							Criteria criteria = new Criteria();
							criteria.addCriteria("temp", String.valueOf(Math.random()));
							headGrid.fetchData(criteria);
							detailGrid.setData(new ListGridRecord[]{});	
						}
					}
				});
			}
		});

		// 根据策略自动配料
		final IButton btnAutoPicking = new IButton();
		btnAutoPicking.setTitle("根据策略自动配料");
		btnAutoPicking.setLeft(60);
		btnAutoPicking.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
			}
		});

		// 返回按钮
		final IButton btnReturn = new IButton();
		btnReturn.setTitle("取消");
		btnReturn.setWidth(65);
		btnReturn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				ShowWindowTools.showCloseWindow(rect, objWindow, -1);
			}
		});

		mainForm.setFields(txtPickinglistID
						,txtPickingListNumber
						,selBusinessType
						,txtOrderID
						,txtOrderNumber
						,txtContractNumber
						,txtPriority
						,txtDeliveryDate
						,txtMemo
						,rdoIsBonded
						);
		if (isFromWaitPicking == false) {
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

			// 业务类型
			selBusinessType.setValue(record.getAttribute("businessType"));
			// 指令ID
			txtOrderID.setValue(record.getAttribute("orderID"));
			// 指令单号
			txtOrderNumber.setValue(record.getAttribute("orderNumber"));
			// 合同编号
			txtContractNumber.setValue(record.getAttribute("contractNumber"));
			// 优先级
			txtPriority.setValue(record.getAttribute("priority"));
			// 交货日期
			txtDeliveryDate.setValue(record.getAttributeAsDate("deliveryDate"));
			// 是否保税
			rdoIsBonded.setValue(record.getAttribute("isBonded"));
			// 发货人姓名
			txtShipper.setValue(record.getAttribute("shipper"));
			// 发货单位
			txtCompanyOfShipper.setValue(record.getAttribute("companyOfShipper"));
			// 发货地址及详细地址
			txtAddressOfShipper.setValue(record.getAttribute("addressOfShipper"));
			// 发货人联系方式
			txtTelephonOfShipper.setValue(record.getAttribute("telephonOfShipper"));
			// 收货人姓名
			txtConsignee.setValue(record.getAttribute("consignee"));
			// 收货单位
			txtCompanyOfConsignee.setValue(record.getAttribute("companyOfConsignee"));
			// 收货地址及详细信息
			txtAddressOfConsignee.setValue(record.getAttribute("addressOfConsignee"));
			// 收货人联系方式
			txtTelephoneOfConsignee.setValue(record.getAttribute("telephoneOfConsignee"));
		}

		final BtnsHLayout layoutHeadBtn = new BtnsHLayout(); 
		layoutHeadBtn.addMember(btnSave);
		layoutHeadBtn.addMember(btnReturn);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(shipperForm);
		layout.addMember(layoutHeadBtn);
		layout.addMember(deliveryOrderItemsListGrid);
		layout.addMember(pickingDetailListGrid);

		if (isView == true) {
			Utils.setReadOnlyForm(mainForm);
			Utils.setReadOnlyForm(shipperForm);
			btnSave.setVisible(false);
			btnReturn.setVisible(false);
		}
		
		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}