package com.skynet.spms.action.logisticsCustomsDeclaration.logisticsOutlayRecordManage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.logisticsOutlayRecordManage.LogisticsOutlayRecordManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.logisticsOutlayRecordManage.LogisticsOutlayRecord;

/**
 * 描述：费用记录管理相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class LogisticsOutlayRecordDatasourceAction implements DataSourceAction<LogisticsOutlayRecord>{
	@Autowired
	private LogisticsOutlayRecordManager logisticsOutlayRecordManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"logisticsOutlayRecordManage_dataSource"};
	}

	/**
	 * 新增费用记录管理相关信息
	 * @param logisticsOutlayRecord
	 */
	@Override
	public void insert(LogisticsOutlayRecord logisticsOutlayRecord) {
		logisticsOutlayRecordManager.insertEntity(logisticsOutlayRecord);
	}

	/**
	 * 更新费用记录管理相关信息
	 * @param newValues
	 * @param number
	 * @return 费用记录管理相关信息
	 */
	@Override
	public LogisticsOutlayRecord update(Map newValues, String number) {
		return (LogisticsOutlayRecord) logisticsOutlayRecordManager.updateEntity(newValues, number, LogisticsOutlayRecord.class);
	}

	/**
	 * 删除费用记录管理相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		logisticsOutlayRecordManager.deleteEntity(number, LogisticsOutlayRecord.class);
	}

	/**
	 * 查询费用记录管理相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 费用记录管理相关信息
	 */
	@Override
	public List<LogisticsOutlayRecord> doQuery(Map values, int startRow, int endRow) {
		return logisticsOutlayRecordManager.getLogisticsOutlayRecord(values, 0, -1);
	}

	/**
	 * 获取所有费用记录管理信息
	 * @param startRow
	 * @param endRow
	 * @return 费用记录管理信息
	 */
	@Override
	public List<LogisticsOutlayRecord> getList(int startRow, int endRow) {
		return logisticsOutlayRecordManager.getLogisticsOutlayRecord(null, 0, -1);
	}
}
