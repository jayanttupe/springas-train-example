package com.skynet.spms.persistence.entity.spmsdd;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.skynet.spms.persistence.entity.base.baseCodeEntity.BaseCodeEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 10-三月-2011 11:10:39
 */
@Entity
@DiscriminatorValue("ClearanceAgent")
public class ClearanceAgent extends BaseCodeEntity {

}