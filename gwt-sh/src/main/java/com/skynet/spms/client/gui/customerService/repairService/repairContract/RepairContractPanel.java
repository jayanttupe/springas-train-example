package com.skynet.spms.client.gui.customerService.repairService.repairContract;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.FeatureLoadCompleteEvent;
import com.skynet.spms.client.gui.base.FeatureLoadCompleteEventHandler;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
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
 * 送修合同管理主面板
 * 
 * @author tqc
 * 
 */
public class RepairContractPanel extends ShowcasePanel {

	private static I18n ui = new I18n();

	private RepairContractToolStrip toolStrip;
	
	Business business=new Business();

	private RepairContractListGrid repairContractListGrid;

	public static class Factory implements PanelFactory {
		private String DESCRIPTION = ui.getM().mod_repairContract_name();
		private String id;

		public Canvas create() {
			RepairContractPanel panel = new RepairContractPanel();
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
		repairContractListGrid = new RepairContractListGrid();
		// 初始化数据源, 指定查询数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_REPAIRECONTRACT,
				DSKey.C_REPAIRECONTRACT_FOR_LIST_GRID_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						repairContractListGrid.setDataSource(dataSource);
						repairContractListGrid.fetchData();
						repairContractListGrid.drawGrid();
					}
				});

		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setMembersMargin(2);
		v.setWidth100();
		v.setHeight100();

		toolStrip = new RepairContractToolStrip(repairContractListGrid);

		// 合同变更
		final ToolStripMenuButton contractChangeBtn = new ToolStripMenuButton(ui.getRepairModule().toolStrip_contract_changing());
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

		// 业务指令
		final ToolStripMenuButton bussinessOrderBtn = new ToolStripMenuButton(ui.getRepairModule().toolStrip_biz_order());
		Menu bussinessOrderMenu = new Menu();
		MenuItem bussinessOrderMenuItem = new MenuItem(ui.getRepairModule().toolStrip_create_repair_order());
		bussinessOrderMenuItem.addClickHandler(new ClickHandler() {
			public void onClick(MenuItemClickEvent event) {
				business.createCustomerRepairInspectionOrder(repairContractListGrid,bussinessOrderBtn);
			}
		});
		bussinessOrderMenu.setItems(bussinessOrderMenuItem);
		bussinessOrderBtn.setMenu(bussinessOrderMenu);


		toolStrip.handlerManager.addHandler(FeatureLoadCompleteEvent.HANDLER,
				new FeatureLoadCompleteEventHandler() {
					public void onFeatureLoadComplete(
							FeatureLoadCompleteEvent event) {
						toolStrip.addMenuButton(contractChangeBtn, 5);
						toolStrip.addMenuButton(submitBtn, 6);
						toolStrip.addMenuButton(bussinessOrderBtn, 7);
					}
				});

		SectionStack listGridStack = new SectionStack();
		listGridStack.setHeight(280);
		listGridStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		listGridStack.setAnimateSections(true);
		listGridStack.setOverflow(Overflow.HIDDEN);
		listGridStack.setShowResizeBar(true);

		SectionStackSection siStackSection = new SectionStackSection(ui.getM()
				.mod_repairContract_name());
		siStackSection.addItem(repairContractListGrid);
		siStackSection.setExpanded(true);
		listGridStack.addSection(siStackSection);

		v.addMember(toolStrip);
		v.addMember(listGridStack);

		return v;
	}

	public String getIntro() {
		return ui.getM().mod_repairContract_name();
	}
}