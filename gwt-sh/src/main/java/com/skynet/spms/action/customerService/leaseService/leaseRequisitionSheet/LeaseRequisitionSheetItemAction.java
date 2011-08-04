package com.skynet.spms.action.customerService.leaseService.leaseRequisitionSheet;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.LeaseService.LeaseRequisitionSheet.LeaseRequisitionSheetItem.LeaseRequisitionSheetItemManager;
import com.skynet.spms.persistence.entity.customerService.LeaseService.LeaseRequisitionSheet.LeaseRequisitionSheetItem;

/**
 * 租赁申请单明细的控制器
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 * 
 */
@Component
public class LeaseRequisitionSheetItemAction implements
		DataSourceAction<LeaseRequisitionSheetItem> {
	@Autowired
	private LeaseRequisitionSheetItemManager leaseRequisitionSheetItemManager;

	public String[] getBindDsName() {

		return new String[] { "leaseRequisitionSheetItem_dataSource" };
	}

	/**
	 * 添加租赁申请单明细的方法
	 * 
	 * @param item
	 *            租赁申请单明细对象
	 */
	public void insert(LeaseRequisitionSheetItem item) {

		leaseRequisitionSheetItemManager.addLeaseRequisitionSheetItem(item);
	}

	/**
	 * 修改租赁申请单明细的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 租赁申请单对象
	 */
	public LeaseRequisitionSheetItem update(Map<String, Object> newValues,
			String itemID) {

		return this.leaseRequisitionSheetItemManager
				.updateLeaseRequisitionSheetItem(newValues, itemID);
	}

	/**
	 * 删除租赁申请单明细的方法
	 * 
	 * @param itemID
	 *            对象ID
	 */
	public void delete(String itemID) {
		this.leaseRequisitionSheetItemManager
				.deleteLeaseRequisitionSheetItem(itemID);
	}

	/**
	 * 根据查询租赁申请单的方法
	 * 
	 * @param values
	 *            新数据对象
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<LeaseRequisitionSheetItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		List<LeaseRequisitionSheetItem> items;
		if (values.get("id") != null) {
			items = leaseRequisitionSheetItemManager
					.queryLeaseRequisitionSheetItemListByLeaseRequisitionSheetId(values
							.get("id").toString());
			return items;
		}
		if ("reload".equals(values.get("key"))) {
			return this.getList(startRow, endRow);
		}
		if (values.get("LeaseRequisitionNumber") != null) {
			items = leaseRequisitionSheetItemManager
					.queryLeaseRequisitionSheetItemListByLeaseNumber(values
							.get("LeaseRequisitionNumber").toString());
			return items;
		}
		return null;
	}

	/**
	 * 分页查询租赁申请单的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<LeaseRequisitionSheetItem> getList(int startRow, int endRow) {
		List<LeaseRequisitionSheetItem> list = leaseRequisitionSheetItemManager
				.queryLeaseRequisitionSheetItemList(startRow, endRow);
		return list;
	}

}
