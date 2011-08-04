package com.skynet.spms.persistence.entity.partCatalog.salesCatalog.salesPrice;
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
///@MappedSuperclass
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_SALES_PRICE")
public class SalesPrice extends BasePrice {

	private List<PriceBreak> m_PriceBreak;
    
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