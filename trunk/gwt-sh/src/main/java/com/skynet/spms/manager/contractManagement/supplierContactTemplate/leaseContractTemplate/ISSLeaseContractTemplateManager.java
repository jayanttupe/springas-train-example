package com.skynet.spms.manager.contractManagement.supplierContactTemplate.leaseContractTemplate;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.leaseContractTemplate.SSLeaseContractTemplate;

/**
 * 供应商租赁合同实现类
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface ISSLeaseContractTemplateManager {
	/**
	 * 添加供应商租赁合同的方法
	 * 
	 * @param 供应商租赁合同对象
	 */
	public void addLeaseContractTemplate(SSLeaseContractTemplate o);

	/**
	 * 修改供应商租赁合同的方法
	 * 
	 * @param 新的数据对象
	 * @param 对象的ID
	 * @return 供应商租赁合同对象
	 */
	public SSLeaseContractTemplate updateSLeaseContractTemplate(
			Map<String, Object> newValues, String itemID);

	/**
	 * 删除供应商租赁合同的方法
	 * 
	 * @param 对象ID
	 */
	public void deleteLeaseContractTemplate(String itemID);

	/**
	 * 分页查询供应商租赁合同的方法
	 * 
	 * @param 首页
	 * @param 尾页
	 * @return 集合对象
	 */
	public List<SSLeaseContractTemplate> queryLeaseContractTemplateList(
			int startRow, int endRow);

	/**
	 * 
	 * 根据供应商租赁合同ID查询供应商租赁合同
	 * 
	 * @param 供应商租赁合同ID
	 * @return 供应商租赁合同对象集合
	 */
	public List<SSLeaseContractTemplate> querySSLeaseContractById(String string);
}
