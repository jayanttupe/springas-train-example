package com.skynet.spms.client.gui.customerplatform.repairsheet;

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
 * 送修申请单管理主面板
 * 
 * @author tqc
 * 
 */
public class RepairRequisitionPanel extends ShowcasePanel {

	private static I18n ui = new I18n();

	private RepairRequisitionToolStrip toolStrip;

	private RepairRequisitionListGrid repairRequisitionListGrid;

	public SheetModelLocator model = SheetModelLocator.getInstance();

	public static class Factory implements PanelFactory {
		private String DESCRIPTION = ui.getM().mod_repairRequisition_desc();
		private String id;

		public Canvas create() {
			RepairRequisitionPanel panel = new RepairRequisitionPanel();
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
		repairRequisitionListGrid = new RepairRequisitionListGrid();
		repairRequisitionListGrid.setHeight("50%");
		// 初始化数据源, 指定查询数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_REPAIRREQUISITIONSHEET,
				DSKey.C_REPAIRREQUISITIONSHEET_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						repairRequisitionListGrid.setDataSource(dataSource);
						repairRequisitionListGrid.fetchData();
						repairRequisitionListGrid.drawGrid();
						model.repairRequisitionListGrid = repairRequisitionListGrid;
					}
				});

		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setMembersMargin(2);
		v.setWidth100();
		v.setHeight100();
		
		toolStrip = new RepairRequisitionToolStrip(repairRequisitionListGrid);

		SectionStack sStack = new SectionStack();
		sStack.setHeight(280);
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);
		sStack.setOverflow(Overflow.HIDDEN);
		sStack.setShowResizeBar(true);

		SectionStackSection siStackSection = new SectionStackSection(ui.getM()
				.mod_repairRequisition_list_title());
		siStackSection.addItem(repairRequisitionListGrid);
		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);

		v.addMember(toolStrip);
		v.addMember(sStack);

		return v;
	}

	public String getIntro() {
		return ui.getM().mod_repairRequisition_desc();
	}
}