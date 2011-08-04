/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveOrderManage;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
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
public class StockMoveOrderManagePanel extends ShowcasePanel{
	
    private static String taskId;
	
	private StockMoveOrderManageButtonPanel stockMoveOrderManageButtonPanel;
	private StockMoveOrderManageListgrid stockMoveOrderManageListgrid;
	private StockMoveOrderManageItemListgrid stockMoveOrderManageItemListgrid;
	
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "移库管理模块";
		private String id;

		public Canvas create() {
			StockMoveOrderManagePanel panel = new StockMoveOrderManagePanel();

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
		final VLayout mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();
		
		// 主列表Grid
		stockMoveOrderManageListgrid = new StockMoveOrderManageListgrid();
		stockMoveOrderManageListgrid.setHeight("50%");
		// 取得Grid中需要显示的数据源
		String mainmodeName = "stockServiceBusiness.stockMoveBusiness.stockMoveOrderManage";
		String maindsName = "stockMovingOrder_dataSource";
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(mainmodeName, maindsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						
						stockMoveOrderManageListgrid.setDataSource(dataSource);
						stockMoveOrderManageListgrid.fetchData();
						stockMoveOrderManageListgrid.drawStockMoveOrderManageListgrid();
					}
				});
		
		// ListGrid中的选择事件处理
		stockMoveOrderManageListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					stockMoveOrderManageListgrid.selectRecords(stockMoveOrderManageListgrid.getSelection(), false);
					stockMoveOrderManageListgrid.selectRecord(selectedRecord);
				}else if(stockMoveOrderManageListgrid.getSelection().length == 1){
					selectedRecord = stockMoveOrderManageListgrid.getSelection()[0];
					stockMoveOrderManageListgrid.scrollToRow(stockMoveOrderManageListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		//明细Grid
		stockMoveOrderManageItemListgrid = new StockMoveOrderManageItemListgrid();
		stockMoveOrderManageItemListgrid.setHeight("100%");
		//获取数据源
		String detailmodeName = "stockServiceBusiness.stockMoveBusiness.stockMoveOrderManage";
		String detaildsName = "stockMovingOrderItem_dataSource";                             
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						
						stockMoveOrderManageItemListgrid.setDataSource(dataSource);
						stockMoveOrderManageItemListgrid.fetchData();
						stockMoveOrderManageItemListgrid.drawStockMoveOrderItemsManageListgrid();
					}
				});
		
		// 共用按钮面板
		stockMoveOrderManageButtonPanel = new StockMoveOrderManageButtonPanel(stockMoveOrderManageListgrid);
		
		stockMoveOrderManageListgrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				taskId = stockMoveOrderManageListgrid.getSelectedRecord().getAttribute("taskNo");
				Criteria criteria = new Criteria();
				criteria.addCriteria("taskNo",taskId);
				stockMoveOrderManageItemListgrid.filterData(criteria);
			}
		});

		// 加载各面板到容器
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(stockMoveOrderManageItemListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		// 主列表面板
		SectionStackSection headSection = new SectionStackSection("移库管理信息列表");
		headSection.setItems(stockMoveOrderManageListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		SectionStackSection detailSection = new SectionStackSection("明细信息列表"); 
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);
		
		// 共用按钮面板
		stockMoveOrderManageButtonPanel = new StockMoveOrderManageButtonPanel(stockMoveOrderManageListgrid);
		
		// 主Section容器
		final SectionStack mainPanelSection = new SectionStack();
		mainPanelSection.setHeight(500);
		mainPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainPanelSection.setAnimateSections(true);

		//明细Section容器
		final SectionStack detailPanelSection = new SectionStack();
		detailPanelSection.setHeight(230);
		detailPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		detailPanelSection.setAnimateSections(true);
		detailPanelSection.addSection(detailSection);
		
		mainPanelSection.addSection(headSection);	
		mainPanelSection.addSection(detailSection);
		mainPanelLayout.addMember(stockMoveOrderManageButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;

	}

}
