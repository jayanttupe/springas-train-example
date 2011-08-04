package com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.procurementPlanItem;

import java.util.List;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 07-五月-2011 10:33:36
 */
public class ProcurementAnalysisData {

	private String partNumber;
	private float floatingAmount;
	private float annualUsageAmount;
	private float storage;
	/**
	 * 做为知识库支持备注文字描述
	 */
	private String theoryTurnover;
	/**
	 * 做为知识库支持备注文字描述
	 */
	private String theoryPlanPurchaseQuantity;
	public List<ProcurementPlanItem> m_ProcurementPlanItem;

}