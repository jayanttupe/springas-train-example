package com.skynet.spms.manager.portal;

import java.util.Date;
import java.util.List;

import com.skynet.spms.persistence.entity.organization.userInformation.UserMessageRecord;

public interface UserMsgRecordManager {

	public List<UserMessageRecord> getUserMessages(String userName);

	public List<UserMessageRecord> getUserMessages(String userName,int start,int end);

	public List<UserMessageRecord> getNewUserMessages(Date date,String userName);
	
	void setReadSign(String msgID);

	void addMessage(String sendTo, String message);
}
