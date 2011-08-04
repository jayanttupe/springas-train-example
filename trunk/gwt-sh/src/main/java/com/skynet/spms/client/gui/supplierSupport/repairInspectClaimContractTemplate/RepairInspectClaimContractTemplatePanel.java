package com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate;

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
import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.model.ModelLocator;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;
import com.smartgwt.client.widgets.toolbar.ToolStripMenuButton;

/**
 * 供应商修理检验/索赔合同模板管理主面板
 * 
 * @author tqc
 * 
 */
public class RepairInspectClaimContractTemplatePanel extends ShowcasePanel {

	private static I18n ui = new I18n();

	private ModelLocator model = ModelLocator.getInstance();

	private RepairInspectClaimContractTemplateToolStrip toolStrip;

	private RepairInspectClaimContractTemplateListGrid repairContractListGrid;
	
	private BusinessMang business=new BusinessMang();

	public static class Factory implements PanelFactory {
		private String DESCRIPTION = ui.getM()
				.mod_repairInspectClaimContractTemplate_name();
		private String id;

		public Canvas create() {
			RepairInspectClaimContractTemplatePanel panel = new RepairInspectClaimContractTemplatePanel();
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

	public Canvas getViewPanel() {
		repairContractListGrid = new RepairInspectClaimContractTemplateListGrid();
		// 初始化数据源, 指定查询数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(
				DSKey.R_EPAIRINSPECTCLAIMCONTRACTTEMPLATE,
				DSKey.R_EPAIRINSPECTCLAIMCONTRACTTEMPLATE_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						repairContractListGrid.setDataSource(dataSource);
						repairContractListGrid.fetchData();
						repairContractListGrid.drawGrid();
						model.repairInsClaimContractListGrid = repairContractListGrid;
					}
				});

		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setMembersMargin(2);
		v.setWidth100();
		v.setHeight100();

		toolStrip = new RepairInspectClaimContractTemplateToolStrip(
				repairContractListGrid);

		// 合同变更
		final ToolStripMenuButton contractChangeBtn = new ToolStripMenuButton(
				ui.getRepairModule().toolStrip_contract_changing());
		Menu contractChangeMenu = new Menu();
		MenuItem contractChangeMenuItem = new MenuItem(ui.getRepairModule().toolStrip_contract_changing());
		contractChangeMenuItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				business.changeContract(repairContractListGrid);
			}
		});
		
		MenuItem contractChangeMenuPubishItem = new MenuItem(ui.getRepairModule().toolStrip_publish_contract_changing());
		contractChangeMenuPubishItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				business.publishContractChanged(repairContractListGrid);
			}
		});
		
		contractChangeMenu.setItems(contractChangeMenuItem,
				contractChangeMenuPubishItem);
		contractChangeBtn.setMenu(contractChangeMenu);

		// 提交审批
		final ToolStripMenuButton submitBtn = new ToolStripMenuButton(ui.getRepairModule().toolStrip_submit_check());
		Menu submitMenu = new Menu();
		MenuItem submitMenuItem = new MenuItem(ui.getRepairModule().toolStrip_submit_first());
		submitMenuItem.addClickHandler(new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				business.approvalContractFirst(repairContractListGrid);
			}
		});
		
		MenuItem submit = new MenuItem(ui.getRepairModule().toolStrip_submit_end());
		submitMenu.setItems(submitMenuItem, submit);
		submitBtn.setMenu(submitMenu);
		submit.addClickHandler(new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				business.approvalContractFinally(repairContractListGrid);
			}
		});

		toolStrip.handlerManager.addHandler(FeatureLoadCompleteEvent.HANDLER,
				new FeatureLoadCompleteEventHandler() {
					public void onFeatureLoadComplete(
							FeatureLoadCompleteEvent event) {
						toolStrip.addMenuButton(contractChangeBtn, 2);
						toolStrip.addMenuButton(submitBtn, 3);
						toolStrip.addMenuButton(
								MenuButtonBuilder
										.create(ContractIndexKey.repairInspectClaimContractTemplateManagerForOrder,
												repairContractListGrid,
												MenuButtonBuilder.ALL,
												DirectiveBusinessType.repair
														.name()), 4);
					}
				});

		SectionStack listGridStack = new SectionStack();
		listGridStack.setHeight(280);
		listGridStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		listGridStack.setAnimateSections(true);
		listGridStack.setOverflow(Overflow.HIDDEN);
		listGridStack.setShowResizeBar(true);

		SectionStackSection siStackSection = new SectionStackSection(ui.getM()
				.mod_repairInspectClaimContractTemplate_name());
		siStackSection.addItem(repairContractListGrid);
		siStackSection.setExpanded(true);
		listGridStack.addSection(siStackSection);

		v.addMember(toolStrip);
		v.addMember(listGridStack);
		return v;
	}

	public String getIntro() {
		return ui.getM().mod_repairInspectClaimContractTemplate_name();
	}
}