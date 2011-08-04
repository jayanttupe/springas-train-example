package com.skynet.spms.client.gui.finance.invoice;

import java.util.Date;
import java.util.LinkedHashMap;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.constants.ButtonConstants;
import com.skynet.spms.client.gui.base.AttachmentListCanvas;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.smartgwt.client.core.Rectangle;
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
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewer;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

public class CheckPurchaseInvoiceWindow extends BaseWindow {

	public CheckPurchaseInvoiceWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
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

		final DetailViewer view = new DetailViewer();
		view.setDataSource(listGrid.getDataSource());
		view.setWidth100();
		view.setPadding(5);
		view.setLayoutAlign(VerticalAlignment.BOTTOM);
		view.setGroupTitle("付款发票信息");
		view.setIsGroup(true);
		ListGridRecord record = listGrid.getSelectedRecord();
		view.setUseAllDataSourceFields(false);
		view.setData(listGrid.getSelection());
		final DetailViewerField invoiceNumber = new DetailViewerField("invoiceNumber");
		invoiceNumber.setValue(record.getAttribute("invoiceNumber"));
		
		final DetailViewerField invoiceActionType = new DetailViewerField("m_InvoiceBusinessType");
		invoiceActionType.setValue(record.getAttribute("m_InvoiceBusinessType"));
		
		final DetailViewerField orderNumber = new DetailViewerField("contractNumber");
		orderNumber.setValue(record.getAttribute("contractNumber"));
		


		final DetailViewerField invoiceType = new DetailViewerField("m_InvoiceType");
		invoiceType.setValue(record.getAttribute("m_InvoiceType"));
		
		final DetailViewerField internationalCurrencyCode = new DetailViewerField("m_InternationalCurrencyCode");
		internationalCurrencyCode.setValue(record.getAttribute("m_InternationalCurrencyCode"));
		
		final DetailViewerField taxRateField = new DetailViewerField("taxRate");
		taxRateField.setValue(record.getAttribute("taxRate"));

		final DetailViewerField payAmount = new DetailViewerField("amount");
		payAmount.setValue(record.getAttribute("amount"));
		
		final DetailViewerField taxField = new DetailViewerField("tax");
		taxField.setValue(record.getAttribute("tax"));
		
	 final DetailViewerField totalAmountField = new DetailViewerField("totalAmount");
	 totalAmountField.setValue(record.getAttribute("totalAmount"));
		
		final DetailViewerField applyUser = new DetailViewerField("invoiceUser");
		applyUser.setValue(record.getAttribute("invoiceUser"));
		
		final DetailViewerField applyDate = new DetailViewerField("invoiceDate");
		applyDate.setValue(record.getAttribute("invoiceDate"));
		
		view.setFields(invoiceNumber,orderNumber,invoiceActionType,invoiceType,
				internationalCurrencyCode,taxRateField,payAmount,taxField,totalAmountField,
				applyUser,applyDate);


	    final AttachmentListCanvas attachmentListCanvas = new AttachmentListCanvas("account.applyManager.payApplyManager","attachments_dataSource",record.getAttribute("id"));
	    attachmentListCanvas.setTop("60%");
	    
	    final DynamicForm form = new DynamicForm();
	    form.setDataSource(listGrid.getDataSource());
	   
	    LinkedHashMap map = new LinkedHashMap();
	    map.put("1", "通过");
	    map.put("2", "不通过");
	    
	 
//审核表单
	    form.setTop(200);
	    form.setWidth("100%");
	    form.setNumCols(4);
	    HiddenItem idHid = new HiddenItem("id");
	    idHid.setValue(record.getAttribute("id"));
/*	    HiddenItem checkDate = new HiddenItem("checkDate");
	    checkDate.setValue(new Date());*/
	    SelectItem sltCheck = new SelectItem("isChecked");
	    sltCheck.setValueMap(map);
	    form.setFields(idHid,sltCheck);
	    
	    BtnsHLayout btnLayout = new BtnsHLayout();
		IButton saveButton = new IButton();
		saveButton.setTitle(buttonConstants.saveButton());
		saveButton.setAlign(Alignment.CENTER);
		saveButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				form.saveData();
				ShowWindowTools.showCloseWindow(srcRect, thisWin, -1);
			}
		});
		btnLayout.addMember(saveButton);

		VLayout vLayout = new VLayout();
		vLayout.addMember(view);
		attachmentListCanvas.setHeight("20%");
		attachmentListCanvas.setLeft("5%");
		vLayout.addMember(attachmentListCanvas);
		vLayout.addMember(form);
		vLayout.addMember(btnLayout);
		return vLayout;
	}

}
