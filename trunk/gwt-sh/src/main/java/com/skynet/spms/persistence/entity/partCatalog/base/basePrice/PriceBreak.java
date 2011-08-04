package com.skynet.spms.persistence.entity.partCatalog.base.basePrice;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;

import com.skynet.spms.persistence.entity.partCatalog.base.baseQuantityPiecewiseQuotationItem.BaseQuantityPiecewiseQuotationItem;

/**
 * @author 曹宏炜
 * @version 1.0
 * @created 18-四月-2011 14:14:25
 */

/*************************************************************************
 *Update by : Huhf    2011-4-27
 *CHECKED BY: Shanyf  2011-4-27
 *Confirm by: 
 *Update list:
 * 
************************************************************************ */
@Entity
@FilterDefs({
	@FilterDef(name="active",
			defaultCondition="IS_DELETED = :isDele",
			parameters=@ParamDef(name="isDele",type="boolean"))		
})
@Filter(name="active")
@Table(name="SPMS_PRICE_BREAK")
public class PriceBreak extends BaseQuantityPiecewiseQuotationItem {

	private String priceId;
	
	@Column(name = "PRICE_ID")
	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}
}