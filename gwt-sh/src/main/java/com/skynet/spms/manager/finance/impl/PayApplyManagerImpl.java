package com.skynet.spms.manager.finance.impl;

import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.finance.PayApplyManager;
import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.persistence.entity.financeService.buySettleAccounts.PayApplyTable;

@Service
@Transactional
public class PayApplyManagerImpl extends CommonManagerImpl<PayApplyTable>
		implements PayApplyManager {
	
	private Logger log=LoggerFactory.getLogger(PayApplyManagerImpl.class);

	@Override
	public List<PayApplyTable> queryByCriteriaList(List criList) {
		// TODO Auto-generated method stub
		Query query = CriteriaConverter.convertCriteriaToQuery(getSession(), criList, PayApplyTable.class);
		return query.list();
	}
	

}
