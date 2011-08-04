package com.skynet.spms.action.stockServiceBusiness.stockTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.stockServiceBusiness.stockTask.RepairCodeTaskItemManager;
import com.skynet.spms.persistence.entity.stockServiceBusiness.stockTask.RepairCodeTaskItem;

/**
 * 描述：补码任务明细相关信息处理
 * @author HDJ
 * @version 1.0
 * @Date 2011-7-14
 */
@Component
public class RepairCodeTaskItemDatasourceAction implements DataSourceAction<RepairCodeTaskItem> {
	
	private Logger log=LoggerFactory.getLogger(RepairCodeTaskItemDatasourceAction.class);
	
	@Autowired
	private RepairCodeTaskItemManager repairCodeTaskItemManager;

	@Override
	public String[] getBindDsName() {
		return new String[] { "repairCodeTaskItem_dataSource" };
	}

	/**
	 * 新增补码任务明细相关信息
	 * @param item
	 */
	@Override
	public void insert(RepairCodeTaskItem item) {
	}

	/**
	 * 更新补码任务明细相关信息
	 * @param newValues
	 * @param number
	 * @return 补码任务明细相关信息
	 */
	@Override
	public RepairCodeTaskItem update(Map<String,Object> newValues, String number) {
		return null;
	}

	/**
	 * 删除补码任务明细相关信息
	 * @param number
	 */
	@Override
	public void delete(String number) {
	}

	/**
	 * 查询补码任务明细相关信息
	 * @param values
	 * @param startRow
	 * @param endRow
	 * @return 补码任务明细相关信息
	 */
	@Override
	public List<RepairCodeTaskItem> doQuery(Map<String,Object> values, int startRow, int endRow) {
		log.info("===============AircraftRegistrationCatalogDataSourceAction.doQuery()");
		for(Map.Entry<String, Object> entry : values.entrySet()){
			log.info(entry.getKey() + " : " + entry.getValue() );
		}
		return repairCodeTaskItemManager.getRepairCodeTaskItem(values);
	}

	/**
	 * 获取所有补码任务明细信息
	 * @param startRow
	 * @param endRow
	 * @return 补码任务明细信息
	 */
	@Override
	public List<RepairCodeTaskItem> getList(int startRow, int endRow) {
		return new ArrayList<RepairCodeTaskItem>();
	}
}