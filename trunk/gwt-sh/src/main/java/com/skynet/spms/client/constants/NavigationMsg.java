package com.skynet.spms.client.constants;

import com.google.gwt.i18n.client.Messages;

public interface NavigationMsg extends Messages {

	String featureDesc(String id,String desc,String tableName);
	
	String userName(String name);

	String getLogoutMsg(String userName);

	String getUserDep(String department);

	String getUserDuty(String duty);
	
}
