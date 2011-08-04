package com.skynet.spms.webservice.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skynet.spms.manager.organization.UserManager;
import com.skynet.spms.persistence.entity.organization.userInformation.User;
import com.skynet.spms.persistence.entity.spmsdd.EnableStatus;
import com.skynet.spms.webservice.entity.AllUsersInputParameters;
import com.skynet.spms.webservice.entity.AllUsersOutParameters;
import com.skynet.spms.webservice.entity.FaultResponse;
import com.skynet.spms.webservice.entity.QueryUserInputParameters;
import com.skynet.spms.webservice.entity.UserIDCardInputParameters;
import com.skynet.spms.webservice.entity.UserInfoInputParameters;
import com.skynet.spms.webservice.entity.UserInfoOutputParameters;
import com.skynet.spms.webservice.service.WsUserService;

@Service
public class WsUserServiceImpl implements WsUserService {

	@Autowired
	UserManager userManager;
	
	@Override
	public UserInfoOutputParameters getUserInfo(UserInfoInputParameters request)
			throws FaultResponse {
		
		String username = request.getUsername();
		
		List<User> userList = getUsersByUsername(username,true);
		if(userList.size() == 0){
			throw new FaultResponse("User not exist!");
		}
		//构建UserInfoOutputParameters实例
		UserInfoOutputParameters userOutput = transform(userList.get(0));
		
		return userOutput;
	}

	@Override
	public boolean isUserEnable(QueryUserInputParameters request)
			throws FaultResponse {
		
		String username = request.getUsername();
		
		List<User> userList = getUsersByUsername(username,true);
		if(userList.size() == 0){
			throw new FaultResponse("User not exist!");
		}
		User user = userList.get(0);
		//读取用户启用状态并返回
		EnableStatus status = user.getM_EnableStatus();
		return status.equals(EnableStatus.enable); 
	} 

	@Override
	public boolean setUserIDCard(UserIDCardInputParameters request)
			throws FaultResponse {
		
		String username = request.getUsername();
		if(username == null || "".equals(username)){
			throw new FaultResponse("Invalid input parameter!");
		}
		Map<String,Object> values = new HashMap<String,Object>();
		//真实姓名
		values.put("realname", request.getRealname());
		//权限等级
		values.put("accessLevel", request.getAccessLevel());
		//有效日期
		XMLGregorianCalendar calendar = request.getExpiryDate();
		Date expiryDate = null;
		if(calendar != null){
			expiryDate = calendar.toGregorianCalendar().getTime();
		}	
		values.put("expiryDate", expiryDate);
		//用户身份卡号(使用测试数据，实际应填入按规则生成的递增的卡号字符串)
		values.put("idCardNumber","test1001");
		
		//调用Manager方法更新用户实体及其身份卡信息
		boolean isSuccess = false;
		try {
			isSuccess = userManager.updateByUsername(username, values);
		} catch (Exception e) {
			throw new FaultResponse(e.getMessage());
		}
	
		return isSuccess;
	}

	@Override
	public AllUsersOutParameters getUserIDCard(QueryUserInputParameters request)
			throws FaultResponse {
		String username = request.getUsername();
		
		List<User> userList = getUsersByUsername(username,false);
		
		AllUsersOutParameters allUsersOut = new AllUsersOutParameters();
		com.skynet.spms.webservice.entity.User wsUser = null;	
		for(User user : userList){
			//将持久化User实体转换为 Webservice User实体
			wsUser = transform(user);
			allUsersOut.getUser().add(wsUser);
		}
		return allUsersOut;
	}

	@Override
	public AllUsersOutParameters getAllUsers(AllUsersInputParameters request)
			throws FaultResponse {
		List<User> userList = null;
		try {
			userList = userManager.list(0, -1, User.class);
		} catch (Exception e) {
			throw new FaultResponse(e.getMessage());
		}
		AllUsersOutParameters allUsersOut = new AllUsersOutParameters();
		com.skynet.spms.webservice.entity.User wsUser = null;	
		for(User user : userList){
			wsUser = transform(user);
			allUsersOut.getUser().add(wsUser);
		}
		return allUsersOut;
	}
	
	//根据username获取User实体
	private List<User> getUsersByUsername(String username,boolean iseq) throws FaultResponse{
		if(username==null || "".equals(username)){
			throw new FaultResponse("Invalid input parameter!");
		}
		//获取用户信息
		List<User> userList = null;
		try {
			userList = userManager.queryByUsername(username,iseq);
		} catch (Exception e) {
			throw new FaultResponse(e.getMessage());
		}
		return userList;
	}
	//将持久化User实体转换为WebService User实体
	private UserInfoOutputParameters transform(User user){
		UserInfoOutputParameters wsUser = new UserInfoOutputParameters();
		//设置用户名
		wsUser.setUsername(user.getUsername());
		//设置真实姓名
		wsUser.setRealname(user.getRealname());
		//设置权限等级
		wsUser.setAccessLevel(user.getAccessLevel());
		//设置Email
		wsUser.setEmail(user.getEmail());
		//设置工号
		wsUser.setJobNumber(user.getJobNumber());
		//用户是否可用
		EnableStatus status = user.getM_EnableStatus();
		boolean enable = status.equals(EnableStatus.enable)? true : false;
		wsUser.setEnable(enable);
		//IDCardNumber 及 有效日期
		if(user.getM_IDCard() != null){
			wsUser.setIDCardNumber(user.getM_IDCard().getIdCardNumber());
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String expiryDate = null;
			if(user.getM_IDCard().getExpiryDate() != null){
				expiryDate = dateFormat.format(user.getM_IDCard().getExpiryDate());
			}
			wsUser.setExpiryDate(expiryDate);
		}
		return wsUser;
	}

}
