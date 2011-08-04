package com.skynet.spms.action.customerService.leaseService.leaseRequisitionSheet;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.contractManagement.customerContactTemplate.leaseContractTemplate.ILeaseContractTemplateManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.contractManagement.template.CustomerContactTemplate.LeaseContractTemplate.LeaseContractTemplate;
import com.skynet.spms.persistence.entity.spmsdd.AuditStatus;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 客户租赁合同控制器
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-7-11
 */
@Component
public class LeaseContractTemplateAction implements
		DataSourceAction<LeaseContractTemplate> {

	@Autowired
	private ILeaseContractTemplateManager leaseContractTemplateManager;

	public String[] getBindDsName() {

		return new String[] { "leaseContract_dataSource" };
	}

	@Resource
	UUIDGeneral uuidGeneral;

	/**
	 * 添加客户租赁合同的方法
	 * 
	 * @param item
	 *            客户租赁合同对象
	 */
	public void insert(LeaseContractTemplate item) {
		item.setContractNumber(uuidGeneral.getSequence("LC"));
		// 设置发布状态
		BussinessPublishStatusEntity pbStatus = new BussinessPublishStatusEntity();
		pbStatus.setM_PublishStatus(PublishStatus.unpublished);
		item.setM_BussinessPublishStatusEntity(pbStatus);
		// 设置业务状态
		BussinessStatusEntity bStatus = new BussinessStatusEntity();
		bStatus.setStatus(EntityStatusMonitor.created);
		item.setM_BussinessStatusEntity(bStatus);
		// 设置审批业务状态
		item.setAuditStatus(AuditStatus.create);
		leaseContractTemplateManager.addLeaseContractTemplate(item);
	}

	/**
	 * 修改租赁合同的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 租赁合同对象
	 */
	public LeaseContractTemplate update(Map<String, Object> newValues,
			String itemID) {

		return leaseContractTemplateManager.updateSLeaseContractTemplate(
				newValues, itemID);
	}

	/**
	 * 删除租赁合同的方法
	 * 
	 * @param itemID
	 *            对象ID
	 */
	public void delete(String itemID) {
		leaseContractTemplateManager.deleteLeaseContractTemplate(itemID);
	}

	/**
	 * 根据条件客户租赁合同的方法
	 * 
	 * @param values
	 *            新数据对象
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<LeaseContractTemplate> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		if ("reload".equals(values.get("key"))) {
			return this.getList(startRow, endRow);
		}
		if (values.get("id") != null) {
			return leaseContractTemplateManager.queryLeaseContractById(values
					.get("id").toString());
		}
		return null;
	}

	/**
	 * 分页查询租赁合同的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<LeaseContractTemplate> getList(int startRow, int endRow) {

		return leaseContractTemplateManager.queryLeaseContractTemplateList(
				startRow, endRow);
	}

}
