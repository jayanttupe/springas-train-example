package com.skynet.spms.manager.customerService.order.affirmRepaireOrder.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.order.affirmRepaireOrder.IAffirmRepaireOrderManager;
import com.skynet.spms.persistence.entity.customerService.order.affirmRepaireOrder.AffirmRepaireOrder;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 客户确认修理指令业务实现类
 * 
 * @author tqc
 * 
 */
@Service
@Transactional
public class AffirmRepaireOrderManager extends HibernateDaoSupport implements
		IAffirmRepaireOrderManager {
	/** 查询hql **/
	private final static String HQL = "select o from AffirmRepaireOrder o ";

	@Resource
	UUIDGeneral uuidGeneral;

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 添加客户确认修理指令
	 */
	public void addAffirmRepaireOrder(AffirmRepaireOrder o) {
		o.setCreateDate(new Date());
		o.setOrderNumber(uuidGeneral.getSequence("RAO"));
		getHibernateTemplate().save(o);
	}

	/**
	 * 更新客户确认修理指令
	 */
	public AffirmRepaireOrder updateAffirmRepaireOrder(
			Map<String, Object> newValues, String itemID) {
		AffirmRepaireOrder o = getAffirmRepaireOrderById(itemID);
		BeanPropUtil.fillEntityWithMap(o, newValues);
		getHibernateTemplate().update(o);
		return o;
	}

	/**
	 * 删除客户确认修理指令
	 */
	public void deleteAffirmRepaireOrder(String itemID) {
		String hql = "update AffirmRepaireOrder set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

	/**
	 * 根据主键查询客户确认修理指令
	 */
	public AffirmRepaireOrder getAffirmRepaireOrderById(String sheetId) {
		return (AffirmRepaireOrder) getSession().get(AffirmRepaireOrder.class,
				sheetId);
	}

	/**
	 * 分页查询客户确认修理指令
	 */
	public List<AffirmRepaireOrder> queryAffirmRepaireOrderList(int startRow,
			int endRow) {
		String hql = HQL + " where o.deleted=false";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

}
