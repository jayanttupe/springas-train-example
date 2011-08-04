package com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.repairOrder.repairInspectionOrder.customerDeliveryOrder;
import com.skynet.spms.persistence.entity.base.baseOrderEntity.BaseOrderEntity;
import com.skynet.spms.persistence.entity.spmsdd.Priority;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 03-四月-2011 11:32:41
 */
public class CustomerDeliveryOrder extends BaseOrderEntity {

	private String contractNumber;
	public Priority m_Priority;
	public CustomerIdentificationCode m_CustomerIdentificationCode;
	public CarrierName m_CarrierName;

}