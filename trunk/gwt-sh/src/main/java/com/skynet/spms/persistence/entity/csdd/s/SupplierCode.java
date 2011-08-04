package com.skynet.spms.persistence.entity.csdd.s;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.skynet.spms.persistence.entity.base.baseCodeEntity.BaseCodeEntity;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-三月-2011 13:34:57
 */
@Entity
@DiscriminatorValue("SupplierCode")
public class SupplierCode extends BaseCodeEntity {
	
}