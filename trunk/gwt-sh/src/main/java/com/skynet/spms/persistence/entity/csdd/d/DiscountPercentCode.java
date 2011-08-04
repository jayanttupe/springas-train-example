package com.skynet.spms.persistence.entity.csdd.d;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * 缩写 DPC 长度 4位 N
 * Identifies the discount associated with a particular Product Category Code and
 * Customer Category Code.  The DPC will be sent to the customer as
 * PDP(Procurement Discount Percent) and the customer will apply this customized
 * PDP to the Unit Price Amount (UNP), the Price Break Quantity and Amount (PQA),
 * and to the Alternate Supply Location Text (ASL) where applicable.
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 16:06:31
 */
/*@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_DISCOUNT_PERCENT_CODE")
public class DiscountPercentCode extends BaseEntity {

}*/