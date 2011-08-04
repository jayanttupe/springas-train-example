package com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.repairOrder.repairInspectionOrder.supplierPickupOrder;
import com.skynet.spms.persistence.entity.base.baseOrderEntity.BaseOrderEntity;
import com.skynet.spms.persistence.entity.supplierSupport.supplierOrder.repairOrder.repairInspectionOrder.customerDeliveryOrder.CustomerDeliveryOrder;
import com.skynet.spms.persistence.entity.spmsdd.Priority;
import com.skynet.spms.persistence.entity.csdd.r.RepairShopCode;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 03-四月-2011 11:33:29
 */
public class SupplierPickupOrder extends BaseOrderEntity {

	private String contractNumber;
	private boolean isDependCustomerContract;
	public CustomerDeliveryOrder m_CustomerDeliveryOrder;
	public Priority m_Priority;
	public RepairShopCode m_RepairShopCode;
	public CarrierName m_CarrierName;

}