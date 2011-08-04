package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.containerBusiness;

import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class ContainerPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "容器信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "容器管理模块";
		private String id;
		
		public Canvas create() {
			ContainerPanel panel = new ContainerPanel();
			
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
		String modeName = "stockServiceBusiness.basicData.container";
		String dsName = "container_dataSource";

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
		final ContainerListgrid containerEntityListgrid = new ContainerListgrid();
		containerEntityListgrid.setHeight100();
		
		// 重构过滤条件
		containerEntityListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
			}
		});
		
		// ListGrid中的选择事件处理
		containerEntityListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					containerEntityListgrid.selectRecords(containerEntityListgrid.getSelection(), false);
					containerEntityListgrid.selectRecord(selectedRecord);
				}else if(containerEntityListgrid.getSelection().length == 1){
					selectedRecord = containerEntityListgrid.getSelection()[0];
					containerEntityListgrid.scrollToRow(containerEntityListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						containerEntityListgrid.setDataSource(dataSource);
						containerEntityListgrid.fetchData();
						containerEntityListgrid.drawContainerEntityListgrid();
					}
				});

		// 共用按钮面板
		ContainerButtonPanel containerEntityButtonPanel = new ContainerButtonPanel(containerEntityListgrid);
		
		// 主列表面板
		SectionStackSection detailPanelSection = new SectionStackSection("容器信息");
		detailPanelSection.setItems(containerEntityListgrid);
		detailPanelSection.setItems(containerEntityButtonPanel);
		detailPanelSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(containerEntityButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
