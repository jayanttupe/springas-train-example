package com.skynet.spms.persistence.entity.contractManagement.template.TransferSheetTemplate.ProcurementPlanTransferSheetTemplate;

import com.skynet.spms.persistence.entity.contractManagement.template.TransferSheetTemplate.BaseTransferSheetTemplate.BaseTransferSheetTemplate;
import com.skynet.spms.persistence.entity.csdd.c.CAGECode;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ConsigneeAddress;
import com.skynet.spms.persistence.entity.organization.enterpriseInformation.baseEnterpriseInformation.ShippingAddress;
import com.skynet.spms.persistence.entity.spmsdd.Priority;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:38
 */

public class ProcurementPlanTransferSheetItemTemplate extends
		BaseTransferSheetTemplate {

	private String procurementPlanNumber;
	// public PickupOrder m_PickupOrder;
	public CarrierName m_CarrierName;
	public ShippingAddress m_ShippingAddress;
	public CAGECode transferFrom;
	public Priority m_Priority;
	public ConsigneeAddress m_ConsigneeAddress;

}