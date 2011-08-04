package com.skynet.spms.manager.contractManagement.customerContactTemplate.leaseContractTemplate;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.LeaseContractTemplate.LeaseContractTemplate;

/**
 * 租赁合同接口
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface ILeaseContractTemplateManager {
	/**
	 * 添加租赁合同的方法
	 * 
	 * @param 租赁合同对象
	 */
	public void addLeaseContractTemplate(LeaseContractTemplate o);

	/**
	 * 修改租赁合同的方法
	 * 
	 * @param 新的数据对象
	 * @param 对象的ID
	 * @return 租赁合同对象
	 */
	public LeaseContractTemplate updateSLeaseContractTemplate(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除租赁合同的方法
	 * 
	 * @param 对象ID
	 */
	public void deleteLeaseContractTemplate(String itemID);

	/**
	 * 分页查询租赁合同的方法
	 * 
	 * @param 首页
	 * @param 尾页
	 * @return 集合对象
	 */
	public List<LeaseContractTemplate> queryLeaseContractTemplateList(
			int startRow, int endRow);

	/**
	 * 
	 * 根据合同ID查询合同对象
	 * 
	 * @param 租赁合同对象ID
	 * @return 租赁合同对象
	 */
	public LeaseContractTemplate getLeaseContractTemplateById(String sheetId);

	/**
	 * 
	 * 根据合同编号所对应的合同
	 * 
	 * @param 合同编号
	 * @return 租赁合同对象集合
	 */
	public List<LeaseContractTemplate> queryLeaseContractById(String string);
}
