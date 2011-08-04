package com.skynet.spms.client.gui.customerService.salesService.salesContract.update;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.contractManagement.tag.TagEnum;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressForm;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.customerService.commonui.BaseRightListGrid;
import com.skynet.spms.client.gui.customerService.commonui.ContractProvisionPanel;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.model.SaleContractModelLocator;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * 修改销售合同窗体
 * 
 * @author fl
 * 
 */
public class SalesContractUpdateWin extends BaseWindow {

	public SalesContractUpdateWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	private SaleContractModelLocator model;
	/** 客户订单明细项表格 **/
	public BaseListGrid salesItemGrid;
	/** 合同明细项表格 **/
	public BaseListGrid contractItemGrid;
	/** 合同明细项表单 **/
	public ContractItemUpdateForm saleContractItemForm;

	/**
	 * 客户订单明细项
	 * 
	 * @return
	 */
	public BaseListGrid getSalesReqItemGrid() {
		BaseListGrid lg = new BaseListGrid() {
			@Override
			public void drawGrid() {
				/**
				 * 件号
				 */
				ListGridField partNumber = new ListGridField("partNumber");
				partNumber.setCanFilter(true);
				/**
				 * 数量
				 */
				ListGridField quantity = new ListGridField("quantity");
				Utils.formatQuantityField(quantity, "m_UnitOfMeasureCode");
				quantity.setCanFilter(true);
				/**
				 * 单价
				 */
				ListGridField unitOfPrice = new ListGridField("unitOfPrice");
				Utils.formatPriceField(unitOfPrice,
						"m_InternationalCurrencyCode");
				unitOfPrice.setCanFilter(true);
				/**
				 * 金额
				 */
				ListGridField totalPrice = new ListGridField("amount");
				Utils.formatPriceField(totalPrice,
						"m_InternationalCurrencyCode");
				totalPrice.setCanFilter(true);
				setFields(partNumber, quantity, unitOfPrice, totalPrice);
			}
		};
		lg.setLoadingDataMessage("数据加载中，请稍后......");
		return lg;
	}

	/**
	 * 销售合同明细表格
	 * 
	 * @return
	 */
	public BaseListGrid getContractItemGrid() {
		BaseListGrid lg = new BaseRightListGrid() {
			@Override
			public void drawGrid() {
				/**
				 * 件号
				 */
				ListGridField field2 = new ListGridField("partNumber");
				field2.setCanFilter(true);
				/**
				 * 数量
				 */
				ListGridField field3 = new ListGridField("quantity");
				Utils.formatQuantityField(field3, "m_UnitOfMeasureCode");
				field3.setCanFilter(true);
				/**
				 * 单价
				 */
				ListGridField field4 = new ListGridField("unitPrice");
				Utils.formatPriceField(field4, "currency");
				field4.setCanFilter(true);
				/**
				 * 金额
				 */
				ListGridField field5 = new ListGridField("price");
				Utils.formatPriceField(field5, "currency");
				field5.setCanFilter(true);

				setFields(field2, field3, field4, field5);
			}
		};
		lg.setLoadingDataMessage("数据加载中，请稍后......");
		return lg;
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		model = SaleContractModelLocator.getInstance();
		VLayout mainLayout = new VLayout();
		mainLayout.setMembersMargin(15);
		contractItemGrid = getContractItemGrid();
		TabSet topTabSet = new TabSet();
		Tab baseTab = new Tab("合同基本信息");
		ContractBaseUpdateForm baseForm = new ContractBaseUpdateForm(
				contractItemGrid);
		baseTab.setPane(baseForm);

		Tab addressTab = new Tab("地址信息");
		addressTab.setPane(new BaseAddressForm(BaseAddressForm.Type.modify));

		Tab provisionTab = new Tab("合同条款");
		provisionTab.setPane(new ContractProvisionPanel(baseForm.form,
				TagEnum.RepairInspectClaimContractTemplate));

		Tab attachmentTab = new Tab("附件信息");
		attachmentTab.setPane(new AttachmentUpdateForm());
		topTabSet.setTabs(baseTab, addressTab, provisionTab, attachmentTab);
		topTabSet.setHeight(500);
		topTabSet.setWidth100();
		mainLayout.addMember(topTabSet);

		HLayout gridLayout = new HLayout();
		gridLayout.setHeight(200);
		VLayout sheetItemGridLayout = new VLayout();
		sheetItemGridLayout.setGroupTitle("客户订单明细项");
		sheetItemGridLayout.setIsGroup(true);
		salesItemGrid = getSalesReqItemGrid();
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESREQUISITIONSHEET_ITEM,
				DSKey.C_SALESREQUISITIONSHEET_ITEM_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						salesItemGrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.setAttribute("id", model.sheetID);
						salesItemGrid.fetchData(criteria);
						salesItemGrid.drawGrid();
						model.sheetItemGrid = salesItemGrid;
					}
				});

		salesItemGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				ListGridRecord record = salesItemGrid.getSelectedRecord();
				if (record != null) {
					saleContractItemForm.form.editNewRecord();
					saleContractItemForm.item_save.setTitle("保存");

					saleContractItemForm.item_no.setValue(record
							.getAttribute("partNumber"));
					saleContractItemForm.item_ata.setValue(record
							.getAttribute("partAta"));
					saleContractItemForm.item_keys.setValue(record
							.getAttribute("keyword"));
					saleContractItemForm.item_count.setValue(record
							.getAttribute("quantity"));
					saleContractItemForm.item_unitPrice.setValue(record
							.getAttribute("unitOfPrice"));
					saleContractItemForm.item_price.setValue(record
							.getAttribute("amount"));
					saleContractItemForm.item_unit.setValue(record
							.getAttribute("m_UnitOfMeasureCode"));
					saleContractItemForm.item_currency.setValue(record
							.getAttribute("m_InternationalCurrencyCode"));
					saleContractItemForm.item_description.setValue(record
							.getAttribute("partDescription"));

					String sheetId = record.getAttribute("id");
					CodeRPCTool
							.getmanufacturerCodeBySalesRequisitionSheetItemId(
									sheetId,
									saleContractItemForm.item_ManufacturerCode,
									saleContractItemForm.item_ManufacturerCodeId);
					// 绑定折扣数据
					String partNumber = record.getAttribute("partNumber");
					String customerCodeId = model.selectedRecord
							.getAttribute("m_CustomerIdentificationCode.id");
					CodeRPCTool.bindDiscountItem(customerCodeId, partNumber,
							saleContractItemForm.item_m_DiscountPercentCode);
				}
			}
		});
		sheetItemGridLayout.addMember(salesItemGrid);
		gridLayout.addMember(sheetItemGridLayout);

		VLayout pactItemGridLayout = new VLayout();
		pactItemGridLayout.setGroupTitle("客户合同明细项");
		pactItemGridLayout.setIsGroup(true);

		dataSourceTool.onCreateDataSource(DSKey.C_SALESCONTRACT,
				DSKey.C_SALESCONTRACTITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						Criteria pactItemCriteria = new Criteria();
						pactItemCriteria.setAttribute("salesTemplate.id",
								model.contractID);
						contractItemGrid.setDataSource(dataSource);
						contractItemGrid.fetchData(pactItemCriteria);
						contractItemGrid.drawGrid();
						model.pactItemGrid = contractItemGrid;
					}
				});
		contractItemGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				saleContractItemForm.form.editSelectedData(contractItemGrid);
				saleContractItemForm.item_save.setTitle("修改");
			}
		});
		pactItemGridLayout.addMember(contractItemGrid);
		gridLayout.addMember(pactItemGridLayout);
		mainLayout.addMember(gridLayout);
		saleContractItemForm = new ContractItemUpdateForm("客户合同明细项");
		mainLayout.addMember(saleContractItemForm);
		return mainLayout;
	}
}
