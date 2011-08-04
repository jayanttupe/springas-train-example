package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.exportCustomsDeclaration.exportSecurityDeposit;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.customsDeclaration.importCustomsDeclaration.importSecurityDeposit.ImportSecurityDepositButtonPanel;
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
public class ExportSecurityDepositPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "编辑出口保证金维护页面";
	
	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "出口保证金管理模块";
		private String id;
		
		public Canvas create() {
			ExportSecurityDepositPanel panel = new ExportSecurityDepositPanel();
			
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
		
		// 主列表Grid
		final ExportSecurityDepositListgrid exportSecurityDepositListgrid = new ExportSecurityDepositListgrid();
		exportSecurityDepositListgrid.setHeight("50%");
        
		//获得数据源
		String modeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportSecurityDeposit";
		String dsName = "exportSecurityDeposit_dataSource";

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();	
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						//dataSource.fetchData();
						exportSecurityDepositListgrid.setDataSource(dataSource);
						exportSecurityDepositListgrid.fetchData();
						exportSecurityDepositListgrid.drawExportSecurityDepositListgrid();
					}
				});
		
		// 明细列表Grid
		final ExportSecurityDepositItemsListgrid exportSecurityDepositItemsListgrid = new ExportSecurityDepositItemsListgrid();
		exportSecurityDepositItemsListgrid.setAutoSaveEdits(false);
		exportSecurityDepositItemsListgrid.setEditEvent(ListGridEditEvent.CLICK);
		exportSecurityDepositItemsListgrid.setHeight100();
		// 获取数据源
		String detailmodeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.customsClearanceJob.exportCustomsDeclaration.exportSecurityDeposit";
		String detaildsName = "exportCustomsDeclarationItems_dataSource";
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
				new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						exportSecurityDepositItemsListgrid.setDataSource(dataSource);
						exportSecurityDepositItemsListgrid.drawExportSecurityDepositSparepartsListgrid();
					}
				});
		
		// 取得相应的明细数据
		exportSecurityDepositListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				exportSecurityDepositItemsListgrid.setData(new ListGridRecord[]{});
				exportSecurityDepositItemsListgrid.fetchData(new Criteria("customsID",
						exportSecurityDepositListgrid.getSelectedRecord().getAttribute("customsID").toString()));
			}
		});
		
		// 共用按钮面板
		final ExportSecurityDepositButtonPanel exportSecurityDepositButtonPanel = new ExportSecurityDepositButtonPanel(exportSecurityDepositListgrid);
		
		// 加载各面板到容器
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(exportSecurityDepositItemsListgrid);

		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);

		// 主列表面板
		SectionStackSection headSection = new SectionStackSection("出口保证金信息");
		headSection.setItems(exportSecurityDepositListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		SectionStackSection detailSection = new SectionStackSection("出口保证金明细信息");
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
		mainPanelLayout.addMember(exportSecurityDepositButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
   }
}
