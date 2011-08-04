/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.waitCheckAndAcceptSheet;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.receivingSheet.ReceivingSheetItemsListgrid;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author Administrator
 *
 */
public class WaitCheckAndAcceptSheetPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "待检验信息管理维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "待检验管理模块";
		private String id;
		
		public Canvas create() {
			WaitCheckAndAcceptSheetPanel panel = new WaitCheckAndAcceptSheetPanel();
			
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
		// 头部列表Grid
		final WaitCheckAndAcceptSheetListgrid waitCheckAndAcceptSheetListgrid = new WaitCheckAndAcceptSheetListgrid();
		waitCheckAndAcceptSheetListgrid.setHeight("50%");
		DataSourceTool headDST = new DataSourceTool();
		String headModeName = "stockServiceBusiness.checkAndAcceptBusiness.waitCheckAndAcceptSheet";
		String headDSName = "receivingSheet_dataSource";
		headDST.onCreateDataSource(headModeName, headDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						waitCheckAndAcceptSheetListgrid.setDataSource(dataSource);
						waitCheckAndAcceptSheetListgrid.drawWaitCheckAndAcceptSheetListgrid();
						
						Criteria criteria = new Criteria();
						criteria.addCriteria("isRepair", false);
						criteria.addCriteria("isWaitCheck", "1");
						waitCheckAndAcceptSheetListgrid.fetchData(criteria);
					}
				});
		waitCheckAndAcceptSheetListgrid.setShowFilterEditor(true);
		
		// 重构过滤条件
		waitCheckAndAcceptSheetListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				criteria.addCriteria("isRepair", false);
				criteria.addCriteria("isWaitCheck", "1");
			}
		});

		// 明细列表Grid
		final ReceivingSheetItemsListgrid waitCheckAndAcceptSheetItemsListgrid = new ReceivingSheetItemsListgrid();
		waitCheckAndAcceptSheetItemsListgrid.setHeight("100%");
		waitCheckAndAcceptSheetItemsListgrid.setAutoSaveEdits(false);
		waitCheckAndAcceptSheetItemsListgrid.setAutoFetchData(false);
		DataSourceTool detailDST = new DataSourceTool();
		String detailModeName = "stockServiceBusiness.checkAndAcceptBusiness.waitCheckAndAcceptSheet";
		String detailDSName = "receivingSheetItems_dataSource";
		detailDST.onCreateDataSource(detailModeName, detailDSName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						waitCheckAndAcceptSheetItemsListgrid.setDataSource(dataSource);
						waitCheckAndAcceptSheetItemsListgrid.drawReceivingSheetItemsListgrid(false);
					}
				});
		
		// 根据选择的，取得相应的明细数据
		waitCheckAndAcceptSheetListgrid.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(RecordClickEvent event) {
				waitCheckAndAcceptSheetItemsListgrid.setData(new ListGridRecord[]{});

				Criteria criteria = new Criteria();
				Record record = waitCheckAndAcceptSheetListgrid.getSelectedRecord();
				String receivingSheetID = record.getAttribute("id");
				criteria.addCriteria("isCheck", "0");
				criteria.addCriteria("receivingSheetID", receivingSheetID);
				waitCheckAndAcceptSheetItemsListgrid.fetchData(criteria);
			}
		});

		// 主Layout
		final VLayout mainPanelLayout = new VLayout();
		mainPanelLayout.setLayoutTopMargin(5);
		mainPanelLayout.setMembersMargin(2);
		mainPanelLayout.setWidth100();
		mainPanelLayout.setHeight100();
		
		// 主Section容器
		final SectionStack mainSectionStack = new SectionStack();
		mainSectionStack.setHeight100();
		mainSectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainSectionStack.setAnimateSections(true);
		
		// 头部列表面板
		SectionStackSection headSection = new SectionStackSection("待检验航材信息");
		headSection.setItems(waitCheckAndAcceptSheetListgrid);
		headSection.setExpanded(true);

		// 明细列表面板
		HLayout detailHLayout = new HLayout();
		detailHLayout.addMember(waitCheckAndAcceptSheetItemsListgrid);
		
		VLayout detailVLayout = new VLayout();
		detailVLayout.addMember(detailHLayout);
		
		SectionStackSection detailSection = new SectionStackSection("待检验航材明细信息");
		detailSection.setItems(detailVLayout);
		detailSection.setExpanded(true);

		// 共用按钮面板
		final WaitCheckAndAcceptSheetButtonPanel waitCheckAndAcceptSheetButtonPanel =
			new WaitCheckAndAcceptSheetButtonPanel(waitCheckAndAcceptSheetListgrid, waitCheckAndAcceptSheetItemsListgrid);
		
		// 加载各面板到容器
		mainSectionStack.addSection(headSection);
		mainSectionStack.addSection(detailSection);
		mainPanelLayout.addMember(waitCheckAndAcceptSheetButtonPanel);
		mainPanelLayout.addMember(mainSectionStack);
		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}

}
