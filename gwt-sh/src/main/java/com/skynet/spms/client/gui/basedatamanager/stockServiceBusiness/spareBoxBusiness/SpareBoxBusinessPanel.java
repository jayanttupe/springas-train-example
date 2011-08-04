/*
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.spareBoxBusiness;

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

public class SpareBoxBusinessPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "配料单管理信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "配料单管理模块";
		private String id;
		
		public Canvas create() {
			SpareBoxBusinessPanel panel = new SpareBoxBusinessPanel();
			
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
		final PickingListListgrid pickingListListgrid = new PickingListListgrid();
		pickingListListgrid.setHeight("50%");
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "stockServiceBusiness.outStockRoomBusiness.pickingList";
		String headDSName = "pickingList_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickingListListgrid.setDataSource(dataSource);
						pickingListListgrid.fetchData();
						pickingListListgrid.drawPickingListListgrid();
					}
				});
		pickingListListgrid.setShowFilterEditor(true);

		// 明细列表Grid
		final PickingListPartItemsListgrid pickingListPartItemsListgrid = new PickingListPartItemsListgrid();
		pickingListPartItemsListgrid.setHeight("100%");
		pickingListPartItemsListgrid.setAutoSaveEdits(false);
		pickingListPartItemsListgrid.setAutoFetchData(false);
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "stockServiceBusiness.outStockRoomBusiness.pickingList";
		String detailDSName = "pickingListPartItems_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						pickingListPartItemsListgrid.setDataSource(dataSource);
						pickingListPartItemsListgrid.drawPickingListPartItemsListgrid();
					}
				});
		
		// 根据选择的，取得相应的配料明细
		pickingListListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				pickingListPartItemsListgrid.setData(new ListGridRecord[]{});
				pickingListPartItemsListgrid.fetchData(new Criteria("pickingListID", pickingListListgrid.getSelectedRecord().getAttribute("id").toString()));
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
		SectionStackSection headSection = new SectionStackSection("配料单信息列表");
		headSection.setItems(pickingListListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(pickingListPartItemsListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		SectionStackSection detailSection = new SectionStackSection("配料单明细信息列表");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 共用按钮面板
		final PickingListButtonPanel stockroomManageButtonPanel =
			new PickingListButtonPanel(pickingListListgrid, pickingListPartItemsListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainSectionStack.addSection(detailSection);
		mainPanelLayout.addMember(stockroomManageButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
*/
