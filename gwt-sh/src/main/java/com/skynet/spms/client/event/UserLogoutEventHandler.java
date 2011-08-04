package com.skynet.spms.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface UserLogoutEventHandler extends EventHandler{

	public void onUserLogout(UserLogoutEvent event);
}
