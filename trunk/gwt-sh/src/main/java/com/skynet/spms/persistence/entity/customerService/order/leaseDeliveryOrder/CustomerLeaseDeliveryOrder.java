package com.skynet.spms.persistence.entity.customerService.order.leaseDeliveryOrder;
import java.util.List;

import com.skynet.spms.persistence.entity.base.baseOrderEntity.BaseOrderEntity;
import com.skynet.spms.persistence.entity.csdd.c.CarrierName;
import com.skynet.spms.persistence.entity.csdd.c.CustomerIdentificationCode;
import com.skynet.spms.persistence.entity.customerService.LeaseService.leaseContract.LeaseContractItem;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 05-五月-2011 11:15:15
 */
public class CustomerLeaseDeliveryOrder extends BaseOrderEntity {

	private String contractNumber;
	public CarrierName m_CarrierName;
	public CustomerIdentificationCode m_CustomerIdentificationCode;
	public List<LeaseContractItem> m_LeaseContractItem;

}