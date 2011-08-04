/**
 * SupplyRepertoryAdapter.java
 * com.skynet.spms.action.customerService.salesService.salesRequisitionSheet
 *
 * TODO 
 *
 *   ver     date      		author
 * ──────────────────────────────────
 *   		 Jul 25, 2011 		Administrator
 *
 * Copyright (c) 2011, Tocersoft.
 */

package com.skynet.spms.action.customerService.salesService.salesRequisitionSheet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.common.gwt.GwtActionHelper;
import com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet.ModulePartIndexManager;
import com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet.SalesRequisitionSheetItemManager;
import com.skynet.spms.manager.supplierSupport.procurement.ProcurementPaln.ProcurementPlanManager;
import com.skynet.spms.manager.supplierSupport.procurement.ProcurementPaln.ProcurementPlanItem.ProcurementPlanItemManager;
import com.skynet.spms.manager.supplierSupport.supplierOrder.procurementOrder.planProcurementOrder.PlanProcurementOrderItemManager;
import com.skynet.spms.manager.supplierSupport.supplierOrder.procurementOrder.planProcurementOrder.PlanProcurementOrderManager;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessPublishStatusEntity;
import com.skynet.spms.persistence.entity.base.businessStatusEntity.BussinessStatusEntity;
import com.skynet.spms.persistence.entity.base.dataDictionary.EntityStatusMonitor;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheet;
import com.skynet.spms.persistence.entity.customerService.sales.salesRequisitionSheet.SalesRequisitionSheetItem;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.applicationData.PartApplicationData;
import com.skynet.spms.persistence.entity.partCatalog.technicalCatalog.indexInfomation.PartIndex;
import com.skynet.spms.persistence.entity.spmsdd.AuditStatus;
import com.skynet.spms.persistence.entity.spmsdd.Priority;
import com.skynet.spms.persistence.entity.spmsdd.ProcurementPlanType;
import com.skynet.spms.persistence.entity.spmsdd.PublishStatus;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.ProcurementPlan;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.procurementPlanItem.ProcurementPlanItem;
import com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.procurementOrder.procurementOrder.ProcurementOrder;
import com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.procurementOrder.procurementOrder.ProcurementOrderItem;
import com.skynet.spms.service.UUIDGeneral;

/**
 * 补库需求生成切面
 * 
 * @author taiqichao
 * @version 1.0
 * @Date Jul 25, 2011
 * 
 */
@Aspect
@Component
@Transactional
public class SupplyRepertoryAdapter {

	@Autowired
	private SalesRequisitionSheetItemManager salesRequisitionSheetItemManager;

	@Autowired
	private ModulePartIndexManager modulePartIndexManager;

	@Autowired
	private ProcurementPlanManager procurementPlanManager;

	@Autowired
	private ProcurementPlanItemManager procurementPlanItemManager;

	@Autowired
	private PlanProcurementOrderManager planProcurementOrderManager;

	@Autowired
	private PlanProcurementOrderItemManager planProcurementOrderItemManager;

	@Autowired
	private UUIDGeneral uuidGeneral;

	@AfterReturning(pointcut = "execution (* com.skynet.spms.manager.customerService.salesService.SalesRequisitionSheet.impl.SalesRequisitionSheetImpl.updateSalesRequisitionSheet(..))", returning = "retVal")
	public void create(Object retVal) {

		SalesRequisitionSheet sheet = (SalesRequisitionSheet) retVal;

		// 判断是否发布了
		if (!sheet.getM_BussinessPublishStatusEntity().getM_PublishStatus()
				.equals(PublishStatus.published)) {

			return;

		}

		// 取出订单明细
		List<SalesRequisitionSheetItem> items = salesRequisitionSheetItemManager
				.querySalesRequisitionSheetItemListByRequisitionId(sheet
						.getId());

		// 采购计划需求
		ProcurementPlan procurementPlan = initProcurementPlan();

		// 采购计划明细
		List<ProcurementPlanItem> procurementPlanItems = new ArrayList<ProcurementPlanItem>();

		// AOG采购指令
		ProcurementOrder planProcurementOrder = initProcurementOrder();

		// AOG采购指令明细
		List<ProcurementOrderItem> procurementOrderItems = new ArrayList<ProcurementOrderItem>();

		for (SalesRequisitionSheetItem item : items) {

			PartIndex partIndex = modulePartIndexManager
					.getPartIndexByPartNumber(item.getPartNumber());

			PartApplicationData data = partIndex.getM_PartApplicationData();

			// 备件的现库存数量(从库存表中取出)
			Long partindexInventory = modulePartIndexManager
					.getPartIndexNumberFromInventoryRecord(item.getPartNumber());

			// 客户订购数量(订单约定某个明细备件的数量)
			Float itemQt = item.getQuantity();

			// 结果数量
			Float resultNumber = partindexInventory - itemQt;

			// 再订货点
			int reorderLine = data.getReorderLine();

			// 安全库存点
			int safeStockLine = data.getSafeStockLine();

			// 再订货量
			int reorderQuantity = data.getReorderQuantity();

			// 库存进行补库数量,自动按照（再订货量+再订货点-目前库存）
			long supplyRepertoryNumber = reorderQuantity + reorderLine
					- partindexInventory;

			// 结果数量>安全库存&&结果数量<=再订货点
			if ((resultNumber > safeStockLine) && (resultNumber <= reorderLine)) {
				// 采购计划需求（ProcurementPlanItem）
				ProcurementPlanItem procurementPlanItem = new ProcurementPlanItem();

				procurementPlanItem
						.setItemNumber(procurementPlanItems.size() + 1);

				procurementPlanItem.setPartNumber(item.getPartNumber());

				procurementPlanItem.setPlannedQuantity(supplyRepertoryNumber);

				procurementPlanItem.setItemKeyword("系统自动补库");

				procurementPlanItems.add(procurementPlanItem);

			} else if (resultNumber <= safeStockLine) {// 结果数量<=安全数量
				// AOG采购指令(ProcurementOrderItem)
				ProcurementOrderItem procurementOrderItem = new ProcurementOrderItem();

				procurementOrderItem
						.setItemNumber(procurementOrderItems.size() + 1);

				procurementOrderItem.setPartNumber(item.getPartNumber());

				procurementOrderItem.setPlannedQuantity(supplyRepertoryNumber);

				procurementOrderItem.setItemKeyword("系统自动补库");

				procurementOrderItems.add(procurementOrderItem);
			}

		}

		// 持久化处理

		if (procurementPlanItems.size() > 0) {
			procurementPlanManager.addProcurementPlan(procurementPlan);
			for (ProcurementPlanItem item : procurementPlanItems) {
				item.setProcurementPlan(procurementPlan);
				procurementPlanItemManager.addProcurementPlanItem(item);
			}
		}

		if (procurementOrderItems.size() > 0) {
			planProcurementOrderManager
					.addProcurementOrder(planProcurementOrder);
			for (ProcurementOrderItem item : procurementOrderItems) {
				item.setProcurementOrder(planProcurementOrder);
				planProcurementOrderItemManager.addProcurementOrderItem(item);
			}
		}

	}

	/**
	 * 初始化采购计划
	 * 
	 * @return
	 */
	private ProcurementPlan initProcurementPlan() {

		ProcurementPlan procurementPlan = new ProcurementPlan();

		procurementPlan.setCreateBy(GwtActionHelper.getCurrUser());

		procurementPlan.setCreateDate(new Date());

		procurementPlan.setProcurementPlanNumber(uuidGeneral.getSequence("PL"));

		// 构建审批状态
		procurementPlan.setAuditStatus(AuditStatus.create);

		// 构建发布状态
		BussinessPublishStatusEntity publish = new BussinessPublishStatusEntity();
		publish.setM_PublishStatus(PublishStatus.unpublished);
		procurementPlan.setM_BussinessPublishStatusEntity(publish);

		// 投建业务状态
		BussinessStatusEntity status = new BussinessStatusEntity();
		status.setStatus(EntityStatusMonitor.created);
		procurementPlan.setM_BussinessStatusEntity(status);

		// 采购计划类型
		procurementPlan.setM_ProcurementPlanType(ProcurementPlanType.temporary);

		// 计划开始时间
		procurementPlan.setStartDate(new Date());

		// 计划结束时间 当前时间推后一个月
		// procurementPlan.setEndDate();

		// 计划用途
		procurementPlan.setPurposes("补库需求");

		// 计划描述
		procurementPlan.setDescription("销售订单产生补库需求");

		// 备注
		procurementPlan.setRemarkText("补库需求");

		return procurementPlan;
	}

	/**
	 * 初始化AOG采购指令
	 * 
	 * @return
	 */
	public ProcurementOrder initProcurementOrder() {
		ProcurementOrder order = new ProcurementOrder();
		order.setOrderNumber(uuidGeneral.getSequence("PI"));
		// 构建发布状态
		BussinessPublishStatusEntity publish = new BussinessPublishStatusEntity();
		publish.setM_PublishStatus(PublishStatus.unpublished);
		order.setM_BussinessPublishStatusEntity(publish);
		// 构建业务状态
		BussinessStatusEntity status = new BussinessStatusEntity();
		status.setStatus(EntityStatusMonitor.created);
		order.setM_BussinessStatusEntity(status);
		order.setM_Priority(Priority.AOG);
		order.setKeyword("系统自动生成");
		order.setDescription("系统自动生成");
		return order;
	}

}
