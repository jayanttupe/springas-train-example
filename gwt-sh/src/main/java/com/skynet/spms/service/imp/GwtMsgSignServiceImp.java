package com.skynet.spms.service.imp;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.skynet.spms.instmsg.UserMsgSign;
import com.skynet.spms.service.GwtMsgSignService;

@Component("gwtMsgSignService")
public class GwtMsgSignServiceImp implements GwtMsgSignService{
	private Logger log = LoggerFactory.getLogger(GwtMsgSignServiceImp.class);

	private ConcurrentMap<String, Set<String>> queueMap = new ConcurrentHashMap<String, Set<String>>();

	@Override
	public boolean checkIsMsgReceive(String userName) {


		return queueMap.remove(userName) != null;
	}

	@Override
	public void addMessage(UserMsgSign msgSign) {
		Set<String> typeSet = new HashSet<String>();

		Set<String> newSet = queueMap.putIfAbsent(msgSign.getToUser(), typeSet);
		if (newSet != null) {
			newSet.add(msgSign.getMsgType());
		} else {
			typeSet.add(msgSign.getMsgType());
		}
	}
}
