package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.credentials;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.DoubleClickEvent;
import com.smartgwt.client.widgets.events.DoubleClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class CredentialsPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "证书存档位置管理信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "证书存档位置管理模块";
		private String id;
		
		public Canvas create() {
			CredentialsPanel panel = new CredentialsPanel();
			
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
		final CredentialsListgrid credentialsListgrid = new CredentialsListgrid();
		credentialsListgrid.setHeight100();
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "stockServiceBusiness.checkAndAcceptBusiness.credentials";
		String headDSName = "credentials_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						dataSource.fetchData();
						credentialsListgrid.setDataSource(dataSource);
						credentialsListgrid.fetchData();
						credentialsListgrid.drawCredentialsListgrid();
					}
				});
		credentialsListgrid.setShowFilterEditor(true);
		
		// ListGrid中的选择事件处理
		credentialsListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					credentialsListgrid.selectRecords(credentialsListgrid.getSelection(), false);
					credentialsListgrid.selectRecord(selectedRecord);
				}else if(credentialsListgrid.getSelection().length == 1){
					selectedRecord = credentialsListgrid.getSelection()[0];
					credentialsListgrid.scrollToRow(credentialsListgrid.getRecordIndex(selectedRecord));
				}
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
		SectionStackSection headSection = new SectionStackSection("证书存档位置信息");
		headSection.setItems(credentialsListgrid);
		headSection.setExpanded(true);

		// 共用按钮面板
		final CredentialsButtonPanel stockroomManageButtonPanel = new CredentialsButtonPanel(credentialsListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainPanelLayout.addMember(stockroomManageButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}

