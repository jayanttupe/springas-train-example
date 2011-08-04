package com.skynet.spms.action.supplierSupport.procurement.procurementOrder;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.supplierOrder.procurementOrder.planProcurementOrder.PlanProcurementOrderItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.procurementOrder.procurementOrder.ProcurementOrderItem;

/**
 * 采购指令明细项控制器
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 * 
 */
@Component
public class ProcurementOrderItemAction implements
		DataSourceAction<ProcurementOrderItem> {

	public String[] getBindDsName() {

		return new String[] { DSKey.S_PROCUREMENTORDERITEM_DS };
	}

	@Autowired
	private PlanProcurementOrderItemManager manager;

	/**
	 * 
	 * 添加采购指令明细
	 * 
	 * @param item
	 *            采购指令明细对象
	 * @return void
	 */
	public void insert(ProcurementOrderItem item) {
		manager.addProcurementOrderItem(item);
	}

	/**
	 * 
	 * 修改采购指令明细
	 * 
	 * @param newValues
	 *            新数据对象
	 * @param itemID
	 *            采购指令明细ID
	 * @return 采购指令明细对象
	 */
	public ProcurementOrderItem update(Map<String, Object> newValues,
			String itemID) {

		return manager.updateProcurementOrderItem(newValues, itemID);
	}

	/**
	 * 
	 * 删除采购指令明细的方法
	 * 
	 * @param itemID
	 *            采购指令明细ID
	 * @return void
	 */
	public void delete(String itemID) {

		manager.deleteProcurementOrderItem(itemID);
	}

	/**
	 * 
	 * 根据条件查询采购指令明细的的方法
	 * 
	 * @param values
	 *            新数据对象
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购计划明细对象集合
	 */
	public List<ProcurementOrderItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		if (values.get("id") != null) {
			return manager.queryProcurementOrderItemListById(values.get("id")
					.toString());
		} else if ("reload".equals(values.get("key"))) {
			return this.getList(startRow, endRow);
		} else if (values.get("orderNumber") != null) {
			return manager.queryProcurementOrderListByOrderNumber(values.get(
					"orderNumber").toString());
		}
		return null;
	}

	/**
	 * 
	 * 分页查询采购指令明细的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购计划明细对象集合
	 */
	public List<ProcurementOrderItem> getList(int startRow, int endRow) {

		return manager.queryProcurementOrderItemList(startRow, endRow);
	}

}
