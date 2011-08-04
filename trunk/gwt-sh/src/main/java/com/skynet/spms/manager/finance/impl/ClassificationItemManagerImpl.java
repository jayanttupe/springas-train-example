package com.skynet.spms.manager.finance.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.finance.ClassificationItemManager;
import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.persistence.entity.financeService.purposeVoucher.ClassificationItem;
import com.skynet.spms.persistence.entity.financeService.purposeVoucher.Subjects;


@Service
@Transactional
public class ClassificationItemManagerImpl extends CommonManagerImpl<ClassificationItem>
		implements ClassificationItemManager {

	@Override
	public void insertSubjects(Subjects subject) {
		getSession().save(subject);
		
	}
	@Override
	public List<Subjects> queryAllSubjects(){
		Session session = getSession();
		session.disableFilter("active");
		Criteria criteria = session.createCriteria(Subjects.class);
		List<Subjects> list = criteria.list();
		
		return list;
	}
	@Override
	public Subjects querySubjectsById(String subjectId){
		return (Subjects)getSession().get(Subjects.class, subjectId);
	}
	@Override
	public Subjects querySubjectsByName(String subjectName) {
		// TODO Auto-generated method stub
		String hql = "from Subjects sub where sub.subjectName=? ";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, subjectName);
		return (Subjects)query.list().get(0);
	}
}
