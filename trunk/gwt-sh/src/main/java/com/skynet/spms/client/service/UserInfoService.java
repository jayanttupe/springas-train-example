package com.skynet.spms.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.skynet.spms.client.vo.UserVo;

@RemoteServiceRelativePath("userInfoAction.form")
public interface UserInfoService  extends RemoteService {

	/**
	 * 根据用户名取用户信息
	 * 
	 * @param userName
	 * @return UserVo
	 */
	public UserVo getUserByUserName(String userName);
}
