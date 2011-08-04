package com.skynet.spms.manager.finance.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.finance.InvoiceApplyManager;
import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.persistence.entity.financeService.buySettleAccounts.PayApplyTable;
import com.skynet.spms.persistence.entity.financeService.salesSettleAccounts.InvoiceApplyTable;

@Service
@Transactional
public class InvoiceApplyManagerImpl extends CommonManagerImpl<InvoiceApplyTable> implements
		InvoiceApplyManager {

	@Override
	public List<InvoiceApplyTable> queryByCriteriaList(List criList) {
		// TODO Auto-generated method stub
		Query query = CriteriaConverter.convertCriteriaToQuery(getSession(), criList, InvoiceApplyTable.class);
		return query.list();
	}

	@Override
	public List<InvoiceApplyTable> queryForInvoice(String invoiceMode) {
		// TODO Auto-generated method stub
		String hql ="from InvoiceApplyTable iat ";
		return null;
	}



}
