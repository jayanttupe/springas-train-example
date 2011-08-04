package com.skynet.spms.client.gui.finance.apply;

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

public class CheckInvoiceApplyWindow extends BaseWindow {

	public CheckInvoiceApplyWindow(String windowTitle, boolean isMax,
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
		view.setGroupTitle("发票申请信息");
		view.setIsGroup(true);
		ListGridRecord record = listGrid.getSelectedRecord();
		view.setUseAllDataSourceFields(false);
		view.setData(listGrid.getSelection());
		final DetailViewerField invoiceActionType = new DetailViewerField("m_InvoiceType");
		invoiceActionType.setValue(record.getAttribute("m_InvoiceType"));
		
		final DetailViewerField orderNumber = new DetailViewerField("orderNumber");
		orderNumber.setValue(record.getAttribute("orderNumber"));
		
		final DetailViewerField applyDepartment = new DetailViewerField("applyDepartment");
		applyDepartment.setValue(record.getAttribute("applyDepartment"));


		final DetailViewerField invoiceType = new DetailViewerField("recipeCashierName");
		invoiceType.setValue(record.getAttribute("recipeCashierName"));
		
		final DetailViewerField internationalCurrencyCode = new DetailViewerField("m_InternationalCurrencyCode");
		internationalCurrencyCode.setValue(record.getAttribute("m_InternationalCurrencyCode"));

		final DetailViewerField payAmount = new DetailViewerField("invoiceAmout");
		payAmount.setValue(record.getAttribute("invoiceAmout"));
		
		final DetailViewerField applyUser = new DetailViewerField("applyUser");
		applyUser.setValue(record.getAttribute("applyUser"));
		
		final DetailViewerField applyDate = new DetailViewerField("applyDate");
		applyDate.setValue(record.getAttribute("applyDate"));
		
		view.setFields(orderNumber,applyDepartment,invoiceActionType,invoiceType,
				internationalCurrencyCode,payAmount,
				applyUser,applyDate);


	    final AttachmentListCanvas attachmentListCanvas = new AttachmentListCanvas("account.applyManager.payApplyManager","attachments_dataSource",record.getAttribute("id"));
	    attachmentListCanvas.setTop("55%");
	    
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
	    HiddenItem checkDate = new HiddenItem("checkDate");
	    checkDate.setValue(new Date());
	    SelectItem sltCheck = new SelectItem("check");
	    sltCheck.setValueMap(map);
	    form.setFields(idHid,checkDate,sltCheck);
	    
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
