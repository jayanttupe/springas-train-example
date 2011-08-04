package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.packingList;

import java.util.HashMap;
import java.util.Map;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class PackingListPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "装箱单管理信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "装箱单管理模块";
		private String id;
		
		public Canvas create() {
			PackingListPanel panel = new PackingListPanel();
			
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
		final PackingListListgrid packingListListgrid = new PackingListListgrid();
		packingListListgrid.setHeight("50%");
		packingListListgrid.setShowFilterEditor(true);
		
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "stockServiceBusiness.outStockRoomBusiness.packingList";
		String headDSName = "packingList_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						packingListListgrid.setDataSource(dataSource);
						
						Criteria criteria = new Criteria();
						criteria.addCriteria("type", "notRelease");
						packingListListgrid.fetchData(criteria);
						packingListListgrid.drawPackingListListgrid();
					}
				});
		
		// 重构过滤条件
		packingListListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("type", "notRelease");
			}
		});

		// ListGrid中的选择事件处理
		packingListListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					packingListListgrid.selectRecords(packingListListgrid.getSelection(), false);
					packingListListgrid.selectRecord(selectedRecord);
				}else if(packingListListgrid.getSelection().length == 1){
					selectedRecord = packingListListgrid.getSelection()[0];
					packingListListgrid.scrollToRow(packingListListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 明细列表Grid
		final PackingListPartItemsListgrid packingListPartItemsListgrid = new PackingListPartItemsListgrid();
		packingListPartItemsListgrid.setHeight("100%");
		packingListPartItemsListgrid.setAutoSaveEdits(false);
		packingListPartItemsListgrid.setAutoFetchData(false);
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "stockServiceBusiness.outStockRoomBusiness.packingList";
		String detailDSName = "packingListPartItems_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						packingListPartItemsListgrid.setDataSource(dataSource);
						packingListPartItemsListgrid.drawPackingListListgrid();
					}
				});
		
		// 根据选择的装箱单，取得相应的装箱明细数据
		packingListListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				// 获取箱子明细数据
				String boxDetailModeName = "stockServiceBusiness.outStockRoomBusiness.packingList";
				String boxDetailDsName = "packingListBoxItems_dataSource";
				DataSourceTool boxDetailDST = new DataSourceTool();
				boxDetailDST.onCreateDataSource(boxDetailModeName, boxDetailDsName,
						new PostDataSourceInit() {
							public void doPostOper(DataSource dataSource,
									DataInfo dataInfo) {
								Criteria criteria = new Criteria();
								criteria.addCriteria("temp", String.valueOf(Math.random()));
								criteria.addCriteria("packingListID", "" + packingListListgrid.getSelectedRecord().getAttribute("id"));
								dataSource.fetchData(criteria, new DSCallback() {
									@Override
									public void execute(DSResponse response, Object rawData, DSRequest request) {
										Record[] records = response.getData();
										// 加载装箱单备件明细Grid中的分箱号
										Map<String, String> valueMaps = new HashMap<String, String>();
										for (int i = 0; i < records.length; i++)
										{
											valueMaps.put(records[i].getAttribute("id"), records[i].getAttribute("boxNumber"));
										}
										packingListPartItemsListgrid.getField("boxID").setValueMap(valueMaps);

										// 取得装箱明细数据
										packingListPartItemsListgrid.setData(new ListGridRecord[]{});
										packingListPartItemsListgrid.fetchData(new Criteria("packingListID", packingListListgrid.getSelectedRecord().getAttribute("id").toString()));
									}
								});
							}
						});
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
		SectionStackSection headSection = new SectionStackSection("装箱单信息");
		headSection.setItems(packingListListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(packingListPartItemsListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		SectionStackSection detailSection = new SectionStackSection("装箱单明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 共用按钮面板
		final PackingListButtonPanel stockroomManageButtonPanel =
			new PackingListButtonPanel(packingListListgrid, packingListPartItemsListgrid);
		
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
