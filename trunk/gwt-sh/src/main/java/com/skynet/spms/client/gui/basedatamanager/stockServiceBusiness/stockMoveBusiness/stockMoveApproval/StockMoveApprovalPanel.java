/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.stockMoveBusiness.stockMoveApproval;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
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
public class StockMoveApprovalPanel extends ShowcasePanel{
	
    private static String moveId;
	
	private StockMoveApprovalButtonPanel stockMoveOutRecordButtonPanel;
	private StockMoveApprovalListgrid stockMoveOutRecordListgrid;
	private StockMoveApprovalItemListgrid stockMoveOutRecordItemListgrid;
	
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "移库审批管理模块";
		private String id;

		public Canvas create() {
			StockMoveApprovalPanel panel = new StockMoveApprovalPanel();

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
		stockMoveOutRecordListgrid = new StockMoveApprovalListgrid();
		stockMoveOutRecordListgrid.setHeight("50%");
		// 取得Grid中需要显示的数据源
		String mainmodeName = "stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveApproval";
		String maindsName = "stockMovingApproval_dataSource";
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(mainmodeName, maindsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						stockMoveOutRecordListgrid.setDataSource(dataSource);
						stockMoveOutRecordListgrid.fetchData();
						stockMoveOutRecordListgrid.drawStockMoveManageListgrid();
					}
				});
		
		// ListGrid中的选择事件处理
		stockMoveOutRecordListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					stockMoveOutRecordListgrid.selectRecords(stockMoveOutRecordListgrid.getSelection(), false);
					stockMoveOutRecordListgrid.selectRecord(selectedRecord);
				}else if(stockMoveOutRecordListgrid.getSelection().length == 1){
					selectedRecord = stockMoveOutRecordListgrid.getSelection()[0];
					stockMoveOutRecordListgrid.scrollToRow(stockMoveOutRecordListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		//明细Grid
		stockMoveOutRecordItemListgrid = new StockMoveApprovalItemListgrid();
		stockMoveOutRecordItemListgrid.setHeight("100%");
		//获取数据源
		String detailmodeName = "stockServiceBusiness.partsInventory.stockMoveBusiness.stockMoveOutRecord";
		String detaildsName = "stockMovingRecordItems_dataSource";
		DataSourceTool detaildataSourceTool = new DataSourceTool();
		detaildataSourceTool.onCreateDataSource(detailmodeName, detaildsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						
		    			stockMoveOutRecordItemListgrid.setDataSource(dataSource);
						stockMoveOutRecordItemListgrid.drawStockMoveItemsManageListgrid();
					}
				});
		     stockMoveOutRecordListgrid.addRecordClickHandler(new RecordClickHandler() {		
			@Override
		     	public void onRecordClick(RecordClickEvent event) {
				stockMoveOutRecordItemListgrid.setData(new ListGridRecord[]{});
				stockMoveOutRecordItemListgrid.fetchData(new Criteria("stockMovingRecordID",stockMoveOutRecordListgrid.getSelectedRecord().getAttribute("id").toString()));
			}
		});
		// 共用按钮面板
		stockMoveOutRecordButtonPanel = new StockMoveApprovalButtonPanel(stockMoveOutRecordListgrid,stockMoveOutRecordItemListgrid);
				
		// 加载各面板到容器
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(stockMoveOutRecordItemListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		// 主列表面板
		SectionStackSection headSection = new SectionStackSection("移库审批记录信息");
		headSection.setItems(stockMoveOutRecordListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		SectionStackSection detailSection = new SectionStackSection("移库审批记录明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);
		
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
		mainPanelLayout.addMember(stockMoveOutRecordButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
}
