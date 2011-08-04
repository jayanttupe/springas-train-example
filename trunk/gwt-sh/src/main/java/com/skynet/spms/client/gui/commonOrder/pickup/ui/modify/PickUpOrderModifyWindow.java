package com.skynet.spms.client.gui.commonOrder.pickup.ui.modify;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.i18n.CommonOrderModuleConst;
import com.skynet.spms.client.gui.commonOrder.pickup.model.DataModelLocator;
import com.skynet.spms.client.service.ContractInfoService;
import com.skynet.spms.client.service.ContractInfoServiceAsync;
import com.skynet.spms.client.vo.contractManagement.Contract;
import com.skynet.spms.client.vo.contractManagement.ContractItem;
import com.smartgwt.client.core.Function;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.EditorEnterEvent;
import com.smartgwt.client.widgets.grid.events.EditorEnterHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 提货添加窗体
 * 
 * @author tqc
 * 
 */
public class PickUpOrderModifyWindow extends BaseWindow {


	/** 合同详细 Grid */
	ListGrid contractDetailListGrid = null;
	/** 装箱 Grid */
	ListGrid vanningListGrid = null;
	/** 装箱物品 Grid */
	ListGrid vanningItemListGrid = null;

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public PickUpOrderModifyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl,
			String contractNumber, String contractType) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
		this.loadContractInfo(DataModelLocator.getInstance().modifyOrderGrid
				.getSelectedRecord().getAttribute("contractID"), contractType);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);
		contractDetailListGrid = new ListGrid();
		buildContractDetailListGrid();
		vanningListGrid = new ListGrid();
		vanningItemListGrid = new ListGrid();
		vanningItemListGrid.setCanRemoveRecords(true);
		/**
		 * 构建箱信息grid数据源
		 */
		final DataSourceTool tool = new DataSourceTool();
		tool.onCreateDataSource(
				"supplierSupport.repairClaim.repairDeliveryOrder",
				"PickupDeliveryVanning_datasource", new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						vanningListGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("orderID", DataModelLocator
								.getInstance().modifyOrderGrid
								.getSelectedRecord().getAttribute("id"));
						criteria.addCriteria("_r",
								String.valueOf(Math.random()));
						vanningListGrid.fetchData(criteria, new DSCallback() {
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {
								buildVanningListGrid();
							}
						});

					}
				});
		/**
		 * 构建发货单明细grid数据源
		 */
		tool.onCreateDataSource(
				"supplierSupport.repairClaim.repairDeliveryOrder",
				"PickupDeliveryVanningItems_datasource",
				new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						vanningItemListGrid.setDataSource(dataSource);
						vanningItemListGrid.setAutoSaveEdits(false);
						Criteria criteria = new Criteria();
						criteria.addCriteria("orderId", DataModelLocator
								.getInstance().modifyOrderGrid
								.getSelectedRecord().getAttribute("id"));
						criteria.addCriteria("_r",
								String.valueOf(Math.random()));
						vanningItemListGrid.fetchData(criteria,
								new DSCallback() {
									@Override
									public void execute(DSResponse response,
											Object rawData, DSRequest request) {
										buildVanningItemListGrid();
										redrawPackNumber();
									}
								});

					}
				});

		this.setTitle(ui.modify_picup_order());
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();

		PickUpBaseModifyForm baseForm = new PickUpBaseModifyForm();
		baseForm.setHeight(313);
		vmain.addMember(baseForm);

		VLayout twoView = getShowGridView();
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);

		return vmain;
	}

	private VLayout getShowGridView() {
		final CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);
		VLayout v = new VLayout();
		v.setMembersMargin(5);
		v.setLayoutTopMargin(3);
		HLayout h = new HLayout();
		h.setWidth100();
		h.setHeight100();
		h.setMembersMargin(10);
		VLayout leftLayout = new VLayout();
		HLayout titleHL = new HLayout();
		titleHL.setHeight(30);
		titleHL.setWidth100();
		Label leftLb = new Label(ui.by_contract_item());
		leftLb.setWidth("80");
		leftLb.setHeight("15");
		IButton batchAddBtn = new IButton(ui.put_in());
		batchAddBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (ValidateUtil.isRecordSelected(contractDetailListGrid)) {
					addToVanningItemList(contractDetailListGrid
							.getSelectedRecord());
				}
			}

		});
		titleHL.addMember(leftLb);
		titleHL.addMember(batchAddBtn);
		leftLayout.addMember(titleHL);
		leftLayout.addMember(contractDetailListGrid);
		VLayout rightLayout = new VLayout();
		HLayout titleRightTopHL = new HLayout();
		titleRightTopHL.setHeight(30);
		titleRightTopHL.setWidth100();

		Label titleRightTopLb = new Label(ui.box_info());
		titleRightTopLb.setWidth("100");
		titleRightTopLb.setHeight("15");
		
		
		IButton addBtn = new IButton(ui.create_box());
		addBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				vanningListGrid.startEditingNew();
			}
		});
		

		IButton saveboxBtn = new IButton(ui.save_box_info());
		saveboxBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				vanningListGrid.saveAllEdits(new Function() {
					@Override
					public void execute() {
						Criteria criteria = new Criteria();
						criteria.addCriteria("orderID", DataModelLocator
								.getInstance().modifyOrderGrid
								.getSelectedRecord().getAttribute("id"));
						criteria.addCriteria("_r",
								String.valueOf(Math.random()));
						vanningListGrid.fetchData(criteria, new DSCallback() {
							@Override
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {
								redrawPackNumber();
							}
						});
					}
				});
			}
		});
		
		IButton removeBoxBtn = new IButton(ui.drop_box());
		removeBoxBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(ValidateUtil.isRecordSelected(vanningListGrid)){
					//判断是否有指令明细引用到箱子
					String boxId=vanningListGrid.getSelectedRecord().getAttribute("id");
					RecordList list = vanningItemListGrid.getRecordList();
					for (int i = 0; null != list && i < list.getLength(); i++) {
						Record r = list.get(i);
						if (null != r) {
							String vanningID = r.getAttribute("vanningID");
							if (null != vanningID && !"".equals(vanningID)) {
								if (vanningID.equals(boxId)) {
									SC.warn(ui.msg_box_have_items());
									return;
								}
							}
						}
					}
					vanningListGrid.removeData(vanningListGrid.getSelectedRecord());
					
				}
			}
		});

		titleRightTopHL.addMember(titleRightTopLb);
		titleRightTopHL.addMember(addBtn);
		titleRightTopHL.addMember(saveboxBtn);
		titleRightTopHL.addMember(removeBoxBtn);
		rightLayout.addMember(titleRightTopHL);
		rightLayout.addMember(vanningListGrid);

		Label rightBottomLb = new Label(ui.picup_order_item());
		rightBottomLb.setHeight("15");
		rightLayout.addMember(rightBottomLb);
		rightLayout.addMember(vanningItemListGrid);
		h.addMember(leftLayout);
		h.addMember(rightLayout);

		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		btnGroup.setLayoutLeftMargin(50);
		btnGroup.setMargin(5);

		IButton btnSave = new IButton(ui.save_order_item());
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				vanningItemListGrid.saveAllEdits(new Function() {
					@Override
					public void execute() {
						Criteria criteria = new Criteria();
						criteria.addCriteria("orderId", DataModelLocator
								.getInstance().modifyOrderGrid
								.getSelectedRecord().getAttribute("id"));
						criteria.addCriteria("_r",
								String.valueOf(Math.random()));
						vanningItemListGrid.fetchData(criteria,
								new DSCallback() {
									@Override
									public void execute(DSResponse response,
											Object rawData, DSRequest request) {
										SC.say(ui.opp_success());
									}
								});
					}
				});
			}
		});
		btnGroup.addMember(btnSave);
		v.addMember(h);
		v.addMember(btnGroup);
		return v;
	}

	/**
	 * 依据合同明细项选择Grid
	 * 
	 * @return
	 */
	private void buildContractDetailListGrid() {
		final CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);
		contractDetailListGrid.setShowRecordComponents(true);
		contractDetailListGrid.setShowRecordComponentsByCell(true);
		contractDetailListGrid.setAutoFetchData(true);
		contractDetailListGrid.setHeight("256");
		ListGridField field1 = new ListGridField("contractNumber", ui.contractNumber());
		ListGridField field2 = new ListGridField("partNumber", ui.partNumber());
		ListGridField field3 = new ListGridField("quantity",ui.quantity());
		ListGridField field4 = new ListGridField("unitPriceAmount", ui.unitPriceAmount());
		ListGridField field5 = new ListGridField("totalAmount", ui.totalAmount());
		contractDetailListGrid
				.setFields(field1, field2, field3, field4, field5);
	}

	/**
	 * 提货指令装箱信息
	 * 
	 * @return
	 */
	private void buildVanningListGrid() {
		final CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);
		vanningListGrid.setHeight("120");
		vanningListGrid.setShowRowNumbers(true);
		ListGridField field0 = new ListGridField("orderID",ui.orderID());
		field0.setHidden(true);
		ListGridField field1 = new ListGridField("itemNo", ui.itemNo());
		field1.setHidden(true);
		ListGridField field2 = new ListGridField("packingListNumber",ui.packingListNumber());
		field2.setRequired(true);
		ListGridField field3 = new ListGridField("pacakgeNumber", ui.pacakgeNumber());
		field3.setRequired(true);
		ListGridField field4 = new ListGridField("containerSizeandWeight", ui.containerSizeandWeight());
		field4.setRequired(true);
		ListGridField field5 = new ListGridField("billOfLadingWeight", ui.billOfLadingWeight());
		field5.setRequired(true);
		field5.setAlign(Alignment.RIGHT);
		vanningListGrid.setFields(field0, field1, field2, field3, field4,
				field5);

		// 编辑键入事件
		vanningListGrid.addEditorEnterHandler(new EditorEnterHandler() {
			public void onEditorEnter(EditorEnterEvent event) {
				// 设置外键
				vanningListGrid.setEditValue(event.getRowNum(), "orderID",
						DataModelLocator.getInstance().modifyOrderGrid
								.getSelectedRecord().getAttribute("id"));
				// 设置项号
				vanningListGrid.setEditValue(event.getRowNum(), "itemNo",
						event.getRowNum() + 1);
			}
		});
	}

	/**
	 * 提货指令明细项
	 * 
	 * @return
	 */
	private void buildVanningItemListGrid() {
		final CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);
		vanningItemListGrid.setCanRemoveRecords(true);
		vanningItemListGrid.setEditEvent(ListGridEditEvent.CLICK);
		vanningItemListGrid.setAutoFetchData(true);
		vanningItemListGrid.setHeight("120");
		vanningItemListGrid.setShowRowNumbers(true);
		ListGridField field0 = new ListGridField("orderID",ui.orderID());
		field0.setHidden(true);
		ListGridField field1 = new ListGridField("itemNo",ui.itemNo());
		field1.setHidden(true);
		ListGridField field2 = new ListGridField("contractNumber",ui.contractNumber());
		field2.setHidden(true);
		ListGridField field3 = new ListGridField("partNumber",ui.partNumber());
		field3.setCanEdit(false);
		ListGridField field4 = new ListGridField("vanningID",ui.vanningID());
		field4.setRequired(true);
		field4.setEditorType(new SelectItem());
		ListGridField field6 = new ListGridField("quantity",ui.picupnumer());
		field6.setCanEdit(false);
		ListGridField field7 = new ListGridField("unitPriceAmount",ui.unitPriceAmount());
		field7.setCanEdit(false);
		ListGridField field8 = new ListGridField("totalAmount", ui.totalAmount());
		field8.setCanEdit(false);

		vanningItemListGrid.setFields(field0, field1, field2, field3, field4,
				field6, field7, field8);

		// 编辑键入事件
		vanningItemListGrid.addEditorEnterHandler(new EditorEnterHandler() {
			public void onEditorEnter(EditorEnterEvent event) {
				// 设置外键
				vanningItemListGrid.setEditValue(event.getRowNum(), "orderID",
						DataModelLocator.getInstance().modifyOrderGrid
								.getSelectedRecord().getAttribute("id"));
				// 设置项号
				vanningItemListGrid.setEditValue(event.getRowNum(), "itemNo",
						event.getRowNum() + 1);
				redrawPackNumber();
			}
		});
	}

	/**
	 * 设置箱号
	 */
	private void redrawPackNumber() {
		RecordList list = vanningListGrid.getRecordList();
		if (null != list) {
			Map<String, String> valueMaps = new LinkedHashMap<String, String>();
			for (int i = 0; i < list.getLength(); i++) {
				Record record = list.get(i);
				valueMaps.put(record.getAttribute("id"),
						record.getAttribute("pacakgeNumber"));
			}
			final ListGridField vanningIDField = vanningItemListGrid
					.getField("vanningID");
			if (null != vanningIDField) {
				vanningIDField.setValueMap(valueMaps);
			}
		}
	}

	/**
	 * 加入箱子详细
	 */
	private void addToVanningItemList(Record record) {
		if (null == record) {
			return;
		}
		// 检查件号是否已经加入Grid
		String currentContractNumber = record.getAttribute("partNumber");
		RecordList list = vanningItemListGrid.getRecordList();
		for (int i = 0; null != list && i < list.getLength(); i++) {
			Record r = list.get(i);
			if (null != r) {
				String contractNumber = r.getAttribute("partNumber");
				if (null != contractNumber && !"".equals(contractNumber)) {
					if (currentContractNumber.equals(contractNumber)) {
						return;
					}
				}
			}
		}
		ListGridRecord itemRecord = new ListGridRecord();
		itemRecord.setAttribute("orderID",
				DataModelLocator.getInstance().modifyOrderGrid
						.getSelectedRecord().getAttribute("id")); // 发货指令外键设置
		itemRecord.setAttribute("contractNumber",
				record.getAttribute("contractNumber")); // 合同号
		itemRecord
				.setAttribute("partNumber", record.getAttribute("partNumber")); // 件号
		itemRecord.setAttribute("pacakgeNumber", ""); // 箱号
		itemRecord.setAttribute("vanningID", ""); // 箱号主键
		itemRecord.setAttribute("quantity", record.getAttribute("quantity")); // 默认提货数量为合同数量
		itemRecord.setAttribute("unitPriceAmount",
				record.getAttribute("unitPriceAmount")); // 单价
		itemRecord.setAttribute("totalAmount",
				record.getAttribute("totalAmount")); // 总价
		// begin to edit
		vanningItemListGrid.startEditingNew(itemRecord);
	}

	/**
	 * 加载合同数据
	 * 
	 * @param contractNumber
	 *            合同编号
	 * @param contractType
	 *            合同类型
	 */
	private void loadContractInfo(String contractNumber, String contractType) {
		ContractInfoServiceAsync service = GWT
				.create(ContractInfoService.class);
		service.getContract(contractNumber, contractType,
				new AsyncCallback<Contract>() {
					public void onSuccess(Contract contract) {
						fillContractForm(contract); // 填充合同数据
						fillContractItemListGrid(contract); // 填充合同详细数据
					}

					public void onFailure(Throwable arg0) {
						SC.warn("error:" + arg0.getMessage());
					}

				});
	}

	/**
	 * 填充合同表单
	 * 
	 * @param contract
	 */
	private void fillContractForm(Contract contract) {
		DataModelLocator.getInstance().contractIdItem
				.setValue(contract.getId());
		DataModelLocator.getInstance().contractNumberText.setValue(contract
				.getContractNumber());

		EnumTool.setValueMap(EnumTool.DELIVERYTYPE,
				DataModelLocator.getInstance().deliveryTypeSelect);
		DataModelLocator.getInstance().deliveryTypeSelect.setValue(contract
				.getM_DeliveryType());

		EnumTool.setValueMap(EnumTool.PRIORITY,
				DataModelLocator.getInstance().prioritySelect);
		DataModelLocator.getInstance().prioritySelect.setValue(contract
				.getM_Priority());

		EnumTool.setValueMap(EnumTool.TRANSPORTATIONCODE,
				DataModelLocator.getInstance().shippingServiceTypeText);
		DataModelLocator.getInstance().shippingServiceTypeText
				.setValue(contract.getM_TransportationCode());

		EnumTool.setValueMap(EnumTool.TRADEMETHODS,
				DataModelLocator.getInstance().tradeMethodsText);
		DataModelLocator.getInstance().tradeMethodsText.setValue(contract
				.getM_TradeMethods());
		DataModelLocator.getInstance().freightAgentCheckbox.setValue(contract
				.getIsAppointFreightAgent());
		DataModelLocator.getInstance().carrierNameText.setValue(contract
				.getAppointForwarder());
		DataModelLocator.getInstance().appointForwarderContactText
				.setValue(contract.getAppointForwarderContact());
		DataModelLocator.getInstance().appointForwarderLinkmanText
				.setValue(contract.getAppointForwarderLinkman());
	}

	/**
	 * 填充合同详细Grid
	 * 
	 * @param contract
	 */
	private void fillContractItemListGrid(Contract contract) {
		List<ContractItem> contractItems = contract.getContractItems();
		if (contractItems != null && contractItems.size() > 0) {
			ListGridRecord[] records = new ListGridRecord[contractItems.size()];
			for (int i = 0; i < records.length; i++) {
				ContractItem contractItem = contractItems.get(i);
				ListGridRecord record = new ListGridRecord();
				record.setAttribute("contractNumber",
						contract.getContractNumber());
				record.setAttribute("partNumber", contractItem.getPartNumber());
				if (contractItem.getQuantity() == null) {
					contractItem.setQuantity(0);
				}
				if (contractItem.getUnitPriceAmount() == null) {
					contractItem.setUnitPriceAmount(0.0f);
				}
				record.setAttribute("quantity", contractItem.getQuantity());
				record.setAttribute("unitPriceAmount",
						contractItem.getUnitPriceAmount());
				record.setAttribute(
						"totalAmount",
						contractItem.getUnitPriceAmount()
								* contractItem.getQuantity());
				records[i] = record;
			}
			contractDetailListGrid.setData(records);
		}
	}

}
