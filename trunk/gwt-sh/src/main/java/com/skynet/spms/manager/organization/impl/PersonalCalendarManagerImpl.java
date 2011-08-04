package com.skynet.spms.manager.organization.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.client.entity.EventInfo;
import com.skynet.spms.manager.CommonDao;
import com.skynet.spms.manager.organization.PersonalCalenderManager;
import com.skynet.spms.persistence.entity.organization.userInformation.PersonalCalendar;
import com.skynet.spms.persistence.entity.organization.userInformation.User;

@Service
@Transactional
public class PersonalCalendarManagerImpl implements PersonalCalenderManager{

	@Autowired
	private CommonDao commDao;
	
	@Override
	public void saveCalendar(PersonalCalendar calendar,String userName){
		User user=(User)commDao.getSession()
		.createQuery("from User where username=:name")
		.setString("name", "sale_0")
		.uniqueResult();
		
		calendar.setUser(user);
		
		commDao.getSession().save(calendar);
	}
	
	@Override
	public void deleteCalendar(String id){
		
		commDao.deleteEntity(id, PersonalCalendar.class);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<PersonalCalendar> getEventListByUser(String userName){
		
		return commDao.getSession()
		 .createQuery("select cal from PersonalCalendar cal " +
		 		" join cal.user m_user where m_user.username = :name ")
		.setString("name", userName)
		.list();
	}

	@Override
	public void updateCalendar(EventInfo info) {
		String id=info.getId();
		
		PersonalCalendar cal=(PersonalCalendar) commDao.queryById(id, PersonalCalendar.class);
		
		cal.updateByEvent(info);
		
		commDao.getSession().flush();
	}
	
	@Override
	public PersonalCalendar updateCalendar(Map<String, Object> newValues,
			String id) {
		return (PersonalCalendar) commDao.updateEntity(newValues, id, PersonalCalendar.class);
	}
}
