package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.inStockRecord;

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

public class InStockRecordPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "合格待入库记录管理信息维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "合格待入库记录管理管理模块";
		private String id;

		public Canvas create() {
			InStockRecordPanel panel = new InStockRecordPanel();

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
		final InStockRecordListgrid inStockRecordListgrid = new InStockRecordListgrid();
		inStockRecordListgrid.setHeight100();
		
		// ListGrid中的选择事件处理
		inStockRecordListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					inStockRecordListgrid.selectRecords(inStockRecordListgrid.getSelection(), false);
					inStockRecordListgrid.selectRecord(selectedRecord);
				}else if(inStockRecordListgrid.getSelection().length == 1){
					selectedRecord = inStockRecordListgrid.getSelection()[0];
					inStockRecordListgrid.scrollToRow(inStockRecordListgrid.getRecordIndex(selectedRecord));
				}
			}
		});
		
		// 重构过滤条件
		inStockRecordListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				criteria.addCriteria("shelvingStatus", "NotSend");
			}
		});

		// 获取数据源
		String modeName = "stockServiceBusiness.inStockRoomBusiness.inStockRecord";
		String dsName = "inStockRecord_dataSource";
		
		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						inStockRecordListgrid.setDataSource(dataSource);
						inStockRecordListgrid.drawInStockRecordListgrid();
						
						Criteria criteria = new Criteria();
						criteria.addCriteria("temp", String.valueOf(Math.random()));
						criteria.addCriteria("shelvingStatus", "NotSend");
						inStockRecordListgrid.fetchData(criteria);
					}
				});

		// 共用按钮面板
		InStockRecordButtonPanel inStockRecordButtonPanel = new InStockRecordButtonPanel(inStockRecordListgrid);
		
		// 主列表面板
		SectionStackSection detailPanelSection = new SectionStackSection("合格待入库信息");
		detailPanelSection.setItems(inStockRecordListgrid);
		detailPanelSection.setItems(inStockRecordButtonPanel);
		detailPanelSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(inStockRecordButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}
