package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.logisticsOutlayRecordManage;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class LogisticsOutlayRecordPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "费用记录管理信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "费用记录管理模块";
		private String id;

		public Canvas create() {
			LogisticsOutlayRecordPanel panel = new LogisticsOutlayRecordPanel();

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
		// 头部列表Grid
		final LogisticsOutlayRecordListgrid logisticsOutlayRecordListgrid = new LogisticsOutlayRecordListgrid();
		logisticsOutlayRecordListgrid.setHeight("50%");
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "logisticsCustomsDeclaration.logisticsOutlayRecordManage";
		String headDSName = "logisticsOutlayRecordManage_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						logisticsOutlayRecordListgrid.setDataSource(dataSource);
						logisticsOutlayRecordListgrid.fetchData();
						logisticsOutlayRecordListgrid.drawLogisticsOutlayRecordListgrid();
				}
			});

		// 明细列表Grid
		final LogisticsOutlayItemListgrid logisticsOutlayItemListgrid = new LogisticsOutlayItemListgrid();
		logisticsOutlayItemListgrid.setHeight100();
		logisticsOutlayItemListgrid.setAutoSaveEdits(false);
		logisticsOutlayItemListgrid.setAutoFetchData(false);
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "logisticsCustomsDeclaration.logisticsOutlayRecordManage";
		String detailDSName = "logisticsOutlayItem_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						logisticsOutlayItemListgrid.setDataSource(dataSource);
						logisticsOutlayItemListgrid.drawLogisticsOutlayItemListgrid();
					}
				});
		
		logisticsOutlayRecordListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				logisticsOutlayItemListgrid.setData(new ListGridRecord[]{});
				logisticsOutlayItemListgrid.fetchData(new Criteria("logisticsOutlayItemID",logisticsOutlayRecordListgrid.getSelectedRecord().getAttribute("id").toString()));
			}
		});
		
		// 主Layout
		final VLayout mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();
		
		// 主Section容器
		final SectionStack mainSectionStack = new SectionStack();
		mainSectionStack.setHeight100();
		mainSectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainSectionStack.setAnimateSections(true);
		
		// 头部列表面板
		SectionStackSection headSection = new SectionStackSection("费用记录管理列表");
		headSection.setItems(logisticsOutlayRecordListgrid);
		headSection.setExpanded(true);
	
		// 明细列表面板
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(logisticsOutlayItemListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		SectionStackSection detailSection = new SectionStackSection("费用记录明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);
	
		// 共用按钮面板
		final LogisticsOutlayRecordButtonPanel logisticsOutlayRecordButtonPanel = new LogisticsOutlayRecordButtonPanel(logisticsOutlayRecordListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainSectionStack.addSection(detailSection);
		mainPanelLayout.addMember(logisticsOutlayRecordButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
