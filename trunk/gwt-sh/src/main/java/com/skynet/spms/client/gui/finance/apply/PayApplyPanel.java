package com.skynet.spms.client.gui.finance.apply;

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

public class PayApplyPanel extends ShowcasePanel {

	
	private static final String DESCRIPTION = "付款申请管理信息维护页面";
	private PayApplyButtonToolBar payApplyToolBar;
	private PayApplyList payApplyList;
	private VLayout mainPanelLayout;
	private DataInfo dataInfomation;
	
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "付款申请管理模块";
		private String id;
		@Override
		public Canvas create() {
			PayApplyPanel panel = new PayApplyPanel();
			id = panel.getID();
			return panel;
		}

		@Override
		public String getID() {
			// TODO Auto-generated method stub
			return id;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return DESCRIPTION;
		}
		
		
	}
	@Override
	public Canvas getViewPanel() {
		// TODO Auto-generated method stub
		String modName="account.applyManager.payApplyManager";
		String dsName="finance_payApply_dataSource";

		
		mainPanelLayout = new VLayout();
		
		
		SectionStack mainStack = new SectionStack();

		mainStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainStack.setAnimateSections(true);
		
		payApplyList = new PayApplyList();
		
		
		DataSourceTool dataSourceTool = new DataSourceTool();

		dataSourceTool.onCreateDataSource(modName, dsName,
				new PostDataSourceInit() {

					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						// TODO Auto-generated method stub
						payApplyList.setHeight(200);
						payApplyList.setAutoFetchData(true);
						payApplyList.setShowFilterEditor(true);			
						payApplyList.setDataSource(dataSource);
						payApplyList.setPayApplyDataInfo(dataInfo);
						dataInfomation= dataInfo;
						payApplyList.fetchData();
						payApplyList.drawPayApplyList();

					}

				});

		final SectionStackSection payApplyMainSection = new SectionStackSection("付款申请管理");
		//payApplyList.drawPayApplyList();
		payApplyToolBar = new PayApplyButtonToolBar(payApplyList);
		payApplyMainSection.setItems(payApplyToolBar, payApplyList);
		payApplyMainSection.setExpanded(true);
		
		
		mainStack.addSection(payApplyMainSection);
	
		mainPanelLayout.addMember(mainStack);
		return mainPanelLayout;
	}

}
