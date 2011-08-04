package com.skynet.spms.persistence.entity.supplierSupport.procurement.partRequirementHandle;

import com.skynet.spms.persistence.entity.base.PartRequirementHandleStatusEntity;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.ProcurementPlan;
import com.skynet.spms.persistence.entity.supplierSupport.procurement.ProcurementPlan.procurementPlanItem.ProcurementPlanItem;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 07-五月-2011 10:33:31
 */
public class PartRequirementHandle extends BaseEntity {

	private String partRequirementNumber;
	public PartRequirementHandleStatusEntity m_PartRequirementHandleStatusEntity;
	public ProcurementPlanItem m_ProcurementPlanItem;
	public ProcurementPlan m_ProcurementPlan;

}