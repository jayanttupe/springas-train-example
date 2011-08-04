package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet;

import com.google.gwt.user.client.Window;
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

public class CheckAndAcceptSheetPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "航材检验单维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "航材检验单模块";
		private String id;
		
		public Canvas create() {
			CheckAndAcceptSheetPanel panel = new CheckAndAcceptSheetPanel();
			
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
		String modeName = "stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet";
		String dsName = "checkAndAcceptSheet_dataSource";

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
		final CheckAndAcceptSheetListgrid checkAndAcceptSheetListgrid = new CheckAndAcceptSheetListgrid();
		checkAndAcceptSheetListgrid.setHeight100();
		checkAndAcceptSheetListgrid.setAutoFetchData(true);

		// 重构过滤条件
		checkAndAcceptSheetListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria cirteria = event.getCriteria();
				cirteria.addCriteria("filter", "1");
				checkAndAcceptSheetListgrid.fetchData(cirteria);
			}
		});
		
		// ListGrid中的选择事件处理
		checkAndAcceptSheetListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					checkAndAcceptSheetListgrid.selectRecords(checkAndAcceptSheetListgrid.getSelection(), false);
					checkAndAcceptSheetListgrid.selectRecord(selectedRecord);
				}else if(checkAndAcceptSheetListgrid.getSelection().length == 1){
					selectedRecord = checkAndAcceptSheetListgrid.getSelection()[0];
					checkAndAcceptSheetListgrid.scrollToRow(checkAndAcceptSheetListgrid.getRecordIndex(selectedRecord));
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
						checkAndAcceptSheetListgrid.setDataSource(dataSource);
						checkAndAcceptSheetListgrid.fetchData();
						checkAndAcceptSheetListgrid.drawCheckAndAcceptSheetManagerListgrid();
					}
				});

		// 共用按钮面板
		CheckAndAcceptSheetButtonPanel checkAndAcceptSheetButtonPanel = new CheckAndAcceptSheetButtonPanel(checkAndAcceptSheetListgrid);
		
		// 主列表面板
		SectionStackSection detailPanelSection = new SectionStackSection("航材检验单信息");
		detailPanelSection.setItems(checkAndAcceptSheetListgrid);
		detailPanelSection.setItems(checkAndAcceptSheetButtonPanel);
		detailPanelSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(checkAndAcceptSheetButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
