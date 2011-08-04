package com.skynet.spms.webservice.service;



import com.skynet.spms.webservice.entity.AllUsersInputParameters;
import com.skynet.spms.webservice.entity.AllUsersOutParameters;
import com.skynet.spms.webservice.entity.FaultResponse;
import com.skynet.spms.webservice.entity.QueryUserInputParameters;
import com.skynet.spms.webservice.entity.UserIDCardInputParameters;
import com.skynet.spms.webservice.entity.UserInfoInputParameters;
import com.skynet.spms.webservice.entity.UserInfoOutputParameters;

public interface WsUserService {

	public UserInfoOutputParameters getUserInfo(UserInfoInputParameters request)
			throws FaultResponse;

	public boolean isUserEnable(QueryUserInputParameters request)
			throws FaultResponse;

	public boolean setUserIDCard(UserIDCardInputParameters request)
			throws FaultResponse;

	public AllUsersOutParameters getUserIDCard(QueryUserInputParameters request)
			throws FaultResponse;

	public AllUsersOutParameters getAllUsers(AllUsersInputParameters request)
			throws FaultResponse;

}
