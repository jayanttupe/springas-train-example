package com.skynet.spms.manager.supplierSupport.procurement.PriceComparisonsReport.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.priceComparisonsReport.PriceComparisonsSheetReport;
import com.skynet.spms.manager.supplierSupport.procurement.PriceComparisonsReport.IPriceComparisonsSheetReportManager;
/**
 * 采购比价单
 * @author  lining
 *
 */
@Service
@Transactional
public class PriceComparisonsSheetReportManager extends HibernateDaoSupport
		implements IPriceComparisonsSheetReportManager {

	private final static String HQL = "select o from PriceComparisonsSheetReport o ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	public void addPriceComparisonsSheetReport(PriceComparisonsSheetReport o) {
		getHibernateTemplate().save(o);
	}

	public PriceComparisonsSheetReport updatePriceComparisonsSheetReport(
			Map<String, Object> newValues, String itemID) {
		PriceComparisonsSheetReport o=new PriceComparisonsSheetReport();
		BeanPropUtil.fillEntityWithMap(o, newValues);
		getHibernateTemplate().update(o);
		return o;
	}

	public void deletePriceComparisonsSheetReport(String itemID) {
		String hql = "update PriceComparisonsSheetReport set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	public PriceComparisonsSheetReport getPriceComparisonsSheetReportById(String sheetId) {
		return (PriceComparisonsSheetReport) getSession().get(
				PriceComparisonsSheetReport.class, sheetId);
	}

	public List<PriceComparisonsSheetReport> queryPriceComparisonsSheetReportList(
			int startRow, int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

}
