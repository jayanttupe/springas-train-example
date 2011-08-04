package com.skynet.spms.action.customerService.repairService.repairRequisitionSheet;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.ListGridFilterManager;
import com.skynet.spms.manager.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetManager;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheet;

/**
 * 
 * 送修申请单数据源
 * 
 * @author taiqichao
 * @version 1.0
 * @Date 2011-7-11
 */
@Component
public class RepairRequisitionDatasourceAction implements
		DataSourceAction<RepairRequisitionSheet> {

	@Autowired
	private RepairRequisitionSheetManager repairRequisitionSheetManager;

	@Autowired
	private ListGridFilterManager<RepairRequisitionSheet> filterManager;

	/**
	 * 绑定数据源 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "repairRequisitionSheet_dataSource" };
	}

	/**
	 * 插入 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(RepairRequisitionSheet item) {
		repairRequisitionSheetManager.addRepairRequisitionSheet(item);
	}

	/**
	 * 分页查询 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<RepairRequisitionSheet> getList(int startRow, int endRow) {
		List<RepairRequisitionSheet> result = repairRequisitionSheetManager
				.queryRepairRequisitionSheetList(startRow, endRow);
		return result;
	}

	/**
	 * 更新 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map,
	 *      java.lang.String)
	 */
	public RepairRequisitionSheet update(Map<String, Object> newValues,
			String itemID) {
		return repairRequisitionSheetManager.updateRepairRequisitionSheet(
				newValues, itemID);
	}

	/**
	 * 删除 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		this.repairRequisitionSheetManager.deleteRepairRequisitionSheet(itemID);
	}

	/**
	 * 高级查询 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map,
	 *      int, int)
	 */
	public List<RepairRequisitionSheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		List<?> criteria = (List<?>) values.remove("criteria");
		if (criteria != null) {
			return filterManager.doQueryFilter(RepairRequisitionSheet.class,
					criteria, startRow, endRow);
		}
		if ("reload".equals(values.get("_key"))) {// 刷新
			return this.getList(startRow, endRow);
		}
		return null;
	}

}
