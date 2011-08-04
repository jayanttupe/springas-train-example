package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class TakeDeliveryOfJobPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "取货工作信息维护页面";

	private TakeDeliveryOfJobButtonPanel takeDeliveryOfJobButtonPanel;
	private TakeDeliveryOfJobListgrid takeDeliveryOfJobListgrid;

	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;
	private SectionStackSection detailPanelSection;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "取货工作管理模块";
		private String id;
		
		public Canvas create() {
			TakeDeliveryOfJobPanel panel = new TakeDeliveryOfJobPanel();
			
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
		String modeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.takeDeliveryOfJob";
		String dsName = "takeDeliveryOfJob_dataSource";

		// 主Layout
		mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();
		
		// 主Section容器
		mainPanelSection = new SectionStack();
		mainPanelSection.setHeight("50%");
		mainPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainPanelSection.setAnimateSections(true);

		// 主列表Grid
		takeDeliveryOfJobListgrid = new TakeDeliveryOfJobListgrid();
		takeDeliveryOfJobListgrid.setHeight100();

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						takeDeliveryOfJobListgrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("workStatus", "2");
						takeDeliveryOfJobListgrid.fetchData(criteria);
						takeDeliveryOfJobListgrid.drawTakeDeliveryOfJobListgrid();
					}
				});
		
		// 重构过滤查询条件
		takeDeliveryOfJobListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				criteria.addCriteria("workStatus", "2");
			}
		});

		// 共用按钮面板
		takeDeliveryOfJobButtonPanel = new TakeDeliveryOfJobButtonPanel(takeDeliveryOfJobListgrid);
		
		// 主列表面板
		detailPanelSection = new SectionStackSection("取货工作信息列表");
		detailPanelSection.setItems(takeDeliveryOfJobListgrid);
		detailPanelSection.setItems(takeDeliveryOfJobButtonPanel);
		detailPanelSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(takeDeliveryOfJobButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
