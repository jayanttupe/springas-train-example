package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.partsInventory.freezeRecord;

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

public class FreezeRecordPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "库存备件冻结管理信息维护页面";

	private FreezeRecordButtonPanel freezeRecordButtonPanl;
	private FreezeRecordListgrid freezeRecordListgrid;

	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;
	private SectionStackSection detailPanelSection;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "库存备件冻结管理模块";
		private String id;
		
		public Canvas create() {
			FreezeRecordPanel panel = new FreezeRecordPanel();			
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
		String modeName = "stockServiceBusiness.partsInventory.freezeRecord";
		String dsName = "freezeRecord_dataSource";

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
		freezeRecordListgrid = new FreezeRecordListgrid();
		freezeRecordListgrid.setHeight100();
		
		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						freezeRecordListgrid.setDataSource(dataSource);
						freezeRecordListgrid.fetchData();
						freezeRecordListgrid.drawFreezeRecordListgrid();
					}
				});

		// 共用按钮面板
		freezeRecordButtonPanl = new FreezeRecordButtonPanel(freezeRecordListgrid);
		
		// 主列表面板
		detailPanelSection = new SectionStackSection("库存备件冻结信息");
		detailPanelSection.setItems(freezeRecordListgrid);		
		detailPanelSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(freezeRecordButtonPanl);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
