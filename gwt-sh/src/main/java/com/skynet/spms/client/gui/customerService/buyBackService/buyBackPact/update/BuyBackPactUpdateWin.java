package com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.update;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.contractManagement.tag.TagEnum;
import com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.model.BuybackPactModelLocator;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressForm;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.customerService.commonui.BaseRightListGrid;
import com.skynet.spms.client.gui.customerService.commonui.ContractProvisionPanel;
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
 * 修改回购合同
 * 
 * @author fl
 * 
 */
public class BuyBackPactUpdateWin extends BaseWindow {

	public BuyBackPactUpdateWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}
	private static I18n ui = new I18n();

	private BuybackPactModelLocator model;
	/** 申请单明细项表格 **/
	public BaseListGrid sheetItemGrid;
	/** 合同明细项表格 **/
	public BaseListGrid pactItemGrid;
	/** 合同明细项表单 **/
	public ContractItemUpdateForm pactItemForm;

	/**
	 * 回购申请明细项
	 * 
	 * @return
	 */
	public BaseListGrid getSheetItemGrid() {
		BaseListGrid lg = new BaseListGrid() {
			@Override
			public void drawGrid() {
				/** 件号 */
				ListGridField fileld2 = new ListGridField("partNumber");
				fileld2.setCanFilter(true);

				/** 数量 */
				ListGridField fileld5 = new ListGridField("quantity");
				Utils.formatQuantityField(fileld5, "m_UnitOfMeasureCode");
				fileld5.setCanFilter(true);
				/** 单价 */
				ListGridField fileld6 = new ListGridField("unitPriceAmount");
				Utils.formatPriceField(fileld6, "currency");
				fileld6.setCanFilter(true);
				/** 金额 */
				ListGridField fileld7 = new ListGridField("price");
				Utils.formatPriceField(fileld7, "currency");
				fileld7.setCanFilter(true);
				setFields(fileld2, fileld5, fileld6, fileld7);
			}
		};
		//"数据加载中，请稍后......"
		lg.setLoadingDataMessage(ui.getM().msgLoading());
		return lg;
	}

	/**
	 * 回购合同明细表格
	 * 
	 * @return
	 */
	public BaseListGrid getPactItemGrid() {
		BaseListGrid lg = new BaseRightListGrid() {
			@Override
			public void drawGrid() {
				/**
				 * 件号
				 */
				ListGridField fileld2 = new ListGridField("partNumber");
				fileld2.setCanFilter(true);
				/**
				 * 数量
				 */
				ListGridField fileld5 = new ListGridField("quantity");
				Utils.formatQuantityField(fileld5, "m_UnitOfMeasureCode");
				fileld5.setCanFilter(true);
				/**
				 * 单价
				 */
				ListGridField fileld6 = new ListGridField("unitPrice");
				Utils.formatPriceField(fileld6, "currency");
				fileld6.setCanFilter(true);
				/**
				 * 金额
				 */
				ListGridField fileld7 = new ListGridField("price");
				Utils.formatPriceField(fileld7, "currency");
				fileld7.setCanFilter(true);
				setFields(fileld2, fileld5, fileld6, fileld7);
			}
		};
		//"数据加载中，请稍后......"
		lg.setLoadingDataMessage(ui.getM().msgLoading());
		return lg;
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		
		//修改回购申请单
		this.setTitle(ui.getM().modifyFormTitle(ui.getM().mod_buyBackSheet_name()));
		
		model = BuybackPactModelLocator.getInstance();
		VLayout mainLayout = new VLayout();
		mainLayout.setMembersMargin(15);
		pactItemGrid = getPactItemGrid();

		TabSet topTabSet = new TabSet();
		//"合同基本信息"
		Tab baseTab = new Tab(ui.getM().address_info_title());
		ContractBaseUpdateForm baseForm = new ContractBaseUpdateForm(
				pactItemGrid);
		baseTab.setPane(baseForm);

		//地址信息
		Tab addressTab = new Tab(ui.getM().address_info_title());
		addressTab.setPane(new BaseAddressForm(BaseAddressForm.Type.modify));

		//合同条款
		Tab provisionTab = new Tab(ui.getM().contract_provision_title());
		provisionTab.setPane(new ContractProvisionPanel(baseForm.form,
				TagEnum.RepairInspectClaimContractTemplate));

		//附件信息
		Tab attachmentTab = new Tab(ui.getM().attachment_title());
		attachmentTab.setPane(new AttachmentUpdateForm());
		topTabSet.setTabs(baseTab, addressTab, provisionTab, attachmentTab);
		topTabSet.setHeight(500);
		topTabSet.setWidth100();
		mainLayout.addMember(topTabSet);

		HLayout gridLayout = new HLayout();
		gridLayout.setHeight(200);
		VLayout sheetItemGridLayout = new VLayout();
		//"回购申请明细项"
		sheetItemGridLayout.setGroupTitle(ui.getM().msgListTitle(ui.getM().mod_buyBackSheet_item_name()));
		sheetItemGridLayout.setIsGroup(true);
		sheetItemGrid = getSheetItemGrid();
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_BUYBACKSHEET,
				DSKey.C_BUYBACKSHEETITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						Criteria criteria = new Criteria();
						criteria.setAttribute("sheet.id",
								model.requisitionSheetNumber);
						sheetItemGrid.setDataSource(dataSource);
						sheetItemGrid.fetchData(criteria);
						sheetItemGrid.drawGrid();
						model.sheetItemGrid = sheetItemGrid;
					}
				});
		sheetItemGridLayout.addMember(sheetItemGrid);
		sheetItemGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				ListGridRecord record = sheetItemGrid.getSelectedRecord();
				if (record != null) {
					pactItemForm.form.editNewRecord();
					pactItemForm.item_save.setTitle(ui.getM().btnSave());
					pactItemForm.item_no.setValue(record
							.getAttribute("partNumber"));
					pactItemForm.item_ManufacturerCode.setValue(record
							.getAttribute("m_ManufacturerCode.code"));
					pactItemForm.item_ManufacturerCodeId.setValue(record
							.getAttribute("m_ManufacturerCode.id"));
					pactItemForm.item_keys.setValue(record
							.getAttribute("keyword"));
					pactItemForm.item_count.setValue(record
							.getAttribute("quantity"));
					pactItemForm.item_unitPrice.setValue(record
							.getAttribute("unitPriceAmount"));
					pactItemForm.item_price.setValue(record
							.getAttribute("price"));
					pactItemForm.item_unit.setValue(record
							.getAttribute("m_UnitOfMeasureCode"));
					pactItemForm.item_currency.setValue(record
							.getAttribute("currency"));
				}
			}
		});
		gridLayout.addMember(sheetItemGridLayout);

		VLayout pactItemGridLayout = new VLayout();
		//"回购合同明细项"
		pactItemGridLayout.setGroupTitle(ui.getM().msgListTitle(ui.getM().mod_buyBackPact_item_name()));
		pactItemGridLayout.setIsGroup(true);
		dataSourceTool.onCreateDataSource(DSKey.C_BUYBACKPACT,
				DSKey.C_BUYBACKPACTITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						Criteria pactItemCriteria = new Criteria();
						pactItemCriteria.setAttribute("template.id",
								model.contractID);
						pactItemGrid.setDataSource(dataSource);
						pactItemGrid.setCriteria(pactItemCriteria);
						pactItemGrid.fetchData(pactItemCriteria);
						pactItemGrid.drawGrid();
						model.pactItemGrid = pactItemGrid;
					}
				});
		pactItemGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				pactItemForm.form.editSelectedData(pactItemGrid);
				pactItemForm.item_save.setTitle(ui.getM().btnModify());
			}
		});
		pactItemGridLayout.addMember(pactItemGrid);
		gridLayout.addMember(pactItemGridLayout);
		mainLayout.addMember(gridLayout);
		pactItemForm = new ContractItemUpdateForm(ui.getM().mod_buyBackPact_item_name());
		mainLayout.addMember(pactItemForm);
		return mainLayout;
	}
}
