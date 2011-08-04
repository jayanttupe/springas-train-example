package com.skynet.spms.manager.organization.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.organization.UserInfoManager;
import com.skynet.spms.persistence.entity.organization.userInformation.UserInformation;


@Service
@Transactional
public class UserInfoManagerImpl extends CommonManagerImpl<UserInformation> implements UserInfoManager{

	private Logger log = LoggerFactory.getLogger(UserInfoManagerImpl.class);

	@Override
	public List<UserInformation> queryByProps(Map<String, Object> values) {
		Criteria criteria = getSession().createCriteria(UserInformation.class);
		for(Map.Entry<String, Object> entry : values.entrySet()){
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		List<UserInformation> list = criteria.list();
		return list;
	}
	
	@Override
	public UserInformation updateUserInfo(Map<String,Object> newValues,String itemID){
		long sex = (Long)newValues.get("sex");
		String mobile = (String)newValues.get("mobile");
		String fax = (String)newValues.get("fax");
		String telephone = (String)newValues.get("telephone");
		String contactAddresses = (String)newValues.get("contactAddresses");
		String remark = (String)newValues.get("remark");
		
		UserInformation userInfo = (UserInformation)getSession().get(UserInformation.class, itemID);
		userInfo.setSex((int)sex);
		userInfo.setMobile(mobile);
		userInfo.setTelephone(telephone);
		userInfo.setFax(fax);
		userInfo.setContactAddresses(contactAddresses);
		userInfo.setRemark(remark);
		
		String stockAccessCode = "SHA1:0000000000000000000000000000000000000000000000000000000000000001";
		userInfo.setStockAccessCode(stockAccessCode);
		
		getSession().saveOrUpdate(userInfo);
		return userInfo;
	}

	

}
