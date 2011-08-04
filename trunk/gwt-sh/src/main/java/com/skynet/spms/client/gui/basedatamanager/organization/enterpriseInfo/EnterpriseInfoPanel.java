package com.skynet.spms.client.gui.basedatamanager.organization.enterpriseInfo;

import java.util.LinkedHashMap;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.ShowcasePanel;
import com.skynet.spms.client.constants.ConstantsUtil;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class EnterpriseInfoPanel extends ShowcasePanel {

	private static final String DESCRIPTION = "企业基础信息维护 ";

	private EnterpriseInfoTreeGrid enterpriseInfoTreeGrid;
	private EnterpriseInfoButtonToolBar enterpriseInfoButtonToolBar;
	private Label lblEnterprise;
	public EnterpriseInfoPanel() {
		super();
	}
	public EnterpriseInfoPanel(String moduleName, String dataSourceName) {
		super(moduleName, dataSourceName,null);
	}

	public static class Factory implements PanelFactory {

		private String id;
		private String moduleName;
		private String dataSourceName;

		public Factory(){
		}
		public Factory(String moduleName,String dataSourceName){
			this.moduleName = moduleName;
			this.dataSourceName = dataSourceName;
		}
		public Canvas create() {
			EnterpriseInfoPanel panel = new EnterpriseInfoPanel(moduleName,dataSourceName);
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

	@Override
	public Canvas getViewPanel() {
		// 主Layout
		VLayout mainPanelLayout = new VLayout();
		
		VLayout barGridLayout = new VLayout();
		barGridLayout.setHeight("40%");
		barGridLayout.setShowResizeBar(true);
		
		//数据表格
		enterpriseInfoTreeGrid = new EnterpriseInfoTreeGrid();		
		//按钮面板（增删改查等按钮）
		enterpriseInfoButtonToolBar = new EnterpriseInfoButtonToolBar(super.moduleName,enterpriseInfoTreeGrid);
		
		barGridLayout.addMember(enterpriseInfoButtonToolBar);
		barGridLayout.addMember(enterpriseInfoTreeGrid);
		
		//中间的所选企业提示
		HLayout tipLayout = new HLayout(5);
		tipLayout.setHeight(20);
		
		Label lblEnterpriseTip = new Label("所选企业：");
		lblEnterpriseTip.setWidth(60);
		lblEnterprise = new Label();
		lblEnterprise.setWidth(200);
		tipLayout.addMember(lblEnterpriseTip);
		tipLayout.addMember(lblEnterprise);
		
		final TabSet tabSet = new TabSet();
		//发货地址管理选项卡
		final Tab shippingAddressTab = new Tab(ConstantsUtil.organizationConstants.shippingAddress(),"pieces/16/star_green.png");
		final ShippingAddressPanel shippingAddressPanel = new ShippingAddressPanel();
		shippingAddressTab.setPane(shippingAddressPanel);
		tabSet.addTab(shippingAddressTab);
		
		//收货地址管理选项卡
		final Tab consigneeAddressTab = new Tab(ConstantsUtil.organizationConstants.consigneeAddress(),"pieces/16/star_green.png");
		final ConsigneeAddressPanel consigneeAddressPanel = new ConsigneeAddressPanel();
		consigneeAddressTab.setPane(consigneeAddressPanel);
		tabSet.addTab(consigneeAddressTab);
		
		//收发票地址管理选项卡
		final Tab recipeInvoiceAddressTab = new Tab(ConstantsUtil.organizationConstants.recipeInvoiceAddress(),"pieces/16/star_green.png");
		final RecipeInvoiceAddressPanel recipeInvoiceAddressPanel = new RecipeInvoiceAddressPanel();
		recipeInvoiceAddressTab.setPane(recipeInvoiceAddressPanel);
		tabSet.addTab(recipeInvoiceAddressTab);
		
		//银行帐户管理选项卡
		final Tab bankInformationTab = new Tab(ConstantsUtil.organizationConstants.bankInformation(),"pieces/16/star_green.png");
		final BankInfoPanel bankInfoPanel = new BankInfoPanel();
		bankInformationTab.setPane(bankInfoPanel);
		tabSet.addTab(bankInformationTab);
		
		//增值税管理选项卡
		final Tab vatTab = new Tab(ConstantsUtil.organizationConstants.vat(),"pieces/16/star_green.png");
		final VATPanel vatPanel = new VATPanel();
		vatTab.setPane(vatPanel);
		tabSet.addTab(vatTab);
		
		//客户信用金管理，信用额度管理选项卡
		if(dataSourceName.equals("customer_dataSource")){
			final Tab creditTab = new Tab(ConstantsUtil.organizationConstants.customerCredit(),"pieces/16/star_green.png");
			final CustomerCreditPanel customerCreditPanel = new CustomerCreditPanel();
			creditTab.setPane(customerCreditPanel);
			tabSet.addTab(creditTab);
			
			final Tab creditLineTab = new Tab(ConstantsUtil.organizationConstants.customerCreditLine(),"pieces/16/star_green.png");
			final CustomerCreditLinePanel customerCreditLinePanel = new CustomerCreditLinePanel();
			creditLineTab.setPane(customerCreditLinePanel);
			tabSet.addTab(creditLineTab);
		}
		
		//供应商质量管理选项卡
		if(dataSourceName.equals("supplier_dataSource")){
			final Tab supplierQATab = new Tab(ConstantsUtil.organizationConstants.supplierQA(),"pieces/16/star_green.png");
			final SupplierQAPanel supplierQAPanel = new SupplierQAPanel();
			supplierQATab.setPane(supplierQAPanel);
			tabSet.addTab(supplierQATab);
		}
		
		//企业GTA管理选项卡
		if(!dataSourceName.equals("COMACSC_dataSource")){
			final Tab gtaTab = new Tab(ConstantsUtil.organizationConstants.gta(),"pieces/16/star_green.png");
			final EnterpriseGTAPanel enterpriseGTAPanel = new EnterpriseGTAPanel();
			gtaTab.setPane(enterpriseGTAPanel);
			tabSet.addTab(gtaTab);
		}
		
		//企业适航授权管理选项卡
		if(dataSourceName.equals("supplier_dataSource") 
				|| dataSourceName.equals("manufacturer_dataSource")
				|| dataSourceName.equals("repairAgency_dataSource")){
			final Tab airWorthinessTab = new Tab(ConstantsUtil.organizationConstants.airworthiness(),"pieces/16/star_green.png");
			final AirworthinessPanel airworthinessPanel = new AirworthinessPanel();
			airWorthinessTab.setPane(airworthinessPanel);
			tabSet.addTab(airWorthinessTab);
		}
		
		
		enterpriseInfoTreeGrid.addSelectionChangedHandler(new SelectionChangedHandler() {
			@Override
			public void onSelectionChanged(SelectionEvent event) {
				Record selectedRecord = enterpriseInfoTreeGrid.getSelectedRecord();
				Tab selectedTab = tabSet.getSelectedTab();
				refreshTab(selectedTab, selectedRecord);
			}
		});
		
		enterpriseInfoTreeGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				Record selectedRecord = event.getRecord();
				enterpriseInfoTreeGrid.selectRecords(enterpriseInfoTreeGrid.getSelection(), false);
				enterpriseInfoTreeGrid.selectRecord(selectedRecord);
				
			}
		});
		
		tabSet.addTabSelectedHandler(new TabSelectedHandler() {	
			@Override
			public void onTabSelected(TabSelectedEvent event) {
				Tab selectedTab = tabSet.getSelectedTab();
				Record selectedRecord = enterpriseInfoTreeGrid.getSelectedRecord();
				refreshTab(selectedTab, selectedRecord);
			}
		});

		/*Canvas spacer = new Canvas();
		spacer.setHeight(2);*/
		
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(moduleName, dataSourceName, new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				DataSourceField idField = dataSource.getField("id");
				idField.setPrimaryKey(true);
				idField.setRequired(true);
				DataSourceField parentIdField = dataSource.getField("parentId");
				parentIdField.setRequired(true);
				parentIdField.setForeignKey("id");
				parentIdField.setRootValue((String)null);
				
				LinkedHashMap<String,String> valueMap = new LinkedHashMap<String,String>();
				valueMap.put("true",ConstantsUtil.commonConstants.choiceYes());
				valueMap.put("false",ConstantsUtil.commonConstants.choiceNo());
				
				DataSourceField isMainField = dataSource.getField("mainEnterprise");
				isMainField.setValueMap(valueMap);
				DataSourceField isRunningField = dataSource.getField("running");
				isRunningField.setValueMap(valueMap);
				DataSourceField isAppointField = dataSource.getField("appoint");
				if(isAppointField != null){
					isAppointField.setValueMap(valueMap);
				}
				
				enterpriseInfoTreeGrid.setDataSource(dataSource);
				enterpriseInfoTreeGrid.setDataInfo(dataInfo);
				enterpriseInfoTreeGrid.fetchData();
				enterpriseInfoTreeGrid.drawEnterpriseInfoTreeGrid();
			}
		});
		mainPanelLayout.addMember(barGridLayout);
		mainPanelLayout.addMember(tipLayout);
		mainPanelLayout.addMember(tabSet);
		
		return mainPanelLayout;
	}
	
	private void refreshTab(Tab selectedTab, Record selectedRecord){
		if(enterpriseInfoTreeGrid.getSelection().length == 1){
			String abbreviation = selectedRecord.getAttribute("abbreviation");
			lblEnterprise.setContents(abbreviation);
		}else{
			lblEnterprise.setContents("");
		}
		Canvas panel = selectedTab.getPane();
		//发货地址管理
		if(panel instanceof ShippingAddressPanel){
			ShippingAddressPanel sap = (ShippingAddressPanel)panel;
			if(enterpriseInfoTreeGrid.getSelection().length == 1){
				sap.fetchData(selectedRecord.getAttribute("id"));
			}else{
				sap.clearData();
			}
			
		}
		//收货地址管理
		if(panel instanceof ConsigneeAddressPanel){
			ConsigneeAddressPanel cap = (ConsigneeAddressPanel)panel;
			if(enterpriseInfoTreeGrid.getSelection().length == 1){
				cap.fetchData(selectedRecord.getAttribute("id"));
			}else{
				cap.clearData();
			}
			
		}
		//收发票地址管理
		if(panel instanceof RecipeInvoiceAddressPanel){
			RecipeInvoiceAddressPanel riap = (RecipeInvoiceAddressPanel)panel;
			if(enterpriseInfoTreeGrid.getSelection().length == 1){
				riap.fetchData(selectedRecord.getAttribute("id"));
			}else{
				riap.clearData();
			}
			
		}
		//银行帐户管理
		if(panel instanceof BankInfoPanel){
			BankInfoPanel bip = (BankInfoPanel)panel;
			if(enterpriseInfoTreeGrid.getSelection().length == 1){
				bip.fetchData(selectedRecord.getAttribute("id"));
			}else{
				bip.clearData();
			}
			
		}
		//增值税管理
		if(panel instanceof VATPanel){
			VATPanel vp = (VATPanel)panel;
			if(enterpriseInfoTreeGrid.getSelection().length == 1){
				String vatId = selectedRecord.getAttribute("m_VAT.id");
				vp.fetchData(vatId);
			}else{
				vp.clearFormValues();
			}
		}
		//企业GTA管理
		if(panel instanceof EnterpriseGTAPanel){
			EnterpriseGTAPanel gtap = (EnterpriseGTAPanel)panel;
			if(enterpriseInfoTreeGrid.getSelection().length == 1){
				gtap.fetchData(selectedRecord.getAttribute("id"));
			}else{
				gtap.clearData();
			}
			
		}
		if(panel instanceof AirworthinessPanel){
			AirworthinessPanel ap = (AirworthinessPanel)panel;
			if(enterpriseInfoTreeGrid.getSelection().length == 1){
				ap.fetchData(selectedRecord.getAttribute("id"));
			}else{
				ap.clearData();
			}
			
		}
		//客户信用额度管理
		if(panel instanceof CustomerCreditLinePanel){
			CustomerCreditLinePanel cclp = (CustomerCreditLinePanel)panel;
			if(enterpriseInfoTreeGrid.getSelection().length == 1){
				cclp.fetchData(selectedRecord.getAttribute("id"));
			}else{
				cclp.clearData();
			}
			
		}
		//客户信用金管理
		if(panel instanceof CustomerCreditPanel){
			CustomerCreditPanel ccp = (CustomerCreditPanel)panel;
			if(enterpriseInfoTreeGrid.getSelection().length == 1){
				ccp.fetchData(selectedRecord.getAttribute("id"));
			}else{
				ccp.clearData();
			}
			
		}
		//企业适航授权管理
		if(panel instanceof SupplierQAPanel){
			SupplierQAPanel sqap = (SupplierQAPanel)panel;
			if(enterpriseInfoTreeGrid.getSelection().length == 1){
				sqap.fetchData(selectedRecord.getAttribute("id"));
			}else{
				sqap.clearData();
			}
			
		}
	}

	public String getIntro() {
		return DESCRIPTION;
	}
}
