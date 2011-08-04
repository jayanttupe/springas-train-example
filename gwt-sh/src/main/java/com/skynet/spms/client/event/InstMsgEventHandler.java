package com.skynet.spms.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface InstMsgEventHandler extends EventHandler{

	public void onReceiveInstMsg(InstMsgEvent event);
}
