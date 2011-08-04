package com.skynet.spms.action.stockServiceBusiness.checkAndAcceptBusiness;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness.CredentialsRecordManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet.CheckAndAcceptSheet;

/**
 * 描述：航材证书管理相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class CredentialsRecordDatasourceAction implements DataSourceAction<CheckAndAcceptSheet>{

	@Autowired
	private CredentialsRecordManager credentialsRecordManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"credentialsRecord_dataSource"};
	}
	
	/**
	 * 新增航材证书管理相关信息
	 * @param item
	 */
	@Override
	public void insert(CheckAndAcceptSheet item) {
	}

	/**
	 * 更新航材证书管理相关信息
	 * @param newValues
	 * @param itemID
	 * @return
	 */
	@Override
	public CheckAndAcceptSheet update(Map newValues, String itemID) {
		return null;
	}

	/**
	 * 删除航材证书管理相关信息
	 * @param itemID
	 */
	@Override
	public void delete(String itemID) {
	}

	/**
	 * 查询航材证书管理相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 航材证书管理相关信息
	 */
	@Override
	public List<CheckAndAcceptSheet> doQuery(Map values, int startRow, int endRow) {
		return credentialsRecordManager.getCredentialsRecord(values, 0, -1);
	}

	/**
	 * 获取所有航材证书管理信息
	 * @param startRow
	 * @param endRow
	 * @return 航材证书管理信息
	 */
	@Override
	public List<CheckAndAcceptSheet> getList(int startRow, int endRow) {
		return credentialsRecordManager.getCredentialsRecord(null, 0, -1);
	}
}
