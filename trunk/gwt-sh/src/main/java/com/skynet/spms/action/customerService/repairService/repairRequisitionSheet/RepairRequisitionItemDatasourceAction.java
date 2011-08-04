package com.skynet.spms.action.customerService.repairService.repairRequisitionSheet;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem.RepairRequisitionSheetItemManager;
import com.skynet.spms.persistence.entity.customerService.RepairService.RepairRequisitionSheet.RepairRequisitionSheetItem.RepairRequisitionSheetItem;

/**
 * 送修申请单 明细项数据源
 * 
 * @author taiqichao
 * @version
 * @Date 2011-7-11
 */
@Component
public class RepairRequisitionItemDatasourceAction implements
		DataSourceAction<RepairRequisitionSheetItem> {

	@Autowired
	private RepairRequisitionSheetItemManager repairRequisitionSheetItemMananger;

	/**
	 * 绑定数据源 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceBind#getBindDsName()
	 */
	public String[] getBindDsName() {
		return new String[] { "repairRequisitionSheetItem_dataSource" };
	}

	/**
	 * 插入 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#insert(java.lang.Object)
	 */
	public void insert(RepairRequisitionSheetItem item) {

		repairRequisitionSheetItemMananger.addRepairRequisitionSheetItem(item);
	}

	/**
	 * 分页查询 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#getList(int, int)
	 */
	public List<RepairRequisitionSheetItem> getList(int startRow, int endRow) {
		List<RepairRequisitionSheetItem> result = repairRequisitionSheetItemMananger
				.queryRepairRequisitionSheetItemList(startRow, endRow);
		return result;
	}

	/**
	 * 更新 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#update(java.util.Map,
	 *      java.lang.String)
	 */
	public RepairRequisitionSheetItem update(Map<String, Object> newValues,
			String itemID) {
		return repairRequisitionSheetItemMananger
				.updateRepairRequisitionSheetItem(newValues, itemID);
	}

	/**
	 * 删除 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#delete(java.lang.String)
	 */
	public void delete(String itemID) {
		this.repairRequisitionSheetItemMananger
				.deleteRepairRequisitionSheetItem(itemID);
	}

	/**
	 * 条件查询 (non-Javadoc)
	 * 
	 * @see com.skynet.spms.datasource.DataSourceAction#doQuery(java.util.Map,
	 *      int, int)
	 */
	public List<RepairRequisitionSheetItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		if ("reload".equals(values.get("key"))) {
			return this.getList(startRow, endRow);
		}
		return null;
	}

}
