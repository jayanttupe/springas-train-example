package com.skynet.spms.action.organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.UserInfoManager;
import com.skynet.spms.persistence.entity.organization.userInformation.UserInformation;

@Component
public class UserInfoAction implements DataSourceAction<UserInformation> {

	private Logger log=LoggerFactory.getLogger(UserInfoAction.class);
	
	@Autowired
	private UserInfoManager userInfoManager;
	
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[]{"userInformation_dataSource"};
	}

	@Override
	public void insert(UserInformation item) {
		log.info("=============================UserInfoAction.insert()");
		
	}

	@Override
	public UserInformation update(Map<String, Object> newValues, String itemID) {
		log.info("=============================UserInfoAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		UserInformation userInfo = userInfoManager.updateUserInfo(newValues, itemID);
		return userInfo;
	}

	@Override
	public void delete(String itemID) {
		log.info("=============================UserInfoAction.delete()");
		
	}

	@Override
	public List<UserInformation> doQuery(Map<String, Object> map,
			int startRow, int endRow) {
		log.info("=============================UserInfoAction.doQuery()");
		for(Map.Entry<String, Object> entry : map.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		
		return userInfoManager.queryByProps(map);
	}

	@Override
	public List<UserInformation> getList(int startRow, int endRow) {
		log.info("=============================UserInfoAction.getList()");
		List<UserInformation> list = new ArrayList<UserInformation>();
		return list;
	}
}
