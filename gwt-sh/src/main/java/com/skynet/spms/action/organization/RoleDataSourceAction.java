package com.skynet.spms.action.organization;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.RoleManager;
import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.persistence.entity.organization.userRole.Role;

@Component
public class RoleDataSourceAction implements DataSourceAction<Role> {

	private Logger log=LoggerFactory.getLogger(RoleDataSourceAction.class);
	
	@Autowired
	private RoleManager roleManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"role_dataSource"};
	}

	@Override
	public void insert(Role item) {
		log.info("=============================RoleDataSourceAction.insert()");
		log.info("id : " + item.getId());
		log.info("roleName : " + item.getRoleName());
		log.info("RoleTitle_zh : " + item.getRoleTitle_zh());
		log.info("RoleTitle_en : " + item.getRoleTitle_en());
		log.info("ApprovalQuota : " + item.getApprovalQuota());
		roleManager.insertEntity(item);
	}

	@Override
	public Role update(Map<String, Object> newValues, String itemID) {
		log.info("=============================RoleDataSourceAction.update()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return roleManager.updateRole(newValues, itemID);
	}

	@Override
	public void delete(String itemID) {
		log.info("=============================RoleDataSourceAction.delete()");
		log.info("deleted Id : " + itemID);
		roleManager.deleteEntity(itemID, Role.class);
	}

	@Override
	public List<Role> doQuery(Map<String, Object> newValues, int startRow, int endRow) {
		log.info("=============================RoleDatasourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : newValues.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		
		Object filter = newValues.remove("filter");
		String userId = (String)newValues.remove("userId");
		List<Role> roleList = null;
		if(filter != null){
			roleList = roleManager.queryByFilter(newValues);
		}else if(userId != null){
			roleList = roleManager.queryByUserId(userId);
		}
		else{
			roleList = roleManager.queryByProps(newValues);
		}
		return roleList;
	}

	@Override
	public List<Role> getList(int startRow, int endRow) {
		return roleManager.list(startRow, endRow, Role.class);
	}

}
