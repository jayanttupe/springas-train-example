package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class CargoSpaceManagerPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "货位管理页面";

	private CargoSpaceManagerButtonPanel cargoSpaceManagerButtonPanel;
	private CargoSpaceManagerListgrid cargoSpaceManagerListgrid;

	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;
	private SectionStackSection detailPanelSection;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "货位管理模块";
		private String id;
		
		public Canvas create() {
			CargoSpaceManagerPanel panel = new CargoSpaceManagerPanel();
			
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
		String modeName = "stockServiceBusiness.basicData.cargoSpace";
		String dsName = "cargoSpace_dataSource";

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
		cargoSpaceManagerListgrid = new CargoSpaceManagerListgrid();
		cargoSpaceManagerListgrid.setHeight100();
		cargoSpaceManagerListgrid.setSelectionType(SelectionStyle.SIMPLE);
		cargoSpaceManagerListgrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		
		// ListGrid中的选择事件处理
		cargoSpaceManagerListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					cargoSpaceManagerListgrid.selectRecords(cargoSpaceManagerListgrid.getSelection(), false);
					cargoSpaceManagerListgrid.selectRecord(selectedRecord);
				}else if(cargoSpaceManagerListgrid.getSelection().length == 1){
					selectedRecord = cargoSpaceManagerListgrid.getSelection()[0];
					cargoSpaceManagerListgrid.scrollToRow(cargoSpaceManagerListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 重构过滤时的查询条件
		cargoSpaceManagerListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
			}
		});

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						cargoSpaceManagerListgrid.setDataInfo(dataInfo);
						cargoSpaceManagerListgrid.setDataSource(dataSource);
						cargoSpaceManagerListgrid.drawCargoSpaceManagerListgrid();
						cargoSpaceManagerListgrid.fetchData();
					}
				});

		// 共用按钮面板
		cargoSpaceManagerButtonPanel = new CargoSpaceManagerButtonPanel(cargoSpaceManagerListgrid);
		
		// 主列表面板
		detailPanelSection = new SectionStackSection("货位信息");
		detailPanelSection.setItems(cargoSpaceManagerListgrid);
		detailPanelSection.setItems(cargoSpaceManagerButtonPanel);
		detailPanelSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(cargoSpaceManagerButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}