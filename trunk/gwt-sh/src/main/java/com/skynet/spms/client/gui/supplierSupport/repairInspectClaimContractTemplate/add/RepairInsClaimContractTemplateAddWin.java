package com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.add;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 
 * @author tqc
 * 
 */
public class RepairInsClaimContractTemplateAddWin extends Window {
	
	private I18n ui = new I18n();

	public RepairInsClaimContractTemplateAddWin() {
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
		setTitle(ui.getB().addFormTitle(ui.getM().mod_repairContract_name()));
		VLayout container = new VLayout();
		container.setWidth100();
		container.setHeight100();
		container.setAlign(Alignment.CENTER);
		ContractTabSet tabSet=new ContractTabSet();
		container.addMember(tabSet);
		addItem(container);
	}
	
	

}
