package com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.credentials.Credentials;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.cargoSpaceManage.CargoSpace;

/**
 * 证书功能Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface CredentialsManager extends CommonManager<Credentials>{
	
	/**
	 * 生成证书编码相关信息
	 * @param credentials
	 */
	public void createCredentialsCode(Credentials credentials);
	
	/**
	 * 获取证书相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 证书相关信息
	 */
	List<Credentials> getAllCredentials(Map values, int startRow, int endRow);
}
