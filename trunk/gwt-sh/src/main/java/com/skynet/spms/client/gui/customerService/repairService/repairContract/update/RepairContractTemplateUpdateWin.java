package com.skynet.spms.client.gui.customerService.repairService.repairContract.update;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.customerService.repairService.repairContract.ContractModelLocator;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 送修合同修改窗体
 * 
 * @author tqc
 * 
 */
public class RepairContractTemplateUpdateWin extends Window {
	
	public ContractModelLocator model = ContractModelLocator.getInstance();
	
	private I18n ui=new I18n();
	
	public RepairContractTemplateUpdateWin() {
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
		setTitle(ui.getRepairModule().modify_repari_contract());
		VLayout container = new VLayout();
		container.setWidth100();
		container.setHeight100();
		container.setAlign(Alignment.CENTER);
		ContractTabSet tabSet=new ContractTabSet();
		container.addMember(tabSet);
		addItem(container);
	}
	
	

}
