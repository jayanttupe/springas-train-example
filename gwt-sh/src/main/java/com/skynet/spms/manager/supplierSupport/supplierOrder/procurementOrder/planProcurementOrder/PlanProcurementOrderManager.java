package com.skynet.spms.manager.supplierSupport.supplierOrder.procurementOrder.planProcurementOrder;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.procurementOrder.procurementOrder.ProcurementOrder;

/**
 * 采购指令接口
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface PlanProcurementOrderManager {
	/**
	 * 
	 * 添加采购指令
	 * 
	 * @param  o 采购指令对象
	 * @return void
	 */
	public void addProcurementOrder(ProcurementOrder o);

	/**
	 * 
	 * 删除采购指令的方法
	 * 
	 * @param id 采购指令ID
	 * @return void
	 */
	public void deleteProcurementOrder(String id);

	/**
	 * 
	 * 修改采购指令
	 * 
	 * @param newValues 新数据对象
	 * @param itemID 采购指令ID
	 * @return 采购指令对象
	 */
	public ProcurementOrder updateProcurementOrder(
			Map<String, Object> newValues, String itemID);

	/**
	 * 
	 * 分页查询采购指令的方法
	 * 
	 * @param startRow 首页
	 * @param endRow 尾页
	 * @return 采购指令对象集合
	 */
	public List<ProcurementOrder> queryProcurementOrderList(int startRow,
			int endRow);

	/**
	 * 
	 * 分页查询执行采购指令的方法
	 * 
	 * @param startRow 首页
	 * @param endRow 尾页
	 * @return 采购指令对象集合
	 */
	public List<ProcurementOrder> queryDoProcuremetnOrderList(int startRow,
			int endRow);

}
