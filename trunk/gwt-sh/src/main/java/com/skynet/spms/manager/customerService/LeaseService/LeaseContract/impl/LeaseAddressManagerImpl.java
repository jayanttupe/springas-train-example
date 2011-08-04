package com.skynet.spms.manager.customerService.LeaseService.LeaseContract.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.manager.customerService.LeaseService.LeaseContract.LeaseAddressManager;
import com.skynet.spms.persistence.entity.base.AddressInfo;

/**
 * 租赁合同地址实现类
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
@Service
@Transactional
public class LeaseAddressManagerImpl extends HibernateDaoSupport implements
		LeaseAddressManager {
	String hql = "select e from AddressInfo e ";

	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	/**
	 * 
	 * 添加合同地址信息
	 * 
	 * @param 合同地址信息对象
	 * @return void
	 */
	public void addAddress(AddressInfo form) {
		form.setCreateDate(new Date());
		getHibernateTemplate().save(form);
	}

	/**
	 * 根据ID查询一条记录
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public AddressInfo getById(String uuid) {
		return getHibernateTemplate().get(AddressInfo.class, uuid);
	}

	/**
	 * 
	 * 修改合同地址信息
	 * 
	 * @param 新数据对象
	 * @param 合同地址对象ID
	 * @return 合同地址对象
	 */
	@Override
	public AddressInfo update(Map<String, Object> newValues, String itemID) {
		AddressInfo entity = (AddressInfo) getSession().get(AddressInfo.class,
				itemID);
		BeanPropUtil.fillEntityWithMap(entity, newValues);
		if (itemID == null) {
			addAddress(entity);
		} else {
			getHibernateTemplate().update(entity);
		}
		return entity;
	}

	/**
	 * 调用此方法是传入的values的key只能是rqId
	 * 
	 * @param 新数据对象
	 * @param 首页
	 * @param 尾页
	 * @return 合同地址对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AddressInfo> doQuery(Map<String, Object> values, int startRow,
			int endRow) {
		Set<String> keys = values.keySet();
		String hql = this.hql + " where e.deleted=false";
		for (String key : keys) {
			Object obj = values.get(key);
			if (obj instanceof String) {
				hql += " and " + key + "='" + values.get(key) + "'";
			} else {
				hql += " and " + key + "=" + values.get(key);
			}
		}
		hql += " order by e.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		return query.list();
	}

	/**
	 * 分页查询
	 * 
	 * @param 首页
	 * @param 尾页
	 * @return 对象集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AddressInfo> getList(int startRow, int endRow) {
		String hql = this.hql
				+ " where e.deleted=false order by e.createDate desc";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(startRow);
		query.setMaxResults(endRow);
		System.out.println("getList 执行sql" + hql);
		return query.list();
	}

	/**
	 * 删除一条记录，这里的删除实际上只是更新操作
	 * 
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
		String hql = "update AddressInfo set deleted=true where id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, itemID);
		query.executeUpdate();
	}

}
