package com.skynet.spms.manager.finance;

import java.util.List;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.financeService.salesSettleAccounts.InvoiceApplyTable;

public interface InvoiceApplyManager extends CommonManager<InvoiceApplyTable> {

	List<InvoiceApplyTable> queryByCriteriaList(List criList);
	
	List<InvoiceApplyTable> queryForInvoice(String invoiceMode);

}
