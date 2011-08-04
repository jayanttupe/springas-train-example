package com.skynet.spms.client.gui.commonOrder.delivery.ui.modify;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.OperationMode;
import com.skynet.spms.client.gui.base.ValidateUtil;
import com.skynet.spms.client.gui.base.i18n.CommonOrderModuleConst;
import com.skynet.spms.client.gui.commonOrder.delivery.model.DataModelLocator;
import com.skynet.spms.client.service.ContractInfoService;
import com.skynet.spms.client.service.ContractInfoServiceAsync;
import com.skynet.spms.client.vo.contractManagement.Contract;
import com.skynet.spms.client.vo.contractManagement.ContractItem;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.core.Function;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 添加窗体
 * 
 * @author tqc
 * 
 */
public class DeliveryOrderWindowModify extends BaseWindow {

	private FilterListGrid lg = null;

	private FilterListGrid itemLg = null;
	

	/**
	 * 此构造函数，为了便于Form数据源的绑定。可直接传递主从表的Grid
	 * 
	 * @param opm
	 *            当前操作方式
	 * @param listGrids
	 *            Gird数组便于绑定数据源
	 */
	public DeliveryOrderWindowModify(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl,
			OperationMode opm, ListGrid[] listGrids) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	/**
	 * 无需传递数据源 重载
	 * 
	 * @param opm
	 *            当前操作方式
	 */
	public DeliveryOrderWindowModify(Rectangle srcRect, ListGrid listGrid,
			String contractNumber, String contractType) {
		super("发货指令修改", true, srcRect, listGrid, "");
		loadContractInfo(contractNumber, contractType);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		lg = new FilterListGrid();
		itemLg = new FilterListGrid();
		DataModelLocator.getInstance().contractItemGrid = lg;
		this.setOverflow(Overflow.AUTO);
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		DeliveryBaseFormModify baseInfoForm = new DeliveryBaseFormModify();
		baseInfoForm.setHeight(313);
		vmain.addMember(baseInfoForm);
		VLayout twoView = getShowGridView();
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);
		return vmain;
	}

	private VLayout getShowGridView() {
	final CommonOrderModuleConst constui=GWT.create(CommonOrderModuleConst.class);
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
		Label leftLb = new Label(constui.by_contract_item());
		leftLb.setWidth("80");
		leftLb.setHeight("15");

		IButton batchAddBtn = new IButton(constui.put_in());
		batchAddBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (ValidateUtil.isRecordSelected(lg)) {
					// 件号
					String partNumber = lg.getSelectedRecord().getAttribute(
							"partNumber");
					String orderID=DataModelLocator.getInstance().modifyOrderGrid.getSelectedRecord().getAttribute("id");
					lg.getSelectedRecord().setAttribute("orderID", orderID);
					ListGridRecord[] list = itemLg.getRecords();
					for (int i = 0; i < list.length; i++) {
						ListGridRecord record = list[i];
						if (partNumber.equals(record.getAttribute("partNumber"))) {
							return;
						} 
					}
					//加入
					itemLg.addData(lg.getSelectedRecord(),new DSCallback() {
						public void execute(DSResponse response, Object rawData, DSRequest request) {
							SC.say(constui.msgAddOpSuccess());
						}
					});
					
				}
			}
		});
		titleHL.addMember(leftLb);
		titleHL.addMember(batchAddBtn);
		leftLayout.addMember(titleHL);
		leftLayout.addMember(getLeftGrid());

		VLayout rightLayout = new VLayout();
		Label rightLb = new Label(constui.delivery_order_item());
		rightLb.setHeight("30");
		rightLayout.addMember(rightLb);
		rightLayout.addMember(getRightGrid());
		h.addMember(leftLayout);
		h.addMember(rightLayout);

		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		btnGroup.setLayoutLeftMargin(50);
		btnGroup.setMargin(5);
		IButton btnSave = new IButton(constui.btnSave());
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				itemLg.saveAllEdits(new Function() {
					public void execute() {
						SC.say(constui.msgAddOpSuccess());
					}
				});

			}
		});
		IButton closeSave = new IButton(constui.btnClose());
		closeSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				destroy();
			}
		});
		btnGroup.addMember(closeSave);
		v.addMember(h);
		v.addMember(btnGroup);
		return v;
	}

	/**
	 * 合同明细Grid
	 * 
	 * @return
	 */
	private ListGrid getLeftGrid() {
		final CommonOrderModuleConst constui=GWT.create(CommonOrderModuleConst.class);
		lg.setHeight("100%");
		lg.setShowRowNumbers(true);
		lg.setShowAllRecords(true);
		ListGridField field1 = new ListGridField("orderID", constui.orderID());
		field1.setHidden(true);
		ListGridField field2 = new ListGridField("partNumber", constui.partNumber());
		ListGridField field3 = new ListGridField("quantity", constui.quantity());
		ListGridField field4 = new ListGridField("unitPriceAmount", constui.unitPriceAmount());
		ListGridField field5 = new ListGridField("totalAmount", constui.totalAmount());

		lg.setFields(field1, field2, field3, field4, field5);

		return lg;
	}

	/**
	 * 发货明细
	 * 
	 * @return
	 */
	private ListGrid getRightGrid() {
		final CommonOrderModuleConst constui=GWT.create(CommonOrderModuleConst.class);
		itemLg.setHeight("100%");
		itemLg.setEmptyMessage(constui.drag_into_grid());
		itemLg.setShowRowNumbers(true);
		itemLg.setShowAllRecords(true);
		itemLg.setCanRemoveRecords(true);

		final DataSourceTool tool = new DataSourceTool();
		tool.onCreateDataSource(
				"supplierSupport.repairClaim.repairDeliveryOrder",
				"PickupDeliveryVanningItems_datasource",
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemLg.setDataSource(dataSource);
						Criteria query = new Criteria();
						query.addCriteria("orderId", DataModelLocator
								.getInstance().modifyOrderGrid
								.getSelectedRecord().getAttribute("id"));
						itemLg.fetchData(query, new DSCallback() {
							public void execute(DSResponse response,
									Object rawData, DSRequest request) {
								ListGridField field1 = new ListGridField(
										"orderID", constui.orderID());
								field1.setHidden(true);
								ListGridField field2 = new ListGridField(
										"partNumber", constui.partNumber());
								ListGridField field3 = new ListGridField(
										"quantity", constui.quantity());
								ListGridField field4 = new ListGridField(
										"unitPriceAmount", constui.unitPriceAmount());
								ListGridField field5 = new ListGridField(
										"totalAmount",constui.totalAmount());
								// 币种
								ListGridField field6 = new ListGridField(
										"currency");
								field6.setHidden(true);

								itemLg.setFields(field1, field2, field3,
										field4, field5, field6);
							}
						});

					}
				});
		return itemLg;
	}

	/**
	 * 填充数据
	 * 
	 * @param contractNumber
	 * @param contractType
	 */
	private void loadContractInfo(String contractNumber, String contractType) {
		ContractInfoServiceAsync service = GWT
				.create(ContractInfoService.class);
		service.getContract(contractNumber, contractType,
				new AsyncCallback<Contract>() {
					public void onSuccess(Contract contract) {
						fillContractItemListGrid(contract);
					}

					public void onFailure(Throwable arg0) {
						SC.warn("error:" + arg0.getMessage());
					}
				});
	}

	/**
	 * 填充合同明细数据表格
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
				record.setAttribute("id", contractItem.getId());
				// 件号
				record.setAttribute("partNumber", contractItem.getPartNumber());
				if (contractItem.getQuantity() == null) {
					contractItem.setQuantity(0);
				}
				if (contractItem.getUnitPriceAmount() == null) {
					contractItem.setUnitPriceAmount(0.0f);
				}
				// 数量
				record.setAttribute("quantity", contractItem.getQuantity());
				// 单价
				record.setAttribute("unitPriceAmount",
						contractItem.getUnitPriceAmount());
				// 总计
				record.setAttribute(
						"totalAmount",
						contractItem.getUnitPriceAmount()
								* contractItem.getQuantity());
				// 单位
				record.setAttribute("unit", contractItem.getUnit());
				// 币种
				record.setAttribute("currency", contractItem.getCurrency());

				records[i] = record;
			}

			lg.setData(records);
		}

	}

}
