package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.repairCodeTask;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockTask.StockTaskListgrid;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.FetchDataEvent;
import com.smartgwt.client.widgets.events.FetchDataHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class RepairCodeTaskPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "补码任务跟踪信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "补码任务跟踪模块";
		private String id;
		
		public Canvas create() {
			RepairCodeTaskPanel panel = new RepairCodeTaskPanel();
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

	@Override
	public Canvas getViewPanel() {

		// 主Layout
		VLayout mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();

		// 主列表Grid
		final StockTaskListgrid stockTaskListgrid = new StockTaskListgrid();
		stockTaskListgrid.setHeight("50%");
		
		// 重构过滤条件
		stockTaskListgrid.addFetchDataHandler(new FetchDataHandler() {
			@Override
			public void onFilterData(FetchDataEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("taskType", "RC");
				criteria.addCriteria("filter", "1");
				
				stockTaskListgrid.setCriteria(criteria);
			}
		});
		
		// ListGrid中的选择事件处理
		stockTaskListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					stockTaskListgrid.selectRecords(stockTaskListgrid.getSelection(), false);
					stockTaskListgrid.selectRecord(selectedRecord);
				}else if(stockTaskListgrid.getSelection().length == 1){
					selectedRecord = stockTaskListgrid.getSelection()[0];
					stockTaskListgrid.scrollToRow(stockTaskListgrid.getRecordIndex(selectedRecord));
				}
			}
		});
		
		// 获取数据源
		String modeName = "stockServiceBusiness.stockTask.repairCodeTask";
		String dsName = "stockTask_dataSource";
		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource,DataInfo dataInfo) {
				stockTaskListgrid.setDataSource(dataSource);
				stockTaskListgrid.drawStockTaskListgrid();
				
				Criteria criteria = new Criteria();
				criteria.addCriteria("taskType", "SC");
				stockTaskListgrid.fetchData(criteria);

			}
		});

		// 明细列表Grid
		final RepairCodeTaskItemListgrid repairCodeTaskItemListgrid = new RepairCodeTaskItemListgrid();
		repairCodeTaskItemListgrid.setHeight("100%");
		// 获取数据源
		String detailmodeName = "stockServiceBusiness.stockTask.repairCodeTask";
		String detaildsName = "repairCodeTaskItem_dataSource";
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
				new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						repairCodeTaskItemListgrid.setDataSource(dataSource);
						repairCodeTaskItemListgrid.drawRepairCodeTaskItemListgrid();
					}
				});
		
		// 共用按钮面板
		RepairCodeTaskButtonPanel repairCodeTaskButtonPanel = new RepairCodeTaskButtonPanel();

		// 取得当前选中任务明细
		stockTaskListgrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				String taskID = stockTaskListgrid.getSelectedRecord().getAttribute("id");
				Criteria criteria = new Criteria();
				criteria.addCriteria("taskID", "" + taskID);
				repairCodeTaskItemListgrid.fetchData(criteria);

			}
		});

		// 加载各面板到容器
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(repairCodeTaskItemListgrid);

		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);

		// 主列表面板
		SectionStackSection headSection = new SectionStackSection("补码任务信息");
		headSection.setItems(stockTaskListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		SectionStackSection detailSection = new SectionStackSection("补码任务明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 主Section容器
		SectionStack mainPanelSection = new SectionStack();
		mainPanelSection.setHeight("50%");
		mainPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainPanelSection.setAnimateSections(true);

		// 明细Section容器
		final SectionStack detailPanelSection = new SectionStack();
		detailPanelSection.setHeight("100%");
		detailPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		detailPanelSection.setAnimateSections(true);
		detailPanelSection.addSection(detailSection);
		
		// 加载各面板到容器
		mainPanelSection.addSection(headSection);
		mainPanelSection.addSection(detailSection);
		mainPanelLayout.addMember(repairCodeTaskButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
