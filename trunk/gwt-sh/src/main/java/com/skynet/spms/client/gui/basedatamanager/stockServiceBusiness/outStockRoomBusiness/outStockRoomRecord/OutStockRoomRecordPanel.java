/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.outStockRoomBusiness.outStockRoomRecord;

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
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;

/**
 * @author Administrator
 *
 */
public class OutStockRoomRecordPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "出库记录管理维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "出库记录管理模块";
		private String id;
		
		public Canvas create() {
			OutStockRoomRecordPanel panel = new OutStockRoomRecordPanel();
			
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
		final OutStockRoomRecordListgrid outStockRoomRecordListgrid = new OutStockRoomRecordListgrid();
		outStockRoomRecordListgrid.setHeight("50%");
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "stockServiceBusiness.outStockRoomBusiness.outStockRoomRecord";
		String headDSName = "packingList_dataSource";
		outStockRoomRecordListgrid.setShowFilterEditor(true);
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						outStockRoomRecordListgrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("type", "release");
						outStockRoomRecordListgrid.fetchData(criteria);
						outStockRoomRecordListgrid.drawOutStockRoomRecordListgrid();
					}
				});

		// ListGrid中的选择事件处理
		outStockRoomRecordListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					outStockRoomRecordListgrid.selectRecords(outStockRoomRecordListgrid.getSelection(), false);
					outStockRoomRecordListgrid.selectRecord(selectedRecord);
				}else if(outStockRoomRecordListgrid.getSelection().length == 1){
					selectedRecord = outStockRoomRecordListgrid.getSelection()[0];
					outStockRoomRecordListgrid.scrollToRow(outStockRoomRecordListgrid.getRecordIndex(selectedRecord));
				}
			}
		});
		
		// 重构Grid过滤条件
		outStockRoomRecordListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				criteria.addCriteria("type", "release");
			}
		});

		// 明细列表Grid
		final OutStockRoomRecordItemsListgrid outStockRoomRecordItemsListgrid = new OutStockRoomRecordItemsListgrid();
		outStockRoomRecordItemsListgrid.setHeight("100%");
		outStockRoomRecordItemsListgrid.setAutoSaveEdits(false);
		outStockRoomRecordItemsListgrid.setAutoFetchData(false);
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "stockServiceBusiness.outStockRoomBusiness.outStockRoomRecord";
		String detailDSName = "packingListPartItems_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						outStockRoomRecordItemsListgrid.setDataSource(dataSource);
						outStockRoomRecordItemsListgrid.drawOutStockRoomRecordItemsListgrid();
					}
				});
		
		// 根据选择的装箱单，取得相应的装箱明细数据
		outStockRoomRecordListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				outStockRoomRecordItemsListgrid.setData(new ListGridRecord[]{});
				outStockRoomRecordItemsListgrid.fetchData(new Criteria("packingListID", outStockRoomRecordListgrid.getSelectedRecord().getAttribute("id").toString()));
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
		SectionStackSection headSection = new SectionStackSection("出库记录信息");
		headSection.setItems(outStockRoomRecordListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(outStockRoomRecordItemsListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		SectionStackSection detailSection = new SectionStackSection("出库记录明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 共用按钮面板
		final OutStockRoomRecordButtonPanel outStockRoomRecordButtonPanel =
			new OutStockRoomRecordButtonPanel(outStockRoomRecordListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainSectionStack.addSection(detailSection);
		mainPanelLayout.addMember(outStockRoomRecordButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}

