package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportCustomsTariffRecord;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.ImportCustomsTariffRecordButtonPanel;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.ImportCustomsTariffRecordItemsListgrid;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importCustomsTariffRecord.ImportCustomsTariffRecordListgrid;
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
public class ExportCustomsTariffRecordPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "编辑出口关税维护页面";

	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;
	
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "出口关税管理模块";
		private String id;
		
		public Canvas create() {
			ExportCustomsTariffRecordPanel panel = new ExportCustomsTariffRecordPanel();
			
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
		
		final ExportCustomsTariffRecordListgrid exportCustomsTariffRecordListgrid = new ExportCustomsTariffRecordListgrid();
		exportCustomsTariffRecordListgrid.setHeight("50%");
        
		//获得数据源
		String modeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportCustomsTariffRecord";
		String dsName = "exportCustomsTariffRecord_dataSource";

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();	
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						//dataSource.fetchData();
						exportCustomsTariffRecordListgrid.setDataSource(dataSource);
						exportCustomsTariffRecordListgrid.fetchData();
						exportCustomsTariffRecordListgrid.drawExportCustomsTariffRecordListgrid();
					}
				});
		
		// 明细列表Grid
		final ExportCustomsTariffRecordItemsListgrid exportCustomsTariffRecordItemsListgrid = new ExportCustomsTariffRecordItemsListgrid();
		exportCustomsTariffRecordItemsListgrid.setAutoSaveEdits(false);
		exportCustomsTariffRecordItemsListgrid.setAutoFetchData(false);
		exportCustomsTariffRecordItemsListgrid.setEditEvent(ListGridEditEvent.CLICK);
		exportCustomsTariffRecordItemsListgrid.setHeight("100%");
		// 获取数据源
		String detailmodeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportCustomsTariffRecord";
		String detaildsName = "exportCustomsDeclarationItems_dataSource";
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
				new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						exportCustomsTariffRecordItemsListgrid.setDataSource(dataSource);
						exportCustomsTariffRecordItemsListgrid.drawExportCustomsTariffRecordItemsListgrid();
					}
				});
		// 取得相应的明细数据
		exportCustomsTariffRecordListgrid.addRecordClickHandler(new RecordClickHandler(){
			@Override
			public void onRecordClick(RecordClickEvent event) {
				exportCustomsTariffRecordItemsListgrid.setData(new ListGridRecord[]{});
				exportCustomsTariffRecordItemsListgrid.fetchData(new Criteria("customsID",
						exportCustomsTariffRecordListgrid.getSelectedRecord().getAttribute("customsID").toString()));
				
			}
			
		});
		// 共用按钮面板
		final ExportCustomsTariffRecordButtonPanel exportCustomsTariffRecordButtonPanel = 
			new ExportCustomsTariffRecordButtonPanel(exportCustomsTariffRecordListgrid);;
		
		// 加载各面板到容器
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(exportCustomsTariffRecordItemsListgrid);

		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);

		// 主列表面板
		SectionStackSection headSection = new SectionStackSection("出口关税信息");
		headSection.setItems(exportCustomsTariffRecordListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		SectionStackSection detailSection = new SectionStackSection("出口关税明细信息");
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
		mainPanelLayout.addMember(exportCustomsTariffRecordButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
}