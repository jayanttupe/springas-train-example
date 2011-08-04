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

public class InvoiceApplyPanel extends ShowcasePanel {
	
	
	private static final String DESCRIPTION = "开票申请管理信息维护页面";
	private InvoiceApplyButtonToolBar invoiceApplyToolBar;
	private InvoiceApplyList invoiceApplyList;
	private VLayout mainPanelLayout;
	private DataInfo dataInfomation;
	

	
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "开票申请管理模块";
		private String id;
		@Override
		public Canvas create() {
			InvoiceApplyPanel panel = new InvoiceApplyPanel();
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
		String modName="account.applyManager.invoiceApplyManager";
		String dsName="finance_invoiceApply_dataSource";

		
		mainPanelLayout = new VLayout();
		
		
		SectionStack mainStack = new SectionStack();

		mainStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainStack.setAnimateSections(true);
		
		invoiceApplyList = new InvoiceApplyList();
		
		
		DataSourceTool dataSourceTool = new DataSourceTool();

		dataSourceTool.onCreateDataSource(modName, dsName,
				new PostDataSourceInit() {

					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						// TODO Auto-generated method stub
						invoiceApplyList.setHeight(200);
						invoiceApplyList.setShowFilterEditor(true);
						invoiceApplyList.setFilterOnKeypress(true);
						invoiceApplyList.setDataSource(dataSource);
						invoiceApplyList.setInvoiceApplyDataInfo(dataInfo);
						dataInfomation= dataInfo;
						invoiceApplyList.fetchData();
						invoiceApplyList.drawInvoiceApplyList();

					}

				});

		final SectionStackSection invoiceApplyMainSection = new SectionStackSection("开票申请管理");
		invoiceApplyToolBar = new InvoiceApplyButtonToolBar(invoiceApplyList);
		invoiceApplyMainSection.setItems(invoiceApplyToolBar, invoiceApplyList);
		invoiceApplyMainSection.setExpanded(true);
		
		
		mainStack.addSection(invoiceApplyMainSection);
	
		mainPanelLayout.addMember(mainStack);
		return mainPanelLayout;
	}

}
