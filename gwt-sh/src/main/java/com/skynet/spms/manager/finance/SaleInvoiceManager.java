package com.skynet.spms.manager.finance;

import java.util.List;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.financeService.salesSettleAccounts.SaleInvoice;

public interface SaleInvoiceManager  extends CommonManager<SaleInvoice> {

	List<SaleInvoice> queryByCriteriaList(List criList);

}

