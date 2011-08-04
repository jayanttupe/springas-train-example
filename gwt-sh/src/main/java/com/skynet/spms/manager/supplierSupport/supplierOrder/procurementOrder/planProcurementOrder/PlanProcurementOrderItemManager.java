package com.skynet.spms.manager.supplierSupport.supplierOrder.procurementOrder.planProcurementOrder;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.procurementOrder.procurementOrder.ProcurementOrderItem;

/**
 * 采购指令明细接口
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface PlanProcurementOrderItemManager {
	/**
	 * 
	 * 添加采购指令明细
	 * 
	 * @param o
	 *            采购指令明细对象
	 * @return void
	 */
	public void addProcurementOrderItem(ProcurementOrderItem o);

	/**
	 * 
	 * 删除采购指令明细的方法
	 * 
	 * @param id
	 *            采购指令明细ID
	 * @return void
	 */
	public void deleteProcurementOrderItem(String id);

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
	public ProcurementOrderItem updateProcurementOrderItem(
			Map<String, Object> newValues, String itemID);

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
	public List<ProcurementOrderItem> queryProcurementOrderItemList(
			int startRow, int endRow);

	/**
	 * 
	 * 根据ID查询采购指令明细
	 * 
	 * @param id
	 *            采购指令明细的ID
	 * @return 采购指令明细对象集合
	 */
	public List<ProcurementOrderItem> queryProcurementOrderItemListById(
			String id);

	/**
	 * 
	 * 根据采购指令编号查询采购指令明细数据
	 * 
	 * @param orderNumber
	 *            采购指令编号
	 * @return 采购指令编号对象集合
	 */
	public List<ProcurementOrderItem> queryProcurementOrderListByOrderNumber(
			String orderNumber);
}
