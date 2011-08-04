package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.repairCodeBusiness;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.basedatamanager.organization.enterpriseInfo.ShippingAddressPanel;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
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
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class RepairCodePanel extends ShowcasePanel {

	private static final String DESCRIPTION = "补码信息维护页面";

	private RepairCodeListgrid repairCodeListgrid;
	private RepairCodeButtonPanel repairCodeButtonPanel;
	
	private VLayout mainPanelLayout;
	private SectionStack mainSectionStack;
	private SectionStackSection mainPanelSection;
	
	private CargoItemListgrid cargoItemListgrid;
	private PartItemListgrid partItemListgrid;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "补码管理模块";
		private String id;
		
		public Canvas create() {
			RepairCodePanel panel = new RepairCodePanel();
			
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
		String modeName = "stockServiceBusiness.basicData.repairCode";
		String dsName = "repairCode_dataSource";
		String csDsName = "repairCodeCargoSpaceItem_dataSource";
		String partDsName = "repairCodePartItem_dataSource";

		// 主列表Grid
		repairCodeListgrid = new RepairCodeListgrid();
		repairCodeListgrid.setHeight100();
		
		// 共用按钮面板
		repairCodeButtonPanel = new RepairCodeButtonPanel(repairCodeListgrid);
		
		//货位明细列表Grid
		cargoItemListgrid = new CargoItemListgrid();
		//航材明细列表Grid
		partItemListgrid = new PartItemListgrid();
		
		// 主Layout容器
		mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();
		
		// 主SectionStack
		mainSectionStack = new SectionStack();
		mainSectionStack.setHeight("50%");
		mainSectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainSectionStack.setAnimateSections(true);
		mainSectionStack.setShowResizeBar(true);
		
		// 主列表Section
		mainPanelSection = new SectionStackSection(ConstantsUtil.stockConstants.RepairCodeInfo());
		mainPanelSection.setItems(repairCodeListgrid);
		mainPanelSection.setExpanded(true);
		
		//将主列表Section添加到主SectionStack
		mainSectionStack.addSection(mainPanelSection);
		
		final TabSet tabSet = new TabSet();
		//补码航材明细列表选项卡
		final Tab partTab = new Tab(ConstantsUtil.stockConstants.partRepairItem(),"pieces/16/star_green.png");
		partTab.setPane(partItemListgrid);
		tabSet.addTab(partTab);
		
		//补码货位明细列表选项卡
		final Tab cargoTab = new Tab(ConstantsUtil.stockConstants.cargoSpaceRepairItem(),"pieces/16/star_green.png");
		cargoTab.setPane(cargoItemListgrid);
		tabSet.addTab(cargoTab);

		// 加载各组件到容器
		mainPanelLayout.addMember(repairCodeButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		mainPanelLayout.addMember(tabSet);
		
		
		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource,DataInfo dataInfo) {
				repairCodeListgrid.setDataInfo(dataInfo);
				repairCodeListgrid.setDataSource(dataSource);
				repairCodeListgrid.fetchData();
				repairCodeListgrid.drawRepairCodeListgrid();
			}
		});
		
		dataSourceTool.onCreateDataSource(modeName, csDsName,new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource,DataInfo dataInfo) {
				cargoItemListgrid.setDataSource(dataSource);
				cargoItemListgrid.drawCargoItemListgrid();
			}
		});
		
		dataSourceTool.onCreateDataSource(modeName, partDsName,new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource,DataInfo dataInfo) {
				partItemListgrid.setDataSource(dataSource);
				partItemListgrid.drawPartItemListgrid();
			}
		});
		
		// ListGrid中的选择事件处理
		repairCodeListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				repairCodeListgrid.fetchData(criteria);
			}
		});
		
		repairCodeListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					repairCodeListgrid.selectRecords(repairCodeListgrid.getSelection(), false);
					repairCodeListgrid.selectRecord(selectedRecord);
				}
				if(repairCodeListgrid.getSelection().length == 1){
					String repairCodeType = selectedRecord.getAttribute("repairCodeType");
					String repairCodeId = selectedRecord.getAttribute("id");
					Criteria criteria = new Criteria("repairCodeId",repairCodeId);
					if(RepairCodeType.SPARE_CODE.equals(repairCodeType)){
						partTab.setDisabled(false);
						tabSet.selectTab(partTab);
						cargoTab.setDisabled(true);
						partItemListgrid.fetchData(criteria);
						cargoItemListgrid.setData(new Record[0]);
					}else if(RepairCodeType.CARGE.equals(repairCodeType)){
						cargoTab.setDisabled(false);
						tabSet.selectTab(cargoTab);
						partTab.setDisabled(true);
						cargoItemListgrid.fetchData(criteria);
						partItemListgrid.setData(new Record[0]);
					}
				}	
			}
		});

		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
