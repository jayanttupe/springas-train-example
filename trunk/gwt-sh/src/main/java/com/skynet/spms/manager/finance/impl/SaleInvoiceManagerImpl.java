package com.skynet.spms.manager.finance.impl;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.finance.SaleInvoiceManager;
import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.persistence.entity.financeService.buySettleAccounts.PayApplyTable;
import com.skynet.spms.persistence.entity.financeService.salesSettleAccounts.SaleInvoice;

@Service
@Transactional
public class SaleInvoiceManagerImpl  extends CommonManagerImpl<SaleInvoice>
		implements SaleInvoiceManager {
	
	private Logger log=LoggerFactory.getLogger(SaleInvoiceManagerImpl.class);

	@Override
	public List<SaleInvoice> queryByCriteriaList(List criList) {
		// TODO Auto-generated method stub
		Query query = CriteriaConverter.convertCriteriaToQuery(getSession(), criList, SaleInvoice.class);
		return query.list();
	}

}
