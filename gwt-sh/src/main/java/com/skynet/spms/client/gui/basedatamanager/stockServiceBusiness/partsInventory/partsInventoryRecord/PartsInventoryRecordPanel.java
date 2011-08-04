package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.partsInventory.partsInventoryRecord;

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
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;

public class PartsInventoryRecordPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "备件库存查询管理信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "备件库存查询管理模块";
		private String id;
		
		public Canvas create() {
			PartsInventoryRecordPanel panel = new PartsInventoryRecordPanel();
			
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
		final PartsInventoryRecordListgrid partsInventoryRecordListgrid = new PartsInventoryRecordListgrid();
		partsInventoryRecordListgrid.setHeight100();
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "stockServiceBusiness.partsInventory.partsInventoryRecord";
		String headDSName = "partsInventoryRecord_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						partsInventoryRecordListgrid.setDataSource(dataSource);
						partsInventoryRecordListgrid.fetchData();
						partsInventoryRecordListgrid.drawPartsInventoryRecordListgrid();
					}
				});
		partsInventoryRecordListgrid.setShowFilterEditor(true);
		
		partsInventoryRecordListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				partsInventoryRecordListgrid.fetchData(criteria);
			}
		});
		
		// ListGrid中的选择事件处理
		partsInventoryRecordListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					partsInventoryRecordListgrid.selectRecords(partsInventoryRecordListgrid.getSelection(), false);
					partsInventoryRecordListgrid.selectRecord(selectedRecord);
				}else if(partsInventoryRecordListgrid.getSelection().length == 1){
					selectedRecord = partsInventoryRecordListgrid.getSelection()[0];
					partsInventoryRecordListgrid.scrollToRow(partsInventoryRecordListgrid.getRecordIndex(selectedRecord));
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
		SectionStackSection headSection = new SectionStackSection("备件库存信息");
		headSection.setItems(partsInventoryRecordListgrid);
		headSection.setExpanded(true);

		// 共用按钮面板
		final PartsInventoryRecordButtonPanel stockroomManageButtonPanel = new PartsInventoryRecordButtonPanel(partsInventoryRecordListgrid);
		
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
