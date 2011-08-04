package com.skynet.spms.action.stockServiceBusiness.checkAndAcceptBusiness;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.BeanPropUtil;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.checkAndAcceptBusiness.CheckAndAcceptSheetManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.checkAndAcceptBusiness.checkAndAcceptSheet.CheckAndAcceptSheet;
import com.skynet.spms.persistence.entity.stockServiceBusiness.warehouseManageBusiness.stockRoomManage.StockRoom;

/**
 * 描述：领料验收单相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class CheckAndAcceptSheetDatasourceAction implements DataSourceAction<CheckAndAcceptSheet>{

	private Logger log=LoggerFactory.getLogger(CheckAndAcceptSheetDatasourceAction.class);
	
	@Autowired
	private CheckAndAcceptSheetManager checkAndAcceptSheetManager;
	
	@Override
	public String[] getBindDsName() {
		return new String[]{"checkAndAcceptSheet_dataSource"};
	}

	/**
	 * 新增领料验收单相关信息
	 * @param checkAndAcceptSheet
	 */
	@Override
	public void insert(CheckAndAcceptSheet checkAndAcceptSheet) {
		// 设置随机证件
		checkAndAcceptSheet.setCredentials(formatCredentials(checkAndAcceptSheet.getCredentialsList()));
		checkAndAcceptSheetManager.saveCheckAndAcceptSheet(checkAndAcceptSheet);
	}

	/**
	 * 更新领料验收单相关信息
	 * @param newValues
	 * @param userId
	 * @return 领料验收单相关信息
	 */
	@Override
	public CheckAndAcceptSheet update(Map newValues, String userId) {
		String[] credentials = null;
		if (newValues.containsKey("credentialsList")) {
			ArrayList credentialsList = (ArrayList)newValues.get("credentialsList");
			credentials = new String[credentialsList.size()];
			credentialsList.toArray(credentials);
		}

		CheckAndAcceptSheet checkAndAcceptSheet = new CheckAndAcceptSheet();
		BeanPropUtil.fillEntityWithMap(checkAndAcceptSheet, newValues);
		checkAndAcceptSheet.setCredentials(formatCredentials(credentials));
		return checkAndAcceptSheetManager.saveCheckAndAcceptSheet(checkAndAcceptSheet);
	}

	/**
	 * 格式化随机证件
	 */
	private String formatCredentials(String[] credentialsList)
	{
		String credentials = "";
		if (credentialsList != null && credentialsList.length > 0) {
			for (int i = 0; i < credentialsList.length; i++) {
				credentials = credentials + String.valueOf(credentialsList[i]) + ",";
			}
		}
		
		if (credentials.length() > 0) {
			return credentials.substring(0, credentials.length() - 1);	
		} else {
			return "";
		}
	}

	/**
	 * 删除领料验收单相关信息
	 * @param userId
	 */
	@Override
	public void delete(String userId) {
		checkAndAcceptSheetManager.deleteEntity(userId, CheckAndAcceptSheet.class);
	}

	/**
	 * 查询领料验收单相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 领料验收单相关信息
	 */
	@Override
	public List<CheckAndAcceptSheet> doQuery(Map values, int startRow, int endRow) {
		return checkAndAcceptSheetManager.getCheckAndAcceptSheet(values, 0, -1);
	}

	/**
	 * 获取所有领料验收单信息
	 * @param startRow
	 * @param endRow
	 * @return 领料验收单信息
	 */
	@Override
	public List<CheckAndAcceptSheet> getList(int startRow, int endRow) {
		return checkAndAcceptSheetManager.getCheckAndAcceptSheet(null, startRow, endRow);
	}

}
