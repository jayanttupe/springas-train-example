package com.skynet.spms.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.skynet.spms.client.vo.contractManagement.Contract;

@RemoteServiceRelativePath("contractInfoAction.form")
public interface ContractInfoService extends RemoteService {
	
	/**
	 * 根据合同主键和检索key查询合同信息
	 * 
	 * @param id
	 * @param key
	 * @return
	 */
	public Contract getContract(String id, String key);


	public List<Contract> getContracts(String userName, String key);

}