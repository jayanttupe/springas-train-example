package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockManageTool.cargoSpaceManagePolicy;

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

public class CargoSpaceManagePolicyPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "库存策略管理信息维护页面";

	private CargoSpaceManagePolicyButtonPanel cargoSpaceManagePolicyButtonPanl;
	private CargoSpaceManagePolicyListgrid cargoSpaceManagePolicyListgrid;

	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;
	private SectionStackSection detailPanelSection;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "库存策略管理模块";
		private String id;
		
		public Canvas create() {
			CargoSpaceManagePolicyPanel panel = new CargoSpaceManagePolicyPanel();
			
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
		String modeName = "stockServiceBusiness.basicData.cargoSpaceManagePolicy";
		String dsName = "cargoSpaceManagePolicy_dataSource";

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
		cargoSpaceManagePolicyListgrid = new CargoSpaceManagePolicyListgrid();
		cargoSpaceManagePolicyListgrid.setHeight100();
		
		// ListGrid中的选择事件处理
		cargoSpaceManagePolicyListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					cargoSpaceManagePolicyListgrid.selectRecords(cargoSpaceManagePolicyListgrid.getSelection(), false);
					cargoSpaceManagePolicyListgrid.selectRecord(selectedRecord);
				}else if(cargoSpaceManagePolicyListgrid.getSelection().length == 1){
					selectedRecord = cargoSpaceManagePolicyListgrid.getSelection()[0];
					cargoSpaceManagePolicyListgrid.scrollToRow(cargoSpaceManagePolicyListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						dataSource.fetchData();
						cargoSpaceManagePolicyListgrid.setDataSource(dataSource);
						cargoSpaceManagePolicyListgrid.fetchData();
						cargoSpaceManagePolicyListgrid.drawCargoSpaceManagePolicyListgrid();
					}
				});

		// 共用按钮面板
		cargoSpaceManagePolicyButtonPanl = new CargoSpaceManagePolicyButtonPanel(cargoSpaceManagePolicyListgrid);
		
		// 主列表面板
		detailPanelSection = new SectionStackSection("货位管理策略信息列表");
		detailPanelSection.setItems(cargoSpaceManagePolicyListgrid);
		detailPanelSection.setItems(cargoSpaceManagePolicyButtonPanl);
		detailPanelSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(cargoSpaceManagePolicyButtonPanl);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
