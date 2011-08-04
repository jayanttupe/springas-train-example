package com.skynet.spms.client.gui.supplierSupport.procurementOrder.procurementContract.view;

import com.skynet.spms.client.gui.customerService.commonui.BaseAddressForm;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
/**
 * 修改合同 面板1
 * @author gqr
 *
 */
public class ProcurementContractViewTabSet extends TabSet {

	public ProcurementContractViewTabSet() {
		_init();
	}

	public void _init() {
		Tab baseTab = new Tab("合同基本信息");
		baseTab.setPane(new ContractBaseViewForm());

		Tab addressTab = new Tab("地址信息");
		addressTab.setPane(new BaseAddressForm(BaseAddressForm.Type.view));

		Tab provisionTab = new Tab("合同条款");
		provisionTab.setPane(new ProvisionForm());

		Tab attachmentTab = new Tab("附件信息");
		attachmentTab.setPane(new AttachmentViewForm());
		setTabs(baseTab,addressTab, provisionTab, attachmentTab);
	}

}
