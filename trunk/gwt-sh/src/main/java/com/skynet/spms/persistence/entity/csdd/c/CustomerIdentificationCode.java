package com.skynet.spms.persistence.entity.csdd.c;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.skynet.spms.persistence.entity.base.baseCodeEntity.BaseCodeEntity;

/**
 * Identifies the airline customer plus the office and/or individual receiving or
 * transmittin data sets.
 * @author 曹宏炜
 * @version 1.0
 * @created 18-三月-2011 13:34:39
 */
@Entity
@DiscriminatorValue("CustomerIdentification")
public class CustomerIdentificationCode extends BaseCodeEntity {

}