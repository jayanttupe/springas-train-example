package com.skynet.spms.client.gui.finance.invoice;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.constants.ButtonConstants;
import com.skynet.spms.client.gui.base.ApprovalSheetDetailWinFactory;
import com.skynet.spms.client.gui.base.AttachmentListCanvas;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.service.ContractInfoService;
import com.skynet.spms.client.service.ContractInfoServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.vo.UserVo;
import com.skynet.spms.client.vo.contractManagement.Contract;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
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
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.BlurEvent;
import com.smartgwt.client.widgets.form.fields.events.BlurHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ItemHoverEvent;
import com.smartgwt.client.widgets.form.fields.events.ItemHoverHandler;
import com.smartgwt.client.widgets.form.fields.events.TitleDoubleClickEvent;
import com.smartgwt.client.widgets.form.fields.events.TitleDoubleClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

public class PurchaseInvoiceAddWindow extends BaseWindow {

	private static final String FONT_COLOR_RED_FONT = "<font color=red>*</font>";
	
	private static final String NUMBER_SYSTEM = "(系统自动生成编号)";
	
	String business= "";
	
	List contractList = null;
	
	TextItem txtAmount = null;
	
	SelectItem selCurrencyCode = null;
	
	Window contractWin=null;

	public PurchaseInvoiceAddWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
	}

	@Override
	protected Canvas getLeftLayout(final Rectangle srcRect, ListGrid listGrid) {

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
		
		TextItem txtInvoiceNumber = new TextItem("invoiceNumber");
		txtInvoiceNumber.setHint(FONT_COLOR_RED_FONT);
		txtInvoiceNumber.setEndRow(true);
		txtInvoiceNumber.setAttribute("readOnly", true);
		txtInvoiceNumber.setValue(NUMBER_SYSTEM);
		itemList.add(txtInvoiceNumber);
		String modName="account.applyManager.invoiceApplyManager";
		String dsName="finance_invoiceApply_dataSource";
		
		final SelectItem selBusinessType = new SelectItem("m_InvoiceBusinessType");
		selBusinessType.setEndRow(true);
		selBusinessType.setHint(FONT_COLOR_RED_FONT);
		final ContractInfoServiceAsync service = GWT
		.create(ContractInfoService.class);
		
		final SelectItem selContract = new SelectItem("contractNumber");
		
		selBusinessType.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				
				
				String businessType =selBusinessType.getValueAsString();
				String businessManager=null;
				selContract.clearValue();
				selContract.setValueMap("");
				txtAmount.setValue("");
				if("repair".equals(businessType))
				{
					//送修费
					businessManager = "repairInspectClaimContractTemplateManagerForOrder";
					business = "回购合同";
				}else if("sales".equals(businessType)){
					businessManager="SalesContractTemplateForOrder";
					business = "销售合同";
				}else if("procurement".equals(businessType)){
					//采购费
					business =  "采购合同";
					businessManager="ProcurementContractTemplateManagerForOrder";
				}
				else if("repurchase".equals(businessType)){
				}
				else if("check".equals(businessType)){
					//送检费
					businessManager="repairInspectClaimContractTemplateManagerForOrder";
				}
				else if("instock".equals(businessType)){

				}else if("logistics".equals(businessType)){
					//物流费
				}
				
				if(businessManager!=null){
					 service.getContracts( UserTools.getCurrentUserName(),businessManager, new AsyncCallback<List<Contract>>() {  
							@Override
							public void onFailure(Throwable arg0) {
								// TODO Auto-generated method stub		
								selContract.clearValue();
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
									selContract.clearValue();
									selContract.setValueMap(strContractNumbers);
								}else{
									selContract.clearValue();
								}
						
							}

						});
				}else{
					selContract.clearValue();
					selContract.setValueMap("");
				}
				  

			}
		});
		selContract.setWidth(200);
		
		itemList.add(selBusinessType);
		

		selContract.setHint(FONT_COLOR_RED_FONT);
		selContract.setAttribute("readOnly", true);
		selContract.setEndRow(true);
		
	selContract.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				String contractId = "";
				for(int i=0;i<contractList.size();i++){
					Contract contract = (Contract)contractList.get(i);
					if(contract.getContractNumber().equals(selContract.getValue().toString())){
						//账户信息
						txtAmount.setValue(contract.getContractTotalAmount());
						if(contract.getM_InternationalCurrencyCode()!=null&&!"".equals(contract.getM_InternationalCurrencyCode()))
							selCurrencyCode.setValue(contract.getM_InternationalCurrencyCode());
						contractId = contract.getId();
						break;
					}
				}
				contractWin = ApprovalSheetDetailWinFactory.getApprovalSheetDetailWin(contractId, business);
			}
		});
	
	selContract.addTitleDoubleClickHandler(new TitleDoubleClickHandler() {
		
		@Override
		public void onTitleDoubleClick(TitleDoubleClickEvent event) {
			// TODO Auto-generated method stub
			contractWin.show();
			
		}
	});
		
		
		itemList.add(selContract);



		SelectItem selInvoiceType = (SelectItem) (SelectItem) ((PurchaseInvoiceList) listGrid)
				.getPurchaseInvoiceDataInfo()
				.getFieldInfoByName("m_InvoiceType").generFormField();
		selInvoiceType.setEndRow(true);
		selInvoiceType.setHint(FONT_COLOR_RED_FONT);
		itemList.add(selInvoiceType);
		
		selCurrencyCode = new SelectItem("m_InternationalCurrencyCode");
		selCurrencyCode.setHint(FONT_COLOR_RED_FONT);
		selCurrencyCode.setEndRow(true);
		itemList.add(selCurrencyCode);

		final TextItem txtTaxRate = new TextItem("taxRate");
		txtTaxRate.setEndRow(true);
		txtTaxRate.setHint(FONT_COLOR_RED_FONT);
		itemList.add(txtTaxRate);



		txtAmount = new TextItem("amount");
		txtAmount.setHint(FONT_COLOR_RED_FONT);
		txtAmount.setEndRow(true);
		itemList.add(txtAmount);

		final TextItem txtTax = new TextItem("tax");
		txtTax.setHint(FONT_COLOR_RED_FONT);
		txtTax.setAttribute("readOnly", true);
		txtTax.setEndRow(true);
		itemList.add(txtTax);

		final TextItem txtTotalAmount = new TextItem("totalAmount");
		txtTotalAmount.setHint(FONT_COLOR_RED_FONT);
		txtTotalAmount.setAttribute("readOnly", true);
		txtTotalAmount.setEndRow(true);
		itemList.add(txtTotalAmount);

		final TextItem invoiceUser = new TextItem("invoiceUser");
		invoiceUser.setValue(UserTools.getCurrentUserName());
		invoiceUser.setAttribute("readOnly", true);
		invoiceUser.addItemHoverHandler(new ItemHoverHandler() {
			
			@Override
			public void onItemHover(ItemHoverEvent event) {
				// TODO Auto-generated method stub
				UserVo user = UserTools.getUserVoByUserName(UserTools.getCurrentUserName());
				if(user!=null){
					String text ="<table width='100%' style='BORDER-COLLAPSE: collapse' bgColor='#ffffff' borderColor='#000000' border='0' cellspacing='0' cellpadding='0' >";
					text = text + "<tr><td width='30%'>姓名：</td><td width='70%'>"+user.getRealName()+"</td></tr>";
					text = text + "<tr><td>EMAIL：</td><td>"+user.getEmail()+"</td></tr>";
					text = text + "</table>";			
					invoiceUser.setPrompt(text);
					
				}
			}
		});
		itemList.add(invoiceUser);

		txtAmount.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				// TODO Auto-generated method stub
				if(txtTaxRate.getValue()!=null&&!txtTaxRate.getValue().equals("")
						&&txtAmount.getValue()!=null&&!txtAmount.getValue().equals("")){
					double taxRateValue = Double.parseDouble(txtTaxRate.getValue().toString());
					double amountValue = Double.parseDouble(txtAmount.getValue().toString());
					txtTax.setValue(amountValue*taxRateValue);
					txtTotalAmount.setValue(amountValue+amountValue*taxRateValue);
				}
			}
		})	;
		
	  txtTaxRate.addBlurHandler(new BlurHandler() {
				
				@Override
				public void onBlur(BlurEvent event) {
					// TODO Auto-generated method stub
					if(txtTaxRate.getValue()!=null&&!txtTaxRate.getValue().equals("")
							&&txtAmount.getValue()!=null&&!txtAmount.getValue().equals("")){
						double taxRateValue = Double.parseDouble(txtTaxRate.getValue().toString());
						double amountValue = Double.parseDouble(txtAmount.getValue().toString());
						txtTax.setValue(amountValue*taxRateValue);
						txtTotalAmount.setValue(amountValue+amountValue*taxRateValue);
					}
				}
			});
					
		
		FormItem[] items = new FormItem[itemList.size()];
		itemList.toArray(items);
		form.setFields(items);
		// 附件上传Grid
		final AttachmentListCanvas attachmentListCanvas = new AttachmentListCanvas(
				"account.applyManager.payApplyManager",
				"attachments_dataSource");
		attachmentListCanvas.setTop("60%");

		IButton saveButton = new IButton();
		saveButton.setTitle(buttonConstants.saveButton());
		saveButton.setAlign(Alignment.CENTER);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				invoiceUser.setValue(UserTools.getCurrentUserName());
				if (form.validate()) {

					form.saveData(new DSCallback() {

						@Override
						public void execute(DSResponse response,
								Object rawData, DSRequest request) {
							// TODO Auto-generated method stub
							Record record = new Record();
							for (int i = 0; i < attachmentListCanvas.attachmentGrid
									.getTotalRows(); i++) {
								record.setAttribute("id",
										attachmentListCanvas.attachmentGrid
												.getEditedRecord(i)
												.getAttribute("id"));
								record.setAttribute("uuid",
										response.getData()[0]
												.getAttribute("id"));
								attachmentListCanvas.attachmentGrid
										.updateData(record);
							}

						}
					});
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
