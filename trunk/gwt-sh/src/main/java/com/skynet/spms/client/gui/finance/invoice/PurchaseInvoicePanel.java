package com.skynet.spms.client.gui.finance.invoice;

import com.google.gwt.core.client.JavaScriptObject;
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

public class PurchaseInvoicePanel extends ShowcasePanel {
	
	private static final String DESCRIPTION = "付款发票管理信息维护页面";
	private VLayout mainPanelLayout;
	private DataInfo dataInfomation;
	private PurchaseInvoiceButtonToolBar purchaseInvoiceToolBar;
	private PurchaseInvoiceList purchaseInvoiceApplyList;
	
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "付款发票管理信息维护页面";
		private String id;
		@Override
		public Canvas create() {
			PurchaseInvoicePanel panel = new PurchaseInvoicePanel();
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
		String modName="account.invoiceManager.payInvoiceManager";
		String dsName="finance_purchaseInvoice_dataSource";

		
		mainPanelLayout = new VLayout();
		
		
		SectionStack mainStack = new SectionStack();

		mainStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainStack.setAnimateSections(true);
		
		purchaseInvoiceApplyList = new PurchaseInvoiceList();
		
		
		DataSourceTool dataSourceTool = new DataSourceTool();

		dataSourceTool.onCreateDataSource(modName, dsName,
				new PostDataSourceInit() {

					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						// TODO Auto-generated method stub
						purchaseInvoiceApplyList.setHeight(200);
						purchaseInvoiceApplyList.setShowFilterEditor(true);
						purchaseInvoiceApplyList.setFilterOnKeypress(true);
						purchaseInvoiceApplyList.setDataSource(dataSource);
						purchaseInvoiceApplyList.setPurchaseInvoiceDataInfo(dataInfo);
						dataInfomation= dataInfo;
						purchaseInvoiceApplyList.fetchData();
						purchaseInvoiceApplyList.drawPerchaseInvoiceList();

					}

				});

		final SectionStackSection invoiceApplyMainSection = new SectionStackSection("付款发票管理");
		purchaseInvoiceToolBar = new PurchaseInvoiceButtonToolBar(purchaseInvoiceApplyList);
		invoiceApplyMainSection.setItems(purchaseInvoiceToolBar, purchaseInvoiceApplyList);
		invoiceApplyMainSection.setExpanded(true);
		
		
		mainStack.addSection(invoiceApplyMainSection);
	
		mainPanelLayout.addMember(mainStack);
		return mainPanelLayout;
	}

}
