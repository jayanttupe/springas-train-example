package com.skynet.spms.manager.supplierSupport.lease.LeaseContract;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.supplierSupport.lease.leaseContract.SSLeaseContractItem;

/**
 * 供应商租赁合同明细接口
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface SSLeaseContracItemManager {
	/**
	 * 添加供应商租赁合同明细的方法
	 * 
	 * @param o
	 *            供应商租赁合同明细对象
	 */
	public void addLeaseContractItem(SSLeaseContractItem o);

	/**
	 * 删除供应商租赁合同明细的方法
	 * 
	 * @param id
	 *            对象ID
	 */
	public void deleteLeaseContractItem(String id);

	/**
	 * 修改供应商租赁合同明细的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 供应商租赁合同明细对象
	 */
	public SSLeaseContractItem updateLeaseContractItem(
			Map<String, Object> newValues, String itemID);

	/**
	 * 分页查询供应商租赁合同明细的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<SSLeaseContractItem> queryLeaseContractItemList(int startRow,
			int endRow);

	/**
	 * 根据供应商租赁合同ID查询租赁合同明细
	 * 
	 * @param LeaseContractItemId
	 *            供应商租赁合同ID
	 * @return 供应商租赁合同明细的对象集合
	 */
	public List<SSLeaseContractItem> queryLeaseConractTemplateListByLeaseContractItemId(
			String LeaseContractItemId);
}
