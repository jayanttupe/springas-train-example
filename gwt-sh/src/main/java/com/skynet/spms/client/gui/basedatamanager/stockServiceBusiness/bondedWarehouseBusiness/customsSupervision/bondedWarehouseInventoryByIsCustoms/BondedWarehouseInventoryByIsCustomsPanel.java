/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.bondedWarehouseBusiness.customsSupervision.bondedWarehouseInventoryByIsCustoms;

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
public class BondedWarehouseInventoryByIsCustomsPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "保税库在库信息维护页面";

	private BondedWarehouseInventoryByIsCustomsButtonPanel bondedWarehouseInventoryByIsCustomsButtonPanel;
	private BondedWarehouseInventoryByIsCustomsListgrid bondedWarehouseInventoryByIsCustomsListgrid;
	
	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;
	private SectionStackSection detailPanelSection;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "保税库在库模块";
		private String id;

		public Canvas create() {
			BondedWarehouseInventoryByIsCustomsPanel panel = new BondedWarehouseInventoryByIsCustomsPanel();

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
		String modeName = "stockServiceBusiness.bondedWarehouseBusiness.customsSupervision.bondedWarehouseInventoryStockByIsCustoms";
		String dsName = "bondedWarehouseInventoryStockByIsCustoms_dataSource";

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
		bondedWarehouseInventoryByIsCustomsListgrid = new BondedWarehouseInventoryByIsCustomsListgrid();
		bondedWarehouseInventoryByIsCustomsListgrid.setHeight100();

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						bondedWarehouseInventoryByIsCustomsListgrid.setDataSource(dataSource);
						bondedWarehouseInventoryByIsCustomsListgrid.fetchData();
						bondedWarehouseInventoryByIsCustomsListgrid.drawBondedWarehouseInventoryByIsCustomsListgrid();
					}
				});
		
		// ListGrid中的选择事件处理
		bondedWarehouseInventoryByIsCustomsListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					bondedWarehouseInventoryByIsCustomsListgrid.selectRecords(bondedWarehouseInventoryByIsCustomsListgrid.getSelection(), false);
					bondedWarehouseInventoryByIsCustomsListgrid.selectRecord(selectedRecord);
				}else if(bondedWarehouseInventoryByIsCustomsListgrid.getSelection().length == 1){
					selectedRecord = bondedWarehouseInventoryByIsCustomsListgrid.getSelection()[0];
					bondedWarehouseInventoryByIsCustomsListgrid.scrollToRow(bondedWarehouseInventoryByIsCustomsListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 共用按钮面板
		bondedWarehouseInventoryByIsCustomsButtonPanel = new BondedWarehouseInventoryByIsCustomsButtonPanel(bondedWarehouseInventoryByIsCustomsListgrid);

		// 主列表面板
		detailPanelSection = new SectionStackSection("保税库在库信息");

		detailPanelSection.setItems(bondedWarehouseInventoryByIsCustomsListgrid);
		detailPanelSection.setItems(bondedWarehouseInventoryByIsCustomsButtonPanel);
		detailPanelSection.setExpanded(true);

		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(bondedWarehouseInventoryByIsCustomsButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}

	public String getIntro() {
		return DESCRIPTION;
	}

}
