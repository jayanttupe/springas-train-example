package com.skynet.spms.action.stockServiceBusiness.stockTask;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.SendCardTaskItemManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.SendCardTaskItem;

/**
 * 描述：发卡任务明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class SendCardTaskItemDatasourceAction implements
		DataSourceAction<SendCardTaskItem> {
	@Autowired
	private SendCardTaskItemManager sendCardTaskItemManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "sendCardTaskItem_dataSource" };
	}

	/**
	 * 新增发卡任务明细相关信息
	 * @param sendCardTaskItem
	 */
	@Override
	public void insert(SendCardTaskItem sendCardTaskItem) {
	}

	/**
	 * 更新发卡任务明细相关信息
	 * @param newValues
	 * @param number
	 * @return null
	 */
	@Override
	public SendCardTaskItem update(Map newValues, String number) {
		return null;
	}

	/**
	 * 删除发卡任务明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
	}

	/**
	 * 查询发卡任务明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 发卡任务明细相关信息
	 */
	@Override
	public List<SendCardTaskItem> doQuery(Map values, int startRow, int endRow) {
		return sendCardTaskItemManager.getSendCardTaskItem(values, startRow, endRow);
	}

	/**
	 * 获取所有发卡任务明细信息
	 * @param startRow
	 * @param endRow
	 * @return 发卡任务明细信息
	 */
	@Override
	public List<SendCardTaskItem> getList(int startRow, int endRow) {
		return sendCardTaskItemManager.getSendCardTaskItem(null, startRow, endRow);
	}
}