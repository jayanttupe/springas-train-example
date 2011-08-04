package com.skynet.spms.client.gui.finance.purposevoucher;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.finance.invoice.PurchaseInvoiceButtonToolBar;
import com.skynet.spms.client.gui.finance.invoice.PurchaseInvoiceList;
import com.skynet.spms.client.gui.finance.invoice.PurchaseInvoicePanel;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.ExpansionMode;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitEvent;
import com.smartgwt.client.widgets.grid.events.FilterEditorSubmitHandler;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

public class PurposeVoucherPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "凭证管理信息维护页面";
	private VLayout mainPanelLayout;
	private DataInfo dataInfomation;
	private PurposeVoucherButtonToolBar purposeVoucherToolBar;
	
	public PurposeVoucherPanel(){
		
	}
	
	public PurposeVoucherPanel(String modolName){
		super(modolName,"",null);
	}
	
	
	
	public static class Factory implements PanelFactory {

		private String DESCRIPTION = "凭证管理信息维护页面";
		private String id;
		private String modolName;
		
		public Factory(){
		}
		public Factory(String modolName){
			this.modolName = modolName;
			
		}
		
		@Override
		public Canvas create() {
			PurposeVoucherPanel panel = new PurposeVoucherPanel(modolName);
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
		String modName="";
		String dsName="";
		
			modName="account.certificateManager.payCertificateManager";
			dsName = "finance_purposeVoucher_dataSource";


			final PurposeVoucherList purposeVoucherList=new PurposeVoucherList();

		
		mainPanelLayout = new VLayout();
		
		
		SectionStack mainStack = new SectionStack();

		mainStack.setVisibilityMode(VisibilityMode.MULTIPLE);
		mainStack.setAnimateSections(true);
		

		
		DataSourceTool dataSourceTool = new DataSourceTool();

		dataSourceTool.onCreateDataSource(modName, dsName,
				new PostDataSourceInit() {

					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						// TODO Auto-generated method stub
						purposeVoucherList.setHeight(200);
						purposeVoucherList.setAutoFetchData(true);
						purposeVoucherList.setShowFilterEditor(true);
						purposeVoucherList.setDataSource(dataSource);
						purposeVoucherList.setPurposeVoucherDataInfo(dataInfo);
						purposeVoucherList.setCanEdit(false);
						dataInfomation= dataInfo;
						Criteria criteria = new Criteria();
						criteria.addCriteria("mode",moduleName);
						purposeVoucherList.fetchData(criteria);
						purposeVoucherList.setExpansionMode(ExpansionMode.DETAILS);
						purposeVoucherList.drawPerchaseInvoiceList();

					}

				});
		
		purposeVoucherList.addFilterEditorSubmitHandler(new FilterEditorSubmitHandler() {
			
			@Override
			public void onFilterEditorSubmit(FilterEditorSubmitEvent event) {
				// TODO Auto-generated method stub
				Criteria criteria = event.getCriteria();
			    String purposeVoucherType = criteria.getAttribute("purposeVoucherType");
			    if(purposeVoucherType==null||"".equals(purposeVoucherType))
			    {
			    	if(moduleName.equals("pay")){
			    		criteria.addCriteria("purposeVoucherType","pay");
			    	}else if(moduleName.equals("gathering"))
			    		criteria.addCriteria("purposeVoucherType","gathering");
			    }
			    	
				
			}
		});

		final SectionStackSection invoiceApplyMainSection = new SectionStackSection("凭证管理");
		purposeVoucherToolBar = new PurposeVoucherButtonToolBar(modName,purposeVoucherList);
		invoiceApplyMainSection.setItems(purposeVoucherToolBar, purposeVoucherList);
		invoiceApplyMainSection.setExpanded(true);
		
		
		mainStack.addSection(invoiceApplyMainSection);
	
		mainPanelLayout.addMember(mainStack);
		return mainPanelLayout;
	}

}
