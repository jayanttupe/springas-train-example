package com.skynet.spms.action.customerService.leaseService.leaseRequisitionSheet;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.customerService.LeaseService.LeaseRequisitionSheet.LeaseRequisitionSheetManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.LeaseService.LeaseRequisitionSheet.LeaseRequisitionSheet;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 租赁申请单控制器
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 * 
 */
@Component
public class LeaseRequisitionSheetAction implements
		DataSourceAction<LeaseRequisitionSheet> {

	@Autowired
	private LeaseRequisitionSheetManager leaseRequisitonSheetManager;

	public String[] getBindDsName() {

		return new String[] { "leaseRequisitionSheet_dataSource" };
	}

	@Resource
	UUIDGeneral uuidGeneral;

	/**
	 * 添加租赁申请单的方法
	 * 
	 * @param item
	 *            租赁申请单对象
	 */
	public void insert(LeaseRequisitionSheet item) {

		// 构建申请单编号
		item.setRequisitionSheetNumber(uuidGeneral.getSequence("LR"));
		item.setRequisitionDate(new Date());
		// 构建业务发布状态
		BussinessPublishStatusEntity pstate = new BussinessPublishStatusEntity();
		pstate.setM_PublishStatus(PublishStatus.unpublished);
		item.setM_BussinessPublishStatusEntity(pstate);

		// 构建业务状态
		BussinessStatusEntity entity = new BussinessStatusEntity();
		entity.setStatus(EntityStatusMonitor.created);
		item.setM_BussinessStatusEntity(entity);
		leaseRequisitonSheetManager.addLeaseRequisitionSheet(item);
	}

	/**
	 * 修改租赁申请单的方法
	 * 
	 * @param newValues
	 *            新的数据对象
	 * @param itemID
	 *            对象的ID
	 * @return 租赁申请单对象
	 */
	public LeaseRequisitionSheet update(Map<String, Object> newValues,
			String itemID) {
		return leaseRequisitonSheetManager.updateleaseRequisitonSheet(
				newValues, itemID);
	}

	/**
	 * 删除租赁申请单的方法
	 * 
	 * @param 对象ID
	 */
	public void delete(String itemID) {
		this.leaseRequisitonSheetManager.deleteLeaseRequisitionSheet(itemID);
	}

	/**
	 * 根据条件查询租赁申请单的方法
	 * 
	 * @param values
	 *            新数据对象
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 集合对象
	 */
	public List<LeaseRequisitionSheet> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		if ("reload".equals(values.get("key"))) {
			return this.getList(startRow, endRow);
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
	public List<LeaseRequisitionSheet> getList(int startRow, int endRow) {
		List<LeaseRequisitionSheet> list = leaseRequisitonSheetManager
				.queryLeaseRequisitionSheetList(startRow, endRow);
		return list;
	}

}
