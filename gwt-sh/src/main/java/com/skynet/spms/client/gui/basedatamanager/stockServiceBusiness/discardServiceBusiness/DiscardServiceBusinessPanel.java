package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.discardServiceBusiness;

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
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;

public class DiscardServiceBusinessPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "备件报废管理信息维护页面";

	private DiscardServiceBusinessButtonPanel discardServiceBusinessButtonPanel;
	private DiscardServiceBusinessListgrid discardServiceBusinessListgrid;

	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;
	private SectionStackSection detailPanelSection;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "备件报废管理模块";
		private String id;

		public Canvas create() {
			DiscardServiceBusinessPanel panel = new DiscardServiceBusinessPanel();

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
	    discardServiceBusinessListgrid = new DiscardServiceBusinessListgrid();
		discardServiceBusinessListgrid.setHeight100();
		DataSourceTool headDST = new DataSourceTool();
		String modeName = "stockServiceBusiness.partsInventory.discardServiceBusiness";
		String dsName = "discardServiceBusiness_dataSource";
		headDST.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						dataSource.fetchData();
						discardServiceBusinessListgrid.setDataSource(dataSource);
						discardServiceBusinessListgrid.fetchData();
						discardServiceBusinessListgrid.drawDiscardServiceBusinessListgrid();
					}
				});
		discardServiceBusinessListgrid.setShowFilterEditor(true);
		
		// 重构了过滤方法
		discardServiceBusinessListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
					@Override
					public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
						Criteria criteria = event.getCriteria();
						criteria.addCriteria("filter", "1");
						discardServiceBusinessListgrid.fetchData(criteria);
					}
				});

		// ListGrid中的选择事件处理
		discardServiceBusinessListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					discardServiceBusinessListgrid.selectRecords(discardServiceBusinessListgrid.getSelection(), false);
					discardServiceBusinessListgrid.selectRecord(selectedRecord);
				}else if(discardServiceBusinessListgrid.getSelection().length == 1){
					selectedRecord = discardServiceBusinessListgrid.getSelection()[0];
					discardServiceBusinessListgrid.scrollToRow(discardServiceBusinessListgrid.getRecordIndex(selectedRecord));
				}
			}
		});
		
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

		// 共用按钮面板
		discardServiceBusinessButtonPanel = new DiscardServiceBusinessButtonPanel(discardServiceBusinessListgrid);

		// 主列表面板
		detailPanelSection = new SectionStackSection("航材报废信息");

		detailPanelSection.setItems(discardServiceBusinessListgrid);
		detailPanelSection.setItems(discardServiceBusinessButtonPanel);
		detailPanelSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(discardServiceBusinessButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}
