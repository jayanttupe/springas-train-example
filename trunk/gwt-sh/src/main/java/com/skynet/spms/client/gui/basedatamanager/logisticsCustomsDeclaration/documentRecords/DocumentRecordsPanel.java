package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.documentRecords;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class DocumentRecordsPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "单证记录管理信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "单证记录管理模块";
		private String id;
		
		public Canvas create() {
			DocumentRecordsPanel panel = new DocumentRecordsPanel();
			
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
		final DocumentRecordsListgrid documentRecordsListgrid = new DocumentRecordsListgrid();
		documentRecordsListgrid.setHeight("50%");
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "logisticsCustomsDeclaration.documentRecords";
		String headDSName = "documentRecords_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						documentRecordsListgrid.setDataSource(dataSource);
						documentRecordsListgrid.fetchData();
						documentRecordsListgrid.drawDocumentRecordsListgrid();
					}
				});

		// 运输单证列表Grid
		final TransprotationDocumentListgrid transprotationDocumentListgrid = new TransprotationDocumentListgrid();
		transprotationDocumentListgrid.setHeight100();
		transprotationDocumentListgrid.setCanEdit(true);
		transprotationDocumentListgrid.setEditEvent(ListGridEditEvent.CLICK);
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "logisticsCustomsDeclaration.documentRecords";
		String detailDSName = "transprotationDocument_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						transprotationDocumentListgrid.setDataSource(dataSource);
						transprotationDocumentListgrid.drawTransprotationDocumentListgrid();
					}
				});
		
		// 报关报检单证列表Grid
		final CIQDocumentListgrid ciqDocumentListgrid = new CIQDocumentListgrid();
		ciqDocumentListgrid.setHeight100();
		ciqDocumentListgrid.setCanEdit(true);
		ciqDocumentListgrid.setEditEvent(ListGridEditEvent.CLICK);
		DataSourceTool ciqDetailDST = new DataSourceTool();
		String ciqDetailModeName = "logisticsCustomsDeclaration.documentRecords";
		String ciqDetailDSName = "cIQDocument_dataSource";
		ciqDetailDST.onCreateDataSource(ciqDetailModeName, ciqDetailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						ciqDocumentListgrid.setDataSource(dataSource);
						ciqDocumentListgrid.drawCIQDocumentListgrid();
					}
				});
		
		// 根据选择的单证记录，取得明细数据
		documentRecordsListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				String documentID = documentRecordsListgrid.getSelectedRecord().getAttribute("id").toString();
				
				transprotationDocumentListgrid.setData(new ListGridRecord[]{});
				transprotationDocumentListgrid.fetchData(new Criteria("documentID",documentID));
				
				ciqDocumentListgrid.setData(new ListGridRecord[]{});
				ciqDocumentListgrid.fetchData(new Criteria("documentID",documentID));

			}
		});
		
		final TabSet deatilsTabSet = new TabSet();  
		deatilsTabSet.setTabBarPosition(Side.TOP);  
		deatilsTabSet.setWidth100();  
		deatilsTabSet.setHeight("50%");

		Tab tTab1 = new Tab("运输单证");  
		tTab1.setPane(transprotationDocumentListgrid);
		deatilsTabSet.addTab(tTab1);
   
		Tab tTab2 = new Tab("报关报检单证");  
		tTab2.setPane(ciqDocumentListgrid);
		deatilsTabSet.addTab(tTab2);
        
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
		
		VLayout headVLayout = new VLayout();
		headVLayout.addMember(documentRecordsListgrid);
		headVLayout.setHeight("50%");
		headVLayout.setShowResizeBar(true);
		
		// 头部列表面板
		SectionStackSection headSection = new SectionStackSection("单证记录管理信息列表");
		headSection.setItems(headVLayout);
		headSection.setExpanded(true);

		// 共用按钮面板
		final DocumentRecordsButtonPanel documentRecordsButtonPanel = new DocumentRecordsButtonPanel(documentRecordsListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainSectionStack.addMember(deatilsTabSet);	
		mainPanelLayout.addMember(documentRecordsButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
