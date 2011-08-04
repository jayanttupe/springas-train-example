package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.inStockRecordManage;

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
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;

public class InStockRecordManagePanel extends ShowcasePanel {

	private static final String DESCRIPTION = "入库记录管理管理信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "入库记录管理管理模块";
		private String id;

		public Canvas create() {
			InStockRecordManagePanel panel = new InStockRecordManagePanel();

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
		final InStockRecordManageListgrid inStockRecordManageListgrid = new InStockRecordManageListgrid();
		inStockRecordManageListgrid.setHeight100();
		
		// ListGrid中的选择事件处理
		inStockRecordManageListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					inStockRecordManageListgrid.selectRecords(inStockRecordManageListgrid.getSelection(), false);
					inStockRecordManageListgrid.selectRecord(selectedRecord);
				}else if(inStockRecordManageListgrid.getSelection().length == 1){
					selectedRecord = inStockRecordManageListgrid.getSelection()[0];
					inStockRecordManageListgrid.scrollToRow(inStockRecordManageListgrid.getRecordIndex(selectedRecord));
				}
			}
		});
		
		// 重构过滤条件
		inStockRecordManageListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				criteria.addCriteria("inStockStatus", "InStock");
			}
		});

		// 获取数据源
		String modeName = "stockServiceBusiness.inStockRoomBusiness.inStockRecordManage";
		String dsName = "inStockRecord_dataSource";
		
		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						inStockRecordManageListgrid.setDataSource(dataSource);
						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						criteria.addCriteria("inStockStatus", "InStock");
						inStockRecordManageListgrid.fetchData(criteria);
						inStockRecordManageListgrid.drawInStockRecordManageListgrid();
					}
				});

		// 共用按钮面板
		InStockRecordManageButtonPanel inStockRecordManageButtonPanel = new InStockRecordManageButtonPanel();
		
		// 主列表面板
		SectionStackSection detailPanelSection = new SectionStackSection("入库记录信息");
		detailPanelSection.setItems(inStockRecordManageListgrid);
		detailPanelSection.setItems(inStockRecordManageButtonPanel);
		detailPanelSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(inStockRecordManageButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}
