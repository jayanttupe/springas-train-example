package com.skynet.spms.client.gui.customerService.buyBackService.buyBackPact.view;

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
import com.skynet.spms.client.gui.customerService.commonui.ContractProvisionPanel;
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
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * 查看回购合同
 * 
 * @author fl
 * 
 */
public class BuyBackPactViewWin extends BaseWindow {

	public BuyBackPactViewWin(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	private static I18n ui = new I18n();
	
	private BuybackPactModelLocator model;
	/** 合同明细项表格 **/
	public BaseListGrid pactItemGrid;
	/** 合同明细项表单 **/
	public ContractItemViewForm pactItemForm;

	/**
	 * 回购合同明细表格
	 * 
	 * @return
	 */
	public BaseListGrid getPactItemGrid() {
		BaseListGrid lg = new BaseListGrid() {
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
		lg.setLoadingDataMessage("数据加载中，请稍后......");
		return lg;
	}

	@Override
	protected Canvas getLeftLayout(Rectangle srcRect, ListGrid listGrid) {
		model = BuybackPactModelLocator.getInstance();
		VLayout mainLayout = new VLayout();
		mainLayout.setMembersMargin(15);
		pactItemGrid = getPactItemGrid();

		TabSet topTabSet = new TabSet();
		Tab baseTab = new Tab("合同基本信息");
		ContractBaseViewForm baseForm = new ContractBaseViewForm(pactItemGrid);
		baseTab.setPane(baseForm);

		Tab addressTab = new Tab("地址信息");
		addressTab.setPane(new BaseAddressForm(BaseAddressForm.Type.view));

		Tab provisionTab = new Tab("合同条款");
		provisionTab.setPane(new ContractProvisionPanel(baseForm.form,
				TagEnum.RepairInspectClaimContractTemplate));

		Tab attachmentTab = new Tab("附件信息");
		attachmentTab.setPane(new AttachmentViewForm());
		topTabSet.setTabs(baseTab, addressTab, provisionTab, attachmentTab);
		topTabSet.setHeight(500);
		topTabSet.setWidth100();
		mainLayout.addMember(topTabSet);

		HLayout gridLayout = new HLayout();
		gridLayout.setHeight(200);
		final DataSourceTool dataSourceTool = new DataSourceTool();

		VLayout pactItemGridLayout = new VLayout();
		pactItemGridLayout.setGroupTitle("回购合同明细项");
		pactItemGridLayout.setIsGroup(true);
		dataSourceTool.onCreateDataSource(DSKey.C_BUYBACKPACT,
				DSKey.C_BUYBACKPACTITEM_DS, new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						Criteria pactItemCriteria = new Criteria();
						StringBuilder builder = new StringBuilder();
						builder.append(model.contractID);
						pactItemCriteria.setAttribute("template.id",
								builder.toString());
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
			}
		});
		pactItemGridLayout.addMember(pactItemGrid);
		gridLayout.addMember(pactItemGridLayout);
		mainLayout.addMember(gridLayout);
		pactItemForm = new ContractItemViewForm("回购合同明细项");
		mainLayout.addMember(pactItemForm);
		return mainLayout;
	}
}
