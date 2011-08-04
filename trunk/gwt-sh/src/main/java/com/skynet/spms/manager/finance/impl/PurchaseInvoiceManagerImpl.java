package com.skynet.spms.manager.finance.impl;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.finance.PurchaseInvoiceManager;
import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.persistence.entity.financeService.buySettleAccounts.PayApplyTable;
import com.skynet.spms.persistence.entity.financeService.buySettleAccounts.PurchaseInvoice;

@Service
@Transactional
public class PurchaseInvoiceManagerImpl extends CommonManagerImpl<PurchaseInvoice>
		implements PurchaseInvoiceManager {
	
	private Logger log=LoggerFactory.getLogger(PurchaseInvoiceManagerImpl.class);

	@Override
	public List<PurchaseInvoice> queryByCriteriaList(List criList) {
		// TODO Auto-generated method stub
		Query query = CriteriaConverter.convertCriteriaToQuery(getSession(), criList, PurchaseInvoice.class);
		return query.list();
	}

}
