/**
 * 
 */
package com.skynet.spms.client.gui.basedatamanager.stockServiceBusiness.checkAndAcceptBusiness.credentialsRecord;

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

/**
 * @author Administrator
 *
 */
public class CredentialsRecordPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "航材证书管理维护页面";

	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "航材证书管理模块";
		private String id;

		public Canvas create() {
			CredentialsRecordPanel panel = new CredentialsRecordPanel();

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
		String modeName = "stockServiceBusiness.checkAndAcceptBusiness.credentialsRecord";
		String dsName = "credentialsRecord_dataSource";

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
		final CredentialsRecordListgrid credentialsRecordListgrid = new CredentialsRecordListgrid();
		credentialsRecordListgrid.setHeight100();
		
		// ListGrid中的选择事件处理
		credentialsRecordListgrid.addCellClickHandler(new CellClickHandler() {
			@Override
			public void onCellClick(CellClickEvent event) {
				ListGridRecord selectedRecord = event.getRecord();
				if(event.getColNum()!=0){
					credentialsRecordListgrid.selectRecords(credentialsRecordListgrid.getSelection(), false);
					credentialsRecordListgrid.selectRecord(selectedRecord);
				}else if(credentialsRecordListgrid.getSelection().length == 1){
					selectedRecord = credentialsRecordListgrid.getSelection()[0];
					credentialsRecordListgrid.scrollToRow(credentialsRecordListgrid.getRecordIndex(selectedRecord));
				}
			}
		});

		// 取得Grid中需要显示的数据源
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modeName, dsName,
				new PostDataSourceInit() {
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						credentialsRecordListgrid.setDataSource(dataSource);
						credentialsRecordListgrid.fetchData();
						credentialsRecordListgrid.drawCredentialsRecordListgrid();
					}
				});

		// 重构过滤条件
		credentialsRecordListgrid.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				Criteria criteria = event.getCriteria();
				criteria.addCriteria("filter", "1");
				credentialsRecordListgrid.fetchData(criteria);
			}
		});
		
		// 共用按钮面板
		CredentialsRecordButtonPanel credentialsRecordButtonPanel = new CredentialsRecordButtonPanel(credentialsRecordListgrid);

		// 主列表面板
		SectionStackSection detailPanelSection = new SectionStackSection("航材证书信息");

		detailPanelSection.setItems(credentialsRecordListgrid);
		detailPanelSection.setItems(credentialsRecordButtonPanel);
		detailPanelSection.setExpanded(true);

		// 加载各面板到容器
		mainPanelSection.addSection(detailPanelSection);
		mainPanelLayout.addMember(credentialsRecordButtonPanel);
		mainPanelLayout.addMember(mainPanelSection);

		return mainPanelLayout;
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}
