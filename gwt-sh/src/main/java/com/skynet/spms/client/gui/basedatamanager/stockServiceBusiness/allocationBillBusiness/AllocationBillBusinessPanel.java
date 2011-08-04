package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.allocationBillBusiness;


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
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;

public class AllocationBillBusinessPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "航材调拨管理管理信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "航材调拨管理管理模块";
		private String id;

		public Canvas create() {
			AllocationBillBusinessPanel panel = new AllocationBillBusinessPanel();

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
		final AllocationBillBusinessListgrid allocationBillBusinessListgrid = new AllocationBillBusinessListgrid();
		allocationBillBusinessListgrid.setHeight("50%");
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "stockServiceBusiness.partsInventory.allocationBillBusiness";
		String headDSName = "allocationBillBusiness_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						allocationBillBusinessListgrid.setDataSource(dataSource);
						allocationBillBusinessListgrid.fetchData();
						allocationBillBusinessListgrid.drawAllocationBillBusinessListgrid();
				}
			});
		
		// ListGrid中的选择事件处理
		allocationBillBusinessListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					allocationBillBusinessListgrid.selectRecords(allocationBillBusinessListgrid.getSelection(), false);
					allocationBillBusinessListgrid.selectRecord(selectedRecord);
				}else if(allocationBillBusinessListgrid.getSelection().length == 1){
					selectedRecord = allocationBillBusinessListgrid.getSelection()[0];
					allocationBillBusinessListgrid.scrollToRow(allocationBillBusinessListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 明细列表Grid
		final AllocationGoodListgrid allocationGoodListgrid = new AllocationGoodListgrid();
		allocationGoodListgrid.setHeight("100%");
		allocationGoodListgrid.setAutoSaveEdits(false);
		allocationGoodListgrid.setAutoFetchData(false);
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "stockServiceBusiness.partsInventory.allocationBillBusiness";
		String detailDSName = "allocationGood_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						allocationGoodListgrid.setDataSource(dataSource);
						allocationGoodListgrid.drawAllocationGoodListgrid();
					}
				});
		
		allocationBillBusinessListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				allocationGoodListgrid.setData(new ListGridRecord[]{});
				allocationGoodListgrid.fetchData(new Criteria("allocationBillID", allocationBillBusinessListgrid.getSelectedRecord().getAttribute("id").toString()));
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
		SectionStackSection headSection = new SectionStackSection("航材调拨信息");
		headSection.setItems(allocationBillBusinessListgrid);
		headSection.setExpanded(true);
	
		// 明细列表面板
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(allocationGoodListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		SectionStackSection detailSection = new SectionStackSection("航材调拨明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);
	
		// 共用按钮面板
		final AllocationBillBusinessButtonPanel allocationBillBusinessButtonPanel = new AllocationBillBusinessButtonPanel(allocationBillBusinessListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainSectionStack.addSection(detailSection);
		mainPanelLayout.addMember(allocationBillBusinessButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
