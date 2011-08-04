package com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder;
/**
 * 物流装箱
 */
import javax.persistence.Entity;
import javax.persistence.Table;

import com.skynet.spms.datasource.annotation.ViewFormAnno;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.base.basePickupDeliveryOrder.BasePickupDeliveryVanning;

/**
 * @author huangdj
 * @version 1.0
 * @created 16-三月-2011 17:21:03
 */
@ViewFormAnno(value="id")
@Entity
@Table( name = "SPMS_PICKUP_DELIVERY_VANNING")
public class PickupDeliveryVanning extends BasePickupDeliveryVanning {

}