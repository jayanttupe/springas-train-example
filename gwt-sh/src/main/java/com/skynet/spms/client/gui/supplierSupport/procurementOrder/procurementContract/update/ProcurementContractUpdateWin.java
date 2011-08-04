package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.update;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.CodeRPCTool;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.contractManagement.tag.TagEnum;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressForm;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.customerService.commonui.BaseRightListGrid;
import com.skynet.spms.client.gui.customerService.commonui.ContractProvisionPanel;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.model.ProcurementContractModelLocator;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.SuppliersParityShowWindow;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.suppliersParity.model.SuppliersParityModel;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
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
 * 添加采购合同
 * 
 * @author fl
 * 
 */
public class ProcurementContractUpdateWin extends BaseWindow {

	public ProcurementContractUpdateWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	private ProcurementContractModelLocator model;
	/** 采购指令明细项表格 **/
	public BaseListGrid procurementOrderItemGrid;
	/** 合同明细项表格 **/
	public BaseListGrid procurementContractItemGrid;
	/** 合同明细项表单 **/
	public ContractItemUpdateForm procurementContractItemForm;

	/**
	 * 采购指令明细项
	 * 
	 * @return
	 */
	public BaseListGrid getProcurementOrderItemGrid() {
		BaseListGrid lg = new BaseListGrid() {
			@Override
			public void drawGrid() {
				/**
				 * 件号
				 */
				ListGridField field2 = new ListGridField("partNumber");
				field2.setCanFilter(true);
				/**
				 * 交货日期
				 */
				ListGridField field3 = new ListGridField("deliveryDate");
				field3.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATE);
				field3.setCanFilter(true);

				/**
				 * 计划采购数量
				 */
				ListGridField field4 = new ListGridField("plannedQuantity");
				Utils.formatQuantityField(field4, "m_UnitOfMeasureCode");
				field4.setCanFilter(true);

				setFields(field2, field3, field4);
			}
		};
		lg.setLoadingDataMessage("数据加载中，请稍后......");
		return lg;
	}

	/**
	 * 采购合同明细表格
	 * 
	 * @return
	 */
	public BaseListGrid getProcurementContractItemGrid() {
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
				ListGridField field5 = new ListGridField("amount");
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
		model = ProcurementContractModelLocator.getInstance();
		VLayout mainLayout = new VLayout();
		mainLayout.setMembersMargin(15);
		procurementContractItemGrid = getProcurementContractItemGrid();
		TabSet topTabSet = new TabSet();
		Tab baseTab = new Tab("合同基本信息");
		ContractBaseUpdateForm baseForm = new ContractBaseUpdateForm(
				procurementContractItemGrid);
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
		VLayout procurementOrderItemGridLayout = new VLayout();
		procurementOrderItemGridLayout.setGroupTitle("采购指令明细项");
		procurementOrderItemGridLayout.setIsGroup(true);
		procurementOrderItemGrid = getProcurementOrderItemGrid();
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTORDER,
				DSKey.S_PROCUREMENTORDERITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						Criteria criteria = new Criteria();
						criteria.setAttribute("id", model.procurementPlanUUid);
						procurementOrderItemGrid.setDataSource(dataSource);
						procurementOrderItemGrid.fetchData(criteria);
						procurementOrderItemGrid.drawGrid();
						model.sheetItemGrid = procurementOrderItemGrid;
					}
				});
		procurementOrderItemGrid
				.addRecordClickHandler(new RecordClickHandler() {

					@Override
					public void onRecordClick(RecordClickEvent event) {
						ListGridRecord record = procurementOrderItemGrid
								.getSelectedRecord();
						if (record != null) {
							procurementContractItemForm.form.editNewRecord();
							procurementContractItemForm.item_save
									.setTitle("保存明细");
							procurementContractItemForm.partNumberItem
									.setValue(record.getAttribute("partNumber"));
							procurementContractItemForm.ataNumItem
									.setValue(record.getAttribute("ata"));
							procurementContractItemForm.keyword.setValue(record
									.getAttribute("itemKeyword"));
							String codeId = record
									.getAttribute("m_ManufacturerCode.id");
							procurementContractItemForm.m_ManufacturerIdItem
									.setValue(codeId);
							CodeRPCTool
									.getCodeById(
											codeId,
											procurementContractItemForm.m_ManufacturerCodeItem);
							procurementContractItemForm.m_UnitOfMeasureCodeItem.setValue(record
									.getAttribute("m_UnitOfMeasureCode"));
							procurementContractItemForm.currencyItem.setValue(record
									.getAttribute("m_InternationalCurrencyCode"));
							procurementContractItemForm.item_unitPrice
									.setValue(record
											.getAttribute("plannedUnitPrice"));
							procurementContractItemForm.quantityItem
									.setValue(record
											.getAttribute("plannedQuantity"));
							procurementContractItemForm.deliveryDate.setValue(record
									.getAttributeAsDate("deliveryDate"));
							procurementContractItemForm.priceItem
									.setValue(record
											.getAttribute("unitPriceAmount"));
						}
					}
				});

		procurementOrderItemGridLayout.addMember(procurementOrderItemGrid);
		gridLayout.addMember(procurementOrderItemGridLayout);

		VLayout procurementContractItemGridLayout = new VLayout();
		procurementContractItemGridLayout.setGroupTitle("采购合同明细项");
		procurementContractItemGridLayout.setIsGroup(true);

		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTCONTRACT,
				DSKey.S_PROCUREMENTCONTRACTITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						Criteria pactItemCriteria = new Criteria();
						pactItemCriteria.setAttribute("template.id",
								model.contractID);
						procurementContractItemGrid.setDataSource(dataSource);
						procurementContractItemGrid
								.setCriteria(pactItemCriteria);
						procurementContractItemGrid.fetchData(pactItemCriteria);
						procurementContractItemGrid.drawGrid();
						model.pactItemGrid = procurementContractItemGrid;
					}
				});
		procurementContractItemGrid
				.addRecordClickHandler(new RecordClickHandler() {
					@Override
					public void onRecordClick(RecordClickEvent event) {
						procurementContractItemForm.form
								.editSelectedData(procurementContractItemGrid);
						procurementContractItemForm.item_save.setTitle("修改");
					}
				});

		procurementContractItemGridLayout
				.addMember(procurementContractItemGrid);
		gridLayout.addMember(procurementContractItemGridLayout);
		
		HLayout hlBtn = new HLayout();
		hlBtn.setWidth100();
		hlBtn.setAlign(Alignment.RIGHT);
		final IButton viewBtn = new IButton("查看比价单");
		viewBtn.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				
				SuppliersParityModel spmodel =SuppliersParityModel.getInstance();
				spmodel.contractId=model.contractID;
				if(!"".equals(spmodel.contractId)||spmodel.contractId!=null){
					SuppliersParityShowWindow win = new SuppliersParityShowWindow("比价单", true, viewBtn.getPageRect(), null, null);
					ShowWindowTools.showWindow(viewBtn.getPageRect(), win, 1);
				}else{
					SC.say("抱歉，比价单不存在");
				}
				
			}
		});
		hlBtn.addMember(viewBtn);
		procurementContractItemGridLayout.addMember(hlBtn);
		
		
		mainLayout.addMember(gridLayout);
		procurementContractItemForm = new ContractItemUpdateForm("合同明细项",
				procurementContractItemGrid);
		mainLayout.addMember(procurementContractItemForm);
		return mainLayout;
	}
}
