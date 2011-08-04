package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook;

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

public class BusinessScopeAccountBookPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "经营电子帐册信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "经营电子帐册模块";
		private String id;
		
		public Canvas create() {
			BusinessScopeAccountBookPanel panel = new BusinessScopeAccountBookPanel();
			
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
		final VLayout mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();
		
		// 头部列表Grid
		final BusinessScopeAccountBookListgrid businessScopeAccountBookListgrid = new BusinessScopeAccountBookListgrid();
		businessScopeAccountBookListgrid.setHeight("50%");
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook";
		String headDSName = "businessScopeAccountBook_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						businessScopeAccountBookListgrid.setDataSource(dataSource);
						businessScopeAccountBookListgrid.fetchData();
						businessScopeAccountBookListgrid.drawBusinessScopeAccountBookListgrid();
					}
				});
		
		// 重构了过滤方法
		businessScopeAccountBookListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
					@Override
					public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
						Criteria criteria = event.getCriteria();
						criteria.addCriteria("filter", "1");
						businessScopeAccountBookListgrid.fetchData(criteria);
					}
				});
		
		// ListGrid中的选择事件处理
		businessScopeAccountBookListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					businessScopeAccountBookListgrid.selectRecords(businessScopeAccountBookListgrid.getSelection(), false);
					businessScopeAccountBookListgrid.selectRecord(selectedRecord);
				}else if(businessScopeAccountBookListgrid.getSelection().length == 1){
					selectedRecord = businessScopeAccountBookListgrid.getSelection()[0];
					businessScopeAccountBookListgrid.scrollToRow(businessScopeAccountBookListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 明细列表Grid
		final BusinessScopeAccountBookItemsListgrid businessScopeAccountBookItemsListgrid = new BusinessScopeAccountBookItemsListgrid();
		businessScopeAccountBookItemsListgrid.setHeight("100%");
		businessScopeAccountBookItemsListgrid.setCanEdit(true);
		businessScopeAccountBookItemsListgrid.setEditEvent(ListGridEditEvent.CLICK);
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "stockServiceBusiness.bondedWarehouseBusiness.businessScopeAccountBook";
		String detailDSName = "businessScopeAccountBookItems_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						businessScopeAccountBookItemsListgrid.setDataSource(dataSource);
						businessScopeAccountBookItemsListgrid.drawBusinessScopeAccountBookItemsListgrid();
					}
				});
		
		// 根据选择的电子帐册，取得相应的明细
		businessScopeAccountBookListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				businessScopeAccountBookItemsListgrid.setData(new ListGridRecord[]{});
				businessScopeAccountBookItemsListgrid.fetchData(new Criteria("businessScopeAccountBookID", businessScopeAccountBookListgrid.getSelectedRecord().getAttribute("id").toString()));
			}
		});

		// 共用按钮面板
		final BusinessScopeAccountBookButtonPanel businessScopeAccountBookButtonPanel = 
			new BusinessScopeAccountBookButtonPanel(businessScopeAccountBookListgrid);
		
		// 主Section容器
		final SectionStack mainPanelSection = new SectionStack();
		mainPanelSection.setHeight100();
		mainPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainPanelSection.setAnimateSections(true);
		
		// 头部列表面板
		SectionStackSection headSection = new SectionStackSection("经营范围电子帐册信息");
		headSection.setItems(businessScopeAccountBookListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(businessScopeAccountBookItemsListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		SectionStackSection detailSection = new SectionStackSection("经营范围电子帐册明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(headSection);	
		mainPanelSection.addSection(detailSection);
		mainPanelLayout.addMember(businessScopeAccountBookButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
