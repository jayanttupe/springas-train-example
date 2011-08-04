package com.skynet.spms.persistence.entity.partCatalog.salesCatalog.discountMatrix;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;

/**
 * 销售折扣矩阵，可有业务人员进行定义，系统将依据销售折扣矩阵的设定，提供备件的折扣服务。
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:22
 */
/*************************************************************************
 *Update by : Huhf    2011-4-21
 *CHECKED BY: 
 *Confirm by: 
 * 
************************************************************************ */
/*@Entity
 @FilterDefs({
		@FilterDef(name="active",
				defaultCondition="IS_DELETED = :isDele",
				parameters=@ParamDef(name="isDele",type="boolean"))		
	})
 @Filter(name="active")
 @Table(name = "SPMS_DISCOUNT_MATRIX")*/
 public class DiscountMatrix extends BaseEntity {
    private List<DiscountItem> m_DiscountItem;
    
	public List<DiscountItem> getM_DiscountItem() {
		return m_DiscountItem;
	}

	public void setM_DiscountItem(List<DiscountItem> m_DiscountItem) {
		this.m_DiscountItem = m_DiscountItem;
	}



}