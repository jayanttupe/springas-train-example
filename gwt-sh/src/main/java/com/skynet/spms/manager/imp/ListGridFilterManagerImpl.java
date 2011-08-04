package com.skynet.spms.manager.imp;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.helper.CriteriaConverter;
/**
 * 用于表格的Filter过滤功能实现
 * @author fl
 *
 * @param <T>
 */
@Service
@Transactional
public class ListGridFilterManagerImpl<T> extends HibernateDaoSupport implements
		ListGridFilterManager<T> {
	
	@Resource(name = "sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	/**
	 * 过滤listGrid当中的数据
	 * 
	 * @param t
	 *            类型名
	 * @param clientCriteria
	 *            客户端的criteria
	 * @param startRow
	 * @param endRow
	 * @return 返回给类型名的对象集合
	 */
	@Override
	public List<T> doQueryFilter(Class<T> t, List clientCriteria, int startRow,
			int endRow) {
		Session session = getSession();
		session.enableFilter("active").setParameter("isDele", false);
		Query query = CriteriaConverter.convertCriteriaToQuery(session,
				clientCriteria, t);
		if (endRow > 0) {
			query.setFirstResult(startRow);
			query.setMaxResults(endRow - startRow);
		}
		List<T> list = query.list();
		return list;
	}

}
