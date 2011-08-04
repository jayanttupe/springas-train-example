package com.skynet.spms.client.gui.finance.invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.constants.ButtonConstants;
import com.skynet.spms.client.entity.DataInfo;
import com.skynet.spms.client.feature.data.DataSourceTool;
import com.skynet.spms.client.feature.data.DataSourceTool.PostDataSourceInit;
import com.skynet.spms.client.gui.base.AttachmentListCanvas;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.gui.finance.apply.InvoiceApplyList;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.tools.UserTools;
import com.smartgwt.client.core.Rectangle;
import com.smartgwt.client.data.Criteria;
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
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.BlurEvent;
import com.smartgwt.client.widgets.form.fields.events.BlurHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;

public class SaleInvoiceAddWindow extends BaseWindow {

	private static final String NUMBER_SYSTEM = "(系统自动生成编号)";
	private static final String FONT_COLOR_RED_FONT = "<font color=red>*</font>";

	public SaleInvoiceAddWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
		// TODO Auto-generated constructor stub
	}

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

		TextItem txtInvoiceNumber = new TextItem("invoiceNumber");
		txtInvoiceNumber.setHint(FONT_COLOR_RED_FONT);
		txtInvoiceNumber.setAttribute("readOnly", true);
		txtInvoiceNumber.setValue(NUMBER_SYSTEM);
		txtInvoiceNumber.setEndRow(true);
		itemList.add(txtInvoiceNumber);
		
		String modName="account.applyManager.invoiceApplyManager";
		String dsName="finance_invoiceApply_dataSource";
		final SelectItem selApplyNumber = new SelectItem("invoiceApplyId");
		
		final LinkedHashMap applyNumberMap = new LinkedHashMap();
		final LinkedHashMap orderNumberMap = new LinkedHashMap();
		final LinkedHashMap amountMap = new LinkedHashMap();
		final LinkedHashMap recordMap = new LinkedHashMap();
		final LinkedHashMap taxMap = new LinkedHashMap();
		final LinkedHashMap currencyMap = new LinkedHashMap();
		final LinkedHashMap invoiceTypeMap = new LinkedHashMap();
		final LinkedHashMap invoiceBusinessTypeMap = new LinkedHashMap();
		DataSourceTool dataSourceTool = new DataSourceTool();
		dataSourceTool.onCreateDataSource(modName, dsName,
				new PostDataSourceInit() {

					@Override
					public void doPostOper(DataSource dataSource,
							DataInfo dataInfo) {
						// TODO Auto-generated method stub
						dataSource.fetchData(null, new DSCallback() {
							
							@Override
							public void execute(DSResponse response, Object rawData, DSRequest request) {
								// TODO Auto-generated method stub
								Record[] applyRecords=response.getData();
								for(Record applyRecord:applyRecords){
									if(applyRecord.getAttribute("check").toString().equals("1")){
										recordMap.put(applyRecord.getAttribute("applyNumber"), applyRecord.getAttribute("id"));
										applyNumberMap.put(applyRecord.getAttribute("id"),applyRecord.getAttribute("applyNumber"));
									    orderNumberMap.put(applyRecord.getAttribute("applyNumber"), applyRecord.getAttribute("orderNumber"));
									    amountMap.put(applyRecord.getAttribute("applyNumber"), applyRecord.getAttribute("invoiceAmout"));
									    //税率
									    taxMap.put(applyRecord.getAttributeAsObject("applyNumber"), applyRecord.getAttribute("tax"));
									    //货币类型
									    currencyMap.put(applyRecord.getAttributeAsObject("applyNumber"), applyRecord.getAttribute("m_InternationalCurrencyCode"));
									    //发票类型
									    invoiceTypeMap.put(applyRecord.getAttributeAsObject("applyNumber"), applyRecord.getAttribute("m_InvoiceType"));
									    //发票业务类型
									    invoiceBusinessTypeMap.put(applyRecord.getAttributeAsObject("applyNumber"), applyRecord.getAttribute("m_PayActionType"));
									}
										
								}
								selApplyNumber.setValueMap(applyNumberMap);
							}
						});
					}

				});
		

		itemList.add(selApplyNumber);
		
		

		final TextItem txtContractNumber = new TextItem("contractNumber");
		txtContractNumber.setHint(FONT_COLOR_RED_FONT);
		txtContractNumber.setEndRow(true);
		txtContractNumber.setAttribute("readOnly", true);
		itemList.add(txtContractNumber);

		final SelectItem selBusinessType = new SelectItem("m_PayActionType");
		selBusinessType.setEndRow(true);
		selBusinessType.setHint(FONT_COLOR_RED_FONT);
		itemList.add(selBusinessType);

		final SelectItem selInvoiceType = new SelectItem("m_InvoiceType");
		selInvoiceType.setEndRow(true);
		selInvoiceType.setHint(FONT_COLOR_RED_FONT);
		itemList.add(selInvoiceType);
		
		final TextItem txtTaxRate = new TextItem("taxRate");
		txtTaxRate.setEndRow(true);
		//txtTaxRate.setAttribute("readOnly", true);
		txtTaxRate.setHint(FONT_COLOR_RED_FONT);
		itemList.add(txtTaxRate);

		final SelectItem selCurrencyCode = new SelectItem("m_InternationalCurrencyCode");
		selCurrencyCode.setHint(FONT_COLOR_RED_FONT);
		selCurrencyCode.setEndRow(true);
		itemList.add(selCurrencyCode);

		final TextItem txtAmount = new TextItem("amount");
		txtAmount.setHint(FONT_COLOR_RED_FONT);
		txtAmount.setEndRow(true);
		itemList.add(txtAmount);
		

		final TextItem txtTax = new TextItem("tax");
		txtTax.setHint(FONT_COLOR_RED_FONT);
		txtTax.setAttribute("readOnly", true);
		txtTax.setEndRow(true);
		itemList.add(txtTax);

		final TextItem txtTotalAmount = new TextItem("totalAmount");
		txtTotalAmount.setAttribute("readOnly", true);
		txtTotalAmount.setHint(FONT_COLOR_RED_FONT);
		txtTotalAmount.setEndRow(true);
		itemList.add(txtTotalAmount);

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
   
		
	selApplyNumber.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				txtContractNumber.setValue(orderNumberMap.get(event.getItem().getDisplayValue()));
				txtAmount.setValue(amountMap.get(event.getItem().getDisplayValue()));
				txtTaxRate.setValue(Double.parseDouble((String)taxMap.get(event.getItem().getDisplayValue())));
			
				if(invoiceTypeMap.get(event.getItem().getDisplayValue())!=null)
					selInvoiceType.setValue(invoiceTypeMap.get(event.getItem().getDisplayValue()));
				
				if(currencyMap.get(event.getItem().getDisplayValue())!=null)
					selCurrencyCode.setValue(currencyMap.get(event.getItem().getDisplayValue()));
				
				if(invoiceBusinessTypeMap.get(event.getItem().getDisplayValue())!=null)
					selBusinessType.setValue(invoiceBusinessTypeMap.get(event.getItem().getDisplayValue()));
				
				if(txtTaxRate.getValue()!=null&&!txtTaxRate.getValue().equals("")
						&&txtAmount.getValue()!=null&&!txtAmount.getValue().equals("")){
					double taxRateValue = Double.parseDouble(txtTaxRate.getValue().toString());
					double amountValue = Double.parseDouble(txtAmount.getValue().toString());
					txtTax.setValue(amountValue*taxRateValue);
					txtTotalAmount.setValue(amountValue+amountValue*taxRateValue);
				}
			
			}
		});

		final HiddenItem invoiceUser = new HiddenItem("invoiceUser");
		itemList.add(invoiceUser);

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
				form.setValue("invoiceApplyId", (String)recordMap.get(selApplyNumber.getDisplayValue()));
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
