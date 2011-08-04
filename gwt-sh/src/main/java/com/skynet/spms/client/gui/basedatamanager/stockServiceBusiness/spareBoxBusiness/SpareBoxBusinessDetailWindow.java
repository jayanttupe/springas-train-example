package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.spareBoxBusiness;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.base.CustomDateItem;
import com.skynet.spms.client.gui.base.SetWindow;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
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

public class SpareBoxBusinessDetailWindow extends Window {

	/**
	 * @param windowTitle
	 * @param isMax
	 * @param srcRect
	 * @param listGrid
	 * @param iconUrl
	 */
	public SpareBoxBusinessDetailWindow(String windowTitle, boolean isMax,
			final Rectangle rect,
			final ListGrid listGrid,
			String iconUrl,
			Boolean updateFlg) {
		final Window objWindow = this;
		setWidth(850);
		setHeight(550);
		
		final DynamicForm mainForm = new DynamicForm();
		mainForm.setNumCols(4);
		mainForm.setWidth(550);
		mainForm.setColWidths(65, 150, 65, 150);
		mainForm.setDataSource(listGrid.getDataSource());
		if (updateFlg == true)
		{
			final Record record = listGrid.getSelectedRecord();
			mainForm.editRecord(record);
		}

		// 配料单ID
		final HiddenItem txtPickinglistID = new HiddenItem("id");
		// 配料单号
		final TextItem txtPickingListNumber = new TextItem("pickingListNumber", "配料单号");
		// 业务类型
		final SelectItem selBusinessType = new SelectItem("businessType", "业务类型");
		selBusinessType.setValueMap("修理"
									,"索赔"
									,"交换"
									,"租赁(入)"
									,"无故障退库"
									,"回购"
									,"采购"
									,"租凭(还回)"
									,"未用退库"
									,"寄售");
		// 指令ID
		final HiddenItem txtInstructionID = new HiddenItem("instructionID");
		// 指令单号
		final TextItem txtInstructionNumbers = new TextItem("instructionNumbers", "指令单号");
		final FormItemIcon icoInstructionNumbers = new FormItemIcon();
		txtInstructionNumbers.setIcons(icoInstructionNumbers);
		// 合同编号
		final TextItem txtContractNumber = new TextItem("contractNumber", "合同编号");
		txtContractNumber.setDisabled(true);
		// 收货单位
		final TextItem txtDelivery = new TextItem("delivery", "收货单位");
		txtDelivery.setDisabled(true);
		// 优先级
		final TextItem txtPriority = new TextItem("priority", "优先级");
		txtPriority.setDisabled(true);
		// 交货日期
		final CustomDateItem txtDeliveryDate = new CustomDateItem("deliveryDate", "交货日期");
		txtDeliveryDate.setDisabled(true);
		// 备注
		final TextAreaItem txtMemo = new TextAreaItem("memo", "备注");
		txtMemo.setColSpan(3);
		txtMemo.setLength(100);
		txtMemo.setWidth(370);
		txtMemo.setHeight(40);

		// 配料备件明细项
		final ListGrid pickingDetailListGrid = new ListGrid();
		// 指令备件明细项
		final ListGrid pickingInstructionDetailListGrid = new ListGrid(){
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
							SpareBoxPartDetailWindow objWindow =
								new SpareBoxPartDetailWindow("配料", rect, "icons/add.gif", listGrid, pickingDetailListGrid, pickingListID);
							ShowWindowTools.showWondow(txtInstructionNumbers.getPageRect(), objWindow, -1);
						}
					});
					return button;
				} else {
					return null;
				}
			}
		};
		pickingInstructionDetailListGrid.setWidth(650);
		pickingInstructionDetailListGrid.setHeight(150);
		pickingInstructionDetailListGrid.setMargin(5);
		pickingInstructionDetailListGrid.setShowRecordComponents(true);
		pickingInstructionDetailListGrid.setShowRecordComponentsByCell(true);
		pickingInstructionDetailListGrid.setSelectionType(SelectionStyle.SINGLE);
		
		// 获取指令备件明细数据
		String instructionDetailModeName = "stockServiceBusiness.outStockRoomBusiness.pickingList";
		String instructionDetailDsName = "pickingInstructionItems_dataSource";
		DataSourceTool instructionDetailDST = new DataSourceTool();
		instructionDetailDST.onCreateDataSource(instructionDetailModeName, instructionDetailDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickingInstructionDetailListGrid.setDataSource(dataSource);

						ListGridField pickingFiled = new ListGridField("picking","操作");
						pickingFiled.setWidth(50);
						pickingFiled.setAlign(Alignment.CENTER);
						// 项号
						ListGridField itemNumberFiled = new ListGridField("itemNumber","项号");
						// 件号
						ListGridField partNumberFiled = new ListGridField("partNumber","件号");
						// 制造商
						ListGridField manufacturerFiled = new ListGridField("manufacturer","制造商");
						// 单位
						ListGridField unitFiled = new ListGridField("unit","单位");
						// 备件状况
						ListGridField partStatusFiled = new ListGridField("partStatus","备件状况");
						// 备件类型
						ListGridField partTypeFiled = new ListGridField("partType","备件类型");
						// 当前库存数量
						ListGridField stockQtyFiled = new ListGridField("stockQty","当前库存数量");
						// 应发数量
						ListGridField qtyFiled = new ListGridField("qty","应发数量");
						// 已配发数量
						ListGridField sendQtyFiled = new ListGridField("sendQty","已配发数量");
						// 备注
						ListGridField memoFiled = new ListGridField("memo","备注");

						itemNumberFiled.setCanEdit(false);
						pickingFiled.setCanEdit(true);
						partNumberFiled.setCanEdit(false);
						manufacturerFiled.setCanEdit(false);
						qtyFiled.setCanEdit(false);
						unitFiled.setCanEdit(false);
						partStatusFiled.setCanEdit(false);
						partTypeFiled.setCanEdit(false);
						stockQtyFiled.setCanEdit(false);
						sendQtyFiled.setCanEdit(false);
						memoFiled.setCanEdit(false);

						pickingInstructionDetailListGrid.setFields(
								itemNumberFiled
								,pickingFiled						
								,partNumberFiled
								,manufacturerFiled
								,qtyFiled
								,unitFiled
								,partStatusFiled
								,partTypeFiled
								,stockQtyFiled
								,sendQtyFiled
								,memoFiled
								);
						
						if (txtInstructionID.getValue() != null)
						{
							String instructionID = txtInstructionID.getValue().toString();
							pickingInstructionDetailListGrid.fetchData(new Criteria("instructionID", instructionID));
						}
					}
				});
		
		// 选择指令时的处理
		txtInstructionNumbers.addIconClickHandler(new IconClickHandler() {
			@Override
			public void onIconClick(IconClickEvent  event) {
				SpareBoxBusinessSelectWindow objWindow =
					new SpareBoxBusinessSelectWindow("选择配料指令", txtInstructionNumbers.getPageRect(), "icons/add.gif", mainForm, pickingInstructionDetailListGrid);
				ShowWindowTools.showWondow(txtInstructionNumbers.getPageRect(), objWindow, -1);
			}
		});
		
		// 配料备件明细项
		pickingDetailListGrid.setMargin(5);
		pickingDetailListGrid.setWidth(650);
		pickingDetailListGrid.setHeight(160);
		// 获取配料备件明细数据
		String pickingDetailModeName = "stockServiceBusiness.outStockRoomBusiness.pickingList";
		String pickingDetailDsName = "pickingListPartItems_dataSource";
		DataSourceTool pickingDetailDST = new DataSourceTool();
		pickingDetailDST.onCreateDataSource(pickingDetailModeName, pickingDetailDsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickingDetailListGrid.setDataSource(dataSource);

						// 项号
						ListGridField itemNumberFiled = new ListGridField("itemNumber","项号");
						// 件号
						ListGridField partNumberFiled = new ListGridField("partNumber","件号");
						// 关键字
						ListGridField keyWordField=new ListGridField("keyWord","关键字");
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
						// 状态
						ListGridField StatusFiled = new ListGridField("Status","状态");
						// 备注
						ListGridField memoFiled = new ListGridField("memo","备注");

						itemNumberFiled.setCanEdit(false);
						partNumberFiled.setCanEdit(false);
						partSerialNumberFiled.setCanEdit(false);
						keyWordField.setCanFilter(true);
						manufacturerFiled.setCanEdit(false);
						sendQtyFiled.setCanEdit(false);
						unitFiled.setCanEdit(false);
						partStatusFiled.setCanEdit(false);
						partStatusFiled.setCanEdit(false);
						stockRoomNumberFiled.setCanEdit(false);
						cargoSpaceNumberFiled.setCanEdit(false);
						lifeFiled.setCanEdit(false);
						StatusFiled.setCanEdit(false);
						memoFiled.setCanEdit(false);
						
						pickingDetailListGrid.setFields(itemNumberFiled
								,partNumberFiled
								,partSerialNumberFiled
								,keyWordField
								,manufacturerFiled
								,sendQtyFiled
								,unitFiled
								,partStatusFiled
								,stockRoomNumberFiled
								,cargoSpaceNumberFiled
								,lifeFiled
								,StatusFiled
								,memoFiled
								);
					}
				});
		
		// 根据选择的指令备件项，取得相应的配料明细
		pickingInstructionDetailListGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				pickingDetailListGrid.setData(new ListGridRecord[]{});
				if (txtPickinglistID.getValue() != null )
				{
					// 指令备件明细ID
					String pickingListID = txtPickinglistID.getValue().toString();
					Criteria criteria = new Criteria();
					criteria.addCriteria("temp", String.valueOf(Math.random()));
					criteria.addCriteria("pickingListID", "" + pickingListID);
					criteria.addCriteria("partNumber", "" + pickingInstructionDetailListGrid.getSelectedRecord().getAttribute("partNumber").toString());
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
				if (txtInstructionNumbers.getAttribute("Value") == null
						|| txtInstructionNumbers.getAttribute("Value").equals("")) {
					SC.say("请选择一个指令单号！");
					return;
				}
				
				mainForm.saveData(new DSCallback() {
					@Override
					public void execute(DSResponse response, Object rawData, DSRequest request) {
						String pickingListID = response.getData()[0].getAttribute("id");
						txtPickinglistID.setValue(pickingListID);
						SC.say("保存成功！");
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
						,txtInstructionID
						,txtInstructionNumbers
						,txtContractNumber
						,txtDelivery
						,txtPriority
						,txtDeliveryDate
						,txtMemo
						);

		final BtnsHLayout layoutHeadBtn = new BtnsHLayout(); 
		layoutHeadBtn.addMember(btnSave);
		layoutHeadBtn.addMember(btnReturn);
		layoutHeadBtn.addMember(btnAutoPicking);
		
		VLayout layout = new VLayout();
		layout.setMargin(5);
		layout.addMember(mainForm);
		layout.addMember(layoutHeadBtn);
		layout.addMember(pickingInstructionDetailListGrid);
		layout.addMember(pickingDetailListGrid);
		
		SetWindow.SetWindowLayout(windowTitle
				,false
				,iconUrl
				,rect
				,objWindow
				,layout);
	}
}