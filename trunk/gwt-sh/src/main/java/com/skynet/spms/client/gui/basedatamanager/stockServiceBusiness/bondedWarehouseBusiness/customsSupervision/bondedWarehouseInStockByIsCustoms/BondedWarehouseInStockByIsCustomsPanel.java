/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.customsSupervision.bondedWarehouseInStockByIsCustoms;

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

/**
 * @author Administrator
 *
 */
public class BondedWarehouseInStockByIsCustomsPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "保税库入库信息维护页面";

	private BondedWarehouseInStockByIsCustomsButtonPanel bondedWarehouseInStockByIsCustomsButtonPanel;
	private BondedWarehouseInStockByIsCustomsListgrid bondedWarehouseInStockByIsCustomsListgrid;

	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;
	private SectionStackSection detailPanelSection;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "保税库入库模块";
		private String id;

		public Canvas create() {
			BondedWarehouseInStockByIsCustomsPanel panel = new BondedWarehouseInStockByIsCustomsPanel();

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
		String modeName = "stockServiceBusiness.bondedWarehouseBusiness.customsSupervision.bondedWarehouseInStockByIsCustoms";
		String dsName = "bondedWarehouseInStockByIsCustoms_dataSource";

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
		bondedWarehouseInStockByIsCustomsListgrid = new BondedWarehouseInStockByIsCustomsListgrid();
		bondedWarehouseInStockByIsCustomsListgrid.setHeight100();

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {

						bondedWarehouseInStockByIsCustomsListgrid.setDataSource(dataSource);
						bondedWarehouseInStockByIsCustomsListgrid.fetchData();
						bondedWarehouseInStockByIsCustomsListgrid.drawBondedWarehouseInStockByIsCustomsListgrid();
					}
				});
		
		// ListGrid中的选择事件处理
		bondedWarehouseInStockByIsCustomsListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					bondedWarehouseInStockByIsCustomsListgrid.selectRecords(bondedWarehouseInStockByIsCustomsListgrid.getSelection(), false);
					bondedWarehouseInStockByIsCustomsListgrid.selectRecord(selectedRecord);
				}else if(bondedWarehouseInStockByIsCustomsListgrid.getSelection().length == 1){
					selectedRecord = bondedWarehouseInStockByIsCustomsListgrid.getSelection()[0];
					bondedWarehouseInStockByIsCustomsListgrid.scrollToRow(bondedWarehouseInStockByIsCustomsListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 共用按钮面板
		bondedWarehouseInStockByIsCustomsButtonPanel = new BondedWarehouseInStockByIsCustomsButtonPanel(bondedWarehouseInStockByIsCustomsListgrid);

		// 主列表面板
		detailPanelSection = new SectionStackSection("保税库入库信息");

		detailPanelSection.setItems(bondedWarehouseInStockByIsCustomsListgrid);
		detailPanelSection.setItems(bondedWarehouseInStockByIsCustomsButtonPanel);
		detailPanelSection.setExpanded(true);

		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(bondedWarehouseInStockByIsCustomsButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}

	public String getIntro() {
		return DESCRIPTION;
	}

}
