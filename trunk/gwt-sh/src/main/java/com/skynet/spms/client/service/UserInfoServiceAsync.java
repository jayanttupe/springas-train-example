package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.skynet.spms.client.vo.UserVo;


public interface UserInfoServiceAsync {

	/**
	 * 根据用户名取用户信息
	 * 
	 * @param userName
	 * @return UserVo
	 */
	 void getUserByUserName(String userName, AsyncCallback<UserVo> callback);
}
