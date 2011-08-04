package com.skynet.spms.action.supplierSupport.procurement.procurementPaln;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skynet.spms.client.gui.supplierSupport.common.DSKey;
import com.skynet.spms.datasource.DataSourceAction;
import com.skynet.spms.manager.supplierSupport.procurement.ProcurementPaln.ProcurementPlanItem.ProcurementPlanItemManager;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.procurementPlanItem.ProcurementPlanItem;

/**
 * 采购指令明细的控制器
 * 
 * @author 赵小强
 * @version 1.0
 * @date 2011-7-11
 * 
 */
@Component
public class ProcurementPlanItemAction implements
		DataSourceAction<ProcurementPlanItem> {

	public String[] getBindDsName() {

		return new String[] { DSKey.S_PROCUREMENTPLANITEM_DS };
	}

	@Autowired
	private ProcurementPlanItemManager manager;

	/**
	 * 
	 * 添加采购计划明细
	 * 
	 * @param item
	 *            采购计划明细对象
	 * @return void
	 */

	public void insert(ProcurementPlanItem item) {
		manager.addProcurementPlanItem(item);
	}

	/**
	 * 
	 * 修改采购计划明细
	 * 
	 * @param newValues
	 *            新数据对象
	 * @param itemID
	 *            采购计划明细ID
	 * @return 采购计划明细对象
	 */
	public ProcurementPlanItem update(Map<String, Object> newValues,
			String itemID) {

		return manager.updateProcurementPlanItem(newValues, itemID);
	}

	/**
	 * 
	 * 删除采购计划明细的方法
	 * 
	 * @param itemID
	 *            采购计划明细ID
	 * @return void
	 */
	public void delete(String itemID) {

		manager.deleteProcurementPlanItem(itemID);
	}

	/**
	 * 
	 * 根据条件查询采购计划明细的方法
	 * 
	 * @param values
	 *            新数据对象
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购计划明细对象集合
	 */
	public List<ProcurementPlanItem> doQuery(Map<String, Object> values,
			int startRow, int endRow) {
		if (values.get("id") != null) {
			return manager.queryProcurementPlanItemListById(values.get("id")
					.toString());
		}
		if ("reload".equals(values.get("key"))) {
			return this.getList(startRow, endRow);
		}

		if (values.get("procurementPlanNumber") != null) {
			return manager.queryProcurementOrderByprocurementPlanNumber(values
					.get("procurementPlanNumber").toString());
		}
		return null;
	}

	/**
	 * 
	 * 分页查询采购计划明细的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购计划明细对象集合
	 */
	public List<ProcurementPlanItem> getList(int startRow, int endRow) {

		return manager.queryProcurementPlanItemList(startRow, endRow);
	}

}
