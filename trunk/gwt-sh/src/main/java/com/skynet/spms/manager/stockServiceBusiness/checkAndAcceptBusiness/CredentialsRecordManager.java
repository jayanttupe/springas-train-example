package com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness;

import java.util.List;
import java.util.Map;

import com.skynet.spms.manager.CommonManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet.CheckAndAcceptSheet;

/**
 * 航材证书管理Manager实现类
 * @author  HDJ
 * @version 1.0
 * @date 2011-07-12
 */
public interface CredentialsRecordManager extends CommonManager<CheckAndAcceptSheet>{

	/**
	 * 获取航材证书管理相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 航材证书管理相关信息
	 */
	public List<CheckAndAcceptSheet> getCredentialsRecord(Map values, int startRow, int endRow);
	
}
