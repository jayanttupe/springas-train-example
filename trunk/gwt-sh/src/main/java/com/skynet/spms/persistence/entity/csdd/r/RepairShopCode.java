package com.skynet.spms.persistence.entity.csdd.r;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.skynet.spms.persistence.entity.base.baseCodeEntity.BaseCodeEntity;

/**
 * Identifies the repair agency for the subject part in warranty administration.
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:11:02
 */
@Entity
@DiscriminatorValue("RepairShopCode")
public class RepairShopCode extends BaseCodeEntity {
    
}