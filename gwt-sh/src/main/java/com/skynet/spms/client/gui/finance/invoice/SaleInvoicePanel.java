package com.skynet.spms.client.gui.finance.invoice;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.finance.apply.InvoiceApplyButtonToolBar;
import com.skynet.spms.client.gui.finance.apply.InvoiceApplyList;
import com.skynet.spms.client.gui.finance.apply.InvoiceApplyPanel;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class SaleInvoicePanel extends ShowcasePanel {
	
	
	private static final String DESCRIPTION = "收款发票管理信息维护页面";
	private VLayout mainPanelLayout;
	private DataInfo dataInfomation;
	private SaleInvoiceButtonToolBar saleInvoiceToolBar;
	private SaleInvoiceList saleInvoiceApplyList;

	

	
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "收款发票管理信息维护页面";
		private String id;
		@Override
		public Canvas create() {
			SaleInvoicePanel panel = new SaleInvoicePanel();
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
		String modName="account.invoiceManager.collectionInvoiceManager";
		String dsName="finance_saleInvoice_dataSource";

		
		mainPanelLayout = new VLayout();
		
		
		SectionStack mainStack = new SectionStack();

		mainStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainStack.setAnimateSections(true);
		
		saleInvoiceApplyList = new SaleInvoiceList();
		
		
		DataSourceTool dataSourceTool = new DataSourceTool();

		dataSourceTool.onCreateDataSource(modName, dsName,
				new PostDataSourceInit() {

					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						// TODO Auto-generated method stub
						saleInvoiceApplyList.setHeight(200);
						saleInvoiceApplyList.setShowFilterEditor(true);
						saleInvoiceApplyList.setFilterOnKeypress(true);
						saleInvoiceApplyList.setDataSource(dataSource);
						saleInvoiceApplyList.setInvoiceApplyDataInfo(dataInfo);
						dataInfomation= dataInfo;
						saleInvoiceApplyList.fetchData();
						saleInvoiceApplyList.drawSaleInvoiceList();

					}

				});

		final SectionStackSection invoiceApplyMainSection = new SectionStackSection("收款发票管理");
		saleInvoiceToolBar = new SaleInvoiceButtonToolBar(saleInvoiceApplyList);
		invoiceApplyMainSection.setItems(saleInvoiceToolBar, saleInvoiceApplyList);
		invoiceApplyMainSection.setExpanded(true);
		
		
		mainStack.addSection(invoiceApplyMainSection);
	
		mainPanelLayout.addMember(mainStack);
		return mainPanelLayout;
	}

}
