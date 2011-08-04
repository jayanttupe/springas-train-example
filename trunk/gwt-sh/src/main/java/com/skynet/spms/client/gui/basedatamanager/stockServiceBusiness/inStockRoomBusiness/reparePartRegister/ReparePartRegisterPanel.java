package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.inStockRoomBusiness.reparePartRegister;

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

public class ReparePartRegisterPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "送修件登记页面";

	private ReparePartRegisterButtonPanel reparePartRegisterButtonPanel;
	private ReparePartRegisterListgrid reparePartRegisterListgrid;

	private VLayout mainPanelLayout;
	private SectionStack mainPanelSection;
	private SectionStackSection detailPanelSection;

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "送修件登记管理模块";
		private String id;

		public Canvas create() {
			ReparePartRegisterPanel panel = new ReparePartRegisterPanel();
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

		// 头部列表Grid
		reparePartRegisterListgrid = new ReparePartRegisterListgrid();
		
		// ListGrid中的选择事件处理
		reparePartRegisterListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					reparePartRegisterListgrid.selectRecords(reparePartRegisterListgrid.getSelection(), false);
					reparePartRegisterListgrid.selectRecord(selectedRecord);
				}else if(reparePartRegisterListgrid.getSelection().length == 1){
					selectedRecord = reparePartRegisterListgrid.getSelection()[0];
					reparePartRegisterListgrid.scrollToRow(reparePartRegisterListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		DataSourceTool headDST = new DataSourceTool();
		String modeName = "stockServiceBusiness.inStockRoomBusiness.reparePartRegister";
		String dsName = "reparePartRegister_dataSource";
		headDST.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						reparePartRegisterListgrid.setDataSource(dataSource);
						reparePartRegisterListgrid.fetchData();
						reparePartRegisterListgrid.drawReparePartRegisterListgrid();
					}
				});
		
		reparePartRegisterListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				reparePartRegisterListgrid.fetchData(criteria);
			}
		});
		
		reparePartRegisterListgrid.setShowFilterEditor(true);
		
		// 共用按钮面板
		reparePartRegisterButtonPanel = new ReparePartRegisterButtonPanel(reparePartRegisterListgrid);
		
		// 主列表面板
		detailPanelSection = new SectionStackSection("送修件登记信息");
		detailPanelSection.setItems(reparePartRegisterListgrid);
		detailPanelSection.setItems(reparePartRegisterButtonPanel);
		detailPanelSection.setExpanded(true);
		
		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(reparePartRegisterButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}
	
	public String getIntro() {
		return DESCRIPTION;
	}
}
