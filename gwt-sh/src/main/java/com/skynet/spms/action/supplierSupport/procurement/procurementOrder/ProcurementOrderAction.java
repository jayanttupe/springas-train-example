package com.skynet.spms.action.supplierSupport.procurement.procurementOrder;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.supplierOrder.procurementOrder.planProcurementOrder.PlanProcurementOrderManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.procurementOrder.procurementOrder.ProcurementOrder;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 采购指令控制器
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 * 
 */
@Component
public class ProcurementOrderAction implements
		DataSourceAction<ProcurementOrder> {

	public String[] getBindDsName() {

		return new String[] { DSKey.S_PROCUREMENTORDER_DS };
	}

	@Autowired
	private PlanProcurementOrderManager manager;

	@Resource
	UUIDGeneral uuidGeneral;

	/**
	 * 
	 * 添加采购指令
	 * 
	 * @param item
	 *            采购指令对象
	 * @return void
	 */
	public void insert(ProcurementOrder item) {
		item.setOrderNumber(uuidGeneral.getSequence("PI"));
		// 构建发布状态
		BussinessPublishStatusEntity publish = new BussinessPublishStatusEntity();
		publish.setM_PublishStatus(PublishStatus.unpublished);
		item.setM_BussinessPublishStatusEntity(publish);
		// 构建业务状态
		BussinessStatusEntity status = new BussinessStatusEntity();
		status.setStatus(EntityStatusMonitor.created);
		item.setM_BussinessStatusEntity(status);
		manager.addProcurementOrder(item);
	}

	/**
	 * 
	 * 修改采购指令
	 * 
	 * @param newValues
	 *            新数据对象
	 * @param itemID
	 *            采购指令ID
	 * @return 采购指令对象
	 */
	public ProcurementOrder update(Map<String, Object> newValues, String itemID) {

		return manager.updateProcurementOrder(newValues, itemID);
	}

	/**
	 * 
	 * 删除采购指令的方法
	 * 
	 * @param itemID
	 *            采购指令ID
	 * @return void
	 */
	public void delete(String itemID) {
		manager.deleteProcurementOrder(itemID);
	}

	/**
	 * 
	 * 根据条件查询采购指令的方法
	 * 
	 * @param values
	 *            新数据对象
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购计划对象集合
	 */
	public List<ProcurementOrder> doQuery(Map<String, Object> values,
			int startRow, int endRow) {

		if (("publish".equals(values.get("publishState")))) {
			return manager.queryDoProcuremetnOrderList(startRow, endRow);
		}
		return manager.queryProcurementOrderList(startRow, endRow);
	}

	/**
	 * 
	 * 分页查询采购指令的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购计划对象集合
	 */
	public List<ProcurementOrder> getList(int startRow, int endRow) {

		return manager.queryProcurementOrderList(startRow, endRow);
	}

}
