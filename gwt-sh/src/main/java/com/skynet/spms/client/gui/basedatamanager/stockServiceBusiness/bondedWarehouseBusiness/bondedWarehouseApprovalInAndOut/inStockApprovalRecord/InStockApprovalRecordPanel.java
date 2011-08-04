package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.inStockApprovalRecord;

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

public class InStockApprovalRecordPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "保税库入库记录管理管理信息维护页面";

	private InStockApprovalRecordButtonPanel inStockApprovalRecordButtonPanel;
	private InStockApprovalRecordListgrid inStockApprovalRecordListgrid;

	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;
	private SectionStackSection detailPanelSection;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "保税库入库记录管理管理模块";
		private String id;

		public Canvas create() {
			InStockApprovalRecordPanel panel = new InStockApprovalRecordPanel();

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
		String modeName = "stockServiceBusiness.bondedWarehouseBusiness.bondedWarehouseApprovalInAndOut.inStockApprovalRecord";
		String dsName = "inStockApprovalRecord_dataSource";

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
		inStockApprovalRecordListgrid = new InStockApprovalRecordListgrid();
		inStockApprovalRecordListgrid.setHeight100();

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						dataSource.fetchData();
						inStockApprovalRecordListgrid.setDataSource(dataSource);
						inStockApprovalRecordListgrid.fetchData();
						inStockApprovalRecordListgrid.drawInStockApprovalRecordListgrid();
					}
				});
		
		// ListGrid中的选择事件处理
		inStockApprovalRecordListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					inStockApprovalRecordListgrid.selectRecords(inStockApprovalRecordListgrid.getSelection(), false);
					inStockApprovalRecordListgrid.selectRecord(selectedRecord);
				}else if(inStockApprovalRecordListgrid.getSelection().length == 1){
					selectedRecord = inStockApprovalRecordListgrid.getSelection()[0];
					inStockApprovalRecordListgrid.scrollToRow(inStockApprovalRecordListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 共用按钮面板
		inStockApprovalRecordButtonPanel = new InStockApprovalRecordButtonPanel(inStockApprovalRecordListgrid);

		// 主列表面板
		detailPanelSection = new SectionStackSection("保税库入库记录管理信息");

		detailPanelSection.setItems(inStockApprovalRecordListgrid);
		detailPanelSection.setItems(inStockApprovalRecordButtonPanel);
		detailPanelSection.setExpanded(true);

		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(inStockApprovalRecordButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}
