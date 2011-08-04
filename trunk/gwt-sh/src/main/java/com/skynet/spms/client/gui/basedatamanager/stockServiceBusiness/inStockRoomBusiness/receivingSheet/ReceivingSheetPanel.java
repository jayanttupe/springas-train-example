package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet;

import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
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

public class ReceivingSheetPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "航材收料单管理信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "航材收料单管理模块";
		private String id;
		
		public Canvas create() {
			ReceivingSheetPanel panel = new ReceivingSheetPanel();
			
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
		final ReceivingSheetListgrid receivingSheetManageListgrid = new ReceivingSheetListgrid();
		receivingSheetManageListgrid.setHeight("50%");
		receivingSheetManageListgrid.setShowFilterEditor(true);
		
		// ListGrid中的选择事件处理
		receivingSheetManageListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					receivingSheetManageListgrid.selectRecords(receivingSheetManageListgrid.getSelection(), false);
					receivingSheetManageListgrid.selectRecord(selectedRecord);
				}else if(receivingSheetManageListgrid.getSelection().length == 1){
					selectedRecord = receivingSheetManageListgrid.getSelection()[0];
					receivingSheetManageListgrid.scrollToRow(receivingSheetManageListgrid.getRecordIndex(selectedRecord));
				}
			}
		});
		
		// 重构过滤条件
		receivingSheetManageListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				receivingSheetManageListgrid.fetchData(criteria);
			}
		});
		
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "stockServiceBusiness.inStockRoomBusiness.receivingSheet";
		String headDSName = "receivingSheet_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						receivingSheetManageListgrid.setDataSource(dataSource);
						receivingSheetManageListgrid.fetchData();
						receivingSheetManageListgrid.drawsheetManageListgrid();
					}
				});

		// 明细列表Grid
		final ReceivingSheetItemsListgrid receivingSheetItemsListgrid = new ReceivingSheetItemsListgrid();
		receivingSheetItemsListgrid.setHeight("100%");
		receivingSheetItemsListgrid.setAutoSaveEdits(false);
		receivingSheetItemsListgrid.setCanEdit(false);
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "stockServiceBusiness.inStockRoomBusiness.receivingSheet";
		String detailDSName = "receivingSheetItems_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						receivingSheetItemsListgrid.setDataSource(dataSource);
						receivingSheetItemsListgrid.drawReceivingSheetItemsListgrid(false);
					}
				});
		
		// 根据选择的收料单，取得相应的收料单明细
		receivingSheetManageListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				receivingSheetItemsListgrid.setData(new ListGridRecord[]{});
				Criteria criteria = new Criteria();
				criteria.addCriteria("type", "receivingSheetDetail");
				criteria.addCriteria("receivingSheetID", "" + receivingSheetManageListgrid.getSelectedRecord().getAttribute("id").toString());			
				receivingSheetItemsListgrid.filterData(criteria);
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
		SectionStackSection headSection = new SectionStackSection("航材收料单信息");
		headSection.setItems(receivingSheetManageListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(receivingSheetItemsListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		SectionStackSection detailSection = new SectionStackSection("航材收料单明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 共用按钮面板
		final ReceivingSheetButtonPanel receivingSheetManageButtonPanel =
			new ReceivingSheetButtonPanel(receivingSheetManageListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainSectionStack.addSection(detailSection);
		mainPanelLayout.addMember(receivingSheetManageButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
