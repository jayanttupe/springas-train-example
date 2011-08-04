package com.skynet.spms.manager.organization.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.organization.IDCardManager;
import com.skynet.spms.manager.organization.UserInfoManager;
import com.skynet.spms.persistence.entity.organization.userInformation.IDCard;
import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.persistence.entity.organization.userInformation.UserInformation;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.basicInformation.BasicInformation;


@Service
@Transactional
public class IDCardManagerImpl extends CommonManagerImpl<IDCard> implements IDCardManager{

	private Logger log = LoggerFactory.getLogger(IDCardManagerImpl.class);

	@Override
	public List<IDCard> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(IDCard.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<IDCard> list = criteria.list();
		return list;
	}

	

	

}
