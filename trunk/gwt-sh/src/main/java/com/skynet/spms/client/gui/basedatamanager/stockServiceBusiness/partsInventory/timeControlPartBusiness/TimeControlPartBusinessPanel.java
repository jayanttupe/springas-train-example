package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.partsInventory.timeControlPartBusiness;

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

public class TimeControlPartBusinessPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "库存时控件预警管理管理信息维护页面";

	private TimeControlPartBusinessButtonPanel timeControlPartBusinessButtonPanel;
	private TimeControlPartBusinessListgrid timeControlPartBusinessListgrid;

	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;
	private SectionStackSection detailPanelSection;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "库存时控件预警管理管理模块";
		private String id;

		public Canvas create() {
			TimeControlPartBusinessPanel panel = new TimeControlPartBusinessPanel();

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

		// 获取数据源
		String modeName = "stockServiceBusiness.partsInventory.timeControlPartBusiness";
		String dsName = "timeControlPartBusiness_dataSource";

			// 主Layout
		mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();

		// 主Section容器
		mainPanelSection = new SectionStack();
		mainPanelSection.setHeight100();
		mainPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainPanelSection.setAnimateSections(true);

		// 主列表Grid
		timeControlPartBusinessListgrid = new TimeControlPartBusinessListgrid();
		timeControlPartBusinessListgrid.setHeight100();
		
		// ListGrid中的选择事件处理
		timeControlPartBusinessListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					timeControlPartBusinessListgrid.selectRecords(timeControlPartBusinessListgrid.getSelection(), false);
					timeControlPartBusinessListgrid.selectRecord(selectedRecord);
				}else if(timeControlPartBusinessListgrid.getSelection().length == 1){
					selectedRecord = timeControlPartBusinessListgrid.getSelection()[0];
					timeControlPartBusinessListgrid.scrollToRow(timeControlPartBusinessListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						timeControlPartBusinessListgrid.setDataSource(dataSource);
						timeControlPartBusinessListgrid.fetchData();
						timeControlPartBusinessListgrid.drawTimeControlPartBusinessListgrid();
					}
				});

		// 重构了过滤方法
		timeControlPartBusinessListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
					@Override
					public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
						Criteria criteria = event.getCriteria();
						criteria.addCriteria("filter", "1");
						timeControlPartBusinessListgrid.fetchData(criteria);
					}
				});
		
		// 共用按钮面板
		timeControlPartBusinessButtonPanel = new TimeControlPartBusinessButtonPanel(timeControlPartBusinessListgrid);

		// 主列表面板
		detailPanelSection = new SectionStackSection("库存时控件预警信息");

		detailPanelSection.setItems(timeControlPartBusinessListgrid);
		detailPanelSection.setItems(timeControlPartBusinessButtonPanel);
		detailPanelSection.setExpanded(true);

		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(timeControlPartBusinessButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}
