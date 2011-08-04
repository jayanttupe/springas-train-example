package com.skynet.spms.manager.base.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.CommonDao;
import com.skynet.spms.manager.base.RemarkManager;
import com.skynet.spms.persistence.entity.base.remarkEntity.RemarkTextEntity;


@Service
@Transactional
public class RemarkManagerImpl implements RemarkManager {
	
	@Autowired
	private CommonDao commDao;
	
	@Override
	public List<RemarkTextEntity> getRemarksForBusiness(String businessKey,String tableName){
		
		return commDao.getClearSession()
			.createQuery("from RemarkTextEntity" +
					" where relInfo.relBusKey = :key and relInfo.tableName= :tab " +
					" order by  createDate desc ")
			.setString("key",businessKey)
			.setString("tab",tableName)
			.list();
		
	}
	
	@Override
	public void addRemarkForBusiness(RemarkTextEntity entity){
		
		entity.setCreateDate(new Date());
		if(StringUtils.isBlank(entity.getFromUser())){
			entity.setFromUser(GwtActionHelper.getCurrUser());
		}
		
		commDao.getSession().save(entity);
	}

}
