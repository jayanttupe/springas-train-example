package com.skynet.spms.action.logisticsCustomsDeclaration.logisticsOutlayRecordManage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.logisticsCustomsDeclaration.logisticsOutlayRecordManage.LogisticsOutlayItemManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.logisticsOutlayRecordManage.logisticsOutlayItem.LogisticsOutlayItem;

/**
 * 描述：费用记录管理明细相关信息
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class LogisticsOutlayItemDatasourceAction implements DataSourceAction<LogisticsOutlayItem>{
	@Autowired
	private LogisticsOutlayItemManager logisticsOutlayItemManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"logisticsOutlayItem_dataSource"};
	}

	/**
	 * 新增费用记录管理明细相关信息
	 * @param logisticsOutlayItem
	 */
	@Override
	public void insert(LogisticsOutlayItem logisticsOutlayItem) {
		logisticsOutlayItemManager.insertEntity(logisticsOutlayItem);
	}

	/**
	 * 更新费用记录管理明细相关信息
	 * @param newValues
	 * @param number
	 * @return 费用记录管理明细相关信息
	 */
	@Override
	public LogisticsOutlayItem update(Map newValues, String number) {
		return (LogisticsOutlayItem) logisticsOutlayItemManager.updateEntity(newValues, number, LogisticsOutlayItem.class);
	}

	/**
	 * 删除费用记录管理明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		logisticsOutlayItemManager.deleteEntity(number, LogisticsOutlayItem.class);
	}

	/**
	 * 查询费用记录管理明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 费用记录管理明细相关信息
	 */
	@Override
	public List<LogisticsOutlayItem> doQuery(Map values, int startRow, int endRow) {
		return logisticsOutlayItemManager.getLogisticsOutlayItem(values, 0, -1);
	}

	/**
	 * 获取所有费用记录管理明信息
	 * @param startRow
	 * @param endRow
	 * @return 费用记录管理明细信息
	 */
	@Override
	public List<LogisticsOutlayItem> getList(int startRow, int endRow) {
		return logisticsOutlayItemManager.list(0, -1, LogisticsOutlayItem.class);
	}
}
