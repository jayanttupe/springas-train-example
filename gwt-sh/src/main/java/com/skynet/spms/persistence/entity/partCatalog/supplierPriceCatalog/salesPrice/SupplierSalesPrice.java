package com.skynet.spms.persistence.entity.partCatalog.supplierPriceCatalog.salesPrice;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.datasource.annotation.Reference;
import com.skynet.spms.persistence.entity.csdd.u.UnitOfMeasureCode;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.BasePrice;
import com.skynet.spms.persistence.entity.partCatalog.base.basePrice.PriceBreak;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:26
 */
/*************************************************************************
 *Update by : Huhf    2011-4-21
 *CHECKED BY:  
 *Confirm by: 
 * 
************************************************************************ */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name = "SPMS_SUPPLIER_SALES_PRICE")
public class SupplierSalesPrice extends BasePrice {
    
	private float partCatalogQuantity;//供应商件号数量
	private UnitOfMeasureCode pcqUnitCode; //供应商件号数量单位
	public List<PriceBreak> m_PriceBreak;//供应商数量分段价格

	@Column(name = "PART_CATALOG_QUANTITY")
	public float getPartCatalogQuantity() {
		return partCatalogQuantity;
	}

	public void setPartCatalogQuantity(float partCatalogQuantity) {
		this.partCatalogQuantity = partCatalogQuantity;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "PCQ_UNIT_TYPE")
	public UnitOfMeasureCode getPcqUnitCode() {
		return pcqUnitCode;
	}

	public void setPcqUnitCode(UnitOfMeasureCode pcqUnitCode) {
		this.pcqUnitCode = pcqUnitCode;
	}
	
	@Reference(itemCls=PriceBreak.class)
	@OneToMany()
	@JoinColumn(name = "PRICE_ID")
	public List<PriceBreak> getM_PriceBreak() {
		return m_PriceBreak;
	}

	public void setM_PriceBreak(List<PriceBreak> m_PriceBreak) {
		this.m_PriceBreak = m_PriceBreak;
	}

}