package com.skynet.spms.client.gui.supplierSupport.customerRepairInsOrder;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 送修送检指令管理主面板
 * 
 * @author tqc
 * 
 */
public class CustomerRepairInsOrderPanel extends ShowcasePanel {

	private static I18n ui = new I18n();

	private CustomerRepairInsOrderListGrid customerRepairInsOrderListGrid;

	public static class Factory implements PanelFactory {
		private String DESCRIPTION = ui.getM().mod_customerRepairInsOrder_name();
		private String id;

		public Canvas create() {
			CustomerRepairInsOrderPanel panel = new CustomerRepairInsOrderPanel();
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
		customerRepairInsOrderListGrid = new CustomerRepairInsOrderListGrid();
		// 初始化数据源, 指定查询数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_CUSTOMERREPAIRINSORDER,
				DSKey.C_CUSTOMERREPAIRINSORDER_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						customerRepairInsOrderListGrid
								.setDataSource(dataSource);
						customerRepairInsOrderListGrid.fetchData();
						customerRepairInsOrderListGrid.drawGrid();
					}
				});

		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setMembersMargin(2);
		v.setWidth100();
		v.setHeight100();

		CustomerRepairInsOrderToolStrip toolStrip = new CustomerRepairInsOrderToolStrip(
				customerRepairInsOrderListGrid);

		SectionStack listGridStack = new SectionStack();
		listGridStack.setHeight(280);
		listGridStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		listGridStack.setAnimateSections(true);
		listGridStack.setOverflow(Overflow.HIDDEN);
		listGridStack.setShowResizeBar(true);

		SectionStackSection siStackSection = new SectionStackSection(ui.getM()
				.mod_customerRepairInsOrder_name());
		siStackSection.addItem(customerRepairInsOrderListGrid);
		siStackSection.setExpanded(true);
		listGridStack.addSection(siStackSection);

		v.addMember(toolStrip);
		v.addMember(listGridStack);
		return v;
	}

	public String getIntro() {
		return ui.getM().mod_customerRepairInsOrder_name();
	}
}