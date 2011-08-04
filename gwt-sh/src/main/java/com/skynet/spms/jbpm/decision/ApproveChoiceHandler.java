package com.skynet.spms.jbpm.decision;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.entity.CommonApproveParam;
import com.skynet.spms.client.gui.base.WorkFlowBusinessType;
import com.skynet.spms.persistence.entity.organization.userInformation.UserQuota;
import com.skynet.spms.persistence.entity.organization.userRole.Role;

@Component("approveChoiceHandler")
public class ApproveChoiceHandler implements DecisionHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7506825868784445073L;

	private Session getSession() {

		EnvironmentImpl env = EnvironmentImpl.getCurrent();
		SessionFactory sessionFactory = env.get(SessionFactory.class);
		return sessionFactory.getCurrentSession();

	}

	private String groupName;

	public void setGroupName(String name) {
		this.groupName = name;
	}

	private int getUserRight() {

		Role role = (Role) getSession()
				.createQuery("from Role where roleName = :name")
				.setString("name", groupName).uniqueResult();

		return role.getApprovalQuota();

	}

	@Override
	public String decide(OpenExecution execution) {
		CommonApproveParam param = (CommonApproveParam) execution
				.getVariable("param");
		String actName=execution.getActivity().getName();
		
		if(actName.equals("checkManagerRight")&&
				param.getBusinessType().equals(WorkFlowBusinessType.SALESCONTRACT)){
			return "vp"; 
		}
		
		float amount = param.getAmount();

		int userRight = getUserRight();

		if (amount < userRight) {
			return "locale";
		} else {
			return "upperGroup";
		}
	}

}
