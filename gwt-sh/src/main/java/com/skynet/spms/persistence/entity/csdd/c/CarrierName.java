package com.skynet.spms.persistence.entity.csdd.c;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.skynet.spms.persistence.entity.base.baseCodeEntity.BaseCodeEntity;

/**
 * Identifies the name or type of a cargo carrier transporting the supplier's
 * shipment.
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:38
 */
@Entity
@DiscriminatorValue("CarrierName")
public class CarrierName extends BaseCodeEntity {

}