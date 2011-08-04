package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.model.ProcurementContractModelLocator;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 添加采购合同
 * 
 * @author gqr
 * 
 */
public class ProcurementContractViewWin extends BaseWindow {

	private ProcurementContractModelLocator model;
	/** 合同明细项表格 **/
	public BaseListGrid procurementContractItemGrid;
	/** 合同明细项表单 **/
	public ContractItemViewForm procurementContractItemForm;

	/**
	 * 
	 * @param windowTitle
	 *            标题
	 * @param isMax
	 *            是否最大化
	 * @param srcRect
	 * @param listGrid
	 * @param iconUrl
	 *            图标
	 * @param id
	 *            合同单据主键
	 */
	public ProcurementContractViewWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	/**
	 * 采购合同明细表格
	 * 
	 * @return
	 */
	public BaseListGrid getProcurementContractItemGrid() {
		BaseListGrid lg = new BaseListGrid() {
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

		ProcurementContractViewTabSet topTabSet = new ProcurementContractViewTabSet();
		topTabSet.setHeight(500);
		topTabSet.setWidth100();
		mainLayout.addMember(topTabSet);

		HLayout gridLayout = new HLayout();
		gridLayout.setHeight(200);
		VLayout procurementContractItemGridLayout = new VLayout();
		procurementContractItemGridLayout.setGroupTitle("采购合同明细项");
		procurementContractItemGridLayout.setIsGroup(true);
		procurementContractItemGrid = getProcurementContractItemGrid();
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.S_PROCUREMENTCONTRACT,
				DSKey.S_PROCUREMENTCONTRACTITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						Criteria pactItemCriteria = new Criteria();
						StringBuilder builder = new StringBuilder();
						builder.append(model.contractID);
						pactItemCriteria.setAttribute("template.id",
								builder.toString());
						procurementContractItemGrid.setDataSource(dataSource);
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
					}
				});

		procurementContractItemGridLayout
				.addMember(procurementContractItemGrid);
		gridLayout.addMember(procurementContractItemGridLayout);
		mainLayout.addMember(gridLayout);
		procurementContractItemForm = new ContractItemViewForm("合同明细项",
				procurementContractItemGrid);
		mainLayout.addMember(procurementContractItemForm);
		return mainLayout;
	}
}
