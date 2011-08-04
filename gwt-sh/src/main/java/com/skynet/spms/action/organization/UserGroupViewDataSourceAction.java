/**
 * File: 
 * copyright  2010-2011   Shanghai  Skynetsoft, INC.All Rights Reserved.
 * Date        Author      Changes
 * 2011-3-24   黄贇	    
 */

package com.skynet.spms.action.organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.organization.UserManager;
import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.web.form.UserGroupForm;


/**
 * Description
 * @see com.skynet.spms.action.organization.UserGroupViewDataSourceAction
 * @author 黄贇
 * @version 0.5
 */

@Component
public class UserGroupViewDataSourceAction implements DataSourceAction<UserGroupForm> {

	@Autowired
	private UserManager userManager;
	/**
	 * Description
	 * @see 数据源绑定方法
	 * @param 
	 * @return
	 * @throws
	 */
	@Override
	public String[] getBindDsName() {
		// TODO Auto-generated method stub
		return new String[] { "usergroupview_dataSource" };
	}
	
	/**
	 * Description
	 * @see 将用户添加到用户组
	 * @param UserGroupForm
	 * @return
	 * @throws
	 */
	@Override
	public void insert(UserGroupForm userGroupForm) {
		// TODO Auto-generated method stub

		
		String userId = userGroupForm.getUser().getId();
		String groupId = userGroupForm.getUserGroup().getId();
		//userManager.addOrRemoveUserFromGroup(userId, groupId);

	
	}
	
	/**
	 * Description
	 * @see 修改
	 * @param Map
	 * @param String
	 * @return UserGroupForm
	 * @throws
	 */
	@Override
	public UserGroupForm update(Map<String, Object> map, String userId) {		
		return null;
	}
	
	/**
	 * Description
	 * @see 删除
	 * @param String
	 * @return 
	 * @throws
	 */
	@Override
	public void delete(String userGroupId) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Description
	 * @see 与用户组关联查询
	 * @param Map
	 * @param int
	 * @param int
	 * @return List
	 * @throws
	 */
	@Override
	public List<UserGroupForm> doQuery(Map<String, Object> map, int startRow,
			int endRow) {
		// TODO Auto-generated method stub
		List<User> userList = null;//userManager.listUserByRestrictions(map, 0, -1);
		List<UserGroupForm> list = new ArrayList();
		for (User user : userList) {
			UserGroupForm userGroupForm = new UserGroupForm();
			userGroupForm.setUser(user);
			list.add(userGroupForm);
		}
		return list;
	}
	
	/**
	 * Description
	 * @see 全查询
	 * @param int
	 * @param int
	 * @return List
	 * @throws
	 */

	@Override
	public List<UserGroupForm> getList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		List list = new ArrayList();
		List<User> userList = userManager.list(0, -1, User.class);
		for (User user : userList) {
			UserGroupForm userGroupForm = new UserGroupForm();
			userGroupForm.setUser(user);
			list.add(userGroupForm);
		}
		return list;
	}

}
