package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook;

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
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;

public class ClearanceAccountBookPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "通关电子帐册信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "通关电子帐册模块";
		private String id;
		
		public Canvas create() {
			ClearanceAccountBookPanel panel = new ClearanceAccountBookPanel();
			
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
		final ClearanceAccountBookListgrid clearanceAccountBookListgrid = new ClearanceAccountBookListgrid();
		clearanceAccountBookListgrid.setHeight("50%");
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook";
		String headDSName = "clearanceAccountBook_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						clearanceAccountBookListgrid.setDataSource(dataSource);
						clearanceAccountBookListgrid.fetchData();
						clearanceAccountBookListgrid.drawClearanceAccountBookListgrid();
					}
				});
		
		// 重构了过滤方法
		clearanceAccountBookListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
					@Override
					public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
						Criteria criteria = event.getCriteria();
						criteria.addCriteria("filter", "1");
						clearanceAccountBookListgrid.fetchData(criteria);
					}
				});
		
		// ListGrid中的选择事件处理
		clearanceAccountBookListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					clearanceAccountBookListgrid.selectRecords(clearanceAccountBookListgrid.getSelection(), false);
					clearanceAccountBookListgrid.selectRecord(selectedRecord);
				}else if(clearanceAccountBookListgrid.getSelection().length == 1){
					selectedRecord = clearanceAccountBookListgrid.getSelection()[0];
					clearanceAccountBookListgrid.scrollToRow(clearanceAccountBookListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 明细列表Grid
		final ClearanceAccountBookItemsListgrid clearanceAccountBookItemsListgrid = new ClearanceAccountBookItemsListgrid();
		clearanceAccountBookItemsListgrid.setHeight("100%");
		clearanceAccountBookItemsListgrid.setCanEdit(true);
		clearanceAccountBookItemsListgrid.setEditEvent(ListGridEditEvent.CLICK);
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "stockServiceBusiness.bondedWarehouseBusiness.clearanceAccountBook";
		String detailDSName = "clearanceAccountBookItems_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						clearanceAccountBookItemsListgrid.setDataSource(dataSource);
						clearanceAccountBookItemsListgrid.drawClearanceAccountBookItemsListgrid();
					}
				});
		
		// 根据选择的电子帐册，取得相应的明细
		clearanceAccountBookListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				clearanceAccountBookItemsListgrid.setData(new ListGridRecord[]{});
				clearanceAccountBookItemsListgrid.fetchData(new Criteria("clearanceAccountBookID", clearanceAccountBookListgrid.getSelectedRecord().getAttribute("id").toString()));
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
		SectionStackSection headSection = new SectionStackSection("通关电子帐册信息");
		headSection.setItems(clearanceAccountBookListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(clearanceAccountBookItemsListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		SectionStackSection detailSection = new SectionStackSection("通关电子帐册明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 共用按钮面板
		final ClearanceAccountBookButtonPanel clearanceAccountBookButtonPanel =
			new ClearanceAccountBookButtonPanel(clearanceAccountBookListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainSectionStack.addSection(detailSection);
		mainPanelLayout.addMember(clearanceAccountBookButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
