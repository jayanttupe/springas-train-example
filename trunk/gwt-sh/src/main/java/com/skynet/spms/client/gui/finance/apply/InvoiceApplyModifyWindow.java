package com.skynet.spms.client.gui.finance.apply;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.constants.ButtonConstants;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.ApprovalSheetDetailWinFactory;
import com.skynet.spms.client.gui.base.AttachmentListCanvas;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.service.ContractInfoService;
import com.skynet.spms.client.service.ContractInfoServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.vo.contractManagement.Contract;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.TitleDoubleClickEvent;
import com.smartgwt.client.widgets.form.fields.events.TitleDoubleClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewer;

public class InvoiceApplyModifyWindow extends BaseWindow {

	public InvoiceApplyModifyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
		this.setWidth(900);
		this.setHeight(600);
		// TODO Auto-generated constructor stub
	}

	ListGridField numberField = null;

	ListGridField titleField = null;

	ListGridField filenameField = null;

	ListGridField modifyUserField = null;

	ListGridField modifyDateField = null;
	
	List contractList = null;
	
	String business = null;
	
	Window contractWin=null;
	
	TextItem txtRecipeCashierName=null;
	//合同项数 
	TextItem txtTotalCount = null;
	//合同金额
	TextItem txtTotalAmount = null;
	//税率
	TextItem txtTax = null;
	//币种
	SelectItem selCurrencyCode =null;

	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {
		// TODO Auto-generated method stub
		ButtonConstants buttonConstants = GWT.create(ButtonConstants.class);
		final Window thisWin = this;
		setShowMinimizeButton(false);
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClientEvent event) {
				ShowWindowTools.showCloseWindow(srcRect, thisWin, -1);
			}
		});

		List<FormItem> itemList = new ArrayList<FormItem>();

		final DynamicForm form = new DynamicForm();
		form.setDataSource(listGrid.getDataSource());
		form.setHeight100();
		form.setWidth100();
		form.setPadding(5);
		form.setLayoutAlign(VerticalAlignment.BOTTOM);
		form.setNumCols(4);
		ListGridRecord record = listGrid.getSelectedRecord();
		final ContractInfoServiceAsync service = GWT.create(ContractInfoService.class);
		
		final TextItem txtApplyNumber = new TextItem("applyNumber");
		txtApplyNumber.setAttribute("readOnly", true);
		txtApplyNumber.setEndRow(true);
		txtApplyNumber.setValue(record.getAttribute("applyNumber"));
		itemList.add(txtApplyNumber);
		
		final SelectItem selInvoiceActionType = new SelectItem("m_PayActionType");
		selInvoiceActionType.setHint("<font color=red>*</font>");
		selInvoiceActionType.setEndRow(true);
		selInvoiceActionType.setDefaultValue(record.getAttribute("m_PayActionType"));
		itemList.add(selInvoiceActionType);

	final SelectItem sltOrderNumber = new SelectItem("orderNumber");
		
		selInvoiceActionType.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				
				
				String businessType =selInvoiceActionType.getValueAsString();
				String businessManager=null;
				sltOrderNumber.clearValue();
				sltOrderNumber.setValueMap("");
				txtRecipeCashierName.setValue("");
				if("repair".equals(businessType))
				{
					//送修费
					businessManager = "repairInspectClaimContractTemplateManagerForOrder";
					business = "送修合同";
				}else if("sales".equals(businessType)){
					businessManager="SalesContractTemplateForOrder";
					business = "销售合同";
				}else if("procurement".equals(businessType)){
					//采购费
					business =  "采购合同";
					businessManager="ProcurementContractTemplateManagerForOrder";
				}
				else if("repurchase".equals(businessType)){
					//businessManager="SalesContractTemplateForOrder";
				}
				else if("check".equals(businessType)){
					//送检费
					businessManager="repairInspectClaimContractTemplateManagerForOrder";
				}
				else if("instock".equals(businessType)){
					//businessManager="SalesContractTemplateForOrder";
				}else if("logistics".equals(businessType)){
					//物流费
					//businessManager="SalesContractTemplateForOrder";
				}
				
				if(businessManager!=null){
					service.getContracts( UserTools.getCurrentUserName(),businessManager, new AsyncCallback<List<Contract>>() {  
							@Override
							public void onFailure(Throwable arg0) {
								// TODO Auto-generated method stub		
								sltOrderNumber.clearValue();
							}
							@Override
							public void onSuccess(List<Contract> list) {
								// TODO Auto-generated method stub
								contractList = list;
								String[] strContractNumbers = null;
								if(list!=null&&list.size()!=0)
									 strContractNumbers = new String[list.size()];
								
								for(int i=0;i<list.size();i++){
									strContractNumbers[i] = new String();
									strContractNumbers[i]  = list.get(i).getContractNumber();
								}
								if (strContractNumbers!=null&&strContractNumbers.length!=0)
								{
									sltOrderNumber.clearValue();
									sltOrderNumber.setValueMap(strContractNumbers);
								}else{
									sltOrderNumber.clearValue();
								}
						
							}

						});
				}else{
					sltOrderNumber.clearValue();
					sltOrderNumber.setValueMap("");
				}
				  

			}
		});
		
	sltOrderNumber.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				String contractId = "";
				for(int i=0;i<contractList.size();i++){
					Contract contract = (Contract)contractList.get(i);
					if(contract.getContractNumber().equals(sltOrderNumber.getValue().toString())){
						//收票人
						txtRecipeCashierName.setValue(contract.getRecipeInvoiceAddress().getRecipient());
						//合同金额
						txtTotalAmount.setValue(contract.getContractTotalAmount());
						//合同项数
						txtTotalCount.setValue(contract.getTotalCount());
						//币种
						selCurrencyCode.setValue(contract.getM_InternationalCurrencyCode());
						contractId = contract.getId();
						break;
					}
				}
				contractWin = ApprovalSheetDetailWinFactory.getApprovalSheetDetailWin(contractId, business);
				
			}
		});
	
	sltOrderNumber.addTitleDoubleClickHandler(new TitleDoubleClickHandler() {
		
		@Override
		public void onTitleDoubleClick(TitleDoubleClickEvent event) {
			// TODO Auto-generated method stub
		   contractWin.show();	
		}
	});

		itemList.add(sltOrderNumber);
		sltOrderNumber.setValue(record.getAttribute("orderNumber"));
		
		sltOrderNumber.setHint("<font color=red>*</font>");
		final SelectItem selApplyDepartment = new SelectItem("applyDepartment");

		selApplyDepartment.setEndRow(true);
		selApplyDepartment.setHint("<font color=red>*</font>");

		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("organization.enterprise.department",
				"department_dataSource", new PostDataSourceInit() {
					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						selApplyDepartment.setOptionDataSource(dataSource);
						selApplyDepartment.setDisplayField("department");
					}
				});
		
		selApplyDepartment.setDefaultValue(record.getAttribute("applyDepartment"));

		itemList.add(selApplyDepartment);

		txtRecipeCashierName = new TextItem("recipeCashierName");
		txtRecipeCashierName.setEndRow(true);
		txtRecipeCashierName.setHint("<font color=red>*</font>");
		txtRecipeCashierName.setValue(record.getAttribute("recipeCashierName"));
		itemList.add(txtRecipeCashierName);

		SelectItem selInvoiceType = (SelectItem) ((InvoiceApplyList) listGrid)
				.getInvoiceApplyDataInfo().getFieldInfoByName("m_InvoiceType")
				.generFormField();
		selInvoiceType.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				if(event.getValue().toString().trim().equals("proformaInvoice")){
					txtTax.setDisabled(true);
				}else{
					txtTax.setDisabled(false);
				}
				
			}
		});
		selInvoiceType.setHint("<font color=red>*</font>");
		selInvoiceType.setDefaultValue(record.getAttribute("m_InvoiceType"));
		itemList.add(selInvoiceType);

		selCurrencyCode = (SelectItem) ((InvoiceApplyList) listGrid)
				.getInvoiceApplyDataInfo()
				.getFieldInfoByName("m_InternationalCurrencyCode")
				.generFormField();
		selCurrencyCode.setHint("<font color=red>*</font>");
		selCurrencyCode.setDefaultValue(record.getAttribute("m_InternationalCurrencyCode"));
		itemList.add(selCurrencyCode);
		
		txtTotalCount = new TextItem("totalCount");
		txtTotalCount.setAttribute("readOnly", true);
		txtTotalCount.setValue(record.getAttribute("totalCount"));
		itemList.add(txtTotalCount);
		
		txtTotalAmount = new TextItem("extendedValueTotalAmount");
		txtTotalAmount.setValue(record.getAttribute("extendedValueTotalAmount"));
		txtTotalAmount.setAttribute("readOnly", true);
		txtTotalAmount.setEndRow(true);
		itemList.add(txtTotalAmount);
		
		txtTax = new TextItem("tax");
		txtTax.setValue(record.getAttribute("tax"));
		txtTax.setDisabled(true);
		txtTax.setHint("%");
		txtTax.setWidth(25);
		txtTax.setEndRow(true);
		itemList.add(txtTax);

		TextItem txtInvoiceAmount = new TextItem("invoiceAmout");
		txtInvoiceAmount.setEndRow(true);
		txtInvoiceAmount.setHint("<font color=red>*</font>");
		txtInvoiceAmount.setDefaultValue(record.getAttribute("invoiceAmout"));
		itemList.add(txtInvoiceAmount);
		
		final TextItem operUser = new TextItem("applyUser");
		operUser.setValue(record.getAttribute("applyUser"));
		operUser.setAttribute("readOnly", true);
		itemList.add(operUser);
		
		HiddenItem id = new HiddenItem("id");
		id.setDefaultValue(record.getAttribute("id"));
		itemList.add(id);
		
		final HiddenItem upLoadItems = new HiddenItem("attachments");

		FormItem[] items = new FormItem[itemList.size()];
		itemList.toArray(items);
		form.setFields(items);

	    final AttachmentListCanvas attachmentListCanvas = new AttachmentListCanvas("account.applyManager.payApplyManager","attachments_dataSource",record.getAttribute("id"));
	    attachmentListCanvas.setTop("50%");
		IButton saveButton = new IButton();
		saveButton.setTitle(buttonConstants.saveButton());
		saveButton.setAlign(Alignment.CENTER);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				if (form.validate()) {
					if(selApplyDepartment.getSelectedRecord()!=null)
					selApplyDepartment.setValue(selApplyDepartment
							.getSelectedRecord().getAttribute("department"));
					
					form.setValue("check", "0");
					form.saveData();
					clear();
					ShowWindowTools.showCloseWindow(srcRect, thisWin, -1);
				}

			}
		});
		
		BtnsHLayout btnLayout = new BtnsHLayout();
		btnLayout.addMember(saveButton);

		VLayout vLayout = new VLayout();
		vLayout.addMember(form);
		vLayout.addMember(attachmentListCanvas);
		vLayout.addMember(btnLayout);
		return vLayout;
	}


}
