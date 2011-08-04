package com.skynet.spms.instmsg;

import java.util.List;
import java.util.Locale;

import com.skynet.spms.client.entity.MsgEntity;



public interface InstMsgManager {


	List<MsgEntity> getMsgList(String userName,Locale locale);

	

}