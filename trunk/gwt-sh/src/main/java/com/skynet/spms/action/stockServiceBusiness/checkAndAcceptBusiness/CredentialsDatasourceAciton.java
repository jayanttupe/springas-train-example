package com.skynet.spms.action.stockServiceBusiness.checkAndAcceptBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness.CredentialsManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.credentials.Credentials;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.nonconformingReport.NonconformingReport;

/**
 * 描述：证书相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class CredentialsDatasourceAciton implements DataSourceAction<Credentials>{

	@Autowired
	private CredentialsManager credentialsManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"credentials_dataSource"};
	}
	
	/**
	 * 新增证书相关信息
	 * @param credentials
	 */
	@Override
	public void insert(Credentials credentials) {
		credentialsManager.createCredentialsCode(credentials);
	}

	/**
	 * 更新证书相关信息
	 * @param newValues
	 * @param number
	 * @return 证书相关信息
	 */
	@Override
	public Credentials update(Map newValues, String number) {
		return null;
	}

	/**
	 * 删除证书相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		credentialsManager.deleteEntity(number, Credentials.class);
	}

	/**
	 * 查询证书相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 证书相关信息
	 */
	@Override
	public List<Credentials> doQuery(Map values, int startRow, int endRow) {
		return credentialsManager.getAllCredentials(values, 0, -1);
	}

	/**
	 * 获取所有证书信息 
	 * @param startRow
	 * @param endRow
	 * @return 证书信息
	 */
	@Override
	public List<Credentials> getList(int startRow, int endRow) {
		return credentialsManager.getAllCredentials(null, 0, -1);
	}
}
