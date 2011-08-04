package com.skynet.spms.manager.finance.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.finance.PurposeVoucherManager;
import com.skynet.spms.manager.helper.CriteriaConverter;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.persistence.entity.financeService.purposeVoucher.PurposeVoucher;
import com.skynet.spms.persistence.entity.spmsdd.PurposeVoucherType;

@Service
@Transactional
public class PurposeVoucherManagerImpl extends CommonManagerImpl<PurposeVoucher> implements
		PurposeVoucherManager {

	private Logger log=LoggerFactory.getLogger(PurposeVoucherManagerImpl.class);

	@Override
	public PurposeVoucher saveOrUpdate(PurposeVoucher purposeVoucher) {
		// TODO Auto-generated method stub
		getSession().saveOrUpdate(purposeVoucher);
		return purposeVoucher;
	}

	@Override
	public List<PurposeVoucher> listAllPurposeVouchers() {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(PurposeVoucher.class);
		criteria.add(Restrictions.eq("deleted", false))
				.add(Restrictions.eq("purposeVoucherType", PurposeVoucherType.pay));
		return criteria.list();
	}

	@Override
	public List<PurposeVoucher> listPurposeVouchersByMode(String mode) {
		// TODO Auto-generated method stub
		Criteria criteria = getSession().createCriteria(PurposeVoucher.class);
		if("pay".equals(mode))
		   criteria.add(Restrictions.eq("purposeVoucherType", PurposeVoucherType.pay));
		else if("gathering".equals(mode))
			criteria.add(Restrictions.eq("purposeVoucherType", PurposeVoucherType.gathering));
		return criteria.list();
	}

	@Override
	public List<PurposeVoucher> queryByCriteriaList(List criList) {
		// TODO Auto-generated method stub
		Query query = CriteriaConverter.convertCriteriaToQuery(getSession(), criList, PurposeVoucher.class);
		return query.list();
	}

}
