package com.skynet.spms.client.gui.customerplatform.exchangeRequisitionSheet.modify;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerplatform.exchangeRequisitionSheet.SheetModelLocator;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 送修申请单添加窗体
 * 
 * @author tqc
 * 
 */
public class ExchangeRequisitionSheetModifyWin extends Window {
	
	private I18n ui = new I18n();
	public ExchangeRequisitionSheetModifyWin() {
		_init();
	}

	private void _init() {
		setWidth100();
		setHeight100();
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		centerInPage();
		setAlign(Alignment.CENTER);
		setTitle(ui.getM().modifyFormTitle(ui.getM().mod_exchangeRequisition_name()));//修改交换申请单
		VLayout container = new VLayout();
		container.setWidth100();
		container.setHeight100();
		container.setAlign(Alignment.CENTER);
		TabSetPanel tabSet=new TabSetPanel();
		container.addMember(tabSet);
		addItem(container);
		SheetModelLocator model = SheetModelLocator.getInstance();
		model.parentWindow=this;
	}
	
	

}
