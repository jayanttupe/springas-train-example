package com.skynet.spms.manager.customerService.LeaseService.LeaseContract;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.customerService.LeaseService.leaseContract.LeaseContractItem;

/**
 * 租赁合同明细接口
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface LeaseContracItemManager {
	/**
	 * 添加租赁合同明细的方法
	 * 
	 * @param o
	 *            租赁合同明细对象
	 */
	public void addLeaseContractItem(LeaseContractItem o);

	/**
	 * 删除租赁合同明细的方法
	 * 
	 * @param id
	 *            对象ID
	 */
	public void deleteLeaseContractItem(String id);

	/**
	 * 修改租赁合同明细的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 租赁合同明细对象
	 */
	public LeaseContractItem updateLeaseContractItem(
			Map<String, Object> newValues, String itemID);

	/**
	 * 分页查询租赁合同明细的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<LeaseContractItem> queryLeaseContractItemList(int startRow,
			int endRow);

	/**
	 * 根据租赁合同ID查询租赁合同明细
	 * 
	 * @param LeaseContractItemId
	 *            租赁合同ID
	 * @return 租赁合同明细的对象集合
	 */
	public List<LeaseContractItem> queryLeaseConractTemplateListByLeaseContractItemId(
			String LeaseContractItemId);

	/**
	 * 根据租赁合同编号查询租赁合同明细
	 * 
	 * @param contractNumber
	 *            租赁合同编号
	 * @return 租赁合同明细的对象集合
	 */
	public List<LeaseContractItem> queryLeaseConractTemplateListBycontractNumber(
			String contractNumber);
}
