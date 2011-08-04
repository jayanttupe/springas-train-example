package com.skynet.spms.action.stockServiceBusiness.stockTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.PackingTaskItemManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.PackingTaskItem;

/**
 * 描述：装箱单任务明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class PackingTaskItemDatasourceAction implements
		DataSourceAction<PackingTaskItem> {
	@Autowired
	private PackingTaskItemManager packingTaskItemManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "packingTaskItem_dataSource" };
	}

	/**
	 * 新增装箱单任务明细相关信息
	 * @param shelvingTaskItem
	 */
	@Override
	public void insert(PackingTaskItem shelvingTaskItem) {
	}

	/**
	 * 更新装箱单任务明细相关信息
	 * @param newValues
	 * @param number
	 * @return
	 */
	@Override
	public PackingTaskItem update(Map newValues, String number) {
		return null;
	}

	/**
	 * 删除装箱单任务明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
	}

	/**
	 * 查询装箱单任务明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 装箱单任务明细相关信息
	 */
	@Override
	public List<PackingTaskItem> doQuery(Map values, int startRow, int endRow) {
		return packingTaskItemManager.getPackingTaskItem(values, startRow, endRow);
	}

	/**
	 * 获取所有装箱单任务明细信息
	 * @param startRow
	 * @param endRow
	 * @return 装箱单任务明细信息
	 */
	@Override
	public List<PackingTaskItem> getList(int startRow, int endRow) {
		return packingTaskItemManager.getPackingTaskItem(null, startRow, endRow);
	}
}