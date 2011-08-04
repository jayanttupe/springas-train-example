package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.waitPickingList;

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
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class WaitPickingPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "待发料信息管理维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "待发料管理模块";
		private String id;
		
		public Canvas create() {
			WaitPickingPanel panel = new WaitPickingPanel();
			
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
		final WaitPickingListgrid waitPickingListgrid = new WaitPickingListgrid();
		waitPickingListgrid.setHeight("50%");
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "stockServiceBusiness.outStockRoomBusiness.waitDeliveryOrder";
		String headDSName = "deliveryOrder_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						waitPickingListgrid.setDataSource(dataSource);
						waitPickingListgrid.fetchData();
						waitPickingListgrid.drawWaitPickingListgrid();
					}
				});
		waitPickingListgrid.setShowFilterEditor(true);
		
		// 重构过滤查询条件
		waitPickingListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
			}
		});

		// 明细列表Grid
		final WaitPickingItemsListgrid waitPickingItemsListgrid = new WaitPickingItemsListgrid();
		waitPickingItemsListgrid.setHeight("100%");
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "stockServiceBusiness.outStockRoomBusiness.waitDeliveryOrder";
		String detailDSName = "deliveryOrderItems_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						waitPickingItemsListgrid.setDataSource(dataSource);
						waitPickingItemsListgrid.drawWaitPickingItemsListgrid();
					}
				});
		
		// 根据选择的，取得相应的明细数据
		waitPickingListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				waitPickingItemsListgrid.setData(new ListGridRecord[]{});
				Criteria criteria = new Criteria();
				criteria.addCriteria("temp", String.valueOf(Math.random()));
				criteria.addCriteria("orderID", waitPickingListgrid.getSelectedRecord().getAttribute("orderID"));
				waitPickingItemsListgrid.fetchData(criteria);
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
		SectionStackSection headSection = new SectionStackSection("待发料信息");
		headSection.setItems(waitPickingListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(waitPickingItemsListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		SectionStackSection detailSection = new SectionStackSection("待发料明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 共用按钮面板
		final WaitPickingButtonPanel waitPickingButtonPanel =
			new WaitPickingButtonPanel(waitPickingListgrid, waitPickingItemsListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainSectionStack.addSection(detailSection);
		mainPanelLayout.addMember(waitPickingButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
