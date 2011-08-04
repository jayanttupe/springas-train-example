package com.skynet.spms.service;

import com.skynet.spms.instmsg.UserMsgSign;

public interface GwtMsgSignService {

	boolean checkIsMsgReceive(String userName);

	void addMessage(UserMsgSign msgSign);

}
