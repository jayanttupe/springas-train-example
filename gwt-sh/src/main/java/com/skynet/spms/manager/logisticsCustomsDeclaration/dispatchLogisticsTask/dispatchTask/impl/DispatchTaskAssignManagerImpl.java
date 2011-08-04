package com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.dispatchTask.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skynet.spms.manager.imp.CommonManagerImpl;
import com.skynet.spms.manager.logisticsCustomsDeclaration.dispatchLogisticsTask.dispatchTask.DispatchTaskAssignManager;
import com.skynet.spms.persistence.entity.logisticsCustomsDeclaration.pickupDeliveryOrder.PickupDeliveryTaskAssign;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class DispatchTaskAssignManagerImpl extends CommonManagerImpl<PickupDeliveryTaskAssign> implements DispatchTaskAssignManager{

}
