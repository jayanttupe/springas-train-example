package com.skynet.spms.persistence.entity.partCatalog.salesCatalog.discountMatrix;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.base.baseEntity.BaseEntity;
import com.skynet.spms.persistence.entity.csdd.c.CustomerCategoryCode;
import com.skynet.spms.persistence.entity.csdd.p.ProductCategoryCode;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:22
 */
/*************************************************************************
 * Update by : Huhf 2011-4-21 CHECKED BY: Confirm by:
 * 
 ************************************************************************ */
@Entity
@FilterDefs({ @FilterDef(name = "active", 
		defaultCondition = "IS_DELETED = :isDele", 
		parameters = @ParamDef(name = "isDele", type = "boolean")) })
@Filter(name = "active")
@Table(name = "SPMS_DISCOUNT_ITEM")
public class DiscountItem extends BaseEntity {
	
	private String discountItem;//折扣明细项
	private float m_DiscountPercentCode;//折扣百分比代码
	private ProductCategoryCode m_ProductCategoryCode;//产品分类代码
	private CustomerCategoryCode m_CustomerCategoryCode;//客户分类代码
	private String partSaleReleaseId;//销售发布信息
	
	
	@Column(name = "DISCOUNT_ITEM")
	public String getDiscountItem() {
		return discountItem;
	}
	public void setDiscountItem(String discountItem) {
		this.discountItem = discountItem;
	}		
	@Column(name = "PART_SALE_RELEASE_ID")
	public String getPartSaleReleaseId() {
		return partSaleReleaseId;
	}
	public void setPartSaleReleaseId(String partSaleReleaseId) {
		this.partSaleReleaseId = partSaleReleaseId;
	}

	@Column(name = "DISCOUNT_PERCENT_CODE")
	public float getM_DiscountPercentCode() {
		return m_DiscountPercentCode;
	}

	public void setM_DiscountPercentCode(float m_DiscountPercentCode) {
		this.m_DiscountPercentCode = m_DiscountPercentCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "PRODUCT_CATEGORY_CODE")
	public ProductCategoryCode getM_ProductCategoryCode() {
		return m_ProductCategoryCode;
	}

	public void setM_ProductCategoryCode(
			ProductCategoryCode m_ProductCategoryCode) {
		this.m_ProductCategoryCode = m_ProductCategoryCode;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "CUSTOMER_CATEGORY_CODE")
	public CustomerCategoryCode getM_CustomerCategoryCode() {
		return m_CustomerCategoryCode;
	}

	public void setM_CustomerCategoryCode(
			CustomerCategoryCode m_CustomerCategoryCode) {
		this.m_CustomerCategoryCode = m_CustomerCategoryCode;
	}

}