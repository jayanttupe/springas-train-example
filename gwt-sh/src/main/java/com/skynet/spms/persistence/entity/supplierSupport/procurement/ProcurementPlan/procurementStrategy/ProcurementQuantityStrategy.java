package com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.procurementStrategy;

import java.util.Date;

/**
 * 采购数量策略，可以设定每次采购的数量约定，其中周期可以由用户自行来进行设定。
 * @author 曹宏炜
 * @version 1.0
 * @created 07-五月-2011 10:33:37
 */
public class ProcurementQuantityStrategy {

	private float quantity;
	private Date startDate;
	private Date expiryDate;
	public PeriodConditionStrategy m_PeriodConditionStrategy;

}