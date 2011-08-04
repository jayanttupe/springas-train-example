package com.skynet.spms.client.gui.finance.common;

import java.util.Map;

import com.skynet.spms.client.PanelFactory;
import com.skynet.spms.client.gui.base.ModulePlug;
import com.skynet.spms.client.gui.finance.apply.InvoiceApplyPanel;
import com.skynet.spms.client.gui.finance.apply.PayApplyPanel;
import com.skynet.spms.client.gui.finance.invoice.PurchaseInvoicePanel;
import com.skynet.spms.client.gui.finance.invoice.SaleInvoicePanel;
import com.skynet.spms.client.gui.finance.purposevoucher.PuroseVoucherPayPanel;
import com.skynet.spms.client.gui.finance.purposevoucher.PurposeVoucherPanel;

public class FinancePlugin implements ModulePlug {

	@Override
	public void plug(Map<String, PanelFactory> map) {
		// TODO Auto-generated method stub
		map.put("account.applyManager.payApplyManager", new PayApplyPanel.Factory());
		map.put("account.applyManager.invoiceApplyManager", new InvoiceApplyPanel.Factory());
		map.put("account.invoiceManager.collectionInvoiceManager", new SaleInvoicePanel.Factory());
		map.put("account.invoiceManager.payInvoiceManager", new PurchaseInvoicePanel.Factory());
		map.put("account.certificateManager.payCertificateManager", new PurposeVoucherPanel.Factory("pay"));
		map.put("account.certificateManager.collectionCertificateManager", new PuroseVoucherPayPanel.Factory("gathering"));
	}

}
