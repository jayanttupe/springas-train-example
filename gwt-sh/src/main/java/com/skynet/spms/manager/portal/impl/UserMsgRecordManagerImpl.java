package com.skynet.spms.manager.portal.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.CommonDao;
import com.skynet.spms.manager.portal.UserMsgRecordManager;
import com.skynet.spms.persistence.entity.base.messageEntity.MessageEntity;
import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.persistence.entity.organization.userInformation.UserMessageRecord;

@Transactional
@Service
public class UserMsgRecordManagerImpl implements UserMsgRecordManager {

	@Autowired
	private CommonDao commDao;
	
	@Override
	public List<UserMessageRecord> getUserMessages(String userName) {
		Session session=commDao.getSession();
		
		List resultList=session.createQuery(
				"select record,msg " +
				" from User user join user.m_UserMessageRecord record join record.m_MessageEntity msg " +
				"where " +
				" user.username = :user and record.readed = false " +
				" order by record.createDate desc")		
		.setParameter("user", userName)
		.setMaxResults(20)		
		.list();
		
		List<UserMessageRecord> recordList = generUserMsgList(resultList);
		
		return recordList;
	}

	private List<UserMessageRecord> generUserMsgList(List resultList) {
		List<UserMessageRecord> recordList=new ArrayList<UserMessageRecord>();
		for(Object obj:resultList){
			Object[] valArray=(Object[])obj;
			
			UserMessageRecord record=(UserMessageRecord) valArray[0];
			MessageEntity msg=(MessageEntity)valArray[1];
			record.setM_MessageEntity(msg);
			recordList.add(record);
		}
		return recordList;
	}
	
	@Override
	public void setReadSign(String msgID){
		Session session=commDao.getSession();
		
		UserMessageRecord record=(UserMessageRecord) session.get(UserMessageRecord.class, msgID);
		record.setReaded(true);
		record.setReadDatetime(new Date());		
	}
	
	@Override
	public void addMessage(String sendToUser,String message){

		User user= (User) commDao.getSession()
			.createQuery("from User where username= :name")
			.setParameter("name", sendToUser)
			.uniqueResult();
		
		UserMessageRecord record=new UserMessageRecord();
		record.setReaded(false);
		MessageEntity msgEntity=new MessageEntity();
		msgEntity.setMessage(message);
		commDao.insertEntity(msgEntity);
		record.setM_MessageEntity(msgEntity);
		commDao.getSession().save(record);
		
		user.getM_UserMessageRecord().add(record);
		
	}

	@Override
	public List<UserMessageRecord> getNewUserMessages(Date date, String userName) {
		Session session=commDao.getSession();
		
		List resultList=session.createQuery(
				"select record,msg " +
				" from User user join user.m_UserMessageRecord record join record.m_MessageEntity msg " +
				"where " +
				" user.username = :user and " +
				" record.readed = false and " +
				" record.createDate > :date" +
				" order by record.createDate")		
		.setParameter("user", userName)
		.setParameter("date",date)
		.list();
		
		List<UserMessageRecord> recordList = generUserMsgList(resultList);
		
		return recordList;
	}

	@Override
	public List<UserMessageRecord> getUserMessages(String userName, int start,
			int end) {
		Session session=commDao.getSession();
		
		List resultList=session.createQuery(
				"select record,msg " +
				" from User user join user.m_UserMessageRecord record join record.m_MessageEntity msg " +
				"where " +
				" user.username = :user " +
				" order by record.createDate desc")		
		.setParameter("user", userName)		
		.setMaxResults(end)		
		.list();
		
		List<UserMessageRecord> recordList = generUserMsgList(resultList);
		
		return recordList.subList(start, recordList.size());		
	}

}
