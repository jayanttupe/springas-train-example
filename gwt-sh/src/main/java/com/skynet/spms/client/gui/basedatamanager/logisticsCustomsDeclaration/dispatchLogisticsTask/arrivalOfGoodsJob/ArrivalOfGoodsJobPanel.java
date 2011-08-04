package com.skynet.spms.client.gui.basedatamanager.logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob;

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

public class ArrivalOfGoodsJobPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "到达工作信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "到达工作管理模块";
		private String id;
		
		public Canvas create() {
			ArrivalOfGoodsJobPanel panel = new ArrivalOfGoodsJobPanel();
			
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
		String modeName = "logisticsCustomsDeclaration.dispatchLogisticsTask.arrivalOfGoodsJob";
		String dsName = "arrivalOfGoodsJob_dataSource";

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
		final ArrivalOfGoodsJobListgrid arrivalOfGoodsJobListgrid = new ArrivalOfGoodsJobListgrid();
		arrivalOfGoodsJobListgrid.setHeight100();

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						arrivalOfGoodsJobListgrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("workStatus", "2");
						arrivalOfGoodsJobListgrid.fetchData(criteria);
						arrivalOfGoodsJobListgrid.drawArrivalOfGoodsJobListgrid();
					}
				});
		
		// 重构过滤查询条件
		arrivalOfGoodsJobListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				criteria.addCriteria("workStatus", "2");
			}
		});

		// 共用按钮面板
		final ArrivalOfGoodsJobButtonPanel arrivalOfGoodsJobButtonPanel = new ArrivalOfGoodsJobButtonPanel(arrivalOfGoodsJobListgrid);

		// 主列表面板
		SectionStackSection detailPanelSection = new SectionStackSection("到达工作信息");
		detailPanelSection.setItems(arrivalOfGoodsJobListgrid);
		detailPanelSection.setItems(arrivalOfGoodsJobButtonPanel);
		detailPanelSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(arrivalOfGoodsJobButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
