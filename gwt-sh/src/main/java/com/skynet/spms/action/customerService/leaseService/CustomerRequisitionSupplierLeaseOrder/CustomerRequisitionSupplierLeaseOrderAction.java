package com.skynet.spms.action.customerService.leaseService.CustomerRequisitionSupplierLeaseOrder;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.LeaseService.CustomerRequisitionSupplierLeaseOrder.LeaseInstructManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.order.requisitionSupplierLeaseOrder.CustomerRequisitionSupplierLeaseOrder;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 客户申请供应商租赁指令
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-07-11
 * 
 */
@Component
public class CustomerRequisitionSupplierLeaseOrderAction implements
		DataSourceAction<CustomerRequisitionSupplierLeaseOrder> {

	@Autowired
	private LeaseInstructManager manager;

	public String[] getBindDsName() {

		return new String[] { "LeaseInstruct_dataSource" };
	}

	@Resource
	UUIDGeneral uuidGeneral;

	/**
	 * 添加客户申请供应商租赁指令的方法
	 * 
	 * @param item
	 *            客户申请供应商租赁指令对象
	 */
	public void insert(CustomerRequisitionSupplierLeaseOrder item) {

		item.setOrderNumber(uuidGeneral.getSequence("CSL"));
		// item.setCreateDate(new Date());
		// 构建业务发布状态
		BussinessPublishStatusEntity pstate = new BussinessPublishStatusEntity();
		pstate.setM_PublishStatus(PublishStatus.unpublished);
		item.setM_BussinessPublishStatusEntity(pstate);
		// 构建业务状态
		BussinessStatusEntity entity = new BussinessStatusEntity();
		entity.setStatus(EntityStatusMonitor.created);
		item.setM_BussinessStatusEntity(entity);

		manager.addLeaseInstruct(item);
	}

	/**
	 * 修改客户申请供应商租赁指令的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 租赁申请单对象
	 */
	public CustomerRequisitionSupplierLeaseOrder update(
			Map<String, Object> newValues, String itemID) {

		return manager.updateleaseInstruct(newValues, itemID);
	}

	/**
	 * 删除客户申请供应商租赁指令的方法
	 * 
	 * @param itemID
	 *            对象ID
	 */
	public void delete(String itemID) {
		manager.deleteLeaseInstruct(itemID);
	}

	/**
	 * 根据条件查询客户申请供应商租赁指令的方法
	 * 
	 * @param values
	 *            新数据对象
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<CustomerRequisitionSupplierLeaseOrder> doQuery(
			Map<String, Object> values, int startRow, int endRow) {
		if (values.get("publishState") != null
				&& values.get("publishState").equals("publish")) {
			return manager.querySsLeaseInstructList(startRow, endRow);
		}
		if ("reload".equals(values.get("key"))) {
			return this.getList(startRow, endRow);
		}
		return null;
	}

	/**
	 * 分页查询客户申请供应商租赁指令的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<CustomerRequisitionSupplierLeaseOrder> getList(int startRow,
			int endRow) {

		return manager.queryLeaseInstructList(startRow, endRow);
	}

}
