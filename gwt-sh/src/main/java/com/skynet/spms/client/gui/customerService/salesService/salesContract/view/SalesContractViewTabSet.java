package com.skynet.spms.client.gui.customerService.salesService.salesContract.view;

import com.skynet.spms.client.gui.customerService.commonui.BaseAddressForm;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class SalesContractViewTabSet extends TabSet {

	public SalesContractViewTabSet() {
		_init();
	}

	public void _init() {
		Tab baseTab = new Tab("合同基本信息");
		baseTab.setPane(new ContractBaseViewForm());

		Tab addressTab = new Tab("地址信息");
		BaseAddressForm addressForm=new BaseAddressForm(BaseAddressForm.Type.view);
		addressForm.setDisabled(true);
		addressTab.setPane(addressForm);

		Tab provisionTab = new Tab("合同条款");
		provisionTab.setPane(new ProvisionForm());

		Tab attachmentTab = new Tab("附件信息");
		attachmentTab.setPane(new AttachmentViewForm());
		setTabs(baseTab, addressTab, provisionTab, attachmentTab);
	}

}
