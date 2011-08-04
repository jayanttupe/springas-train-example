package com.skynet.spms.client.tools;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.service.UserInfoService;
import com.skynet.spms.client.service.UserInfoServiceAsync;
import com.skynet.spms.client.vo.UserVo;

public class UserTools {

	private static String username;
	private static UserVo user = null;
	public static void setCurrentUserName(String loginUsername){
		username = loginUsername;
	}
	
	public static String getCurrentUserName(){
		return username;
	}
	
	public static UserVo getUserVo(){
		return user;
	}
	
	public static UserVo getUserVoByUserName(String userName){
		UserInfoServiceAsync service = GWT.create(UserInfoService.class);
	
		service.getUserByUserName(userName, new AsyncCallback<UserVo>() {
			
			@Override
			public void onSuccess(UserVo userVo) {
				// TODO Auto-generated method stub
				user = userVo;
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		return user;
	}

	public static void setUserVo(String userName2) {
		// TODO Auto-generated method stub
		UserInfoServiceAsync service = GWT.create(UserInfoService.class);
		
		service.getUserByUserName(userName2, new AsyncCallback<UserVo>() {
			
			@Override
			public void onSuccess(UserVo userVo) {
				// TODO Auto-generated method stub
				user = userVo;
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
}
