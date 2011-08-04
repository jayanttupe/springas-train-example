package com.skynet.spms.manager.supplierSupport.procurement.ProcurementPaln;

import java.util.List;
import java.util.Map;

import com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.ProcurementPlan;

/**
 * 采购计划
 * 
 * @version 1.0
 * @author 赵小强
 * @date 2011-07-011
 */
public interface ProcurementPlanManager {
	/**
	 * 
	 * 添加采购计划
	 * 
	 * @param o
	 *            采购计划对象
	 * @return void
	 */
	public void addProcurementPlan(ProcurementPlan o);

	/**
	 * 
	 * 删除采购计划的方法
	 * 
	 * @param id
	 *            采购计划ID
	 * @return void
	 */
	public void deleteProcurementPlan(String id);

	/**
	 * 
	 * 修改采购计划
	 * 
	 * @param newValues
	 *            新数据对象
	 * @param itemID
	 *            采购计划ID
	 * @return 采购计划对象
	 */
	public ProcurementPlan updateProcurementPlan(Map<String, Object> newValues,
			String itemID);

	/**
	 * 
	 * 分页查询采购计划的方法
	 * 
	 * @param startRow
	 *            首页
	 * @param endRow
	 *            尾页
	 * @return 采购计划对象集合
	 */
	public List<ProcurementPlan> queryProcurementPlanList(int startRow,
			int endRow);

	/**
	 * 
	 * 根据ID查询采购计划
	 * 
	 * @param id
	 *            采购几乎ID
	 * @return 采购计划对象集合
	 */
	public List<ProcurementPlan> queryProcurementById(String id);
}
