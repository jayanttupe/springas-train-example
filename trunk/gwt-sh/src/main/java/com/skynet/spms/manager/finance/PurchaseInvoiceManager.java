package com.skynet.spms.manager.finance;

import java.util.List;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.financeService.buySettleAccounts.PurchaseInvoice;

public interface PurchaseInvoiceManager extends CommonManager<PurchaseInvoice> {

	List<PurchaseInvoice> queryByCriteriaList(List criList);

}
