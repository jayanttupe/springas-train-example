package com.skynet.spms.client.gui.commonOrder.delivery.ui.add;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.EnumTool;
import com.skynet.spms.client.gui.base.OperationMode;
import com.skynet.spms.client.gui.base.i18n.CommonOrderModuleConst;
import com.skynet.spms.client.gui.commonOrder.delivery.model.DataModelLocator;
import com.skynet.spms.client.service.BaseCodeService;
import com.skynet.spms.client.service.BaseCodeServiceAsync;
import com.skynet.spms.client.service.ContractInfoService;
import com.skynet.spms.client.service.ContractInfoServiceAsync;
import com.skynet.spms.client.vo.AddressInfoVO;
import com.skynet.spms.client.vo.contractManagement.Contract;
import com.skynet.spms.client.vo.contractManagement.ContractItem;
import com.skynet.spms.client.widgets.grid.FilterListGrid;
import com.smartgwt.client.core.Function;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.DragDataAction;
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
public class DeliveryOrderAddWindow extends BaseWindow {

	private Logger log = Logger.getLogger("DeliveryOrderAddWindow");

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
	public DeliveryOrderAddWindow(String windowTitle, boolean isMax,
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
	public DeliveryOrderAddWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl,
			OperationMode opm, String contractNumber, String contractType) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
		log.info("init the DeliveryOrderAddWindow");
		loadContractInfo(contractNumber, contractType);
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		lg = new FilterListGrid();
		lg.setDisabled(true);
		itemLg = new FilterListGrid();
		CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);
		DataModelLocator.getInstance().contractItemGrid = lg;
		this.setTitle(ui.create_order());
		this.setOverflow(Overflow.AUTO);
		VLayout vmain = new VLayout();
		vmain.setWidth100();
		vmain.setHeight100();
		DeliveryBaseForm baseInfoForm = new DeliveryBaseForm();
		baseInfoForm.setHeight(313);
		vmain.addMember(baseInfoForm);
		VLayout twoView = getShowGridView();
		twoView.setLayoutTopMargin(10);
		vmain.addMember(twoView);
		return vmain;
	}

	// 布局2
	private VLayout getShowGridView() {
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
		final CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);
		Label leftLb = new Label(ui.by_contract_item());
		leftLb.setWidth("80");
		leftLb.setHeight("15");
		
		IButton batchAddBtn = new IButton(ui.put_in());
		batchAddBtn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			}
		});
		titleHL.addMember(leftLb);
		leftLayout.addMember(titleHL);
		leftLayout.addMember(getLeftGrid());

		VLayout rightLayout = new VLayout();
		Label rightLb = new Label(ui.delivery_order_item());
		rightLb.setHeight("30");
		rightLayout.addMember(rightLb);
		rightLayout.addMember(getRightGrid());
		h.addMember(leftLayout);
		h.addMember(rightLayout);

		HLayout btnGroup = new HLayout();
		btnGroup.setMembersMargin(3);
		btnGroup.setLayoutLeftMargin(50);
		btnGroup.setMargin(5);
		IButton btnSave = new IButton(ui.btnSave());
		btnSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				itemLg.saveAllEdits(new Function() {
					public void execute() {
						SC.say(ui.msgAddOpSuccess());
					}
				});

			}
		});
		IButton closeSave = new IButton(ui.btnClose());
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
	 * @return
	 */
	private ListGrid getLeftGrid() {
		final CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);
		lg.setHeight("100%");
		lg.setShowRowNumbers(true);
		lg.setShowAllRecords(true);
		lg.setCanReorderRecords(true);
		lg.setCanDragRecordsOut(true);
		lg.setCanAcceptDroppedRecords(true);
		lg.setDragDataAction(DragDataAction.MOVE);
		ListGridField field1 = new ListGridField("orderID", ui.orderID());
		field1.setHidden(true);
		ListGridField field2 = new ListGridField("partNumber", ui.partNumber());
		ListGridField field3 = new ListGridField("quantity", ui.quantity());
		ListGridField field4 = new ListGridField("unitPriceAmount", ui.unitPriceAmount());
		ListGridField field5 = new ListGridField("totalAmount", ui.totalAmount());
		lg.setFields(field1, field2, field3, field4, field5);
		
		return lg;
	}

	/**
	 * 发货明细
	 * @return
	 */
	private ListGrid getRightGrid() {
		final CommonOrderModuleConst ui=GWT.create(CommonOrderModuleConst.class);
		itemLg.setHeight("100%");
		itemLg.setEmptyMessage(ui.drag_into_grid());
		itemLg.setShowRowNumbers(true);
		itemLg.setShowAllRecords(true);
		itemLg.setCanReorderRecords(true);
		itemLg.setCanDragRecordsOut(true);
		itemLg.setCanAcceptDroppedRecords(true);
		itemLg.setDragDataAction(DragDataAction.MOVE);

		final DataSourceTool tool = new DataSourceTool();
		tool.onCreateDataSource(
				"supplierSupport.repairClaim.repairDeliveryOrder",
				"PickupDeliveryVanningItems_datasource",
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						itemLg.setDataSource(dataSource);
						ListGridField field1 = new ListGridField("orderID",
								ui.orderID());
						field1.setHidden(true);
						ListGridField field2 = new ListGridField("partNumber",
								ui.partNumber());
						ListGridField field3 = new ListGridField("quantity",ui.quantity());
						ListGridField field4 = new ListGridField("unitPriceAmount",ui.unitPriceAmount());
						ListGridField field5 = new ListGridField("totalAmount",ui.totalAmount());
						//币种
						ListGridField field6 = new ListGridField("currency");
						field6.setHidden(true);
						itemLg.setFields(field1, field2, field3, field4, field5,field6);
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
						fillContractForm(contract);
						fillAddressInfo(contract.getId());
						fillContractItemListGrid(contract);
					}

					public void onFailure(Throwable arg0) {
						SC.warn("error:" + arg0.getMessage());
					}

				});
	}
	
	/**
	 * 填充合同数据
	 * 
	 * @param contract
	 */
	private void fillContractForm(Contract contract) {
		
		DataModelLocator.getInstance().contractIdItem.setValue(contract.getId());
		DataModelLocator.getInstance().contractNumberText.setValue(contract
				.getContractNumber());
		
		EnumTool.setValueMap(EnumTool.DELIVERYTYPE, DataModelLocator.getInstance().deliveryTypeSelect);
		DataModelLocator.getInstance().deliveryTypeSelect.setValue(contract
				.getM_DeliveryType());
		
		EnumTool.setValueMap(EnumTool.PRIORITY, DataModelLocator.getInstance().prioritySelect);
		DataModelLocator.getInstance().prioritySelect.setValue(contract.getM_Priority());
		
		
		EnumTool.setValueMap(EnumTool.TRANSPORTATIONCODE, DataModelLocator.getInstance().shippingServiceTypeText);
		DataModelLocator.getInstance().shippingServiceTypeText
				.setValue(contract.getM_TransportationCode());
		
		
		EnumTool.setValueMap(EnumTool.TRADEMETHODS, DataModelLocator.getInstance().tradeMethodsText);
		DataModelLocator.getInstance().tradeMethodsText.setValue(contract
				.getM_TradeMethods());
		
		
		DataModelLocator.getInstance().freightAgentCheckbox.setValue(contract.getIsAppointFreightAgent());
		DataModelLocator.getInstance().carrierNameText.setValue(contract.getAppointForwarder());
		DataModelLocator.getInstance().appointForwarderContactText.setValue(contract.getAppointForwarderContact());
		DataModelLocator.getInstance().appointForwarderLinkmanText.setValue(contract.getAppointForwarderLinkman());
		
		
	}

	/**
	 * 填充合同明细数据表格
	 * 
	 * @param contract
	 */
	private void fillContractItemListGrid(Contract contract) {
		List<ContractItem> contractItems = contract.getContractItems();
		log.info("contractItems:" + contract.getContractItems().size());
		if (contractItems != null && contractItems.size() > 0) {
			ListGridRecord[] records = new ListGridRecord[contractItems.size()];
			for (int i = 0; i < records.length; i++) {
				ContractItem contractItem = contractItems.get(i);
				ListGridRecord record = new ListGridRecord();
				record.setAttribute("id", contractItem.getId());
				//件号
				record.setAttribute("partNumber", contractItem.getPartNumber());
				if (contractItem.getQuantity() == null) {
					contractItem.setQuantity(0);
				}
				if (contractItem.getUnitPriceAmount() == null) {
					contractItem.setUnitPriceAmount(0.0f);
				}
				//数量
				record.setAttribute("quantity", contractItem.getQuantity());
				//单价
				record.setAttribute("unitPriceAmount", contractItem.getUnitPriceAmount());
				//总计
				record.setAttribute("totalAmount", contractItem.getUnitPriceAmount()
						* contractItem.getQuantity());
				//单位
				record.setAttribute("unit", contractItem.getUnit());
				//币种
				record.setAttribute("currency", contractItem.getCurrency());
				
				records[i] = record;
			}
			
			lg.setData(records);
		}

	}
	/**
	 * 填充地址
	 * @param contractID
	 */
	private void fillAddressInfo(String contractID){
		BaseCodeServiceAsync service=GWT.create(BaseCodeService.class);
		service.getAddressInfoByUUID(contractID, new AsyncCallback<AddressInfoVO>() {
			@Override
			public void onSuccess(AddressInfoVO result) {
				DataModelLocator model=DataModelLocator.getInstance();
				model.companyOfShipperItem.setValue(result.getShippingUnit());
				model.shipperItem.setValue(result.getShippingMan());
				model.addressOfShipperItem.setValue(result.getShippingAddr());
				model.telephonOfShipperItem.setValue(result.getShippingLinkType());
				model.companyOfConsigneeItem.setValue(result.getConsigneeUnit());
				model.consigneeItem.setValue(result.getConsigneeMan());
				model.addressOfConsigneeItem.setValue(result.getConsigneeAddr());
				model.telephoneOfConsigneeItem.setValue(result.getConsigneeLinkType());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				SC.warn("Load contract info failed.");
			}
		});
		
	}
	
	

}
