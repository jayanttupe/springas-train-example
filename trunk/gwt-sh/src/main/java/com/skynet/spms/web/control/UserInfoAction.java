package com.skynet.spms.web.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.service.UserInfoService;
import com.skynet.spms.client.vo.CustomerVo;
import com.skynet.spms.client.vo.DepartmentVo;
import com.skynet.spms.client.vo.DutyVo;
import com.skynet.spms.client.vo.EnterpriseVO;
import com.skynet.spms.client.vo.UserInformationVo;
import com.skynet.spms.client.vo.UserVo;
import com.skynet.spms.manager.organization.UserManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.customers.Customer;
import com.skynet.spms.persistence.entity.organization.userInformation.User;

/**
 * 
 * 处理rpc方式获取用户信息请求
 * 
 * @author huangyun
 * 
 */
@Controller
@GwtRpcEndPoint
public class UserInfoAction implements UserInfoService {

	@Autowired
	private UserManager userManager;
	
	@Override
	@Transactional
	public UserVo getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		List userList = userManager.queryByUsername(userName, true);
		UserVo userVo = null;
		EnterpriseVO empVo = null;
		DepartmentVo deptVo = null;
		DutyVo dtVo = null;
		UserInformationVo userInfoVo = null;
		
		if(userList!=null&&userList.size()>0)
		{
			User user = (User)userList.get(0);
			//用户信息
			userVo = new UserVo();
			userVo.setId(user.getId());
			userVo.setUserName(user.getUsername());
			userVo.setEmail(user.getEmail());
			userVo.setRealName(user.getRealname());
			userVo.setLastVisit(user.getLastVisit());
			
			//用户其他信息
			userInfoVo = new UserInformationVo();
			userInfoVo.setMobile(user.getM_UserInformation().getMobile());
			userInfoVo.setFax(user.getM_UserInformation().getFax());
			userInfoVo.setSex(user.getM_UserInformation().getSex());
			userInfoVo.setTelephone(user.getM_UserInformation().getTelephone());
			
			userVo.setUserInfoVo(userInfoVo);
			
			//用户企业信息
			empVo = new EnterpriseVO();
			empVo.setEnterpriseName_zh(user.getM_BaseEnterpriseInformation().getEnterpriseName_zh());
			
			userVo.setEnterpriseVo(empVo);
			
			//客户识别码
			if(user.getM_BaseEnterpriseInformation() instanceof Customer){
				Customer customer = (Customer)user.getM_BaseEnterpriseInformation();
				CustomerVo customerVo = new CustomerVo();
				customerVo.setId(customer.getM_CustomerIdentificationCode().getId());
				customerVo.setCode(customer.getM_CustomerIdentificationCode().getCode());
				userVo.setCustomerVo(customerVo);
			}
			
/*			//用户部门信息
			deptVo = new DepartmentVo();
			deptVo.setDepartment(user.getM_DepartmentInformation().getDepartment());
			
			userVo.setDepartmentVo(deptVo);
			
			//用户职务信息
			dtVo= new DutyVo();
			dtVo.setDutyName(user.getM_Duty().getDutyName());
			
			userVo.setDutyVo(dtVo);*/
			
			return userVo;
			
		}

		return null;
	}

}
