package com.skynet.spms.action.customerService.leaseService.leaseRequisitionSheet;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.customerService.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.LeaseService.LeaseContract.LeaseContracItemManager;
import com.skynet.spms.persistence.entity.customerService.LeaseService.leaseContract.LeaseContractItem;

/**
 * 客户租赁合同明细的数据源
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 * 
 */
@Component
public class LeaseContractItemAction implements
		DataSourceAction<LeaseContractItem> {

	@Autowired
	private LeaseContracItemManager manager;

	public String[] getBindDsName() {

		return new String[] { DSKey.C_LEASECONTRACT_TIEM_DS };
	}

	/**
	 * 添加租赁合同明细的方法
	 * 
	 * @param item
	 *            租赁合同明细对象
	 */
	public void insert(LeaseContractItem item) {
		manager.addLeaseContractItem(item);

	}

	/**
	 * 修改租赁合同明细的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 租赁合同明细对象
	 */
	public LeaseContractItem update(Map<String, Object> newValues, String itemID) {

		return manager.updateLeaseContractItem(newValues, itemID);
	}

	/**
	 * 删除租赁合同明细的方法
	 * 
	 * @param itemID
	 *            对象ID
	 */
	public void delete(String itemID) {
		manager.deleteLeaseContractItem(itemID);
	}

	/**
	 * 根据条件客户租赁合同明细的方法
	 * 
	 * @param values
	 *            新数据对象
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<LeaseContractItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		List<LeaseContractItem> items;
		if (values.get("id") != null) {
			items = manager
					.queryLeaseConractTemplateListByLeaseContractItemId(values
							.get("id").toString());
			return items;
		}
		if ("reload".equals(values.get("key"))) {
			return this.getList(startRow, endRow);
		}
		if (values.get("contractNumber") != null) {
			items = manager
					.queryLeaseConractTemplateListBycontractNumber(values.get(
							"contractNumber").toString());
			return items;
		}
		return null;
	}

	/**
	 * 分页查询租赁合同明细的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<LeaseContractItem> getList(int startRow, int endRow) {

		return manager.queryLeaseContractItemList(startRow, endRow);
	}

}
