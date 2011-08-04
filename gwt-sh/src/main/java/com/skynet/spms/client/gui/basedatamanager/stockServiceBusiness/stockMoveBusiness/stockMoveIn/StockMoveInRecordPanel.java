/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveIn;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;

/**
 * @author 补桓
 *
 */
public class StockMoveInRecordPanel extends ShowcasePanel{
	
    private static String moveId;
	
	private StockMoveInRecordButtonPanel stockMoveInRecordButtonPanel;
	private StockMoveInRecordListgrid stockMoveInRecordListgrid;
	private StockMoveInRecordItemListgrid stockMoveInRecordItemListgrid;
	
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "移入库管理模块";
		private String id;

		public Canvas create() {
			StockMoveInRecordPanel panel = new StockMoveInRecordPanel();

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
		stockMoveInRecordListgrid = new StockMoveInRecordListgrid();
		stockMoveInRecordListgrid.setHeight("50%");
		// 取得Grid中需要显示的数据源
		String mainmodeName = "stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveInRecord";
		String maindsName = "stockMovingInRecord_dataSource";
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(mainmodeName, maindsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {					
						stockMoveInRecordListgrid.setDataSource(dataSource);
						stockMoveInRecordListgrid.fetchData();
						stockMoveInRecordListgrid.drawStockMoveManageListgrid();
					}
				});
		
		// ListGrid中的选择事件处理
		stockMoveInRecordListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					stockMoveInRecordListgrid.selectRecords(stockMoveInRecordListgrid.getSelection(), false);
					stockMoveInRecordListgrid.selectRecord(selectedRecord);
				}else if(stockMoveInRecordListgrid.getSelection().length == 1){
					selectedRecord = stockMoveInRecordListgrid.getSelection()[0];
					stockMoveInRecordListgrid.scrollToRow(stockMoveInRecordListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		//明细Grid
		stockMoveInRecordItemListgrid = new StockMoveInRecordItemListgrid();
		stockMoveInRecordItemListgrid.setHeight("100%");
		//获取数据源
		String detailmodeName = "stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveInRecord";
		String detaildsName = "stockMovingRecordItems_dataSource";
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						
		    			stockMoveInRecordItemListgrid.setDataSource(dataSource);
						stockMoveInRecordItemListgrid.drawStockMoveItemsManageListgrid();
					}
				});

		stockMoveInRecordListgrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				moveId = stockMoveInRecordListgrid.getSelectedRecord().getAttribute("id");
				Criteria criteria = new Criteria();
				criteria.addCriteria("stockMovingRecordID",moveId);				
				stockMoveInRecordItemListgrid.filterData(criteria);
			}
		});

		// 加载各面板到容器
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(stockMoveInRecordItemListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		// 主列表面板
		SectionStackSection headSection = new SectionStackSection("航材移入记录信息");
		headSection.setItems(stockMoveInRecordListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		SectionStackSection detailSection = new SectionStackSection("航材移入记录明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);
		
		// 共用按钮面板
		stockMoveInRecordButtonPanel = new StockMoveInRecordButtonPanel(stockMoveInRecordListgrid);
		
		// 主Section容器
		final SectionStack mainPanelSection = new SectionStack();
		mainPanelSection.setHeight100();
		mainPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainPanelSection.setAnimateSections(true);

		//明细Section容器
		final SectionStack detailPanelSection = new SectionStack();
		detailPanelSection.setHeight100();
		detailPanelSection.setVisibilityMode(VisibilityMode.MULTIPLE);
		detailPanelSection.setAnimateSections(true);
		detailPanelSection.addSection(detailSection);
		
		mainPanelSection.addSection(headSection);	
		mainPanelSection.addSection(detailSection);
		mainPanelLayout.addMember(stockMoveInRecordButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;

	}
}
