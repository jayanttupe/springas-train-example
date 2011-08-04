package com.skynet.spms.client.gui.finance.apply;

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
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.CloseClientEvent;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.TitleDoubleClickEvent;
import com.smartgwt.client.widgets.form.fields.events.TitleDoubleClickHandler;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;

public class PayApplyModifyWindow extends BaseWindow{



	private static final String FONT_COLOR_RED_FONT = "<font color=red>*</font>";

	public PayApplyModifyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
		this.setWidth(900);
		this.setHeight(600);
		// TODO Auto-generated constructor stub
	}
	
	List contractList = null;
	
	TextItem txtAccountName = null;
	
	TextItem txtAccountAddress  = null;
	
	String business = null;
	
	Window contractWin = null;
	
	TextItem txtAccount = null;
	
	TextItem txtRecieveName  = null;
	
	//总项数 2011-6-14 yhuang
	TextItem txtTotalCount = null;
	
	//总金额 2011-6-14 yhuang
	TextItem txtTotalAmount = null;
	
	//合同支付说明
	TextAreaItem txtPayDescription = null;
	
	//支付方式
	SelectItem selPayment = null; 
	
	//货币代码
	SelectItem selCurrencyCode = null;

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

		final DynamicForm form = new DynamicForm();
		form.setDataSource(listGrid.getDataSource());
		form.setHeight100();
		form.setWidth100();
		form.setPadding(5);
		form.setLayoutAlign(VerticalAlignment.BOTTOM);
		form.setNumCols(4);
		ListGridRecord record = listGrid.getSelectedRecord();

		
		final SelectItem selPayActionType = (SelectItem) ((PayApplyList) listGrid)
				.getPayApplyDataInfo().getFieldInfoByName("m_PayActionType")
				.generFormField();
		selPayActionType.setDefaultValue(record.getAttribute("m_PayActionType"));
		selPayActionType.setHint(FONT_COLOR_RED_FONT);

		final SelectItem selContract = new SelectItem("orderNumber");
		selPayActionType.setHint(FONT_COLOR_RED_FONT);
		final ContractInfoServiceAsync service = GWT
		.create(ContractInfoService.class);
		
		selPayActionType.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				
				
				String businessType =selPayActionType.getValueAsString();
				String businessManager=null;
				selContract.clearValue();
				selContract.setValueMap("");
				txtAccountName.setValue("");
				txtAccountAddress.setValue("");
				txtAccount.setValue("");
				txtRecieveName.setValue("");
				txtTotalCount.setValue("");
				txtTotalAmount.setValue("");
				if("repair".equals(businessType))
				{
					//送修费
					business = "送修合同";
					businessManager = "repairInspectClaimContractTemplateManagerForOrder";
				}else if("sales".equals(businessType)){
					business = "销售合同";
					businessManager="SalesContractTemplateForOrder";
				}else if("procurement".equals(businessType)){
					//采购费
					business = "采购合同";
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
				}
				  

			}
		});
		selContract.setWidth(200);
		selContract.setValue(record.getAttribute("orderNumber"));
		selContract.setHint(FONT_COLOR_RED_FONT);

        selContract.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				String contractId = "";
				for(int i=0;i<contractList.size();i++){
					Contract contract = (Contract)contractList.get(i);
					if(contract.getContractNumber().equals(selContract.getValue().toString())){
						//账户信息
						txtAccountName.setValue(contract.getBankInformation().getBankName());
						txtAccountAddress.setValue(contract.getBankInformation().getBankAddress());
						txtAccount.setValue(contract.getBankInformation().getBankAccount());
						txtRecieveName.setValue(contract.getRecieveName());
						txtTotalCount.setValue(contract.getTotalCount());
						txtTotalAmount.setValue(contract.getContractTotalAmount());
						txtPayDescription.setValue(contract.getPaymentSM());
						selPayment.setValue(contract.getM_Payment());
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
		final SelectItem selApplyDepartment = new SelectItem("applyDepartment");

		selApplyDepartment.setEndRow(true);
		selApplyDepartment.setHint(FONT_COLOR_RED_FONT);
		
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource("organization.enterprise.department", "department_dataSource", new PostDataSourceInit() {
			@Override
			public void doPostOper(DataSource dataSource, DataInfo dataInfo) {
				selApplyDepartment.setOptionDataSource(dataSource);
				selApplyDepartment.setDisplayField("department");
			}
		});
		selApplyDepartment.setDefaultValue(record.getAttribute("applyDepartment"));
		
		
		
		txtRecieveName = new TextItem("cashierName");
		txtRecieveName.setDefaultValue(record.getAttribute("cashierName"));
		txtRecieveName.setEndRow(true);
		txtRecieveName.setHint(FONT_COLOR_RED_FONT);

		TextItem txtPayAmount = new TextItem("payAmount");
		txtPayAmount.setDefaultValue(record.getAttribute("payAmount"));
		txtPayAmount.setEndRow(true);
		txtPayAmount.setHint(FONT_COLOR_RED_FONT);
		
		txtTotalAmount = new TextItem("totalAmount");
		txtTotalAmount .setAttribute("readOnly", true);
		txtTotalAmount.setDefaultValue(record.getAttribute("totalAmount"));
		txtTotalAmount .setHint(FONT_COLOR_RED_FONT);
		
		txtPayDescription = new TextAreaItem("payDescription");
		txtPayDescription .setAttribute("readOnly", true);
		txtPayDescription.setEndRow(true);
		txtPayDescription.setDefaultValue(record.getAttribute("payDescription"));
		txtPayDescription .setHint(FONT_COLOR_RED_FONT);

		selPayment = new SelectItem("m_Payment");
		selPayment.setDefaultValue(record.getAttribute("m_Payment"));
		selPayment.setHint(FONT_COLOR_RED_FONT);
		
		selCurrencyCode = new SelectItem("m_InternationalCurrencyCode");
		selCurrencyCode.setDefaultValue(record.getAttribute("m_InternationalCurrencyCode"));
		selCurrencyCode.setHint(FONT_COLOR_RED_FONT);
		
		txtTotalCount = new TextItem("totalCount");
		txtTotalCount .setAttribute("readOnly", true);
		txtTotalCount .setEndRow(true);
		txtTotalCount.setDefaultValue(record.getAttribute("totalCount"));
		txtTotalCount .setHint(FONT_COLOR_RED_FONT);

		txtAccountName = new TextItem("accountName");
		txtAccountName.setDefaultValue(record.getAttribute("accountName"));
		txtAccountName.setEndRow(true);
		txtAccountName.setHint(FONT_COLOR_RED_FONT);

		txtAccountAddress = new TextItem("accountAddress");
		txtAccountAddress.setDefaultValue(record.getAttribute("accountAddress"));
		txtAccountAddress.setWidth(150);
		txtAccountAddress.setHint(FONT_COLOR_RED_FONT);
		txtAccountAddress.setEndRow(true);

		txtAccount = new TextItem("account");
		txtAccount.setDefaultValue(record.getAttribute("account"));
		txtAccount.setEndRow(true);
		txtAccount.setHint(FONT_COLOR_RED_FONT);
		
		HiddenItem id = new HiddenItem("id");
		id.setDefaultValue(record.getAttribute("id"));
		
		final HiddenItem upLoadItems = new HiddenItem("attachments");

		form.setItems(id,selPayActionType, selContract, selApplyDepartment,
				txtRecieveName, txtPayAmount, txtTotalAmount,txtPayDescription,
				selPayment, selCurrencyCode,txtTotalCount,
				txtAccountName, txtAccountAddress, txtAccount,upLoadItems);
		
	
		
		 final AttachmentListCanvas attachmentListCanvas = new AttachmentListCanvas("account.applyManager.payApplyManager","attachments_dataSource",record.getAttribute("id"));
		  attachmentListCanvas.setTop("70%");
		 IButton saveButton = new IButton();
		saveButton.setTitle(buttonConstants.saveButton());
		saveButton.setAlign(Alignment.CENTER);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if(selApplyDepartment.getSelectedRecord()!=null);
					selApplyDepartment.setValue(selApplyDepartment.getSelectedRecord().getAttribute("department"));
					form.setValue("check", "0");
					form.saveData();
					ShowWindowTools.showCloseWindow(srcRect, thisWin, -1);
				

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
