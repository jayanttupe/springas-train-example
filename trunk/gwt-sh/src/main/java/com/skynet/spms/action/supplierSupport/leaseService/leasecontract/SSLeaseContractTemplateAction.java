package com.skynet.spms.action.supplierSupport.leaseService.leasecontract;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.contractManagement.supplierContactTemplate.leaseContractTemplate.ISSLeaseContractTemplateManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.contractManagement.template.supplierContactTemplate.leaseContractTemplate.SSLeaseContractTemplate;
import com.skynet.spms.persistence.entity.spmsdd.AuditStatus;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 供应商租赁合同的控制器
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 * 
 */
@Component
public class SSLeaseContractTemplateAction implements
		DataSourceAction<SSLeaseContractTemplate> {

	@Autowired
	private ISSLeaseContractTemplateManager leaseContractTemplateManager;

	public String[] getBindDsName() {

		return new String[] { DSKey.S_LEASECONTRACT_DS };
	}

	@Resource
	UUIDGeneral uuidGeneral;

	/**
	 * 添加供应商租赁合同的方法
	 * 
	 * @param item
	 *            供应商租赁合同对象
	 */
	public void insert(SSLeaseContractTemplate item) {
		item.setContractNumber(uuidGeneral.getSequence("LS"));
		// 设置发布状态
		BussinessPublishStatusEntity pbStatus = new BussinessPublishStatusEntity();
		pbStatus.setM_PublishStatus(PublishStatus.unpublished);
		item.setM_BussinessPublishStatusEntity(pbStatus);
		// 设置业务状态
		BussinessStatusEntity bStatus = new BussinessStatusEntity();
		bStatus.setStatus(EntityStatusMonitor.created);
		item.setM_BussinessStatusEntity(bStatus);

		item.setAuditStatus(AuditStatus.create);
		leaseContractTemplateManager.addLeaseContractTemplate(item);
	}

	/**
	 * 修改供应商租赁合同的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 供应商租赁合同对象
	 */
	public SSLeaseContractTemplate update(Map<String, Object> newValues,
			String itemID) {

		return leaseContractTemplateManager.updateSLeaseContractTemplate(
				newValues, itemID);
	}

	/**
	 * 删除供应商租赁合同的方法
	 * 
	 * @param itemID
	 *            对象ID
	 */
	public void delete(String itemID) {
		leaseContractTemplateManager.deleteLeaseContractTemplate(itemID);
	}

	/**
	 * 根据条件查询供应商租赁合同的方法
	 * 
	 * @param values
	 *            新数据对象
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<SSLeaseContractTemplate> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		if ("reload".equals(values.get("key"))) {
			return this.getList(startRow, endRow);
		}
		if (values.get("id") != null) {
			return leaseContractTemplateManager.querySSLeaseContractById(values
					.get("id").toString());
		}
		return null;
	}

	/**
	 * 分页查询供应商租赁合同的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<SSLeaseContractTemplate> getList(int startRow, int endRow) {

		return leaseContractTemplateManager.queryLeaseContractTemplateList(
				startRow, endRow);
	}

}
