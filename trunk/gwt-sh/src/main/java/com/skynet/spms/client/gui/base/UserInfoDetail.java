package com.skynet.spms.client.gui.base;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.skynet.spms.client.service.UserInfoServiceAsync;
import com.skynet.spms.client.vo.UserVo;
import com.smartgwt.client.widgets.viewer.DetailViewer;
import com.smartgwt.client.widgets.viewer.DetailViewerField;

public class UserInfoDetail extends DetailViewer {

	DetailViewerField realNameField = null;
	
	public UserInfoDetail(){
		
	}
	
	public UserInfoDetail(String userName){
		
		this.setWidth(80);
		this.setHeight(40);
		UserInfoServiceAsync service = GWT.create(UserInfoDetail.class);
		service.getUserByUserName(userName, new AsyncCallback<UserVo>() {
			
			@Override
			public void onSuccess(UserVo userVo) {
				// TODO Auto-generated method stub
				realNameField = new DetailViewerField("姓名");
				realNameField.setValue(userVo.getRealName());
				
				setFields(realNameField);
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
