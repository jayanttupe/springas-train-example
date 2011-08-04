package com.skynet.spms.client.gui.customerService.salesService.salesContract.view;

import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.Utils;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerService.commonui.BaseListGrid;
import com.skynet.spms.client.gui.customerService.salesService.salesContract.model.SaleContractModelLocator;
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
 * 修改销售合同窗体
 * 
 * @author fl
 * 
 */
public class SalesContractViewWin extends BaseWindow {

	public SalesContractViewWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	private BaseAddressModel addressModel;
	private SaleContractModelLocator model;
	/** 合同明细项表格 **/
	public BaseListGrid contractItemGrid;
	/** 合同明细项表单 **/
	public ContractItemViewForm saleContractItemForm;

	/**
	 * 销售合同明细表格
	 * 
	 * @return
	 */
	public BaseListGrid getContractItemGrid() {
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
		addressModel = BaseAddressModel.getInstance();
		addressModel.addr_sheetId = model.contractID;
		VLayout mainLayout = new VLayout();
		mainLayout.setMembersMargin(15);

		SalesContractViewTabSet topTabSet = new SalesContractViewTabSet();
		topTabSet.setHeight(500);
		topTabSet.setWidth100();
		mainLayout.addMember(topTabSet);

		HLayout gridLayout = new HLayout();
		gridLayout.setHeight(200);

		VLayout pactItemGridLayout = new VLayout();
		pactItemGridLayout.setGroupTitle("客户合同明细项");
		pactItemGridLayout.setIsGroup(true);
		contractItemGrid = getContractItemGrid();
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_SALESCONTRACT,
				DSKey.C_SALESCONTRACTITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						Criteria pactItemCriteria = new Criteria();
						StringBuilder builder = new StringBuilder();
						builder.append(model.contractID);
						pactItemCriteria.setAttribute("salesTemplate.id",
								builder.toString());
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
			}
		});
		pactItemGridLayout.addMember(contractItemGrid);
		gridLayout.addMember(pactItemGridLayout);
		mainLayout.addMember(gridLayout);
		saleContractItemForm = new ContractItemViewForm("客户合同明细项");
		mainLayout.addMember(saleContractItemForm);
		return mainLayout;
	}
}
