package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.storageRacks;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;

public class StorageRacksPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "货架管理页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "货架管理模块";
		private String id;
		
		public Canvas create() {
			StorageRacksPanel panel = new StorageRacksPanel();
			
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
		final StorageRacksListgrid storageRacksListgrid = new StorageRacksListgrid();
		storageRacksListgrid.setHeight100();
		
		// ListGrid中的选择事件处理
		storageRacksListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					storageRacksListgrid.selectRecords(storageRacksListgrid.getSelection(), false);
					storageRacksListgrid.selectRecord(selectedRecord);
				}else if(storageRacksListgrid.getSelection().length == 1){
					selectedRecord = storageRacksListgrid.getSelection()[0];
					storageRacksListgrid.scrollToRow(storageRacksListgrid.getRecordIndex(selectedRecord));
				}
			}
		});
		
		DataSourceTool headDST = new DataSourceTool();
		String modeName = "stockServiceBusiness.basicData.storageRacks";
		String dsName = "storageRacks_dataSource";
		headDST.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						storageRacksListgrid.setDataSource(dataSource);
						storageRacksListgrid.fetchData();
						storageRacksListgrid.drawStorageRacksListgrid();
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
		SectionStackSection headSection = new SectionStackSection("货架信息");
		headSection.setItems(storageRacksListgrid);
		headSection.setExpanded(true);

		// 共用按钮面板
		final StorageRacksButtonPanel storageRacksButtonPanel = new StorageRacksButtonPanel(storageRacksListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainPanelLayout.addMember(storageRacksButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}