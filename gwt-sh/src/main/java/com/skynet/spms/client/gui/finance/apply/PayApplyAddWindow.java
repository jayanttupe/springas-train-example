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
import com.skynet.spms.client.gui.base.WorkFlowSheetType;
import com.skynet.spms.client.service.ContractInfoService;
import com.skynet.spms.client.service.ContractInfoServiceAsync;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.UserTools;
import com.skynet.spms.client.vo.contractManagement.Contract;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.DSCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
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
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;

public class PayApplyAddWindow extends BaseWindow {

	private static final String FONT_COLOR_RED_FONT = "<font color=red>*</font>";

	private static final String NUMBER_SYSTEM = "(系统自动生成编号)";

	public PayApplyAddWindow(String windowTitle, boolean isMax,
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
	
	String business= "";
	
	List contractList = null;
	
	Window contractWin=null;
	
	TextItem txtAccountName = null;
	
	TextItem txtAccountAddress  = null;
	
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
	protected Canvas getLeftLayout(final Rectangle srcRect,
			final ListGrid listGrid) {
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
		
		final TextItem txtApplyNumber = new TextItem("applyNumber");
		txtApplyNumber.setAttribute("readOnly", true);
		txtApplyNumber.setValue(NUMBER_SYSTEM);
		txtApplyNumber.setEndRow(true);

		final SelectItem selPayActionType = (SelectItem) ((PayApplyList) listGrid)
				.getPayApplyDataInfo().getFieldInfoByName("m_PayActionType")
				.generFormField();
		
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
						if(contract.getM_Payment()!=null&&!"".equals(contract.getM_Payment()))
							selPayment.setValue(contract.getM_Payment());
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
	

		final SelectItem selApplyDepartment = new SelectItem("applyDepartment");

		selApplyDepartment.setEndRow(true);
		selApplyDepartment.setHint(FONT_COLOR_RED_FONT);
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

		txtRecieveName = new TextItem("cashierName");
		txtRecieveName.setEndRow(true);
		txtRecieveName.setHint(FONT_COLOR_RED_FONT);

		TextItem txtPayAmount = new TextItem("payAmount");
		txtPayAmount.setHint(FONT_COLOR_RED_FONT);
		
		txtTotalAmount = new TextItem("totalAmount");
		txtTotalAmount .setAttribute("readOnly", true);
		txtTotalAmount .setHint(FONT_COLOR_RED_FONT);
		
		txtPayDescription = new TextAreaItem("payDescription");
		txtPayDescription .setAttribute("readOnly", true);
		txtPayDescription.setEndRow(true);
		txtPayDescription .setHint(FONT_COLOR_RED_FONT);

		selPayment = new SelectItem("m_Payment");
		selPayment.setHint(FONT_COLOR_RED_FONT);

		selCurrencyCode =new SelectItem("m_InternationalCurrencyCode");
		selCurrencyCode.setHint(FONT_COLOR_RED_FONT);
		
		txtTotalCount = new TextItem("totalCount");
		txtTotalCount .setAttribute("readOnly", true);
		txtTotalCount .setEndRow(true);
		txtTotalCount .setHint(FONT_COLOR_RED_FONT);
		
		txtAccountName = new TextItem("accountName");
		txtAccountName.setEndRow(true);
		txtAccountName.setHint(FONT_COLOR_RED_FONT);

		txtAccountAddress = new TextItem("accountAddress");
		txtAccountAddress.setWidth(150);
		txtAccountAddress.setHint(FONT_COLOR_RED_FONT);
		txtAccountAddress.setEndRow(true);

		txtAccount = new TextItem("account");
		txtAccount.setEndRow(true);
		txtAccount.setHint(FONT_COLOR_RED_FONT);

		final HiddenItem upLoadItems = new HiddenItem("attachments");
		final HiddenItem operUser = new HiddenItem("applyUser");
		final HiddenItem applyUser = new HiddenItem("createBy");
		form.setItems(txtApplyNumber,selPayActionType, selContract, selApplyDepartment,
				txtRecieveName, txtPayAmount, txtTotalAmount,txtPayDescription,selPayment, selCurrencyCode,txtTotalCount,
				txtAccountName, txtAccountAddress, txtAccount, upLoadItems,operUser,applyUser);
		//附件上传Grid
		final AttachmentListCanvas attachmentListCanvas = new AttachmentListCanvas(
				"account.applyManager.payApplyManager",
				"attachments_dataSource");
		  attachmentListCanvas.setTop("70%");
		IButton saveButton = new IButton();
		saveButton.setTitle(buttonConstants.saveButton());
		saveButton.setAlign(Alignment.CENTER);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				if (form.validate()) {
					operUser.setValue(UserTools.getCurrentUserName());
					applyUser.setValue(UserTools.getCurrentUserName());
					selApplyDepartment.setValue(selApplyDepartment
							.getSelectedRecord().getAttribute("department"));
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
