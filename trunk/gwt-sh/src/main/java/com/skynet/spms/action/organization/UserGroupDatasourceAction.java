package com.skynet.spms.action.organization;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.UserGroupManager;
import com.skynet.spms.persistence.entity.organization.userGroup.UserGroup;

@Component
public class UserGroupDatasourceAction implements DataSourceAction<UserGroup> {

	private Logger log=LoggerFactory.getLogger(UserGroupDatasourceAction.class);
	
	@Autowired
	private UserGroupManager userGroupManager;
	@Override
	public String[] getBindDsName() {
		return new String[]{"userGroup_dataSource"};
	}

	@Override
	public void insert(UserGroup item) {
		log.info("=============================UserGroupDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("groupName : " + item.getGroupName());
		log.info("description : " + item.getDescription());
		userGroupManager.insertEntity(item);
	}

	@Override
	public UserGroup update(Map<String,Object> newValues, String itemID) {
		log.info("=============================UserGroupDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
			if(entry.getValue() != null){
				log.info(entry.getKey() + " Type : " + entry.getValue().getClass());
			}
		}
		List<String> userIds = (List<String>)newValues.remove("userIds");
		List<String> roleIds = (List<String>)newValues.remove("roleIds");
		UserGroup userGroup = null;
		if(userIds != null){
			String groupId = (String)newValues.get("id");
			userGroup = userGroupManager.addGroupUsers(groupId, userIds);
		}else if(roleIds != null){
			String groupId = (String)newValues.get("id");
			userGroup = userGroupManager.addRolesToGroupUsers(groupId, roleIds);
		}
		else{
			userGroup = (UserGroup)userGroupManager.updateEntity(newValues, itemID, UserGroup.class);
		}
		return userGroup;
	}

	@Override
	public void delete(String itemID) {
		log.info("=============================UserGroupDataSourceAction.delete()");
		log.info("deleted Id : " + itemID);
		userGroupManager.deleteUserGroup(itemID);
		
	}

	@Override
	public List<UserGroup> doQuery(Map<String,Object> values, int startRow, int endRow) {
		log.info("=============================UserGroupDataSourceAction.doQuery()");
		Object filter = values.remove("filter");
		List<UserGroup> list = null;
		if(filter != null){
			list = userGroupManager.queryByFilter(values);
		}
		return list;
	}

	@Override
	public List<UserGroup> getList(int startRow, int endRow) {
		List<UserGroup> ugList = userGroupManager.list(startRow, endRow, UserGroup.class); 
		return ugList;
	}

}
