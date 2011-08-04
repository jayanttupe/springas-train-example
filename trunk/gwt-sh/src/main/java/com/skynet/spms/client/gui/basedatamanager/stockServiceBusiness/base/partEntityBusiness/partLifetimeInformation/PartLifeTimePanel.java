package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.base.partEntityBusiness.partLifetimeInformation;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;


public class PartLifeTimePanel extends ShowcasePanel {

	private static final String DESCRIPTION = "备件时寿信息管理信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "备件时寿信息管理模块";
		private String id;
		
		public Canvas create() {
			PartLifeTimePanel panel = new PartLifeTimePanel();
			
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
		String modeName = "stockServiceBusiness.partsInventory.partLifeTime";
		String dsName = "partLifeTime_dataSource";

		// 主Layout
		VLayout mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();
		
		// 主Section容器
		SectionStack mainPanelSection = new SectionStack();
		mainPanelSection.setHeight100();
		mainPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainPanelSection.setAnimateSections(true);

		// 主列表Grid
		final PartLifeTimeListgrid partLifeTimeListgrid = new PartLifeTimeListgrid();
		partLifeTimeListgrid.setHeight100();

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						partLifeTimeListgrid.setDataSource(dataSource);
						partLifeTimeListgrid.fetchData();
						partLifeTimeListgrid.drawPartLifetimeInformationListgrid();
					}
				});
		
		// 重构了过滤方法
		partLifeTimeListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				partLifeTimeListgrid.fetchData(criteria);
			}
		});
		
		// ListGrid中的选择事件处理
		partLifeTimeListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					partLifeTimeListgrid.selectRecords(partLifeTimeListgrid.getSelection(), false);
					partLifeTimeListgrid.selectRecord(selectedRecord);
				}else if(partLifeTimeListgrid.getSelection().length == 1){
					selectedRecord = partLifeTimeListgrid.getSelection()[0];
					partLifeTimeListgrid.scrollToRow(partLifeTimeListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 共用按钮面板
		PartLifeTimeButtonPanel partLifeTimeButtonPanel = new PartLifeTimeButtonPanel(partLifeTimeListgrid);
		
		// 主列表面板
		SectionStackSection detailPanelSection = new SectionStackSection("库存备件时寿信息");
		detailPanelSection.setItems(partLifeTimeListgrid);
		detailPanelSection.setItems(partLifeTimeButtonPanel);
		detailPanelSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(partLifeTimeButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
