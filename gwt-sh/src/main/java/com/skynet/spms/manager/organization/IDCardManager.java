package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.userInformation.IDCard;


public interface IDCardManager extends CommonManager<IDCard>{
	
	public List<IDCard> queryByProps(Map<String, Object> values);

}
