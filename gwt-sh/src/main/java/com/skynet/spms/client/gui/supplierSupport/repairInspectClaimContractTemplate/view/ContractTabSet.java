package com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.view;

import com.skynet.spms.client.gui.base.i18n.I18n;
import com.skynet.spms.client.gui.contractManagement.tag.TagEnum;
import com.skynet.spms.client.gui.customerService.commonui.AttachmentCanvas;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressForm;
import com.skynet.spms.client.gui.customerService.commonui.BaseAddressModel;
import com.skynet.spms.client.gui.customerService.commonui.ContractProvisionPanel;
import com.skynet.spms.client.gui.supplierSupport.repairInspectClaimContractTemplate.model.ModelLocator;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class ContractTabSet extends TabSet {

	private ModelLocator model = ModelLocator.getInstance();
	
	private I18n ui=new I18n();

	public ContractTabSet() {
		_init();
	}

	@SuppressWarnings("static-access")
	public void _init() {
		Tab baseTab = new Tab(ui.getRepairModule().contract_base_info_title());
		ContractBaseForm baseForm=new ContractBaseForm();
		baseTab.setPane(baseForm);

		BaseAddressModel.getInstance().addr_sheetId = model.viewDetailContractID;
		Tab addressTab = new Tab(ui.getRepairModule().address_info_title());
		addressTab.setPane(new BaseAddressForm(BaseAddressForm.Type.view));

		Tab provisionTab = new Tab(ui.getRepairModule().contract_provision_title());
		provisionTab.setPane(new ContractProvisionPanel(baseForm.form,TagEnum.RepairInspectClaimContractTemplate));

		Tab attachmentTab = new Tab(ui.getRepairModule().attachment_title());
		attachmentTab.setPane(new AttachmentCanvas(model.viewDetailContractID));

		Tab inspectOutlayRecordTab = new Tab(ui.getRepairModule().check_cast());
		inspectOutlayRecordTab.setPane(new InspectOutlayRecordForm());

		Tab repairQuoteRecordTab = new Tab(ui.getRepairModule().fix_record());
		repairQuoteRecordTab.setPane(new RepairQuoteRecordForm());

		setTabs(baseTab, addressTab, provisionTab, attachmentTab,
				inspectOutlayRecordTab, repairQuoteRecordTab);

	}

}
