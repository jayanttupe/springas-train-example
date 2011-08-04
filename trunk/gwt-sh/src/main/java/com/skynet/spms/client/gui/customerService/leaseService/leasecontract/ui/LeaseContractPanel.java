package com.skynet.spms.client.gui.customerService.leaseService.leasecontract.ui;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.FeatureLoadCompleteEvent;
import com.skynet.spms.client.gui.base.FeatureLoadCompleteEventHandler;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.commonOrder.ContractIndexKey;
import com.skynet.spms.client.gui.commonOrder.DirectiveBusinessType;
import com.skynet.spms.client.gui.commonOrder.MenuButtonBuilder;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerService.leaseService.business.LeaseRequisitionSheetBusiness;
import com.skynet.spms.client.gui.customerService.leaseService.model.MainModelLocator;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;
/**
 *  租赁合同
 * Description TODO
 *
 * @author   Tony FANG
 * @version  1.0
 * @Date	 2011	2011-7-21
 */
public class LeaseContractPanel extends ShowcasePanel {
	private static I18n ui = new I18n();
	private MainModelLocator model = MainModelLocator.getInstance();
	private LeaseContractToolStrip leaseContractToolStrip;
	private LeaseContractListGrid leaseContractListGrid;
	private LeaseContractItemListGrid leaseContractItemListGrid;
	private LeaseRequisitionSheetBusiness buiness = new LeaseRequisitionSheetBusiness();
	private BaseAddressModel addressModel = BaseAddressModel.getInstance();

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = ui.getM().mod_leaseContract_name();
		private String id;

		public Canvas create() {
			LeaseContractPanel panel = new LeaseContractPanel();
			id = panel.getID();
			return panel;
		}

		public String getID() {
			return id;
		}

		public String getDescription() {
			return DESCRIPTION;
		}
	}

	@Override
	public Canvas getViewPanel() {

		// 合同列表
		leaseContractListGrid = new LeaseContractListGrid();
		// 合同ToolStrip
		leaseContractToolStrip = new LeaseContractToolStrip(
				leaseContractListGrid);
		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setMembersMargin(2);
		v.setWidth100();
		v.setHeight100();
		v.addMember(leaseContractToolStrip);

		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_LEASECONTRACT,
				DSKey.C_LEASECONTRACT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						leaseContractListGrid.setDataSource(dataSource);
						leaseContractListGrid.fetchData();
						leaseContractListGrid.drawGrid();
						model.leaseContractListGrid = leaseContractListGrid;
					}
				});

		dataSourceTool.onCreateDataSource(DSKey.C_LEASECONTRACT_ITEM,
				DSKey.C_LEASECONTRACT_TIEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.leaseContractItemDs = dataSource;
					}
				});
		// 初始化附件数据源
		dataSourceTool.onCreateDataSource(DSKey.C_LEASECONTRACT,
				DSKey.C_LEASEATTACHMENT_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.attachmentDs = dataSource;
					}
				});
		// 初始化地址添加数据源
		dataSourceTool.onCreateDataSource(DSKey.C_LEASECONTRACT,
				DSKey.C_LEASEADDRESS_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						model.addressDataSource = dataSource;
					}
				});
		// 添加业务指令按钮
		final ToolStripMenuButton menuBtn = new ToolStripMenuButton("业务指令");
		Menu menu = new Menu();
		MenuItem item1 = new MenuItem("新建申请供应商租赁指令");
		item1.addClickHandler(new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				buiness.addLeaseInstruct(leaseContractListGrid, menuBtn);
			}
		});
		menu.addItem(item1);
		menuBtn.setMenu(menu);
		leaseContractToolStrip.handlerManager.addHandler(
				FeatureLoadCompleteEvent.HANDLER,
				new FeatureLoadCompleteEventHandler() {
					public void onFeatureLoadComplete(
							FeatureLoadCompleteEvent event) {
						leaseContractToolStrip.addMenuButton(menuBtn, 12);
						// 挂载发货指令
						leaseContractToolStrip
								.addMenuButton(
										MenuButtonBuilder
												.create(
														ContractIndexKey.LeaseContractTemplateManagerForOrder,
														leaseContractListGrid,
														MenuButtonBuilder.ALL,
														DirectiveBusinessType.leaseservice
																.name()), 5);

					}
				});
		// 点击租赁申请单查询租赁申请单明细
		leaseContractListGrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				String leaseId = leaseContractListGrid.getSelectedRecord()
						.getAttribute("id");
				model.LeaseContractId = leaseId;
				addressModel.addr_sheetId = leaseId;
				Criteria criteria = new Criteria();
				criteria.addCriteria("id", leaseId);
				criteria.addCriteria("_r", String.valueOf(Math.random()));
				leaseContractItemListGrid.fetchData(criteria);

			}
		});

		// 合同明细列表
		leaseContractItemListGrid = new LeaseContractItemListGrid();
		dataSourceTool.onCreateDataSource(DSKey.C_LEASECONTRACT_ITEM,
				DSKey.C_LEASECONTRACT_TIEM_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						leaseContractItemListGrid.setDataSource(dataSource);
						leaseContractItemListGrid.drawGrid();
					}
				});

		// 主容器
		SectionStack sStack = new SectionStack();
		sStack.setHeight100();
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);
		// 合同容器
		SectionStackSection sStackSection = new SectionStackSection(ui.getM()
				.mod_leaseContract_name());
		sStackSection.addItem(leaseContractListGrid);
		sStackSection.setExpanded(true);
		sStack.addSection(sStackSection);
		// 合同详细容器
		SectionStackSection siStackSection = new SectionStackSection(ui.getB()
				.leaseContractItems());
		siStackSection.setItems(leaseContractItemListGrid);
		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);
		v.addMember(sStack);
		return v;
	}

}
