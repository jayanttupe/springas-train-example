package com.skynet.spms.client.gui.finance.apply;

import java.util.LinkedHashMap;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.skynet.spms.client.constants.ButtonConstants;
import com.skynet.spms.client.gui.base.AttachmentListCanvas;
import com.skynet.spms.client.gui.base.BaseWindow;
import com.skynet.spms.client.gui.base.BtnsHLayout;
import com.skynet.spms.client.tools.ShowWindowTools;
import com.skynet.spms.client.widgets.form.LayoutDynamicForm;
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
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewer;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

public class CheckPayApplyWindow extends BaseWindow {

	public CheckPayApplyWindow(String windowTitle, boolean isMax,
			Rectangle srcRect, ListGrid listGrid, String iconUrl) {
		super(windowTitle, isMax, srcRect, listGrid, iconUrl);
		// TODO Auto-generated constructor stub
	}
	ListGridField numberField = null;

	ListGridField titleField = null;

	ListGridField filenameField = null;

	ListGridField modifyUserField = null;

	ListGridField modifyDateField = null;
	
	List contractList = null;
	
	TextItem txtRecipeCashierName=null;
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
		view.setIsGroup(true);
		view.setLayoutAlign(VerticalAlignment.BOTTOM);
		view.setGroupTitle("付款申请信息");
		ListGridRecord record = listGrid.getSelectedRecord();
		view.setUseAllDataSourceFields(false);
		view.setData(listGrid.getSelection());
		final DetailViewerField invoiceActionType = new DetailViewerField("m_PayActionType");
		invoiceActionType.setValue(record.getAttribute("m_PayActionType"));
		
		final DetailViewerField orderNumber = new DetailViewerField("orderNumber");
		orderNumber.setValue(record.getAttribute("orderNumber"));
		
		final DetailViewerField applyDepartment = new DetailViewerField("applyDepartment");
		applyDepartment.setValue(record.getAttribute("applyDepartment"));


		final DetailViewerField invoiceType = new DetailViewerField("m_Payment");
		invoiceType.setValue(record.getAttribute("m_Payment"));
		
		final DetailViewerField internationalCurrencyCode = new DetailViewerField("m_InternationalCurrencyCode");
		internationalCurrencyCode.setValue(record.getAttribute("m_InternationalCurrencyCode"));

		final DetailViewerField payAmount = new DetailViewerField("payAmount");
		payAmount.setValue(record.getAttribute("payAmount"));
		
		final DetailViewerField accountName = new DetailViewerField("accountName");
		accountName.setValue(record.getAttribute("accountName"));
		
		final DetailViewerField accountAddress = new DetailViewerField("accountAddress");
		accountAddress.setValue(record.getAttribute("accountAddress"));
		
		final DetailViewerField account = new DetailViewerField("account");
		account.setValue(record.getAttribute("account"));
		
		final DetailViewerField applyUser = new DetailViewerField("applyUser");
		applyUser.setValue(record.getAttribute("applyUser"));
		
		final DetailViewerField applyDate = new DetailViewerField("applyDate");
		applyDate.setValue(record.getAttribute("applyDate"));
		
		view.setFields(orderNumber,applyDepartment,invoiceActionType,invoiceType,
				internationalCurrencyCode,payAmount,accountName,
				accountAddress,account,applyUser,applyDate);


	    final AttachmentListCanvas attachmentListCanvas = new AttachmentListCanvas("account.applyManager.payApplyManager","attachments_dataSource",record.getAttribute("id"));
	    attachmentListCanvas.setTop("70%");
	    
	    final DynamicForm form = new DynamicForm();
	    form.setDataSource(listGrid.getDataSource());
	   
	    LinkedHashMap map = new LinkedHashMap();
	    map.put("1", "通过");
	    map.put("2", "不通过");
	    
	 
	    form.setTop("50%");
	    form.setWidth("100%");
	    form.setNumCols(4);
	    HiddenItem idHid = new HiddenItem("id");
	    idHid.setValue(record.getAttribute("id"));
	    SelectItem sltCheck = new SelectItem("check");
	    sltCheck.setDisabled(false);
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

		// 将form放入TileGrid中，因为TileGrid可以有滚动条

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
