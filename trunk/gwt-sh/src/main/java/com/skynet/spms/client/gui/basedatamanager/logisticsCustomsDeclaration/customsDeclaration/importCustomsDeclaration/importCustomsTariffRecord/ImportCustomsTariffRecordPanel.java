/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Administrator
 *
 */
public class ImportCustomsTariffRecordPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "编辑进口关税维护页面";

	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "进口关税管理模块";
		private String id;
		
		public Canvas create() {
			ImportCustomsTariffRecordPanel panel = new ImportCustomsTariffRecordPanel();
			
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
		
		// 主Layout
		mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();
		
		final ImportCustomsTariffRecordListgrid importCustomsTariffRecordListgrid = new ImportCustomsTariffRecordListgrid();
		importCustomsTariffRecordListgrid.setHeight("50%");
        
		//获得数据源
		String modeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.importCustomsDeclaration.importCustomsTariffRecord";
		String dsName = "importCustomsTariffRecord_dataSource";

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();	
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						importCustomsTariffRecordListgrid.setDataSource(dataSource);
						importCustomsTariffRecordListgrid.fetchData();
						importCustomsTariffRecordListgrid.drawImportCustomsTariffRecordListgrid();
					}
				});
	
		// 明细列表Grid
		final ImportCustomsTariffRecordItemsListgrid importCustomsTariffRecordItemsListgrid = new ImportCustomsTariffRecordItemsListgrid();
		importCustomsTariffRecordItemsListgrid.setAutoSaveEdits(false);
		importCustomsTariffRecordItemsListgrid.setAutoFetchData(false);
		importCustomsTariffRecordItemsListgrid.setEditEvent(ListGridEditEvent.CLICK);
		importCustomsTariffRecordItemsListgrid.setHeight("100%");
		// 获取数据源
		String detailmodeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.importCustomsDeclaration.importCustomsTariffRecord";
		String detaildsName = "importCustomsDeclarationItems_dataSource";
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
				new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,DataInfo dataInfo) {
						importCustomsTariffRecordItemsListgrid.setDataSource(dataSource);
						importCustomsTariffRecordItemsListgrid.drawImportSecurityDepositItemsListgrid();
					}
				});
		// 取得相应的明细数据
		importCustomsTariffRecordListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				importCustomsTariffRecordItemsListgrid.setData(new ListGridRecord[]{});
				importCustomsTariffRecordItemsListgrid.fetchData(new Criteria("customsID",
						importCustomsTariffRecordListgrid.getSelectedRecord().getAttribute("customsID").toString()));
			}
		});
		
		// 共用按钮面板
		final ImportCustomsTariffRecordButtonPanel importCustomsTariffRecordButtonPanel = 
			new ImportCustomsTariffRecordButtonPanel(importCustomsTariffRecordListgrid);;
		
		// 加载各面板到容器
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(importCustomsTariffRecordItemsListgrid);

		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);

		// 主列表面板
		SectionStackSection headSection = new SectionStackSection("进口关税信息");
		headSection.setItems(importCustomsTariffRecordListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		SectionStackSection detailSection = new SectionStackSection("进口关税明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 主Section容器
		mainPanelSection = new SectionStack();
		mainPanelSection.setHeight100();
		mainPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainPanelSection.setAnimateSections(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(headSection);
		mainPanelSection.addSection(detailSection);
		mainPanelLayout.addMember(importCustomsTariffRecordButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
}
