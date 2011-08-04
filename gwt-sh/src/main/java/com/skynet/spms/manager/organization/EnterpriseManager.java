package com.skynet.spms.manager.organization;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.BaseEnterpriseInformation;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.VAT;

public interface EnterpriseManager extends CommonManager<BaseEnterpriseInformation> {

	public List<BaseEnterpriseInformation> queryTree(String parentId,Class cls);
	
	public BaseEnterpriseInformation updateEntityCascade(Map<String,Object> props,String entityId,Class cls); 
	
	//public BaseEnterpriseInformation saveVATCascade(BaseEnterpriseInformation entity);
	
	public void deleteCascade(String enterpriseId,Class cls);
	
	public List<BaseEnterpriseInformation>  queryByFilter(Map<String,Object> props,Class cls);
	
	public void insertCascade(BaseEnterpriseInformation entity);
	
	public List<BaseEnterpriseInformation> queryByCode(String propName,String codeValue, Class subCls);
	//根据企业ID查找企业及其所有子企业
	public List<BaseEnterpriseInformation> queryAllWithSubById(String enterpriseId);
	
	public List<BaseEnterpriseInformation> queryAllWithSubByUsername(String username);
}
