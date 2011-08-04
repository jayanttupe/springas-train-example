package com.skynet.spms.jbpm.assignment;

import java.util.List;

import org.jbpm.api.identity.User;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.springframework.stereotype.Component;

import com.skynet.spms.jbpm.dao.WfGroupUserDao;

@Component("singleUserAssign")
public class SingleUserAssign implements AssignmentHandler{

/**
	 * 
	 */
	private static final long serialVersionUID = -6997336315165093570L;

	private WfGroupUserDao getWfDao() {
		EnvironmentImpl env=EnvironmentImpl.getCurrent();
		
		return env.get(WfGroupUserDao.class);
	}
	
	
	private String groupName;
	
	public void setGroupName(String name){
		this.groupName=name;
	}
	
	@Override
	public void assign(Assignable assignable, OpenExecution execution)
			throws Exception {
		
		List<User> userList=getWfDao().getUserNameByGroup(groupName);
		if(userList.size()==1){
			assignable.setAssignee(userList.get(0).getId());
		}else if(userList.size()>1){
			for(User user:userList){
				assignable.addCandidateUser(user.getId());
			}
		}else{
			assignable.setAssignee(groupName);
		}
		
	}
	
	

}
