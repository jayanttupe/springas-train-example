package com.skynet.spms.action.supplierSupport.leaseService.leasecontract;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.lease.LeaseContract.SSLeaseContracItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.lease.leaseContract.SSLeaseContractItem;

/**
 * 供应商租赁合同明细的控制器
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 * 
 */
@Component
public class SSLeaseContractItemAction implements
		DataSourceAction<SSLeaseContractItem> {

	@Autowired
	private SSLeaseContracItemManager manager;

	public String[] getBindDsName() {

		return new String[] { DSKey.S_LEASECONTRACTITEM_DS };
	}

	/**
	 * 添加供应商租赁合同明细的方法
	 * 
	 * @param item
	 *            供应商租赁合同明细对象
	 */
	public void insert(SSLeaseContractItem item) {
		manager.addLeaseContractItem(item);

	}

	/**
	 * 修改供应商租赁合同明细的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 供应商租赁合同明细对象
	 */
	public SSLeaseContractItem update(Map<String, Object> newValues,
			String itemID) {

		return manager.updateLeaseContractItem(newValues, itemID);
	}

	/**
	 * 删除供应商租赁合同明细的方法
	 * 
	 * @param itemID
	 *            对象ID
	 */
	public void delete(String itemID) {
		manager.deleteLeaseContractItem(itemID);
	}

	/**
	 * 根据条件查询供应商租赁合同明细的方法
	 * 
	 * @param values
	 *            新数据对象
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<SSLeaseContractItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		List<SSLeaseContractItem> items;
		if (StringUtils.isNotBlank(values.get("id").toString())) {
			items = manager
					.queryLeaseConractTemplateListByLeaseContractItemId(values
							.get("id").toString());
			return items;
		}
		if ("reload".equals(values.get("key"))) {
			return this.getList(startRow, endRow);
		}
		return null;
	}

	/**
	 * 分页查询供应商租赁合同明细的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<SSLeaseContractItem> getList(int startRow, int endRow) {

		return manager.queryLeaseContractItemList(startRow, endRow);
	}

}
