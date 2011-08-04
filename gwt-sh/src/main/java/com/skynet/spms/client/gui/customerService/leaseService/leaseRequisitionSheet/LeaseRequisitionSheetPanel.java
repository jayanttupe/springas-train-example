package com.skynet.spms.client.gui.customerService.leaseService.leaseRequisitionSheet;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.common.DSKey;
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
/**
 * 申请供应商租赁指令
 * Description TODO
 *
 * @author   Tony FANG
 * @version  
 * @Date	 2011	2011-7-21
 */
public class LeaseRequisitionSheetPanel extends ShowcasePanel {
	private static I18n ui = new I18n();
	private LeaseRequisitionSheetToolStrip leaseRequisitionSheetToolStrip;
	private LeaseRequisitionSheetListGrid leaseRequisitionSheetListGrid;
	private LeaseRequisitionSheetItemListGrid leaseRequisitionSheetItemListGrid;

	private MainModelLocator modelLocator = MainModelLocator.getInstance();

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = ui.getM()
				.mod_leaseRequisitionSheeet_name();
		private String id;

		public Canvas create() {
			LeaseRequisitionSheetPanel panel = new LeaseRequisitionSheetPanel();
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
		// 租赁列表
		leaseRequisitionSheetListGrid = new LeaseRequisitionSheetListGrid();
		// 初始化租赁列表数据源
		final DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(DSKey.C_LEASEREQUISITIONSHEET,
				DSKey.C_LEASEREQUISITIONSHEET_DS, new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						leaseRequisitionSheetListGrid.setDataSource(dataSource);
						leaseRequisitionSheetListGrid.fetchData();
						leaseRequisitionSheetListGrid.drawGrid();
						modelLocator.leaseRequisitionSheetListGrid = leaseRequisitionSheetListGrid;
					}
				});

		// 点击租赁申请单查询租赁申请单明细
		leaseRequisitionSheetListGrid
				.addRecordClickHandler(new RecordClickHandler() {

					public void onRecordClick(RecordClickEvent event) {
						final String leaseId = leaseRequisitionSheetListGrid
								.getSelectedRecord().getAttribute("id");
						Criteria criteria = new Criteria();
						criteria.addCriteria("id", leaseId);
						criteria.addCriteria("_r", String
								.valueOf(Math.random()));
						leaseRequisitionSheetItemListGrid.fetchData(criteria);
						leaseRequisitionSheetItemListGrid.drawGrid();
					}
				});
		// 租赁ToolStrip
		leaseRequisitionSheetToolStrip = new LeaseRequisitionSheetToolStrip(
				leaseRequisitionSheetListGrid);

		// 租赁详细列表
		leaseRequisitionSheetItemListGrid = new LeaseRequisitionSheetItemListGrid();

		dataSourceTool.onCreateDataSource(DSKey.C_LEASEREQUISITIONSHEET_ITEM,
				DSKey.C_LEASEREQUISITIONSHEET_ITEM_DS,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						modelLocator.LeaseRequisitionSheetItemdataSource = dataSource;
						leaseRequisitionSheetItemListGrid
								.setDataSource(dataSource);
						leaseRequisitionSheetItemListGrid.drawGrid();
					}
				});

		VLayout v = new VLayout();
		v.setLayoutTopMargin(5);
		v.setMembersMargin(2);
		v.setWidth100();
		v.setHeight100();
		v.addMember(leaseRequisitionSheetToolStrip);
		// 主容器
		SectionStack sStack = new SectionStack();
		sStack.setHeight100();
		sStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		sStack.setAnimateSections(true);
		// 租赁容器
		SectionStackSection sStackSection = new SectionStackSection(ui.getM()
				.mod_leaseRequisitionSheeet_name());
		sStackSection.addItem(leaseRequisitionSheetListGrid);
		sStackSection.setExpanded(true);
		sStack.addSection(sStackSection);

		// 租赁详细容器
		SectionStackSection siStackSection = new SectionStackSection(ui.getB()
				.leaseRequisitionSheetItems());
		siStackSection.setItems(leaseRequisitionSheetItemListGrid);
		siStackSection.setExpanded(true);
		sStack.addSection(siStackSection);

		v.addMember(sStack);
		return v;
	}

}
