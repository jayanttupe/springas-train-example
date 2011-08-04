package com.skynet.spms.manager.contractManagement.template;

import java.util.List;

import com.skynet.spms.client.vo.contractManagement.Contract;
import com.skynet.spms.client.vo.contractManagement.ContractItem;

public interface IRuoterManager {
	
	public Contract getContract(String id);
	
	public List<Contract> getContracts(String userName);
	
}

