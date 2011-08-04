package com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.helper.SqlHelperTool;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness.NonconformingReportManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.nonconformingReport.NonconformingReport;
import com.skynet.spms.service.UUIDGeneral;

@Service
@Transactional
public class NonconformingReportManagerImpl extends CommonManagerImpl<NonconformingReport> implements NonconformingReportManager{

	@Autowired
	UUIDGeneral uUIDGeneral;
	
	@Override
	public List<NonconformingReport> getNonconformingReport(Map values, int startRow,
			int endRow) {
		Criteria criteria= getSession().createCriteria(NonconformingReport.class);
		criteria.add(Restrictions.eq("deleted", false));
		SqlHelperTool.createCriteria(values, criteria, NonconformingReport.class, null);
		if(endRow>0){
			criteria.setFirstResult(startRow).setMaxResults(endRow-startRow);
		}

		return criteria.list();
	}
	
	@Override
	public NonconformingReport saveNonconformingReport(NonconformingReport nonconformingReport)
	{
		Session session = getSession();
		if (nonconformingReport.getNonconformingReportNumber() == null 
				|| nonconformingReport.getNonconformingReportNumber().equals("")
				|| nonconformingReport.getNonconformingReportNumber().equals("业务编号自动生成")) {
			// 取得下一个编号
			String curNO = uUIDGeneral.getSequence("NON");
			// 收料单编号
			nonconformingReport.setNonconformingReportNumber(curNO);
		}
		nonconformingReport.setCreateBy(GwtActionHelper.getCurrUser());
		nonconformingReport.setCreateDate(new Date());
		// 保存收料单基本信息
		session.saveOrUpdate(nonconformingReport);
		
		return nonconformingReport;
	}
}