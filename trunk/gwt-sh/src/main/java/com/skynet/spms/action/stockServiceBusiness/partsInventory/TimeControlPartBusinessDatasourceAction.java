package com.skynet.spms.action.stockServiceBusiness.partsInventory;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.partsInventory.TimeControlPartBusinessManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.partsInventory.partsInventoryRecord.PartsInventoryRecord;

/**
 * 描述：时控件相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class TimeControlPartBusinessDatasourceAction implements DataSourceAction<PartsInventoryRecord>{
	@Autowired
	private TimeControlPartBusinessManager timeControlPartBusinessManager;

	@Override
	public String[] getBindDsName() {
		return new String[]{"timeControlPartBusiness_dataSource"};
	}

	/**
	 * 新增时控件相关信息
	 * @param timeControlPartBusiness
	 */
	@Override
	public void insert(PartsInventoryRecord timeControlPartBusiness) {
		timeControlPartBusinessManager.insertEntity(timeControlPartBusiness);
	}

	/**
	 * 更新时控件相关信息
	 * @param newValues
	 * @param number
	 * @return 时控件相关信息
	 */
	@Override
	public PartsInventoryRecord update(Map newValues, String number) {
		return (PartsInventoryRecord) timeControlPartBusinessManager.updateEntity(newValues, number, PartsInventoryRecord.class);
	}

	/**
	 * 删除时控件相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
		timeControlPartBusinessManager.deleteEntity(number, PartsInventoryRecord.class);
	}

	/**
	 * 查询时控件相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 时控件相关信息
	 */
	@Override
	public List<PartsInventoryRecord> doQuery(Map values, int startRow, int endRow) {
		return timeControlPartBusinessManager.getTimeControlPartBusiness(values, 0, -1);
	}

	/**
	 * 获取所有时控件信息
	 * @param startRow
	 * @param endRow
	 * @return 时控件信息
	 */
	@Override
	public List<PartsInventoryRecord> getList(int startRow, int endRow) {
		return timeControlPartBusinessManager.getTimeControlPartBusiness(null, 0, -1);
	}
}