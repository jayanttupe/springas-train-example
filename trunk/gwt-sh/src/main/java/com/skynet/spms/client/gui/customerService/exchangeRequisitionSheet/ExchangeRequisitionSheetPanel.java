package com.skynet.spms.client.gui.customerService.exchangeRequisitionSheet;

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
public class ExchangeRequisitionSheetPanel extends ShowcasePanel {

	private static I18n ui = new I18n();

	private ExchangeRequisitionSheetToolStrip toolStrip;

	private ExchangeRequisitionSheetListGrid repairRequisitionListGrid;

	public SheetModelLocator model = SheetModelLocator.getInstance();

	public static class Factory implements PanelFactory {
		//private String DESCRIPTION = ui.getM().mod_exchangeRequisition_desc();
		private String DESCRIPTION = "交换申请单";
		private String id;

		public Canvas create() {
			ExchangeRequisitionSheetPanel panel = new ExchangeRequisitionSheetPanel();
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
		repairRequisitionListGrid = new ExchangeRequisitionSheetListGrid();
		// 初始化数据源, 指定查询数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_EXCHANGE_REQUISITION,
				DSKey.C_EXCHANGE_REQUISITION_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						repairRequisitionListGrid.setDataSource(dataSource);
						repairRequisitionListGrid.fetchData();
						repairRequisitionListGrid.drawGrid();
						model.dataSource=dataSource;
						model.repairRequisitionListGrid = repairRequisitionListGrid;
					}
				});

		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setMembersMargin(2);
		v.setWidth100();
		v.setHeight100();
		
		toolStrip = new ExchangeRequisitionSheetToolStrip(repairRequisitionListGrid);

		SectionStack sStack = new SectionStack();
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);
		sStack.setOverflow(Overflow.HIDDEN);
		sStack.setShowResizeBar(true);

		SectionStackSection siStackSection = new SectionStackSection(ui.getM()
				.mod_exchangeRequisition_list_title());
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