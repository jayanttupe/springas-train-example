package com.skynet.spms.web.control.contractManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.skynet.common.gwt.GwtRpcEndPoint;
import com.skynet.spms.client.service.ContractInfoService;
import com.skynet.spms.client.vo.contractManagement.Contract;
import com.skynet.spms.manager.contractManagement.template.IRuoterManager;

/**
 * 
 * 处理rpc方式获取合同信息请求
 * 
 * @author tqc
 * 
 */
@Controller
@GwtRpcEndPoint
public class ContractInfoAction implements
		ContractInfoService {

	@Autowired
	private ApplicationContext applicationContext;

	private Contract get(String id, String key) {
		return applicationContext.getBean(key, IRuoterManager.class)
				.getContract(id);
	}
	
	private List<Contract> getBusinessContracts(String userName,String key){
		return applicationContext.getBean(key, IRuoterManager.class)
		.getContracts(userName);
	}
	
	@Override
	public Contract getContract(String id, String key) {
		Contract contract=get(id, key);
		return contract;
	}

	@Override
	public List<Contract> getContracts(String userName, String key) {
		return getBusinessContracts(userName, key);
	}

}
