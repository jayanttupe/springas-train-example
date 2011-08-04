package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.basePickupDeliveryOrder.BasePickupDeliveryTaskAssign;

/**
 * @author huangdj
 * @version 1.0
 * @created 16-三月-2011 17:21:03
 */
@ViewFormAnno(value="id")
@Entity
@Table( name = "SPMS_PICKUP_DELIVERY_ASSIGN")
public class PickupDeliveryTaskAssign extends BasePickupDeliveryTaskAssign {
	
}